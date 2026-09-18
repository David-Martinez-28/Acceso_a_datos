package org.example.Ficheros.EJ1;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class EJ1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Escriba la ruta que quieres buscar:");
        String ruta = scanner.nextLine();

        Path path = Path.of(ruta);


        if (Files.exists(path)) {

            if (Files.isDirectory(path)) {
                System.out.println("Es un directorio este es el contenido:");

                try (DirectoryStream<Path> archivos = Files.newDirectoryStream(path)) {
                    archivos.forEach(archivo -> {
                         System.out.println("- " + archivo.getFileName());
                    });
                } catch (IOException e) {
                    System.out.println("Error al leer el directorio: " + e.getMessage());
                }

            } else {
                System.out.println("La ruta es de un archivo");
            }
        } else {
            System.out.println("La ruta no existe");
        }

        scanner.close();
    }
}
