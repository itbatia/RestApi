package com.itbatia.app.repositopy.db;

import com.itbatia.app.model.Event;
import com.itbatia.app.repositopy.EventRepository;
import org.hibernate.*;

import java.util.List;

import static com.itbatia.app.utils.HibernateUtil.getSession;

public class DbEventRepositoryImpl implements EventRepository {
    @Override
    public Event save(Event event) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Integer id = (Integer) session.save(event);
            event.setId(id);
            transaction.commit();
            return event;
        }
    }

    @Override
    public Event getById(Integer id) {
        try (Session session = getSession()) {
            return (Event) session.createQuery("FROM Event e WHERE e.id=" + id).uniqueResult();
        }
    }

    @Override
    public List<Event> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("FROM Event").list();
        }
    }

    @Override
    public Event update(Event event) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(event);
            transaction.commit();
            return event;
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Event event = session.get(Event.class, id);
            session.delete(event);
            transaction.commit();
        }
    }
}
