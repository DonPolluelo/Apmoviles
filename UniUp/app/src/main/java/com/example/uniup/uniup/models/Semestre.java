package com.example.uniup.uniup.models;

public class Semestre {
    private int id;
    private String nombre;

    public Semestre(String nombre) {
        this.nombre = nombre;
    }

    public Semestre() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
