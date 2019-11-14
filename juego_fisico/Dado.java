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
    private int vecesPelota;
    private int numeroPelota;
    private boolean seguirPelota;

    public Dado() {
        this.dado1 = 0;
        this.dado2 = 0;
        this.iguales = false;
        this.posActual = 0;
        this.posSiguiente = 0;
        this.dadoTotal = 0;
        this.vecesPelota = 0;
        this.numeroPelota = 0;
        this.seguirPelota = false;
    }

    public Dado(Dado dado) throws InterruptedException {
        dado.generarValorDados(dado);
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

        this.dadoTotal = lanzarLosDados();
        jugador.sumarVecesdados();

        this.posActual = jugador.getAvatar().getCasilla().getPosicion();
        taboleiro.getCasillaPosicion(this.posActual).eliminarAvatar(jugador.getAvatar().getId());

        if (!jugador.getAvatar().getModoAvanzado()) {
            this.posSiguiente = this.posActual + this.dadoTotal;
            modoNormal(jugador, taboleiro, menu);
        } else {
            switch (jugador.getAvatar().getTipo()) {
                case "coche":
                    modoCoche(jugador, taboleiro, menu);
                    break;

                case "pelota":
                    this.posSiguiente = this.posActual + this.dadoTotal;
                    modoPelota(jugador, taboleiro, menu);
                    break;

                case "esfinge":

                    break;

                case "sombrero":

                    break;

                default:
                    System.out.println("ERROR. Se lanzarán los dados normal.");
            }
        }
    }

    public boolean getIguales() {
        return this.iguales;
    }

    public void modoCoche(Jugador jugador, Taboleiro taboleiro, Menu menu) {
        if (jugador.getAvatar().getContadorCoche() <= 3 && this.dadoTotal > 4) {
            jugador.getAvatar().setModoCoche(true);
            this.posSiguiente = this.posActual + this.dadoTotal;
            modoNormal(jugador, taboleiro, menu);
            jugador.getAvatar().setLanzarDadosCoche(true);
        } else if (this.dadoTotal <= 4) {
            jugador.getAvatar().setModoCoche(false);
            this.posSiguiente = this.posActual - this.dadoTotal;
            if (this.posSiguiente < 0) {
                this.posSiguiente += 39;
            }
            modoNormal(jugador, taboleiro, menu);
            jugador.getAvatar().setSubirPenalizacion(true);
            jugador.getAvatar().setContadorCoche(0);
            jugador.getAvatar().setCompraCoche(false);
            jugador.getAvatar().setPenalizacion(0);
            jugador.getAvatar().setLanzarDadosCoche(false);
            menu.setDadosLanzados(true);
        }
    }

    public void modoNormal(Jugador jugador, Taboleiro taboleiro, Menu menu) {
        Casilla casillaSiguiente;
        if (this.posSiguiente > 39) {
            jugador.sumarFortuna(Valor.VUELTA);
            jugador.sumarVecesSalida();
            System.out.println("Has pasado por la casilla de salida, cobras: " + Valor.VUELTA + "€.");
            if (taboleiro.getCasillaPosicion(0).isSubirPrecio()) {
                taboleiro.subirPrecios();
            }
            taboleiro.subirPreciosTotal(menu);
        }
        if (this.posSiguiente == 30) {
            jugador.irCarcere(taboleiro);
            System.out.println("Caíste en la casilla Ir Cárcel, por lo que ahora estás en la cárcel.");
            taboleiro.getCasillaPosicion(10).setAvatar(jugador.getAvatar());
            jugador.sumarVecesCarcel();
            taboleiro.setContadorVueltas(0);
        } else {
            this.posSiguiente = this.posSiguiente % 40;
            casillaSiguiente = taboleiro.getCasillaPosicion(this.posSiguiente);
            jugador.getAvatar().setCasilla(casillaSiguiente);
            taboleiro.getCasillaPosicion(this.posSiguiente).setAvatar(jugador.getAvatar());
            if (this.posSiguiente != 10 && this.posSiguiente != 20 && this.posSiguiente != 0) {
                taboleiro.getCasillaPosicion(this.posSiguiente).setVecesCasilla(jugador);
            }
        }
    }

    public int sumarImpar(int vez, int num) {
        if (vez == 0) {
            return 5;
        } else {
            return num + 2;
        }
    }

    public void modoPelota(Jugador jugador, Taboleiro taboleiro, Menu menu) {
        this.seguirPelota = true;
        if (this.dadoTotal > 4) {
            /* //MIRAR ESTO TODO
            if ((this.numeroPelota + 1) == this.dadoTotal) {
                this.posSiguiente = this.posSiguiente + 1;
                this.posActual = jugador.getAvatar().getCasilla().getPosicion();
                taboleiro.getCasillaPosicion(this.posActual).eliminarAvatar(jugador.getAvatar().getId());
                modoNormal(jugador, taboleiro, menu);
                this.seguirPelota = false;
                this.numeroPelota = 0;
                this.vecesPelota = 0;
                return;
            } else if ((this.numeroPelota + 2) == this.dadoTotal) {
                this.posSiguiente = this.posSiguiente + 2;
                this.posActual = jugador.getAvatar().getCasilla().getPosicion();
                taboleiro.getCasillaPosicion(this.posActual).eliminarAvatar(jugador.getAvatar().getId());
                modoNormal(jugador, taboleiro, menu);
                this.seguirPelota = false;
                this.numeroPelota = 0;
                this.vecesPelota = 0;
                return;
            } else {
                this.numeroPelota = sumarImpar(this.vecesPelota, this.numeroPelota);
                this.posSiguiente = this.posActual + this.numeroPelota;
                this.posActual = jugador.getAvatar().getCasilla().getPosicion();
                taboleiro.getCasillaPosicion(this.posActual).eliminarAvatar(jugador.getAvatar().getId());
                modoNormal(jugador, taboleiro, menu);
            }

            if ((this.posSiguiente == 30) || (this.numeroPelota >= this.dadoTotal)) {
                this.seguirPelota = false;
                this.numeroPelota = 0;
                this.vecesPelota = 0;
                return;
            }
            this.vecesPelota++;

             */

        } else {
            if (this.vecesPelota == 0) {
                if (this.dadoTotal == 4){
                    this.posSiguiente = this.posActual - 3;
                    this.vecesPelota++;
                }
                else{
                    this.posSiguiente = this.posActual - this.dadoTotal;
                    this.seguirPelota = false;
                    this.numeroPelota = 0;
                    this.vecesPelota = 0;
                }
            } else{
                this.posSiguiente = this.posActual - 1;
                this.seguirPelota = false;
                this.numeroPelota = 0;
                this.vecesPelota = 0;
            }
            if (this.posSiguiente < 0) {
                this.posSiguiente = 40 + this.posSiguiente;
            }
            this.posActual = jugador.getAvatar().getCasilla().getPosicion();
            taboleiro.getCasillaPosicion(this.posActual).eliminarAvatar(jugador.getAvatar().getId());
            modoNormal(jugador, taboleiro, menu);
        }
    }

    public String textoLanzarDados(Taboleiro taboleiro) {
        String texto;
        int sumaDados = this.dado1 + this.dado2;
        texto = " avanza " + sumaDados + " posiciones, desde " + taboleiro.getCasillaPosicion(this.posActual).getNombreSinEspacio() +
                " hasta " + taboleiro.getCasillaPosicion(this.posSiguiente).getNombreSinEspacio() + ".";

        return texto;
    }
}