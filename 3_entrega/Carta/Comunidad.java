package Carta;

import Casilla.*;
import ExcepcionesNumericas.ExcepcionesNumericas;
import ExcepcionesPartida.ExcepcionesDinero;
import Juego_fisico.*;
import Jugador.*;
import Monopoly.*;

import java.util.ArrayList;

public final class Comunidad extends Carta {

    public Comunidad() {
        super();
    }


    /***********************************************************************************************
     **   NO COMPROBAMOS SI SE LE PASAN NULLS YA QUE LO HACEMOS EN LAS FUNCIONES QUE LOS LLAMAN   **
     **********************************************************************************************/

    private void cartasComunidad_1(Jugador jugador, Taboleiro taboleiro) throws ExcepcionesDinero {
        super.setMovimiento(false);
        super.setTexto("Paga " + Valor.VUELTA + "€ por un fin de semana en un balneario de 5 estrellas.");
        if (jugador.getFortuna() < ((float) Valor.VUELTA)) {
            super.anhadirTexto("\nDinero insuficiente para pagar el fin de semana en el balneario de 5 estrellas. Debes vender " +
                    "edificios o hipotecar propiedades.");
        } else {
            ((Especial)taboleiro.getCasillaPosicion(20)).sumarBote((float) Valor.VUELTA);
            jugador.restarFortuna((float) Valor.VUELTA);
        }
    }

    private void cartasComunidad_2(Jugador jugador, Taboleiro taboleiro) throws ExcepcionesNumericas {
        super.setMovimiento(true);
        super.setTexto("Te investigan por fraude de identidad. Ve a la Cárcel. Ve directamente sin pasar por la casilla " +
                "de Salida y sin cobrar los " + Valor.VUELTA + "€.");
        int pos = jugador.getAvatar().getCasilla().getPosicion();
        taboleiro.getCasillaPosicion(pos).eliminarAvatar(jugador.getAvatar().getId());

        jugador.irCarcere(taboleiro);
        taboleiro.getCasillaPosicion(10).setAvatar(jugador.getAvatar());
        jugador.sumarVecesCarcel();
        taboleiro.setContadorVueltas(0);
    }

    private void cartasComunidad_3(Jugador jugador, Taboleiro taboleiro) {
        super.setMovimiento(true);
        super.setTexto("Colócate en la casilla de Salida. Cobra " + Valor.VUELTA + "€.");
        int pos = jugador.getAvatar().getCasilla().getPosicion();
        taboleiro.getCasillaPosicion(pos).eliminarAvatar(jugador.getAvatar().getId());

        taboleiro.getCasillaPosicion(0).setAvatar(jugador.getAvatar());
        jugador.sumarVecesSalida();
        jugador.sumarFortuna(Valor.VUELTA);
    }

    private void cartasComunidad_4(Jugador jugador) {
        super.setMovimiento(false);
        super.setTexto("Tu compañía de Internet ETSEfónica obtiene beneficios. Recibe " + Valor.VUELTA * 2 + "€.");
        jugador.sumarFortuna((float) Valor.VUELTA * 2);
        jugador.sumarPremiosInversionesBote((float) Valor.VUELTA * 2);
    }

    private void cartasComunidad_5(Jugador jugador) throws ExcepcionesDinero {
        super.setMovimiento(false);
        super.setTexto("Paga " + Valor.VUELTA + "€ por invitar a todos tus amigos a un viaje a Química.");
        if (jugador.getFortuna() < ((float) Valor.VUELTA)) {
            super.anhadirTexto("\nDinero insuficiente para pagar el viaje de tus amigos a Química. Debes vender " +
                    "edificios o hipotecar propiedades.");
        } else {
            jugador.restarFortuna((float) Valor.VUELTA);
        }
    }

    private void cartasComunidad_6(Jugador jugador) {
        super.setMovimiento(false);
        super.setTexto("Devolución de Hacienda. Cobra " + Valor.PRECIO_GRUPO5 + "€.");
        jugador.sumarFortuna(Valor.PRECIO_GRUPO5);
        jugador.sumarPremiosInversionesBote(Valor.PRECIO_GRUPO5);
    }

    private void cartasComunidad_7(Jugador jugador, Taboleiro taboleiro) {
        super.setMovimiento(true);
        super.setTexto("Retrocedes hasta 'FIC' para comprar programadores anticuados.");
        int pos = jugador.getAvatar().getCasilla().getPosicion();
        taboleiro.getCasillaPosicion(pos).eliminarAvatar(jugador.getAvatar().getId());
        taboleiro.getCasillaPosicion(19).setAvatar(jugador.getAvatar());
        jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(19));
    }

    private void cartasComunidad_8(Jugador jugador, Menu menu) throws ExcepcionesDinero {
        super.setMovimiento(false);
        Juego juego = menu.getJuego();
        int jugadores = juego.getJugadores().size() - 1;
        super.setTexto("Alquilas una villa en la 'ETSE' durante una semana, le pagas a cada uno de los jugadores " +
                Valor.VUELTA / 2 + "€.");
        if (jugador.getFortuna() < ((float) Valor.VUELTA / 2) * jugadores) {
            super.anhadirTexto("\nDinero insuficiente para pagar el viaje de tus amigos a Química. Debes vender " +
                    "edificios o hipotecar propiedades.");
        } else {
            jugador.restarFortuna(((float) Valor.VUELTA / 2) * jugadores);
            for (Jugador jugador1 : juego.getJugadores()) {
                jugador1.sumarFortuna((float) Valor.VUELTA / 2);
                jugador1.sumarPremiosInversionesBote((float) Valor.VUELTA / 2);
            }
        }
    }

    private void cartasComunidad_9(Jugador jugador) {
        super.setMovimiento(false);
        super.setTexto("Recibes " + Valor.VUELTA + "€ por el alquiler de tu jet privado.");
        jugador.sumarFortuna(Valor.VUELTA);
        jugador.sumarPremiosInversionesBote(Valor.VUELTA);
    }

    private void cartasComunidad_10(Jugador jugador, Taboleiro taboleiro, Menu menu) throws ExcepcionesNumericas {
        super.setMovimiento(true);
        super.setTexto("Ve a la 'Ing. Caminos' a disfrutar de sus demostraciones de extracción de minerales." +
                " Si pasas por la casilla de Salida, cobra " + Valor.VUELTA + "€.");
        if (jugador.getAvatar().getCasilla().getPosicion() >= 18) {
            jugador.sumarFortuna(Valor.VUELTA);
            jugador.sumarVecesSalida();
            super.anhadirTexto("\nHas pasado por la casilla de salida, cobras: " + Valor.VUELTA + "€.");
            if (taboleiro.getCasillaPosicion(0).isSubirPrecio()) {
                taboleiro.subirPrecios();
            }
            taboleiro.subirPreciosTotal(menu.getJuego());
        }
        taboleiro.getCasillaPosicion(jugador.getAvatar().getCasilla().getPosicion()).eliminarAvatar(jugador.getAvatar().getId());
        taboleiro.getCasillaPosicion(18).setAvatar(jugador.getAvatar());
        jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(18));
    }

    @Override
    public void accion(Jugador jugador, Taboleiro taboleiro, Menu menu, int escogida) throws ExcepcionesDinero, ExcepcionesNumericas {
        int numero;

        if (jugador != null && taboleiro != null && menu != null) {
            numero = barajar(escogida);
            if (numero >= 1 && numero <= 10) {

                switch (numero) {
                    case 1:
                        cartasComunidad_1(jugador, taboleiro);
                        break;
                    case 2:
                        cartasComunidad_2(jugador, taboleiro);
                        break;
                    case 3:
                        cartasComunidad_3(jugador, taboleiro);
                        break;
                    case 4:
                        cartasComunidad_4(jugador);
                        break;
                    case 5:
                        cartasComunidad_5(jugador);
                        break;
                    case 6:
                        cartasComunidad_6(jugador);
                        break;
                    case 7:
                        cartasComunidad_7(jugador, taboleiro);
                        break;
                    case 8:
                        cartasComunidad_8(jugador, menu);
                        break;
                    case 9:
                        cartasComunidad_9(jugador);
                        break;
                    case 10:
                        cartasComunidad_10(jugador, taboleiro, menu);
                        break;
                    default:
                        System.out.println("Error al escoger la carta!");
                        break;
                }
            } else {
                System.out.println("Error al escoger la carta!");
            }
        } else {
            System.out.println("El jugador, el tablero o el menú no se encuentran inicializados!");

        }
    }

    @Override
    public int barajar(int escogida) {
        int numero;
        ArrayList<Integer> cartas = new ArrayList<>();

        while (cartas.size() < 10) {
            numero = (int) (Math.random() * 10 + 1);
            if (!estaNumero(numero, cartas))
                cartas.add(numero);
        }
        return cartas.get(escogida);
    }
}
