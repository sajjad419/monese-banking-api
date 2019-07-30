package com.monese.api.banking;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author C.SajjadHussain
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
	MessageSource messageSource;
	 
	@Autowired
	private TypeResolver typeResolver;
	
	@Value("${monese.swagger.groupname}")
	private String groupName;
	@Value("${monese.swagger.title}")
	private String title;
	@Value("${monese.swagger.description}")
	private String description;
	@Value("${monese.swagger.version}")
	private String version;
	@Value("${monese.swagger.license}")
	private String license;
	@Value("${monese.swagger.licenseurl}")
	private String licenseUrl;

	@Bean
	public Docket moneseDocApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName(groupName)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(regex("/*.*"))
				.build()
				.pathMapping("/")
				.directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(
						newRule(typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
								typeResolver.resolve(WildcardType.class)))
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(title)
				.description(description)
				.version(version)
				.license(license)
				.licenseUrl(licenseUrl)
				.build();
	}
}