package org.example.dao.classes;

import org.example.dao.interfaces.DAOAddress;
import org.example.entity.Address;
import org.example.utils.HibernateUtil;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AddressDaoImpl extends DAOImpl<Address> implements DAOAddress {
    public static final String SELECT = "select *\n" +
            "from address\n" +
            "order by id desc\n" +
            "limit 2";

    @Override
    public void create(Address obj) {
        super.create(obj);
    }

    @Override
    public Address read(int id, Class<Address> addressClass) {
        return super.read(id, addressClass);
    }

    @Override
    public Address update(int id, Class<Address> addressClass, Address obj) {
        return super.update(id, addressClass, obj);
    }

    @Override
    public Address delete(int id, Class<Address> addressClass) {
        return super.delete(id, addressClass);
    }

    @Override
    public String increaseHouse() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery(SELECT, Address.class);
        List<Address> list = query.getResultList();
        if (list.size() < 2) {
            return "Не хватает значений";
        }
        for (Address address : list) {
            address.setHouse(address.getHouse() + 1);
            entityManager.merge(address);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Успешно";
    }
}
