package Casilla;

import Carta.*;
import Consola.*;
import Monopoly.Juego;

public final class AccionSuerte extends Accion {
    private Suerte carta;

    public AccionSuerte(String nombre, int posicion, String colorGrupo) {
        super(nombre, posicion, colorGrupo);
        if (posicion != 7 && posicion != 22 && posicion != 36) // Debería ser una excepción
            Juego.consola.imprimir("Esta casilla no pertenece a suerte!");
        this.carta = new Suerte();
    }

    public Suerte getCarta() {
        return carta;
    }

    @Override
    public String toString() {
        return "No hay información sobre esta casilla!";
    }
}
