package org.example.Ficheros.EJ4;

import org.example.Ficheros.Alumno;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EJ4 {
    public static void main(String[] args) {
        String raiz = "/home/ciclosm/IdeaProjects/Acesso a datos/src/main/java/org/example/Ficheros/EJ4";
        Path fichero = Path.of(raiz, "alumnos.txt");
        List<Alumno> alumnos = new ArrayList<>();
        Scanner src=new Scanner(System.in);

        int id=0;
        String nombre="";
        String apellidos="";
        int edad=0;
        double nota=0;

        System.out.println("Escribe la id:");
        id=src.nextInt();
        src.nextLine();
        System.out.println("Escriba su nombre:");
        nombre=src.nextLine();
        System.out.println("Escribe su apellido:");
        apellidos=src.nextLine();
        System.out.println("Escribe su edad:");
        edad=src.nextInt();
        System.out.println("Escribe su nota:");
        nota=src.nextDouble();


        Alumno ana = new Alumno(id, nombre, apellidos, edad, nota);
        alumnos.add(ana);


        try {
            if (!Files.exists(fichero)) {
                Files.createFile(fichero);
            }

            try (FileWriter fileWriter = new FileWriter(fichero.toFile())) {
                alumnos.forEach(alumno -> {
                    try {
                        fileWriter.write(alumno.toString() + "\n");
                    } catch (IOException e) {
                        System.out.println("Error al escribir el alumno: " + alumno.getNombre());
                    }
                });
                System.out.println("Fichero escrito correctamente.");
            }
            try {
                List<String> lineas = Files.readAllLines(fichero);

                System.out.println("\n--- Procesando y reconstruyendo alumnos ---");
                lineas.forEach(linea -> {

                    String[] formato = linea.split(";");

                    int id_formato = Integer.parseInt(formato[0]);
                    String nombre_formato = formato[1];
                    int edad_formato = Integer.parseInt(formato[2]);
                    double nota_formato = Double.parseDouble(formato[3]);
                    System.out.println("id: "+id_formato);
                    System.out.println("Nombre: "+nombre_formato);
                    System.out.println("Edad: "+edad_formato);
                    System.out.println("Nota: "+nota_formato);
                    System.out.println("--------------");


                });

            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error al transformar los datos numéricos: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
