package br.com.netline.veiculospdf.relatorio.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class LogModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private UUID uuid;

    @Column(name = "email_usuario")
    private String userEmail;

    @Column(length = 10, name = "data")
    private String date;

    @Column(name = "acao_realizada")
    private String actionPerformed;

}
