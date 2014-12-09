package guru.drinkit.springconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

@Configuration
@ComponentScan("guru.drinkit.cocktail.service")
@PropertySource("classpath:application.properties")
//@EnableTransactionManagement
@Import(MongoConfig.class)
public class AppConfig {

    @Resource
    Environment env;

//    @Bean
//    public DriverManagerDataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
//        dataSource.setUrl(env.getRequiredProperty("db.url"));
//        dataSource.setUsername(env.getRequiredProperty("db.username"));
//        dataSource.setPassword(env.getRequiredProperty("db.password"));
//        Properties properties = new Properties();
//        properties.setProperty("characterEncoding", "utf8");
//        dataSource.setConnectionProperties(properties);
//        return dataSource;
//    }
//
//    @Bean
//    public Flyway flyway(){
//        Flyway flyway = new Flyway();
//        flyway.setDataSource(dataSource());
//        flyway.setCleanOnValidationError(true);
//        flyway.migrate();
//        return flyway;
//    }
//
//    @Bean
//    @DependsOn("flyway")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean sessionFactory = new LocalContainerEntityManagerFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("guru.drinkit.cocktail.persistence.entity");
//        sessionFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        sessionFactory.setJpaProperties(hibProperties());
//        return sessionFactory;
//    }
//
//    private Properties hibProperties() {
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
//        hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql", "false"));
//        hibernateProperties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql", "false"));
//        return hibernateProperties;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return transactionManager;
//    }
}
