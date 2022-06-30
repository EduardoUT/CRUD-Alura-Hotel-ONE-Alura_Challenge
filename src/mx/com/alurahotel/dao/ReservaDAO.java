/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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
            String sql = "SELECT id_reserva, fecha_entrada, fecha_salida, valor, forma_pago "
                    + "FROM reservas";
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Reserva fila = new Reserva(
                            resultSet.getString("ID_RESERVA"),
                            resultSet.getDate("FECHA_ENTRADA"),
                            resultSet.getDate("FECHA_SALIDA"),
                            resultSet.getBigDecimal("VALOR"),
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
     * Ejecuta una sentencia MySQL con parámetro LIKE, recibiendo el idReserva
     * índicada.
     *
     * @param idReserva - Clave de la reserva para tomar referencia del
     * registro.
     * @return - Devúelve la lista de reservas según el idReserva índicado.
     */
    public List<Reserva> listar(String idReserva) {
        List<Reserva> listaReservas = new ArrayList<>();
        String sql = "SELECT\n"
                + "id_reserva, fecha_entrada, fecha_salida, valor, forma_pago\n"
                + "FROM reservas\n"
                + "WHERE id_reserva LIKE ?";
        try {
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.setString(1, idReserva.concat("%"));
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Reserva fila = new Reserva(
                            resultSet.getString("ID_RESERVA"),
                            resultSet.getDate("FECHA_ENTRADA"),
                            resultSet.getDate("FECHA_SALIDA"),
                            resultSet.getBigDecimal("VALOR"),
                            resultSet.getString("FORMA_PAGO")
                    );
                    listaReservas.add(fila);
                }
                return listaReservas;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Inténtelo más tarde.",
                    "Error al traer los datos.",
                    JOptionPane.ERROR_MESSAGE
            );
            throw new RuntimeException(e);
        }
    }

    /**
     * Permite almacenar el modelo de datos de Reserva, en la tabla reservas de
     * MySQL, usar sólo si no hay dependencia de exceptions.
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
                preparedStatement.setBigDecimal(4, reserva.getValorReserva());
                preparedStatement.setString(5, reserva.getFormaPago());
                preparedStatement.execute();
                try ( ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                    while (resultSet.next()) {
                        System.out.println(
                                String.format("Fue insertada la reserva: %s", reserva)
                        );
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar el registro.", "Error al guardar los datos.", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }

    /**
     * Permite actualizar el registro en la Base de Datos, tomando los valores
     * del controlador.
     *
     * @param idReserva - Clave de la reserva para tomar referencia del
     * registro.
     * @param fechaEntrada - Fecha de entrada del húesped.
     * @param fechaSalida - Fecha de salida del húesped.
     * @param valorReserva - Valor monetario de la reserva.
     * @param formaPago - Forma de pago del húesped.
     * @return - Retrora el número de registros actualizados.
     */
    public int actualizar(String idReserva, Date fechaEntrada,
            Date fechaSalida, double valorReserva, String formaPago) {
        try {
            String sql = "UPDATE reservas "
                    + "SET fecha_entrada = ?, fecha_salida = ?, valor = ?, forma_pago = ? "
                    + "WHERE id_reserva = ?";
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.setDate(1, fechaEntrada);
                preparedStatement.setDate(2, fechaSalida);
                preparedStatement.setDouble(3, valorReserva);
                preparedStatement.setString(4, formaPago);
                preparedStatement.setString(5, idReserva);
                preparedStatement.execute();
                int updateCount = preparedStatement.getUpdateCount();
                return updateCount;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el registro.");
            throw new RuntimeException(e);
        }
    }
}
