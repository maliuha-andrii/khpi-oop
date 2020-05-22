package ua.khpi.oop.maliuha9_12;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class Serialization {

    public static <E> void LongTermPersistenceWrite(MyList<E> object, String path) throws FileNotFoundException {

        XMLEncoder encoder = new XMLEncoder(
                new BufferedOutputStream(
                        new FileOutputStream(path)));

        for (E elem : object) {
            encoder.writeObject(elem);
        }
        encoder.close();
    }


    @SuppressWarnings("unchecked")
    public static <E extends Composition> MyList<E> LongTermPersistenceRead(String path) throws FileNotFoundException {
        boolean test = true;
        MyList<E> object = new MyList<>();
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
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return object;
    }

    public static <E extends Composition> void ObjectWrite(MyList<E> object, String path) throws IOException, ClassNotFoundException {

        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(object);
        objectOutputStream.close();

    }

    @SuppressWarnings("unchecked")
    public static <E> MyList<E> ObjectRead(MyList<E> object, String path) throws IOException, ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            object = (MyList<E>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            System.out.println("Woops.......Error");
            System.err.println(e.getMessage());
        }
        return object;
    }
}