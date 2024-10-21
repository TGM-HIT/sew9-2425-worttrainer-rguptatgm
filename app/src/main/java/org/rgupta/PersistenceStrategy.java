package org.rgupta;

import java.io.IOException;

public interface PersistenceStrategy {
    void save(Coach coach, String filePath) throws IOException;

    Coach load(String filePath) throws IOException, ClassNotFoundException;
}
