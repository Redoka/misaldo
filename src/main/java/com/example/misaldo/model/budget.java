/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.misaldo.model;

import com.example.misaldo.controller.PeriodController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alejandro
 */
public class budget {

    private int id;
    private User idUsuario;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private Salary idSalario;
    private float porcentajeAhorro;
    private float ahorrado;
    private float aGastar;
    private float restante;
    private float restanteAnterior;

    private List<Transaction> transacciones;

    public budget() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public User getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(User idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Salary getIdSalario() {
        return idSalario;
    }

    public void setIdSalario(Salary idSalario) {
        this.idSalario = idSalario;
    }

    public float getPorcentajeAhorro() {
        return porcentajeAhorro;
    }

    public void setPorcentajeAhorro(float porcentajeAhorro) {
        this.porcentajeAhorro = porcentajeAhorro;
    }

    public float getAhorrado() {
        return ahorrado;
    }

    public void setAhorrado(float ahorrado) {
        this.ahorrado = ahorrado;
    }

    public float getaGastar() {
        return aGastar;
    }

    public void setaGastar(float aGastar) {
        this.aGastar = aGastar;
    }

    public float getRestante() {
        return restante;
    }

    public void setRestante(float restante) {
        this.restante = restante;
    }

    public float getRestanteAnterior() {
        return restanteAnterior;
    }

    public void setRestanteAnterior(float restanteAnterior) {
        this.restanteAnterior = restanteAnterior;
    }

    public List<Transaction> getTransacciones() {
        if (transacciones == null) {
            transacciones = new ArrayList();
        }
        return transacciones;
    }

    public void setTransacciones(List<Transaction> transacciones) {
        this.transacciones = transacciones;
    }

}
