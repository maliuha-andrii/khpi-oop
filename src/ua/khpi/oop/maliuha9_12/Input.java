package ua.khpi.oop.maliuha9_12;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Input {
    private static Scanner in = new Scanner(System.in);

    public static Composition insert() throws IOException {
        Composition output = new Composition();

        output.setName(enterName());
        output.setGenre(enterGenre());
        output.setAuthor(enterAuthor());
        System.out.println("Do you want enter lyrics?(Y/N)");
        String answer = in.nextLine();
        if (answer == "Y" || answer == "Yes" || answer == "yes" || answer == "да") {
            output.setLyrics(enterLyrics());
        }
        output.setDate(enterDate());
        output.setDurationSec(enterDuration());
        output.setRatings((LinkedHashMap) enterRates());
        output.setDataFormat(enterDataFormat());

        return output;
    }


    private static String enterName() {
        System.out.println("Input name of composition: ");
        String name = in.nextLine();
        return name;
    }

    private static String enterGenre() {
        System.out.println("Input genre: ");
        String genre = in.nextLine();
        while (genre != "exit") {
            if (!Regex.checkGenre(genre)) {
                System.out.print("WARNING: Invalid genre. Try again: ");
                genre = in.nextLine();
            } else {
                return genre;
            }
        }
        return "";
    }

    private static String enterAuthor() {
        System.out.println("Input author: ");
        String author = in.nextLine();
        return author;
    }

    private static List<String> enterLyrics() throws IOException {
        System.out.println("Enter file path with lyrics: ");
        String path = in.nextLine();
        while (path != "exit") {
            if (Files.exists(Paths.get(path)) && !Files.isDirectory(Paths.get(path))) {
                List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
                return lines;
            } else {
                System.out.println("WARNING: Invalid path. Enter 'exit' to leave. Try again: ");
            }
        }
        return null;
    }

    private static String enterDate() {
        System.out.println("Input date of release(yyyy-mm-dd): ");
        String date = in.nextLine();
        while (date != "exit") {
            if (!Regex.checkDate(date)) {
                System.out.print("WARNING: Invalid date of release. Enter 'exit' to leave. Try again: ");
                date = in.nextLine();
            } else {
                return date;
            }
        }
        return null;
    }

    private static int enterDuration() {
        System.out.println("Enter duration in seconds: ");
        int num = Integer.parseInt(in.nextLine());
        if (num < 0) {
            num *= -1;
        }
        return num;
    }

    private static String enterDataFormat() {
        System.out.print("Enter data format: ");
        String str = in.nextLine();
        while (str != "exit") {
            if (!Regex.checkDataFormat(str)) {
                System.out.print("WARNING: Invalid data format. Enter 'exit' to leave. Try again: ");
                str = in.nextLine();
            } else {
                return str;
            }
        }
        return null;
    }

    private static Map enterRates() {
        System.out.print("Set amount of assessment sources: ");
        int number = Integer.parseInt(in.nextLine());
        if (number > 0) {
            String nameOfSource;
            int rate;
            Map<String, Integer> rates = new LinkedHashMap<>();
            for (int i = 0; i < number; i++) {
                System.out.println("Set name of assessment sources: ");
                nameOfSource = in.nextLine();
                System.out.println("Set rate: ");
                rate = Integer.parseInt(in.nextLine());
                while (rate < 0 || rate > 10) {
                    System.out.println("WARNING: Invalid data rate. Try again: ");
                    rate = in.nextInt();
                }
                rates.put(nameOfSource, rate);
            }
            return rates;
        }
        return null;
    }
}