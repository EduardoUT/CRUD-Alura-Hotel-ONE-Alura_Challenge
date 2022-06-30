/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.controller;

import java.util.List;
import mx.com.alurahotel.dao.UsuarioDAO;
import mx.com.alurahotel.factory.ConnectionFactory;
import mx.com.alurahotel.modelo.Usuario;

/**
 * Clase que posee todas las acciones de operación entre la capa View y la capa
 * DAO.
 *
 * @author Eduardo Reyes Hernández
 */
public class UsuarioController {

    private final UsuarioDAO usuarioDAO;

    /**
     * Creando conexion para operaciones con MySQL con la tabla de usuarios.
     */
    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO(new ConnectionFactory().realizarConexion());
    }

    /**
     * Obteniendo el listado del DAO de usuarios.
     *
     * @return - List de usuarios existentes.
     */
    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }

    /**
     * Permite consultar los usuarios acorde al nombre de usuario.
     *
     * @param categoriaUsuario - Categoría del usuario.
     * @return - Lista de usuarios según el nombrede usuario.
     */
    public List<Usuario> listar(String categoriaUsuario) {
        return usuarioDAO.listar(categoriaUsuario);
    }

    /**
     * Obtiene los valores nombre y contraseña en el modelo view, para ser
     * validados con la Base de Datos.
     *
     * @param nombreUsuario - Nombre del usuario.
     * @param password - Contraseña del usuario.
     * @return - Lista con un registro de categoría_usuario.
     */
    public List<Usuario> listar(String nombreUsuario, String password) {
        return usuarioDAO.listar(nombreUsuario, password);
    }

    /**
     * Almacenando los atributos del Usuario obtenidos en el modelo view.
     *
     * @param usuario - Objeto de tipo Usuario.
     */
    public void guardar(Usuario usuario) {
        usuarioDAO.guardar(usuario);
    }

    /**
     * Recopila los datos del usuario obtenidos en el modelo View.
     *
     * @param idUsuario - Clave de referencia del Usuario a actualizar.
     * @param nombreUsuario - Nombre del usuario.
     * @param categoriaUsuario - Categoría del usuario.
     * @param password - Contraseña del usuario.
     * @return - Número de registros actualizados.
     */
    public int actualizar(Integer idUsuario, String nombreUsuario,
            String categoriaUsuario, String password) {
        return usuarioDAO.actualizar(idUsuario, nombreUsuario, categoriaUsuario, password);
    }

    /**
     * Obtiene el id del usuario como referencia para la eliminación de
     * registro.
     *
     * @param IdUsuario - Clave del usuario.
     * @return - Número de registros eliminados.
     */
    public int eliminar(Integer IdUsuario) {
        return usuarioDAO.eliminar(IdUsuario);
    }
}
