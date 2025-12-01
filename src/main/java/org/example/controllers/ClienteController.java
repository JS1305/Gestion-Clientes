package org.example.controllers;

import org.example.entitis.Cliente;
import org.example.persistece.ClienteJPA;

import java.util.List;

/**
 * ES: Clase de controlador para la gestión de clientes.
 *     Sirve como intermediario entre la capa de presentación (Main) y la capa de persistencia (ClienteJPA).
 *
 * EN: Controller class for client management.
 *     Acts as an intermediary between the presentation layer (Main) and persistence layer (ClienteJPA).
 */
public class ClienteController {

    // ES: Instancia de la clase ClienteJPA que maneja la persistencia en la base de datos.
    // EN: Instance of ClienteJPA class that handles database persistence.
    private ClienteJPA clienteJPA;

    /**
     * ES: Constructor que inicializa la capa de persistencia.
     * EN: Constructor that initializes the persistence layer.
     */
    public ClienteController(){
        this.clienteJPA = new ClienteJPA();
    }

    /**
     * ES: Método para crear un nuevo cliente en la base de datos.
     * EN: Method to create a new client in the database.
     *
     * @param cliente Cliente a guardar.
     *                Client to be saved.
     */
    public void crearCliente(Cliente cliente) {
        clienteJPA.crearCliente(cliente);
    }

    /**
     * ES: Método que devuelve la lista completa de clientes.
     * EN: Method that returns the complete list of clients.
     *
     * @return Lista de clientes. List of clients.
     */
    public List<Cliente> listarCliente() {
        return clienteJPA.listarCliente();
    }

    /**
     * ES: Método para actualizar un cliente existente en la base de datos.
     * EN: Method to update an existing client in the database.
     *
     * @param cliente Cliente a modificar.
     *                Client to modify.
     */
    public void modifyCliente(Cliente cliente) {
        clienteJPA.modifyCliente(cliente);
    }

    /**
     * ES: Método para eliminar un cliente de la base de datos.
     * EN: Method to delete a client from the database.
     *
     * @param cliente Cliente a eliminar.
     *                Client to delete.
     */
    public void eliminarCliente(Cliente cliente) {
        clienteJPA.deleteCliente(cliente);
    }

    /**
     * ES: Método para buscar clientes según la ciudad.
     * EN: Method to search for clients by city.
     *
     * @param ciudad Ciudad de búsqueda.
     *               City to search for.
     * @return Lista de clientes que coinciden con la ciudad.
     *         List of clients matching the city.
     */
    public List<Cliente> findClienteByCity(String ciudad) {
        return clienteJPA.findClientListByCity(ciudad);
    }
}