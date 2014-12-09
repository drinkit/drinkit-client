package guru.drinkit.springconfig;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import guru.drinkit.repository.RepositoryPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.Resource;

/**
 * Created by pkolmykov on 12/8/2014.
 */
@Configuration
@EnableMongoRepositories(basePackageClasses = RepositoryPackage.class)
public class MongoConfig extends AbstractMongoConfiguration{

    @Resource
    Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getRequiredProperty("mongo.dbname");
    }

    @Override
    @Bean
    public MongoClient mongo() throws Exception {
        MongoClient client = new MongoClient("localhost");
        client.setWriteConcern(WriteConcern.SAFE);//todo figure out
        return client;
    }

    @Override
    protected String getMappingBasePackage() {
        return "guru.drinkit.domain";
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }
}
