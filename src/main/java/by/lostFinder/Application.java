package by.lostFinder;

/**
 * Created by operb_000 on 07.10.2015.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ComponentScan
@EnableJpaRepositories(basePackages = "by.lostFinder")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

