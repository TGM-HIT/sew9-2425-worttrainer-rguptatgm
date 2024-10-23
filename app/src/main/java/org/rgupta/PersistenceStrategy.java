/**
 * The PersistenceStrategy interface defines the methods required to save and
 * load the Coach state. Implementations of this interface can provide different
 * mechanisms for persistence.
 */
package org.rgupta;

import java.io.IOException;

public interface PersistenceStrategy {
    void save(Coach coach, String filePath) throws IOException;

    Coach load(String filePath) throws IOException, ClassNotFoundException;
}
