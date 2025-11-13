package org.example.entitis;

import jakarta.persistence.*;

@Entity
@Table (name="clients")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,length = 50)
    private String nombre;

    @Column(nullable = false,length = 50)
    private String apellidos;

    @Column(nullable = false,length = 10)
    private Sexo sexo;

    @Column(nullable = false,length = 50)
    private String ciudad;

    @Column(nullable = false,length = 10)
    private String fecha;

    @Column(nullable = false,length = 13)
    private String telefono;

    @Column(nullable = false,length = 50)
    private String correo;


    public Cliente() {
    }

    public Cliente(String nombre, String apellidos, Sexo sexo, String ciudad, String fecha, String telefono, String correo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.ciudad = ciudad;
        this.fecha = fecha;
        this.telefono = telefono;
        this.correo = correo;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Cliente: [" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", sexo='" + sexo + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", fecha='" + fecha + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ']';
    }


    public enum Sexo {
        MASCULINO,
        FEMENINO
    }
}
