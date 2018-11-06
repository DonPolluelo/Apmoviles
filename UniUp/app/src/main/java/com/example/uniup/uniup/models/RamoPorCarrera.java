package com.example.uniup.uniup.models;

public class RamoPorCarrera {

    private int id_carrera;
    private int id_ramo;
    private int semestre;

    public RamoPorCarrera(){}

    public int getId_carrera(){
        return id_carrera;
    }

    public void setId_carrera(int id_carrera){ this.id_carrera = id_carrera; }

    public int getId_ramo(){
        return id_ramo;
    }

    public void setId_ramo(int id_ramo){
        this.id_ramo = id_ramo;
    }

    public int getSemestre(){
        return semestre;
    }

    public void setSemestre(int semestre){
        this.semestre = semestre;
    }


}
