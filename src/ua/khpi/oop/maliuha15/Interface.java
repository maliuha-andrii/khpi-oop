package ua.khpi.oop.maliuha15;

import ua.khpi.oop.maliuha9_12.Composition;
import ua.khpi.oop.maliuha9_12.Input;
import ua.khpi.oop.maliuha9_12.Regex;
import ua.khpi.oop.maliuha9_12.Files;


import java.io.IOException;
import java.util.*;

public class Interface {

    public static ArrayList<Composition> object = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    @SuppressWarnings("unchecked")
    public static void menu() throws IOException {
        int choose;

        choices();
        choose = in.nextInt();
        while (choose != 0) {
            switch (choose) {
                case 1:
                    printList(object);
                    break;
                case 2:
                    object.add(Input.insert());
                    break;
                case 3:
                    remove(object);
                    break;
                case 4:
                    sorters(object);
                    break;
                case 5:
                    clear(object);
                    break;
                case 6:
                    System.out.println(toStr(object));
                    break;
                case 7:
                    Composition[] test = object.toArray(new Composition[object.size()]);
                    break;
                case 8:
                    save(object);
                    break;
                case 9:
                    object = download();
                    break;
                case 10:
                    settingSearch(object);
                    break;
                case 11:
                    Generator.generator(10);
                    break;
            }
            choices();
            choose = in.nextInt();
        }

    }


    @SuppressWarnings("unchecked")
    private static void save(ArrayList<Composition> input) {
        int choose;
        System.out.println("What type of saving do you wish for: ");
        System.out.println("1 - XML");
        System.out.println("2 - Standard Serialization");
        System.out.println("Choose: ");
        choose = in.nextInt();
        try {
            if (choose == 1) {
                Serialization.LongTermPersistenceWrite(input, Files.controller());
            } else {
                Serialization.ObjectWrite(input, Files.controller());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void autoMode() throws IOException {
        object = download();
        menu();
    }

    private static ArrayList download() {
        ArrayList<Composition> output = new ArrayList<>();
        int choose;
        System.out.println("What type of saving do you wish for: ");
        System.out.println("1 - XML");
        System.out.println("2 - Standard Serialization");
        System.out.println("Choose: ");
        choose = in.nextInt();

        if (choose == 1) {
            output = Serialization.LongTermPersistenceRead(Files.controller());
        } else {
            output = Serialization.ObjectRead(output, Files.controller());
        }

        return output;
    }

    private static void choices() {

        System.out.println("List of settings: ");
        System.out.println("1 - Show list");
        System.out.println("2 - Insert data from keyboard");
        System.out.println("3 - Remove object from list");
        System.out.println("4 - Sort list");
        System.out.println("5 - Clear list");
        System.out.println("6 - Convert list to String");
        System.out.println("7 - Convert list to Array");
        System.out.println("8 - Save data");
        System.out.println("9 - Download data");
        System.out.println("10 - Search compositions about New Year");
        System.out.println("11 - Generate Data");
        System.out.println("0 - Exit");
        System.out.print("Select: ");

    }

    private static void settingAdvance() {
        System.out.println("List of settings: ");
        System.out.println("0 - Exit");
        System.out.println("1 - Sort by name");
        System.out.println("2 - Sort by ratings");
        System.out.println("3 - Sort by author");
        System.out.println("4 - Show list");
        System.out.print("Select: ");
    }

    @SuppressWarnings("unchecked")
    private static void sorters(ArrayList<Composition> input) {
        int choose;
        settingAdvance();
        choose = in.nextInt();
        while (choose != 0) {
            switch (choose) {
                case 1:
                    input.sort(new Comparator<Composition>() {
                        @Override
                        public int compare(Composition p1, Composition p2) {
                            return p1.getName().compareTo(p2.getName());
                        }
                    });
                    break;
                case 2:
                    input.sort(new Comparator<Composition>() {
                        @Override
                        public int compare(Composition p1, Composition p2) {
                            if (p1.getMiddleRatings() != p2.getMiddleRatings()) {
                                return p1.getMiddleRatings() - p2.getMiddleRatings();
                            }
                            return 0;
                        }
                    });
                    break;
                case 3:
                    input.sort(new Comparator<Composition>() {
                        @Override
                        public int compare(Composition p1, Composition p2) {
                            return p1.getAuthor().compareTo(p2.getAuthor());
                        }
                    });
                    break;
                case 4:
                    printList(input);
                    break;
            }
            settingAdvance();
            choose = in.nextInt();
        }
    }

    private static void settingSearch(ArrayList<Composition> input) {
        for (Composition elem : input) {
            if (search(elem)) {
                System.out.println(elem);
            }
        }
    }

    private static void remove(ArrayList<Composition> input) {
        int num;
        if (input.size() != 0) {
            System.out.println("Input number of note: ");
            num = in.nextInt();
            if (num >= 0 && num < input.size()) {
                input.remove(num);
            }
        } else {
            System.out.println("Error: arr is empty");
        }
    }

    private static void clear(ArrayList<Composition> input) {

        System.out.print("Are you sure that you want delete list \n\t\t" + ANSI_RED + "\"Yes" + ANSI_GREEN + "|NO\":"
                + ANSI_RESET);
        if (in.next().matches("Yes|yes")) {
            input.clear();
        }
    }

    private static String toStr(ArrayList<Composition> input) {
        return input.toString();
    }

    private static <E> void printList(ArrayList<E> input) {

        for (E elem : input) System.out.println(elem);

    }

    private static boolean search(Composition input) {

        if (Regex.findSomeText(input.getName())) {
            return true;
        }
        if (input.getLyrics() != null) {
            for (String line : input.getLyrics()) {
                if (Regex.findSomeText(line)) {
                    return true;
                }
            }
        }
        return false;
    }

}