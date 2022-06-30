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
import javax.swing.JOptionPane;
import mx.com.alurahotel.modelo.Usuario;

/**
 * Definiendo capa Data Access Object la cual permite acceder a los atributos de
 * la clase Usuario, y interactuar con las operaciones de la Base de Datos.
 *
 * @author Eduardo Reyes Hernández
 */
public class UsuarioDAO {

    private final Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    /**
     * Ejecuta una sentencia SELECT en MySQL de todos los usuarios.
     *
     * @return - Lista de usuarios en la Base de Datos de MySQL.
     */
    public List<Usuario> listar() {
        List<Usuario> listarUsuarios = new ArrayList<>();
        String sql = "SELECT id_usuario, nombre_usuario, categoria_usuario "
                + "FROM usuarios";
        try {
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Usuario fila = new Usuario(
                            resultSet.getInt("ID_USUARIO"),
                            resultSet.getString("NOMBRE_USUARIO"),
                            resultSet.getString("CATEGORIA_USUARIO")
                    );
                    listarUsuarios.add(fila);
                }
                return listarUsuarios;
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
     * Ejecuta una sentencia MySQL con parámetro LIKE, recibiendo el nombre del
     * usuario.
     *
     * @param categoriaUsuario - Categoría del usuario.
     * @return - Lista de usuarios según el nombre del usuario.
     */
    public List<Usuario> listar(String categoriaUsuario) {
        List<Usuario> listaUsuarios = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "id_usuario, nombre_usuario, categoria_usuario\n"
                    + "FROM usuarios\n"
                    + "WHERE categoria_usuario LIKE ?";
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.setString(1, categoriaUsuario.concat("%"));
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Usuario fila = new Usuario(
                            resultSet.getInt("ID_USUARIO"),
                            resultSet.getString("NOMBRE_USUARIO"),
                            resultSet.getString("CATEGORIA_USUARIO")
                    );
                    listaUsuarios.add(fila);
                }
                return listaUsuarios;
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
     * Método para consultar la categoría del usuario acorde al usuario y
     * contraseña.
     *
     * @param nombreUsuario - Nombre del usuario.
     * @param password - Contraseña del usuario.
     * @return - Retorna una lista con la coincidencia de parámetros con la Base
     * de Datos.
     */
    public List<Usuario> listar(String nombreUsuario, String password) {
        List<Usuario> listaUsuarios = new ArrayList<>();
        try {
            String sql = "SELECT nombre_usuario, categoria_usuario "
                    + "FROM usuarios "
                    + "WHERE nombre_usuario = ? AND password = ?";
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.setString(1, nombreUsuario);
                preparedStatement.setString(2, password);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    Usuario fila = new Usuario(
                            resultSet.getString("NOMBRE_USUARIO"),
                            resultSet.getString("CATEGORIA_USUARIO")
                    );
                    listaUsuarios.add(fila);
                }
                return listaUsuarios;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Inténtelo más tarde.",
                    "Error al obtener los datos.",
                    JOptionPane.ERROR_MESSAGE
            );
            throw new RuntimeException(e);
        }
    }

    /**
     * Permite almacenar el modelo de datos de Usuario, en la tabla usuarios de
     * MySQL, usar sólo si no hay dependencia de exceptions.
     *
     * @param usuario - Objeto de tipo Usuario.
     */
    public void guardar(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuarios (nombre_usuario, categoria_usuario, password)"
                    + "VALUES (?, ?, ?)";
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
                preparedStatement.setString(1, usuario.getNombreUsuario());
                preparedStatement.setString(2, usuario.getCategoriaUsuario());
                preparedStatement.setString(3, usuario.getPassword());
                preparedStatement.execute();
                try ( ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                    while (resultSet.next()) {
                        System.out.println(
                                String.format("Fue ingresado el usuario: %s", usuario)
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
     * Realiza la actualización de los valores del usuario en la base de datos.
     *
     * @param idUsuario - Clave de referencia del Usuario a actualizar.
     * @param nombreUsuario - Nombre del usuario.
     * @param categoriaUsuario - Categoría del usuario.
     * @param password - Contraseña del usuario.
     * @return - Número de registros actualizados.
     */
    public int actualizar(Integer idUsuario, String nombreUsuario, String categoriaUsuario, String password) {
        try {
            String sql = "UPDATE usuarios "
                    + "SET nombre_usuario = ?, categoria_usuario = ?, password = ? "
                    + "WHERE id_usuario = ?";
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.setString(1, nombreUsuario);
                preparedStatement.setString(2, categoriaUsuario);
                preparedStatement.setString(3, password);
                preparedStatement.setInt(4, idUsuario);
                preparedStatement.execute();
                int updateCount = preparedStatement.getUpdateCount();
                return updateCount;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el registro.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Ejecuta una sentencia DELETE en el registro del usuario.
     *
     * @param idUsuario - Clave del usuario tomada como referencia.
     * @return - Número de registros eliminados.
     */
    public int eliminar(Integer idUsuario) {
        try {
            String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
            try ( PreparedStatement preparedStatement = con.prepareStatement(sql);) {
                preparedStatement.setInt(1, idUsuario);
                preparedStatement.execute();
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
}
