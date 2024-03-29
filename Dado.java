package juego_fisico;

import monopoly.Menu;
import monopoly.Valor;
import partida_virtual.Jugador;

import java.util.Random;

public class Dado {
    private int dado1;
    private int dado2;
    private boolean iguales;
    private int posActual;
    private int posSiguiente;
    private int dadoTotal;

    public Dado() {

    }
    // NO se permite ponerle valor predeterminado a ningún campo del dado ya que sería trucar la "aletoriedad" de
    // los mismos

    // Tampoco se permite saber el valor de los dados por separado, no hay que saberlo, ni los campos calculados
    // de posActual y posSiguiente


    public void generarValorDados(Dado dado) throws InterruptedException {
        Random ale = new Random(System.currentTimeMillis()); //pone una semilla nueva de cada vez

        dado.dado1 = (int) (Math.random() * 6 + 1);
        Thread.sleep(50);
        //ale = new Random(System.currentTimeMillis());
        dado.dado2 = (int) (Math.random() * 6 + 1);

    }

    public int getDadoTotal() {
        return this.dadoTotal;
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

    public void lanzarDados(Jugador jugador, Taboleiro taboleiro, Menu menu) throws InterruptedException {

        Casilla casillaSiguiente;
        this.dadoTotal = lanzarLosDados();

        this.posActual = jugador.getAvatar().getCasilla().getPosicion();
        taboleiro.getCasillaPosicion(this.posActual).eliminarAvatar(jugador.getAvatar().getId());
        this.posSiguiente = this.posActual + this.dadoTotal;
        if (this.posSiguiente > 39) {
            jugador.sumarFortuna(Valor.VUELTA);
            taboleiro.getCasillaPosicion(0).setVecesCasilla(jugador);
            System.out.println("Has pasado por la casilla de salida, cobras: " + Valor.VUELTA + "€.");
            if (taboleiro.getCasillaPosicion(0).isSubirPrecio()) {
                taboleiro.subirPrecios();
            }
            taboleiro.subirPreciosTotal(menu);
        }
        if (this.posSiguiente == 30) {
            jugador.irCarcere(taboleiro);
            System.out.println("Caíste en la casilla Ir Cárcel, por lo que ahora estás en la cárcel.");
            taboleiro.getCasillaPosicion(10).setAvatar(jugador.getAvatar().getId(), jugador.getAvatar());
            taboleiro.setContadorVueltas(0);
        } else {
            this.posSiguiente = this.posSiguiente % 40;
            casillaSiguiente = taboleiro.getCasillaPosicion(this.posSiguiente);
            jugador.getAvatar().setCasilla(casillaSiguiente);
            taboleiro.getCasillaPosicion(this.posSiguiente).setAvatar(jugador.getAvatar().getId(), jugador.getAvatar());
        }
    }

    public boolean getIguales() {
        return this.iguales;
    }

    public String textoLanzarDados(Taboleiro taboleiro) {
        String texto;
        int sumaDados = this.dado1 + this.dado2;
        texto = " avanza " + sumaDados + " posiciones, desde " + taboleiro.getCasillaPosicion(this.posActual).getNombreSinEspacio() +
                " hasta " + taboleiro.getCasillaPosicion(this.posSiguiente).getNombreSinEspacio() + "."; // Falta pagaronse
        return texto;
    }
}