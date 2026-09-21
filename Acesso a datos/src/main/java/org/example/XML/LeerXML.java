package org.example.XML;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LeerXML {

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
            System.out.println("XML cargado correctamente");
            for (int i = 0; i < alumnos.getLength(); i++) {

                Node alumno = alumnos.item(i);

                System.out.println(alumno.getNodeName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
