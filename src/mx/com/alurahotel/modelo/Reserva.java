/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.modelo;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Reserva {

    private final String id_Reserva;
    private LocalTime fechaEntrada;
    private LocalTime fechaSalida;
    private BigDecimal totalReserva;

    public Reserva() {
        this.id_Reserva = generarIdReserva();
    }
    
    /**
     * @return the id_Reserva
     */
    public String getId_Reserva() {
        return id_Reserva;
    }

    /**
     * @return the fechaEntrada
     */
    public LocalTime getFechaEntrada() {
        return fechaEntrada;
    }

    /**
     * @param fechaEntrada the fechaEntrada to set
     */
    public void setFechaEntrada(LocalTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    /**
     * @return the fechaSalida
     */
    public LocalTime getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(LocalTime fechaSalida) {
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
        return "ID: " + this.id_Reserva;
    }
}
