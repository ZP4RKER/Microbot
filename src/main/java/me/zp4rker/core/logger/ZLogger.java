package me.zp4rker.core.logger;


import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ZP4RKER
 */
public class ZLogger {

    private static Logger logger = Logger.getLogger("ZLogger");

    public static void initialise() {
        try {
            ConsoleHandler cHandler = new ConsoleHandler();
            cHandler.setFormatter(new ZFormatter());
            logger.addHandler(cHandler);
            logger.setUseParentHandlers(false);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }

    public static void info(String message) {
        logger.info(message + "\n\n");
    }

    public static void warn(String message) {
        logger.warning(message + "\n\n");
    }

    public static void debug(String message) {
        logger.log(Level.OFF, "[DEBUG] " + message + "\n\n");
    }

    public static void blankLine() {
        System.out.println();
    }

}
