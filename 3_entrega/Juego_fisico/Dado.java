package Juego_fisico;

import Casilla.*;
import Jugador.*;
import Monopoly.*;

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
    private String texto;

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
        this.texto = "";
    }

    public Dado(Dado dado) throws InterruptedException {
        dado.generarValorDados(dado);
    }
    // NO se permite ponerle valor predeterminado a ningún campo del dado ya que sería trucar la "aletoriedad" de
    // los mismo    // los mismos


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
        this.iguales = this.dado2 == this.dado1;
        return this.dado1 + this.dado2;
    }

    public void lanzarDados(Jugador jugador, Taboleiro taboleiro, Menu menu) throws InterruptedException {

        this.dadoTotal = lanzarLosDados();
        jugador.sumarVecesdados();

        this.posActual = jugador.getAvatar().getCasilla().getPosicion();
        taboleiro.getCasillaPosicion(this.posActual).eliminarAvatar(jugador.getAvatar().getId());

        jugador.getAvatar().mover(taboleiro, menu);
    }

    public boolean getIguales() {
        return this.iguales;
    }

    public int getPosActual() {
        return posActual;
    }

    public void setPosActual(int posActual) {
        if (posActual >= 0 && posActual < 40)
            this.posActual = posActual;
    }

    public int getPosSiguiente() {
        return posSiguiente;
    }

    public void setPosSiguiente(int posSiguiente) {
        if (posSiguiente >= 0 && posSiguiente < 40)
            this.posSiguiente = posSiguiente;
    }

    public String textoAuxLanzarDados(Taboleiro taboleiro, Jugador jugador, Menu menu, String texto) {
        String aux;
        if (taboleiro != null && jugador != null && texto != null)
            if (taboleiro.getCasillaPosicion(this.posSiguiente) instanceof Accion) {

                if (taboleiro.getCasillaPosicion(this.posSiguiente) instanceof AccionSuerte) {
                    texto += "\n" + jugador.getNombre() + ", elige una carta de suerte (1-13): ";
                    aux = "Acción: " + ((AccionSuerte) taboleiro.getCasillaPosicion(this.posSiguiente)).getCarta().getTexto() + "\n";
                } else {
                    texto += "\n" + jugador.getNombre() + ", elige una carta de caja de comunidad (1-10): ";
                    aux = "Acción: " + ((AccionCajaComunidad) taboleiro.getCasillaPosicion(this.posSiguiente)).getCarta().getTexto() + "\n";
                }
                System.out.print(texto);
                menu.leerComando();
                texto = aux;
            }

        return texto;
    }

    public String textoLanzarDados(Taboleiro taboleiro, Jugador jugador, Menu menu) {
        String texto = taboleiro + "\n";
        int sumaDados = this.dado1 + this.dado2;
        if ((this.posSiguiente == 7) || (this.posSiguiente == 22) || (this.posSiguiente == 36)) {
            texto = "";
        } else if ((this.posSiguiente == 2) || (this.posSiguiente == 17) || (this.posSiguiente == 33)) {
            texto = "";
        }
        if (jugador.getAvatar().isModoAvanzado() && (jugador.getAvatar() instanceof Coche) && sumaDados <= 4) {
            texto += Valor.RESET + "El avatar " + menu.getJugadorActual().getAvatar().getId() + " retrocede " + sumaDados +
                    " posiciones, desde " + taboleiro.getCasillaPosicion(this.posActual).getNombreSinEspacio() +
                    " hasta " + taboleiro.getCasillaPosicion(this.posSiguiente).getNombreSinEspacio() + ". " + this.texto;
        } else {
            texto += Valor.RESET + "El avatar " + menu.getJugadorActual().getAvatar().getId() + " avanza " + sumaDados +
                    " posiciones, desde " + taboleiro.getCasillaPosicion(this.posActual).getNombreSinEspacio() +
                    " hasta " + taboleiro.getCasillaPosicion(this.posSiguiente).getNombreSinEspacio() + ". ";
        }
        texto = textoAuxLanzarDados(taboleiro, jugador, menu, texto);

        return texto;
    }

    public void aux(Menu menu) throws InterruptedException {
        String texto;
        if (!((menu.getJugadorActual().getAvatar() instanceof Coche) && (((Coche) menu.getJugadorActual().getAvatar()).getPenalizacion() <= 2))) {
            if (!menu.getJugadorActual().getEstarCarcere()) {
                menu.getJuego().getDado().lanzarDados(menu.getJugadorActual(), menu.getJuego().getTaboleiro(), menu);
                menu.setPartidaEmpezada(true);
                if (menu.getJuego().getDado().getIguales()) {
                    menu.setDadosLanzados(false);
                    menu.setSigueTurno(true);
                    menu.setContadorDobles(menu.getContadorDobles() + 1);
                    menu.setPoderComprar(true);
                    texto = " Sacastes dobles! Llevas: " + menu.getContadorDobles() + " veces.";
                } else {
                    menu.setDadosLanzados(true);
                    menu.setSigueTurno(false);
                    menu.setPoderComprar(true);
                    menu.setContadorDobles(0);
                }
                if (this.contadorDobles == 3) {
                    this.jugadorActual.irCarcere(this.juego.getTaboleiro());
                    this.dadosLanzados = true;
                    this.sigueTurno = false;
                    this.poderComprar = false;
                    this.contadorDobles = 0;
                    texto = "Sacastes tres dobles seguidos, por lo que tienes que ir a la cárcel!";
                    this.juego.getTaboleiro().getCasillaPosicion(this.jugadorActual.getAvatar().getCasilla().getPosicion()).eliminarAvatar(this.jugadorActual.getAvatar().getId());
                    this.juego.getTaboleiro().getCasillaPosicion(10).setAvatar(this.jugadorActual.getAvatar());
                    //System.out.println(this.taboleiro);
                    System.out.println(this.juego.getDado().textoLanzarDados(this.juego.getTaboleiro(), this.jugadorActual, this) + texto);
                } else {
                    if ((!this.jugadorActual.getAvatar().isModoAvanzado()) || (this.jugadorActual.getAvatar().isModoAvanzado() && (this.jugadorActual.getAvatar() instanceof Coche))) {
                        //System.out.println(taboleiro);
                        System.out.println(this.juego.getDado().textoLanzarDados(this.juego.getTaboleiro(), this.jugadorActual, this) + texto);
                        this.jugadorActual.pagarAlquiler(this.jugadorActual.getAvatar().getCasilla(), this.juego.getDado().getDadoTotal());
                        this.jugadorActual.pagarImpuestos(this.jugadorActual.getAvatar().getCasilla(), this.juego.getTaboleiro());
                        this.jugadorActual.cobrarParking(this.jugadorActual.getAvatar().getCasilla());
                    }
                }
            } else {
                this.juego.getDado().lanzarLosDados();
                if (this.juego.getDado().getIguales()) {
                    this.jugadorActual.setContadorEstarCarcere(0);
                    System.out.println("Sacastes dobles, puedes salír de la cárcel. Lanza los dados para continuar.");
                    this.dadosLanzados = false;
                    this.sigueTurno = true;
                } else {
                    this.jugadorActual.setContadorEstarCarcere(1);
                    System.out.println("No sacastes dobles, llevas " + this.jugadorActual.getContadorEstarCarcere() + " intentos.");
                    this.dadosLanzados = true;
                    this.sigueTurno = false;
                    if (this.jugadorActual.getContadorEstarCarcere() >= 3) {
                        System.out.println("Ya llevas 3 intentos, por lo que debes pagar para salír.");
                        if (this.jugadorActual.getFortuna() >= Valor.SALIR_CARCEL) {
                            this.jugadorActual.restarFortuna(Valor.SALIR_CARCEL);
                            ((Especial) this.juego.getTaboleiro().getCasillaPosicion(20)).sumarBote(Valor.SALIR_CARCEL);
                            this.jugadorActual.setContadorEstarCarcere(0);
                            System.out.println("Pago efectuado. Ya podrás tirar en el seguiente turno.");
                        } else {
                            System.out.println("No tienes suficiente dinero para salír de la cárcel, por lo que estás en bancarrota.");
                        }
                    }
                }
            }
            if ((this.jugadorActual.getAvatar() instanceof Coche) && (this.jugadorActual.getAvatar().isModoAvanzado())) {
                ((Coche) this.jugadorActual.getAvatar()).sumarLanzardados(this);
                if (((Coche) this.jugadorActual.getAvatar()).isCompraCoche())
                    this.poderComprar = false;
            }
        } else {
            System.out.println("Estás penalizado, debes acabar turno y pasarle el turno al siguiente jugador.");
            this.dadosLanzados = true;
            this.sigueTurno = false;
        }
    }
}