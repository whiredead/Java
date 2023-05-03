package com.example.controle;

import java.io.*;
import java.util.*;

public class Serializer implements Serializable {
    public File fichier ;
    public Serializer(String path) {
        this.fichier = new File(path);
    }
    /*public void write(Manager m) throws IOException {
        Manager a;
        boolean exist = false;
        File temp = new File("temp.txt");
        FileOutputStream fileoutput = new FileOutputStream(temp);
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        out = new ObjectOutputStream(new FileOutputStream(temp));
        try {
            in = new ObjectInputStream(new FileInputStream(f));
            a = (Manager) in.readObject();
            while (a != null) {
                if (a.Id==m.Id) {
                    out.writeObject(this);
                    exist = true;
                }
                else {
                    out.writeObject(a);
                }
                a = (Manager) in.readObject();
            }
        }
        catch (EOFException e) {
            System.out.println( e.getCause());
            in.close();
        }catch (FileNotFoundException e) {
            System.out.println( e.getCause());
        }
        catch (Exception e){
            System.out.println( e.getCause());
        }
        if (!exist) {
            out.writeObject(m);
        }
        out.close();
        f.delete();
        temp.renameTo(f);
    }
*/
    public void write(Manager m) throws Exception {
        Manager a;
        boolean exist = false;
        File temp = new File("temp.txt");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(temp));;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(fichier));
            a = (Manager) in.readObject();
            while (a != null) {
                if (a.Id == m.Id) {
                    System.out.println("first if");
                    out.writeObject(m);
                    exist = true;
                }
                else {
                    out.writeObject(a);
                }
                a = (Manager) in.readObject();
            }
        } catch (FileNotFoundException e) {
        } catch (EOFException ex) {
            in.close();
        }
        if (!exist) {
            System.out.println("second if");
            out.writeObject(m);
        }
        out.close();
        fichier.delete();
        temp.renameTo(fichier);
    }

    public Set<Manager> Read() throws Exception {
         Manager manager;
         Set<Manager> managers = new HashSet<>() {};
         ObjectInputStream in = new ObjectInputStream(new FileInputStream(fichier));
         try {
             manager = (Manager) in.readObject();
             while (manager != null) {
                 managers.add(manager);
                 manager = (Manager) in.readObject();
             }
         }
         catch (EOFException exc) {
             in.close();
         }
         return managers;
     }
}
