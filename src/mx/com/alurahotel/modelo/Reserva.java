/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.modelo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Reserva {

    private final String idReserva;
    private Date fechaEntrada;
    private Date fechaSalida;
    private BigDecimal valorReserva;
    private String formaPago;

    public Reserva(Date fechaEntrada, Date fechaSalida, BigDecimal valorReserva, String formaPago) {
        this.idReserva = generarIdReserva();
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorReserva = valorReserva;
        this.formaPago = formaPago;
    }

    public Reserva(String idReserva, Date fechaEntrada, Date fechaSalida, BigDecimal valorReserva, String formaPago) {
        this.idReserva = idReserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorReserva = valorReserva;
        this.formaPago = formaPago;
    }

    /**
     * @return the id_Reserva
     */
    public String getId_Reserva() {
        return idReserva;
    }

    /**
     * @return the fechaEntrada
     */
    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    /**
     * @param fechaEntrada the fechaEntrada to set
     */
    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    /**
     * @return the fechaSalida
     */
    public Date getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * @return the totalReserva
     */
    public BigDecimal getValorReserva() {
        return valorReserva;
    }

    /**
     * @param valorReserva the totalReserva to set
     */
    public void setValorReserva(BigDecimal valorReserva) {
        this.valorReserva = valorReserva;
    }

    /**
     * @return the formaPago
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * Generando ID, para ser asignado al crear el objeto
     * en el constructor, cuando no se específique un id.
     * 
     * @return - ID aleatorio en base al estándar UUID. 
     */
    private String generarIdReserva() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @Override
    public String toString() {
        return String.format("{ID: %s, FechaEntrada: %s, FechaSalida: %s, Total: %f, FormaPago: %s}",
                this.idReserva,
                this.fechaEntrada,
                this.fechaSalida,
                this.valorReserva,
                this.formaPago
        );
    }
}
