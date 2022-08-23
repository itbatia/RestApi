package com.itbatia.app.repository.db;

import com.itbatia.app.model.User;
import com.itbatia.app.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.stream.Collectors;

import static com.itbatia.app.utils.HibernateUtil.getSession;

public class DbUserRepositoryImpl implements UserRepository {
    @Override
    public User save(User user) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Integer id = (Integer) session.save(user);
            user.setId(id);
            transaction.commit();
            return user;
        }
    }

    @Override
    public User getById(Integer id) {
        try (Session session = getSession()) {
            return (User) session.createQuery("FROM User u LEFT JOIN FETCH u.events WHERE u.id=" + id).uniqueResult();
        }
    }


    @Override
    public List<User> getAll() {
        try (Session session = getSession()) {
            return (List<User>) session.createQuery("FROM User u LEFT JOIN FETCH u.events ORDER BY u.id ASC")
                    .list().stream().distinct().collect(Collectors.toList());
        }
    }

    @Override
    public User update(User user) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            return user;
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        }
    }
}
