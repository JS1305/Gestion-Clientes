package org.example;

import org.example.controllers.ClienteController;
import org.example.entitis.Cliente;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Cliente> clienteList;
    private Cliente cliente;
    void main() {
        ClienteController clienteController = new ClienteController();
        Scanner input = new Scanner(System.in);
        this.cliente = new Cliente();
        int opt;

        while (true) {
            try {
                mostrarMenu();
                opt = input.nextInt();
                input.nextLine();

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
                        System.out.println("Operación no valida");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido. Intentelo nuevamente");
                input.nextLine();
            }
        }
    }

    private void optionFindClientByCity(ClienteController clienteController, Scanner input) {
        System.out.println("Ingrese la ciudad a buscar");
        String ciudad = input.nextLine();

        List<Cliente> clientesByCity = clienteController.findClienteByCity(ciudad);

        if (!clientesByCity.isEmpty()) {
            for (Cliente cliente : clientesByCity) {
                System.out.println(cliente);
            }
        } else {
            System.out.println("\nNingún cliente registrado en esa ciudad");
            System.out.println("-------------------------------------------");
        }
    }

    private void optionDeleteClient(ClienteController clienteController, Scanner input) {
        optionPrintClientList(clienteController);

        System.out.println("Ingrese el número del cliente a eliminar:");
        int pos = input.nextInt();
        input.nextLine();

        if (pos < 1 || pos > clienteList.size()) {
            System.out.println("❌ Posición no válida.");
            return;
        }

        Cliente cliente = clienteList.get(pos - 1);

        System.out.println("¿Está seguro de eliminar a: " + cliente.getNombre() + " " + cliente.getApellidos() + "? (S/N)");
        String confirm = input.nextLine().trim().toUpperCase();

        if (!confirm.equals("S")) {
            System.out.println("Operación cancelada.");
            return;
        }

        clienteController.eliminarCliente(cliente);
        System.out.println("✔ Cliente eliminado.");
    }

    private void updateClientForm(Cliente cliente, Scanner input) {

        cliente.setNombre(inputEditable("Nombre", cliente.getNombre(), input));

        cliente.setApellidos(inputEditable("Apellidos", cliente.getApellidos(), input));

        System.out.println("Sexo actual: " + cliente.getSexo());
        System.out.println("Nuevo sexo (MASCULINO/FEMENINO) — ENTER para mantener:");
        String entradaSexo = input.nextLine().trim();
        if (!entradaSexo.isEmpty()) {
            try {
                cliente.setSexo(Cliente.Sexo.valueOf(entradaSexo.toUpperCase()));
            } catch (Exception e) {
                System.out.println("Valor inválido. Se mantiene el actual.");
            }
        }

        cliente.setCiudad(inputEditable("Ciudad", cliente.getCiudad(), input));

        cliente.setFecha(inputEditable("Fecha (DD/MM/AAAA)", cliente.getFecha(), input));

        cliente.setTelefono(inputEditable("Teléfono", cliente.getTelefono(), input));

        cliente.setCorreo(inputEditable("Correo", cliente.getCorreo(), input));
    }

    private void optionUpdateClient(ClienteController clienteController, Scanner input) {

        optionPrintClientList(clienteController);
        System.out.println("Ingrese el número del cliente a actualizar:");
        int clientPosition = input.nextInt();
        input.nextLine();

        if (clientPosition < 1 || clientPosition > clienteList.size()) {
            System.out.println("❌ Posición no válida");
            return;
        }

        Cliente clienteToUpdate = clienteList.get(clientPosition - 1);

        updateClientForm(clienteToUpdate, input);

        clienteController.modifyCliente(clienteToUpdate);

        System.out.println("✔ Cliente actualizado satisfactoriamente");
    }

    private void formClient(Cliente cliente, Scanner input) {

        cliente.setNombre(inputObligatorio("Ingrese nombre:", input));

        cliente.setApellidos(inputObligatorio("Ingrese apellidos:", input));

        cliente.setSexo(inputSexo("Ingrese sexo (MASCULINO/FEMENINO):", input));

        cliente.setCiudad(inputObligatorio("Ingrese ciudad:", input));

        cliente.setFecha(inputFecha("Ingrese fecha de nacimiento (DD/MM/AAAA):", input));

        cliente.setTelefono(inputTelefono("Ingrese número de teléfono:", input));

        cliente.setCorreo(inputEmail("Ingrese correo electrónico:", input));
    }

    private String inputObligatorio(String mensaje, Scanner input) {
        String valor;
        do {
            System.out.println(mensaje);
            valor = input.nextLine().trim();

            if (valor.isEmpty()) {
                System.out.println("❌ Este campo es obligatorio. Inténtelo de nuevo.");
            }
        } while (valor.isEmpty());
        return valor;
    }

    private Cliente.Sexo inputSexo(String mensaje, Scanner input) {
        while (true) {
            System.out.println(mensaje);
            String entrada = input.nextLine().trim().toUpperCase();

            try {
                return Cliente.Sexo.valueOf(entrada);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Valor inválido. Debe ingresar MASCULINO o FEMENINO.");
            }
        }
    }


    private void optionPrintClientList(ClienteController clienteController) {
        clienteList = clienteController.listarCliente();
        if (!clienteList.isEmpty()) {
            for (int i = 0; i < clienteList.size(); i++) {
                Cliente cliente = clienteList.get(i);
                System.out.println((i + 1) + " - " + cliente);
            }
        } else {
            System.out.println("\nNingún cliente registrado");
            System.out.println("-------------------------------------------");
        }
    }

    private void optionCreateCliente(ClienteController clienteController, Scanner input) {

        Cliente nuevoCliente = new Cliente();  // <--- se crea uno nuevo siempre

        formClient(nuevoCliente, input);

        clienteController.crearCliente(nuevoCliente);

        System.out.println("\nCliente creado satisfactoriamente !");
        System.out.println("-------------------------------------------");
    }

    private static void mostrarMenu() {
        System.out.println("\nBienvenido al gestor de clientes !");
        System.out.println("\nCual de las siguientes operaciones deseas ejecutar ");
        System.out.println("0 - Salir");
        System.out.println("1 - Agregar nuevo cliente");
        System.out.println("2 - Listado de clientes");
        System.out.println("3 - Actualizar informacion de un cliente");
        System.out.println("4 - Eliminar cliente");
        System.out.println("5 - Busqueda de cliente por ciudad");
    }

    private String inputEmail(String mensaje, Scanner input) {
        String email;
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        do {
            System.out.println(mensaje);
            email = input.nextLine().trim();

            if (!email.matches(regex)) {
                System.out.println("❌ Email inválido. Ejemplo válido: ejemplo@correo.com");
                email = "";
            }

        } while (email.isEmpty());

        return email;
    }

    private String inputTelefono(String mensaje, Scanner input) {
        String tel;
        String regex = "^[0-9]{7,13}$"; // ajustable

        do {
            System.out.println(mensaje);
            tel = input.nextLine().trim();

            if (!tel.matches(regex)) {
                System.out.println("❌ Teléfono inválido. Use solo números (7-13 dígitos).");
                tel = "";
            }
        } while (tel.isEmpty());
        return tel;
    }

    private String inputFecha(String mensaje, Scanner input) {
        String fecha;
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";

        do {
            System.out.println(mensaje);
            fecha = input.nextLine().trim();

            if (!fecha.matches(regex)) {
                System.out.println("❌ Fecha inválida. Formato correcto: DD/MM/AAAA");
                fecha = "";
            }

        } while (fecha.isEmpty());

        return fecha;
    }

    private String inputEditable(String mensaje, String valorActual, Scanner input) {
        System.out.println(mensaje + " (actual: " + valorActual + ") — ENTER para mantener:");
        String nuevoValor = input.nextLine().trim();

        return nuevoValor.isEmpty() ? valorActual : nuevoValor;
    }

    private void optionExit(){
        System.out.println("¡Adiós!");
    }
}




