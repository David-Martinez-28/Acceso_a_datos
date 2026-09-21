package org.example.XML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PruebaXML {

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();

        DocumentBuilder builder =
                factory.newDocumentBuilder();

        Document documento =
                builder.newDocument();

        Element raiz =
                documento.createElement("alumnos");

        documento.appendChild(raiz);

        System.out.println("XML funcionando correctamente");
    }
}
