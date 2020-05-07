package com.mma.finnkino;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser {
    private Document doc;

    XMLParser(String uri) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            doc = builder.parse(uri);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValueFromTag(Element e, String tag, String attr) {
        String str;

        str = ((Element) e.getElementsByTagName(tag).item(0)).getAttribute(attr);

        return str;
    }

    public String getTextFromTag(Element e, String tag) {
        String str;

        str = e.getElementsByTagName(tag).item(0).getTextContent();

        return str;
    }

    public Document getDOC() {
        return doc;
    }
}
