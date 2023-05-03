package com.example.controle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Junior extends Manager{
    public Junior(int id, String name,int age, int hours) {
        super(id, name, age,hours);
    }

    @Override
    public double cout() {
        if(hours>2500)
            return 2000*2500 + (hours-2500)*3000;
        else if(hours<2500)
            return hours*1500;
        else return 2500*2000;
    }
}
