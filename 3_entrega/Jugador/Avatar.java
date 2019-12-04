package Jugador;

import Casilla.*;
import Juego_fisico.*;
import Monopoly.*;

import java.util.ArrayList;
import java.util.Random;

public abstract class Avatar {
    private String id;
    private Jugador jugador;
    private Casilla casilla;
    private boolean modoAvanzado;
    private String textoAvanzado;

    public Avatar() {
        this.id = "banca";
        this.jugador = new Jugador();
        this.casilla = null;
        this.modoAvanzado = false;
        this.textoAvanzado = null;
    }

    public Avatar(Jugador jugador, ArrayList<Jugador> jugadores, Casilla casilla) {
        if (jugador != null) {
            this.jugador = jugador;
        } else {
            this.jugador = new Jugador();
        }
        if (casilla != null) {
            this.casilla = casilla;
        } else {
            this.casilla = null;
        }
        if (jugadores != null) {
            this.generarId(jugadores);
        } else {
            this.id = "banca";
        }
        this.modoAvanzado = false;
        this.textoAvanzado = null;
    }

    public String getId() {
        return id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        if (casilla != null) {
            this.casilla = casilla;
        }
    }

    public boolean isModoAvanzado() {
        return modoAvanzado;
    }

    public void setModoAvanzado(boolean modoAvanzado) {
        this.modoAvanzado = modoAvanzado;
    }

    public String getTextoAvanzado() {
        return textoAvanzado;
    }

    public void setTextoAvanzado(String textoAvanzado) {
        if (textoAvanzado != null)
            this.textoAvanzado = textoAvanzado;
    }

    private boolean idUnico(String id, ArrayList<Jugador> jugadores) {
        for (Jugador jug : jugadores) {
            if (jug.getAvatar().id.equals(id))
                return false;
        }
        return true;
    }

    private void generarId(ArrayList<Jugador> jugadores) {
        String idAux;
        do {
            Random ale = new Random(System.currentTimeMillis()); //pone una semilla nueva de cada vez
            int numero = ale.nextInt(20) + 65; //genera un numero aleatorio entre 65 y 65+20
            idAux = ""; // Se iguala aquí si no podría tener dos letras un id
            idAux += (char) numero; //se pasa a ASCII y se convierte en String concatenandolo con ""
        } while (!idUnico(idAux, jugadores));

        this.id = idAux;
    }

    public void mover(Taboleiro taboleiro, Menu menu) throws InterruptedException {
        int posActual, posSiguiente;
        this.jugador.sumarVecesdados();

        posActual = this.jugador.getAvatar().getCasilla().getPosicion();
        taboleiro.getCasillaPosicion(posActual).eliminarAvatar(this.jugador.getAvatar().getId());

        if (this.modoAvanzado)
            this.moverEnAvanzado(taboleiro, menu, menu.getJuego().getDado());
        else {
            posSiguiente = posActual + menu.getJuego().getDado().getDadoTotal();
            this.moverEnBasico(taboleiro, menu, posSiguiente);
        }
    }

    public void moverEnBasico(Taboleiro taboleiro, Menu menu, int posSiguiente) {
        Casilla casillaSiguiente;
        int escogida;
        String[] leer;
        String texto = "";
        if (posSiguiente > 39) {
            this.jugador.sumarFortuna(Valor.VUELTA);
            this.jugador.sumarVecesSalida();
            texto = "Has pasado por la casilla de salida, cobras: " + Valor.VUELTA + "€.";
            if (taboleiro.getCasillaPosicion(0).isSubirPrecio()) {
                taboleiro.subirPrecios();
            }
            taboleiro.subirPreciosTotal(menu.getJuego());
        }
        if (posSiguiente == 30) {
            this.jugador.irCarcere(taboleiro);
            texto += "Caíste en la casilla Ir Cárcel, por lo que ahora estás en la cárcel.";
            taboleiro.getCasillaPosicion(10).setAvatar(jugador.getAvatar());
            this.jugador.sumarVecesCarcel();
            taboleiro.setContadorVueltas(0);
            System.out.println(taboleiro + texto);
        } else {
            posSiguiente = posSiguiente % 40;
            casillaSiguiente = taboleiro.getCasillaPosicion(posSiguiente);
            this.jugador.getAvatar().setCasilla(casillaSiguiente);
            taboleiro.getCasillaPosicion(posSiguiente).setAvatar(this.jugador.getAvatar());
            if (posSiguiente != 10 && posSiguiente != 20 && posSiguiente != 0) {
                taboleiro.getCasillaPosicion(posSiguiente).setVecesCasilla(this.jugador);
            }

            System.out.println(taboleiro + texto);

            if (casillaSiguiente instanceof AccionSuerte) {
                menu.getJuego().getDado().setPosSiguiente(posSiguiente);
                System.out.print(texto + menu.getJuego().getDado().textoLanzarDados(taboleiro, this.jugador, menu) + "\n" +
                        jugador.getNombre() + ", elige una carta de suerte (1-13): ");
                leer = menu.leerComando();                /*** Aquí va una excepción ****/
                escogida = Integer.parseInt(leer[0]);
                ((AccionSuerte) casillaSiguiente).getCarta().accion(this.jugador, taboleiro, menu, escogida);
                System.out.println("Acción: " + ((AccionSuerte) taboleiro.getCasillaPosicion(posSiguiente)).getCarta().getTexto());
            } else if (casillaSiguiente instanceof AccionCajaComunidad) {
                menu.getJuego().getDado().setPosSiguiente(posSiguiente);
                System.out.print(texto + menu.getJuego().getDado().textoLanzarDados(taboleiro, this.jugador, menu) + "\n" +
                         jugador.getNombre() + ", elige una carta de caja de comunidad (1-10): ");
                leer = menu.leerComando();                /*** Aquí va una excepción ****/
                escogida = Integer.parseInt(leer[0]);
                ((AccionCajaComunidad) casillaSiguiente).getCarta().accion(this.jugador, taboleiro, menu, escogida);
                System.out.println("Acción: " + ((AccionCajaComunidad) taboleiro.getCasillaPosicion(posSiguiente)).getCarta().getTexto());
            }
        }
        menu.getJuego().getDado().setPosSiguiente(posSiguiente);
    }

    public abstract String getTipo();

    public abstract void moverEnAvanzado(Taboleiro taboleiro, Menu menu, Dado dado);

    @Override
    public abstract String toString();
}
