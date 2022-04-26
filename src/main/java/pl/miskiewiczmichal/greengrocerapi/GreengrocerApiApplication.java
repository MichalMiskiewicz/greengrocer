package pl.miskiewiczmichal.greengrocerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GreengrocerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreengrocerApiApplication.class, args);
	}

}
