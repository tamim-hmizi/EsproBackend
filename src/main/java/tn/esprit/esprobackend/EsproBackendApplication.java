package tn.esprit.esprobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EsproBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsproBackendApplication.class, args);
    }

}
