import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
        this.taboleiro = new Taboleiro();
        this.dados = new Dado();
        this.partida = new Partida();
        this.jugadores = new ArrayList<>();
        this.sigueTurno = false;
        this.contadorDobles = 0;
        this.dadosLanzados = false;
        boolean partidaEmpezada = false;
        String[] comando;
        String texto;

        System.out.print("Desea leer los comandos de un archivo (si/no): ");
        comando = leerComando();

        //abrir archivo de los comandos
        BufferedReader buffRead = abrirArchivo();

        if (comando[0].toLowerCase().equals("si")){
            this.esLeerArchivo = true;
        }

        while (seguir) { //mirar cuando acabar la partida
            System.out.print("$> ");

            if (this.esLeerArchivo){
                //leer comandos del archivo
                comando = leerComandoArchivo(buffRead);
            } else {
                //leer comandos por consola
                comando = leerComando();
            }

            switch (comando[0]) {
                case "crear":
                    if (comando.length == 4) {
                        if (comando[1].equals("jugador")) {
                            if (jugadores.size() < 6) {
                                if (!partidaEmpezada) {
                                    Jugador jugador = new Jugador(comando[2], comando[3], jugadores, taboleiro.getCasillaPosicion(0));
                                    taboleiro.getCasillaPosicion(0).setAvatar(jugador.getAvatar().getId(), jugador.getAvatar());
                                    System.out.println(jugador);
                                    jugadores.add(jugador);
                                    partida.anhadeJugador(jugador);
                                    jugadorActual = jugadores.get(0);
                                    this.sigueTurno = true;
                                    this.dadosLanzados = false;

                                    if (jugadores.size() >= 2) {
                                        jugadorTurnoSiguiente = jugadores.get(1);
                                    } else {
                                        jugadorTurnoSiguiente = jugadores.get(0);
                                    }
                                } else{
                                    System.out.println("No puedes crear más jugadores ya que la partida ya está empezada!");
                                }
                            } else {
                                System.out.println("El número de jugadores ya es el máximo, no puedes añadir más!");
                            }
                        }
                    } else
                        System.out.println("Comando incorrecto.");
                    break;

                case "jugador":
                    System.out.println(jugadorActual);
                    break;

                case "listar":
                    if (comando.length == 2) {
                        switch (comando[1]) {
                            case "jugadores":
                                partida.listarJugadores();
                                break;

                            case "avatares":
                                partida.listarAvatares();
                                break;

                            case "enventa":
                                taboleiro.listarEnVenta();
                                break;

                            default:
                                System.out.println("Comando incorrecto.");
                                break;
                        }
                    } else {
                        System.out.println("Comando incorrecto.");
                    }
                    break;

                case "lanzar":
                    if (comando.length == 2) {
                        if (comando[1].equals("dados")) {
                            if (jugadores.size() > 0) {
                                texto = "";
                                if (!this.dadosLanzados) {
                                    if (!this.jugadorActual.getEstarCarcere()) {
                                        dados.lanzarDados(jugadorActual, taboleiro, this);
                                        partidaEmpezada = true;
                                        if (dados.getIguales()) {
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
                                            this.jugadorActual.irCarcere(taboleiro);
                                            this.dadosLanzados = true;
                                            this.sigueTurno = false;
                                            this.poderComprar = false;
                                            this.contadorDobles = 0;
                                            texto = "Sacastes tres dobles seguidos, por lo que tienes que ir a la cárcel!";
                                            taboleiro.getCasillaPosicion(jugadorActual.getAvatar().getCasilla().getPosicion()).eliminarAvatar(jugadorActual.getAvatar().getId());
                                            taboleiro.getCasillaPosicion(10).setAvatar(jugadorActual.getAvatar().getId(), jugadorActual.getAvatar());
                                            System.out.println(taboleiro);
                                            System.out.println("El avatar " + jugadorActual.getAvatar().getId() + dados.textoLanzarDados(taboleiro) + texto);
                                        } else {
                                            System.out.println(taboleiro);
                                            System.out.println("El avatar " + jugadorActual.getAvatar().getId() + dados.textoLanzarDados(taboleiro) + texto);
                                            this.jugadorActual.pagarAlquiler(this.jugadorActual.getAvatar().getCasilla(), dados.getDadoTotal(), taboleiro);
                                            this.jugadorActual.pagarImpuestos(this.jugadorActual.getAvatar().getCasilla(), this.taboleiro);
                                            this.jugadorActual.cobrarParking(this.jugadorActual.getAvatar().getCasilla());
                                        }
                                    } else {
                                        dados.lanzarLosDados();
                                        if (dados.getIguales()) {
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
                                                    this.jugadorActual.setContadorEstarCarcere(0);
                                                    System.out.println("Pago efectuado. Ya podrás tirar en el seguiente turno.");
                                                } else {
                                                    System.out.println("No tienes suficiente dinero para salír de la cárcel, por lo que estás en bancarrota.");
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("Ya tirastes los dados! Para poder tirarlos el siguinte jugador antes debes acabar turno!");
                                }
                            } else {
                                System.out.println("Antes de lanzar los dados inserte al jugador!");
                            }
                        } else {
                            System.out.println("Comando incorrecto.");
                        }
                    } else {
                        System.out.println("Comando incorrecto.");
                    }

                    break;

                case "acabar":
                    if (comando.length == 2) {
                        if (comando[1].equals("turno")) {
                            if (!this.sigueTurno) {
                                calcularJugadores();
                                this.dadosLanzados = false;
                                this.sigueTurno = true;
                                System.out.println("El jugador actual es " + this.jugadorActual.getNombre() + ".");

                                if (this.jugadorActual.getEstarCarcere()) {
                                    System.out.println("Estás en la cárcel, por lo que debes tirar los dados para obtener dobles.");
                                }
                            } else {
                                System.out.println("No puedes acabar turno porque tienes que tirar los dados!");
                            }
                        } else {
                            System.out.println("Comando incorrecto.");
                        }
                    } else
                        System.out.println("Comando incorrecto.");
                    break;

                case "salir":
                    if (comando.length == 2) {
                        if (comando[1].equals("carcel")) {
                            if (this.jugadorActual.getFortuna() >= Valor.SAIR_CARCERE) {
                                this.jugadorActual.restarFortuna(Valor.SAIR_CARCERE);
                                this.jugadorActual.setContadorEstarCarcere(0);
                                this.sigueTurno = true;
                                this.dadosLanzados = false;
                            } else {
                                System.out.println("No tienes suficiente dinero para salír de la cárcel, por lo que debes esperar a que pasen los turnos de penalización.");
                            }
                        } else {
                            System.out.println("Comando incorrecto.");
                        }
                    } else
                        System.out.println("Comando incorrecto.");
                    break;

                case "describir":
                    if (comando.length == 3) {
                        if (comando[1].equals("jugador")) {
                            if (partida.getJugadores().containsKey(comando[2]))
                                System.out.println(partida.getJugadores().get(comando[2]));
                            else
                                System.out.println("Comando incorrecto. Jugador no encontrado.");
                        } else if (comando[1].equals("avatar")) {
                            if (partida.getAvatares().containsKey(comando[2])) {
                                System.out.println(partida.getAvatares().get(comando[2])); //mirar en caso de que no este el avatar
                            } else
                                System.out.println("Comando incorrecto. Avatar no encontrado.");
                        } else {
                            System.out.println("Comando incorrecto.");
                        }
                    } else if (comando.length == 2) {
                        if (taboleiro.getCasillas().containsKey(comando[1]))
                            System.out.println(taboleiro.getCasillas().get(comando[1]));
                        else
                            System.out.println("Comando incorrecto. El nome de una casilla debe introducirse tal y como aparece en el tablero pero SIN espacios.");
                    } else {
                        System.out.println("Comando incorrecto.");
                    }

                    break;

                case "comprar":
                    if (comando.length == 2) {
                        if (comando[1].equals(this.jugadorActual.getAvatar().getCasilla().getNombreSinEspacios())) {
                            if (!this.jugadorActual.getEstarCarcere()) {
                                if (this.poderComprar) {
                                    this.jugadorActual.comprarCasilla(this.jugadorActual.getAvatar().getCasilla(), this.taboleiro);
                                    taboleiro.setContadorVueltas(0);
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
                        System.out.println("Comando incorrecto.");
                    }
                    break;

                case "ver":
                    if (comando.length == 2) {
                        if (comando[1].equals("tablero"))
                            System.out.println(taboleiro);
                        else
                            System.out.println("Comando incorrecto.");
                    } else
                        System.out.println("Comando incorrecto");

                    break;

                case "abandonar":
                    System.out.println("Abandonando partida...");
                    seguir = false;
                    break;

                case "stop":
                    new Scanner(System.in).nextLine();
                    break;

                default:
                    System.out.println("Comando incorrecto.");
            }

        }

    }

    public int numeroJugadores() {
        return jugadores.size();
    }

    public String[] leerComando() {
        String comando;
        Scanner teclado = new Scanner(System.in);
        comando = teclado.nextLine();
        return comando.split(" ");
    }

    public BufferedReader abrirArchivo(){
        BufferedReader buffRead= null;
        try {
            String directorio= "/Users/davidmohedano/Documents/USC/Segundo/Primer cuatrimestre/POO/proyecto1/src/";
            FileReader fileRead= new FileReader(directorio + "comandos.txt");
            buffRead= new BufferedReader(fileRead);
        } catch(FileNotFoundException notFound) {
            System.out.print(notFound.getMessage());
            System.exit(0);
        }
        return buffRead;
    }

    public String[] leerComandoArchivo(BufferedReader buffRead){
        String comandoEntero = null;
        String[] comando;
        try {
            comandoEntero= buffRead.readLine();
        } catch(IOException io) {
            System.out.println(io.getMessage());
        }
        if (comandoEntero != null) {
            System.out.println(comandoEntero);
            comando = comandoEntero.split(" ");
        }
        else{
            System.out.println("ERROR leyendo el archivo. Se acabó de leer el archivo, introduce tus comandos.");
            System.out.print("$> ");
            this.esLeerArchivo = false;
            comando = leerComando();
        }
        return comando;
    }

    public Jugador getJugadorTurnoSiguiente(Jugador jugadorActual) {
        int i = 0;
        int total = jugadores.size() - 1;
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

    public void calcularJugadores() {
        this.jugadorActual = this.jugadorTurnoSiguiente;
        this.jugadorTurnoSiguiente = getJugadorTurnoSiguiente(this.jugadorActual);
        if (this.jugadorTurnoSiguiente == null) {
            System.err.println("ERROR al calcular el jugador siguinte.");
        }
    }

}