package org.example.dao.classes;

import org.example.dao.interfaces.DAOPeople;
import org.example.entity.People;
import org.example.utils.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PeopleDaoImpl extends DAOImpl<People> implements DAOPeople {

    public static final String SELECT_FROM_PEOPLE = "select *from people\n" +
            "order by  id desc\n" +
            "limit 2";

    @Override
    public void create(People obj) {
        super.create(obj);
    }

    @Override
    public People read(int id, Class<People> peopleClass) {
        return super.read(id, peopleClass);
    }

    @Override
    public People update(int id, Class<People> peopleClass, People obj) {
        return super.update(id, peopleClass, obj);
    }

    @Override
    public void delete(int id, Class<People> peopleClass) {
        super.delete(id, peopleClass);
    }

    @Override
    public String increaseAge() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery(SELECT_FROM_PEOPLE, People.class);
        List<People> list = query.getResultList();
        if (list.size() < 2) {
            return "Не хватает значений";
        }
        for (People people1 : list) {
            people1.setAge(people1.getAge() + 2);
            entityManager.merge(people1);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Успешно";
    }
}
