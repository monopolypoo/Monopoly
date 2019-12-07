package Trato;

import Jugador.Jugador;

public abstract class Tratos {
    private String id;
    private Jugador jugadorOrigen;
    private Jugador jugadorDestino;
    private boolean aceptado;


    public Tratos(){
        this.id = null;
        this.jugadorOrigen = null;
        this.jugadorDestino = null;
        this.aceptado = false;
    }

    public Tratos(String id, Jugador jugadorOrigen, Jugador jugadorDestino){
        if ((id != null) && (jugadorOrigen != null) && (jugadorDestino != null)) {
            this.id = id;
            this.jugadorOrigen = jugadorOrigen;
            this.jugadorDestino = jugadorDestino;
            this.aceptado = false;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Jugador getJugadorOrigen() {
        return jugadorOrigen;
    }

    public Jugador getJugadorDestino() {
        return jugadorDestino;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }

    public abstract String textoDisponible();

    public abstract String toString();
}
