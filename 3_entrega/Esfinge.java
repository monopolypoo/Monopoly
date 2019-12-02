import java.util.ArrayList;

public final class Esfinge extends Avatar{

    public Esfinge(){
        super();
    }

    public Esfinge(Jugador jugador, ArrayList<Jugador> jugadores, Casilla casilla){
        super(jugador, jugadores, casilla);
    }

    public void moverEnAvanzado(){

    }

    @Override
    public String toString() {
        String texto = "{\n" +
                "\tid: " + super.getId() + "\n" +
                "\ttipo: esfinge"  + "\n" +
                "\tcasilla: " + super.getCasilla().getNombre() + "\n" +
                "\tjugador: " + super.getJugador().getNombre() + "\n}";

        return texto;
    }
}
