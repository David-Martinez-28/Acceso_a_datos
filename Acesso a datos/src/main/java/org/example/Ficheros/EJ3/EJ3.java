package org.example.Ficheros.EJ3;

import org.example.Ficheros.Alumno;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EJ3 {
    public static void main(String[] args) {
        String raiz = "/home/ciclosm/IdeaProjects/Acesso a datos/src/main/java/org/example/Ficheros/EJ3";
        Path fichero = Path.of(raiz, "alumnos.txt");
        List<Alumno> alumnos = new ArrayList<>();
        Alumno ana = new Alumno(1, "Ana", "Garcia", 20, 8.5);
        Alumno luis = new Alumno(2, "Luis", "Perez", 22, 7.2);
        Alumno marta = new Alumno(3, "Marta", "Lopez", 19, 9.1);
        Alumno carlos = new Alumno(4, "Carlos", "Ruiz", 22, 6.8);
        alumnos.add(ana);
        alumnos.add(luis);
        alumnos.add(marta);
        alumnos.add(carlos);

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

                    int id = Integer.parseInt(formato[0]);
                    String nombre = formato[1];
                    int edad = Integer.parseInt(formato[2]);
                    double nota = Double.parseDouble(formato[3]);
                    System.out.println("id: "+id);
                    System.out.println("Nombre: "+nombre);
                    System.out.println("Edad: "+edad);
                    System.out.println("Nota: "+nota);
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
