package com.example.sharedpreferences;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String firstName;
    private int age;
    private String mail;
    private Address address;

    private ArrayList<FamilyMember> family;

    public Employee(String firstName, int age, String mail, Address address, ArrayList<FamilyMember> family){
        this.firstName = firstName;
        this.age = age;
        this.mail = mail;
        this.address = address;
        this.family = family;
    }
}
