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
                query = "SELECT c FROM CasterModel c WHERE vehicleplate = :vehicleplate")
)
public class CasterModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Integer id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private UUID uuid;

    @Column(length = 7)
    private String vehiclePlate;
    @Column(length = 30)
    private String emailUser;
    @Column(length = 6)
    private Integer kmLastCaster;
    @Column(length = 6)
    private Integer kmNextCaster;
    @Column(length = 10)
    private String dateLastCast;

}
