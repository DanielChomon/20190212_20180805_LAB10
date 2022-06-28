package com.example.lab10_20190212_20180805.Beans;

import java.math.BigDecimal;

public class Trip {
    private int id_viaje;
    private String fecha_reserva;
    private String fecha_viaje;
    private String ciudad_origen;
    private String ciudad_destino;
    private int boleto;
    private BigDecimal costo;
    private Student codigo_estudiante;
    private Insurance seguro;


    public int getId_viaje() {
        return id_viaje;
    }

    public void setId_viaje(int identi) {
        this.id_viaje = identi;
    }

    public String getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(String fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public String getFecha_viaje() {
        return fecha_viaje;
    }

    public void setFecha_viaje(String fecha_viaje) {
        this.fecha_viaje = fecha_viaje;
    }

    public String getCiudad_origen() {
        return ciudad_origen;
    }

    public void setCiudad_origen(String ciudad_origen) {
        this.ciudad_origen = ciudad_origen;
    }

    public String getCiudad_destino() {
        return ciudad_destino;
    }

    public void setCiudad_destino(String ciudad_destino) {
        this.ciudad_destino = ciudad_destino;
    }

    public int getBoleto() {
        return boleto;
    }

    public void setBoleto(int boleto) {
        this.boleto = boleto;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Student getCodigo_estudiante() {
        return codigo_estudiante;
    }

    public void setCodigo_estudiante(Student codigo) {
        this.codigo_estudiante = codigo;
    }

    public Insurance getSeguro() {
        return seguro;
    }

    public void setSeguro(Insurance seguro) {
        this.seguro = seguro;
    }
}
