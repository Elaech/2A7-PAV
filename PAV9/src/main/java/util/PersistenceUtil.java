package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The singleton class that returns the unique instance of EntityManagerFactory
 */
public class PersistenceUtil {
    private static EntityManagerFactory instance;

    private PersistenceUtil(){
        instance = Persistence.createEntityManagerFactory("MusicAlbumsPU");
    }

    public static EntityManagerFactory getInstance(){
        if(instance == null)
            new PersistenceUtil();
        return instance;
    }
}
