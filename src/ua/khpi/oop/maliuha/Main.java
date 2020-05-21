package ua.khpi.oop.maliuha;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Interface.menu();
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            System.err.println("Woops........Error");
            System.err.println(e.getMessage());
        }
    }

}
