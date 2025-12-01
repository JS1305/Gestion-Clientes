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
        System.out.println("Ingrese el numero del cliente a eliminar");

        int clientPosition = input.nextInt();

        if (clientPosition < 0 || clientPosition > clienteList.size()) {
            System.out.println("Posición no valida");
            return;
        }
        try {
            Cliente clienteToDelete = clienteList.get(clientPosition - 1);
            clienteController.eliminarCliente(clienteToDelete);
            System.out.println("\nCliente eliminado satisfactoriamente !");
        } catch (Exception e){}
    }

    private void optionUpdateClient(ClienteController clienteController, Scanner input) {
        optionPrintClientList(clienteController);
        System.out.println("Ingrese el numero del cliente a actualizar");
        int clientPosition = input.nextInt();

        if (clientPosition < 0 || clientPosition > clienteList.size()) {
            System.out.println("Posición no valida");
            return;
        }
        Cliente clienteToUpdate = clienteList.get(clientPosition - 1);

        formClient(clienteToUpdate, input);

        clienteController.modifyCliente(clienteToUpdate);
        System.out.println("\nCliente actualizado satisfactoriamente !");
        System.out.println("-------------------------------------------");
    }

    private void formClient(Cliente cliente, Scanner input) {

        /*
        System.out.println("Ingrese nombre");
        cliente.setNombre(input.nextLine());
        System.out.println("Ingrese Apellidos");
        cliente.setApellidos(input.nextLine());
        System.out.println("Ingrese sexo MASCULINO/FEMENINO ");
        try {
            cliente.setSexo(Cliente.Sexo.valueOf(input.nextLine().toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println("Valor inválido. Por defecto se asignará MASCULINO.");
            cliente.setSexo(Cliente.Sexo.MASCULINO);
        }
        System.out.println("Ingrese su ciudad");
        cliente.setCiudad(input.nextLine());
        System.out.println("Ingrese fecha de nacimiento");
        cliente.setFecha(input.nextLine());
        System.out.println("Ingrese numero de telefono");
        cliente.setTelefono(input.nextLine());
        System.out.println("Ingrese correro electronico");
        cliente.setCorreo(input.nextLine());
    }
         */
        cliente.setNombre(inputObligatorio("Ingrese nombre: ", input));

        cliente.setApellidos(inputObligatorio("Ingrese apellidos: ", input));

        cliente.setSexo(inputSexo("Ingrese sexo (MASCULINO/FEMENINO): ", input));

        cliente.setCiudad(inputObligatorio("Ingrese su ciudad: ", input));

        cliente.setFecha(inputObligatorio("Ingrese fecha de nacimiento: ", input));

        cliente.setTelefono(inputObligatorio("Ingrese número de teléfono: ", input));

        cliente.setCorreo(inputObligatorio("Ingrese correo electrónico: ", input));
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

    private void optionExit(){
        System.out.println("¡Adiós!");
    }
}


