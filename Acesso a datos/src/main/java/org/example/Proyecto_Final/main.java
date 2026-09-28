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
    static void main(String[] args) {
        do {
            ArrayList<Alumno> alumnos = new ArrayList<>();
            importarXML(alumnos);
            Scanner sc = new Scanner(System.in);
            boolean esNUmero = true;
            int opcion = 0;
            mostrarMenu();
            do {
                System.out.println("Escribe la opcion:");
                try {
                    opcion = sc.nextInt();
                    esNUmero = false;
                }catch (InputMismatchException e){

                }

            }while (esNUmero);

            switch (opcion) {
                case 1:

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
                    case 5:exportarXML(alumnos);
                    break;
                    case 6:
                        importarXML(alumnos);
                        break;
                        case 7:

                            break;
                            case 8:

                                                break;
            }

        }while (true);
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
    public static void mostrarAlumnos(ArrayList<Alumno> alumnos){
        if (alumnos.isEmpty()){
            System.out.println("No alumnos encontrados");
        }else {
            for(Alumno alumno: alumnos){
                System.out.println(alumno);
            }
        }

    }
    public static void buscarAlumno(ArrayList<Alumno> alumnos){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce alumno");
        int alumno=sc.nextInt();
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
        String alumno=sc.nextLine();
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

            for (Alumno alumnos : alumnosList) {

                // Alumno
                Element alumno = documento.createElement("alumno");
                raiz.appendChild(alumno);

                // id
                Element id = documento.createElement("id");
                id.setTextContent(String.valueOf(alumnos.getId()));
                alumno.appendChild(id);

                // Nombre
                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(alumnos.getNombre());
                alumno.appendChild(nombre);

                // Edad
                Element edad = documento.createElement("edad");
                edad.setTextContent(String.valueOf(alumnos.getEdad()));
                alumno.appendChild(edad);

                // Nota
                Element nota = documento.createElement("nota");
                nota.setTextContent(String.valueOf(alumnos.getNota()));
                alumno.appendChild(nota);

            }

            //Dar formato de XML

            Transformer t = TransformerFactory.newInstance().newTransformer();

            t.setParameter(OutputKeys.INDENT, "yes");
            ByteArrayOutputStream s = new ByteArrayOutputStream();

            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            t.transform(new DOMSource(documento),new StreamResult(s));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
