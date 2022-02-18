package br.com.netline.veiculospdf.relatorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/gerar-pdf").allowedOrigins("http://netlinecarros.web.app")
//                        .allowedOrigins("http://netlinecarros.web.app:8081")
//                        .allowedOrigins("https://netlinecarros.web.app")
//                        .allowedOrigins("https://netlinecarros.web.app:8081")
//                        .allowedOrigins("http://netlinecarros.web.app:8081")
//                        .allowedOrigins("https://netlinecarros.web.app:8081")
//                        .allowedOrigins("http://localhost:8081");
//            }
//
//        };
//    }

}
