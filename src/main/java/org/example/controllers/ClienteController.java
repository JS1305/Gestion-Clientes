package org.example.controllers;

import org.example.entitis.Cliente;
import org.example.persistece.ClienteJPA;

import java.util.List;

public class ClienteController {
    private ClienteJPA clienteJPA;

    public ClienteController(){
        this.clienteJPA = new ClienteJPA();
    }
    public void crearCliente(Cliente cliente) {
        clienteJPA.crearCliente(cliente);
    }

    public List<Cliente> listarCliente() {
       return clienteJPA.listarCliente();
    }

    public void modifyCliente(Cliente cliente) {
        clienteJPA.modifyCliente(cliente);
    }

    public void eliminarCliente(Cliente cliente) {
        clienteJPA.deleteCliente(cliente);
    }

    public List<Cliente> findClienteByCity(String ciudad) {
        return clienteJPA.findClientListByCity(ciudad);
    }
}
