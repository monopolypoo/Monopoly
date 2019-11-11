
package partida_virtual;

import juego_fisico.Grupo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Partida {
    private HashMap<String, Jugador> jugadores;
    private HashMap<String, Avatar> avatares;
    private Jugador banca;
    private ArrayList<Jugador> turnos;
    private int turno_actual;
    private ArrayList<Grupo> grupos;

    public Partida() {
        this.jugadores = new HashMap<>();
        this.avatares = new HashMap<>();
        this.banca = new Jugador();
        this.grupos = new ArrayList<>();
    }

    public Partida(HashMap<String, Jugador> jugadores) {
        if (jugadores != null) {
            this.jugadores = jugadores;
        }
        this.avatares = new HashMap<>();
        this.grupos = new ArrayList<>();
    }

    public HashMap<String, Jugador> getJugadores() {
        return this.jugadores;
    }

    public void setJugadores(HashMap<String, Jugador> jugadores) {
        if (jugadores != null)
            this.jugadores = jugadores;
    }

    public HashMap<String, Avatar> getAvatares() {
        return avatares;
    }

    public void anhadeJugador(Jugador jugador) {
        if (jugador != null)
            this.jugadores.put(jugador.getNombre(), jugador);
        else {
            jugador = new Jugador();
            if (!jugador.getAvatar().getId().equals("banca"))
                this.avatares.put(jugador.getAvatar().getId(), jugador.getAvatar());
        }
    }

    public void listarJugadores() {
        Iterator<Jugador> jug_i = this.jugadores.values().iterator();
        while (jug_i.hasNext()) {
            Jugador jug = jug_i.next();
            System.out.println(jug.toString());
        }
    }

    public void listarAvatares() {
        Iterator<Avatar> ava_i = this.avatares.values().iterator();
        while (ava_i.hasNext()) {
            Avatar ava = ava_i.next();
            System.out.println(ava.toString());
        }
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setAvatares(HashMap<String, Avatar> avatares) {
        if (avatares != null)
            this.avatares = avatares;
    }

    public void añadirGrupo(Grupo grupo){
        if(grupo != null && this.grupos != null){
            this.grupos.add(grupo);
        }
    }

    public void listarComandos() {
        String comandos;
        comandos = "{\n\tLa lista de comandos disponibles son:\n" +
                "\t\tAbandonar partida ──> Acaba la partida en el momento deseado por los jugadores.\n" +
                "\t\tVer tablero ──> Imprime por pantalla la posición actual de los avatares de cada jugador.\n" +
                "\t\tVer comandos ──> Imprime los comandos disponibles.\n" +
                "\t\tComprar casilla ──> Para comprar la casilla en la que te encuentras debes introducir el nombre de la misma sin espacios.\n" +
                "\t\tDescribir jugador nombre ──> Describe el jugador que tiene en el campo <nombre> su nombre de jugador.\n" +
                "\t\tDescribir avatar id ──> Describe el jugador que tiene en el campo <id> su identificador.\n" +
                "\t\tDescribir casilla ──> Describe la casilla. El nombre de la casilla debe aparecer tal y como está en el tablero sin espacios.\n" +
                "\t\tSalir carcel ──> En caso de encontrarte en la cárcel y disponer de fondos suficientes se le permite al jugador abandonarla.\n" +
                "\t\tAcabar turno ──> En caso de haber lanzado los dados y no querer interactuar más con el tablero se debe acabar turno para que puede pasar el turno al siguiente jugador.\n" +
                "\t\tLanzar dados ──> Comando encargado de lanzar los dados y colocar al jugador que los lance en la posición que le tocara en los mismos.\n" +
                "\t\tListar jugadores ──> Muestra por pantalla el nombre y los atributos de todos los jugadores que están en la partida.\n" +
                "\t\tListar avatares ──> Muestra por pantalla el id y los atributos de todos los avatares que están en la partida.\n" +
                "\t\tListar enventa ──> Muestra por pantalla todas las casillas disponibles para la compra.\n" +
                "\t\tJugador ──> Muestra por pantalla la información del jugador que dispone del turno.\n" +
                "\t\tCrear jugador nombre avatar ──> Crea al jugador que poniéndole como nombre: <nombre> y como avatar: <avatar>. Los avatares disponibles son: Coche, Esfinge, Sombrero, Pelota.\n" +
                "}";
        System.out.println(comandos);
    }

    public void describirGrupo(int parseInt) {
        System.out.println(this.grupos.get(parseInt));
    }
}