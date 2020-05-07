package com.mma.finnkino;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class TheatreHandler {
    private ArrayList<Theatre> list;
    private Document doc;

    public TheatreHandler(XMLParser xml) {
        list = new ArrayList();
        long id;
        String name;

        doc = xml.getDOC();
        NodeList nList = doc.getElementsByTagName("TheatreArea");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            Element e = (Element) node;

            id = Long.parseLong(xml.getTextFromTag(e, "ID"));
            name = xml.getTextFromTag(e, "Name");

            list.add(new Theatre(id, name));
        }
    }

    public ArrayList<Theatre> getList() {
        return list;
    }
}
