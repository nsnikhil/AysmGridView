package com.nsnik.nrs.aysmgridview;

/**
 * Created by Nikhil on 05-Mar-17.
 */

public class CustomObject {

    String name,surname;

    CustomObject(String name){
        this.name = name;
    }

    CustomObject(String name,String surname){
        this(name);
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
