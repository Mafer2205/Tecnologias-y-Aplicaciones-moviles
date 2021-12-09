package com.example.proyectotyam;

public class Areas {
    private int id_area;
    private String nomb_area;

    public Areas(int id_area, String nomb_area){
        this.id_area = id_area;
        this.nomb_area =nomb_area;
    }
    public Areas(String nomb_area){
        this.nomb_area = nomb_area;
    }

    public int getId_area() {
        return id_area;
    }

    public String getNomb_area() {
        return nomb_area;
    }

    public void setId_area(int id_area) {
        this.id_area = id_area;
    }

    public void setNomb_area(String nomb_area) {
        this.nomb_area = nomb_area;
    }
}
