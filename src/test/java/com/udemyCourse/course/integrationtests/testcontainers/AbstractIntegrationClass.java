package com.udemyCourse.course.integrationtests.testcontainers;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractIntegrationClass.Initializer.class)
public class AbstractIntegrationClass {


    public static class Initializer implements ApplicationContextInitializer <ConfigurableApplicationContext>{
        static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:lts");
        private void startContainers() {
            Startables.deepStart(Stream.of(mysql)).join();
        }
        private Map<String, String> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url",  mysql.getJdbcUrl() ,
                    "spring.datasource.url" , mysql.getUsername(),
                    "spring.datasource.password", mysql.getPassword()

            );
        }
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testContainers = new MapPropertySource("testcontainers",
                    (Map) createConnectionConfiguration());
            environment.getPropertySources().addFirst(testContainers);
        }




    }
}
