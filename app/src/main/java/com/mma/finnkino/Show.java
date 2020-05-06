package com.mma.finnkino;

import androidx.annotation.NonNull;

import java.util.Date;

public class Show {
    private long id;
    private long eventid;
    private Date showStart;
    private Date showEnd;
    private String title;
    private String location;

    public Show(long id, long eventid, Date start, Date end, String title, String location) {
        this.id = id;
        this.eventid = eventid;
        this.showStart = start;
        this.showEnd = end;
        this.title = title;
        this.location = location;
    }

    public long getID() {
        return id;
    }

    public long getEventID() {
        return eventid;
    }

    public String getTitle() {
        return title;
    }

    public Date getStart() {
        return showStart;
    }

    public Date getEnd() {
        return showEnd;
    }

    public String getLocation() {
        return location;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s: %s - %s (%s)", title, showStart, showEnd, location);
    }
}
