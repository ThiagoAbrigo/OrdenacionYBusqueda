/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Model;

import Model.Auto;
import javax.swing.table.AbstractTableModel;
import lista.Controller.Lista;

/**
 *
 * @author Home
 */
public class TableAuto extends AbstractTableModel{
    private Lista<Auto> lista = new Lista();

    public Lista<Auto> getLista() {
        return lista;
    }

    public void setLista(Lista<Auto> lista) {
        this.lista = lista;
    }
    

    @Override
    public int getRowCount() {
        return lista.tamanio();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        Auto a = lista.consultarDatoPosicion(arg0);
        switch(arg1){
            case 0: return (arg0+1);
            case 1: return a.getModelo();
            case 2: return a.getColor();
            case 3: return a.getPlaca();
            default : return null;
        
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "ID";
            case 1: return "MODELO";
            case 2: return "COLOR";
            case 3: return "PLACA";           
            default: return null;
           
                   
                   
        
        
        }
    }
    
    
}
