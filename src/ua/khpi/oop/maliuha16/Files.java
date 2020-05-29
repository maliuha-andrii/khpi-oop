
import java.io.File;
import java.util.Scanner;


public class Files {

    public static String controller() {

        String output = ("~/\\");

        String find2 = "";
        int k2 = 0;
        while (!"0".equals(find2)) {
            System.out.println("Current path: " + output + "\n" +
                    "1. Continue\n" +
                    "2. Create file\n" +
                    "3. Another directory\n" +
                    "0. Save\n" +
                    "Choose: ");
            find2 = in.next();
            try {
                k2 = Integer.parseInt(find2);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect data!");
            }

            switch (k2) {
                case 1:
                    output = chooseFile(Files.getListOfFiles(output));
                    break;
                case 2:
                    output = createFile(output);
                    break;
                case 3:

                    output = moveHigher(output);
                    break;
            }
        }

        return output;
    }

    static Scanner in = new Scanner(System.in);

    private static File[] getListOfFiles(String path) {
        File f = new File(path);
        File[] list = f.listFiles();
        return list;

    }

    private static void printListOfFiles(File[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(i + 1 + ") " + list[i].getName());
        }
    }

    private static String moveHigher(String path) {
        return new StringBuilder(path).delete(path.lastIndexOf(File.separator), path.length()).toString();
    }

    private static String chooseFile(File[] list) {
        printListOfFiles(list);
        System.out.println("Choose package or file:");
        int index = in.nextInt();

        return (list[index - 1].getAbsolutePath());

    }

    private static String createFile(String path) {
        String newPath = path + File.separator + inStr();
        return new File(newPath).getAbsolutePath();

    }

    private static String inStr() {
        System.out.println("Waiting......");
        @SuppressWarnings("resource")
        String user_str = new Scanner(System.in).nextLine();
        return user_str;
    }

}