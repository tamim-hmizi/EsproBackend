package tn.esprit.esprobackend.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class PythonScriptRunner {

    private String downloadPythonScript() throws IOException {
        // Download the script to a temporary location
        URL url = new URL("http://localhost/hackathon_tunisia_scraps.py");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        File tempScript = File.createTempFile("script", ".py");
        try (InputStream in = connection.getInputStream();
             FileOutputStream out = new FileOutputStream(tempScript)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        return tempScript.getAbsolutePath();
    }

    @Scheduled(fixedDelay = 1000000)
    public void runPythonScript() {
        try {
            // Download the script and get its path
            String scriptPath = downloadPythonScript();
            ProcessBuilder processBuilder = new ProcessBuilder("python", scriptPath);

            Process process = processBuilder.start();

            // Read the standard output
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Read the error stream to capture errors
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    System.err.println("Error: " + errorLine);
                }
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Process exited with error code: " + exitCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
