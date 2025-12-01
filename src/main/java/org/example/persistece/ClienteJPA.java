package org.example.persistece;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.entitis.Cliente;

import java.util.List;
import java.util.Scanner;

public class ClienteJPA {
    private ConfiJPA confiJPA;
    public ClienteJPA(){
        confiJPA = new ConfiJPA();
    }

    public void crearCliente(Cliente Cliente) {

        try (EntityManager em = confiJPA.getEntityManager();){
            em.getTransaction().begin();
            em.persist(Cliente);
            em.getTransaction().commit();
        }
    }


    public List<Cliente> listarCliente() {

        try (EntityManager em = confiJPA.getEntityManager()){
            return em.createQuery("FROM Cliente", Cliente.class).getResultList();

        }
    }

    public void modifyCliente(Cliente cliente) {
        try (EntityManager em = confiJPA.getEntityManager()){
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        }
    }

    public void deleteCliente(Cliente cliente) {
        try (EntityManager em = confiJPA.getEntityManager()){
            em.getTransaction().begin();
            Cliente clienteStored = em.find(Cliente.class, cliente.getId());
            em.remove(clienteStored);
            em.getTransaction().commit();
        }
    }






    public List<Cliente> findClientListByCity(String ciudad) {
        List<Cliente> clientes = null;

        try (EntityManager em = confiJPA.getEntityManager()){
            em.getTransaction().begin();
            // Consulta JPQL
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.ciudad = :ciudad", Cliente.class);
            query.setParameter("ciudad", ciudad);
            clientes = query.getResultList();
        }
        return clientes;
    }
}
