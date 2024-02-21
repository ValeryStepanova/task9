package org.example.dao.interfaces;

import org.example.entity.People;

public interface DAOPeople extends DAO<People> {
    String increaseAge();
}
