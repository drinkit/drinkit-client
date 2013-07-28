package ua.kiev.naiv.drinkit.springconfig;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 21.07.13
 * Time: 14:28
 */
@Configuration
@ComponentScan("ua.kiev.naiv.drinkit.cocktail")
@EnableJpaRepositories("ua.kiev.naiv.drinkit.cocktail.repository")
@EnableAspectJAutoProxy
public class TestConfig {


    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/cocktail");
        dataSource.setUsername("drinkit");
        dataSource.setPassword("drinkit");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean sessionFactory = new LocalContainerEntityManagerFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ua.kiev.naiv.drinkit.cocktail.model");
        sessionFactory.setPersistenceProviderClass(HibernatePersistence.class);
        sessionFactory.setJpaProperties(hibProperties());
        return sessionFactory;
    }

    private Properties hibProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", MySQL5Dialect.class.getCanonicalName());
        hibernateProperties.setProperty("hibernate.show_sql", Boolean.TRUE.toString());
        return hibernateProperties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public TestHelper testHelper() {
        return new TestHelper();
    }
}