package JMC.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecommendationApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(RecommendationApplication.class, args);
		} catch (Throwable t) {
			System.err.println("Error al iniciar la aplicación:");
			t.printStackTrace();
			// Re-lanzar para que el proceso muera con código de error (opcional)
			throw t;
		}
	}

}
