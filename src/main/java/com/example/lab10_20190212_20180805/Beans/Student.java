package com.example.lab10_20190212_20180805.Beans;

import java.math.BigDecimal;

public class Student {
    private String nombre;
    private String apellido;
    private int edad;
    private int codigo;
    private String correo;
    private String especialidad;
    private String contra;
    private String status;
    private BigDecimal dinero_gastado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getDinero_gastado() {
        return dinero_gastado;
    }

    public void setDinero_gastado(BigDecimal dinero_gastado) {
        this.dinero_gastado = dinero_gastado;
    }
}
