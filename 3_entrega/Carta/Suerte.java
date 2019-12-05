package Carta;

import Casilla.*;
import Consola.*;
import Edificio.*;
import ExcepcionesNull.ExcepcionesNull;
import ExcepcionesNumericas.ExcepcionesNumericas;
import ExcepcionesPartida.ExcepcionesDinero;
import Juego_fisico.*;
import Jugador.*;
import Monopoly.*;

import java.util.ArrayList;

public final class Suerte extends Carta {

    public Suerte() {
        super();
    }

    /***********************************************************************************************
     **   NO COMPROBAMOS SI SE LE PASAN NULLS YA QUE LO HACEMOS EN LAS FUNCIONES QUE LOS LLAMAN   **
     **********************************************************************************************/

    private void cartaSuerte_1(Jugador jugador, Taboleiro taboleiro, Menu menu) throws ExcepcionesNumericas {
        super.setMovimiento(true);
        super.setTexto("Ve al Aeropuerto y coge un avión. Si pasas por la casilla de Salida, cobra " + Valor.VUELTA + "€. ");
        if (jugador.getAvatar().getCasilla().getPosicion() >= 25) {
            jugador.sumarFortuna(Valor.VUELTA);
            jugador.sumarVecesSalida();
            super.anhadirTexto("\nHas pasado por la casilla de salida, cobras: " + Valor.VUELTA + "€.");
            if (taboleiro.getCasillaPosicion(0).isSubirPrecio()) {
                taboleiro.subirPrecios();
            }
            taboleiro.subirPreciosTotal(menu.getJuego());
        }
        taboleiro.getCasillaPosicion(jugador.getAvatar().getCasilla().getPosicion()).eliminarAvatar(jugador.getAvatar().getId());
        taboleiro.getCasillaPosicion(25).setAvatar(jugador.getAvatar());
        jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(25));
    }

    private void cartasSuerte_2(Jugador jugador, Taboleiro taboleiro) {
        super.setMovimiento(true);
        super.setTexto("Decides hacer un viaje de placer. Avanza hasta ETSE.");
        taboleiro.getCasillaPosicion(jugador.getAvatar().getCasilla().getPosicion()).eliminarAvatar(jugador.getAvatar().getId());
        taboleiro.getCasillaPosicion(39).setAvatar(jugador.getAvatar());
        jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(39));
    }

    private void cartasSuerte_3(Jugador jugador) {
        super.setMovimiento(false);
        super.setTexto("Vendes tu billete de avión para Física en una subasta por Internet. Cobra " + Valor.VUELTA / 4 + "€.");
        jugador.sumarFortuna((float) Valor.VUELTA / 4);
        jugador.sumarPremiosInversionesBote((float) Valor.VUELTA / 4);
    }

    private void cartasSuerte_4(Jugador jugador, Taboleiro taboleiro, Menu menu) throws ExcepcionesNumericas {
        super.setMovimiento(true);
        super.setTexto("Ve a Filología. Si pasas por la casilla de Salida, cobra " + Valor.VUELTA + "€.");
        if (jugador.getAvatar().getCasilla().getPosicion() >= 23) {
            jugador.sumarFortuna(Valor.VUELTA);
            jugador.sumarVecesSalida();
            super.anhadirTexto("\nHas pasado por la casilla de salida, cobras: " + Valor.VUELTA + "€.");
            if (taboleiro.getCasillaPosicion(0).isSubirPrecio()) {
                taboleiro.subirPrecios();
            }
            taboleiro.subirPreciosTotal(menu.getJuego());
        }
        taboleiro.getCasillaPosicion(jugador.getAvatar().getCasilla().getPosicion()).eliminarAvatar(jugador.getAvatar().getId());
        taboleiro.getCasillaPosicion(23).setAvatar(jugador.getAvatar());
        jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(23));
    }

    private void cartasSuerte_5(Jugador jugador, Taboleiro taboleiro) throws ExcepcionesNumericas {
        super.setMovimiento(true);
        super.setTexto("Los acreedores te persiguen por impago. Ve a la Cárcel. Ve directamente sin pasar por la casilla de " +
                "Salida y sin cobrar los " + Valor.VUELTA + "€.");
        taboleiro.getCasillaPosicion(jugador.getAvatar().getCasilla().getPosicion()).eliminarAvatar(jugador.getAvatar().getId());
        taboleiro.getCasillaPosicion(10).setAvatar(jugador.getAvatar());
        jugador.irCarcere(taboleiro);
        jugador.sumarVecesCarcel();
        taboleiro.setContadorVueltas(0);
    }

    private void cartasSuerte_6(Jugador jugador) {
        super.setMovimiento(false);
        super.setTexto("¡Has ganado el bote de la lotería! Recibe " + Valor.VUELTA / 2 + "€.");
        jugador.sumarFortuna((float) Valor.VUELTA / 2);
        jugador.sumarPremiosInversionesBote((float) Valor.VUELTA / 2);
    }

    private void cartasSuerte_7(Jugador jugador, Taboleiro taboleiro) throws ExcepcionesDinero {
        super.setMovimiento(false);
        super.setTexto("Paga " + Valor.VUELTA + "€ por la matrícula del colegio privado.");
        if (jugador.getFortuna() < Valor.VUELTA) {
            super.anhadirTexto("\nDinero insuficiente para pagar la matrícula del colegio privado. Debes vender edificios " +
                    "o hipotecar propiedades.");
        } else {
            ((Especial) taboleiro.getCasillaPosicion(20)).sumarBote((float) Valor.VUELTA);
            jugador.restarFortuna(Valor.VUELTA);
            jugador.sumarTasasImpuestos(Valor.VUELTA);
        }
    }

    private void cartasSuerte_8(Jugador jugador) throws ExcepcionesDinero {
        super.setMovimiento(false);
        float contador = 0;
        int casas = 0, hoteles = 0, piscinas = 0, pistas = 0;
        for (Edificio edificio : jugador.getEdificaciones()) {
            if (edificio instanceof Casa) {
                contador += ((float) Valor.VUELTA / 2);
                casas++;
            } else if (edificio instanceof Hotel) {
                contador += ((float) Valor.VUELTA * 2);
                hoteles++;
            } else if (edificio instanceof Piscina) {
                contador += Valor.VUELTA;
                piscinas++;
            } else if (edificio instanceof Pista) {
                contador += ((float) Valor.VUELTA * 3);
                pistas++;
            }
        }
        super.setTexto("El aumento del impuesto sobre bienes inmuebles afecta a todas tus propiedades. Paga " + Valor.VUELTA / 2 + "€ por casa, " +
                Valor.VUELTA * 2 + "€ por hotel, " + Valor.VUELTA + "€ por piscina y " + Valor.VUELTA * 3 + "€ por pista de deporte." +
                "\nTienes " + casas + " casas, " + hoteles + " hoteles, " + piscinas + "piscinas y " + pistas +
                "pistas. Por lo que tienes que pagar " + contador + "€.");
        jugador.restarFortuna(contador);
        jugador.setDineroGastado(jugador.getDineroGastado() + contador);
        jugador.sumarTasasImpuestos(contador);
        super.anhadirTexto("\nPago efectuado.");
    }

    private void cartasSuerte_9(Jugador jugador, Taboleiro taboleiro, Menu menu) throws ExcepcionesNumericas {
        super.setTexto("Ve a Turismo. Si pasas por la casilla de Salida, cobra " + Valor.VUELTA + "€.");
        super.setMovimiento(true);
        if (jugador.getAvatar().getCasilla().getPosicion() >= 13) {
            jugador.sumarFortuna(Valor.VUELTA);
            jugador.sumarVecesSalida();
            super.anhadirTexto("\nHas pasado por la casilla de salida, cobras: " + Valor.VUELTA + "€.");
            if (taboleiro.getCasillaPosicion(0).isSubirPrecio()) {
                taboleiro.subirPrecios();
            }
            taboleiro.subirPreciosTotal(menu.getJuego());
        }
        taboleiro.getCasillaPosicion(jugador.getAvatar().getCasilla().getPosicion()).eliminarAvatar(jugador.getAvatar().getId());
        taboleiro.getCasillaPosicion(13).setAvatar(jugador.getAvatar());
        jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(13));
    }

    private void cartasSuerte_10(Jugador jugador, Menu menu) throws ExcepcionesDinero {
        super.setTexto("Has sido elegido presidente de la junta directiva. Paga a cada jugador " + Valor.VUELTA + "€");
        super.setMovimiento(false);
        float dineroApagar = Valor.VUELTA * (menu.getJuego().getJugadores().size() - 1);

        if (jugador.getFortuna() < dineroApagar) {
            super.anhadirTexto("\nDinero insuficiente para pagarle a los " + (menu.getJuego().getJugadores().size() - 1) +
                    " jugadores la cantidad de: " + dineroApagar + ". Debes vender edificios o hipotecar propiedades.");
        } else {
            for (Jugador aPagar : menu.getJuego().getJugadores()) {
                if (!aPagar.getAvatar().getId().equals(jugador.getAvatar().getId())) {
                    aPagar.sumarFortuna(Valor.VUELTA);
                }
            }
            jugador.restarFortuna(dineroApagar);
            jugador.sumarTasasImpuestos(dineroApagar);
            super.anhadirTexto("\nAcabas de pagar un total de  " + dineroApagar + " a los otros " + (menu.getJuego().getJugadores().size() - 1)
                    + " jugadores.");
        }

    }

    private void cartasSuerte_11(Jugador jugador, Taboleiro taboleiro) {
        super.setTexto("¡Hora punta de tráfico! Retrocede tres casillas.");
        super.setMovimiento(true);
        int pos = jugador.getAvatar().getCasilla().getPosicion();

        taboleiro.getCasillaPosicion(pos).eliminarAvatar(jugador.getAvatar().getId());
        if (pos < 3) {
            pos += 40;
        }
        pos -= 3;
        taboleiro.getCasillaPosicion(pos).setAvatar(jugador.getAvatar());
        jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(pos));
        super.anhadirTexto("\n" + taboleiro);
    }

    private void cartasSuerte_12(Jugador jugador, Taboleiro taboleiro) throws ExcepcionesDinero {
        super.setTexto("Te multan por usar el móvil mientras conduces. Paga " + Valor.VUELTA / 4 + "€.");
        super.setMovimiento(false);
        if (jugador.getFortuna() < ((float) Valor.VUELTA / 4)) {
            super.anhadirTexto("\nDinero insuficiente para pagar la multa de tráfico por usar el teléfono. Debes vender " +
                    "edificios o hipotecar propiedades.");
        } else {
            ((Especial) taboleiro.getCasillaPosicion(20)).sumarBote((float) Valor.VUELTA / 4);
            jugador.restarFortuna((float) Valor.VUELTA / 4);
            jugador.sumarTasasImpuestos((float) Valor.VUELTA / 4);
        }
    }

    private void cartasSuerte_13(Jugador jugador) {
        super.setMovimiento(false);
        super.setTexto("Beneficio por la venta de tus acciones. Recibe " + Valor.VUELTA / 4 + "€.");
        jugador.sumarFortuna((float) Valor.VUELTA / 4);
        jugador.sumarPremiosInversionesBote((float) Valor.VUELTA / 4);
    }


    /*
    private void cartasSuerte_14(Jugador jugador, Taboleiro taboleiro, Menu menu) {
        int pos = jugador.getAvatar().getCasilla().getPosicion();
        taboleiro.getCasillaPosicion(pos).eliminarAvatar(jugador.getAvatar().getId());
        if (pos < 5) {
            taboleiro.getCasillaPosicion(5).setAvatar(jugador.getAvatar());
            jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(5));
        } else if (pos < 15) {
            taboleiro.getCasillaPosicion(15).setAvatar(jugador.getAvatar());
            jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(15));
        } else if (pos < 25) {
            taboleiro.getCasillaPosicion(25).setAvatar(jugador.getAvatar());
            jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(25));
        } else if (pos < 35) {
            taboleiro.getCasillaPosicion(35).setAvatar(jugador.getAvatar());
            jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(35));
        } else {
            taboleiro.getCasillaPosicion(5).setAvatar(jugador.getAvatar());
            jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(5));
        }
        if (jugador.getAvatar().getCasilla().getDuenho() == null) {
            String[] respuesta;
            System.out.print("Esta casilla no tiene dueño, desea comprarla (si/no)?: ");
            respuesta = menu.leerComando();
            if (respuesta[0].toLowerCase().equals("si")) {
                jugador.comprarCasilla(jugador.getAvatar().getCasilla(), taboleiro);
            } else {
                System.out.println("De acuerdo, no se comprará.");
            }
        } else {
            if (!jugador.getAvatar().getCasilla().getDuenho().getAvatar().getId().equals(jugador.getAvatar().getId())) {
                System.out.println("Por elegir esta carta de suerte y caer en una casilla que ya tiene dueño, debes pagar el doble del alquiler.");
                jugador.pagarAlquiler(jugador.getAvatar().getCasilla(), 0, taboleiro, 2);
            } else {
                System.out.println("Has caído en una casilla que ya es tuya, por lo que no ocurre nada.");
            }
        }
    }

     */

    @Override
    public int barajar(int escogida) {
        int numero;
        ArrayList<Integer> cartas = new ArrayList<>();

        while (cartas.size() < 13) {
            numero = (int) (Math.random() * 13 + 1);
            if (!estaNumero(numero, cartas))
                cartas.add(numero);
        }
        return cartas.get(escogida);
    }

    @Override
    public void accion(Jugador jugador, Taboleiro taboleiro, Menu menu, int escogida) throws ExcepcionesDinero, ExcepcionesNull, ExcepcionesNumericas {
        int numero;

        if (jugador != null && taboleiro != null && menu != null) {
            numero = barajar(escogida);
            if (numero >= 1 && numero <= 13) {

                // Según el tipo que es, pues, es único para cada una
                switch (numero) {
                    case 1:
                        cartaSuerte_1(jugador, taboleiro, menu);
                        break;
                    case 2:
                        cartasSuerte_2(jugador, taboleiro);
                        break;
                    case 3:
                        cartasSuerte_3(jugador);
                        break;
                    case 4:
                        cartasSuerte_4(jugador, taboleiro, menu);
                        break;
                    case 5:
                        cartasSuerte_5(jugador, taboleiro);
                        break;
                    case 6:
                        cartasSuerte_6(jugador);
                        break;
                    case 7:
                        cartasSuerte_7(jugador, taboleiro);
                        break;
                    case 8:
                        cartasSuerte_8(jugador);
                        break;
                    case 9:
                        cartasSuerte_9(jugador, taboleiro, menu);
                        break;
                    case 10:
                        cartasSuerte_10(jugador, menu);
                        break;
                    case 11:
                        cartasSuerte_11(jugador, taboleiro);
                        break;
                    case 12:
                        cartasSuerte_12(jugador, taboleiro);
                        break;
                    case 13:
                        cartasSuerte_13(jugador);
                        break;
                    /*
                    case 14:
                        cartasSuerte_14(jugador, taboleiro, menu);
                        break;
                     */
                    default:
                        Juego.consola.imprimir("Error al escoger la carta!");
                        break;
                }
            } else {
                Juego.consola.imprimir("Error al escoger la carta!");
            }
        } else throw new ExcepcionesNull("El jugador, el tablero o el menú no se encuentran inicializados!");
    }
}
