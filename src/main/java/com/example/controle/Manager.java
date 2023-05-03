package com.example.controle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public abstract class Manager implements Serializable {
    public int Id;
    public String name;
    public int age;
    public int hours;

    public Manager(int id, String name,int age, int hours) {
        Id = id;
        this.name = name;
        this.age=age;
        this.hours = hours;
    }

    public abstract double cout();

    public String toString() {
        return Id + ", " + name + ", " + this.hours + " heures , " + this.age +" ans , " +this.getClass().getName()+", "+ this.cout()+ " DH" + "\n";
    }

}
