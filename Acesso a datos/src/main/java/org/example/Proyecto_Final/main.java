package org.example.Proyecto_Final;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        boolean salir = false;
        ArrayList<Alumno> alumnos = new ArrayList<>();
        importarXML(alumnos);
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {

            mostrarMenu();
            do {
                System.out.println("Introduce la opcion:");
                opcion = pedirNumero();
                if (opcion > 8 || opcion<=0) {
                    System.out.println("Se ha introducido un numero no valido(1 al 8)");
                }
            }while (opcion<=0 || opcion>8);


            switch (opcion) {
                case 1:
                    anadirAlumno(alumnos);
                    break;
                case 2:
                    mostrarAlumnos(alumnos);
                break;
                case 3:
                    buscarAlumno(alumnos);
                break;
                case 4:
                    eliminarAlumno(alumnos);
                    break;
                case 5:
                    exportarXML(alumnos);
                    break;
                case 6:
                    importarXML(alumnos);
                    break;
                case 7:
                    copiaDeSeguridad(alumnos);
                    break;
                case 8:
                    salir = true;
                    System.out.println("Salir");
                    break;
            }

        }while (!salir);
    }
    public static void mostrarMenu(){
        System.out.println("GESTOR DE ALUMNOS");
        System.out.println("=================");
        System.out.println("1. Añadir alumno");
        System.out.println("2. Mostrar alumnos");
        System.out.println("3. Buscar alumno");
        System.out.println("4. Eliminar alumno");
        System.out.println("5. Exportar a XML");
        System.out.println("6. Importar desde XML");
        System.out.println("7. Crear copia de seguridad");
        System.out.println("8. Salir");

    }

    public static void anadirAlumno(ArrayList<Alumno> alumnos){
        if(alumnos.isEmpty()){
            System.out.println("No hay alumnos registrados");
        }else{
            int edad;
            double nota;
            int id=recogerId(alumnos);
            System.out.println("Introduce el nombre");
            String nombre=pedirNombre();
            System.out.println("Introduce el apellido");
            String apellido=pedirNombre();
            do {
                System.out.println("Introduce la edad");
                edad=pedirNumero();
                if(edad<0){
                    System.out.println("Se ha introducido un edad negativo");
                }
            }while(edad<0);
            do{
                System.out.println("introduce la nota");
                nota=pedirNumeroDecimal();
                if(nota<0 || nota>10){
                    System.out.println("No se ha introducido una nota entre 0 a 10");
                }
            }while(nota < 0 || nota > 10);
            Alumno alumnoNuevo=new Alumno(id,nombre,apellido,edad,nota);

            alumnos.add(alumnoNuevo);
            exportarXML(alumnos);
        }
    }
    public static void mostrarAlumnos(ArrayList<Alumno> alumnos){
        if (alumnos.isEmpty()){
            System.out.println("No alumnos encontrados");
        }else {
            for(Alumno alumno: alumnos){
                System.out.println("====================");
                System.out.println("Nombre:"+alumno.getNombre());
                System.out.println("Apellidos:"+alumno.getApellidos());
                System.out.println("Edad:"+alumno.getEdad());
                System.out.println("Nota"+alumno.getNota());

            }
        }

    }
    public static void buscarAlumno(ArrayList<Alumno> alumnos){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce alumno");
        int alumno=pedirNumero();
        for(Alumno alumno1:alumnos){
            if(alumno1.getId()==alumno){
                System.out.println(alumno1);
            }else {
                System.out.println("Alumno no existe");
            }
        }
    }
    public static void eliminarAlumno(ArrayList<Alumno> alumnos){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce alumno");
        int alumno= pedirNumero();
        boolean eliminar=false;
        for(Alumno alumno1:alumnos){
            if (alumno1.getNombre().equals(alumno)){
                System.out.println(alumno);
                System.out.println(alumno1.getNombre());
                eliminar=true;
            }else {
                eliminar=false;
            }
        }
        if(eliminar){
            alumnos.remove(alumno);
        }else{
            System.out.println("Alumno no existe");
        }
        exportarXML(alumnos);
    }


    public static void importarXML( ArrayList<Alumno> alumnosList) {


        try {
            //Ruta de larchivo a leer
            File archivo = new File("/home/ciclosm/IdeaProjects/Acesso a datos/src/main/java/org/example/Proyecto_Final/alumnos.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivo);

            //Recogemos los nodos alumno del documento XML
            NodeList alumnos = documento.getElementsByTagName("alumno");

            for (int i = 0; i < alumnos.getLength(); i++) {

                //Castemos el Node de alumno a un elemento Element
                Element alumno1 = (Element) alumnos.item(i);

                //Datos del XML
                String idStr =
                        alumno1.getElementsByTagName("id")
                                .item(0)
                                .getTextContent();

                String nombre =
                        alumno1.getElementsByTagName("nombre")
                                .item(0)
                                .getTextContent();

                String edadStr =
                        alumno1.getElementsByTagName("edad")
                                .item(0)
                                .getTextContent();

                String notaStr =
                        alumno1.getElementsByTagName("nota")
                                .item(0)
                                .getTextContent();

                String apellidos =
                        alumno1.getElementsByTagName("apellidos")
                                .item(0)
                                .getTextContent();


                //Parseo de datos de String a INT/Double
                int id = Integer.parseInt(idStr);
                int edad = Integer.parseInt(edadStr);
                double nota = Double.parseDouble(notaStr);

                alumnosList.add(new Alumno(id, nombre, apellidos, edad, nota));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void exportarXML(ArrayList<Alumno> alumnosList) {

        try {
            Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            // Elemento raíz
            Element raiz = documento.createElement("alumnos");
            documento.appendChild(raiz);

            for (Alumno alumnoItem : alumnosList) { // Nombre de variable en singular para mayor claridad

                // Elemento alumno
                Element alumno = documento.createElement("alumno");
                raiz.appendChild(alumno);

                // id
                Element id = documento.createElement("id");
                id.setTextContent(String.valueOf(alumnoItem.getId()));
                alumno.appendChild(id);

                // Nombre
                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(alumnoItem.getNombre());
                alumno.appendChild(nombre);

                // Edad
                Element edad = documento.createElement("edad");
                edad.setTextContent(String.valueOf(alumnoItem.getEdad()));
                alumno.appendChild(edad);

                // Nota
                Element nota = documento.createElement("nota");
                nota.setTextContent(String.valueOf(alumnoItem.getNota()));
                alumno.appendChild(nota);

                Element apellidos = documento.createElement("apellidos");
                apellidos.setTextContent(String.valueOf(alumnoItem.getApellidos()));
                alumno.appendChild(apellidos);
            }

            // Configuración del Transformer
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            // Guardar directamente en archivo físico
            File archivoSalida = new File("/home/ciclosm/IdeaProjects/Acesso a datos/src/main/java/org/example/Proyecto_Final/alumnos.xml");
            t.transform(new DOMSource(documento), new StreamResult(archivoSalida));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean esNumero(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int pedirNumero() {
        Scanner sc = new Scanner(System.in);
        boolean esNumero;
        String numero;

        do {

            numero = sc.nextLine();
            esNumero = esNumero(numero);

            if (!esNumero) {
                System.out.println("Entrada no válida. Por favor, introduce un número entero.");
            }
        } while (!esNumero);

        return Integer.parseInt(numero);
    }
    public static boolean esNumeroDecimal(String cadena) {
        try {
            Double.parseDouble(cadena.replace(',', '.'));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static double pedirNumeroDecimal() {
        Scanner sc = new Scanner(System.in);
        boolean esNumero;
        String numero;

        do {

            numero = sc.nextLine();
            esNumero = esNumeroDecimal(numero);

            if (!esNumero) {
                System.out.println("Entrada no válida. Por favor, introduce un número decimal correcto.");
            }
        } while (!esNumero);

        // Convertimos la coma a punto antes de parsear a double
        return Double.parseDouble(numero.replace(',', '.'));
    }




    public static boolean esNombreValido(String cadena) {
            if (cadena == null) {
                return false;
            }
            String textoLimpio = cadena.trim();
            return !textoLimpio.isEmpty() && textoLimpio.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }

    public static String pedirNombre() {
            Scanner sc = new Scanner(System.in);
            boolean esValido;
            String nombre;
            do {

                nombre = sc.nextLine();
                esValido = esNombreValido(nombre);

                if (!esValido) {
                    System.out.println("Entrada no válida. El nombre solo debe contener letras y no puede estar vacío.");
                }
            } while (!esValido);

            return nombre.trim();
    }
    public static int recogerId(ArrayList<Alumno> alumnos) {
        int ultimaId=0;
        for (Alumno alumnoItem : alumnos) {
            if (alumnoItem.getId() > ultimaId) {
                ultimaId = alumnoItem.getId();

            }
        }
        return ultimaId+1;
    }
    public static void copiaDeSeguridad(ArrayList<Alumno> alumnosList) {

        try {
            Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            // Elemento raíz
            Element raiz = documento.createElement("alumnos");
            documento.appendChild(raiz);

            for (Alumno alumnoItem : alumnosList) { // Nombre de variable en singular para mayor claridad

                // Elemento alumno
                Element alumno = documento.createElement("alumno");
                raiz.appendChild(alumno);

                // id
                Element id = documento.createElement("id");
                id.setTextContent(String.valueOf(alumnoItem.getId()));
                alumno.appendChild(id);

                // Nombre
                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(alumnoItem.getNombre());
                alumno.appendChild(nombre);

                // Edad
                Element edad = documento.createElement("edad");
                edad.setTextContent(String.valueOf(alumnoItem.getEdad()));
                alumno.appendChild(edad);

                // Nota
                Element nota = documento.createElement("nota");
                nota.setTextContent(String.valueOf(alumnoItem.getNota()));
                alumno.appendChild(nota);

                Element apellidos = documento.createElement("apellidos");
                apellidos.setTextContent(String.valueOf(alumnoItem.getApellidos()));
                alumno.appendChild(apellidos);
            }

            // Configuración del Transformer
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            // Guardar directamente en archivo físico
            File archivoSalida = new File("/home/ciclosm/IdeaProjects/Acesso a datos/src/main/java/org/example/Proyecto_Final/CopiaDeSeguridadAlumnosalumnos.xml");
            t.transform(new DOMSource(documento), new StreamResult(archivoSalida));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
