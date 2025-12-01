package org.example.persistece;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConfiJPA {

    // ES: Crea una única instancia de EntityManagerFactory para toda la aplicación.
    //     Esto es importante porque crear un EMF es muy costoso en recursos.
    //
    // EN: Creates a single instance of EntityManagerFactory for the entire application.
    //     This is important because creating an EMF is resource-expensive.
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");

    // ES: Constructor vacío. No realiza ninguna acción.
    // EN: Empty constructor. Performs no actions.
    public ConfiJPA(){
    }

    // ES: Cierra el EntityManagerFactory cuando la aplicación termina.
    //     Debe llamarse solo una vez, al finalizar toda la ejecución.
    //
    // EN: Closes the EntityManagerFactory when the application ends.
    //     Should be called only once at the end of the execution.
    public static void close(){
        emf.close();
    }

    // ES: Devuelve un EntityManager nuevo para realizar operaciones CRUD.
    //     Un EntityManager es una conexión ligera hacia la base de datos.
    //
    // EN: Returns a new EntityManager used for CRUD operations.
    //     An EntityManager is a lightweight connection to the database.
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}