package org.example.XML;

public class Alumno {
    private int id;
    private String nombre;
    private String apellidos;
    private int edad;
    private double  nota;

    public Alumno(int id, String nombre,  int edad, double nota) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return id+" "+nombre+" "+edad+" "+nota;
    }
}
