package ua.khpi.oop.maliuha14;

import ua.khpi.oop.maliuha.Interface;

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
