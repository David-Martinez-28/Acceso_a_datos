package org.example.Ficheros.EJ5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EJ5 {
    public static void main(String[] args) {
        String raiz = "/home/ciclosm/IdeaProjects/Acesso a datos/src/main/java/org/example/Ficheros/EJ5";
        Path fichero = Path.of(raiz, "alumnos.txt");

        try {
            List<String> lineas = Files.readAllLines(fichero);

            System.out.println("\n--- Procesando y reconstruyendo alumnos ---");


            int aprobados = 0;
            int suspensos = 0;
            double totalNotas = 0;
            int numeroDeAlumnos = 0;
            double notaMaxima = Integer.MIN_VALUE;
            double notaMinima = Integer.MAX_VALUE;

            // 2. Usar un bucle for-each tradicional
            for (String linea : lineas) {
                String[] formato = linea.split(";");
                double nota = Double.parseDouble(formato[3]);

                numeroDeAlumnos++;
                totalNotas += nota;

                if (nota >= 5) {
                    aprobados++;
                } else {
                    suspensos++;
                }


                if (nota > notaMaxima) {
                    notaMaxima = nota;
                }
                if (nota < notaMinima) {
                    notaMinima = nota;
                }
            }


            System.out.println("Número de alumnos: " + numeroDeAlumnos);
            System.out.println("Aprobados: " + aprobados);
            System.out.println("Suspensos: " + suspensos);
            System.out.println("Nota máxima: " + notaMaxima);
            System.out.println("Nota mínima: " + notaMinima);
            if (numeroDeAlumnos > 0) {
                System.out.println("Nota media: " + (totalNotas / numeroDeAlumnos));
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {System.out.println("Error al transformar los datos numéricos: " + e.getMessage());
        }
    }
}
