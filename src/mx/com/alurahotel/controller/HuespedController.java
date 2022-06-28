/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.controller;

import java.sql.Date;
import java.util.List;
import mx.com.alurahotel.dao.HuespedDAO;
import mx.com.alurahotel.factory.ConnectionFactory;
import mx.com.alurahotel.modelo.Huesped;

/**
 *
 * Clase que posee todas las acciones de operación entre la capa View y la capa
 * DAO.
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
     * Permite consultar los húespedes acorde a su apellido(s).
     *
     * @param apellido - Apellido del húesped.
     * @return - Lista de húespedes según su apellido(s).
     */
    public List<Huesped> listar(String apellido) {
        return huespedDAO.listar(apellido);
    }

    /**
     * Recopila los datos del húesped obtenidos del View y agrega el idReserva,
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

    /**
     * Recopila los datos del húespedo obtenidos del modelo View para ser
     * procesados en el DAO.
     *
     * @param nombre - Nombre del húesped.
     * @param apellido - Apellido del húesped.
     * @param fechaNacimiento - Fecha de nacimiento del húesped.
     * @param nacionalidad - Nacionalidad del húesped.
     * @param telefono - Télefono del húesped.
     * @return
     */
    public int actualizar(Integer idHuesped, String nombre, String apellido, Date fechaNacimiento,
            String nacionalidad, String telefono) {
        return huespedDAO.actualizar(idHuesped, nombre, apellido, fechaNacimiento, nacionalidad, telefono);
    }

    /**
     * Recopila las claves asociadas del húesped y la reserva.
     *
     * @param idHuesped - Clave de resferencia para identificac al húesped
     * actual.
     * @param idReserva - Clave de la reserva para tomar referencia del
     * registro.
     * @return - Número de registros eliminados.
     */
    public int eliminar(Integer idHuesped, String idReserva) {
        return huespedDAO.eliminar(idHuesped, idReserva);
    }
}
