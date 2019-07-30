package com.monese.api.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author C.SajjadHussain
 *
 */

@SpringBootApplication
@PropertySource(value = "classpath:application.yml")
public class MoneseTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoneseTestApplication.class, args);
	}
}