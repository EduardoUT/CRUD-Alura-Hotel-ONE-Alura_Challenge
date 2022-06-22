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

    public List<Huesped> listar() {
        return huespedDAO.listar();
    }
}
