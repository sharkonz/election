
/*
 * This project created by Eden Charcon, for Cropx company as a Test.
 *01/08/2019
 * */

package cropx.election.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
@ComponentScan(basePackages = "cropx.election")
public class ApplicationConfig {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        MongoClient mongoClient = new MongoClient(Constants.LOCAL_HOST, Constants.MONGO_DB_PORT);
        return new SimpleMongoDbFactory(mongoClient,Constants.CROPX_DATA_BASE);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }


}
