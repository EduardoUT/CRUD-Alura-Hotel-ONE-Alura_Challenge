/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mx.com.alurahotel;

import mx.com.alurahotel.ventanas.MenuPrincipal;

/**
 * Clase principal para visualizar el menú principal al ejecutar JAR.
 *
 * @author Eduardo Reyes Hernández
 */
public class AluraHotelMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
    }
}
