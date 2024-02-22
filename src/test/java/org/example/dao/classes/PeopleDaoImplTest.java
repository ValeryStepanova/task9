package org.example.dao.classes;

import org.example.dao.classes.utils.MockUtils;
import org.example.entity.People;
import org.example.utils.HibernateUtil;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class PeopleDaoImplTest {
    private static final PeopleDaoImpl peopleDao = new PeopleDaoImpl();

    @Test
    void create() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        People people = MockUtils.buildPeople();
        peopleDao.create(people);
        People exPeople = entityManager.find(People.class, 1);
        entityManager.getTransaction().commit();
        assertEquals(exPeople.getAge(), people.getAge());
        assertEquals(exPeople.getName(), people.getName());
        assertEquals(exPeople.getSurname(), people.getSurname());
        entityManager.close();
    }

    @Test
    void read() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        People people = MockUtils.buildPeople();
        entityManager.persist(people);
        entityManager.getTransaction().commit();
        People exPeople = peopleDao.read(1, People.class);
        assertEquals(exPeople.getAge(), people.getAge());
        assertEquals(exPeople.getName(), people.getName());
        assertEquals(exPeople.getSurname(), people.getSurname());
        entityManager.close();
    }

    @Test
    void update() {
        People people = MockUtils.buildPeople();
        peopleDao.create(people);
        int age = people.getAge();
        people.setAge(20);
        peopleDao.update(1, People.class, people);
        assertNotEquals(age, people.getAge());
    }

    @Test
    void delete() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        People people = MockUtils.buildPeople();
        entityManager.persist(people);
        entityManager.getTransaction().commit();
        People exPeople = peopleDao.delete(1, People.class);
        assertEquals(exPeople.getAge(), people.getAge());
        assertEquals(exPeople.getName(), people.getName());
        assertEquals(exPeople.getSurname(), people.getSurname());
    }

    @Test
    void increaseAge() {
        People people1 = MockUtils.buildPeople();
        People people2 = MockUtils.buildPeople();
        peopleDao.create(people1);
        peopleDao.create(people2);
        peopleDao.increaseAge();
        People exPeople1 = peopleDao.read(1, People.class);
        People exPeople2 = peopleDao.read(2, People.class);
        assertNotEquals(exPeople1.getAge(), people1.getAge());
        assertNotEquals(exPeople2.getAge(), people2.getAge());
    }
}