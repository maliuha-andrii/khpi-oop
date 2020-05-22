package ua.khpi.oop.maliuha;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.time.LocalDate;

public class Generator {

    public static void generator(int size) throws IOException {
        //MyLinkedList<Composition> compositions = new MyLinkedList<>();
//        String[] nameArray = new String[]{
//                "Stressed out", "Happy New Year", "Tear in my heart"
//        };
//
//        String[] genreArray = new String[]{
//                "rok, pop, hip-hop", "pop", "pop"
//        };
//
//        String[] authorArray = new String[]{
//                "Twenty one pilots", "Abba", "Twenty one pilots"
//        };
//
//        String[] paths = new String[]{
//                "src/stressed_out.txt", "src/happy_new_year.txt", "src/tear_in_my_heart.txt"
//        };
//
//        List<LocalDate> dates = new ArrayList<>();
//        dates.add(LocalDate.parse("2015-10-10"));
//        dates.add(LocalDate.parse("2009-09-09"));
//        dates.add(LocalDate.parse("2015-05-06"));
//        int length = 16;
//        Random r = new Random();
//        String s = r.ints(48, 122)
//                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
//                .mapToObj(i -> (char) i)
//                .limit(length)
//                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
//                .toString();
//
//        int[] durations = {180, 180, 180};
//
//        String[] dataFormats = {"mp3", "mp3", "mp3"};
//        List<Map<String, Integer>> ratings = new ArrayList<>();
//        Map<String, Integer> ratings1 = new LinkedHashMap<>();
//        ratings1.put("odnoklasniki", 10);
//        ratings1.put("vkontakte", 9);
//        Map<String, Integer> ratings2 = new LinkedHashMap<>();
//        ratings2.put("odnoklasniki", 8);
//        ratings2.put("vkontakte", 2);
//        Map<String, Integer> ratings3 = new LinkedHashMap<>();
//        ratings3.put("odnoklasniki", 10);
//        ratings3.put("vkontakte", 10);
//        ratings.add(ratings1);
//        ratings.add(ratings2);
//        ratings.add(ratings3);
//
//        List<String> lines = null;
//        for (int i = 0; i < paths.length; i++) {
//            Composition composition = new Composition();
//            composition.setName(nameArray[i]);
//            composition.setAuthor(authorArray[i]);
//            composition.setGenre(genreArray[i]);
//            composition.setDurationSec(durations[i]);
//            composition.setDate(dates.get(i));
//            composition.setDataFormat(dataFormats[i]);
//            composition.setRatings((LinkedHashMap) ratings.get(i));
////            if (java.nio.file.Files.exists(Paths.get(paths[i]))) {
////                lines = Files.readAllLines(Paths.get(paths[i]), StandardCharsets.UTF_8);
////                composition.setLyrics(lines);
////            }
//            Interface.object.add(composition);
//        }
        Integer year, month, day, duration;
        Random r = new Random();
        int length = 16;
        for (int i = 0; i < size; i++) {
            String s = r.ints(48, 122)
                    .filter(j -> (j < 57 || j > 65) && (j < 90 || j > 97))
                    .mapToObj(j -> (char) j)
                    .limit(length)
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString();
            Composition composition = new Composition();
            composition.setName(s);
            composition.setAuthor(s);
            composition.setGenre(s);
            year = (int) (Math.random() * (2020 - 1900)) + 1900;
            month = (int) (Math.random() * (10 - 11)) + 11;
            day = (int) (Math.random() * (10 - 28)) + 28;
            duration = (int) (Math.random() * (280 - 80)) + 80;
            composition.setDurationSec(duration);
            composition.setDate(year.toString() + '-' + month.toString() + '-' + day.toString());
            composition.setDataFormat("mp3");
            Map<String, Integer> rating = new LinkedHashMap<>();
            duration = (int) (Math.random() * (11 - 1)) + 1;
            rating.put(s, duration);
            composition.setRatings((LinkedHashMap) rating);
//            if (java.nio.file.Files.exists(Paths.get(paths[i]))) {
//                lines = Files.readAllLines(Paths.get(paths[i]), StandardCharsets.UTF_8);
//                composition.setLyrics(lines);
//            }

            Interface.object.add(composition);
        }
    }
}
