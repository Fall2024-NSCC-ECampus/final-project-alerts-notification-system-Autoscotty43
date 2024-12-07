/**
 * Created By; Jared Scott 12/3/2024
 */
package ca.nscc.alertnotificaionfinal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertNotificaionFinalApplication {

	private static final Logger logger = LoggerFactory.getLogger(AlertNotificaionFinalApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AlertNotificaionFinalApplication.class, args);
		logger.info("Alert Notification Final Application has started successfully.");
	}

}