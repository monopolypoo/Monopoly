import java.util.ArrayList;
import java.util.Random;

public class Avatar{
    private String id;
    private String tipo;
    private Jugador jugador;
    private Casilla casilla;

    public Avatar(){}

    public Avatar(String tipo, Jugador jugador, ArrayList<Jugador> jugadores, Casilla casilla){
        if(tipo.equals("sombrero") || tipo.equals("esfinge") || tipo.equals("coche") || tipo.equals("pelota"))
            this.tipo = tipo;

        else
            this.tipo = "coche";
        if (jugador == null){
            System.err.println("Error: jugador no inicializado.");
            System.exit(1);
        }
        this.jugador = jugador;
        this.generarId(jugadores);
        this.casilla = casilla;
    }

    private boolean idUnico(String id, ArrayList<Jugador> jugadores){
        for (Jugador jug: jugadores){
            if (jug.getAvatar().id.equals(id))
                return false;
        }
        return true;
    }

    private void generarId(ArrayList<Jugador> jugadores){
        String idAux = "";
        do{
            Random ale = new Random(System.currentTimeMillis()); //pone una semilla nueva de cada vez
            int numero = ale.nextInt(20) + 65; //genera un numero aleatorio entre 65 y 65+20
            idAux += (char) numero; //se pasa a ASCII y se convierte en String concatenandolo con ""
        }while (!idUnico(idAux, jugadores));

        this.id = idAux;
    }

    public String getId(){
        return id;
    }

    public String getTipo(){
        return tipo;
    }

    public String getJugador(){
        return jugador.getNombre();
    }

    public Casilla getCasilla(){ return casilla; }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    @Override
    public String toString(){
        String texto = "{\n" +
                "\tid: " + this.id + "\n" +
                "\ttipo: " + this.tipo + "\n" +
                "\tcasilla: " + this.casilla.getNombre() + "\n" +
                "\tjugador: " + this.jugador.getNombre() + "\n}";

        return texto;
    }
}
