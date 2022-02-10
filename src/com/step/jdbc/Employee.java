package com.step.jdbc;

public class Employee {

    private String name;
    private String surname;
    private String address;

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }

    public Employee(int id, String name, String surname) {
        this(name, surname);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{"+
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname'" + surname + '\'' +
                '}';
    }
}
