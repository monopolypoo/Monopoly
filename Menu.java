import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Taboleiro taboleiro;
    private Dado dados;
    private Partida partida;
    private Jugador jugadorActual;
    private Jugador jugadorTurnoSiguiente;
    private ArrayList<Jugador> jugadores;

    public Menu() throws InterruptedException {
        boolean seguir = true;
        this.taboleiro = new Taboleiro();
        this.dados = new Dado();
        this.partida = new Partida();
        this.jugadores = new ArrayList<>();
        String[] comando;

        while(seguir){ //mirar cuando acabar la partida
            System.out.print("$> ");
            comando = leerComando();
            switch (comando[0]){
                case "crear":
                    if (comando[1].equals("jugador")){

                        if (comando[2].equals("banca")) {
                            Jugador jugador = new Jugador();
                            partida.anhadeJugador(jugador);
                        }
                        else {
                            Jugador jugador = new Jugador(comando[2], comando[3], jugadores, taboleiro.getCasillaPosicion(0));
                            jugadores.add(jugador);
                            partida.anhadeJugador(jugador);
                            jugadorActual = jugadores.get(0);

                            if (jugadores.size() >= 2){
                                jugadorTurnoSiguiente = jugadores.get(1);
                            }
                            else{
                                jugadorTurnoSiguiente = jugadores.get(0);
                            }
                        }
                    }
                    else
                        System.out.println("Comando incorrecto.");
                    break;

                case "jugador":
                    System.out.println(jugadorActual);
                    break;

                case "listar":
                    switch (comando[1]){
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
                    }
                    break;

                case "lanzar":
                    if (comando[1].equals("dados")) {
                        dados.lanzarDados(jugadorActual, taboleiro);
                    }
                    else
                        System.out.println("Comando incorrecto.");
                    break;

                case "acabar":
                    if (comando[1].equals("turno")){
                        calcularJugadores();
                    }
                    else
                        System.out.println("Comando incorrecto.");
                    break;

                case "salir":
                    if (comando[1].equals("carcel")) {
                        //HACER ESTO
                    }
                    else
                        System.out.println("Comando incorrecto.");
                    break;

                case "describir":
                    if (comando[1].equals("jugador")) {
                        if (partida.getJugadores().containsKey(comando[2]))
                            System.out.println(partida.getJugadores().get(comando[2]));
                        else
                            System.out.println("Comando incorrecto. Jugador no encontrado.");
                    }

                    else if (comando[1].equals("avatar")) {
                        if (partida.getAvatares().containsKey(comando[2])) {
                            System.out.println(partida.getAvatares().get(comando[2])); //mirar en caso de que no este el avatar
                        }
                        else
                            System.out.println("Comando incorrecto. Avatar no encontrado.");
                    }

                    else {
                        if (taboleiro.getCasillas().containsKey(comando[2]))
                            System.out.println(taboleiro.getCasillas().get(comando[2]));
                        else
                            System.out.println("Comando incorrecto. O nome dunha casilla debe introducirse tal e como aparece no taboleiro pero SEN espazos.");
                    }
                    break;

                case "comprar":
                    //no para esta entrega
                    break;

                case "ver":
                    if (comando[1].equals("tablero"))
                        System.out.println(taboleiro);

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

    public String[] leerComando(){
        String comando;
        Scanner teclado = new Scanner(System.in); //mirar esto!!!!!!!!!!!!!!!!!!!!!!
        comando = teclado.nextLine();
        return comando.split(" ");
    }

    public Jugador getJugadorTurnoSiguiente(Jugador jugadorActual){
        int i=0;
        int total = jugadores.size() - 1;
        for (Jugador jug: this.jugadores){
            if (jug.getNombre().equals(jugadorActual.getNombre())){
                if (i == total){
                    return jugadores.get(0);
                }
                else{
                    i++;
                    return jugadores.get(i);
                }
            }
            i++;
        }
        return null;
    }

    public void calcularJugadores(){
        this.jugadorActual = this.jugadorTurnoSiguiente;
        this.jugadorTurnoSiguiente = getJugadorTurnoSiguiente(this.jugadorActual);
        if (this.jugadorTurnoSiguiente == null){
            System.err.println("ERROR ao calcular o xogador seguinte.");
        }
    }

}
