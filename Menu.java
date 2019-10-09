import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Taboleiro taboleiro;
    private Dado dados;
    private Partida partida;
    private Jugador jugadorActual;
    private Jugador jugadorTurno;
    private ArrayList<Jugador> jugadores;

    public Menu(){
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
                            Jugador jugador = new Jugador(comando[2], comando[3], jugadores);
                            jugadores.add(jugador);
                            partida.anhadeJugador(jugador);
                        }
                    }
                    else
                        System.out.println("Comando incorrecto.");
                    break;

                case "jugador":
                    System.out.println(jugadorTurno);
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
                    if (comando[1].equals("dados"))
                        dados.lanzarDados(); //editar esta funcion
                    else
                        System.out.println("Comando incorrecto.");
                    break;

                case "acabar":
                    if (comando[1].equals("turno")){
                        //HACER ESTO
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
                            System.out.println(partida.getJugadores().get(comando[2])); //mirar en caso de que no este el jugador
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

}
