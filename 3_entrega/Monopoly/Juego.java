package Monopoly;

import Casilla.*;
import Edificio.*;
import ExcepcionesNull.*;
import ExcepcionesNumericas.*;
import ExcepcionesPartida.*;
import Interfaces.Comandos;
import Juego_fisico.*;
import Jugador.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Juego implements Comandos {
    private Dado dado;
    private Taboleiro taboleiro;
    private ArrayList<Jugador> jugadores;
    private HashMap<String, Avatar> avatares;
    private ArrayList<Grupo> grupos;
    private Menu menu;

    public Juego(Menu menu) {
        this.dado = new Dado();
        this.taboleiro = new Taboleiro(this);
        this.jugadores = new ArrayList<>();
        this.avatares = new HashMap<>();
        this.grupos = new ArrayList<>();
        this.menu = menu;
    }

    public Dado getDado() {
        return this.dado;
    }

    public Taboleiro getTaboleiro() {
        return taboleiro;
    }

    //Los setters de dado y de taboleiro no los añadimos porque no queremos que se pueda hacer esa acción

    public ArrayList<Jugador> getJugadores() {
        return this.jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        if (jugadores != null) {
            this.jugadores = jugadores;
        }
    }

    public HashMap<String, Avatar> getAvatares() {
        return avatares;
    }

    public void anhadeJugador(Jugador jugador) {
        if (jugador != null) {
            this.jugadores.add(jugador);
            this.avatares.put(jugador.getAvatar().getId(), jugador.getAvatar());
        } else {
            jugador = new Jugador();
            if (!jugador.getAvatar().getId().equals("banca"))
                this.avatares.put(jugador.getAvatar().getId(), jugador.getAvatar());
        }
    }

    public void listarJugadores() {
        for (Jugador jug : this.jugadores) {
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

    public void listarEdificios(Taboleiro taboleiro) throws ExcepcionesNull {
        String texto = "Taboleiro non inicializado.";
        String[] aux;
        String id;
        double coste = 0;
        Iterator<Jugador> jug_i = this.jugadores.iterator();

        if (taboleiro != null) {
            texto = "";
            while (jug_i.hasNext()) {
                Jugador jug = jug_i.next();
                for (Edificio edificio : jug.getEdificaciones()) {
                    id = edificio.getId();
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
        } else throw new ExcepcionesNull("Tablero no inicializado.");
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
        Propiedad propiedad;

        for (Grupo grupo : this.grupos) {
            for (Casilla casilla : grupo.getCasillas()) {
                if (casilla instanceof Propiedad) {
                    propiedad = (Propiedad) casilla;
                    if (precio <= propiedad.getValorAlquiler()) {
                        precio = propiedad.getValorAlquiler();
                        texto = casilla.getNombreSinEspacio();
                    }
                }
            }
        }
        return texto;
    }

    public String grupoMasRentable() {
        String texto = "";
        double precio = 0, rentable = 0;
        Propiedad propiedad;

        for (Grupo grupo : this.grupos) {
            for (Casilla casilla : grupo.getCasillas()) {
                if (casilla instanceof Propiedad) {
                    propiedad = (Propiedad) casilla;
                    precio += propiedad.getValorAlquiler();
                }
            }
            if (rentable <= precio) {
                rentable = precio;
                texto = "" + grupo.getNumeroGrupo();
            }
            precio = 0;
        }

        return texto;
    }

    public void estadisticas(Taboleiro taboleiro) throws ExcepcionesNull, ExcepcionesNumericas {
        String texto = "Taboleiro non inicializado.";
        if (taboleiro != null) {
            texto = "{\n\tCasilla.Casilla más rentable: " + casillaMasRentable() +
                    "\n\tGrupo más rentable: " + grupoMasRentable() +
                    "\n\tCasilla.Casilla más frecuentada: " + casillaFrecuentada(taboleiro) +
                    "\n\tJugador más vueltas: " + jugadorMasVueltas() +
                    "\n\tJugador más veces dados: " + jugadorMasDados() +
                    "\n\tJugador en cabeza: " + jugadorCabeza() +
                    "\n}";
        } else throw new ExcepcionesNull("Tablero no inicializado.");

        System.out.println(texto);
    }

    public void estadisticas_jugador(Jugador jugador) throws ExcepcionesNull {
        String texto = "Jugador non encontrado.";
        if (jugador != null) {
            if (this.jugadores.contains(jugador)) {
                texto = "{\n\tDinero invertido: " + jugador.getDineroInvertido() +
                        "\n\tPago tasas impuestos: " + jugador.getTasasImpuestos() +
                        "\n\tPago de alquileres: " + jugador.getPagoAlquileres() +
                        "\n\tCobro de alquileres: " + jugador.getCobroAlquileres() +
                        "\n\tPasar por la casilla de salida: " + jugador.getVecesSalida() * Valor.VUELTA +
                        "\n\tPremios, inversiones o bote: " + jugador.getPremiosInversionesBote() +
                        "\n\tVeces en la cárcel: " + jugador.getVecesCarcel() +
                        "\n}";
            }
        } else throw new ExcepcionesNull("Jugador no inicializado.");
        System.out.println(texto);
    }

    public String casillaFrecuentada(Taboleiro taboleiro) throws ExcepcionesNull, ExcepcionesNumericas {
        String texto = "Taboleiro non inicializado";
        String[] datos;
        int veces = 0;

        if (taboleiro != null) {
            texto = "";
            for (Casilla casilla : taboleiro.getCasillas().values()) {
                for (Jugador jugador : this.jugadores) {
                    if (casilla.getVecesCasilla().containsKey(jugador.getAvatar().getId())) {
                        datos = casilla.getVecesCasilla().get(jugador.getAvatar().getId());
                        try {
                            if (veces < Integer.parseInt(datos[1])) {
                                veces = Integer.parseInt(datos[1]);
                                texto = casilla.getNombreSinEspacio();
                            } else if (veces == Integer.parseInt(datos[1])) {
                                texto += casilla.getNombreSinEspacio();
                            }
                        } catch (NumberFormatException exc){
                            throw new ExcepcionesNumericas("Error pasando el string a entero.");
                        }
                    }
                }
            }
        } else throw new ExcepcionesNull("Tablero no inicializado.");
        return texto;
    }

    public String jugadorCabeza() {
        String texto = "";
        float dinero = 0;

        for (Jugador jugador : this.jugadores) {
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

        for (Jugador jugador : this.jugadores) {
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

        for (Jugador jugador : this.jugadores) {
            if (veces < jugador.getVecesSalida()) {
                veces = jugador.getVecesSalida();
                texto = jugador.getNombre();
            } else if (veces == jugador.getVecesSalida()) {
                texto += jugador.getNombre();
            }
        }

        return texto;
    }

    public Jugador estaJugadorNombre(String nombre) {
        for (Jugador jugador : this.jugadores) {
            if (jugador.getNombre().equals(nombre)) {
                return jugador;
            }
        }
        return null;
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
                "\t\tComprar casilla ──> Compra la casilla en la que se encuentra el jugador tras haber lanzado los dados.\n" +
                "\t\tEdificar casa ──> En una casilla en propiedad si has caído dos veces en la misma o posees todo el grupo se te permite edificar hasta 4 casas por casilla.\n" +
                "\t\tEdificar hotel ──> En una casilla con cuatro casas se te permite intercabiarlas por un hotel.\n" +
                "\t\tEdificar piscina ──> En una casilla con un hotel y dos casas como mínimo se te permite edificar una piscina.\n" +
                "\t\tEdificar pista ──> En una casilla con dos hotel se te permite edificar una pista de deportes.\n" +
                "\t\tHipotecar casilla ──> En caso de necesitar dinero puedes hipotecar casillas en propiedad.\n" +
                "\t\tDeshipotecar casilla ──> En caso de querer volver a disponer de una casilla hipotecada puedes deshipotecarla.\n" +
                "\t\tVender casa ──> En caso de necesitar dinero puedes vender las casas de la casilla en la que te encuentras.\n" +
                "\t\tVender hotel ──> En caso de necesitar dinero puedes vender los hoteles de la casilla en la que te encuentras.\n" +
                "\t\tVender piscina ──> En caso de necesitar dinero puedes vender las piscinas de la casilla en la que te encuentras.\n" +
                "\t\tVender pista ──> En caso de necesitar dinero puedes vender las pistas de deporte de la casilla en la que te encuentras.\n" +
                "\t\tEstadisticas ──> Se muestran las estadísticas de la partida en curso.\n" +
                "\t\tEstadisticas jugador ──> Se muestran las estadísticas del jugador introducido.\n" +
                "\t\tCambiar modo ──> Se cambia el modo del que se lanzan los dados según el tipo de jugador que sea.\n" +
                "}";
        System.out.println(comandos);
    }

    public void describirGrupo(int parseInt) {
        System.out.println(this.grupos.get(parseInt));
    }

    public void CrearJugador(String[] comando) throws ExcepcionesNull, ExcepcionesComandos {
        if (comando.length == 4) {
            if (comando[1].equals("jugador")) {
                if (this.getJugadores().size() < 6) {
                    if (!this.menu.isPartidaEmpezada()) {
                        Jugador jugador = new Jugador(comando[2], comando[3], this.menu.getJuego().getJugadores(), this.getTaboleiro().getCasillaPosicion(0));
                        this.getTaboleiro().getCasillaPosicion(0).setAvatar(jugador.getAvatar());
                        System.out.println(jugador);
                        this.anhadeJugador(jugador);
                        this.menu.setJugadorActual(this.menu.getJuego().getJugadores().get(0));
                        this.menu.setSigueTurno(true);
                        this.menu.setDadosLanzados(false);

                        if (this.getJugadores().size() >= 2) {
                            this.menu.setJugadorTurnoSiguiente(this.getJugadores().get(1));
                        } else {
                            this.menu.setJugadorTurnoSiguiente(this.getJugadores().get(0));
                        }
                    } else
                        throw new ExcepcionesNull("No puedes crear más jugadores ya que la partida ya está empezada!");
                } else throw new ExcepcionesNull("El número de jugadores ya es el máximo, no puedes añadir más!");
            } else
                throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
        } else
            throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
    }

    public void Jugador() {
        System.out.println(this.menu.getJugadorActual());
    }

    public void Listar(String[] comando) throws ExcepcionesNull, ExcepcionesComandos, ExcepcionesNumericas {
        if (comando.length == 2) {
            switch (comando[1]) {
                case "jugadores":
                    this.listarJugadores();
                    break;

                case "avatares":
                    this.listarAvatares();
                    break;

                case "enventa":
                    this.getTaboleiro().listarEnVenta();
                    break;
                case "edificios":
                    this.menu.getJuego().listarEdificios(this.menu.getJuego().getTaboleiro());
                    break;

                default:
                    throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
            }
        } else if (comando.length == 3) {
            if (comando[1].equals("edificios")) {
                try {
                    if (Integer.parseInt(comando[2]) >= 0 && Integer.parseInt(comando[2]) < 9) {
                        this.describirGrupo(Integer.parseInt(comando[2]));
                    } else
                        throw new ExcepcionesNumericas("Número incorrecto, tiene que ser mayor o igual a 0 y menos que 9.");
                } catch (NumberFormatException excepcion){
                    throw new ExcepcionesNumericas("Error pasando el comando a entero.");
                }
            } else
                throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
        } else
            throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
    }

    public void LanzarDados(String[] comando) throws InterruptedException, ExcepcionesDinero, ExcepcionesNull, ExcepcionesJugando, ExcepcionesComandos, ExcepcionesNumericas {
        if (comando.length == 2) {
            if (comando[1].equals("dados")) {
                if (this.getJugadores().size() > 0) {
                    if (!this.menu.isDadosLanzados()) {
                        this.getDado().lanzarDadosAux(this.menu);
                    } else
                        throw new ExcepcionesJugando("Ya tiraste los dados! Para poder tirarlos el siguinte jugador antes debes acabar turno!");
                } else
                    throw new ExcepcionesJugando("Antes de lanzar los dados inserte al jugador! Si no sabe como hacerlo teclee: Ver comandos.");
            } else
                throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
        } else
            throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
    }

    public void AcabarTurno(String[] comando) throws ExcepcionesJugando, ExcepcionesComandos {
        if (comando.length == 2) {
            if (comando[1].equals("turno")) {
                if (!this.menu.isSigueTurno()) {
                    if (this.menu.getJugadorActual().getAvatar() instanceof Coche) {
                        ((Coche) this.menu.getJugadorActual().getAvatar()).resetCoche(this.menu);
                    }
                    this.menu.setPoderComprar(true);
                    this.menu.calcularJugadores();
                    this.menu.setDadosLanzados(false);
                    this.menu.setSigueTurno(true);
                    if (this.menu.getJugadorActual().getAvatar().isModoAvanzado()) {
                        System.out.println("El jugador actual es " + this.menu.getJugadorActual().getNombre() + " y está en modo avanzado de " + this.menu.getJugadorActual().getAvatar().getTipo() + ".");
                    } else {
                        System.out.println("El jugador actual es " + this.menu.getJugadorActual().getNombre() + " y está en modo normal.");
                    }

                    if (this.menu.getJugadorActual().getEstarCarcere())
                        throw new ExcepcionesJugando("Estás en la cárcel, por lo que debes tirar los dados para obtener dobles.");
                } else throw new ExcepcionesJugando("No puedes acabar turno porque tienes que tirar los dados!");
            } else
                throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
        } else
            throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
    }

    public void SalirCarcel(String[] comando) throws ExcepcionesDinero, ExcepcionesJugando, ExcepcionesComandos {
        if (comando.length == 2) {
            if (comando[1].equals("carcel")) {
                if (this.menu.getJugadorActual().getEstarCarcere()) {
                    this.menu.getJugadorActual().restarFortuna(Valor.SALIR_CARCEL);
                    ((Especial) this.getTaboleiro().getCasillaPosicion(20)).sumarBote(Valor.SALIR_CARCEL);
                    this.menu.getJugadorActual().setContadorEstarCarcere(0);
                    this.menu.setSigueTurno(true);
                    this.menu.setDadosLanzados(false);
                    System.out.println("Acabas de pagar " + Valor.SALIR_CARCEL + " para salir de la cárcel.");

                } else throw new ExcepcionesJugando("No puedes salir de la cárcel porque ya no estás en ella.");
            } else
                throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
        } else
            throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
    }

    public void Describir(String[] comando) throws ExcepcionesComandos {
        if (comando.length == 3) {
            if (comando[1].equals("jugador")) {
                Jugador jug = this.estaJugadorNombre(comando[2]);
                if (jug != null) {
                    System.out.println(jug);
                } else
                    throw new ExcepcionesComandos("Comando incorrecto. Jugador no encontrado. Para ver los comandos disponibles escriba: Ver Comandos");
            } else if (comando[1].equals("avatar")) {
                if (this.getAvatares().containsKey(comando[2])) {
                    System.out.println(this.getAvatares().get(comando[2]));
                } else
                    throw new ExcepcionesComandos("Comando incorrecto. Avatar no encontrado. Para ver los comandos disponibles escriba: Ver Interfaces.Comandos");
            } else
                throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
        } else if (comando.length == 2) {
            if (this.getTaboleiro().getCasillas().containsKey(comando[1]))
                System.out.println(this.getTaboleiro().getCasillas().get(comando[1]));
            else
                throw new ExcepcionesComandos("Comando incorrecto. El nombre de una casilla debe introducirse tal y como aparece en el tablero pero SIN espacios." +
                        " \nPara ver todos los comandos disponibles escriba: Ver Comandos");
        } else
            throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
    }

    public void ComprarCasilla(String[] comando) throws ExcepcionesDuenho, ExcepcionesHipotecar, ExcepcionesDinero, ExcepcionesJugando, ExcepcionesComandos {
        if (comando.length == 2) {
            if (comando[1].equals(this.menu.getJugadorActual().getAvatar().getCasilla().getNombreSinEspacios())) {
                if (!this.menu.getJugadorActual().getEstarCarcere()) {
                    if (this.menu.getJugadorActual().getAvatar().getCasilla() instanceof Propiedad) {
                        if (this.menu.getJugadorActual().getAvatar().isModoAvanzado() && (this.menu.getJugadorActual().getAvatar() instanceof Coche) &&
                                !((Coche) this.menu.getJugadorActual().getAvatar()).isCompraCoche() && this.menu.isPoderComprar()) {
                            ((Propiedad) this.menu.getJugadorActual().getAvatar().getCasilla()).comprarCasilla(this.menu.getJugadorActual(), this.getTaboleiro());
                            this.getTaboleiro().setContadorVueltas(0);
                            if (this.menu.getJugadorActual().getAvatar() instanceof Coche) {
                                ((Coche) this.menu.getJugadorActual().getAvatar()).setCompraCoche(true);
                            }
                            this.menu.setPoderComprar(false);
                        } else if (this.menu.isPoderComprar()) {
                            ((Propiedad) this.menu.getJugadorActual().getAvatar().getCasilla()).comprarCasilla(this.menu.getJugadorActual(), this.getTaboleiro());
                            this.getTaboleiro().setContadorVueltas(0);

                        } else if ((this.menu.getJugadorActual().getAvatar() instanceof Coche) && (((Coche) this.menu.getJugadorActual().getAvatar()).isCompraCoche())) {
                            throw new ExcepcionesJugando("Ya has comprado en este turno, por lo que no puedes volver a comprar en este turno.");
                        } else {
                            throw new ExcepcionesJugando("Para comprar una casilla antes debe tirar los dados.");
                        }
                    } else {
                        throw new ExcepcionesJugando("Esta casilla no se puede comprar.");
                    }
                } else {
                    throw new ExcepcionesJugando("Si estás en la cárcel no puedes comprar.");
                }
            } else {
                throw new ExcepcionesJugando("No puedes comprar una casilla en la que no estás. \n" +
                        "Asegurate de que has introducido bien el nombre de la casilla (tal y como aparece en el tablero pero sin espacios).");
            }
        } else {
            throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
        }
    }

    public void Ver(String[] comando) throws ExcepcionesComandos {
        if (comando.length == 2) {
            if (comando[1].equals("tablero"))
                System.out.println(this.getTaboleiro());
            else if (comando[1].toLowerCase().equals("comandos")) {
                this.listarComandos();
            } else
                throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
        } else
            throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
    }

    public void Edificar(String[] comando) throws ExcepcionesEdificios, ExcepcionesDuenho, ExcepcionesDinero, ExcepcionesJugando, ExcepcionesNumericas {
        if (this.menu.getJugadorActual().getAvatar().getCasilla() instanceof Solar) {
            if (comando.length == 2) {
                switch (comando[1]) {
                    case "casa":
                        ((Solar) this.menu.getJugadorActual().getAvatar().getCasilla()).edificar("casa", this.menu.getJugadorActual(), this.getTaboleiro());
                        break;
                    case "hotel":
                        ((Solar) this.menu.getJugadorActual().getAvatar().getCasilla()).edificar("hotel", this.menu.getJugadorActual(), this.getTaboleiro());
                        break;
                    case "piscina":
                        ((Solar) this.menu.getJugadorActual().getAvatar().getCasilla()).edificar("piscina", this.menu.getJugadorActual(), this.getTaboleiro());
                        break;
                    case "pista":
                        ((Solar) this.menu.getJugadorActual().getAvatar().getCasilla()).edificar("pista", this.menu.getJugadorActual(), this.getTaboleiro());
                        break;
                }
            } else if (comando.length == 4) {
                if (comando[1].equals("pista") && comando[2].equals("de") && comando[3].equals("deportes")) {
                    ((Solar) this.menu.getJugadorActual().getAvatar().getCasilla()).edificar("pista", this.menu.getJugadorActual(), this.getTaboleiro());
                }
            }
        } else {
            throw new ExcepcionesJugando("En esta casilla no se puede construir.");
        }
    }

    public void HipotecarCasilla(String[] comando) throws ExcepcionesDuenho, ExcepcionesHipotecar, ExcepcionesEdificios, ExcepcionesJugando, ExcepcionesComandos {
        if (comando.length == 2) {
            if (this.getTaboleiro().getCasillas().containsKey(comando[1])) {
                if (this.getTaboleiro().getCasillas().get(comando[1]) instanceof Propiedad) {
                    ((Propiedad) this.getTaboleiro().getCasillas().get(comando[1])).hipotecarCasilla(this.menu.getJugadorActual(), this.getTaboleiro());
                } else {
                    throw new ExcepcionesJugando("Esta casilla no se puede hipotecar.");
                }
            } else {
                throw new ExcepcionesComandos("Comando incorrecto. El nombre de una casilla debe introducirse tal y como aparece en el tablero pero SIN espacios." +
                        " \nPara ver todos los comandos disponibles escriba: Ver Comandos");
            }
        } else {
            throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
        }
    }

    public void DeshipotecarCasilla(String[] comando) throws ExcepcionesDuenho, ExcepcionesDeshipotecar, ExcepcionesHipotecar, ExcepcionesDinero, ExcepcionesJugando, ExcepcionesComandos {
        if (comando.length == 2) {
            if (this.getTaboleiro().getCasillas().containsKey(comando[1])) {
                if (this.getTaboleiro().getCasillas().get(comando[1]) instanceof Propiedad) {
                    ((Propiedad) this.getTaboleiro().getCasillas().get(comando[1])).deshipotecarCasilla(this.menu.getJugadorActual(), this.getTaboleiro());
                } else {
                    throw new ExcepcionesJugando("Esta casilla no se puede deshipotecar.");
                }
            } else {
                throw new ExcepcionesComandos("Comando incorrecto. El nombre de una casilla debe introducirse tal y como aparece en el tablero pero SIN espacios." +
                        " \nPara ver todos los comandos disponibles escriba: Ver Comandos");
            }
        } else {
            throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
        }
    }

    public void VenderEdificio(String[] comando) throws ExcepcionesEdificios, ExcepcionesDuenho, ExcepcionesNull, ExcepcionesJugando, ExcepcionesComandos, ExcepcionesNumericas {
        if (comando.length == 4) {
            int numero = 0;
            try {
                numero = Integer.parseInt(comando[3]);
            } catch (NumberFormatException exc){
                throw new ExcepcionesNumericas("Error pasando el string a entero.");
            }
            if (this.getTaboleiro().getCasillas().get(comando[2]) instanceof Solar) {
                ((Solar) this.getTaboleiro().getCasillas().get(comando[2])).venderEdificio(comando[1], this.menu.getJugadorActual(), this.getTaboleiro(), numero);
            } else {
                throw new ExcepcionesJugando("No se pueden vender edificios en esta casilla.");
            }
        } else {
            throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
        }
    }

    public void Estadisticas(String[] comando) throws ExcepcionesComandos, ExcepcionesNull, ExcepcionesNumericas {
        if (comando.length == 1) {
            this.estadisticas(this.getTaboleiro());
        } else if (comando.length == 2) {
            Jugador jug = this.estaJugadorNombre(comando[1]);
            if (jug != null) {
                this.estadisticas_jugador(jug);
            } else
                throw new ExcepcionesComandos("Comando incorrecto. Jugador no encontrado. Para ver los comandos disponibles escriba: Ver Comandos");
        } else {
            throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
        }
    }

    public void CambiarModo(String[] comando) throws ExcepcionesComandos {
        if (comando.length == 2) {
            if (comando[1].equals("modo")) {
                if (!this.menu.getJugadorActual().getAvatar().isModoAvanzado()) {
                    this.menu.getJugadorActual().getAvatar().setModoAvanzado(true);
                    if (this.menu.getJugadorActual().getAvatar() instanceof Coche) {
                        ((Coche) this.menu.getJugadorActual().getAvatar()).setCompraCoche(false);
                    }
                    System.out.println("El avatar " + this.menu.getJugadorActual().getAvatar().getId() + " ahora está en modo AVANZADO de " + this.menu.getJugadorActual().getAvatar().getTipo() + ".");
                } else {
                    this.menu.getJugadorActual().getAvatar().setModoAvanzado(false);
                    System.out.println("El avatar " + this.menu.getJugadorActual().getAvatar().getId() + " ahora está en modo NORMAL de " + this.menu.getJugadorActual().getAvatar().getTipo() + ".");

                }

            } else throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
        } else throw new ExcepcionesComandos("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
    }

    public void Mover(String[] comando) throws ExcepcionesNumericas {
        if (comando.length == 2) {
            try {
                this.getTaboleiro().getCasillaPosicion(this.menu.getJugadorActual().getAvatar().getCasilla().getPosicion()).eliminarAvatar(this.menu.getJugadorActual().getAvatar().getId());
                this.menu.getJugadorActual().getAvatar().setCasilla(this.getTaboleiro().getCasillaPosicion(Integer.parseInt(comando[1])));
                this.getTaboleiro().getCasillaPosicion(Integer.parseInt(comando[1])).setAvatar(this.menu.getJugadorActual().getAvatar());
                System.out.println(this.getTaboleiro());
            } catch (NumberFormatException exc){
                throw new ExcepcionesNumericas("Error pasando el string a entero.");
            }
        }
    }

    public void Stop(){
        String[] comando2 = this.menu.leerComando();
        if (comando2[0].toLowerCase().equals("si")) {
            this.menu.setCombinado(true);
            System.out.print("$> ");
        }
    }
}
