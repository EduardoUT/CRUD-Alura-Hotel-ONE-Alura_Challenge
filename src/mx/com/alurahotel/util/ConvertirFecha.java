/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.com.alurahotel.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Eduardo Reyes Hernández
 */
public class ConvertirFecha {

    public static LocalDate convertirDateALocalDate(Date fechaAConvertir) {
        return fechaAConvertir.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
