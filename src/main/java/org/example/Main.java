package org.example;

import org.example.controllers.ClienteController;
import org.example.entitis.Cliente;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    // ES: Lista temporal usada para mostrar y seleccionar clientes
    // EN: Temporary list used to display and select clients
    private List<Cliente> clienteList;

    // ES: Cliente actual en operaciones (creación/edición)
    // EN: Current client in create/edit operations
    private Cliente cliente;

    // ES: Método principal del programa (debe ser static main normalmente)
    // EN: Main program method (normally should be static main)
    void main() {
        ClienteController clienteController = new ClienteController();
        Scanner input = new Scanner(System.in);
        this.cliente = new Cliente();
        int opt;

        // ES: Bucle principal del menú
        // EN: Main menu loop
        while (true) {
            try {
                mostrarMenu(); // ES/EN: Show menu
                opt = input.nextInt();
                input.nextLine(); // ES/EN: Clear input buffer

                switch (opt) {
                    case 0:
                        optionExit();
                        return;
                    case 1:
                        optionCreateCliente(clienteController, input);
                        break;
                    case 2:
                        optionPrintClientList(clienteController);
                        break;
                    case 3:
                        optionUpdateClient(clienteController, input);
                        break;
                    case 4:
                        optionDeleteClient(clienteController, input);
                        break;
                    case 5:
                        optionFindClientByCity(clienteController, input);
                        break;
                    default:
                        System.out.println("Operación no valida / Invalid operation");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un número válido / Please enter a valid number");
                input.nextLine();
            }
        }
    }

    // ES: Buscar clientes por ciudad
    // EN: Search clients by city
    private void optionFindClientByCity(ClienteController clienteController, Scanner input) {
        System.out.println("Ingrese la ciudad a buscar / Enter the city to search:");
        String ciudad = input.nextLine();

        List<Cliente> clientesByCity = clienteController.findClienteByCity(ciudad);

        if (!clientesByCity.isEmpty()) {
            for (Cliente cliente : clientesByCity) {
                System.out.println(cliente);
            }
        } else {
            System.out.println("\nNingún cliente registrado en esa ciudad / No clients found in that city");
            System.out.println("-------------------------------------------");
        }
    }

    // ES: Eliminar un cliente con confirmación
    // EN: Delete a client with confirmation
    private void optionDeleteClient(ClienteController clienteController, Scanner input) {
        optionPrintClientList(clienteController);

        System.out.println("Ingrese el número del cliente a eliminar / Enter the number of the client to delete:");
        int pos = input.nextInt();
        input.nextLine();

        if (pos < 1 || pos > clienteList.size()) {
            System.out.println("❌ Posición no válida / Invalid position");
            return;
        }

        Cliente cliente = clienteList.get(pos - 1);

        System.out.println(
                "¿Está seguro de eliminar a " + cliente.getNombre() + " " + cliente.getApellidos() +
                        "? (S/N) / Are you sure you want to delete this client? (Y/N)"
        );
        String confirm = input.nextLine().trim().toUpperCase();

        if (!confirm.equals("S") && !confirm.equals("Y")) {
            System.out.println("Operación cancelada / Operation cancelled");
            return;
        }

        clienteController.eliminarCliente(cliente);
        System.out.println("✔ Cliente eliminado / Client deleted");
    }

    // ES: Formulario para actualizar un cliente permitiendo mantener valores actuales
    // EN: Form to update a client allowing to keep the current values
    private void updateClientForm(Cliente cliente, Scanner input) {

        cliente.setNombre(inputEditable("Nombre / Name", cliente.getNombre(), input));

        cliente.setApellidos(inputEditable("Apellidos / Last name", cliente.getApellidos(), input));

        System.out.println("Sexo actual: " + cliente.getSexo() + " / Current gender:");
        System.out.println("Nuevo sexo (MASCULINO/FEMENINO) — ENTER para mantener / New gender — ENTER to keep:");
        String entradaSexo = input.nextLine().trim();
        if (!entradaSexo.isEmpty()) {
            try {
                cliente.setSexo(Cliente.Sexo.valueOf(entradaSexo.toUpperCase()));
            } catch (Exception e) {
                System.out.println("Valor inválido / Invalid value");
            }
        }

        cliente.setCiudad(inputEditable("Ciudad / City", cliente.getCiudad(), input));

        cliente.setFecha(inputEditable("Fecha / Birthdate (DD/MM/AAAA)", cliente.getFecha(), input));

        cliente.setTelefono(inputEditable("Teléfono / Phone number", cliente.getTelefono(), input));

        cliente.setCorreo(inputEditable("Correo / Email", cliente.getCorreo(), input));
    }

    // ES: Lógica para actualizar cliente desde el menú
    // EN: Logic to update client from the menu
    private void optionUpdateClient(ClienteController clienteController, Scanner input) {

        optionPrintClientList(clienteController);
        System.out.println("Ingrese el número del cliente a actualizar / Enter client number to update:");
        int clientPosition = input.nextInt();
        input.nextLine();

        if (clientPosition < 1 || clientPosition > clienteList.size()) {
            System.out.println("❌ Posición no válida / Invalid position");
            return;
        }

        Cliente clienteToUpdate = clienteList.get(clientPosition - 1);

        updateClientForm(clienteToUpdate, input);

        clienteController.modifyCliente(clienteToUpdate);

        System.out.println("✔ Cliente actualizado satisfactoriamente / Client updated successfully");
    }

    // ES: Formulario de creación de cliente
    // EN: Client creation form
    private void formClient(Cliente cliente, Scanner input) {

        cliente.setNombre(inputObligatorio("Ingrese nombre / Enter name:", input));

        cliente.setApellidos(inputObligatorio("Ingrese apellidos / Enter last name:", input));

        cliente.setSexo(inputSexo("Ingrese sexo / Enter gender:", input));

        cliente.setCiudad(inputObligatorio("Ingrese ciudad / Enter city:", input));

        cliente.setFecha(inputFecha("Ingrese fecha de nacimiento (DD/MM/AAAA) / Enter birthdate:", input));

        cliente.setTelefono(inputTelefono("Ingrese número de teléfono / Enter phone number:", input));

        cliente.setCorreo(inputEmail("Ingrese correo electrónico / Enter email:", input));
    }

    // ES: Entrada obligatoria (no permite vacío)
    // EN: Required input (does not allow empty)
    private String inputObligatorio(String mensaje, Scanner input) {
        String valor;
        do {
            System.out.println(mensaje);
            valor = input.nextLine().trim();

            if (valor.isEmpty()) {
                System.out.println("❌ Campo obligatorio / Required field");
            }
        } while (valor.isEmpty());
        return valor;
    }

    // ES: Entrada para valores del enum Sexo
    // EN: Input for Sexo enum values
    private Cliente.Sexo inputSexo(String mensaje, Scanner input) {
        while (true) {
            System.out.println(mensaje);
            String entrada = input.nextLine().trim().toUpperCase();

            try {
                return Cliente.Sexo.valueOf(entrada);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Valor inválido / Invalid value");
            }
        }
    }

    // ES: Mostrar listado de clientes
    // EN: Display client list
    private void optionPrintClientList(ClienteController clienteController) {
        clienteList = clienteController.listarCliente();
        if (!clienteList.isEmpty()) {
            for (int i = 0; i < clienteList.size(); i++) {
                Cliente cliente = clienteList.get(i);
                System.out.println((i + 1) + " - " + cliente);
            }
        } else {
            System.out.println("\nNingún cliente registrado / No clients registered");
            System.out.println("-------------------------------------------");
        }
    }

    // ES: Crear un nuevo cliente
    // EN: Create a new client
    private void optionCreateCliente(ClienteController clienteController, Scanner input) {

        Cliente nuevoCliente = new Cliente();  // ES/EN: Always create a new client instance

        formClient(nuevoCliente, input);

        clienteController.crearCliente(nuevoCliente);

        System.out.println("\nCliente creado satisfactoriamente / Client created successfully");
        System.out.println("-------------------------------------------");
    }

    // ES: Mostrar menú principal
    // EN: Display main menu
    private static void mostrarMenu() {
        System.out.println("\nBienvenido al gestor de clientes / Welcome to the client manager!");
        System.out.println("0 - Salir / Exit");
        System.out.println("1 - Agregar nuevo cliente / Add new client");
        System.out.println("2 - Listado de clientes / Client list");
        System.out.println("3 - Actualizar cliente / Update client");
        System.out.println("4 - Eliminar cliente / Delete client");
        System.out.println("5 - Buscar por ciudad / Search by city");
    }

    // ES: Validar formato de email
    // EN: Validate email format
    private String inputEmail(String mensaje, Scanner input) {
        String email;
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        do {
            System.out.println(mensaje);
            email = input.nextLine().trim();

            if (!email.matches(regex)) {
                System.out.println("❌ Email inválido / Invalid email");
                email = "";
            }

        } while (email.isEmpty());

        return email;
    }

    // ES: Validar teléfono numérico
    // EN: Validate numeric phone number
    private String inputTelefono(String mensaje, Scanner input) {
        String tel;
        String regex = "^[0-9]{7,13}$";

        do {
            System.out.println(mensaje);
            tel = input.nextLine().trim();

            if (!tel.matches(regex)) {
                System.out.println("❌ Teléfono inválido / Invalid phone number");
                tel = "";
            }
        } while (tel.isEmpty());
        return tel;
    }

    // ES: Validar fecha en formato DD/MM/AAAA
    // EN: Validate date format DD/MM/YYYY
    private String inputFecha(String mensaje, Scanner input) {
        String fecha;
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";

        do {
            System.out.println(mensaje);
            fecha = input.nextLine().trim();

            if (!fecha.matches(regex)) {
                System.out.println("❌ Fecha inválida / Invalid date");
                fecha = "";
            }

        } while (fecha.isEmpty());

        return fecha;
    }

    // ES: Entrada editable que permite dejar valor actual
    // EN: Editable input allowing to keep the current value
    private String inputEditable(String mensaje, String valorActual, Scanner input) {
        System.out.println(mensaje + " (actual/current: " + valorActual + ") — ENTER para mantener / ENTER to keep:");
        String nuevoValor = input.nextLine().trim();

        return nuevoValor.isEmpty() ? valorActual : nuevoValor;
    }

    // ES: Finalizar aplicación
    // EN: Exit application
    private void optionExit(){
        System.out.println("¡Adiós! / Goodbye!");
    }
}