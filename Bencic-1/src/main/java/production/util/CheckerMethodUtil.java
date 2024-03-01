package production.util;

import java.math.BigDecimal;

public class CheckerMethodUtil {
    public static boolean bigDecimalChecker(BigDecimal input) {
        return input.compareTo(new BigDecimal("0")) <= 0;
    }

    public static boolean integerChecker(Integer input, Integer maxNumber) {
        return !(input > 0 && input <= maxNumber);
    }
}
