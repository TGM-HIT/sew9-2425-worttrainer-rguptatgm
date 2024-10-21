package org.rgupta;

import java.io.*;

public class SerializationStrategy implements PersistenceStrategy {

    @Override
    public void save(Coach coach, String filePath) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) { // try with resources
            out.writeObject(coach);
        }
    }

    @Override
    public Coach load(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Coach) in.readObject();
        }
    }
}
