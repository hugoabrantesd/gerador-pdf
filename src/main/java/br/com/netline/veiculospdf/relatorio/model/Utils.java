package br.com.netline.veiculospdf.relatorio.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static String dateToString() {
        return LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss")
        );
    }

}
