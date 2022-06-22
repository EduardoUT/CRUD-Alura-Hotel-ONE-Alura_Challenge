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

    public static void ConfirmarSalida(java.awt.event.MouseEvent evt) {
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
}
