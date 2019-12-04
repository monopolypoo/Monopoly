package Juego_fisico;

import Casilla.*;
import ExcepcionesNull.ExcepcionesNull;
import ExcepcionesNumericas.ExcepcionesNumericas;
import ExcepcionesPartida.ExcepcionesDinero;
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

    public void setDadoTotal(int dadoTotal) {
        if (dadoTotal >= 2 && dadoTotal <= 12)
            this.dadoTotal = dadoTotal;
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

    public void lanzarDados(Jugador jugador, Taboleiro taboleiro, Menu menu) throws InterruptedException, ExcepcionesDinero, ExcepcionesNull, ExcepcionesNumericas {

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

    public String textoLanzarDados(Taboleiro taboleiro, Jugador jugador, Menu menu) {
        String texto = "";
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
        return texto;
    }

    public void lanzarDadosAux(Menu menu) throws InterruptedException, ExcepcionesDinero, ExcepcionesNull, ExcepcionesNumericas {
        String texto = "";
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
                if (menu.getContadorDobles() == 3) {
                    menu.getJugadorActual().irCarcere(menu.getJuego().getTaboleiro());
                    menu.setDadosLanzados(true);
                    menu.setSigueTurno(false);
                    menu.setPoderComprar(false);
                    menu.setContadorDobles(0);
                    texto = "Sacastes tres dobles seguidos, por lo que tienes que ir a la cárcel!";
                    menu.getJuego().getTaboleiro().getCasillaPosicion(menu.getJugadorActual().getAvatar().getCasilla().getPosicion()).eliminarAvatar(menu.getJugadorActual().getAvatar().getId());
                    menu.getJuego().getTaboleiro().getCasillaPosicion(10).setAvatar(menu.getJugadorActual().getAvatar());
                    //System.out.println(this.taboleiro);
                    if (!(menu.getJugadorActual().getAvatar().getCasilla() instanceof AccionCajaComunidad) &&
                            !(menu.getJugadorActual().getAvatar().getCasilla() instanceof AccionSuerte))
                        System.out.println(menu.getJuego().getDado().textoLanzarDados(menu.getJuego().getTaboleiro(), menu.getJugadorActual(), menu) + texto);
                } else {
                    if ((!menu.getJugadorActual().getAvatar().isModoAvanzado()) || (menu.getJugadorActual().getAvatar().isModoAvanzado() && (menu.getJugadorActual().getAvatar() instanceof Coche))) {
                        //System.out.println(taboleiro);
                        if (!(menu.getJugadorActual().getAvatar().getCasilla() instanceof AccionCajaComunidad) &&
                                !(menu.getJugadorActual().getAvatar().getCasilla() instanceof AccionSuerte))
                            System.out.println(menu.getJuego().getDado().textoLanzarDados(menu.getJuego().getTaboleiro(), menu.getJugadorActual(), menu) + texto);
                        menu.getJugadorActual().pagarAlquiler(menu.getJugadorActual().getAvatar().getCasilla(), menu.getJuego().getDado().getDadoTotal());
                        menu.getJugadorActual().pagarImpuestos(menu.getJugadorActual().getAvatar().getCasilla(), menu.getJuego().getTaboleiro());
                        menu.getJugadorActual().cobrarParking(menu.getJugadorActual().getAvatar().getCasilla());
                    }
                }
            } else {
                menu.getJuego().getDado().lanzarLosDados();
                if (menu.getJuego().getDado().getIguales()) {
                    menu.getJugadorActual().setContadorEstarCarcere(0);
                    System.out.println("Sacastes dobles, puedes salír de la cárcel. Lanza los dados para continuar.");
                    menu.setDadosLanzados(false);
                    menu.setSigueTurno(true);
                } else {
                    menu.getJugadorActual().setContadorEstarCarcere(1);
                    System.out.println("No sacastes dobles, llevas " + menu.getJugadorActual().getContadorEstarCarcere() + " intentos.");
                    menu.setDadosLanzados(true);
                    menu.setSigueTurno(false);
                    if (menu.getJugadorActual().getContadorEstarCarcere() >= 3) {
                        System.out.println("Ya llevas 3 intentos, por lo que debes pagar para salír.");
                        menu.getJugadorActual().restarFortuna(Valor.SALIR_CARCEL);
                        ((Especial) menu.getJuego().getTaboleiro().getCasillaPosicion(20)).sumarBote(Valor.SALIR_CARCEL);
                        menu.getJugadorActual().setContadorEstarCarcere(0);
                        System.out.println("Pago efectuado. Ya podrás tirar en el seguiente turno.");
                    }
                }
            }
            if ((menu.getJugadorActual().getAvatar() instanceof Coche) && (menu.getJugadorActual().getAvatar().isModoAvanzado())) {
                ((Coche) menu.getJugadorActual().getAvatar()).sumarLanzardados(menu);
                if (((Coche) menu.getJugadorActual().getAvatar()).isCompraCoche())
                    menu.setPoderComprar(false);
            }
            if ((menu.getJugadorActual().getAvatar() instanceof Esfinge) && (menu.getJugadorActual().getAvatar().isModoAvanzado())) {
                if (((Esfinge) menu.getJugadorActual().getAvatar()).getVecesDados() < 3) {
                    menu.setDadosLanzados(false);
                    menu.setSigueTurno(true);
                } else {
                    ((Esfinge) menu.getJugadorActual().getAvatar()).setVecesDados(0);
                    ((Esfinge) menu.getJugadorActual().getAvatar()).vaciarRegistroEsfinge();
                }
            }
        } else {
            System.out.println("Estás penalizado, debes acabar turno y pasarle el turno al siguiente jugador.");
            menu.setDadosLanzados(true);
            menu.setSigueTurno(false);
        }
    }
}