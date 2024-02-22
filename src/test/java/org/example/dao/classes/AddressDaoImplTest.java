package org.example.dao.classes;

import org.example.dao.classes.utils.MockUtils;
import org.example.entity.Address;
import org.example.utils.HibernateUtil;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class AddressDaoImplTest {
    private static final AddressDaoImpl addressDao = new AddressDaoImpl();
    @Test
    @Order(1)
    void create() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Address address = MockUtils.buildAddress();
        addressDao.create(address);
        Address expectAddress = entityManager.find(Address.class, 1);
        entityManager.getTransaction().commit();
        assertEquals(expectAddress.getStreet(), address.getStreet());
        assertEquals(expectAddress.getHouse(), address.getHouse());
        entityManager.close();
    }

    @Test
    @Order(2)
    void read() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Address address = MockUtils.buildAddress();
        entityManager.persist(address);
        entityManager.getTransaction().commit();
        Address expectAddress = addressDao.read(1, Address.class);
        assertEquals(expectAddress.getStreet(), address.getStreet());
        assertEquals(expectAddress.getHouse(), address.getHouse());
        entityManager.close();
    }

    @Test
    @Order(3)
    void update() {
        Address address = MockUtils.buildAddress();
        addressDao.create(address);
        int house = address.getHouse();
        address.setHouse(43);
        addressDao.update(1, Address.class, address);
        assertNotEquals(house, address.getHouse());
    }

    @Test
    @Order(4)
    void delete() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Address address = MockUtils.buildAddress();
        entityManager.persist(address);
        entityManager.getTransaction().commit();
        Address expectAddress = addressDao.delete(1, Address.class);
        assertEquals(expectAddress.getHouse(), address.getHouse());
        assertEquals(expectAddress.getStreet(), address.getStreet());
    }

    @Test
    @Order(5)
    void increaseHouse() {
        Address address1 = MockUtils.buildAddress();
        Address address2 = MockUtils.buildAddress();
        addressDao.create(address1);
        addressDao.create(address2);
        addressDao.increaseHouse();
        Address expectAddress1 = addressDao.read(1, Address.class);
        Address expectAddress2 = addressDao.read(2, Address.class);
        assertNotEquals(expectAddress1.getHouse(), address1.getHouse());
        assertNotEquals(expectAddress2.getHouse(), address2.getHouse());
    }
}