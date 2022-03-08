package br.com.netline.veiculospdf.relatorio.model;

import com.google.gson.internal.LinkedTreeMap;

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

    public FlowModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(String horaChegada) {
        this.horaChegada = horaChegada;
    }

    public int getKmSaida() {
        return kmSaida;
    }

    public void setKmSaida(int kmSaida) {
        this.kmSaida = kmSaida;
    }

    public int getKmChegada() {
        return kmChegada;
    }

    public void setKmChegada(int kmChegada) {
        this.kmChegada = kmChegada;
    }

    public String getStatusVeiculo() {
        return statusVeiculo;
    }

    public void setStatusVeiculo(String statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

    public int getTotalKm() {
        return totalKm;
    }

    public void setTotalKm(int totalKm) {
        this.totalKm = totalKm;
    }

    public String getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(String totalHoras) {
        this.totalHoras = totalHoras;
    }

    public String getSetorVeiculo() {
        return setorVeiculo;
    }

    public void setSetorVeiculo(String setorVeiculo) {
        this.setorVeiculo = setorVeiculo;
    }

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
        //System.out.println(flx.getNomeTecnico());
        return flx;
    }

    private static String mapValueToString(Object o) {
        return String.valueOf(o);
    }

    @Override
    public String toString() {
        return "FluxoModel{" +
                "id='" + id + '\'' +
                ", nomeTecnico='" + nomeTecnico + '\'' +
                ", dataSaida='" + dataSaida + '\'' +
                ", dataChegada='" + dataChegada + '\'' +
                ", horaSaida='" + horaSaida + '\'' +
                ", horaChegada='" + horaChegada + '\'' +
                ", kmSaida=" + kmSaida +
                ", kmChegada=" + kmChegada +
                ", statusVeiculo='" + statusVeiculo + '\'' +
                ", totalKm=" + totalKm +
                ", totalHoras='" + totalHoras + '\'' +
                ", setorVeiculo='" + setorVeiculo + '\'' +
                '}';
    }
}
