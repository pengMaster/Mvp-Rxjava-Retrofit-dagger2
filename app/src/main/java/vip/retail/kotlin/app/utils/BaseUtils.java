package vip.retail.kotlin.app.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

public class BaseUtils {
    public static final int STYLE_SHORT = 3;
    public static final int STYLE_MEDIUM = 2;
    public static final int STYLE_LONG = 1;
    public static final int STYLE_FULL = 0;
    public static final int STYLE_DEFAULT = 2;
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_TIME = "HH:mm:ss";
    public static final String FORMAT_YEAR = "yyyy";
    public static final String FORMAT_YEARMONTH = "yyyy-MM";

    private BaseUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() < 1 || str.equalsIgnoreCase("null");
    }

    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || isEmpty(obj.toString());
    }



    public static boolean equals(String str1, String str2) {
        return !isEmpty(str1) && !isEmpty(str2) ? str1.trim().equals(str2.trim()) : false;
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return !isEmpty(str1) && !isEmpty(str2) ? str1.trim().equalsIgnoreCase(str2.trim()) : false;
    }

    public static String nullToBlank(String str) {
        return isEmpty(str) ? "" : str;
    }

    public static String nullToBlank(Object obj) {
        return isEmpty(obj) ? "" : obj.toString();
    }

    public static boolean toBoolean(String str) {
        return isEmpty(str) ? false : (str.equalsIgnoreCase("false") ? false : (str.equalsIgnoreCase("true") ? true : (str.equals("0") ? false : (str.equals("1") ? true : (str.equalsIgnoreCase("n") ? false : str.equalsIgnoreCase("y"))))));
    }

    public static boolean toBoolean(Object obj) {
        return isEmpty(obj) ? false : (obj instanceof Boolean ? ((Boolean) obj).booleanValue() : toBoolean(obj.toString()));
    }

    public static String fillEmpty(char fillChar, int val, int length) {
        String valStr = (new Integer(val)).toString();
        return fillEmpty(fillChar, valStr, length);
    }

    public static String fillEmpty(char fillChar, String valStr, int length) {
        int valLength = valStr.length();
        if (valLength >= length) {
            return valStr;
        } else {
            int diff = length - valLength;
            String fill = "";

            for (int i = 0; i < diff; ++i) {
                fill = fill + fillChar;
            }

            return fill + valStr;
        }
    }

    public static String trimFootBlank(String str) {
        return str.replaceAll("\\s*$", "");
    }

    public static boolean isExist(Map<?, ?> map, Object key) {
        return map != null && map.keySet().contains(key);
    }

    public static Object getMapValue(Map<?, ?> map, Object key) {
        return isEmpty(map) ? null : map.get(key);
    }

    public static Iterator<?> getIterator(Collection<?> coll) {
        return isEmpty(coll) ? null : coll.iterator();
    }

    public static Iterator<?> getKeysIterator(Map<?, ?> map) {
        return isEmpty(map) ? null : map.keySet().iterator();
    }

    public static Iterator<?> getValuesIterator(Map<?, ?> map) {
        return isEmpty(map) ? null : map.values().iterator();
    }

    public static Object getFirstValue(Map<?, ?> map) {
        Iterator it = getValuesIterator(map);
        return it == null ? null : it.next();
    }

    public static Timestamp getCurrentDatetime() {
        Calendar c = Calendar.getInstance();
        return new Timestamp(c.getTimeInMillis());
    }

    public static Map.Entry<String, String>[] sortMap(Map<String, String> map) {
        Map.Entry[] en = (Map.Entry[]) map.entrySet().toArray(new Map.Entry[map.size()]);

        for (int i = 0; i < en.length; ++i) {
            for (int j = 0; j < en.length; ++j) {
                if (((String) en[i].getKey()).length() > ((String) en[j].getKey()).length()) {
                    Map.Entry tmp = en[i];
                    en[i] = en[j];
                    en[j] = tmp;
                }
            }
        }

        return en;
    }

    public static String dateToStr(Date timValue, int style1, int style2) {
        DateFormat df = DateFormat.getDateTimeInstance(style1, style2, new Locale("zh", "CN"));
        return df.format(timValue);
    }

    public static String dateToStr(Date timValue) {
        return dateToStr(timValue, 0, 3);
    }

    public static String dateToStr(int style1, int style2) {
        Timestamp timValue = getCurrentDatetime();
        return dateToStr(timValue, style1, style2);
    }

    public static String dateToStr(Date date, String dateFormat) {
        String rtn = null;
        SimpleDateFormat fmtDate = new SimpleDateFormat(dateFormat, new Locale("zh", "CN"));
        rtn = fmtDate.format(date);
        return rtn;
    }

    public static String dateToStr(String dateFormat) {
        return dateToStr(getCurrentDatetime(), dateFormat);
    }

    public static String dateToStr() {
        return dateToStr(0, 3);
    }

    public static Date strToDate(String str) throws ParseException {
        DateFormat df = DateFormat.getDateTimeInstance(2, 2, new Locale("zh", "CN"));
        Date date = df.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return new Date(calendar.getTimeInMillis());
    }

    public static String dateStrToShow(String str) throws ParseException {
        return dateToStr(strToDate(str));
    }

    public static Date convertDatetime(Date definitionDatetime) throws ParseException {
        String currStr = dateToStr(getCurrentDatetime(), "yyyy-MM-dd");
        String oldStr = dateToStr(definitionDatetime, "HH:mm:ss");
        return strToDate(currStr.concat(" ").concat(oldStr));
    }

    public static long getSpaceTime(Date datetime) throws ParseException {
        return convertDatetime(datetime).getTime() - getCurrentDatetime().getTime();
    }

    public static String secondConvTime(float sec) {
        int blurSec = Math.round(sec);
        int resSec = blurSec % 60;
        blurSec -= resSec;
        int minute = blurSec / 60 % 60;
        blurSec -= minute;
        int hour = blurSec / 3600;
        return (hour < 10 ? "0" + hour : (new Integer(hour)).toString()) + ":" + (minute < 10 ? "0" + minute : (new Integer(minute)).toString()) + ":" + (resSec < 10 ? "0" + resSec : (new Integer(resSec)).toString());
    }

    public static String addTime(String datetimeStr, String timeStr) {
        String[] datetime = datetimeStr.split(":");
        String[] date = datetime[0].split("-");
        String taskYear = date[0];
        String taskMonth = date[1];
        String taskDate = date[2].split(" ")[0];
        String taskHour = date[2].split(" ")[1];
        String taskMinute = datetime[1];
        String taskSecond = datetime[2];
        String[] time = timeStr.split(":");
        String hour = time[0];
        String minute = time[1];
        String second = time[2];
        Calendar c = Calendar.getInstance(new Locale("zh", "CN"));
        c.set(Integer.parseInt(taskYear), Integer.parseInt(taskMonth), Integer.parseInt(taskDate), Integer.parseInt(taskHour), Integer.parseInt(taskMinute), Integer.parseInt(taskSecond));
        c.add(13, Integer.parseInt(second));
        c.add(12, Integer.parseInt(minute));
        c.add(10, Integer.parseInt(hour));
        c.add(2, -1);
        return dateToStr(new Date(c.getTimeInMillis()), "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getDate(int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return new Date(c.getTimeInMillis());
    }

    public static String getYYYYMMDD() {
        Calendar c = Calendar.getInstance(new Locale("zh", "CN"));
        int year = c.get(1);
        int month = c.get(2) + 1;
        int day = c.get(5);
        return year + (month >= 10 ? "" + month : "0" + month) + (day >= 10 ? "" + day : "0" + day);
    }

    public static String getYYYYMMDD(int field, int amount) {
        Calendar c = Calendar.getInstance(new Locale("zh", "CN"));
        c.add(field, amount);
        int year = c.get(1);
        int month = c.get(2) + 1;
        int day = c.get(5);
        return year + (month >= 10 ? "" + month : "0" + month) + (day >= 10 ? "" + day : "0" + day);
    }

    public static String getHHMMSS() {
        Calendar c = Calendar.getInstance(new Locale("zh", "CN"));
        int hour = c.get(11);
        int minute = c.get(12);
        int second = c.get(13);
        return (hour >= 10 ? "" + hour : "0" + hour) + (minute >= 10 ? "" + minute : "0" + minute) + (second >= 10 ? "" + second : "0" + second);
    }

    public static String getHHMMSS(int field, int amount) {
        Calendar c = Calendar.getInstance(new Locale("zh", "CN"));
        c.add(field, amount);
        int hour = c.get(11);
        int minute = c.get(12);
        int second = c.get(13);
        return (hour >= 10 ? "" + hour : "0" + hour) + ":" + (minute >= 10 ? "" + minute : "0" + minute) + ":" + (second >= 10 ? "" + second : "0" + second);
    }

    public static Calendar getCurrWeekInOneDay(int dayOfWeek) {
        GregorianCalendar gc = new GregorianCalendar(new Locale("zh", "CN"));
        gc.add(5, -gc.get(7) + 1 + dayOfWeek);
        return gc;
    }

    public static String getCurrWeekInOneDayYYYYMMDD(int dayOfWeek) {
        Calendar gc = getCurrWeekInOneDay(dayOfWeek);
        return String.valueOf(gc.get(1)) + fillEmpty('0', gc.get(2) + 1, 2) + fillEmpty('0', gc.get(5), 2);
    }

    public static int getYear() {
        return Calendar.getInstance().get(1);
    }

    public static int getYear(int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return c.get(1);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(2) + 1;
    }

    public static int getMonth(int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return c.get(2) + 1;
    }

    public static int getDay(int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return c.get(5);
    }

    public static int getDay() {
        return Calendar.getInstance().get(5);
    }

    public static int getWeek() {
        return Calendar.getInstance().get(7) - 1;
    }

    public static int getWeek(int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return c.get(7) - 1;
    }

    public static int getHour() {
        return Calendar.getInstance().get(11);
    }

    public static int getMinute() {
        return Calendar.getInstance().get(12);
    }

    public static int getSecond() {
        return Calendar.getInstance().get(13);
    }

    public static int contentEquals(String str1, String str2) {
        if (str1 != null && str2 != null) {
            int length = str1.length() > str2.length() ? str2.length() : str1.length();

            for (int i = 0; i < length; ++i) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    return i;
                }
            }

            return str1.length() != str2.length() ? length : -1;
        } else {
            return -2;
        }
    }

    public static String reverseOrderStr(String str) {
        char[] c = str.toCharArray();
        char[] c2 = new char[c.length];
        int index = 0;

        for (int i = c.length - 1; i >= 0; --i) {
            c2[index++] = c[i];
        }

        return new String(c2);
    }

    public static String clearNullityChar(String str) {
        if (isEmpty(str)) {
            return null;
        } else {
            String[] fraction = Pattern.compile("[\\s]{1,}").split(str);
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < fraction.length; ++i) {
                sb.append(fraction[i]);
                sb.append(" ");
            }

            return sb.toString();
        }
    }

    public static double percent(double p1, double p2) {
        double p3 = p1 / p2;
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);
        String str = nf.format(p3);
        return (new Double(str.substring(0, str.length() - 1))).doubleValue();
    }

    public static String makePwd(int pweLength) {
        boolean maxNum = true;
        int count = 0;
        char[] str = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();

        while (count < pweLength) {
            int i = Math.abs(r.nextInt(36));
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                ++count;
            }
        }

        return pwd.toString();
    }

    public static String txt2htm(String txt) {
        if (isEmpty(txt)) {
            return "";
        } else {
            StringBuilder bld = new StringBuilder();

            for (int i = 0; i < txt.length(); ++i) {
                char c = txt.charAt(i);
                switch (c) {
                    case '\n':
                        bld.append("<br/>");
                        break;
                    case ' ':
                        bld.append("&nbsp;");
                        break;
                    case '\"':
                        bld.append("&quot;");
                        break;
                    case '&':
                        bld.append("&amp;");
                        break;
                    case '<':
                        bld.append("&lt;");
                        break;
                    case '>':
                        bld.append("&gt;");
                        break;
                    default:
                        bld.append(c);
                }
            }

            return bld.toString();
        }
    }

    public static String htm2txt(String htm) {
        if (htm == null) {
            return htm;
        } else {
            htm = htm.replace("&amp;", "&");
            htm = htm.replace("&lt;", "<");
            htm = htm.replace("&gt;", ">");
            htm = htm.replace("&quot;", "\"");
            htm = htm.replace("&nbsp;", " ");
            htm = htm.replace("<br/>", "\n");
            return htm;
        }
    }

    public static String Q2B(String qjStr) {
        String outStr = "";
        String Tstr = "";
        byte[] b = (byte[]) null;

        for (int i = 0; i < qjStr.length(); ++i) {
            try {
                Tstr = qjStr.substring(i, i + 1);
                b = Tstr.getBytes("unicode");
            } catch (UnsupportedEncodingException var7) {
                var7.printStackTrace();
            }

            if (b[3] == -1) {
                b[2] = (byte) (b[2] + 32);
                b[3] = 0;

                try {
                    outStr = outStr + new String(b, "unicode");
                } catch (UnsupportedEncodingException var6) {
                    var6.printStackTrace();
                }
            } else {
                outStr = outStr + Tstr;
            }
        }

        return outStr;
    }

    public static String convertMD5(String pwd) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(pwd.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException var5) {
            throw new RuntimeException(var5);
        } catch (UnsupportedEncodingException var6) {
            throw new RuntimeException(var6);
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; ++i) {
            if (Integer.toHexString(255 & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(255 & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(255 & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }
//
//    public static Properties getProperties(String path) throws IOException {
//        InputStream is = Utils.class.getResourceAsStream(path);
//
//        Properties var4;
//        try {
//            Properties e = new Properties();
//            e.load(is);
//            var4 = e;
//        } catch (IOException var11) {
//            throw var11;
//        } finally {
//            try {
//                if (is != null) {
//                    is.close();
//                }
//            } catch (IOException var10) {
//                ;
//            }
//
//        }
//
//        return var4;
//    }
//
//    public static String getProperties(String path, String key) throws IOException {
//        Properties prop = getProperties(path);
//        return prop.getProperty(key);
//    }

    public static String readFile(String filePath) throws FileNotFoundException, IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        } else {
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;

            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                String e = bufferedReader.readLine();

                StringBuilder str;
                for (str = new StringBuilder(); e != null; e = bufferedReader.readLine()) {
                    str.append(e);
                }

                String var7 = str.toString();
                return var7;
            } catch (FileNotFoundException var15) {
                throw var15;
            } catch (IOException var16) {
                throw var16;
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }

                    if (fileReader != null) {
                        fileReader.close();
                    }
                } catch (IOException var14) {
                    ;
                }

            }
        }
    }
}