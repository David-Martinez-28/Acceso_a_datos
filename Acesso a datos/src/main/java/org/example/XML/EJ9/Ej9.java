package org.example.XML.EJ9;

import org.example.XML.Alumno;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Ej9 {
    static void main(String[] args) {
        ArrayList<Alumno> alumnosList=new ArrayList<>();
        alumnosList=formatearAlumnos(alumnosList);
        for(Object alumno :  alumnosList){
           System.out.println(alumno);
        }

    }

    private static ArrayList<Alumno> formatearAlumnos( ArrayList alumnosList){


        try {

            File archivo = new File("/home/ciclosm/IdeaProjects/Acesso a datos/src/main/java/org/example/XML/alumnos.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.parse(archivo);
            NodeList alumnos = documento.getElementsByTagName("alumno");

            for (int i = 0; i < alumnos.getLength(); i++) {

                Element alumno1= (Element) alumnos.item(i);

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

                int id=Integer.parseInt(idStr);
                int edad=Integer.parseInt(edadStr);
                double nota=Double.parseDouble(notaStr);

                alumnosList.add(new Alumno(id,nombre,edad,nota));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return    alumnosList;
    }
}
