package com.peppermint.usermanagementsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableSwagger2
//@Profile("dev")
public class SpringfoxConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("PepperMint API Version 1")
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
                .directModelSubstitute(ZonedDateTime.class, String.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.peppermint"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .apiInfo(metadata("PepperMint API Version 1"))
                .useDefaultResponseMessages(false)
                .genericModelSubstitutes(Optional.class)
                .globalOperationParameters(parameterBuilder())
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private List<Parameter> parameterBuilder() {
        //Adding Header
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Accept-Language")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("en")
                .build();
        List<Parameter> aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());
        return aParameters;
    }

    /**
     * OAUTH2 Security Configuration
     *
     * @return
     */
    private OAuth securitySchema() {

        List<AuthorizationScope> authorizationScopeList = new ArrayList();
        authorizationScopeList.add(new AuthorizationScope("read", "read all"));
        authorizationScopeList.add(new AuthorizationScope("write", "write all"));
        authorizationScopeList.add(new AuthorizationScope("trust", "trust all"));
        authorizationScopeList.add(new AuthorizationScope("openid", "openid"));

        List<GrantType> grantTypes = new ArrayList();
        ResourceOwnerPasswordCredentialsGrant passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant("http://192.168.103.104:8090/oauth/token");

        grantTypes.add(passwordCredentialsGrant);
        return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.ant("/api/v1/**"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[4];
        authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
        authorizationScopes[2] = new AuthorizationScope("write", "write all");
        authorizationScopes[3] = new AuthorizationScope("openid", "openid");
        return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
    }

    private ApiInfo metadata(String title) {
        return new ApiInfoBuilder()
                .title(title) //
                .description(title + ". you should click on the right top button `Authorize` and introduce it with the prefix \"Bearer \".")//
                .version("1.0.0")
                .license("")
                .licenseUrl("")
                .contact(new Contact(title, null, "info@peppermint.com"))//
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(true)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }
}
