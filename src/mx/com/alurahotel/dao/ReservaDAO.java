/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
     *
     * @return - listaReservas de tipo List.
     */
    public List<Reserva> listar() {
        List<Reserva> listarReservas = new ArrayList<>();
        try {
            String sql = "SELECT id_reserva, fecha_entrada, fecha_salida, valor, forma_pago";
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

    /**
     * Permite almacenar el modelo de datos de Reserva, en la tabla reservas de
     * MySQL.
     *
     * @param reserva - Objeto de tipo Reserva.
     */
    public void guardar(Reserva reserva) {
        try {
            String sql = "INSERT INTO reservas (id_reserva, fecha_entrada, fecha_salida, valor, forma_pago) "
                    + "VALUES (?, ?, ?, ?, ?)";
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
                preparedStatement.setString(1, reserva.getId_Reserva());
                preparedStatement.setDate(2, reserva.getFechaEntrada());
                preparedStatement.setDate(3, reserva.getFechaSalida());
                preparedStatement.setDouble(4, reserva.getValorReserva());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
