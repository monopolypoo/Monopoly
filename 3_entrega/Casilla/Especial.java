package Casilla;

import Consola.*;
import Monopoly.*;

public final class Especial extends Casilla {
    private double valor;
    private Consola consola;

    public Especial(String nombre, int posicion) {
        super(nombre, posicion);
        this.consola = new ConsolaNormal();
        switch (posicion) {
            case 10:
                this.valor = Valor.SALIR_CARCEL;
                break;
            case 20:
            case 30:
            case 7:
            case 17:
            case 2:
            case 22:
            case 33:
            case 36:
                this.valor = 0;
                break;
            case 0:
                this.valor = Valor.VUELTA;
                break;
            default:
                consola.imprimir("ERROR creando la casilla especial.");
                break;
        }
    }

    public double getValor() {
        return this.valor;
    }

    public void sumarBote(double precio) {
        if ((precio > 0) && (super.getPosicion() == 20)) {
            this.valor += precio;
        } else {
            consola.imprimir("ERROR, el valor de esta casilla no se puede incrementar.");
        }
    }

    public double vaciarBote() {
        if (super.getPosicion() == 20) {
            double precio = this.valor;
            this.valor = 0;
            return precio;
        }
        return 0;
    }

    public void devolverBote(double valor) {
        if (valor >= 0) {
            this.valor -= valor;
        }
    }

    public String toString() {
        String texto;
        if (super.getPosicion() == 7 || super.getPosicion() == 17 || super.getPosicion() == 2 || super.getPosicion() == 22
                || super.getPosicion() == 33 || super.getPosicion() == 36 || super.getPosicion() == 30) {
            texto = "No hay información sobre esta casilla!";
        } else if (super.getPosicion() == 0) {
            texto = "{\n\ttipo: especial,\n\tvalor a cobrar al pasar: " + this.valor + ",\n}";
        } else if (super.getPosicion() == 10) {
            texto = "";
            for (String[] nombre : super.getVecesCasilla().values()) {
                texto += "[" + nombre[0] + ", " + nombre[1] + "] ";
            }
            texto = "{\n\tsalir: " + this.valor + ",\n\tjugadores: " + texto + "\n}";
        } else if (super.getPosicion() == 20) {
            texto = "[";
            for (String[] nombre : super.getVecesCasilla().values()) {
                texto += nombre[0] + " "; //mirar para ponerle la coma sin que se la ponga al ultimo tambien
            }
            texto += "]";
            texto = "{\n\tbote: " + this.valor + ",\n\tjugadores: " + texto + "\n}";
        } else {
            texto = "Esta casilla no es de tipo especial.\n";
        }
        return texto;
    }

}
