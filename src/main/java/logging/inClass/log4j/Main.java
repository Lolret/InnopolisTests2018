package logging.inClass.log4j;

import org.apache.log4j.Logger;

public class Main {
    final  static Logger logger = Logger.getLogger(Main.class);


    public static void main(String[] args) {
        logger.debug("debug-message");

        for (int i=0; i < 10_000_000; i++) {
            logger.info("info-message");
        }
    }
}