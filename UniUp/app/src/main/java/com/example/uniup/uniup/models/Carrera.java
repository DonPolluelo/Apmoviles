package com.example.uniup.uniup.models;

import java.util.Comparator;

public class Carrera implements Comparable<Carrera> {
    private Integer id;
    private String nombre;
    private Integer id_universidad;
    private Integer duracion;


    public Carrera(Integer id, String nombre, Integer universidad, Integer duracion) {
        this.id = id;
        this.nombre = nombre;
        this.id_universidad = universidad;
        this.duracion = duracion;
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


    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }



    public static Comparator<Carrera> CarreraNameComparator
            = new Comparator<Carrera>() {

        public int compare(Carrera c1, Carrera c2) {

            String CarreraName1 = c1.getNombre().toUpperCase();
            String CarreraName2 = c2.getNombre().toUpperCase();

            //ascending order
            return CarreraName1.compareTo(CarreraName2);

            //descending order
            //return fruitName2.compareTo(fruitName1);
        }

    };

    @Override
    public int compareTo(Carrera o) {
        return 0;
    }
}


