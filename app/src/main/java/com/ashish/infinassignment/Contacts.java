package com.ashish.infinassignment;

/**
 * Created by Ashish Yadav on 27-Dec-16.
 */

public class Contacts implements Comparable<Contacts> {
    private String name;
    private  String number;
    public Contacts(String name, String number1){
        this.name = name;
        this.number = number1;

    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int compareTo(Contacts contacts) {
        return name.compareTo(contacts.name);
    }
}

