package kr.goodchoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CursorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CursorApplication.class, args);
    }

}
