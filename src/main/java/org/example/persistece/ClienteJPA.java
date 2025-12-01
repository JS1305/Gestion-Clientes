package org.example.persistece;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entitis.Cliente;

import java.util.List;

public class ClienteJPA {

    // ES: Maneja la configuración JPA y proporciona EntityManagers.
    // EN: Handles JPA configuration and provides EntityManagers.
    private ConfiJPA confiJPA;

    public ClienteJPA() {
        // ES: Al crear esta clase, se inicializa el manejador de configuración JPA.
        // EN: When this class is instantiated, the JPA configuration manager is initialized.
        confiJPA = new ConfiJPA();
    }

    /**
     * ES: Guarda un nuevo cliente en la base de datos.
     *     Se inicia una transacción, se persiste el objeto y se confirma.
     *
     * EN: Saves a new client into the database.
     *     Begins a transaction, persists the object and commits it.
     */
    public void crearCliente(Cliente cliente) {
        try (EntityManager em = confiJPA.getEntityManager()) {
            em.getTransaction().begin();     // ES/EN: Start transaction
            em.persist(cliente);             // ES: Inserta en BD / EN: Inserts into DB
            em.getTransaction().commit();    // ES/EN: Commit transaction
        }
    }

    /**
     * ES: Obtiene la lista completa de clientes desde la base de datos.
     *     Usa una consulta JPQL para recuperar todos los registros.
     *
     * EN: Retrieves the full list of clients from the database.
     *     Uses a JPQL query to fetch all records.
     */
    public List<Cliente> listarCliente() {
        try (EntityManager em = confiJPA.getEntityManager()) {
            return em.createQuery("FROM Cliente", Cliente.class).getResultList();
        }
    }

    /**
     * ES: Actualiza un cliente existente en la base de datos.
     *     'merge' sincroniza el objeto con su registro en BD.
     *
     * EN: Updates an existing client in the database.
     *     'merge' synchronizes the object with its DB record.
     */
    public void modifyCliente(Cliente cliente) {
        try (EntityManager em = confiJPA.getEntityManager()) {
            em.getTransaction().begin();
            em.merge(cliente);               // ES: Actualiza / EN: Updates
            em.getTransaction().commit();
        }
    }

    /**
     * ES: Elimina un cliente de la base de datos.
     *     Primero se busca el cliente dentro del contexto de persistencia.
     *
     * EN: Deletes a client from the database.
     *     First retrieves the client inside the persistence context.
     */
    public void deleteCliente(Cliente cliente) {
        try (EntityManager em = confiJPA.getEntityManager()) {
            em.getTransaction().begin();
            Cliente clienteStored = em.find(Cliente.class, cliente.getId());  // ES/EN: Retrieve entity
            em.remove(clienteStored);                                         // ES: Elimina / EN: Removes
            em.getTransaction().commit();
        }
    }

    /**
     * ES: Busca todos los clientes que pertenezcan a una ciudad específica.
     *     Utiliza un parámetro en una consulta JPQL para filtrar resultados.
     *
     * EN: Finds all clients that belong to a specific city.
     *     Uses a JPQL query with a parameter to filter results.
     */
    public List<Cliente> findClientListByCity(String ciudad) {
        List<Cliente> clientes = null;

        try (EntityManager em = confiJPA.getEntityManager()) {
            em.getTransaction().begin();   // (Opcional — no se requiere para SELECT simple)

            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.ciudad = :ciudad", Cliente.class
            );
            query.setParameter("ciudad", ciudad);

            clientes = query.getResultList();
        }
        return clientes;
    }
}