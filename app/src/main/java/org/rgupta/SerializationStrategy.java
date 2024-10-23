/**
 * The SerializationStrategy class is an implementation of the PersistenceStrategy
 * interface. It provides functionality to save and load the Coach object using
 * Java's built-in serialization mechanism.
 */
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
