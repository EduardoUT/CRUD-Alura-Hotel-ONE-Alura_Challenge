/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.controller;

import java.util.List;
import mx.com.alurahotel.dao.ReservaDAO;
import mx.com.alurahotel.factory.ConnectionFactory;
import mx.com.alurahotel.modelo.Reserva;

/**
 * Clase que alberga todas las acciones de operación entre la capa View y la
 * capa DAO.
 *
 * @author Eduardo Reyes Hernández
 */
public class ReservaController {

    private final ReservaDAO reservaDAO;

    /**
     * Creando conexion para operaciones con MySQL con la tabla de reservas.
     */
    public ReservaController() {
        this.reservaDAO = new ReservaDAO(new ConnectionFactory().realizarConexion());
    }

    /**
     * Obteniendo el listado del DAO de huespedes.
     *
     * @return - List de reservas existentes.
     */
    public List<Reserva> listar() {
        return reservaDAO.listar();
    }

    /**
     * Almacenando la información obtenida del View, en la capa DAO.
     *
     * @param reserva - Objeto de tipo reserva.
     */
    public void guardar(Reserva reserva) {
        reservaDAO.guardar(reserva);
    }
}
