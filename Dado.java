import javax.persistence.criteria.CriteriaBuilder;
import java.util.Random;

public class Dado {
    private int dado1;
    private int dado2;
    private boolean iguales;
    private int posActual;
    private int posSiguiente;

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
        int dado;

        Casilla casillaSiguiente;
        dado = lanzarLosDados();

        this.posActual = jugador.getAvatar().getCasilla().getPosicion();
        taboleiro.getCasillaPosicion(this.posActual).eliminarAvatar(jugador.getAvatar().getId());
        this.posSiguiente = this.posActual + dado;
        if(this.posSiguiente > 39){
            jugador.sumarFortuna(Valor.VUELTA);
            taboleiro.getCasillaPosicion(0).setVecesCasilla(jugador);
            if (taboleiro.getCasillaPosicion(0).isSubirPrecio()){
                taboleiro.subirPrecios();
            }
        }
        if (this.posSiguiente == 30){
            jugador.irCarcere(taboleiro);
            System.out.println("Tocouche a casilla Ir Cárcere, polo que agora estás no cárcere."); //decir lo que tiene que hacer
        }
        else {
            this.posSiguiente = this.posSiguiente % 40;
            casillaSiguiente = taboleiro.getCasillaPosicion(this.posSiguiente);

            jugador.getAvatar().setCasilla(casillaSiguiente);
        }
        taboleiro.getCasillaPosicion(this.posSiguiente).setAvatar(jugador.getAvatar().getId(), jugador.getAvatar());
    }

    public boolean sonIguales() {
        return this.iguales;
    }

    public String textoLanzarDados(Taboleiro taboleiro) {
        String texto;
        int sumaDados = this.dado1 + this.dado2;
        texto = " avanza " + sumaDados + " posicións, dende " + taboleiro.getCasillaPosicion(this.posActual).getNombreSinEspacio() +
                " ata " + taboleiro.getCasillaPosicion(this.posSiguiente).getNombreSinEspacio() + "."; // Falta pagaronse
        return texto;
    }


}