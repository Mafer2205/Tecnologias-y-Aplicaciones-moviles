package com.example.proyectotyam;


import java.io.Serializable;

public class Archivos implements Serializable {

    private int id_archivo, r_area;
    private String nomb_arch, color;

    public Archivos(String nomb_arch, String color, int r_area) {
        this.nomb_arch = nomb_arch;
        this.color = color;
        this.r_area = r_area;
    }

    public Archivos (String nomb_arch, int r_area) {
        this.nomb_arch = nomb_arch;
        this.r_area = r_area;
    }

    public int getId_archivo() {return id_archivo;}
    public void setId_archivo(int id_archivo) {this.id_archivo = id_archivo;}

    public int getR_area() {return r_area;}
    public void setR_area(int r_area) {this.r_area = r_area;}

    public String getNomb_arch() {return nomb_arch;}
    public void setNomb_arch(String nomb_arch) {this.nomb_arch = nomb_arch;}

    public String getColor() {return color;}
    public void setColor(String color) {this.color = color;}
}
