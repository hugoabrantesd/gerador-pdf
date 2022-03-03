package br.com.netline.veiculospdf.relatorio.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class LogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private UUID uuid;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String emailUser;
    private String data;
    private String action;

}
