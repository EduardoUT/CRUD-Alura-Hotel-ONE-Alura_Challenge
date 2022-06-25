/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.controller;

import java.util.List;
import mx.com.alurahotel.dao.HuespedDAO;
import mx.com.alurahotel.factory.ConnectionFactory;
import mx.com.alurahotel.modelo.Huesped;

/**
 *
 * Clase que alberga todas las acciones de operación entre la capa View y la
 * capa DAO.
 *
 * @author Eduardo Reyes Hernández
 */
public class HuespedController {

    private final HuespedDAO huespedDAO;

    /**
     * Creando conexion para operaciones con MySQL.
     */
    public HuespedController() {
        this.huespedDAO = new HuespedDAO(new ConnectionFactory().realizarConexion());
    }

    /**
     * Obteniendo el listado de la Base de Datos de los húespedes.
     *
     * @return - List de húespedes.
     */
    public List<Huesped> listar() {
        return huespedDAO.listar();
    }

    /**
     * Almacena los datos del húesped obtenidos del View y agrega el idReserva,
     * para generar relación con la tabla en MySQL de reservas.
     *
     * @param huesped - Objeto de tipo Huesped.
     * @param idReserva - El idReserva que se generá al crear un nuevo objeto
     * Reserva.
     * @param reserva
     */
    public void guardar(Huesped huesped, String idReserva) {
        huesped.setIdReserva(idReserva);
        huespedDAO.guardar(huesped);
    }
}
