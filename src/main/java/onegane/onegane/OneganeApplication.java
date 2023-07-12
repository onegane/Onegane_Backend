package onegane.onegane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = {"onegane.onegane.global.jwt", "onegane.onegane.global.config.security.auth"})
public class OneganeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OneganeApplication.class, args);
    }

}
