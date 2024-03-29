package production.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static production.main.Main.logger;

public class LoggerMethods {
    public static final Logger consoleLogger = LoggerFactory.getLogger("Bencic-3.production.util.LoggerMethods");
    public static void writeInConsoleWithLogger(String string){
        if(consoleLogger.isInfoEnabled()){
            consoleLogger.info(string);
        }
    }
    public static void writeInFIleWithLogger(String string){
       logger.error(string);
    }
}
