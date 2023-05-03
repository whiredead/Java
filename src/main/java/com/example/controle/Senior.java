package com.example.controle;

public class Senior extends Manager{
    public Senior(int id, String name,int age, int hours) {
        super(id, name, age,hours);
    }

    @Override
    public double cout() {
        if(hours>2000)
            return 2500*2000 + (hours-2000)*3500;
        else if(hours<2000)
            return hours*2000;
        else return 2500*2000;
    }
}
