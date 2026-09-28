package org.example.XML.EJ10;

import org.example.XML.Alumno;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;


public class Ej10 {
    static void main(String[] args) {

        //Arrays de uso

        ArrayList<Alumno> alumnosList=new ArrayList<>();

        ArrayList<Alumno> alumnosList1=new ArrayList<>();

        //Funcion que le pasamos el arrayList que queremos que se pasen los datos

        formatearAlumnos(alumnosList);

        //Se pasa el arrayList de la clase Alumno para que escriba el XML

        escribirXMLDeArrayList(alumnosList);

        //Se pasa otro Array list para ver como recupera los datos del XML

        formatearAlumnos(alumnosList1);

        for(Alumno alumno : alumnosList1){
            System.out.println(alumno);
        }

    }

    /// Funcion que lee el XML y los tranforma el contenido a una clase  Alumno
    private static void formatearAlumnos(ArrayList<Alumno> alumnosList){


        try {
            //Ruta de larchivo a leer
            File archivo = new File("/home/ciclosm/IdeaProjects/Acesso a datos/src/main/java/org/example/XML/alumnos.xml");

            Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivo);

            //Recogemos los nodos alumno del documento XML
            NodeList alumnos = documento.getElementsByTagName("alumno");

            for (int i = 0; i < alumnos.getLength(); i++) {

                //Castemos el Node de alumno a un elemento Element
                Element alumno1= (Element) alumnos.item(i);

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


                //Parseo de datos de String a INT/Double
                int id=Integer.parseInt(idStr);
                int edad=Integer.parseInt(edadStr);
                double nota=Double.parseDouble(notaStr);

                alumnosList.add(new Alumno(id,nombre,edad,nota));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Funcion qeu se le pasa el arrayList para que escriba el archivo XML

    private static void escribirXMLDeArrayList(ArrayList<Alumno> alumnosList) {

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
