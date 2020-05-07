package com.mma.finnkino;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Date;

public class ShowHandler {
    private ArrayList<Show> list;
    private Document doc;

    public ShowHandler(XMLParser xml) {
        list = new ArrayList();
        long id;
        long eventid;
        Date showStart;
        Date showEnd;
        String title;
        String location;

        doc = xml.getDOC();
        NodeList nList = doc.getElementsByTagName("Show");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            Element e = (Element) node;

            id = Long.parseLong(xml.getTextFromTag(e, "ID"));
            eventid = Long.parseLong(xml.getTextFromTag(e, "EventID"));
            showStart = DateParser.parseDateTime(xml.getTextFromTag(e, "dttmShowStart"), "yyyy-MM-dd'T'HH:mm:ss");
            showEnd = DateParser.parseDateTime(xml.getTextFromTag(e, "dttmShowEnd"), "yyyy-MM-dd'T'HH:mm:ss");
            title = xml.getTextFromTag(e, "Title");
            location = xml.getTextFromTag(e, "TheatreAndAuditorium");

            list.add(new Show(id, eventid, showStart, showEnd, title, location));
        }
    }

    public ArrayList<Show> getList() {
        return list;
    }

    public ArrayList<Show> getFilteredList(String date, String start, String end, String title) {
        ArrayList<Show> showList = new ArrayList<>();
        Date showStart, showEnd;
        final String DATE_FORMAT = "dd.MM.yyyy HH.mm";
        String output;
        boolean addTitle;

        if (start == null || start.isEmpty()) {
            start = "00.00";
        }

        if (end == null || end.isEmpty()) {
            end = "23.59";
        }

        start = start.replace(":", ".");
        end = end.replace(":", ".");
        showStart = DateParser.parseDateTime(date, start, DATE_FORMAT);
        showEnd = DateParser.parseDateTime(date, end, DATE_FORMAT);

        for (Show s : list) {
            addTitle = false;

            if (showStart.after(s.getStart()) || showEnd.before(s.getEnd())) {
                continue;
            }

            if (title.length() == 0) {
                addTitle = true;
            }
            else if (s.getTitle().toLowerCase().contains(title.toLowerCase())) {
                addTitle = true;
            }

            if (addTitle) {
                showList.add(s);
            }
        }

        return showList;
    }
}
