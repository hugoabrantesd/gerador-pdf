package br.com.netline.veiculospdf.relatorio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@NamedQueries(
        @NamedQuery(
                name = "getAll",
                query = "SELECT c FROM CasterModel c WHERE placa_veiculo = :placa")
)
public class CasterModel implements Serializable {

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(unique = true)
//    private Integer id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private UUID uuid;

    @Column(length = 7, name = "placa_veiculo")
    private String vehiclePlate;

    @Column(length = 30, name = "email_usuario")
    private String emailUser;

    @Column(length = 6, name = "km_ultimo_rodizio")
    private Integer kmLastCaster;

    @Column(length = 6, name = "km_proximo_rodizio")
    private Integer kmNextCaster;

    @Column(length = 10, name = "data_ultimo_rodizio")
    private String dateLastCast;

    @Column(length = 40, name = "tipo_rodizio")
    private String type;

    @Column(length = 254, name = "observacao")
    private String observation;

    @Column(length = 10, name = "diant_trocados")
    private Boolean exchangedFront;

    @Column(length = 10, name = "tras_trocados")
    private Boolean exchangedRear;

}
