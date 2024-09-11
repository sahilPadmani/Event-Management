package com.example.eventx20;
// Event.java
public class Event {
    private String name;
    private String date;
    private String time;
    private String location;

    public Event(String name, String date, String time, String location) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }
}
