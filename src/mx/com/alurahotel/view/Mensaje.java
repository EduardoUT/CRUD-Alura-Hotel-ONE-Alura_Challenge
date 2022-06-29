/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.view;

import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class Mensaje {

    public static void confirmarSalida(java.awt.event.MouseEvent evt) {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(
                null,
                "En realidad desea realizar cerrar la aplicacion",
                "Mensaje de Confirmación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                "Aceptar"
        );
        if (eleccion == JOptionPane.YES_OPTION) {
            evt.consume();
            System.exit(0);
        }
    }

    public static void cancelarRegistro(java.awt.event.MouseEvent evt, RegistrarHuesped huesped) {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(
                null,
                "¿Desea cancelar el registro actual?\n"
                + "Será enviado al menu y los datos de la reserva y húesped se perderán.",
                "Confirmar cancelación de registro.",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                opciones,
                "Aceptar"
        );
        if (eleccion == JOptionPane.YES_OPTION) {
            evt.consume();
            huesped.limpiarCampos();
            Reservas reservas = new Reservas();
            reservas.setReserva(null);
            huesped.dispose();
            MenuUsuario menuUsuario = new MenuUsuario();
            menuUsuario.setVisible(true);

        }
    }

    public static void cancelarRegistro(java.awt.event.MouseEvent evt, RegistrarUsuario registrarUsuario) {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(
                null,
                "¿Desea cancelar el registro actual?\n"
                + "Será enviado al menu del usuario se perderán.",
                "Confirmar cancelación de registro de Usuario.",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                opciones,
                "Aceptar"
        );
        if (eleccion == JOptionPane.YES_OPTION) {
            evt.consume();
            registrarUsuario.reestablecerCampos();
            registrarUsuario.dispose();
            MenuUsuario menuUsuario = new MenuUsuario();
            menuUsuario.setVisible(true);
        }
    }
}
