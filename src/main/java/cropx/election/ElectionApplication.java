
/*
 * This project created by Eden Charcon, for Cropx company as a Test.
 *01/08/2019
 * */

package cropx.election;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@PropertySource("application.properties")
@ComponentScan("cropx")
@EntityScan("cropx")
@EnableTransactionManagement
@SpringBootApplication
public class ElectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectionApplication.class, args);

	}
}
