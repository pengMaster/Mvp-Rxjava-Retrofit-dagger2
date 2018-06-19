package vip.retail.kotlin.app.utils;

import java.util.Collection;
import java.util.Map;

public class CheckUtils {

    /**
     * 任意一个为 null 返回 true
     */
    public static boolean isAnyEmpty(Object... obj) {
        boolean isAnyEmpty;
        for (Object o : obj) {
            if (o instanceof CharSequence) {
                isAnyEmpty = isEmpty(o.toString());
            } else if (o instanceof Object[]) {
                isAnyEmpty = obj.length == 0;
            } else if (o instanceof Collection) {
                isAnyEmpty = isEmpty((Collection<?>) o);
            } else if (o instanceof Map) {
                isAnyEmpty = isEmpty((Map<?, ?>) o);
            } else {
                isAnyEmpty = isNull(o);
            }
            if (isAnyEmpty) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(CharSequence str) {
        return isNull(str) || str.length() == 0 || "null".equals(str);
    }

    public static boolean isEmpty(Object[] obj) {
        return isNull(obj) || obj.length == 0;
    }

    public static boolean isEmpty(Collection<?> list) {
        return isNull(list) || list.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isType(String str, String regex) {
        return str != null && str.matches(regex);
    }

    public static boolean isNoMinusInt(String str) {
        return isType(str, "^\\d+$");
    }

    public static boolean isPositiveInt(String str) {
        return isType(str, "^[0-9]*[1-9][0-9]*$");
    }

    public static boolean isNoPositiveInt(String str) {
        return isType(str, "^((-\\d+)|(0+))$");
    }

    public static boolean isMinusInt(String str) {
        return isType(str, "^-[0-9]*[1-9][0-9]*$");
    }

    public static boolean isInt(String str) {
        return isType(str, "^-?\\d+$");
    }

    public static boolean isNoMinusDouble(String str) {
        return isType(str, "^\\d+(\\.\\d+)?$");
    }

    public static boolean isPositiveDouble(String str) {
        return isType(str, "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$");
    }

    public static boolean isNoPositiveDouble(String str) {
        return isType(str, "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$");
    }

    public static boolean isMinusDouble(String str) {
        return isType(str, "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$");
    }

    public static boolean isDouble(String str) {
        return isType(str, "^(-?\\d+)(\\.\\d+)?$");
    }

    public static boolean isEmail(String str) {
        return isType(str, "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
    }

    public static boolean isPhone(String str) {
        return isType(str, "^[0-9][0-9,\\-]+[0-9]$");
    }

    public static boolean isIP(String str) {
        return isType(str, "^(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5])$");
    }

    public static boolean isDate(String str) {
        return isType(str, "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$");
    }

    public static boolean isDatetime(String str) {
        return isType(str, "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$");
    }

    public static boolean isURL(String str) {
        return isType(str, "^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$");
    }

    public static boolean isIdCard(String num) {
        if (!isType(num, "\\d{18}")) {
            return false;
        } else {
            int sum = 0;
            char lastChar = num.charAt(num.length() - 1);
            int[] VAR_LIST = new int[]{2, 4, 8, 5, 10, 9, 7, 3, 6, 1, 2, 4, 8, 5, 10, 9, 7};
            String STR_TAIL_CHARS = "10X98765432";
            char[] numChar = num.toCharArray();
            int[] numInt = new int[numChar.length - 1];

            int R;
            for (R = 0; R < numInt.length; ++R) {
                numInt[R] = Character.getNumericValue(numChar[R]);
            }

            for (R = 0; R < numInt.length; ++R) {
                sum += numInt[numInt.length - R - 1] * VAR_LIST[R];
            }

            R = sum % 11;
            return lastChar == "10X98765432".charAt(R);
        }
    }

}
