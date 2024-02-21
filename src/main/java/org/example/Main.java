package org.example;
import org.example.dao.classes.AddressDaoImpl;
import org.example.dao.classes.PeopleDaoImpl;
import org.example.entity.People;

public class Main {
    public static void main(String[] args) {
        People people = People.builder()
                .name("Lera")
                .age(20)
                .surname("Stepanova")
                .build();

        PeopleDaoImpl peopleDao = new PeopleDaoImpl();
        //  peopleDao.create(people);
        People people1 = People.builder()
                .age(22).surname("gfd").name("ffd").build();
        // peopleDao.update(2, People.class, people1);
        //  peopleDao.delete(2, People.class);
        //  System.out.println(peopleDao.read(1, People.class));
        System.out.println(peopleDao.increaseAge());
        AddressDaoImpl addressDao = new AddressDaoImpl();
        System.out.println(addressDao.increaseHouse());
    }
}