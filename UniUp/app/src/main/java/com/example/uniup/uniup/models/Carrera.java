package com.example.uniup.uniup.models;

public class Carrera {
    private Integer id;
    private String nombre;
    private Integer id_universidad;

    public Carrera(Integer id, String nombre, Integer universidad) {
        this.id = id;
        this.nombre = nombre;
        this.id_universidad = universidad;
    }

    public Carrera(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_universidad() {
        return id_universidad;
    }

    public void setId_universidad(Integer universidad) {
        this.id_universidad = universidad;
    }
}


