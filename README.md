# Gestión de Clientes - Proyecto Técnico en Java

---

##  Sección 1: Español

### Descripción
Este proyecto es una **aplicación de gestión de clientes**, desarrollada en **Java**, que permite realizar operaciones de **crear, listar, actualizar, eliminar y buscar clientes** utilizando **JPA y Hibernate** para la persistencia en base de datos.

El sistema permite:
- Crear clientes con información obligatoria: nombre, apellidos, sexo, ciudad, fecha de nacimiento, teléfono y correo electrónico.
- Listar todos los clientes registrados.
- Actualizar la información de un cliente existente, manteniendo los valores actuales si se desea.
- Eliminar clientes con confirmación por parte del usuario.
- Buscar clientes por ciudad y mostrar los resultados de forma legible en consola.

El proyecto incluye:
- Clase `Cliente` con atributos y métodos getter/setter.
- Clase `ClienteController` para manejar la lógica de negocio.
- Clase `ClienteJPA` para operaciones CRUD usando JPA/Hibernate.
- Clase `ConfiJPA` para configuración y obtención del `EntityManager`.
- Clase `Main` para ejecutar el menú interactivo del programa.

### Requisitos
- Java 17 o superior.
- IDE recomendado: IntelliJ IDEA, Eclipse o similar.
- Base de datos configurada en `persistence.xml` con unidad de persistencia `miUnidad`.

### Ejecución
1. Clonar o descargar el repositorio:

   https://github.com/JS1305/Gestion-Clientes

# Client Management - Java Technical Project

---
## Section 2: English


### Description
This project is a **client management application**, developed in **Java**, which allows performing **create, list, update, delete, and search operations** for clients using **JPA and Hibernate** for database persistence.

The system allows:
- Creating clients with mandatory information: first name, last name, gender, city, birth date, phone number, and email.
- Listing all registered clients.
- Updating information of an existing client while keeping current values if desired.
- Deleting clients with user confirmation.
- Searching clients by city and displaying the results in a readable format in the console.

The project includes:
- `Cliente` class with attributes and getter/setter methods.
- `ClienteController` class to handle business logic.
- `ClienteJPA` class for CRUD operations using JPA/Hibernate.
- `ConfiJPA` class for configuration and obtaining the `EntityManager`.
- `Main` class to run the program’s interactive menu.

### Requirements
- Java 17 or higher.
- Recommended IDE: IntelliJ IDEA, Eclipse, or similar.
- Database configured in `persistence.xml` with persistence unit `miUnidad`.

### Execution
1. Clone or download the repository:

https://github.com/JS1305/Gestion-Clientes

```bash

Abrir el proyecto en su IDE preferido.  
Ejecutar la clase `Main`.  
Seguir las instrucciones del menú interactivo para crear, listar, actualizar, eliminar o buscar clientes por ciudad.

## Uso
- **Crear cliente:** todos los campos son obligatorios, con validación de correo, teléfono y fecha.  
- **Listar clientes:** muestra todos los clientes registrados.  
- **Actualizar cliente:** permite editar campos, manteniendo los valores actuales si se presiona ENTER.  
- **Eliminar cliente:** solicita confirmación antes de eliminar.  
- **Buscar por ciudad:** muestra los clientes registrados en la ciudad indicada.

## Notas
- Proyecto educativo y funcional, ideal para practicar Java y JPA.  
- Mejoras posibles: interfaz gráfica, validaciones avanzadas o registro de errores.  
- Todos los datos se almacenan en la base de datos definida en `persistence.xml`.

## Autor
Sebastian Riveros  
Email: sebasriveros1305@gmail.com  
GitHub: JS1305

Open the project in your preferred IDE.  
Run the `Main` class.  
Follow the instructions in the interactive menu to create, list, update, delete, or search clients by city.

## Usage
- **Create client:** all fields are mandatory, with validation for email, phone, and date.  
- **List clients:** displays all registered clients.  
- **Update client:** allows editing fields while keeping current values if ENTER is pressed.  
- **Delete client:** asks for confirmation before deleting.  
- **Search by city:** displays clients registered in the specified city.

## Notes
- Educational and functional project, ideal for practicing Java and JPA.  
- Possible improvements: graphical interface, advanced validations, or error logging.  
- All data is stored in the database defined in `persistence.xml`.

## Author
Sebastian Riveros  
Email: sebasriveros1305@gmail.com  
GitHub: JS1305
