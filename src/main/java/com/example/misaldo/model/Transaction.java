/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.misaldo.model;

import java.util.Date;

/**
 *
 * @author Alejandro
 */
public class Transaction {
    
    private int id;
    private budget idPeriodo;
    private Category idCategoria;
    private String descripcion;
    private float cantidad;
    private int idTipo;
    private Date fecha;

    public Transaction() {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public budget getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(budget idPedido) {
        this.idPeriodo = idPedido;
    }

    public Category getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Category idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

}
