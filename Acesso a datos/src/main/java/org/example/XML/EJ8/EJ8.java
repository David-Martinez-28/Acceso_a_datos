package org.example.XML.EJ8;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EJ8 {

    public static void main(String[] args) {

        try {

            File archivo = new File("/home/ciclosm/IdeaProjects/Acesso a datos/src/main/java/org/example/XML/alumnos.xml");

            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            Document documento =
                    builder.parse(archivo);
            NodeList alumnos =
                    documento.getElementsByTagName("alumno");

            for (int i = 0; i < alumnos.getLength(); i++) {

                Node alumno = alumnos.item(i);

                Element alumno1= (Element) alumno;

                String id =
                        alumno1.getElementsByTagName("id")
                                .item(0)
                                .getTextContent();

                String nombre =
                        alumno1.getElementsByTagName("nombre")
                                .item(0)
                                .getTextContent();

                String edad =
                        alumno1.getElementsByTagName("edad")
                                .item(0)
                                .getTextContent();

                String nota =
                        alumno1.getElementsByTagName("nota")
                                .item(0)
                                .getTextContent();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}