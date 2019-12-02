import java.util.ArrayList;

public final class Pelota extends Avatar{
    private int vecesPelota;
    private int numeroPelota;
    private boolean seguirPelota;

    public Pelota(){
        super();
        this.vecesPelota = 0;
        this.numeroPelota = 0;
        this.seguirPelota = false;
    }

    public Pelota(Jugador jugador, ArrayList<Jugador> jugadores, Casilla casilla){
        super(jugador, jugadores, casilla);
        this.vecesPelota = 0;
        this.numeroPelota = 0;
        this.seguirPelota = false;
    }

    public int getVecesPelota() {
        return vecesPelota;
    }

    public void setVecesPelota(int vecesPelota) {
        this.vecesPelota = vecesPelota;
    }

    public int getNumeroPelota() {
        return numeroPelota;
    }

    public void setNumeroPelota(int numeroPelota) {
        this.numeroPelota = numeroPelota;
    }

    public boolean isSeguirPelota() {
        return seguirPelota;
    }

    public void setSeguirPelota(boolean seguirPelota) {
        this.seguirPelota = seguirPelota;
    }

    @Override
    public void moverEnAvanzado(Taboleiro taboleiro, Menu menu, Dado dado) {

    }

    @Override
    public String toString() {
        String texto = "{\n" +
                "\tid: " + super.getId() + "\n" +
                "\ttipo: pelota"  + "\n" +
                "\tcasilla: " + super.getCasilla().getNombre() + "\n" +
                "\tjugador: " + super.getJugador().getNombre() + "\n}";

        return texto;
    }
}
