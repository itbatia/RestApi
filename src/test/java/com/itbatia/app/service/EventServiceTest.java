package com.itbatia.app.service;

import com.itbatia.app.model.*;
import com.itbatia.app.repository.EventRepository;
import com.itbatia.app.repository.db.DbEventRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EventServiceTest {

    private EventService eventService;
    private Activity activity;
    private Date dateEvent;
    private File file;
    private Integer userId;

    @Mock
    private final EventRepository mock = mock(DbEventRepositoryImpl.class);

    @Before
    public void setUp() {
        eventService = new EventService(mock);
        activity = Activity.CREATION;
        dateEvent = new Date();
        file = new File();
        userId = 1;
    }

    @Test
    public void createEvent() {
        Event event = new Event(null, activity, dateEvent, file, userId);
        Event eventExpected = new Event(1, activity, dateEvent, file, userId);

        when(mock.save(event)).thenReturn(eventExpected);
        Event eventActual = eventService.createEvent(activity, dateEvent, file, userId);

        verify(mock).save(event); //проверяем, что с методом save() было взаимодействие
        verify(mock, times(1)).save(event); //проверяем, что метод save() был вызван 1 раз
        assertEquals(eventExpected, eventActual); //сравниваем ожидаемые и актуальные данные
    }

    @Test
    public void getEvent() {
        Event eventExpected = new Event(1, activity, dateEvent, file, userId);

        when(mock.getById(1)).thenReturn(eventExpected);
        Event eventActual = eventService.getEvent(1);

        verify(mock).getById(1); //проверяем, что с методом getById() было взаимодействие
        verify(mock, never()).getById(2); //проверяем, что метод getById с параметром "2" не вызывался
        assertEquals(eventExpected, eventActual); //сравниваем ожидаемые и актуальные данные
    }

    @Test
    public void getAllEvents() {
        List<Event> eventsExpected = new ArrayList<>();

        when(mock.getAll()).thenReturn(eventsExpected);
        List<Event> eventsActual = eventService.getAllEvents();

        verify(mock).getAll(); //проверяем, что с методом getAll() было взаимодействие
        verify(mock, times(1)).getAll(); //проверяем, что метод getAll() был вызван 1 раз
        assertEquals(eventsExpected, eventsActual); //сравниваем ожидаемые и актуальные данные
    }

    @Test
    public void updateEvent() {
        Event eventExpected = new Event(1, activity, dateEvent, file, userId);

        when(mock.update(eventExpected)).thenReturn(eventExpected);
        Event eventActual = eventService.updateEvent(eventExpected);

        verify(mock).update(eventExpected); //проверяем, что с методом update() было взаимодействие
        verify(mock, times(1)).update(eventExpected); //проверяем, что метод update() был вызван 1 раз
        assertEquals(eventExpected, eventActual); //сравниваем ожидаемые и актуальные данные
    }

    @Test
    public void deleteEvent() {
        eventService.deleteEvent(1);

        verify(mock).deleteById(1); //проверяем, что с методом deleteById() было взаимодействие
        verify(mock, atLeast(1)).deleteById(1); //проверяем, что взаимодействие с методом
        verify(mock, atMost(2)).deleteById(1); //было минимум 1 раз, но не более 2-х раз
    }
}
