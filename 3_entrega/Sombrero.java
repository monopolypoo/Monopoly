import java.util.ArrayList;

public final class Sombrero extends Avatar{

    public Sombrero(){
        super();
    }

    public Sombrero(Jugador jugador, ArrayList<Jugador> jugadores, Casilla casilla){
        super(jugador, jugadores, casilla);
    }

    public void moverEnAvanzado(){

    }

    @Override
    public String getTipo() {
        return "sombrero";
    }

    @Override
    public String toString() {
        String texto = "{\n" +
                "\tid: " + super.getId() + "\n" +
                "\ttipo: sombrero"  + "\n" +
                "\tcasilla: " + super.getCasilla().getNombre() + "\n" +
                "\tjugador: " + super.getJugador().getNombre() + "\n}";

        return texto;
    }
}