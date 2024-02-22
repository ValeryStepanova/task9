package org.example.dao.classes.utils;

import org.example.entity.Address;
import org.example.entity.People;

public class MockUtils {
    public static People buildPeople() {
        return  People.builder()
                .age(22).surname("gfd").name("ffd").build();
    }
    public  static Address buildAddress(){
        return Address.builder().house(34).street("gdfd").build();
    }
}
