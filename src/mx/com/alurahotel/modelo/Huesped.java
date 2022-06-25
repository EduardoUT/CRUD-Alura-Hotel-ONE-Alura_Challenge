/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.modelo;

import java.sql.Date;

/**
 * Clase para el modelado del objeto Huesped.
 *
 * @author Eduardo Reyes Hernández
 */
public class Huesped {

    private Integer idHuesped;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    private String idReserva;

    public Huesped(int idHuesped, String nombre, String apellido,
            Date fechaNacimiento, String nacionalidad, String telefono,
            String idReserva) {
        this.idHuesped = idHuesped;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.idReserva = idReserva;
    }

    public Huesped(String nombre, String apellido, Date fechaNacimiento,
            String nacionalidad, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
    }

    /**
     * @return the idHuesped
     */
    public Integer getIdHuesped() {
        return idHuesped;
    }

    /**
     * @param idHuesped the idHuesped to set
     */
    public void setIdHuesped(Integer idHuesped) {
        this.idHuesped = idHuesped;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the idReserva
     */
    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    @Override
    public String toString() {
        return String.format("{ID: %s, Nombre: %s, Apellido: %s, FechaNacimiento: %s, Nacionalidad: %s, Teléfono: %s, ID_Reserva: %s}",
                this.idHuesped,
                this.nombre,
                this.apellido,
                this.fechaNacimiento,
                this.nacionalidad,
                this.telefono,
                this.idReserva
        );
    }
}
