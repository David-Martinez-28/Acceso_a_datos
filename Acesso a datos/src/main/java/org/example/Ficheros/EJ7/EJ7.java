package org.example.Ficheros.EJ7;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.Scanner;

public class EJ7 {

    public static void main(String[] args) {

        Path origen = Path.of(
                "src/main/java/org/example/Ficheros/datos/alumnos.dat"
        );

        try (RandomAccessFile fichero = new RandomAccessFile(origen.toFile(), "rw");

             Scanner sc = new Scanner(System.in)) {

            fichero.writeInt(1);
            fichero.writeInt(20);
            fichero.writeDouble(7.5);

            fichero.writeInt(2);
            fichero.writeInt(21);
            fichero.writeDouble(8.2);

            fichero.writeInt(3);
            fichero.writeInt(19);
            fichero.writeDouble(9.1);

            // Pedimos el número de alumno
            System.out.print("Introduce el número de alumno: ");
            int numero = sc.nextInt();

            long posicion = (numero - 1) * 16L;

            fichero.seek(posicion);

            int id = fichero.readInt();
            int edad = fichero.readInt();
            double nota = fichero.readDouble();


            System.out.println("ID: " + id);
            System.out.println("Edad: " + edad);
            System.out.println("Nota: " + nota);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
