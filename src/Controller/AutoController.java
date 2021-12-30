/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Auto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lista.Controller.Lista;

/**
 *
 * @author Home
 */
public class AutoController<T> {
    private Class<T> clazz;
    private String CARPETA = "datos" + File.separatorChar;
    private Lista<T> lista = new Lista<>();

    public AutoController(Class<T> clazz) {
        this.clazz = clazz;
        CARPETA += this.clazz.getSimpleName().toLowerCase() + ".obj";
        lista.setClazz(clazz);
    }
    public boolean guardar(Object dato) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARPETA));
            Lista aux = listar();
            aux.insertarNodo(dato);
            oos.writeObject(aux);
            oos.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar "+e);
            e.printStackTrace();
        }
        return false;
    }
    public Lista<T> listar() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CARPETA));
            lista = (Lista<T>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            
        }
        return lista;
    }
}
