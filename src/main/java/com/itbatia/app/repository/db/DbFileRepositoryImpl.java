package com.itbatia.app.repository.db;

import com.itbatia.app.model.File;
import com.itbatia.app.repository.FileRepository;
import org.hibernate.*;

import java.util.List;

import static com.itbatia.app.utils.HibernateUtil.getSession;

public class DbFileRepositoryImpl implements FileRepository {

    @Override
    public File save(File file) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            Integer id = (Integer) session.save(file);
            file.setId(id);
            transaction.commit();
            return file;
        }
    }

    @Override
    public File getById(Integer id) {
        try (Session session = getSession()) {
            return (File) session.createQuery("FROM File f WHERE f.id=" + id).uniqueResult();
        }
    }

    @Override
    public List<File> getAll() {
        try (Session session = getSession()) {
            return session.createQuery("FROM File").list();
        }
    }

    @Override
    public File update(File file) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(file);
            transaction.commit();
            return file;
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            File file = session.get(File.class, id);
            session.delete(file);
            transaction.commit();
        }
    }
}
