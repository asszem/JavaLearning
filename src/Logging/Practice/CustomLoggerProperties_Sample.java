package Logging.Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CustomLoggerProperties_Sample {

	public static void main(String[] args) {
		// Open a custom bankLogger setup
		try {
			LogManager.getLogManager()
					.readConfiguration(new FileInputStream("J:/Logs/AndrasCustomLoggingSetup.properties"));
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Logger logger = Logger.getLogger("");
		for (int i = 0; i < 10; i++) {
			logger.info("Logging iteration " + i);
		}

	}
}
