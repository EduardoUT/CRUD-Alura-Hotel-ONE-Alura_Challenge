/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.dao;

import mx.com.alurahotel.modelo.Huesped;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Definiendo capa Data Access Object la cual permite acceder a los atributos de
 * la clase Huesped, y interactuar con las operaciones de la Base de Datos.
 *
 * @author Eduardo Reyes Hernández
 */
public class HuespedDAO {

    private final Connection con;

    public HuespedDAO(Connection con) {
        this.con = con;
    }

    /**
     * Ejecuta una sentencia SELECT en MySQL de todos los húespedes.
     *
     * @return - Lista de húespedes en la Base de Datos de MySQL.
     */
    public List<Huesped> listar() {
        List<Huesped> listaHuespedes = new ArrayList<>();
        String sql = "SELECT id_huesped, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva "
                + "FROM huespedes";
        try {
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Huesped fila = new Huesped(
                            resultSet.getInt("ID_HUESPED"),
                            resultSet.getString("NOMBRE"),
                            resultSet.getString("APELLIDO"),
                            resultSet.getDate("FECHA_NACIMIENTO"),
                            resultSet.getString("NACIONALIDAD"),
                            resultSet.getString("TELEFONO"),
                            resultSet.getString("ID_RESERVA")
                    );
                    listaHuespedes.add(fila);
                }
                return listaHuespedes;
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
     * Ejecuta una sentencia MySQL con parámetro LIKE, recibiendo el apellido
     * del húesped.
     *
     * @param apellido - Apellido del húesped.
     * @return - Lista de húespedes según el apellido índicado.
     */
    public List<Huesped> listar(String apellido) {
        List<Huesped> listaHuespedes = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "id_huesped, nombre, apellido, fecha_nacimiento,\n"
                    + "nacionalidad, telefono, id_reserva\n"
                    + "FROM huespedes\n"
                    + "WHERE apellido LIKE ?";
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.setString(1, apellido.concat("%"));
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Huesped fila = new Huesped(
                            resultSet.getInt("ID_HUESPED"),
                            resultSet.getString("NOMBRE"),
                            resultSet.getString("APELLIDO"),
                            resultSet.getDate("FECHA_NACIMIENTO"),
                            resultSet.getString("NACIONALIDAD"),
                            resultSet.getString("TELEFONO"),
                            resultSet.getString("ID_RESERVA")
                    );
                    listaHuespedes.add(fila);
                }
                return listaHuespedes;
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
     * Permite almacenar el modelo de datos de Huesped, en la tabla reservas de
     * MySQL.
     *
     * @param huesped - Objeto de tipo Reserva.
     */
    public void guardar(Huesped huesped) {
        try {
            String sql = "INSERT INTO huespedes "
                    + "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
                preparedStatement.setString(1, huesped.getNombre());
                preparedStatement.setString(2, huesped.getApellido());
                preparedStatement.setDate(3, huesped.getFechaNacimiento());
                preparedStatement.setString(4, huesped.getNacionalidad());
                preparedStatement.setString(5, huesped.getTelefono());
                preparedStatement.setString(6, huesped.getIdReserva());
                preparedStatement.execute();
                try ( ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        huesped.setIdHuesped(resultSet.getInt(1));
                        System.out.println(
                                String.format("Fue guardado con éxito el "
                                        + "húesped: %s", huesped)
                        );
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al realizar el registro.",
                    "Error al guardar los datos.",
                    JOptionPane.ERROR_MESSAGE
            );
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para actualizar los campos de la tabla huespedes en MySQL.
     *
     * @param idHuesped - Clave de resferencia para identificac al húesped
     * actual.
     * @param nombre - Nombre obtenido del modelo del JTable.
     * @param apellido - Apellido obtenido del modelo del JTable.
     * @param fechaNacimiento - Fecha de Nacimiento obtenido del modelo del
     * JTable.
     * @param nacionalidad - Nacionalidad obtenida del modelo del JTable.
     * @param telefono -
     * @return
     */
    public int actualizar(Integer idHuesped, String nombre, String apellido, Date fechaNacimiento,
            String nacionalidad, String telefono) {
        try {
            String sql = "UPDATE huespedes "
                    + "SET nombre = ?, apellido = ?, fecha_nacimiento = ?, "
                    + "nacionalidad = ?, telefono = ? "
                    + "WHERE id_huesped = ?";
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, apellido);
                preparedStatement.setDate(3, fechaNacimiento);
                preparedStatement.setString(4, nacionalidad);
                preparedStatement.setString(5, telefono);
                preparedStatement.setInt(6, idHuesped);
                preparedStatement.execute();
                int updateCount = preparedStatement.getUpdateCount();
                return updateCount;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "Error al actualizar los datos.",
                    "Inténtelo más tarde.",
                    JOptionPane.ERROR_MESSAGE
            );
            throw new RuntimeException(e);
        }
    }

    /**
     * Eliminará tanto al húesped como a la reserva asociada.
     *
     * @param idHuesped - Clave de resferencia para identificac al húesped
     * actual.
     * @param idReserva - Clave de la reserva para tomar referencia del
     * registro.
     * @return
     */
    public int eliminar(Integer idHuesped, String idReserva) {
        try {
            String sql = "DELETE FROM huespedes WHERE id_huesped = ?";
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, idHuesped);
                preparedStatement.execute();
                eliminarReserva(idReserva);
                int updateCount = preparedStatement.getUpdateCount();
                return updateCount;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "Error al eliminar los datos.",
                    "Inténtelo más tarde.",
                    JOptionPane.ERROR_MESSAGE
            );
            throw new RuntimeException(e);
        }
    }

    private void eliminarReserva(String idReserva) {
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";
        try {
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.setString(1, idReserva);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null, "Error al eliminar los datos.",
                    "Inténtelo más tarde.",
                    JOptionPane.ERROR_MESSAGE
            );
            throw new RuntimeException(e);
        }
    }
}
