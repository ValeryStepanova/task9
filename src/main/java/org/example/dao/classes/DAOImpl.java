package org.example.dao.classes;

import org.example.dao.interfaces.DAO;
import org.example.utils.HibernateUtil;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.util.Arrays;

public class DAOImpl<T> implements DAO<T> {
    @Override
    public void create(T obj) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println("OK");
    }

    @Override
    public T read(int id, Class<T> tClass) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        T obj = entityManager.find(tClass, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return obj;
    }

    @Override
    public T update(int id, Class<T> tClass, T obj) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();

        T result = entityManager.find(tClass, id);
        if (checkFindById(result)) {
            entityManager.getTransaction().commit();
            return null;
        }

        Field[] objFields = tClass.getDeclaredFields();
        Arrays.stream(objFields)
                .peek(q -> q.setAccessible(true))
                .filter(field -> field.isAnnotationPresent(Column.class))
                .forEach(field -> {
                    try {
                        field.set(result, field.get(obj));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        entityManager.merge(result);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return obj;
    }

    @Override
    public T delete(int id, Class<T> tClass) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        T obj = entityManager.find(tClass, id);
        entityManager.remove(obj);
        entityManager.getTransaction().commit();
        entityManager.close();
        return obj;
    }

    private boolean checkFindById(T object) {
        if (null == object) {
            System.out.println("Not found");
            return true;
        }
        return false;
    }
}
