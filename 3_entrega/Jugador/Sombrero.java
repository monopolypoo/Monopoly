package Jugador;

import Casilla.*;
import ExcepcionesNull.ExcepcionesNull;
import ExcepcionesNumericas.ExcepcionesNumericas;
import ExcepcionesPartida.ExcepcionesDinero;
import Juego_fisico.*;
import Monopoly.*;

import java.util.ArrayList;

public final class Sombrero extends Avatar {
    private int vecesDados;
    private ArrayList<String> casillas;

    public Sombrero() {
        super();
        this.vecesDados = 0;
        this.casillas = new ArrayList<>();
    }

    public Sombrero(Jugador jugador, ArrayList<Jugador> jugadores, Casilla casilla) {
        super(jugador, jugadores, casilla);
        this.vecesDados = 0;
        this.casillas = new ArrayList<>();
    }

    public int getVecesDados() {
        return vecesDados;
    }

    public void setVecesDados(int vecesDados) {
        if (vecesDados == 0)
            this.vecesDados = vecesDados;
    }

    public void vaciarRegistroSombrero() {
        if (this.casillas.size() > 0)
            //this.casillas.subList(0, this.casillas.size() + 1).clear(); // borrar el registro de casillas visitadas
            this.casillas.clear();
    }

    private int calcularPosSiquiente(int posActual, int posInicio) {
        int posSiguiente;
        if (posActual >= 1 && posActual <= 9)
            posSiguiente = 11;
        else if (posActual == 10)
            posSiguiente = 39;
        else if (posActual > 10 && posActual < 20)
            posSiguiente = 49 - posActual;
        else if (posActual == 20)
            posSiguiente = 10;
        else if (posActual >= 21 && posActual <= 29)
            posSiguiente = 10;
        else if (posActual == 0)
            posSiguiente = 11;
        else
            posSiguiente = 49 - posActual + 2;
        
        return posSiguiente;
    }

    private void moverSombrero(Menu menu, Dado dado, int posInicio) throws ExcepcionesDinero, ExcepcionesNumericas, ExcepcionesNull {
        int posSiguiente;
        for (int i = 0; i < dado.getDadoTotal(); i++) {
            if (!this.getJugador().getEstarCarcere()) {
                menu.getJuego().getTaboleiro().getCasillaPosicion(this.getCasilla().getPosicion()).eliminarAvatar(this.getId());
                posSiguiente = calcularPosSiquiente(this.getCasilla().getPosicion(), posInicio);
                if (posSiguiente == 0)
                    posInicio = 0;
                if (posSiguiente == 30) {
                    this.getJugador().irCarcere(menu.getJuego().getTaboleiro());
                    menu.setDadosLanzados(true);
                    menu.setSigueTurno(false);
                    menu.setPoderComprar(false);
                    menu.setContadorDobles(0);
                } else
                    moverEnBasico(menu.getJuego().getTaboleiro(), menu, posSiguiente);
                menu.getJugadorActual().pagarAlquiler(menu.getJugadorActual().getAvatar().getCasilla(), menu.getJuego().getDado().getDadoTotal());
                menu.getJugadorActual().pagarImpuestos(menu.getJugadorActual().getAvatar().getCasilla(), menu.getJuego().getTaboleiro());
                menu.getJugadorActual().cobrarParking(menu.getJugadorActual().getAvatar().getCasilla());
                this.casillas.add(this.getCasilla().getNombreSinEspacios());
            }
        }
    }

    @Override
    public String getTipo() {
        return "sombrero";
    }

    @Override
    public void moverEnAvanzado(Taboleiro taboleiro, Menu menu, Dado dado) throws ExcepcionesDinero, ExcepcionesNumericas, ExcepcionesNull {
        int posInicio = this.getCasilla().getPosicion();
        Juego.consola.imprimir("El dado tiene el valor de: " + dado.getDadoTotal() + ".");
        if (dado.getDadoTotal() > 4) {
            menu.getJuego().getDado().setDadoTotal(menu.getJuego().getDado().getDadoTotal() - 1);
            //dado.setDadoTotal(dado.getDadoTotal() - 1);
            if (super.getCasilla().getPosicion() <= 39 && super.getCasilla().getPosicion() > 30 /*|| super.getCasilla().getPosicion() == 0*/) {
                moverEnBasico(taboleiro, menu, 40);
                this.casillas.add(taboleiro.getCasillaPosicion(1).getNombreSinEspacios());
                // dado.setDadoTotal(dado.getDadoTotal() - 1);
                moverSombrero(menu, dado, posInicio);
            } else if (super.getCasilla().getPosicion() > 10 && super.getCasilla().getPosicion() < 20) {
                moverEnBasico(taboleiro, menu, 21);
                this.casillas.add(taboleiro.getCasillaPosicion(21).getNombreSinEspacios());
                moverSombrero(menu, dado, posInicio);
            } else {
                moverEnBasico(taboleiro, menu, calcularPosSiquiente(this.getCasilla().getPosicion(), this.getCasilla().getPosicion()));
                moverSombrero(menu, dado, posInicio);
            }
            this.vecesDados++;
        } else {
            if (!this.casillas.isEmpty()) {
                Propiedad propiedad;
                Impuesto impuesto;
                Especial especial;
                for (String nombre : this.casillas) {
                    if (taboleiro.getCasillas().get(nombre) instanceof Propiedad) {
                        propiedad = ((Propiedad) taboleiro.getCasillas().get(nombre));
                        if (!propiedad.getDuenho().equals(this.getJugador())) {
                            propiedad.getDuenho().restarFortuna((float) propiedad.getAlquiler());
                            this.getJugador().sumarFortuna((float) propiedad.getAlquiler());
                        }
                    } else if (taboleiro.getCasillas().get(nombre) instanceof Impuesto) {
                        impuesto = ((Impuesto) taboleiro.getCasillas().get(nombre));
                        this.getJugador().sumarFortuna((float) impuesto.getImpuesto());
                        ((Especial) taboleiro.getCasillaPosicion(20)).devolverBote(impuesto.getImpuesto());
                    } else if (taboleiro.getCasillas().get(nombre) instanceof Especial) {
                        especial = ((Especial) taboleiro.getCasillas().get(nombre));
                        if (especial.getNombreSinEspacio().equals("Salida")) {
                            this.getJugador().restarFortuna(Valor.VUELTA);
                        }
                    }
                }
                vaciarRegistroSombrero();
            }
            menu.setDadosLanzados(true);
            menu.setSigueTurno(false);
            menu.setPoderComprar(false);
            menu.setContadorDobles(0);
            this.vecesDados = 4; // num mayor que 3 para que no deje volver a tirar los dados
            moverEnBasico(menu.getJuego().getTaboleiro(), menu, posInicio);
            // Juego.consola.imprimir(taboleiro.toString());
        }
    }

    @Override
    public String toString() {
        String texto = "{\n" +
                "\tid: " + super.getId() + "\n" +
                "\ttipo: sombrero" + "\n" +
                "\tcasilla: " + super.getCasilla().getNombre() + "\n" +
                "\tjugador: " + super.getJugador().getNombre() + "\n}";

        return texto;
    }
}