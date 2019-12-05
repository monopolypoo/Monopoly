package Casilla;

import Carta.*;
import Consola.*;
import Monopoly.Juego;

public final class AccionCajaComunidad extends Accion {
    private Comunidad carta;

    public AccionCajaComunidad(String nombre, int posicion, String colorGrupo) {
        super(nombre, posicion, colorGrupo);
        if (posicion != 2 && posicion != 17 && posicion != 33)
            Juego.consola.imprimir("Esta casilla no pertenece a caja comunidad!");
        this.carta = new Comunidad();
    }

    public Comunidad getCarta() {
        return carta;
    }

    @Override
    public String toString() {
        return "No hay información sobre esta casilla!";
    }
}