package hr.eestec_zg.populator;

import hr.eestec_zg.populator.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Populator {
    private static final Logger logger = LoggerFactory.getLogger(Populator.class);

    public static void main(String[] args) {
        logger.info("Starting...");

        if (System.getProperty("spring.profiles.active") == null) {
            System.setProperty("spring.profiles.active", "production");
        }

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        ctx.close();

        logger.info("Finishing...");
    }

}
