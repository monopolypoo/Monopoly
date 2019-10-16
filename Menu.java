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
    private int contadorDobles;
    private boolean dadosLanzados;
    private boolean poderComprar;

    public Menu() throws InterruptedException {
        boolean seguir = true;
        this.taboleiro = new Taboleiro();
        this.dados = new Dado();
        this.partida = new Partida();
        this.jugadores = new ArrayList<>();
        this.sigueTurno = false;
        this.contadorDobles = 0;
        this.dadosLanzados = false;
        String[] comando;

        String texto;
        while (seguir) { //mirar cuando acabar la partida
            System.out.print("$> ");
            comando = leerComando();
            switch (comando[0]) {
                case "crear":
                    if (comando.length == 4) {
                        if (comando[1].equals("jugador")) {
                            if (jugadores.size() < 6) {
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
                            } else {
                                System.out.println("O número de xogadores xa é o máximo, non podes engadir máis!");
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
                                //no para esta entrega
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
                                        dados.lanzarDados(jugadorActual, taboleiro);
                                        if (dados.sonIguales()) {
                                            this.dadosLanzados = false;
                                            this.sigueTurno = true;
                                            this.contadorDobles++;
                                            this.poderComprar = true;
                                            texto = " Sacaches dobles! Levas: " + this.contadorDobles + " veces.";
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
                                            texto = "Sacaches tres dobles seguidos, polo que tes que ir ao cárcere!";
                                        }
                                        System.out.println(taboleiro);
                                        System.out.println("O avatar " + jugadorActual.getAvatar().getId() + dados.textoLanzarDados(taboleiro) + texto);
                                    }
                                    else{
                                        dados.lanzarLosDados();
                                        if (dados.sonIguales()) {
                                            this.jugadorActual.setContadorEstarCarcere(0);
                                            System.out.println("Sacaches dobles, podes saír do cárcere. Lanza os dados para continuar.");
                                            this.dadosLanzados = false;
                                            this.sigueTurno = true;
                                        }
                                        else{
                                            this.jugadorActual.setContadorEstarCarcere(1);
                                            System.out.println("Non sacaches dobles, levas " + this.jugadorActual.getContadorEstarCarcere() + " intentos.");
                                            this.dadosLanzados = true;
                                            this.sigueTurno = false;
                                            if (this.jugadorActual.getContadorEstarCarcere() >= 3){
                                                System.out.println("Xa levas 3 intentos, polo que debes pagar para saír.");
                                                if (this.jugadorActual.getFortuna() >= Valor.SAIR_CARCERE){
                                                    this.jugadorActual.restarFortuna(Valor.SAIR_CARCERE);
                                                    this.jugadorActual.setContadorEstarCarcere(0);
                                                    System.out.println("Pago efectuado. Xa poderás tirar no seguinte turno.");
                                                }
                                                else{
                                                    System.out.println("Non tes suficientes cartos para saír do cárcere, polo que estás en bancarrota.");
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("Xa tiraches os dados! Para poder tiralos o seguinte xogador antes debes acabar turno!");
                                }
                            } else {
                                System.out.println("Antes de lanzar os dados inserte o xogador!");
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
                                System.out.println("O xogador actual é " + this.jugadorActual.getNombre() + ".");

                                if (this.jugadorActual.getEstarCarcere()){
                                    System.out.println("Estás no cárcere, polo que debes tirar os dados para obter dobles.");
                                }
                            } else {
                                System.out.println("Non podes acabar turno porque tes que tirar os dados!");
                            }
                        }
                        else{
                            System.out.println("Comando incorrecto.");
                        }
                    } else
                        System.out.println("Comando incorrecto.");
                    break;

                case "salir":
                    if (comando.length == 2) {
                        if (comando[1].equals("carcel")) {
                            if (this.jugadorActual.getFortuna() >= Valor.SAIR_CARCERE){
                                this.jugadorActual.restarFortuna(Valor.SAIR_CARCERE);
                                this.jugadorActual.setContadorEstarCarcere(0);
                                this.sigueTurno = true;
                                this.dadosLanzados = false;
                            }
                            else{
                                System.out.println("Non tes suficientes cartos para saír do cárcere, polo que debes esperar a que pasen os turnos de penalización.");
                            }
                        }
                        else{
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
                        }
                        else{
                            System.out.println("Comando incorrecto.");
                        }
                    } else if (comando.length == 2) {
                        if (taboleiro.getCasillas().containsKey(comando[1]))
                            System.out.println(taboleiro.getCasillas().get(comando[1]));
                        else
                            System.out.println("Comando incorrecto. O nome dunha casilla debe introducirse tal e como aparece no taboleiro pero SEN espazos.");
                    } else {
                        System.out.println("Comando incorrecto.");
                    }

                    break;

                case "comprar":
                    if (comando.length == 2){
                        if (!this.jugadorActual.getEstarCarcere()){
                            if (this.poderComprar){
                                this.jugadorActual.comprarCasilla(this.jugadorActual.getAvatar().getCasilla(), this.taboleiro);
                            }
                            else{
                                System.out.println("Para comprar unha casilla antes debe tirar os dados.");
                            }
                        }
                        else{
                            System.out.println("Se estás no cárcere non podes comprar.");
                        }
                    }
                    else{
                        System.out.println("Comando incorrecto.");
                    }
                    break;

                case "ver":
                    if (comando.length == 2) {
                        if (comando[1].equals("tablero"))
                            System.out.println(taboleiro);
                        else
                            System.out.println("Comando incorrecto.");
                    }
                    else
                        System.out.println("Comando incorrecto");

                    break;

                case "abandonar":
                    System.out.println("Abandonando partida...");
                    seguir = false;
                    break;

                default:
                    System.out.println("Comando incorrecto.");
            }

        }

    }

    public String[] leerComando() {
        String comando;
        Scanner teclado = new Scanner(System.in);
        comando = teclado.nextLine();
        return comando.split(" ");
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
            System.err.println("ERROR ao calcular o xogador seguinte.");
        }
    }

}