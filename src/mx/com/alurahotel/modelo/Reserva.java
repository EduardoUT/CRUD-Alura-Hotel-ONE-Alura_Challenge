/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.modelo;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;
import mx.com.alurahotel.util.ConvertirFecha;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Reserva {

    private String idReserva;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private BigDecimal totalReserva;
    

    public Reserva() {
        this.idReserva = generarIdReserva();
    }

    public Reserva(String idReserva, Date fechaEntrada, Date fechaSalida, double valor, String formaPago) {
        this.idReserva = idReserva;
        this.fechaEntrada = ConvertirFecha.convertirDateALocalDate(fechaEntrada);
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
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    /**
     * @param fechaEntrada the fechaEntrada to set
     */
    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    /**
     * @return the fechaSalida
     */
    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * @return the totalReserva
     */
    public BigDecimal getTotalReserva() {
        return totalReserva;
    }

    /**
     * @param totalReserva the totalReserva to set
     */
    public void setTotalReserva(BigDecimal totalReserva) {
        this.totalReserva = totalReserva;
    }
    
    private String generarIdReserva() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @Override
    public String toString() {
        return "ID: " + this.idReserva;
    }
}
