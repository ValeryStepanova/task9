package org.example.dao.interfaces;

import org.example.entity.Address;

public interface DAOAddress extends DAO<Address> {
    String increaseHouse();
}
