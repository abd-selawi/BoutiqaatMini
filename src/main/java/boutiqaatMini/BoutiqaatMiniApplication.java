package boutiqaatMini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@EnableFeignClients
public class BoutiqaatMiniApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoutiqaatMiniApplication.class, args);
	}

}