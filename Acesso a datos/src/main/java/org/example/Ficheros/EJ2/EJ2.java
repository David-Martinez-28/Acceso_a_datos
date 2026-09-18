package org.example.Ficheros.EJ2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EJ2 {
    public static void main(String[] args) {


        String base = "src/main/java/org/example/Ficheros/EJ2";

        Path rutaRaiz = Path.of(base, "DAM");

        String[] subcarpetas = {"documentos", "imagenes", "datos", "copias"};

        try {
            if (!Files.exists(rutaRaiz)) {
                Files.createDirectories(rutaRaiz);
                System.out.println("Carpeta principal 'DAM' creada.");
            }

            for (String sub : subcarpetas) {

                Path rutaCompleta = rutaRaiz.resolve(sub);

                if (!Files.exists(rutaCompleta)) {
                    Files.createDirectories(rutaCompleta);
                    System.out.println("Subcarpeta creada: " + sub);
                }
            }

            System.out.println("\nEstructura creada correctamente de forma completa.");

        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear la estructura: " + e.getMessage());
        }
    }
}