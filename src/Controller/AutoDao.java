/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Auto;
import lista.Controller.Lista;

/**
 *
 * @author Home
 */
public class AutoDao extends AutoController<Auto> {

    private Auto auto;

    public AutoDao() {
        super(Auto.class);

    }

    public Auto getAuto() {
        if (auto == null) {
            auto = new Auto();
        }
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public boolean guardar() {
        auto.setId(new Long(listar().tamanio() + 1));
        return guardar(auto);
    }

    public Lista<Auto> buscarPorModelo(String dato, Lista auto) {
        System.out.println(dato);
        Lista<Auto> a = new Lista();
        int central, izquierda, derecha;
        izquierda = 0;
        derecha = auto.tamanio() - 1;
        while (izquierda <= derecha) {
            central = (izquierda + derecha) / 2;
            Auto valorcentral = (Auto) auto.consultarDatoPosicion(central);
            if (valorcentral.getModelo().toLowerCase().contains(dato.toLowerCase())) {
                a.insertarNodo(valorcentral);
                return a;
            }
            if (valorcentral.getModelo().compareTo(dato) > 0) {
                derecha = central - 1;
            } else {
                izquierda = central + 1;
            }

        }
        return a;
    }
}
