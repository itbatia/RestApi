package com.itbatia.app.service;

import com.itbatia.app.model.Activity;
import com.itbatia.app.model.Event;
import com.itbatia.app.model.File;
import com.itbatia.app.repository.EventRepository;
import com.itbatia.app.repository.db.DbEventRepositoryImpl;

import java.util.Date;
import java.util.List;

public class EventService {

    private EventRepository eventRepository = new DbEventRepositoryImpl();

    public EventService() {}
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Activity activity, Date date, File file, Integer userId) {
        Event event = new Event(null, activity, date, file, userId);
        return eventRepository.save(event);
    }

    public Event getEvent(Integer id) {
        return eventRepository.getById(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.getAll();
    }

    public Event updateEvent(Event event) {
        return eventRepository.update(event);
    }

    public void deleteEvent(Integer id) {
        eventRepository.deleteById(id);
    }
}
