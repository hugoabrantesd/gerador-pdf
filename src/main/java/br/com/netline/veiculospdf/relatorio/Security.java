//package br.com.netline.veiculospdf.relatorio;//package br.com.netline.veiculospdf.relatorio;//package br.com.netline.veiculospdf.relatorio;
//////
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.util.List;
//
//@EnableWebSecurity
//public class Security extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedHeaders(List.of("Access-Control-Allow-Headers", "custId, appId," +
//                " Origin, Content-Type, Cookie, X-CSRF-TOKEN, Accept, Authorization," +
//                " X-XSRF-TOKEN, Access-Control-Allow-Origin"
//        ));
//        corsConfiguration.setAllowedMethods(List.of("POST", "GET", "OPTIONS", "PUT", "DELETE", "HEAD"));
//        corsConfiguration.setAllowedOrigins(List.of("http://192.168.2.42:8081", "http://177.12.118.43:8081",
//                "http://localhost:8081", "http://localhost:53110",
//                "https://netlinecarros.web.app:8081", "https://netlinecarros.web.app",
//                "http://netlinecarros.web.app:8081", "http://netlinecarros.web.app",
//                "http://8.8.8.8", "http://199.36.158.100", "http://199.36.158.100:8081",
//                "http://8.8.8.8:8081", "https://199.36.158.100", "https://199.36.158.100:8081",
//                "http://www.google.com:8081", "https://www.google.com", "https://google.com:8081",
//                "http://google.com", "*"
//        ));
//       // corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setExposedHeaders(List.of("Authorization, authenticated"));
//
//
//
//        corsConfiguration.addAllowedHeader("Access-Control-Allow-Origin: *");
//        corsConfiguration.addAllowedHeader("custId");
//        corsConfiguration.addAllowedHeader("appId");
//        corsConfiguration.addAllowedHeader("Origin");
//        corsConfiguration.addAllowedHeader("Content-Type");
//        corsConfiguration.addAllowedHeader("Cookie");
//        corsConfiguration.addAllowedHeader("X-CSRF-TOKEN");
//        corsConfiguration.addAllowedHeader("Accept");
//        corsConfiguration.addAllowedHeader("Authorization");
//        corsConfiguration.addAllowedHeader("Access-Control-Allow-Origin");
//        corsConfiguration.addAllowedHeader("Access-Control-Allow-Headers");
//        corsConfiguration.addAllowedHeader("Access-Control-Expose-Headers");
//        corsConfiguration.addAllowedHeader("Authorization");
//        corsConfiguration.addAllowedHeader("authenticated");
//        corsConfiguration.addAllowedHeader("Access-Control-Expose-Headers");
//        corsConfiguration.addAllowedHeader("Access-Control-Expose-Headers");
//        corsConfiguration.addAllowedHeader("Access-Control-Expose-Headers");
//
//        // You can customize the following part based on your project, it's only a sample
//        http.authorizeRequests().antMatchers("/**").permitAll().anyRequest()
//                .authenticated().and().csrf().disable().cors().configurationSource(request -> corsConfiguration);
//
//    }
//}
////
////import org.springframework.context.annotation.Configuration;
////import org.springframework.web.servlet.config.annotation.CorsRegistry;
////import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
////
////@Configuration
////public class Security implements WebMvcConfigurer {
////
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/gerar-pdf")
////                .allowedOrigins("https://netlinecarros.web.app")
////                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
////
////        registry.addMapping("/gerar-pdf")
////                .allowedOrigins("http://177.12.118.43:8081")
////                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
////    }
////}