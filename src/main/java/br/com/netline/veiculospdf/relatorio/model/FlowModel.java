package br.com.netline.veiculospdf.relatorio.model;

import com.google.gson.internal.LinkedTreeMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlowModel {

    private String id;
    private String nomeTecnico;
    private String dataSaida;
    private String dataChegada;
    private String horaSaida;
    private String horaChegada;
    private int kmSaida;
    private int kmChegada;
    private String statusVeiculo;
    private int totalKm;
    private String totalHoras;
    private String setorVeiculo;

    public static FlowModel fromMap(LinkedTreeMap<String, Object> obj) {
        FlowModel flx = new FlowModel();
        flx.setId(mapValueToString(obj.get("id")));
        flx.setNomeTecnico(mapValueToString(obj.get("nomeTecnico")));
        flx.setDataSaida(mapValueToString(obj.get("dataSaida")));
        flx.setDataChegada(mapValueToString(obj.get("dataChegada")));
        flx.setHoraSaida(mapValueToString(obj.get("horaSaida")));
        flx.setHoraChegada(mapValueToString(obj.get("horaChegada")));
        flx.setKmSaida((int) Double.parseDouble(mapValueToString(obj.get("kmSaida"))));
        flx.setKmChegada((int) Double.parseDouble(mapValueToString(obj.get("kmChegada"))));
        flx.setStatusVeiculo(mapValueToString(obj.get("statusVeiculo")));
        flx.setTotalKm((int) Double.parseDouble(mapValueToString(obj.get("totalKm"))));
        flx.setTotalHoras(mapValueToString(obj.get("totalHoras")));
        flx.setSetorVeiculo(mapValueToString(obj.get("setorVeiculo")));
        return flx;
    }

    private static String mapValueToString(Object o) {
        return String.valueOf(o);
    }

}
