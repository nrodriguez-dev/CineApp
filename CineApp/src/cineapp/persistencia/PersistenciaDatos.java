package cineapp.persistencia;

import cineapp.modelo.Cine;

import java.io.*;

//Persistencia de datos con los sus respectivos métodos, uso de ObjectOutputStream y ObjectInputStream. Se cargan los archivos al abrir el programa, y se guardan al finalizarlo en sus respectivos controladores.

public class PersistenciaDatos {
    private static final String FILE = "cine.ser";

    public static void guardar(Cine cine) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(cine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Cine cargar() {
        File file = new File(FILE);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
                return (Cine) in.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Cine();
    }
}
