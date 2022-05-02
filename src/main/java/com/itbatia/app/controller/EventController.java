package com.itbatia.app.controller;

import com.itbatia.app.model.Activity;
import com.itbatia.app.model.Event;
import com.itbatia.app.model.File;
import com.itbatia.app.service.EventService;

import java.util.Date;
import java.util.List;

public class EventController {
    private final EventService eventService = new EventService();

    public Event createEvent(Activity activity, Date date, File file, Integer userId) {
        return eventService.createEvent(activity, date, file, userId);
    }

    public Event getEvent(Integer id) {
        return eventService.getEvent(id);
    }

    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    public Event updateEvent(Event event) {
        return eventService.updateEvent(event);
    }

    public void deleteEvent(Integer id) {
        eventService.deleteEvent(id);
    }
}
