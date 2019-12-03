package Jugador;

import Casilla.*;
import Juego_fisico.*;
import Monopoly.*;

import java.util.ArrayList;

public final class Pelota extends Avatar {
    private int vecesPelota;
    private int numeroPelota;
    private boolean seguirPelota;

    public Pelota() {
        super();
        this.vecesPelota = 0;
        this.numeroPelota = 0;
        this.seguirPelota = false;
    }

    public Pelota(Jugador jugador, ArrayList<Jugador> jugadores, Casilla casilla) {
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

    public int sumarImpar(int vez) {
        if (vez == 0) {
            return 5;
        } else {
            return 2;
        }
    }

    @Override
    public String getTipo() {
        return "pelota";
    }

    @Override
    public void moverEnAvanzado(Taboleiro taboleiro, Menu menu, Dado dado) {
        this.seguirPelota = true;
        if (menu.getJuego().getDado().getDadoTotal() > 4) {
            String[] comando;
            String casillaAnterior;
            int contador = 0;
            if (menu.getJuego().getDado().getIguales()) {
                System.out.println("Has sacado dobles, cuando termines con el modo avanzado de pelota, tendrás que volver a tirar los dados.");
            } else {
                menu.setContadorDobles(0);
            }
            while (this.seguirPelota) {
                if (contador >= menu.getJuego().getDado().getDadoTotal()) {
                    this.seguirPelota = false;
                    this.numeroPelota = 0;
                    this.vecesPelota = 0;
                    System.out.println(Valor.RESET + "El modo avanzado de pelota ha finalizado.");
                } else if ((contador + 1) == menu.getJuego().getDado().getDadoTotal()) {
                    menu.getJuego().getDado().setPosActual(super.getCasilla().getPosicion());
                    casillaAnterior = super.getCasilla().getNombreSinEspacio();
                    menu.getJuego().getDado().setPosSiguiente(menu.getJuego().getDado().getPosActual() + 1);
                    contador++;
                    taboleiro.getCasillaPosicion(menu.getJuego().getDado().getPosActual()).eliminarAvatar(super.getId());
                    super.moverEnBasico(taboleiro, menu, menu.getJuego().getDado().getPosSiguiente());
                    this.seguirPelota = false;
                    this.numeroPelota = 0;
                    this.vecesPelota = 0;
                    System.out.println(taboleiro);
                    System.out.println(Valor.RESET + "En los dados te ha tocado un " + menu.getJuego().getDado().getDadoTotal() + ", y por estar en el modo avanzado de pelota, avanzas 1 posición, desde "
                            + casillaAnterior + " hasta " + super.getCasilla().getNombreSinEspacio() + ".");
                    super.getJugador().pagarAlquiler(super.getCasilla(), menu.getJuego().getDado().getDadoTotal());
                    super.getJugador().pagarImpuestos(super.getCasilla(), taboleiro);
                    super.getJugador().cobrarParking(super.getCasilla());
                    if (taboleiro.sePuedeComprar(super.getCasilla()) && (((Propiedad) super.getCasilla()).getDuenho() == null)) {
                        System.out.print(Valor.RESET + "Estás en la casilla " + super.getCasilla().getNombreSinEspacio() + " y se puede comprar, desea comprarla (si/no)? ");
                        comando = menu.leerComando();
                        if (comando[0].toLowerCase().equals("si")) {
                            ((Propiedad) super.getCasilla()).comprarCasilla(super.getJugador(), taboleiro);
                            System.out.println("Pulse enter para continuar con el modo pelota.");
                            menu.leerComando();
                        } else {
                            System.out.println("De acuerdo, no se comprará.");
                        }
                    }
                    System.out.println(Valor.RESET + "El modo avanzado de pelota ha finalizado.");
                } else {
                    this.numeroPelota = sumarImpar(this.vecesPelota);
                    contador += this.numeroPelota;
                    menu.getJuego().getDado().setPosActual(super.getCasilla().getPosicion());
                    casillaAnterior = super.getCasilla().getNombreSinEspacio();
                    menu.getJuego().getDado().setPosSiguiente(menu.getJuego().getDado().getPosActual() + this.numeroPelota);
                    taboleiro.getCasillaPosicion(menu.getJuego().getDado().getPosActual()).eliminarAvatar(super.getId());
                    super.moverEnBasico(taboleiro, menu, menu.getJuego().getDado().getPosSiguiente());
                    this.vecesPelota++;
                    System.out.println(taboleiro);
                    System.out.println(Valor.RESET + "En los dados te ha tocado un " + menu.getJuego().getDado().getDadoTotal() + ", y por estar en el modo avanzado de pelota, avanzas "
                            + this.numeroPelota + " posiciones, desde " + casillaAnterior + " hasta " + super.getCasilla().getNombreSinEspacio() + ".");
                    super.getJugador().pagarAlquiler(super.getCasilla(), menu.getJuego().getDado().getDadoTotal());
                    super.getJugador().pagarImpuestos(super.getCasilla(), taboleiro);
                    super.getJugador().cobrarParking(super.getCasilla());
                }
                if (super.getJugador().getEstarCarcere()) {
                    this.seguirPelota = false;
                    this.numeroPelota = 0;
                    this.vecesPelota = 0;
                    System.out.println(Valor.RESET + "Caíste en la casilla Ir Cárcel, por lo que ahora estás en la cárcel.");
                    System.out.println("El modo avanzado de pelota ha finalizado.");
                }
                if (this.seguirPelota) {
                    if (taboleiro.sePuedeComprar(super.getCasilla()) && (((Propiedad) super.getCasilla()).getDuenho() == null)) {
                        System.out.print(Valor.RESET + "Estás en la casilla " + super.getCasilla().getNombreSinEspacio() + " y se puede comprar, desea comprarla (si/no)? ");
                        comando = menu.leerComando();
                        if (comando[0].toLowerCase().equals("si")) {
                            ((Propiedad) super.getCasilla()).comprarCasilla(super.getJugador(), taboleiro);
                            System.out.println("Pulse enter para continuar con el modo pelota.");
                            menu.leerComando();
                        } else {
                            System.out.println("De acuerdo, no se comprará.");
                        }
                    } else {
                        System.out.println("Pulse enter para continuar con el modo pelota.");
                        menu.leerComando();
                    }
                }
            }

        } else {
            int num = 0;
            String casillaAnterior;
            while (this.seguirPelota) {
                if (this.vecesPelota == 0) {
                    if (menu.getJuego().getDado().getDadoTotal() == 4) {
                        menu.getJuego().getDado().setPosSiguiente(menu.getJuego().getDado().getPosActual() - 3);
                        this.vecesPelota++;
                        num = 0;
                    } else {
                        menu.getJuego().getDado().setPosSiguiente(menu.getJuego().getDado().getPosActual() - menu.getJuego().getDado().getDadoTotal());
                        this.seguirPelota = false;
                        this.numeroPelota = 0;
                        this.vecesPelota = 0;
                        num = menu.getJuego().getDado().getDadoTotal();
                    }
                } else {
                    menu.getJuego().getDado().setPosSiguiente(menu.getJuego().getDado().getPosActual() - 1);
                    this.seguirPelota = false;
                    this.numeroPelota = 0;
                    this.vecesPelota = 0;
                    num = 1;
                }
                if (menu.getJuego().getDado().getPosSiguiente() < 0) {
                    menu.getJuego().getDado().setPosSiguiente(menu.getJuego().getDado().getPosSiguiente() + 40);
                }
                menu.getJuego().getDado().setPosActual(super.getCasilla().getPosicion());
                casillaAnterior = super.getCasilla().getNombreSinEspacio();
                taboleiro.getCasillaPosicion(menu.getJuego().getDado().getPosActual()).eliminarAvatar(super.getId());
                super.moverEnBasico(menu.getJuego().getTaboleiro(), menu, menu.getJuego().getDado().getPosSiguiente());
                System.out.println(taboleiro);
                System.out.println(Valor.RESET + "En los dados te ha tocado un " + menu.getJuego().getDado().getDadoTotal() + ", y por estar en el modo avanzado de pelota, retrocedes "
                        + num + " posiciones, desde " + casillaAnterior + " hasta " + super.getCasilla().getNombreSinEspacio() + ".");
            }
        }
    }

    @Override
    public String toString() {
        String texto = "{\n" +
                "\tid: " + super.getId() + "\n" +
                "\ttipo: pelota" + "\n" +
                "\tcasilla: " + super.getCasilla().getNombre() + "\n" +
                "\tjugador: " + super.getJugador().getNombre() + "\n}";

        return texto;
    }
}
