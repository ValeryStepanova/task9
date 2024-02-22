package org.example;
import org.example.dao.classes.AddressDaoImpl;
import org.example.dao.classes.PeopleDaoImpl;
import org.example.entity.Address;
import org.example.entity.People;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PeopleDaoImpl peopleDao = new PeopleDaoImpl();
        AddressDaoImpl addressDao = new AddressDaoImpl();

        People people = People.builder()
                .name("Lera")
                .age(20)
                .surname("Stepanova")
                .build();
        Address address = Address.builder().house(34).street("gdfd").build();
        Address address1 = Address.builder().house(34).street("gdfd").build();
        addressDao.create(address1);
        addressDao.create(address);

        People people1 = People.builder()
                .age(22).surname("gfd").name("ffd").addresses(Set.of(address1, address)).build();


        peopleDao.create(people);
        peopleDao.create(people1);

        System.out.println(peopleDao.read(2, People.class));
    }
}