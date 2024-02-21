package org.example.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static javax.persistence.Persistence.createEntityManagerFactory;

public final class HibernateUtil {

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            createEntityManagerFactory("lera");

    public static EntityManager getEntityManager() {

        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    public static void close() {
        ENTITY_MANAGER_FACTORY.close();
    }

}
