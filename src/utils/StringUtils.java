package utils;

/**
 * Created by connected on 11/15/16.
 */
public class StringUtils {

    public static Integer getNextInteger(StringBuilder stringBuilder) {
        Integer index = stringBuilder.indexOf(" ");
        Integer number;
        if(index > 0) {
            number = Integer.valueOf(stringBuilder.substring(0, index).trim());
            stringBuilder.delete(0, index + 1);
        } else {
            number = Integer.valueOf(stringBuilder.toString().trim());
            stringBuilder.delete(0, stringBuilder.length());
        }

        return number;
    }

}
