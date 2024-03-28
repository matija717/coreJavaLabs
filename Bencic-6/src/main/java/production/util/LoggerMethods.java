package production.util;

import static production.main.Main.consoleLogger;
import static production.main.Main.logger;

public class LoggerMethods {
    private LoggerMethods(){
        throw new AssertionError("Logger method should not be instantiated");
    }
    public static void writeInConsoleWithLogger(String string){
        if(consoleLogger.isInfoEnabled()){
            consoleLogger.info(string);
        }
    }
    public static void writeInFIleWithLogger(String string){
        if(logger.isErrorEnabled()){
            logger.error(string);
        }
    }
}
