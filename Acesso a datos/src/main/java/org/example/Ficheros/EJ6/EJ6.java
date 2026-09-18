package org.example.Ficheros.EJ6;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class EJ6 {
    public static void main(String[] args) {
        Path origen = Path.of("/home/ciclosm/IdeaProjects/Acesso a datos/src/main/java/org/example/Ficheros/EJ6/alumnos.txt");
        Path directorioCopias = Path.of("/home/ciclosm/IdeaProjects/Acesso a datos/src/main/java/org/example/Ficheros/EJ6/copias");


        if (Files.exists(origen)) {

            try {
                if (!Files.exists(directorioCopias)) {
                    Files.createDirectories(directorioCopias);
                }

                String fechaActual = LocalDate.now().toString();
                Path destino = directorioCopias.resolve("alumnos_" + fechaActual + ".txt");

                Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copia de seguridad creada correctamente: " + destino);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("El archivo alumnos.txt no existe.");
        }


    }
}
