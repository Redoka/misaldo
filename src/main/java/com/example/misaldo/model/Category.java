/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.misaldo.model;

/**
 *
 * @author Alejandro
 */
public class Category {
     private int id;
     private int idUsuario;
     private String nombre;
     private CategoryType tipo;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoryType getTipo() {
        return tipo;
    }

    public void setTipo(CategoryType tipo) {
        this.tipo = tipo;
    }
     
     
}
