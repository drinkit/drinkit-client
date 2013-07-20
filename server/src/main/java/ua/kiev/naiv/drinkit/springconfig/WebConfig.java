package ua.kiev.naiv.drinkit.springconfig;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel Kolmykov
 * Date: 18.07.13
 * Time: 22:27
 */
@Configuration
@ComponentScan("ua.kiev.naiv.drinkit.cocktail")
@EnableJpaRepositories("ua.kiev.naiv.drinkit.cocktail.repository")
public class WebConfig {

//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver resolver =
//                new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/view/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }

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
}
