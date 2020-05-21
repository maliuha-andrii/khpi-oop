package ua.khpi.oop.maliuha;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Composition {
    private String name;
    private String genre;
    private String author;
    private List<String> lyrics;
    private LocalDate date;
    private int durationSec;
    private String dataFormat;
    private Map<String, Integer> ratings;

    private String compressionLvl;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public Composition() {
        name = "";
        genre = "";
        author = "";
        lyrics = null;
        date = null;
        durationSec = 0;
        dataFormat = "";
        ratings = new LinkedHashMap<>();
        compressionLvl = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLyrics(List<String> lyrics) {
        this.lyrics = lyrics;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDurationSec(int durationSec) {
        this.durationSec = durationSec;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
        determineCompressionLvl();
    }

    public void setRatings(LinkedHashMap ratings) {
        this.ratings = ratings;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getLyrics() {
        return lyrics;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getDurationSec() {
        return durationSec;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public Map getRatings() {
        return ratings;
    }

    public int getMiddleRatings() {
        int middleRating = 0;
        Set set = ratings.keySet();
        Object[] arrStr = set.toArray();
        for (int j = 0; j < arrStr.length; j++) {
            middleRating += ratings.get(arrStr[j]);
        }
        if (arrStr.length > 0) {
            middleRating /= arrStr.length;
        }
        return middleRating;
    }

    private void determineCompressionLvl() {
        if (dataFormat == "mp3" || dataFormat == "mp2" || dataFormat == "ogg") {
            this.compressionLvl = "with losses";
            return;
        }
        if (dataFormat == "ape" || dataFormat == "flac") {
            this.compressionLvl = "without losses";
            return;
        }
        if (dataFormat == "wav" || dataFormat == "aiff") {
            this.compressionLvl = "without compression";
            return;
        }
    }

    public String toString() {
        String str = "\n-----------------------------------\n" + "Name: " + ANSI_GREEN + name + ANSI_RESET
                + "\nAuthor: " + ANSI_GREEN + author + ANSI_RESET
                + "\nGenre: " + ANSI_GREEN + genre + ANSI_RESET
                + "\nLyrics: " + ANSI_GREEN;
        if (lyrics != null) {
            for (String line : lyrics) {
                str += line + '\n';
            }
        }
        Set set = ratings.keySet();
        Object[] arrStr = set.toArray();
        str += ANSI_RESET + "\nRatings: " + ANSI_GREEN + '\n';
        for (int j = 0; j < arrStr.length; j++) {
            str += arrStr[j] + ": " + ratings.get(arrStr[j]) + '\n';
        }
        str += ANSI_RESET + "\nDate release: " + ANSI_GREEN + getDate().toString() + ANSI_RESET
                + "\nDuration: " + ANSI_GREEN + getDurationSec() / 60 + ANSI_RESET
                + "\nDataFormat: " + ANSI_GREEN + getDataFormat() + ANSI_RESET
                + "\n-----------------------------------\n";

        return str;
    }

    public String getCompressionLvl() {
        return compressionLvl;
    }
}