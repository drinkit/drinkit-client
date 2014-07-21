package ua.kiev.naiv.drinkit.springconfig;

import org.flywaydb.core.Flyway;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.Resource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("ua.kiev.naiv.drinkit.cocktail.persistence.repository")
@ComponentScan("ua.kiev.naiv.drinkit.cocktail.service")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Resource
    Environment env;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSource.setUrl(env.getRequiredProperty("db.url"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));
        Properties properties = new Properties();
        properties.setProperty("characterEncoding", "utf8");
        dataSource.setConnectionProperties(properties);
        return dataSource;
    }

    @Bean
    public Flyway flyway(){
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.setCleanOnValidationError(true);
        flyway.migrate();
        return flyway;
    }

    @Bean
    @DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean sessionFactory = new LocalContainerEntityManagerFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ua.kiev.naiv.drinkit.cocktail.persistence.model");
        sessionFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        sessionFactory.setJpaProperties(hibProperties());
        return sessionFactory;
    }

    private Properties hibProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql", "false"));
        hibernateProperties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql", "false"));
        return hibernateProperties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
