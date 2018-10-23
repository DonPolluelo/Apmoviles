package com.example.uniup.uniup.models;


public class Ramo {
    private int id;
    private String name;

    public Ramo(){}

    public Ramo(String name){
        this.name=name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String toString() {
        return "Ramo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
