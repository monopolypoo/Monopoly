package partida_virtual;

import juego_fisico.Casilla;
import juego_fisico.Taboleiro;
import monopoly.Menu;

import java.util.ArrayList;
import java.util.Random;

public class Avatar {
    private String id;
    private String tipo;
    private Jugador jugador;
    private Casilla casilla;
    private boolean modoAvanzado;
    private boolean lanzarDadosCoche;
    private boolean compraCoche;
    private boolean isSubirPenalizacion;
    private int contadorCoche;
    private int penalizacion;

    public Avatar() {
        this.id = "banca";
        this.modoAvanzado = false;
        this.lanzarDadosCoche = false;
    }

    public Avatar(String tipo, Jugador jugador, ArrayList<Jugador> jugadores, Casilla casilla) {
        this.modoAvanzado = false;
        tipo = tipo.toLowerCase();
        if (tipo.equals("sombrero") || tipo.equals("esfinge") || tipo.equals("coche") || tipo.equals("pelota"))
            this.tipo = tipo;

        else
            this.tipo = "coche";
        if (jugador == null) {
            System.err.println("Error: jugador no inicializado.");
            System.exit(1);
        }
        this.jugador = jugador;
        this.generarId(jugadores);
        this.casilla = casilla;
        this.penalizacion = 5; // valor mayor que 2
    }

    public int getPenalizacion() {
        return penalizacion;
    }

    public void sumarPenalizacion() {
        ++this.penalizacion;
    }

    public void setPenalizacion(int penalizacion) {
        if (penalizacion == 0)
            this.penalizacion = penalizacion;
    }

    public int getContadorCoche() {
        return contadorCoche;
    }

    public void sumarContadorCoche(){
        ++this.contadorCoche;
    }

    public void setContadorCoche(int contadorCoche){
        if (contadorCoche == 0){
            this.contadorCoche = 0;
        }
    }

    public boolean isSubirPenalizacion() {
        return isSubirPenalizacion;
    }

    public void setSubirPenalizacion(boolean subirPenalizacion) {
        this.isSubirPenalizacion = subirPenalizacion;
    }

    public boolean isCompraCoche() {
        return compraCoche;
    }

    public void setCompraCoche(boolean compraCoche) {
        this.compraCoche = compraCoche;
    }

    public boolean getModoAvanzado() {
        return modoAvanzado;
    }

    public void setModoAvanzado(boolean modoAvanzado) {
        this.modoAvanzado = modoAvanzado;
    }

    public boolean isLanzarDados() {
        return lanzarDadosCoche;
    }

    public void setLanzarDadosCoche(boolean lanzarDadosCoche) {
        this.lanzarDadosCoche = lanzarDadosCoche;
    }

    public void sumarLanzardados(Menu menu) {
        if (this.contadorCoche < 3 && this.lanzarDadosCoche) {
            menu.setDadosLanzados(false);
            menu.setSigueTurno(true);
            System.out.println("El jugador " + menu.getJugadorActual().getNombre() + " mantiene el turno.");

            if (menu.getJugadorActual().getEstarCarcere()) {
                System.out.println("Estás en la cárcel, por lo que debes tirar los dados para obtener dobles.");
            }
            this.sumarContadorCoche();
        } else {
            System.out.println("Ya tiraste los dados el número máximo en tu modo avanzado");
            menu.setDadosLanzados(true);
            menu.setSigueTurno(false);
            this.lanzarDadosCoche = false;
            this.contadorCoche = 0;
        }
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

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getJugador() {
        return jugador.getNombre();
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        if (casilla != null) {
            this.casilla = casilla;
        }
    }

    //no definimos setId(), ni setTipo(), ni setJugador() porque no permitimos modificar un avatar una vez creado

    @Override
    public String toString() {
        String texto = "{\n" +
                "\tid: " + this.id + "\n" +
                "\ttipo: " + this.tipo + "\n" +
                "\tcasilla: " + this.casilla.getNombre() + "\n" +
                "\tjugador: " + this.jugador.getNombre() + "\n}";

        return texto;
    }
}
