import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Generator {

    public static void generator(int size) {
        Integer year, month, day, duration;
        Random r = new Random();
        String[] names = {"Stressed out", "Tear in my heart", "Car radio", "Happy new year",
                "Back in Black", "Highway to hell", "Mr. Sandman", "Holiday",
                "Дешевые драмы", "Кладбище самолетов"};
        String[] authors = {"Twenty One Pilots", "Twenty One Pilots", "Twenty One Pilots",
                "Abba", "AC/DC", "AC/DC", "Chordettes", "Green Day", "Валентин Стрыкало",
                "Валентин Стрыкало"};
        String[] genres = {"pop, rock", "pop, indy-pop", "rock", "pop", "rock", "rock", "pop", "rock", "pop", "pop"};
        String[] sourceOfRatings = {"Deezer", "SoundCloud", "Play Music", "Amazon"};
        String[] lyrics = {"I wish I found some better sounds no one's ever heard\n" +
                "I wish I had a better voice that sang some better words\n" +
                "I wish I found some chords in an order that is new\n" +
                "I wish I didn't have to rhyme every time I sang\n" +
                "I was told when I get older all my fears would shrink\n" +
                "But now I'm insecure and I care what people think\n" +
                "My name's Blurryface and I care what you think\n" +
                "My name's Blurryface and I care what you think\n" +
                "Wish we could turn back time, to the good old days\n" +
                "When our momma sang us to sleep but now we're stressed out\n" +
                "Wish we could turn back time, to the good old days\n" +
                "When our momma sang us to sleep but now we're stressed out\n" +
                "We're stressed out\n" +
                "Sometimes a certain smell will take me back to when I was young\n" +
                "How come I'm never able to identify where it's coming…"};
        int points;
        int index;
        for (int i = 0; i < size; i++) {
            Composition composition = new Composition();
            index = (int) (Math.random() * (10 - 0)) + 0;
            composition.setName(names[index]);
            composition.setAuthor(authors[index]);
            composition.setGenre(genres[index]);
            year = (int) (Math.random() * (2020 - 1900)) + 1900;
            month = (int) (Math.random() * (10 - 11)) + 11;
            day = (int) (Math.random() * (10 - 28)) + 28;
            duration = (int) (Math.random() * (280 - 80)) + 80;
            composition.setDurationSec(duration);
            composition.setDate(year.toString() + '-' + month.toString() + '-' + day.toString());
            composition.setDataFormat("mp3");
            Map<String, Integer> rating = new LinkedHashMap<>();
            index = (int) (Math.random() * (5 - 1)) + 1;
            for (int j = 0; j < index; j++) {
                points = (int) (Math.random() * (11 - 1)) + 1;
                rating.put(sourceOfRatings[j], points);
            }
            composition.setRatings((LinkedHashMap) rating);
            composition.setLyrics(Arrays.asList(lyrics));
            MyCollections.list.add(composition);
        }
    }
}
