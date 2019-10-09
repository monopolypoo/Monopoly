import java.util.Random;

public class Dado {
    private int dado1;
    private int dado2;

    public Dado() {

    }

    public void generarValorDados(Dado dado) throws InterruptedException {
        Random ale = new Random(System.currentTimeMillis()); //pone una semilla nueva de cada vez

        dado.dado1 = (int) (Math.random() * 6 + 1);
        Thread.sleep(50);
        //ale = new Random(System.currentTimeMillis());
        dado.dado2 = (int) (Math.random() * 6 + 1);

    }

    public Dado(Dado dado) throws InterruptedException {
        dado.generarValorDados(dado);
    }

    public int lanzarLosDados() throws InterruptedException {
        this.dado1 = (int) Math.floor(Math.random() * 6 + 1);
        Thread.sleep(50);
        this.dado2 = (int) Math.floor(Math.random() * 6 + 1);
        return this.dado1 + this.dado2;
    }

    public void lanzarDados(Jugador jugador, Taboleiro taboleiro) throws InterruptedException {
        int posActual, posSig;
        int dado;
        Casilla casillaSiguiente;
        dado = lanzarLosDados();

        posActual = jugador.getAvatar().getCasilla().getPosicion();
        posSig = posActual + dado;
        casillaSiguiente = taboleiro.getCasillaPosicion(posSig);

        jugador.getAvatar().setCasilla(casillaSiguiente);
    }


    public String toString() {
        return "O valor do primeiro dado é: " + this.dado1 + "\nO valor do segundo dado é: " + this.dado2;
    }


}