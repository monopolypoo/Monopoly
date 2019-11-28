import java.util.ArrayList;
import java.util.Random;

public abstract class Avatar {
    private String id;
    private Jugador jugador;
    private Casilla casilla;
    private boolean modoAvanzado;

    public Avatar() {
        this.id = "banca";
        this.jugador = new Jugador();
        this.casilla = null;
        this.modoAvanzado = false;
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

    public abstract void moverEnBasico();
    public abstract void moverEnAvanzado();

    @Override
    public abstract String toString();
}
