package com.heuron.heuroncloud.global.swagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocsConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .addServersItem(new Server().url("/"))
            .info(new Info().title("HEURON REST API")
                .description("서버에서 제공하는 REST API 스펙을 기술합니다")
                .contact(new Contact()
                    .name("Back-End Team Email")
                    .email("wjdrhkddud2@gmail.com")
                )
                .version("v0.0.1")).externalDocs(new ExternalDocumentation());
    }

}
