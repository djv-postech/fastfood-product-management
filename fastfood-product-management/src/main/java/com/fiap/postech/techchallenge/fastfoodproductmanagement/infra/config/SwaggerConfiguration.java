package com.fiap.postech.techchallenge.fastfoodproductmanagement.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().info(new Info().title("API de Gerenciamento de Produtos")
            .description("API reponsável por fazer o gerenciamento de produtos, estoque e precificação do fastfood")
            .version("1.0.0"));
  }
}
