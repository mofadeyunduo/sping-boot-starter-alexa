package per.piers.alexa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AlexaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlexaApplication.class, args);
    }

}