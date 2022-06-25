/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.dao;

import mx.com.alurahotel.modelo.Huesped;
import java.sql.Connection;
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
                                String.format("Fue guardado con éxito el húesped: %s", huesped)
                        );
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar el registro.", "Error al guardar los datos.", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}
