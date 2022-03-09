package br.com.netline.veiculospdf.relatorio;

import br.com.netline.veiculospdf.relatorio.model.LogModel;
import br.com.netline.veiculospdf.relatorio.repository.CasterRepository;
import br.com.netline.veiculospdf.relatorio.repository.LogRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        LogRepository repository = new LogRepository();
//
//        LogModel log = LogModel.builder()
//                .emailUser("hugo@gmail.com")
//                .data(String.valueOf(new SimpleDateFormat(
//                        "dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime()))
//                ).action("Aletrou status de: ".concat("ativo para: ").concat("inativo"))
//                .build();
//
//        repository.registerLog(log);


    }
}
