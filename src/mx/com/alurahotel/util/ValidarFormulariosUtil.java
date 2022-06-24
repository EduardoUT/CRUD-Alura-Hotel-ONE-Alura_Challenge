/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.util;

import com.toedter.calendar.JDateChooser;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * Contiene métodos estáticos para la validacion de los campos y formularios del
 * paquete view.
 *
 * @author Eduardo Reyes Hernández
 */
public class ValidarFormulariosUtil {

    /**
     * Evalúa que todos los campos del fomulario de registro de húespedes
     * cumplan con las validaciones solicitadas, en caso de no hacerlo, mostrará
     * un mensaje de error al usuario solicitando la información faltante.
     *
     * @param nombre - Campo de tipo String obtenido del JTextField.
     * @param apellido - Campo de tipo String obtenido del JTextField.
     * @param fechaNac - Campo de tipo JDateChooser, se transforma a Date para
     * obtener su valor.
     * @param tel - Campo de tipo String, recibe un número telefónico de un
     * JTextField.
     * @return true - Si el usuario rellenó adecuadamente todos los campos.
     * @see mx.com.alurahotel.view.RegistrarHuesped - Implementación en evento
     * MouseClicked del boton de guardado.
     */
    public static boolean esFormularioHuespedValido(String nombre, String apellido, JDateChooser fechaNac, String tel) {
        String regexNombre = "^(?=.{3,25}$)([A-ZÁÉÍÓÚ][a-záéíóúñ]+(?:[\\s]{1}[A-ZÁÉÍÓÚ][a-záéíóúñ]+)*)$";
        String regexTel = "^([\\d]{2}[\\-]){4}[\\d]{2}$";
        Pattern patternNombre = Pattern.compile(regexNombre);
        Pattern patternTelefono = Pattern.compile(regexTel);
        Matcher matchNombre = patternNombre.matcher(nombre);
        Matcher matchApellido = patternNombre.matcher(apellido);
        Matcher matchTelefono = patternTelefono.matcher(tel);
        if (!matchNombre.find()) {
            desplegarMensajeError(
                    "Nombre inválido",
                    "1. El nombre debe contener la primer letra mayúscula:\n"
                    + "John\n"
                    + "2. De igual forma si es un nombre compuesto:\n"
                    + "John Doe\n"
                    + "3. Si es un sólo nombre verifique que no hayan espacios en blanco antes o después."
            );
            return false;
        } else if (!matchApellido.find()) {
            desplegarMensajeError(
                    "Apellido inválido.",
                    "1. El apellido debe contener la primer letra mayúscula:\n"
                    + "Reyes\n"
                    + "2. De igual forma si es un apellido compuesto:\n"
                    + "Reyes Hernández\n"
                    + "3. Si es un sólo apellido verifique que no hayan espacios en blanco antes o después."
            );
            return false;
        } else if (fechaNac.getDate() == null) {
            desplegarMensajeError("Fecha inválida.", "El campo fecha está vacío.");
            return false;
        } else if (!matchTelefono.find()) {
            desplegarMensajeError(
                    "Teléfono inválido.",
                    "El formato admitido debe contener 10 dígitos,\n"
                    + "incluyendo la clave teléfonica del estado, separados por guiones(-):\n"
                    + "55-43-22-22-43"
            );
            return false;
        } else {
            return true;
        }
    }

    public static boolean esFormularioReservaValido(JDateChooser fechaEntrada, JDateChooser fechaSalida, String valor, JComboBox<String> formaPago) {
        if ((fechaEntrada.getDate() == null) && (fechaSalida.getDate() == null)) {
            desplegarMensajeError("Fechas inválidas.",
                    "Por favor, seleccione las fechas de entrada y salida.\n"
                    + "Puede escribir la fecha manualmente si cumple con el siguiente formato:\n"
                    + "dd/mm/yyyy"
            );
            return false;
        } else if (valor.equals("0.0")) {
            desplegarMensajeError(
                    "Valor de reserva en cero.",
                    "Por favor, seleccione las fechas de entrada y salida\n"
                    + "para efectuar el total monetario de la reserva.");
            return false;
        } else if (formaPago.getSelectedItem().equals("Elija forma de pago")) {
            desplegarMensajeError("Selección de pago inválida.", "Por favor, seleccione una forma de pago.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Recibe sólo números (char) si es utilizado dentro del evento KeyTyped en
     * un jTextField.
     *
     * Si la tecla no corresponde a un rango númerico entre 0 y 9 devuelve un
     * char en blanco.
     *
     * @param evt Recibe el evento y lo convierte a su valor en char literal.
     */
    public static void numeros(KeyEvent evt) {
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car != '.') && (car != '-')) {
            evt.consume();
        }
    }

    /**
     * Mensaje de error para uso de los métodos estáticos de validación de
     * formularios.
     *
     * @param titulo - Titulo del JOptionPane.
     * @param mensaje - Mensaje del JOptionPane
     */
    public static void desplegarMensajeError(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
}
