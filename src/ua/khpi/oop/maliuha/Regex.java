package ua.khpi.oop.maliuha;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static boolean checkDate(String input) {
        return input.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");
    }

    public static boolean checkGenre(String input) {
        return input.matches("[A-Z,А-Я,a-z,а-я\\s\\-]+");
    }

    public static boolean checkDataFormat(String input) {
        return input.matches("[mp3]*[ape]*[wav]*[aiff]*[flac]*[ogg]*[mp2]*");
    }

    public static boolean findSomeText(String input) {
        Pattern pattern = Pattern.compile("[H][a][p][p][y][\\s][N][e][w][\\s][Y][e][a][r]");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) {
            pattern = Pattern.compile("[N][e][w][\\s][Y][e][a][r]");
            matcher = pattern.matcher(input);
            return matcher.find();
        }
        else {
            return true;
        }
    }

    public static boolean checkNumber(String input) {
        return input.matches("^[1-9{1}][\\d{1,10}]*");
    }
}
