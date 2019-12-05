package Casilla;

import Consola.*;
import Monopoly.*;

public final class Impuesto extends Casilla {
    private double valor;

    public Impuesto(String nombre, int posicion, String colorGrupo) {
        super(nombre, posicion, colorGrupo);
        if (posicion == 4)
            this.valor = Valor.VUELTA;
        else if (posicion == 38)
            this.valor = Valor.VUELTA / 2.0;
        else
            Juego.consola.imprimir("ERROR creando la casilla de impuesto.");
    }

    public double getImpuesto() {
        return this.valor;
    }

    //No se crea el setter de valor porque no vamos a querer que se modifique en ningún momento.

    @Override
    public String toString() {
        String texto = "";
        if (super.getPosicion() == 4 || super.getPosicion() == 38)
            texto = "{\n\ttipo: impuesto" + ",\n\ta pagar: " + this.valor + ",\n}";
        return texto;
    }
}
