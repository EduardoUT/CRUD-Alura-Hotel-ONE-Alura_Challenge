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
        String sql = "SELECT id_huesped, nombre, apellido, fecha_nacimiento, nacionalidad, telefono "
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
                            resultSet.getString("FECHA_NACIMIENTO"),
                            resultSet.getString("NACIONALIDAD"),
                            resultSet.getString("TELEFONO")
                    );
                    listaHuespedes.add(fila);
                }
                return listaHuespedes;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
