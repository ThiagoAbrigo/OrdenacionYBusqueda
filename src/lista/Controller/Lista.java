/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.Controller;

import Model.Auto;
import java.io.Serializable;
import java.lang.reflect.Field;
import lista.Model.Nodo;

/**
 *
 * @author Home
 * @param <T>
 */
public class Lista<T> implements Serializable {

    private Nodo cabecera;
    private Class clazz;

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public boolean estaVacia() {
        return this.cabecera == null;
    }

    /**
     * Permite insertar dato en la lista
     *
     * @param dato El dato a ingresar
     */
    private void insertar(T dato) {
        Nodo nodo = new Nodo(dato, cabecera);
        cabecera = nodo;
    }

    private void insertarFinal(T dato) {
        insertar(dato, tamanio());
    }

    /**
     * Insertar un dato por posicion
     *
     * @param dato el dato a insertar
     * @param pos la posicion a insertar
     */
    public void insertar(T dato, int pos) {
        if (estaVacia() || pos <= 0) {
            insertar(dato);
        } else {
            Nodo iterador = cabecera;
            for (int i = 0; i < pos - 1; i++) {
                if (iterador.getNodoSiguiente() == null) {
                    break;
                }
                iterador = iterador.getNodoSiguiente();
            }
            Nodo tmp = new Nodo(dato, iterador.getNodoSiguiente());
            iterador.setNodoSiguiente(tmp);
        }
    }

    /**
     * Agregar item a la lista ascendente, quiere decir que el primer elemento
     * es la cabecera
     *
     * @param dato el dato a agregar
     */
    public void insertarNodo(T dato) {
        if (tamanio() > 0) {
            insertarFinal(dato);
        } else {
            insertar(dato);
        }
    }

    /**
     * *
     * Retorna el tamanio de la Lista
     *
     * @return numero de elemento
     */
    public int tamanio() {
        int cont = 0;
        Nodo tmp = cabecera;
        while (!estaVacia() && tmp != null) {
            cont++;
            tmp = tmp.getNodoSiguiente();
        }
        return cont;
    }

    /**
     * Permite extraer el primer dato de la lista
     *
     * @return El dato
     */
    public T extraer() {
        T dato = null;
        if (!estaVacia()) {
            dato = (T) cabecera.getDato();
            cabecera = cabecera.getNodoSiguiente();
        }
        return dato;
    }

    /**
     * Permite consultar un dato de la lista por posicion
     *
     * @param pos posicion en la lista
     * @return dato encontrado en la posicion
     */
    public T consultarDatoPosicion(int pos) {
        T dato = null;
        if (!estaVacia() && (pos >= 0 && pos <= tamanio() - 1)) {
            Nodo tmp = cabecera;
            for (int i = 0; i < pos; i++) {
                tmp = tmp.getNodoSiguiente();
                if (tmp == null) {
                    break;
                }
            }
            if (tmp != null) {
                dato = (T) tmp.getDato();
            }
        }
        return dato;
    }

    /**
     *
     */
    public void imprimir() {
        Nodo tmp = cabecera;
        while (!estaVacia() && tmp != null) {
            System.out.println(tmp.getDato());
            tmp = tmp.getNodoSiguiente();
        }
    }

    public boolean modificarPorPos(T dato, int pos) {
        if (!estaVacia() && pos <= tamanio() - 1 && pos >= 0) {
            Nodo iterador = cabecera;
            for (int i = 0; i < pos; i++) {
                iterador = iterador.getNodoSiguiente();
                if (iterador == null) {
                    break;
                }
            }
            if (iterador != null) {
                iterador.setDato(dato);
                return true;
            }
        }
        return false;
    }

    private Field getField(String nombre) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(nombre)) {
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }

    private Object value(T dato, String atributo) throws Exception {
        return getField(atributo).get(dato);
    }

    public Lista<Auto> ordenarQuicksort3(Lista<Auto> auto, int primero, int ultimo) {
        int i, j, central;
        Auto pivote = null;
        central = (primero + ultimo) / 2;
        pivote = (Auto) auto.consultarDatoPosicion(central);
        i = primero;
        j = ultimo;
        do {

            while (auto.consultarDatoPosicion(i).getModelo().compareTo(pivote.getModelo()) < 0) {
                i++;
            }
            while (pivote.getModelo().compareTo(auto.consultarDatoPosicion(j).getModelo()) < 0) {
                j--;
            }
            if (i <= j) {
                Auto aux = auto.consultarDatoPosicion(i);
                auto.modificarPorPos(auto.consultarDatoPosicion(j), i);
                auto.modificarPorPos(aux, j);
                i++;
                j--;
            }
        } while (i <= j);
        if (primero < j) {
            ordenarQuicksort3(auto, primero, j);
        }
        if (i < ultimo) {
            ordenarQuicksort3(auto, i, ultimo);
        }
        return auto;
    }

    public Lista<T> Ordenar_Shell(String atributo, Lista auto) {
        //Lista<T> auto = this;
        try {
            int i, salto;
            int n = tamanio() / 2;
            T aux;
            boolean cambio;
            for (salto = n; salto != 0; salto /= 2) {
                cambio = true;
                while (cambio) {
                    cambio = false;
                    for (i = salto; i < tamanio(); i++) {
                        Object datoT = value(consultarDatoPosicion(i - salto), atributo);
                        Object datoJ = value(consultarDatoPosicion(i), atributo);
                            if (datoT.toString().compareTo(datoJ.toString()) > 0) {
                                aux = consultarDatoPosicion(i);
                                modificarPorPos(consultarDatoPosicion(i - salto), i);
                                modificarPorPos(aux, i - salto);
                                cambio = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
//    public Lista<Auto> buscarPorModelo(String dato) {
//        System.out.println(dato);
//        Lista<Auto> auto = new Lista();
//        Lista<Auto> aux = listar();
//        int central, izquierda, derecha;
//        izquierda = 0;
//        derecha = auto.tamanio() - 1;
//        while (izquierda <= derecha) {
//            central = (izquierda + derecha) / 2;
//            Auto valorcentral = aux.consultarDatoPosicion(central);
//            if (valorcentral.getModelo().toLowerCase().contains(dato.toLowerCase())) {
//                auto.insertarNodo(valorcentral);
//            }
//            if (valorcentral.getModelo().compareTo(dato) < 0) {
//                derecha = central - 1;
//            } else {
//                izquierda = central + 1;
//            }
//
//        }
//        return auto;
//    }
}
