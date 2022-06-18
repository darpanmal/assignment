package algo;

import algo.trigram.TrigramAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class TrigramApplication implements CommandLineRunner {

	@Autowired
	private TrigramAlgorithm trigramAlgorithm;

	public static void main(String[] args) {
		SpringApplication.run(TrigramApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		trigramAlgorithm.trigramAlgorithm();
	}

}
