package ua.khpi.oop.maliuha15;


import ua.khpi.oop.maliuha.Composition;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class Serialization {

    public static <E> void LongTermPersistenceWrite(ArrayList<E> object, String path) throws FileNotFoundException {

        XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream(path)));

        for (E elem : object) {
            encoder.writeObject(elem);
        }
        encoder.close();
    }


    @SuppressWarnings("unchecked")
    public static <E extends Composition> ArrayList<E> LongTermPersistenceRead(String path){
        boolean test = true;
        ArrayList<E> object = new ArrayList<>();
        try {
            XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(path)));
            while (test) {
                try {
                    E elem = ((E) decoder.readObject());
                    object.add(elem);
                } catch (Exception e) {
                    test = false;
                }
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return object;
    }

    public static <E extends Composition> void ObjectWrite(ArrayList<E> object, String path) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(object);
        objectOutputStream.close();

    }

    @SuppressWarnings("unchecked")
    public static <E> ArrayList<E> ObjectRead(ArrayList<E> object, String path){
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            object = (ArrayList<E>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            System.out.println("Woops.......Error");
            System.err.println(e.getMessage());
        }
        return object;
    }
}