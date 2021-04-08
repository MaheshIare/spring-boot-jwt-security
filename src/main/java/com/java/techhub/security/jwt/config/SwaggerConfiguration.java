/**
 * 
 */
package com.java.techhub.security.jwt.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author mahes
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	@Value("${server.servlet.context-path}")
	private String contextPath;

	@Value("${swagger.documentation.title}")
	private String apiTitle;

	@Value("${swagger.documentation.description}")
	private String apiDescription;

	@Value("${swagger.documentation.version}")
	private String apiVersion;

	@Value("${swagger.documentation.termsofservice.url}")
	private String apiTermsOfServiceUrl;

	@Value("${swagger.documentation.contact.name}")
	private String contactName;

	@Value("${swagger.documentation.contact.website}")
	private String contactSite;

	@Value("${swagger.documentation.contact.email}")
	private String contactEmail;

	@Value("${swagger.documentation.license.name}")
	private String licenseName;

	@Value("${swagger.documentation.license.url}")
	private String licenseUrl;

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
			Arrays.asList("application/json"));

	@Bean
	public Docket userApi() {
		return new Docket(DocumentationType.SWAGGER_2).produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(regex(contextPath + ".*")).build().securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey())).apiInfo(metaData());
	}

	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().docExpansion(DocExpansion.LIST).build();
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfoBuilder().title(apiTitle).description(apiDescription).version(apiVersion)
				.termsOfServiceUrl(apiTermsOfServiceUrl).contact(new Contact(contactName, contactSite, contactEmail))
				.license(licenseName).licenseUrl(licenseUrl).build();
		return apiInfo;
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

}
