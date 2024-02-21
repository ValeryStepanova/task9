package org.example;

import org.example.dao.classes.AddressDaoImpl;
import org.example.dao.classes.PeopleDaoImpl;
import org.example.entity.Address;
import org.example.entity.People;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PeopleDaoImpl peopleDao = new PeopleDaoImpl();
        AddressDaoImpl addressDao = new AddressDaoImpl();

        List<People> list = new ArrayList<>();

        People people1 = People.builder()
                .age(22)
                .surname("gfd")
                .name("ffd")
                .build();

        People people = People.builder()
                .age(20)
                .surname("Stepanova")
                .name("Valery")
                .build();

        peopleDao.create(people);
        peopleDao.create(people1);

        list.add(people);
        list.add(people1);

        Address address = Address.builder()
                .street("gfdggg")
                .house(43)
                .peoples(list)
                .build();
        addressDao.create(address);
        addressDao.delete(1, Address.class);

    }
}