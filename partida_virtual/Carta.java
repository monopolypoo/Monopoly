package partida_virtual;

import juego_fisico.Taboleiro;
import monopoly.Menu;
import monopoly.Valor;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;

public class Carta {
    private ArrayList<Carta> cartasSuerte;
    private ArrayList<Carta> cartasComunidad;
    private int tipo;

    public Carta() {
        this.cartasSuerte = new ArrayList<>();
        this.cartasComunidad = new ArrayList<>();

        Carta cartaSuerte1 = new Carta(1, "suerte");
        Carta cartaSuerte2 = new Carta(2, "suerte");
        Carta cartaSuerte3 = new Carta(3, "suerte");
        Carta cartaSuerte4 = new Carta(4, "suerte");
        Carta cartaSuerte5 = new Carta(5, "suerte");
        Carta cartaSuerte6 = new Carta(6, "suerte");
        Carta cartaSuerte7 = new Carta(7, "suerte");
        Carta cartaSuerte8 = new Carta(8, "suerte");
        Carta cartaSuerte9 = new Carta(9, "suerte");
        Carta cartaSuerte10 = new Carta(10, "suerte");
        Carta cartaSuerte11 = new Carta(11, "suerte");
        Carta cartaSuerte12 = new Carta(12, "suerte");
        Carta cartaSuerte13 = new Carta(13, "suerte");
        Carta cartaSuerte14 = new Carta(14, "suerte");

        this.cartasSuerte.add(cartaSuerte1);
        this.cartasSuerte.add(cartaSuerte2);
        this.cartasSuerte.add(cartaSuerte3);
        this.cartasSuerte.add(cartaSuerte4);
        this.cartasSuerte.add(cartaSuerte5);
        this.cartasSuerte.add(cartaSuerte6);
        this.cartasSuerte.add(cartaSuerte7);
        this.cartasSuerte.add(cartaSuerte8);
        this.cartasSuerte.add(cartaSuerte9);
        this.cartasSuerte.add(cartaSuerte10);
        this.cartasSuerte.add(cartaSuerte11);
        this.cartasSuerte.add(cartaSuerte12);
        this.cartasSuerte.add(cartaSuerte13);
        this.cartasSuerte.add(cartaSuerte14);

        Carta cartaComunidad1 = new Carta(1, "cajaDeComunidad");
        Carta cartaComunidad2 = new Carta(2, "cajaDeComunidad");
        Carta cartaComunidad3 = new Carta(3, "cajaDeComunidad");
        Carta cartaComunidad4 = new Carta(4, "cajaDeComunidad");
        Carta cartaComunidad5 = new Carta(5, "cajaDeComunidad");
        Carta cartaComunidad6 = new Carta(6, "cajaDeComunidad");
        Carta cartaComunidad7 = new Carta(7, "cajaDeComunidad");
        Carta cartaComunidad8 = new Carta(8, "cajaDeComunidad");
        Carta cartaComunidad9 = new Carta(9, "cajaDeComunidad");
        Carta cartaComunidad10 = new Carta(10, "cajaDeComunidad");

        this.cartasComunidad.add(cartaComunidad1);
        this.cartasComunidad.add(cartaComunidad2);
        this.cartasComunidad.add(cartaComunidad3);
        this.cartasComunidad.add(cartaComunidad4);
        this.cartasComunidad.add(cartaComunidad5);
        this.cartasComunidad.add(cartaComunidad6);
        this.cartasComunidad.add(cartaComunidad7);
        this.cartasComunidad.add(cartaComunidad8);
        this.cartasComunidad.add(cartaComunidad9);
        this.cartasComunidad.add(cartaComunidad10);
    }

    private Carta(int num, String cartas) {
        if (cartas != null) {
            if (cartas.equals("suerte")) {
                if (num >= 1 && num <= 14)
                    this.tipo = num;
                else
                    this.tipo = 0;
            } else if (cartas.equals("cajaDeComunidad")) {
                if (num >= 1 && num <= 10) {
                    this.tipo = num;
                } else
                    this.tipo = 0;
            }
        }
    }

    public ArrayList<Carta> getCartasComunidad() {
        return cartasComunidad;
    }

    public ArrayList<Carta> getCartasSuerte() {
        return cartasSuerte;
    }

    public int getTipo() {
        return tipo;
    }

    public void lanzarCartaSuerte(Jugador jugador, Taboleiro taboleiro, Menu menu) {
        int numero;
        Carta cartita;

        if (jugador != null && taboleiro != null && menu != null) {
            numero = (int) (Math.random() * 14 + 1);
            if (numero >= 1 && numero <= 14) {
                cartita = this.cartasSuerte.get(numero - 1);

                // Según el tipo que es, pues, es único para cada una
                switch (cartita.tipo) {
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
                    case 14:
                        cartasSuerte_14(jugador, taboleiro, menu);
                    default:
                }
            } else {
                System.out.println("Error al escoger la carta!");
            }
        } else {
            System.out.println("El jugador, el tablero o el menú no se encuentran inicializados!");
        }
    }

    /**********************************************************************************************
     **   NO COMPROBAMOS SI SE LE PASAN NULLS YA QUE LO HACEMOS EN LAS FUNCIONES QUE LOS LLAMAN   **
     **********************************************************************************************/

    private void cartaSuerte_1(Jugador jugador, Taboleiro taboleiro, Menu menu) {
        if (jugador.getAvatar().getCasilla().getPosicion() >= 25) {
            jugador.sumarFortuna(Valor.VUELTA);
            jugador.sumarVecesSalida();
            System.out.println("Has pasado por la casilla de salida, cobras: " + Valor.VUELTA + "€.");
            if (taboleiro.getCasillaPosicion(0).isSubirPrecio()) {
                taboleiro.subirPrecios();
            }
            taboleiro.subirPreciosTotal(menu);
        }
        taboleiro.getCasillaPosicion(jugador.getAvatar().getCasilla().getPosicion()).eliminarAvatar(jugador.getAvatar().getId());
        taboleiro.getCasillaPosicion(25).setAvatar(jugador.getAvatar());
        jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(25));
        System.out.println("Te encuentras en el aeropuerto, ahora vas a coger un avion e irás hasta dónde te lleve.");
    }

    private void cartasSuerte_2(Jugador jugador, Taboleiro taboleiro) {
        taboleiro.getCasillaPosicion(jugador.getAvatar().getCasilla().getPosicion()).eliminarAvatar(jugador.getAvatar().getId());
        taboleiro.getCasillaPosicion(39).setAvatar(jugador.getAvatar());
        jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(39));
        System.out.println("Te tocó un viaje hasta la casilla 'ETSE'.");
    }

    private void cartasSuerte_3(Jugador jugador) {
        jugador.sumarFortuna((float) Valor.VUELTA / 4);
        jugador.sumarPremiosInversionesBote((float) Valor.VUELTA / 4);
        System.out.println("Acabas de vender un viaje a Física en una subasta por internet por un precio de " + Valor.VUELTA / 4 + "€.");
    }

    private void cartasSuerte_4(Jugador jugador, Taboleiro taboleiro, Menu menu) {
        if (jugador.getAvatar().getCasilla().getPosicion() >= 23) {
            jugador.sumarFortuna(Valor.VUELTA);
            jugador.sumarVecesSalida();
            System.out.println("Has pasado por la casilla de salida, cobras: " + Valor.VUELTA + "€.");
            if (taboleiro.getCasillaPosicion(0).isSubirPrecio()) {
                taboleiro.subirPrecios();
            }
            taboleiro.subirPreciosTotal(menu);
        }
        taboleiro.getCasillaPosicion(jugador.getAvatar().getCasilla().getPosicion()).eliminarAvatar(jugador.getAvatar().getId());
        taboleiro.getCasillaPosicion(23).setAvatar(jugador.getAvatar());
        jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(23));
        System.out.println("Has avanzado hasta Filología.");
    }

    private void cartasSuerte_5(Jugador jugador, Taboleiro taboleiro) {
        taboleiro.getCasillaPosicion(jugador.getAvatar().getCasilla().getPosicion()).eliminarAvatar(jugador.getAvatar().getId());
        taboleiro.getCasillaPosicion(10).setAvatar(jugador.getAvatar());
        jugador.irCarcere(taboleiro);
        jugador.sumarVecesCarcel();
        taboleiro.setContadorVueltas(0);
        System.out.println("Los acreedores te persiguen por impago, Te vas directamente a la cárcel.");
    }

    private void cartasSuerte_6(Jugador jugador) {
        jugador.sumarFortuna((float) Valor.VUELTA / 2);
        jugador.sumarPremiosInversionesBote((float) Valor.VUELTA / 2);
        System.out.println("¡Has ganado el bote de la lotería! Recibes: " + Valor.VUELTA / 2 + "€.");
    }

    private void cartasSuerte_7(Jugador jugador, Taboleiro taboleiro) {
        if (jugador.getFortuna() < Valor.VUELTA) {
            System.out.println("Dinero insuficiente para pagar la matrícula del colegio privado. Debes vender edificios " +
                    "o hipotecar propiedades.");
        } else {
            taboleiro.getCasillaPosicion(20).sumarValor((float) Valor.VUELTA);
            jugador.restarFortuna(Valor.VUELTA);
            jugador.sumarTasasImpuestos(Valor.VUELTA);
            System.out.println("Acabas de pagar " + Valor.VUELTA + "€ por la matrícula del colegio privado.");
        }
    }

    private void cartasSuerte_8(Jugador jugador) {
        String[] partes;
        float contador = 0;
        int casas = 0, hoteles = 0, piscinas = 0, pistas = 0;
        for (String id : jugador.getEdificaciones()) {
            partes = id.split("-");
            switch (partes[0]) {
                case "casa":
                    contador += ((float) Valor.VUELTA / 2);
                    casas++;
                    break;
                case "hotel":
                    contador += ((float) Valor.VUELTA * 2);
                    hoteles++;
                    break;
                case "piscina":
                    contador += Valor.VUELTA;
                    piscinas++;
                    break;
                case "pista":
                    contador += ((float) Valor.VUELTA * 3);
                    pistas++;
                    break;
            }
        }

        System.out.println("Tienes " + casas + " casas, " + hoteles + " hoteles, " + piscinas + "piscinas y " + pistas + "pistas. Por lo que tienes que pagar " + contador + "€.");
        if (jugador.getFortuna() >= contador){
            jugador.restarFortuna(contador);
            jugador.setDineroGastado(jugador.getDineroGastado() + contador);
            jugador.sumarTasasImpuestos(contador);
            System.out.println("Pago efectuado.");
        } else{
            System.out.println("No tienes dinero suficiente para pagar los impuestos por edificios. Debes vender edificios " +
                    "o hipotecar propiedades.");
        }
    }

    private void cartasSuerte_9(Jugador jugador, Taboleiro taboleiro, Menu menu) {
        if (jugador.getAvatar().getCasilla().getPosicion() >= 13) {
            jugador.sumarFortuna(Valor.VUELTA);
            jugador.sumarVecesSalida();
            System.out.println("Has pasado por la casilla de salida, cobras: " + Valor.VUELTA + "€.");
            if (taboleiro.getCasillaPosicion(0).isSubirPrecio()) {
                taboleiro.subirPrecios();
            }
            taboleiro.subirPreciosTotal(menu);
        }
        taboleiro.getCasillaPosicion(jugador.getAvatar().getCasilla().getPosicion()).eliminarAvatar(jugador.getAvatar().getId());
        taboleiro.getCasillaPosicion(13).setAvatar(jugador.getAvatar());
        jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(13));
        System.out.println("Has avanzado hasta Turismo.");
    }

    private void cartasSuerte_10(Jugador jugador, Menu menu) {
        float dineroApagar = Valor.VUELTA * menu.getPartida().getJugadores().size() - 1;

        if (jugador.getFortuna() < dineroApagar) {
            System.out.println("Dinero insuficiente para pagarle a los " + (menu.getPartida().getJugadores().size() - 1) +
                    " jugadores la cantidad de: " + dineroApagar + ". Debes vender edificios o hipotecar propiedades.");
        } else {
            for (Jugador aPagar : menu.getPartida().getJugadores().values()) {
                if (!aPagar.getAvatar().getId().equals(jugador.getAvatar().getId())) {
                    aPagar.sumarFortuna(Valor.VUELTA);
                    System.out.println("Dinero pagado al jugador: " + aPagar.getNombre());
                }
            }
            jugador.restarFortuna(dineroApagar);
            jugador.sumarTasasImpuestos(dineroApagar);
            System.out.println("Acabas de pagarle " + Valor.VUELTA + " a los otros " + (menu.getPartida().getJugadores().size() - 1)
                    + " jugadores, ya que te han elegido presidente de la junta directiva!");
        }

    }

    private void cartasSuerte_11(Jugador jugador, Taboleiro taboleiro) {
        int pos = jugador.getAvatar().getCasilla().getPosicion();

        taboleiro.getCasillaPosicion(pos).eliminarAvatar(jugador.getAvatar().getId());
        if (pos < 3) {
            pos += 40;
        }
        pos -= 3;
        taboleiro.getCasillaPosicion(pos).setAvatar(jugador.getAvatar());
        jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(pos));
        System.out.println(taboleiro);
        System.out.println("A esta hora hay demasiado tráfico, por lo que tienes que retroceder 3 casillas!");

    }

    private void cartasSuerte_12(Jugador jugador, Taboleiro taboleiro) {
        if (jugador.getFortuna() < ((float) Valor.VUELTA / 4)) {
            System.out.println("Dinero insuficiente para pagar la multa de tráfico por usar el teléfono. Debes vender " +
                    "edificios o hipotecar propiedades.");
        } else {
            taboleiro.getCasillaPosicion(20).sumarValor((float) Valor.VUELTA / 4);
            jugador.restarFortuna((float) Valor.VUELTA / 4);
            jugador.sumarTasasImpuestos((float) Valor.VUELTA / 4);
            System.out.println("No se puede usar el móvil mientras se conduce, recuérdalo par la próxima vez. Pagas una " +
                    "multa de " + Valor.VUELTA / 4 + "€.");
        }
    }

    private void cartasSuerte_13(Jugador jugador) {
        System.out.println("Como beneficio por tus acciones recibes la cantidad de: " + Valor.VUELTA / 4 + "€.");
        jugador.sumarFortuna((float) Valor.VUELTA / 4);
        jugador.sumarPremiosInversionesBote((float) Valor.VUELTA / 4);
    }

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
        } else if (pos < 35){
            taboleiro.getCasillaPosicion(35).setAvatar(jugador.getAvatar());
            jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(35));
        } else{
            taboleiro.getCasillaPosicion(5).setAvatar(jugador.getAvatar());
            jugador.getAvatar().setCasilla(taboleiro.getCasillaPosicion(5));
        }
        if (jugador.getAvatar().getCasilla().getDuenho() == null){
            String[] respuesta;
            System.out.print("Esta casilla no tiene dueño, desea comprarla (si/no)?: ");
            respuesta = menu.leerComando();
            if (respuesta[0].toLowerCase().equals("si")) {
                jugador.comprarCasilla(jugador.getAvatar().getCasilla(), taboleiro);
            } else{
                System.out.println("De acuerdo, no se comprará.");
            }
        } else{
            if (!jugador.getAvatar().getCasilla().getDuenho().getAvatar().getId().equals(jugador.getAvatar().getId())) {
                System.out.println("Por elegir esta carta de suerte y caer en una casilla que ya tiene dueño, debes pagar el doble del alquiler.");
                jugador.pagarAlquiler(jugador.getAvatar().getCasilla(), 0, taboleiro, 2);
            } else{
                System.out.println("Has caído en una casilla que ya es tuya, por lo que no ocurre nada.");
            }
        }
    }

}

