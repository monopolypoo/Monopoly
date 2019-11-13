
package partida_virtual;

import juego_fisico.Casilla;
import juego_fisico.Grupo;
import juego_fisico.Taboleiro;

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

    public void listarEdificios(Taboleiro taboleiro) {
        String texto = "Taboleiro non inicializado.";
        String[] aux;
        double coste = 0;
        Iterator<Jugador> jug_i = this.jugadores.values().iterator();

        if (taboleiro != null) {
            texto = "";
            while (jug_i.hasNext()) {
                Jugador jug = jug_i.next();
                for (String id : jug.getEdificaciones()) {
                    aux = id.split("-");
                    if (aux[0].equals("casa")) {
                        coste = taboleiro.getEdificaciones().get(id).getValorCasa();
                    } else if (aux[0].equals("hotel")) {
                        coste = taboleiro.getEdificaciones().get(id).getValorHotel();
                    } else if (aux[0].equals("piscina")) {
                        coste = taboleiro.getEdificaciones().get(id).getValorPiscina();
                    } else if (aux[0].equals("pista")) {
                        coste = taboleiro.getEdificaciones().get(id).getValorPistaDeporte();
                    }
                    texto += "{\n\tid: " + id + ",\n" +
                            "\tpropietario: " + jug.getNombre() + ",\n" +
                            "\tcasilla: " + taboleiro.getEdificaciones().get(id).getNombre() + ",\n" +
                            "\tgrupo: " + taboleiro.getEdificaciones().get(id).getGrupo().getNumeroGrupo() + ",\n" +
                            "\tcoste: " + coste + "\n}\n";
                }
            }
        }
        System.out.println(texto);
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }

    public void setAvatares(HashMap<String, Avatar> avatares) {
        if (avatares != null)
            this.avatares = avatares;
    }

    public void añadirGrupo(Grupo grupo) {
        if (grupo != null && this.grupos != null) {
            this.grupos.add(grupo);
        }
    }

    public String casillaMasRentable() {
        String texto = "";
        double precio = 0;

        for (Grupo grupo : this.grupos) {
            for (Casilla casilla : grupo.getCasillas()) {
                if (precio <= casilla.getValorAlquiler()) {
                    precio = casilla.getValorAlquiler();
                    texto = casilla.getNombreSinEspacio();
                }
            }
        }
        return texto;
    }

    public String grupoMasRentable() {
        String texto = "";
        double precio = 0, rentable = 0;

        for (Grupo grupo : this.grupos) {
            for (Casilla casilla : grupo.getCasillas()) {
                precio += casilla.getValorAlquiler();
            }
            if (rentable <= precio) {
                rentable = precio;
                texto = "" + grupo.getNumeroGrupo();
            }
            precio = 0;
        }

        return texto;
    }

    public void estadisticas(Taboleiro taboleiro) {
        String texto = "Taboleiro non inicializado.";
        if (taboleiro != null) {
            texto = "{\n\tCasilla más rentable: " + casillaMasRentable() +
                    "\n\tGrupo más rentable: " + grupoMasRentable() +
                    "\n\tCasilla más frecuentada: " + casillaFrecuentada(taboleiro) +
                    "\n\tJugador más vueltas: " + jugadorMasVueltas() +
                    "\n\tJugador más veces dados: " + jugadorMasDados() +
                    "\n\tJugador en cabeza: " + jugadorCabeza() +
                    "\n}";
        }
        System.out.println(texto);
    }

    public void estadisticas_jugador(Jugador jugador) {
        String texto = "Jugador non encontrado.";
        if (jugador != null) {
            if (this.getJugadores().containsKey(jugador.getNombre())) {
                texto = "{\n\tDinero invertido: " + jugador.getDineroInvertido() +
                        "\n\tPago tasas impuestos: " + jugador.getTasasImpuestos() +
                        "\n\tPago de alquileres: " + jugador.getPagoAlquileres() +
                        "\n\tCobro de alquileres: " + jugador.getCobroAlquileres() +
                        "\n\tPasar por la casilla de salida: " + jugador.getVecesSalida() +
                        "\n\tPremios, inversiones o bote: " + jugador.getPremiosInversionesBote() +
                        "\n\tVeces en la cárcel: " + jugador.getVecesCarcel() +
                        "\n}";
            }
        }
        System.out.println(texto);
    }

    public String casillaFrecuentada(Taboleiro taboleiro) {
        String texto = "Taboleiro non inicializado";
        String[] datos;
        int veces = 0;

        if (taboleiro != null) {
            for (Casilla casilla : taboleiro.getCasillas().values()) {
                for (Jugador jugador : this.jugadores.values()) {
                    if (casilla.getVecesCasilla().containsKey(jugador.getAvatar().getId())) {
                        datos = casilla.getVecesCasilla().get(jugador.getAvatar().getId());
                        if (veces < Integer.parseInt(datos[1])) {
                            veces = Integer.parseInt(datos[1]);
                            texto = casilla.getNombreSinEspacio();
                        } else if (veces == Integer.parseInt(datos[1])) {
                            texto += casilla.getNombreSinEspacio();
                        }
                    }
                }
            }
        }
        return texto;
    }

    public String jugadorCabeza() {
        String texto = "";
        float dinero = 0;

        for (Jugador jugador : this.jugadores.values()) {
            if (dinero < jugador.getFortuna()) {
                dinero = jugador.getFortuna();
                texto = jugador.getNombre();
            } else if (dinero == jugador.getFortuna()) {
                texto += jugador.getNombre();
            }
        }

        return texto;
    }

    public String jugadorMasDados() {
        String texto = "";
        int veces = 0;

        for (Jugador jugador : this.jugadores.values()) {
            if (veces < jugador.getVecesDados()) {
                veces = jugador.getVecesDados();
                texto = jugador.getNombre();
            } else if (veces == jugador.getVecesDados()) {
                texto += jugador.getNombre();
            }
        }

        return texto;
    }

    public String jugadorMasVueltas() {
        String texto = "";
        int veces = 0;

        for (Jugador jugador : this.jugadores.values()) {
            if (veces < jugador.getVecesSalida()) {
                veces = jugador.getVecesSalida();
                texto = jugador.getNombre();
            } else if (veces == jugador.getVecesSalida()) {
                texto += jugador.getNombre();
            }
        }

        return texto;
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