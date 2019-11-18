package monopoly;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import juego_fisico.*;
import partida_virtual.*;

public class Menu {
    private Taboleiro taboleiro;
    private Dado dados;
    private Partida partida;
    private Jugador jugadorActual;
    private Jugador jugadorTurnoSiguiente;
    private ArrayList<Jugador> jugadores;
    private boolean sigueTurno;
    private boolean dadosLanzados;
    private boolean poderComprar;
    private boolean esLeerArchivo = false;
    private int contadorDobles;

    public Menu() throws InterruptedException {
        boolean seguir = true;
        this.partida = new Partida();
        this.taboleiro = new Taboleiro(this.partida);
        this.dados = new Dado();
        this.jugadores = new ArrayList<>();
        this.sigueTurno = false;
        this.contadorDobles = 0;
        this.dadosLanzados = false;
        boolean partidaEmpezada = false;
        boolean combinado = false;
        String[] comando, comando2;
        String texto;

        System.out.print("Desea leer los comandos de un archivo (si/no): ");
        comando2 = leerComando();

        //abrir archivo de los comandos
        BufferedReader buffRead = null;

        if (comando2[0].toLowerCase().equals("si")) {
            buffRead = abrirArchivo();
            this.esLeerArchivo = true;
            System.out.println("Si necesita introducir un comando en el medio de la ejecución automática, podrá hacerlo en cada stop, tecleando 'si', donde le aparecerá la entrada típica por línea de comandos.");
        }

        while (seguir) { //mirar cuando acabar la partida

            if (this.esLeerArchivo) {
                //leer comandos del archivo
                if (!combinado) {
                    comando = leerComandoArchivo(buffRead);
                } else {
                    comando = leerComando();
                    combinado = false;
                }
            } else {
                //leer comandos por consola
                System.out.print("$> ");
                comando = leerComando();
            }

            switch (comando[0].toLowerCase()) {
                case "crear":
                    if (comando.length == 4) {
                        if (comando[1].equals("jugador")) {
                            if (this.jugadores.size() < 6) {
                                if (!partidaEmpezada) {
                                    Jugador jugador = new Jugador(comando[2], comando[3], this.jugadores, this.taboleiro.getCasillaPosicion(0));
                                    this.taboleiro.getCasillaPosicion(0).setAvatar(jugador.getAvatar());
                                    System.out.println(jugador);
                                    this.jugadores.add(jugador);
                                    this.partida.anhadeJugador(jugador);
                                    this.jugadorActual = this.jugadores.get(0);
                                    this.sigueTurno = true;
                                    this.dadosLanzados = false;

                                    if (jugadores.size() >= 2) {
                                        this.jugadorTurnoSiguiente = this.jugadores.get(1);
                                    } else {
                                        this.jugadorTurnoSiguiente = this.jugadores.get(0);
                                    }
                                } else {
                                    System.out.println("No puedes crear más jugadores ya que la partida ya está empezada!");
                                }
                            } else {
                                System.out.println("El número de jugadores ya es el máximo, no puedes añadir más!");
                            }
                        }
                    } else
                        System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                    break;

                case "jugador":
                    System.out.println(this.jugadorActual);
                    break;

                case "listar":
                    if (comando.length == 2) {
                        switch (comando[1]) {
                            case "jugadores":
                                this.partida.listarJugadores();
                                break;

                            case "avatares":
                                this.partida.listarAvatares();
                                break;

                            case "enventa":
                                this.taboleiro.listarEnVenta();
                                break;
                            case "edificios":
                                this.partida.listarEdificios(this.taboleiro);
                                break;

                            default:
                                System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                                break;
                        }
                    } else if (comando.length == 3) {
                        if (comando[1].equals("edificios")) {
                            if (Integer.parseInt(comando[2]) >= 0 && Integer.parseInt(comando[2]) < 9) { // ponerle excepción
                                this.partida.describirGrupo(Integer.parseInt(comando[2]));
                            } else {
                                System.out.println("Número incorrecto, tiene que ser mayor o igual a 0 y menos que 9." +
                                        "");
                            }
                        } else {
                            System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                        }
                    } else {
                        System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                    }
                    break;

                case "lanzar":
                    if (comando.length == 2) {
                        if (comando[1].equals("dados")) {
                            if (this.jugadores.size() > 0) {
                                texto = "";
                                if (!this.dadosLanzados) {
                                    if (this.jugadorActual.getAvatar().getPenalizacion() > 2) {
                                        if (!this.jugadorActual.getEstarCarcere()) {
                                            this.dados.lanzarDados(this.jugadorActual, this.taboleiro, this);
                                            partidaEmpezada = true;
                                            if (this.dados.getIguales()) {
                                                this.dadosLanzados = false;
                                                this.sigueTurno = true;
                                                this.contadorDobles++;
                                                this.poderComprar = true;
                                                texto = " Sacastes dobles! Llevas: " + this.contadorDobles + " veces.";
                                            } else {
                                                this.dadosLanzados = true;
                                                this.sigueTurno = false;
                                                this.poderComprar = true;
                                                this.contadorDobles = 0;
                                            }
                                            if (this.contadorDobles == 3) {
                                                this.jugadorActual.irCarcere(this.taboleiro);
                                                this.dadosLanzados = true;
                                                this.sigueTurno = false;
                                                this.poderComprar = false;
                                                this.contadorDobles = 0;
                                                texto = "Sacastes tres dobles seguidos, por lo que tienes que ir a la cárcel!";
                                                this.taboleiro.getCasillaPosicion(this.jugadorActual.getAvatar().getCasilla().getPosicion()).eliminarAvatar(this.jugadorActual.getAvatar().getId());
                                                this.taboleiro.getCasillaPosicion(10).setAvatar(this.jugadorActual.getAvatar());
                                                //System.out.println(this.taboleiro);
                                                System.out.println(this.dados.textoLanzarDados(this.taboleiro, this.jugadorActual, this) + texto);
                                            } else {
                                                if ((!this.jugadorActual.getAvatar().getModoAvanzado()) || (this.jugadorActual.getAvatar().getModoAvanzado() && (this.jugadorActual.getAvatar().getTipo().equals("coche")))) {
                                                    //System.out.println(taboleiro);
                                                    System.out.println(this.dados.textoLanzarDados(this.taboleiro, this.jugadorActual, this) + texto);
                                                    this.jugadorActual.pagarAlquiler(this.jugadorActual.getAvatar().getCasilla(), this.dados.getDadoTotal(), this.taboleiro, 1);
                                                    this.jugadorActual.pagarImpuestos(this.jugadorActual.getAvatar().getCasilla(), this.taboleiro);
                                                    this.jugadorActual.cobrarParking(this.jugadorActual.getAvatar().getCasilla());
                                                }
                                            }
                                        } else {
                                            this.dados.lanzarLosDados();
                                            if (this.dados.getIguales()) {
                                                this.jugadorActual.setContadorEstarCarcere(0);
                                                System.out.println("Sacastes dobles, puedes salír de la cárcel. Lanza los dados para continuar.");
                                                this.dadosLanzados = false;
                                                this.sigueTurno = true;
                                            } else {
                                                this.jugadorActual.setContadorEstarCarcere(1);
                                                System.out.println("No sacastes dobles, llevas " + this.jugadorActual.getContadorEstarCarcere() + " intentos.");
                                                this.dadosLanzados = true;
                                                this.sigueTurno = false;
                                                if (this.jugadorActual.getContadorEstarCarcere() >= 3) {
                                                    System.out.println("Ya llevas 3 intentos, por lo que debes pagar para salír.");
                                                    if (this.jugadorActual.getFortuna() >= Valor.SAIR_CARCERE) {
                                                        this.jugadorActual.restarFortuna(Valor.SAIR_CARCERE);
                                                        this.taboleiro.getCasillaPosicion(20).sumarValor(Valor.SAIR_CARCERE);
                                                        this.jugadorActual.setContadorEstarCarcere(0);
                                                        System.out.println("Pago efectuado. Ya podrás tirar en el seguiente turno.");
                                                    } else {
                                                        System.out.println("No tienes suficiente dinero para salír de la cárcel, por lo que estás en bancarrota.");
                                                    }
                                                }
                                            }
                                        }
                                        if (this.jugadorActual.getAvatar().isModoCoche()) {
                                            this.jugadorActual.getAvatar().sumarLanzardados(this);
                                            if (this.jugadorActual.getAvatar().isCompraCoche())
                                                this.poderComprar = false;
                                        }
                                    } else {
                                        System.out.println("Estás penalizado, debes acabar turno y pasarle el turno al siguiente jugador.");
                                        this.dadosLanzados = true;
                                        this.sigueTurno = false;
                                    }
                                } else {
                                    System.out.println("Ya tiraste los dados! Para poder tirarlos el siguinte jugador antes debes acabar turno!");
                                }
                            } else {
                                System.out.println("Antes de lanzar los dados inserte al jugador! Si no sabe como hacerlo teclee: Ver comandos.");
                            }
                        } else {
                            System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                        }
                    } else {
                        System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                    }
                    taboleiro.getCarta().setTexto("");
                    break;

                case "acabar":
                    if (comando.length == 2) {
                        if (comando[1].equals("turno")) {
                            if (!this.sigueTurno) {
                                this.jugadorActual.getAvatar().setCompraCoche(false);
                                if (this.jugadorActual.getAvatar().isSubirPenalizacion()) {
                                    this.jugadorActual.getAvatar().sumarPenalizacion();
                                    if (this.jugadorActual.getAvatar().getPenalizacion() == 3) {
                                        this.jugadorActual.getAvatar().setLanzarDadosCoche(true);
                                        this.jugadorActual.getAvatar().setSubirPenalizacion(false);
                                    }
                                }
                                this.poderComprar = true;
                                calcularJugadores();
                                this.dadosLanzados = false;
                                this.sigueTurno = true;
                                if (this.jugadorActual.getAvatar().getModoAvanzado()) {
                                    System.out.println("El jugador actual es " + this.jugadorActual.getNombre() + " y está en modo avanzado de " + this.jugadorActual.getAvatar().getTipo() + ".");
                                } else {
                                    System.out.println("El jugador actual es " + this.jugadorActual.getNombre() + " y está en modo normal.");
                                }

                                if (this.jugadorActual.getEstarCarcere()) {
                                    System.out.println("Estás en la cárcel, por lo que debes tirar los dados para obtener dobles.");
                                }
                            } else {
                                System.out.println("No puedes acabar turno porque tienes que tirar los dados!");
                            }
                        } else {
                            System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                        }
                    } else
                        System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                    break;

                case "salir":
                    if (comando.length == 2) {
                        if (comando[1].equals("carcel")) {
                            if (this.jugadorActual.getEstarCarcere()) {
                                if (this.jugadorActual.getFortuna() >= Valor.SAIR_CARCERE) {
                                    this.jugadorActual.restarFortuna(Valor.SAIR_CARCERE);
                                    this.taboleiro.getCasillaPosicion(20).sumarValor(Valor.SAIR_CARCERE);
                                    this.jugadorActual.setContadorEstarCarcere(0);
                                    this.sigueTurno = true;
                                    this.dadosLanzados = false;
                                } else {
                                    System.out.println("No tienes suficiente dinero para salír de la cárcel, por lo que debes esperar a que pasen los turnos de penalización.");
                                }
                            } else {
                                System.out.println("No puedes salir de la cárcel porque ya no estás en ella.");
                            }
                        } else {
                            System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                        }
                    } else
                        System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                    break;

                case "describir":
                    if (comando.length == 3) {
                        if (comando[1].equals("jugador")) {
                            if (partida.getJugadores().containsKey(comando[2]))
                                System.out.println(this.partida.getJugadores().get(comando[2]));
                            else
                                System.out.println("Comando incorrecto. Jugador no encontrado. Para ver los comandos disponibles escriba: Ver Comandos");
                        } else if (comando[1].equals("avatar")) {
                            if (this.partida.getAvatares().containsKey(comando[2])) {
                                System.out.println(this.partida.getAvatares().get(comando[2])); //mirar en caso de que no este el avatar
                            } else
                                System.out.println("Comando incorrecto. Avatar no encontrado. Para ver los comandos disponibles escriba: Ver Comandos");
                        } else {
                            System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                        }
                    } else if (comando.length == 2) {
                        if (this.taboleiro.getCasillas().containsKey(comando[1]))
                            System.out.println(this.taboleiro.getCasillas().get(comando[1]));
                        else
                            System.out.println("Comando incorrecto. El nombre de una casilla debe introducirse tal y como aparece en el tablero pero SIN espacios. \nPara ver todos los comandos disponibles escriba: Ver Comandos");
                    } else {
                        System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                    }

                    break;

                case "comprar":
                    if (comando.length == 2) {
                        if (comando[1].equals(this.jugadorActual.getAvatar().getCasilla().getNombreSinEspacios())) {
                            if (!this.jugadorActual.getEstarCarcere()) {
                                if (this.getJugadorActual().getAvatar().getModoAvanzado() && !this.jugadorActual.getAvatar().isCompraCoche() && this.poderComprar) {
                                    this.jugadorActual.comprarCasilla(this.jugadorActual.getAvatar().getCasilla(), this.taboleiro);
                                    this.taboleiro.setContadorVueltas(0);
                                    this.jugadorActual.getAvatar().setCompraCoche(true);
                                    this.poderComprar = false;
                                } else if (this.poderComprar) {
                                    this.jugadorActual.comprarCasilla(this.jugadorActual.getAvatar().getCasilla(), this.taboleiro);
                                    this.taboleiro.setContadorVueltas(0);
                                } else if (this.jugadorActual.getAvatar().isCompraCoche()) {
                                    System.out.println("Ya has comprado en este turno, por lo que no puedes volver a comprar en este turno.");
                                } else {
                                    System.out.println("Para comprar una casilla antes debe tirar los dados.");
                                }
                            } else {
                                System.out.println("Si estás en la cárcel no puedes comprar.");
                            }
                        } else {
                            System.out.println("No puedes comprar una casilla en la que no estás. \n" +
                                    "Asegurate de que has introducido bien el nombre de la casilla (tal y como aparece en el tablero pero sin espacios).");
                        }
                    } else {
                        System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                    }
                    break;

                case "ver":
                    if (comando.length == 2) {
                        if (comando[1].equals("tablero"))
                            System.out.println(this.taboleiro);
                        else if (comando[1].toLowerCase().equals("comandos")) {
                            this.partida.listarComandos();
                        } else
                            System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                    } else
                        System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");

                    break;

                case "edificar":
                    if (comando.length == 2) {
                        if (comando[1].equals("casa")) {
                            this.jugadorActual.getAvatar().getCasilla().construirCasa(this.jugadorActual, this.taboleiro);
                        } else if (comando[1].equals("hotel")) {
                            this.jugadorActual.getAvatar().getCasilla().construirHotel(this.jugadorActual, this.taboleiro);
                        } else if (comando[1].equals("piscina")) {
                            this.jugadorActual.getAvatar().getCasilla().construirPiscina(this.jugadorActual, this.taboleiro);
                        } else if (comando[1].equals("pista")) {
                            this.jugadorActual.getAvatar().getCasilla().construirPista(this.jugadorActual, this.taboleiro);
                        }
                    } else if (comando.length == 4) {
                        if (comando[1].equals("pista") && comando[2].equals("de") && comando[3].equals("deportes")) {
                            this.jugadorActual.getAvatar().getCasilla().construirPista(this.jugadorActual, this.taboleiro);
                        }
                    }
                    break;

                case "hipotecar":
                    if (comando.length == 2) {
                        if (this.taboleiro.getCasillas().containsKey(comando[1])) {
                            this.taboleiro.getCasillas().get(comando[1]).hipotecarCasilla(this.jugadorActual, this.taboleiro);
                        } else {
                            System.out.println("Comando incorrecto. El nombre de una casilla debe introducirse tal y como aparece en el tablero pero SIN espacios." +
                                    " \nPara ver todos los comandos disponibles escriba: Ver Comandos");
                        }
                    } else {
                        System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                    }
                    break;

                case "deshipotecar":
                    if (comando.length == 2) {
                        if (this.taboleiro.getCasillas().containsKey(comando[1])) {
                            this.taboleiro.getCasillas().get(comando[1]).deshipotecarCasilla(this.jugadorActual, this.taboleiro);
                        } else {
                            System.out.println("Comando incorrecto. El nombre de una casilla debe introducirse tal y como aparece en el tablero pero SIN espacios." +
                                    " \nPara ver todos los comandos disponibles escriba: Ver Comandos");
                        }
                    } else {
                        System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                    }
                    break;

                case "vender":
                    if (comando.length == 4) {
                        int numero = Integer.parseInt(comando[3]);
                        switch (comando[1]) {
                            case "casa":
                            case "casas":
                                if (this.taboleiro.getCasillas().containsKey(comando[2])) {
                                    this.taboleiro.getCasillas().get(comando[2]).venderCasa(this.jugadorActual, taboleiro, numero);
                                }
                                break;

                            case "hotel":
                            case "hoteles":
                                if (this.taboleiro.getCasillas().containsKey(comando[2])) {
                                    this.taboleiro.getCasillas().get(comando[2]).venderHotel(this.jugadorActual, taboleiro, numero);
                                }
                                break;

                            case "piscina":
                            case "piscinas":
                                if (this.taboleiro.getCasillas().containsKey(comando[2])) {
                                    this.taboleiro.getCasillas().get(comando[2]).venderPiscina(this.jugadorActual, taboleiro, numero);
                                }
                                break;

                            case "pista":
                            case "pistas":
                                if (this.taboleiro.getCasillas().containsKey(comando[2])) {
                                    this.taboleiro.getCasillas().get(comando[2]).venderPista(this.jugadorActual, taboleiro, numero);
                                }
                                break;

                            default:
                                System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                        }
                    } else {
                        System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                    }
                    break;

                case "estadisticas":
                    if (comando.length == 1) {
                        this.partida.estadisticas(this.taboleiro);
                    } else if (comando.length == 2) {
                        if (this.partida.getJugadores().containsKey(comando[1])) {
                            this.partida.estadisticas_jugador(this.partida.getJugadores().get(comando[1]));
                        } else
                            System.out.println("Comando incorrecto. Jugador no encontrado. Para ver los comandos disponibles escriba: Ver Comandos");
                    } else {
                        System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                    }
                    break;

                case "cambiar":
                    if (comando.length == 2) {
                        if (comando[1].equals("modo")) {
                            if (!this.jugadorActual.getAvatar().getModoAvanzado()) {
                                this.jugadorActual.getAvatar().setModoAvanzado(true);
                                this.jugadorActual.getAvatar().setCompraCoche(false);
                                System.out.println("El avatar " + this.jugadorActual.getAvatar().getId() + " ahora está en modo AVANZADO de " + this.jugadorActual.getAvatar().getTipo() + ".");
                            } else {
                                this.jugadorActual.getAvatar().setModoAvanzado(false);
                                System.out.println("El avatar " + this.jugadorActual.getAvatar().getId() + " ahora está en modo NORMAL de " + this.jugadorActual.getAvatar().getTipo() + ".");

                            }

                        } else
                            System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
                    } else
                        System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");

                    break;

                case "mover":
                    if (comando.length == 2) {
                        this.taboleiro.getCasillaPosicion(this.jugadorActual.getAvatar().getCasilla().getPosicion()).eliminarAvatar(this.jugadorActual.getAvatar().getId());
                        this.jugadorActual.getAvatar().setCasilla(this.taboleiro.getCasillaPosicion(Integer.parseInt(comando[1])));
                        this.taboleiro.getCasillaPosicion(Integer.parseInt(comando[1])).setAvatar(this.jugadorActual.getAvatar());
                        System.out.println(this.taboleiro);
                    }
                    break;

                case "abandonar":
                    System.out.println("Abandonando partida...");
                    seguir = false;
                    break;

                case "stop":
                    comando2 = leerComando();
                    if (comando2[0].toLowerCase().equals("si")) {
                        combinado = true;
                        System.out.print("$> ");
                    }
                    //new Scanner(System.in).nextLine();
                    break;

                default:
                    System.out.println("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
            }
        }

    }

    public int numeroJugadores() {
        return this.jugadores.size();
    }

    public String[] leerComando() {
        String comando;
        Scanner teclado = new Scanner(System.in);
        comando = teclado.nextLine();
        return comando.split(" ");
    }

    public BufferedReader abrirArchivo() {
        BufferedReader buffRead = null;
        try {
            String directorio = "/Users/davidmohedano/Documents/USC/Segundo/Primer cuatrimestre/POO/proyecto1/src/";
            FileReader fileRead = new FileReader(directorio + "comandos.txt");
            buffRead = new BufferedReader(fileRead);
        } catch (FileNotFoundException notFound) {
            System.out.print(notFound.getMessage());
            System.exit(0);
        }
        return buffRead;
    }

    public String[] leerComandoArchivo(BufferedReader buffRead) {
        String comandoEntero = null;
        String[] comando;
        try {
            comandoEntero = buffRead.readLine();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
        if (comandoEntero != null) {
            System.out.println("$> " + comandoEntero);
            comando = comandoEntero.split(" ");
        } else {
            System.out.println("ERROR leyendo el archivo. Se acabó de leer el archivo, introduce tus comandos.");
            this.esLeerArchivo = false;
            System.out.print("$> ");
            comando = leerComando();
        }
        return comando;
    }

    public Jugador getJugadorTurnoSiguiente(Jugador jugadorActual) {
        int i = 0;
        int total = this.jugadores.size() - 1;
        for (Jugador jug : this.jugadores) {
            if (jug.getNombre().equals(jugadorActual.getNombre())) {
                if (i == total) {
                    return jugadores.get(0);
                } else {
                    i++;
                    return jugadores.get(i);
                }
            }
            i++;
        }
        return null;
    }

    public void setDadosLanzados(boolean dadosLanzados) {
        this.dadosLanzados = dadosLanzados;
    }

    public void setSigueTurno(boolean sigueTurno) {
        this.sigueTurno = sigueTurno;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void calcularJugadores() {
        this.jugadorActual = this.jugadorTurnoSiguiente;
        this.jugadorTurnoSiguiente = getJugadorTurnoSiguiente(this.jugadorActual);
        if (this.jugadorTurnoSiguiente == null) {
            System.err.println("ERROR al calcular el jugador siguinte.");
        }
    }

    public Partida getPartida() {
        return this.partida;
    }

    public int getContadorDobles() {
        return this.contadorDobles;
    }

    public void setContadorDobles(int contadorDobles) {
        this.contadorDobles = contadorDobles;
    }
}