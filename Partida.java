import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Partida {
    private HashMap<String, Jugador> jugadores;
    private HashMap<String, Avatar> avatares;
    private Jugador banca; // mejor en el menu
    private ArrayList<Jugador> turnos;
    private int turno_actual;

    public Partida(){
        // Inicializar la partida
        this.jugadores = new HashMap<>();
        this.avatares = new HashMap<>();
        this.banca = new Jugador();
        this.turnos = new ArrayList<>();
        this.turno_actual = 0;
    }

    // Una parecida para añadir los jugadores
    public Partida(HashMap<String, Jugador> jugadores){
        this.jugadores = jugadores;
        this.avatares = new HashMap<>();
        this.banca = new Jugador();
        this.turnos = new ArrayList<>();
        this.turno_actual = 0;
    }

    public HashMap<String, Jugador> getJugadores() {
        return this.jugadores;
    }

    public void setJugadores(HashMap<String, Jugador> jugadores) {
        if(jugadores != null){
            this.jugadores = jugadores;
        }
    }

    public void anhadeXogador(Jugador xog){
        if(xog != null){
            this.jugadores.put(xog.getNombre(),xog);
        }
    }

    public void listarXogadores(){
        for (Jugador xog : this.jugadores.values()) {
            System.out.println(xog.toString());
        }
        /*
         Iterator<Jugador> xog_i = this.jugadores.values().iterator();
        while (xog_i.hasNext()){
            Jugador xog = xog_i.next();
            System.out.println(xog.toString());
        }
         */
    }
}
