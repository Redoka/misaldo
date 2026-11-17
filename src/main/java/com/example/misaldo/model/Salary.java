/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.misaldo.model;

/**
 *
 * @author Alejandro
 */
public class Salary {

    private int id;
    private String descripcion;
    private float cantidad;
    private User idUsuario;
    private String fechaCobrado;

    public Salary() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public User getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(User idUsurio) {
        this.idUsuario = idUsuario;
    }

    public String getFechaCobrado() {
        return fechaCobrado;
    }

    public void setFechaCobrado(String fechaCobrado) {
        this.fechaCobrado = fechaCobrado;
    }

}
