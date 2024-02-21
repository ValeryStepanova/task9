package org.example;
import org.example.dao.classes.AddressDaoImpl;
import org.example.dao.classes.PeopleDaoImpl;
import org.example.entity.Address;
import org.example.entity.People;

public class Main {
    public static void main(String[] args) {
        PeopleDaoImpl peopleDao = new PeopleDaoImpl();
        AddressDaoImpl addressDao = new AddressDaoImpl();

        People people = People.builder()
                .name("Lera")
                .age(20)
                .surname("Stepanova")
                .build();
        People people1 = People.builder()
                .age(22).surname("gfd").name("ffd").build();
        People people2 = People.builder()
                .age(30).surname("аииии").name("ааа").build();
        People people3 = People.builder()
                .age(20).surname("парв").name("ffd").build();
        People people4 = People.builder()
                .age(22).surname("Дукф").name("пап").build();

        peopleDao.create(people);
        peopleDao.create(people1);
        peopleDao.create(people2);
        peopleDao.create(people3);
        peopleDao.create(people4);

        peopleDao.delete(5, People.class);
        peopleDao.delete(4, People.class);

        Address address = Address.builder().house(34).street("gdfd").build();
        Address address1 = Address.builder().house(34).street("gdfd").build();

        addressDao.create(address1);
        addressDao.create(address);

        System.out.println(peopleDao.increaseAge());
        System.out.println(addressDao.increaseHouse());
    }
}