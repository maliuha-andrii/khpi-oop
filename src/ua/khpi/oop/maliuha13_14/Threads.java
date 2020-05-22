package ua.khpi.oop.maliuha13_14;

import ua.khpi.oop.maliuha9_12.Composition;
import ua.khpi.oop.maliuha9_12.Interface;
import ua.khpi.oop.maliuha9_12.MyList;
import ua.khpi.oop.maliuha9_12.Regex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Scanner;

public class Threads {

    public static void test1() {
        int count = 0;
        System.out.println("First Thread started");
        try {
            for (Composition elem : Interface.object) {
                if (!Thread.currentThread().isInterrupted()) {
                    if (elem.getMiddleRatings() > count) {
                        count = elem.getMiddleRatings();
                    }
                } else {
                    throw new InterruptedException();
                }
            }
            System.out.println("Max rating : " + count);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void test2() {

        System.out.println("Second Thread started");
        try {
            if (!Thread.currentThread().isInterrupted()) {
                Thread2.sortByName(Interface.object);
            } else {
                throw new InterruptedException();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

    }

    public static void test3() {
        System.out.println("Third Thread started");
        try {
            if (!Thread.currentThread().isInterrupted()) {
                Thread3.sortByMiddleRating(Interface.object);
            } else {
                throw new InterruptedException();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void startThreads() {
        Scanner in = new Scanner(System.in);
        System.out.println("Set the timer [0 - 100 000 ms]: ");
        String tmp = in.nextLine();
        while (!Regex.checkNumber(tmp)) { //регулярка для числа
            System.out.println("Timer can be only in digit");
            tmp = in.nextLine();
        }
        int timer_num = Integer.parseInt(tmp);
        System.out.println("Starting all threads...");

        Thread1 first = new Thread1();
        Thread t1 = new Thread(first, "FirstThread");

        Thread2 second = new Thread2();
        Thread t2 = new Thread(second, "SecondThread");

        Thread3 third = new Thread3();
        Thread t3 = new Thread(third, "ThirdThread");

        t1.start();
        t2.start();
        t3.start();
        Timer timer = new Timer(timer_num, new ActionListener() /*дроп потоков по истечению времени*/ {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Interrupting thread...");
                t1.interrupt();
                t2.interrupt();
                t3.interrupt();
            }
        });
        timer.setRepeats(false);
        timer.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            timer.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finishing all threads...");
    }

    public static long cParallel() {

        System.out.println("Starting all threads...");
        Thread1 first = new Thread1();
        Thread t1 = new Thread(first, "FirstThread");

        Thread2 second = new Thread2();
        Thread t2 = new Thread(second, "SecondThread");

        Thread3 third = new Thread3();
        Thread t3 = new Thread(third, "ThirdThread");

        long time_start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finishing all threads...");
        return System.currentTimeMillis() - time_start;
    }

    public static long comparisonSequential() {
        long time_start = System.currentTimeMillis();
        System.out.println("Starting sequence...");
        Threads.test1();
        Threads.test2();
        Threads.test3();
        System.out.println("Finishing sequence...");
        return System.currentTimeMillis() - time_start;
    }
}

class Thread1 implements Runnable {
    public void run() {
        int count = 0;
        System.out.println("First Thread started");
        try {
            for (Composition elem : Interface.object) {
                if (!Thread.currentThread().isInterrupted()) {
                    if (elem.getMiddleRatings() > count) {
                        count = elem.getMiddleRatings();
                    }
                } else {
                    throw new InterruptedException();
                }
            }
            System.out.println("Max rating : " + count);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
} //TODO

class Thread2 implements Runnable {
    public void run() {
        System.out.println("Second Thread started");
        try {
            if (!Thread.currentThread().isInterrupted()) {
                sortByName(Interface.object);
            } else {
                throw new InterruptedException();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void sortByName(MyList input) {
        input.sort(new Comparator<Composition>() {
            @Override
            public int compare(Composition p1, Composition p2) {
                return p1.getName().compareTo(p2.getName());
            }
        }, input.toArray(new Composition[input.size()]));

    }
}

class Thread3 implements Runnable {
    public void run() {
        System.out.println("Third Thread started");
        try {
            if (!Thread.currentThread().isInterrupted()) {
                sortByMiddleRating(Interface.object);
            } else {
                throw new InterruptedException();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }


    public static void sortByMiddleRating(MyList input) {
        input.sort(new Comparator<Composition>() {
            @Override
            public int compare(Composition p1, Composition p2) {
                if (p1.getMiddleRatings() != p2.getMiddleRatings()) {
                    return p1.getMiddleRatings() - p2.getMiddleRatings();
                }
                return 0;
            }
        }, input.toArray(new Composition[input.size()]));

    }
}

