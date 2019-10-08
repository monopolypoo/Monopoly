import java.util.Scanner;

public class Menu {
    private Taboleiro taboleiro;
    private Dado dados;
    private Partida partida;
    private Jugador jugadorActual;
    private Jugador jugadorTurno;

    public Menu(){
        this.taboleiro = new Taboleiro();
        this.dados = new Dado();
        this.partida = new Partida();
        String[] comando;

        do{
            System.out.print("$> ");
            comando = leerComando();
            switch (comando[0]){
                case "crear":

                    break;

                case "jugador":
                    System.out.println(jugadorTurno);
                    break;

                case "listar":
                    switch (comando[1]){
                        case "jugadores":
                            System.out.println(partida.getJugadores()); //mirar esto
                            break;

                        case "avatares":
                            System.out.println(partida.getAvatares()); //mirar esto
                            break;

                        default:
                            System.out.println("Comando incorrecto.");
                    }
                    break;

                case "lanzar":
                    if (comando[1].equals("dados"))
                        dados.lanzarDados();
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
                    if (comando[1].equals("carcel"))
                        //HACER ESTO
                    else
                        System.out.println("Comando incorrecto.");
                    break;

                case "describir":
                    if (comando[1].equals("jugador"))
                        System.out.println(partida.getJugadores().get(comando[2]));

                    else if (comando[1].equals("avatar"))
                        System.out.println(partida.getAvatares().get(comando[2]));

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

                case "listar":
                    //no para esta entrega
                    break;

                case "ver":
                    if (comando[1].equals("tablero"))
                        System.out.println(taboleiro);

                    else
                        System.out.println("Comando incorrecto");

                    break;

                default:
                    System.out.println("Comando incorrecto.");
            }

        }while(true) //mirar cuando acabar la partida
    }

    public String[] leerComando(){
        String comando;
        Scanner teclado = new Scanner(System.in); //mirar esto!!!!!!!!!!!!!!!!!!!!!!
        comando = teclado.nextLine();
        return comando.split(" ");
    }

}
