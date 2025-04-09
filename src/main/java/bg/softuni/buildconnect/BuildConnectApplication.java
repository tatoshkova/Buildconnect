package bg.softuni.buildconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("bg.softuni.buildconnect.entity")
@EnableJpaRepositories("bg.softuni.buildconnect.repository")
public class BuildConnectApplication {
	public static void main(String[] args) {
		SpringApplication.run(BuildConnectApplication.class, args);
	}
}
