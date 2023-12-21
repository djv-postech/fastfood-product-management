package com.fiap.postech.fastfood.product.management.infra.persistence;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = "com.fiap.postech.fastfoodsysteminfra.persistence")
@EnableMongoRepositories
public class JpaConfiguration {}
