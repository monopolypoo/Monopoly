package Carta;

import Juego_fisico.*;
import Jugador.*;
import Monopoly.*;


import java.util.ArrayList;

public abstract class Carta {
    private int tipo;
    private String texto;

    public Carta() {
        this.texto = "";
        this.tipo = 0;
    }

    public int getTipo() {
        return tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        if (texto != null)
            this.texto = texto;
    }

    public void anhadirTexto(String texto) {
        if (texto != null)
            this.texto += texto;
    }

    public boolean estaNumero(int numero, ArrayList<Integer> numeros){
        for (Integer num: numeros){
            if (num == numero)
                return true;
        }
        return false;
    }

    public abstract void accion(Jugador jugador, Taboleiro taboleiro, Menu menu, int escogida);

    public abstract int barajar(int escogida);
}
