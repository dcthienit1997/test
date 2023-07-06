package com.sbc.bbx.sample.configurations;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.integration.spring.SpringResourceAccessor;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
public class LiquibaseConfiguration implements InitializingBean {

  @Autowired
  LiquibaseProperties properties;

  @Autowired
  ResourceLoader resourceLoader;

  @Override
  public void afterPropertiesSet() throws Exception {
    Database openDatabase = DatabaseFactory
      .getInstance()
      .openDatabase(properties.getUrl(), null, null, null, null, null);

    try (
      Liquibase liquibase = new Liquibase(
        properties.getChangeLog(),
        new SpringResourceAccessor(resourceLoader),
        openDatabase
      )
    ) {
      liquibase.update(new Contexts());
    }
  }
}
