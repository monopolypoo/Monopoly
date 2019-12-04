package Carta;

import ExcepcionesNull.ExcepcionesNull;
import ExcepcionesNumericas.ExcepcionesNumericas;
import ExcepcionesPartida.ExcepcionesDinero;
import Juego_fisico.*;
import Jugador.*;
import Monopoly.*;


import java.util.ArrayList;

public abstract class Carta {
    private int tipo;
    private String texto;
    private boolean movimiento;

    public Carta() {
        this.texto = "";
        this.tipo = 0;
        this.movimiento = false;
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

    public boolean isMovimiento(){
        return this.movimiento;
    }

    public void setMovimiento(boolean movimiento) {
        this.movimiento = movimiento;
    }

    public abstract void accion(Jugador jugador, Taboleiro taboleiro, Menu menu, int escogida) throws ExcepcionesDinero, ExcepcionesNull, ExcepcionesNumericas;

    public abstract int barajar(int escogida);
}
