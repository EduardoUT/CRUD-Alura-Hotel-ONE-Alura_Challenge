/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.com.alurahotel.modelo.Reserva;

/**
 * Clase que alberga todas las acciones de operación entre la capa View y la
 * capa DAO.
 *
 * @author Eduardo Reyes Hernández
 */
public class ReservaDAO {

    private final Connection con;

    public ReservaDAO(Connection con) {
        this.con = con;
    }

    /**
     * Devuelve un listado de todas las reservas en la Base de Datos.
     * @return - listaReservas de tipo List. 
     */
    public List<Reserva> listar() {
        List<Reserva> listarReservas = new ArrayList<>();
        String sql = "SELECT id_reserva, fecha_entrada, fecha_salida, valor, forma_pago";
        try {
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Reserva fila = new Reserva(
                            resultSet.getString("ID_RESERVA"),
                            resultSet.getDate("FECHA_ENTRADA"),
                            resultSet.getDate("FECHA_SALIDA"),
                            resultSet.getDouble("VALOR"),
                            resultSet.getString("FORMA_PAGO")
                    );
                    listarReservas.add(fila);
                }
                return listarReservas;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
