package org.example.dao.interfaces;

public interface DAO<T> {
    void create(T obj);
    T read(int id, Class<T> tClass);
    T update(int id, Class<T> tClass, T obj);
    void delete(int id, Class<T> tClass);
}
