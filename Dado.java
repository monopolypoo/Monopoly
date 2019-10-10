import java.util.Random;

public class Dado {
    private int dado1;
    private int dado2;
    private boolean iguales;
    private int posActual;

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
        this.iguales = false;
        this.dado1 = (int) Math.floor(Math.random() * 6 + 1);
        Thread.sleep(50);
        this.dado2 = (int) Math.floor(Math.random() * 6 + 1);
        if (this.dado2 == this.dado1) {
            this.iguales = true;
        } else {
            this.iguales = false;
        }
        return this.dado1 + this.dado2;
    }

    public void lanzarDados(Jugador jugador, Taboleiro taboleiro) throws InterruptedException {
        int posSig;
        int dado;

        Casilla casillaSiguiente;
        dado = lanzarLosDados();

        this.posActual = jugador.getAvatar().getCasilla().getPosicion();
        posSig = this.posActual + dado;
        casillaSiguiente = taboleiro.getCasillaPosicion(posSig);

        jugador.getAvatar().setCasilla(casillaSiguiente);

    }

    public boolean sonIguales() {
        return this.iguales;
    }

    public String textoLanzarDados(Taboleiro taboleiro) {
        String texto;
        int sumaDados = this.dado1 + this.dado2;
        texto = " avanza " + sumaDados + " posicións, dende " + taboleiro.getCasillaPosicion(posActual).getNombreSinEspacio() + " ata " + taboleiro.getCasillaPosicion(posActual + sumaDados).getNombreSinEspacio() + "."; // Falta pagaronse
        return texto;
    }


}
