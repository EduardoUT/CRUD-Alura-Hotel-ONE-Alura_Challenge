/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.modelo;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Usuario {

    private int idUsuario;
    private String nombreUsuario;
    private String categoriaUsuario;
    private String password;

    public Usuario(int idUsuario, String nombreUsuario, String categoriaUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.categoriaUsuario = categoriaUsuario;
    }

    public Usuario(String nombreUsuario, String categoriaUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.categoriaUsuario = categoriaUsuario;
        this.password = password;
    }

    public Usuario(String nombreUsuario, String categoriaUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.categoriaUsuario = categoriaUsuario;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the categoriaUsuario
     */
    public String getCategoriaUsuario() {
        return categoriaUsuario;
    }

    /**
     * @param categoriaUsuario the categoriaUsuario to set
     */
    public void setCategoriaUsuario(String categoriaUsuario) {
        this.categoriaUsuario = categoriaUsuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "{ID Usuario: %d, Nombre Usuario: %s, Categoria Usuario: %s}",
                this.idUsuario,
                this.nombreUsuario,
                this.categoriaUsuario
        );
    }

}
