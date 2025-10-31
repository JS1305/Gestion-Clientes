package org.example.persistece;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConfiJPA {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");

    public ConfiJPA(){
    }

    public static void close(){
        emf.close();
    }

    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
