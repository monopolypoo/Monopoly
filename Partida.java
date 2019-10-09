import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Partida {
    private HashMap<String, Jugador> jugadores;
    private HashMap<String, Avatar> avatares;
    private Jugador banca;
    private ArrayList<Jugador> turnos;
    private int turno_actual;

    public Partida(){
        this.jugadores = new HashMap<>();
        this.avatares = new HashMap<>();
        this.banca = new Jugador();
        //faltan cosas
    }

    public Partida(HashMap<String, Jugador> jugadores){
        this.jugadores = jugadores;
        this.avatares = new HashMap<>();
        //faltan cosas
    }

    public HashMap<String, Jugador> getJugadores(){
        return this.jugadores;
    }

    public void setJugadores(HashMap<String, Jugador> jugadores){
        if (jugadores != null)
            this.jugadores = jugadores;
    }

    public HashMap<String, Avatar> getAvatares() {
        return avatares;
    }

    public void anhadeJugador(Jugador jugador){
        if (jugador != null)
            this.jugadores.put(jugador.getNombre(), jugador);
        if (jugador.getAvatar() != null)
            this.avatares.put(jugador.getAvatar().getId(), jugador.getAvatar());
    }

    public void listarJugadores(){
        Iterator<Jugador> jug_i = this.jugadores.values().iterator();
        while(jug_i.hasNext()){
            Jugador jug = jug_i.next();
            System.out.println(jug.toString());
        }
    }

    public void listarAvatares(){
        Iterator<Avatar> ava_i = this.avatares.values().iterator();
        while(ava_i.hasNext()){
            Avatar ava = ava_i.next();
            System.out.println(ava.toString());
        }
    }
}
