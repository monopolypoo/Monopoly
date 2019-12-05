package Casilla;

import Carta.*;
import Consola.*;

public final class AccionCajaComunidad extends Accion {
    private Comunidad carta;
    private Consola consola;

    public AccionCajaComunidad(String nombre, int posicion, String colorGrupo) {
        super(nombre, posicion, colorGrupo);
        this.consola = new ConsolaNormal();
        if (posicion != 2 && posicion != 17 && posicion != 33)
            this.consola.imprimir("Esta casilla no pertenece a caja comunidad!");
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