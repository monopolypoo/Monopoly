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
                    //añadir descripcion de lo que va a pasar
                    modoCoche(jugador, taboleiro, menu);
                    break;

                case "pelota":
                    //añadir descripcion de lo que va a pasar
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
            this.texto = "\nAcabas de obtener un valor en los dados igual o inferior a 4, por lo que tu turno se " +
                    "acaba y sufres la penalización de estar dos turnos sin poder lanzar dados.";
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

            //AÑADIR AQUI LO DE LAS CARTAS DE SUERTE Y CAJA DE COMUNIDAD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        }
    }

    public int sumarImpar(int vez) {
        if (vez == 0) {
            return 5;
        } else {
            return 2;
        }
    }

    public void modoPelota(Jugador jugador, Taboleiro taboleiro, Menu menu) {
        this.seguirPelota = true;
        if (this.dadoTotal > 4) {
            String[] comando;
            String casillaAnterior;
            int contador = 0;
            if (this.iguales){
                System.out.println("Has sacado dobles, cuando termines con el modo avanzado de pelota, tendrás que volver a tirar los dados.");
            } else{
                menu.setContadorDobles(0);
            }
            while (this.seguirPelota) {
                if (contador >= this.dadoTotal) {
                    this.seguirPelota = false;
                    this.numeroPelota = 0;
                    this.vecesPelota = 0;
                    System.out.println(Valor.RESET + "El modo avanzado de pelota ha finalizado.");
                } else if ((contador + 1) == this.dadoTotal) {
                    this.posActual = jugador.getAvatar().getCasilla().getPosicion();
                    casillaAnterior = jugador.getAvatar().getCasilla().getNombreSinEspacio();
                    this.posSiguiente = this.posActual + 1;
                    contador++;
                    taboleiro.getCasillaPosicion(this.posActual).eliminarAvatar(jugador.getAvatar().getId());
                    modoNormal(jugador, taboleiro, menu);
                    this.seguirPelota = false;
                    this.numeroPelota = 0;
                    this.vecesPelota = 0;
                    System.out.println(taboleiro);
                    System.out.println(Valor.RESET + "En los dados te ha tocado un " + this.dadoTotal + ", y por estar en el modo avanzado de pelota, avanzas 1 posición, desde "
                            + casillaAnterior + " hasta " + jugador.getAvatar().getCasilla().getNombreSinEspacio() + ".");
                    jugador.pagarAlquiler(jugador.getAvatar().getCasilla(), this.dadoTotal, taboleiro, 1);
                    jugador.pagarImpuestos(jugador.getAvatar().getCasilla(), taboleiro);
                    jugador.cobrarParking(jugador.getAvatar().getCasilla());
                    if (taboleiro.sePuedeComprar(jugador.getAvatar().getCasilla()) && (jugador.getAvatar().getCasilla().getDuenho() == null)) {
                        System.out.print(Valor.RESET + "Estás en la casilla " + jugador.getAvatar().getCasilla().getNombreSinEspacio() + " y se puede comprar, desea comprarla (si/no)? ");
                        comando = menu.leerComando();
                        if (comando[0].toLowerCase().equals("si")) {
                            jugador.comprarCasilla(jugador.getAvatar().getCasilla(), taboleiro);
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
                    this.posActual = jugador.getAvatar().getCasilla().getPosicion();
                    casillaAnterior = jugador.getAvatar().getCasilla().getNombreSinEspacio();
                    this.posSiguiente = this.posActual + this.numeroPelota;
                    taboleiro.getCasillaPosicion(this.posActual).eliminarAvatar(jugador.getAvatar().getId());
                    modoNormal(jugador, taboleiro, menu);
                    this.vecesPelota++;
                    System.out.println(taboleiro);
                    System.out.println(Valor.RESET + "En los dados te ha tocado un " + this.dadoTotal + ", y por estar en el modo avanzado de pelota, avanzas "
                            + this.numeroPelota + " posiciones, desde " + casillaAnterior + " hasta " + jugador.getAvatar().getCasilla().getNombreSinEspacio() + ".");
                    jugador.pagarAlquiler(jugador.getAvatar().getCasilla(), this.dadoTotal, taboleiro, 1);
                    jugador.pagarImpuestos(jugador.getAvatar().getCasilla(), taboleiro);
                    jugador.cobrarParking(jugador.getAvatar().getCasilla());
                }
                if (jugador.getEstarCarcere()) {
                    this.seguirPelota = false;
                    this.numeroPelota = 0;
                    this.vecesPelota = 0;
                    System.out.println(Valor.RESET + "Caíste en la casilla Ir Cárcel, por lo que ahora estás en la cárcel.");
                    System.out.println("El modo avanzado de pelota ha finalizado.");
                }
                if (this.seguirPelota) {
                    if (taboleiro.sePuedeComprar(jugador.getAvatar().getCasilla()) && (jugador.getAvatar().getCasilla().getDuenho() == null)) {
                        System.out.print(Valor.RESET + "Estás en la casilla " + jugador.getAvatar().getCasilla().getNombreSinEspacio() + " y se puede comprar, desea comprarla (si/no)? ");
                        comando = menu.leerComando();
                        if (comando[0].toLowerCase().equals("si")) {
                            jugador.comprarCasilla(jugador.getAvatar().getCasilla(), taboleiro);
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
            while(this.seguirPelota) {
                if (this.vecesPelota == 0) {
                    if (this.dadoTotal == 4) {
                        this.posSiguiente = this.posActual - 3;
                        this.vecesPelota++;
                        num = 0;
                    } else {
                        this.posSiguiente = this.posActual - this.dadoTotal;
                        this.seguirPelota = false;
                        this.numeroPelota = 0;
                        this.vecesPelota = 0;
                        num = this.dadoTotal;
                    }
                } else {
                    this.posSiguiente = this.posActual - 1;
                    this.seguirPelota = false;
                    this.numeroPelota = 0;
                    this.vecesPelota = 0;
                    num = 1;
                }
                if (this.posSiguiente < 0) {
                    this.posSiguiente = 40 + this.posSiguiente;
                }
                this.posActual = jugador.getAvatar().getCasilla().getPosicion();
                casillaAnterior = jugador.getAvatar().getCasilla().getNombreSinEspacio();
                taboleiro.getCasillaPosicion(this.posActual).eliminarAvatar(jugador.getAvatar().getId());
                modoNormal(jugador, taboleiro, menu);
                System.out.println(taboleiro);
                System.out.println(Valor.RESET + "En los dados te ha tocado un " + this.dadoTotal + ", y por estar en el modo avanzado de pelota, retrocedes "
                        + num + " posiciones, desde " + casillaAnterior + " hasta " + jugador.getAvatar().getCasilla().getNombreSinEspacio() + ".");
            }
        }
    }

    public String textoLanzarDados(Taboleiro taboleiro, Jugador jugador, Menu menu) {
        String texto;
        String[] comando;
        int sumaDados = this.dado1 + this.dado2;
        if (jugador.getAvatar().getModoAvanzado() && jugador.getAvatar().getTipo().equals("coche") && sumaDados <= 4) {
            texto = " retrocede " + sumaDados + " posiciones, desde " + taboleiro.getCasillaPosicion(this.posActual).getNombreSinEspacio() +
                    " hasta " + taboleiro.getCasillaPosicion(this.posSiguiente).getNombreSinEspacio() + ". " + this.texto;
        } else {
            texto = " avanza " + sumaDados + " posiciones, desde " + taboleiro.getCasillaPosicion(this.posActual).getNombreSinEspacio() +
                    " hasta " + taboleiro.getCasillaPosicion(this.posSiguiente).getNombreSinEspacio() + ". ";
        }
        if ((taboleiro.getCarta().getTexto() != null) && (!taboleiro.getCarta().getTexto().equals(""))){
            texto += "\n" + jugador.getNombre() + ", elige una carta: ";
            menu.leerComando();
            texto += "Acción: " + taboleiro.getCarta().getTexto();
        }

        return texto;
    }
}