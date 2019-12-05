package Casilla;

import Consola.*;
import Edificio.*;
import ExcepcionesNull.*;
import ExcepcionesNumericas.ExcepcionesNumericas;
import ExcepcionesPartida.*;
import Juego_fisico.*;
import Jugador.*;

import java.util.ArrayList;
import java.util.Scanner;

public final class Solar extends Propiedad {
    private double valorCasa;
    private double valorPiscina;
    private double valorPistaDeporte;
    private double valorHotel;
    private ArrayList<Casa> casas;
    private ArrayList<Hotel> hoteles;
    private ArrayList<Piscina> piscinas;
    private ArrayList<Pista> pistas;
    private int numeroCasas;
    private int numeroHoteles;
    private int numeroPiscinas;
    private int numeroPistas;
    private Consola consola;

    public Solar() {
        super();
        this.valorCasa = 0;
        this.valorPiscina = 0;
        this.valorPistaDeporte = 0;
        this.valorHotel = 0;
        this.casas = new ArrayList<>();
        this.hoteles = new ArrayList<>();
        this.piscinas = new ArrayList<>();
        this.pistas = new ArrayList<>();
        this.numeroCasas = 0;
        this.numeroHoteles = 0;
        this.numeroPiscinas = 0;
        this.numeroPistas = 0;
        this.consola = new ConsolaNormal();
    }

    public Solar(String nombre, int posicion, double valor) {
        super(nombre, posicion, valor);
        this.valorCasa = 0.6 * super.getValor();
        this.valorPiscina = 0.4 * super.getValor();
        this.valorPistaDeporte = 1.25 * super.getValor();
        this.valorHotel = this.valorCasa;
        this.casas = new ArrayList<>();
        this.hoteles = new ArrayList<>();
        this.piscinas = new ArrayList<>();
        this.pistas = new ArrayList<>();
        this.numeroCasas = 0;
        this.numeroHoteles = 0;
        this.numeroPiscinas = 0;
        this.numeroPistas = 0;
        this.consola = new ConsolaNormal();
    }

    public double getValorCasa() {
        return this.valorCasa;
    }

    public double getValorHotel() {
        return this.valorHotel;
    }

    public double getValorPiscina() {
        return this.valorPiscina;
    }

    public double getValorPistaDeporte() {
        return this.valorPistaDeporte;
    }

    public ArrayList<Casa> getCasas() {
        return this.casas;
    }

    public ArrayList<Hotel> getHoteles() {
        return this.hoteles;
    }

    public ArrayList<Piscina> getPiscinas() {
        return this.piscinas;
    }

    public ArrayList<Pista> getPistas() {
        return this.pistas;
    }

    public void añadirCasa(Casa casa) {
        if (casa != null) {
            this.casas.add(casa);
        }
    }

    public void añadirHotel(Hotel hotel) {
        if (hotel != null) {
            this.hoteles.add(hotel);
        }
    }

    public void añadirPiscina(Piscina piscina) {
        if (piscina != null) {
            this.piscinas.add(piscina);
        }
    }

    public void añadirPistas(Pista pista) {
        if (pista != null) {
            this.pistas.add(pista);
        }
    }

    public void eliminarCasa(Casa casa) {
        if (casa != null) {
            this.casas.remove(casa);
        }
    }

    public void eliminarHotel(Hotel hotel) {
        if (hotel != null) {
            this.hoteles.remove(hotel);
        }
    }

    public void eliminarPiscina(Piscina piscina) {
        if (piscina != null) {
            this.piscinas.remove(piscina);
        }
    }

    public void eliminarPistas(Pista pista) {
        if (pista != null) {
            this.pistas.remove(pista);
        }
    }

    public int getNumeroCasas() {
        return numeroCasas;
    }

    public int getNumeroHoteles() {
        return numeroHoteles;
    }

    public int getNumeroPiscinas() {
        return numeroPiscinas;
    }

    public int getNumeroPistas() {
        return numeroPistas;
    }

    public void setNumeroCasas(int numero) {
        this.numeroCasas = numero;
    }

    public void setNumeroHoteles(int numero) {
        this.numeroHoteles = numero;
    }

    public void setNumeroPiscinas(int numero) {
        this.numeroPiscinas = numero;
    }

    public void setNumeroPistas(int numero) {
        this.numeroPistas = numero;
    }

    public void edificar(String tipo, Jugador jugador, Taboleiro taboleiro) throws ExcepcionesEdificios, ExcepcionesDinero, ExcepcionesDuenho, ExcepcionesNumericas {
        String id;
        switch (tipo) {
            case "casa":
                id = taboleiro.idCasa(this);
                this.construirEdificio(tipo, jugador, taboleiro, id);
                break;
            case "hotel":
                id = taboleiro.idHotel(this);
                this.construirEdificio(tipo, jugador, taboleiro, id);
                break;
            case "piscina":
                id = taboleiro.idPiscina(this);
                this.construirEdificio(tipo, jugador, taboleiro, id);
                break;
            case "pista":
                id = taboleiro.idPista(this);
                this.construirEdificio(tipo, jugador, taboleiro, id);
                break;
            default:
                throw new ExcepcionesEdificios("ERROR, tipo de edificio erróneo.");
        }
    }

    public void eliminarCasas(Jugador jugador, Taboleiro taboleiro) {
        if (this.casas.size() >= 4) {
            for (Casa casa : this.casas) {
                taboleiro.eliminarCasa(casa.getId());
                jugador.eliminarEdificacion(casa);
            }
            for (int i = 3; i >= 0; --i) {
                this.casas.remove(i);
            }
        }
    }

    public boolean hayEdificios() {
        if (this.casas.size() > 0)
            return true;
        if (this.hoteles.size() > 0)
            return true;
        if (this.pistas.size() > 0)
            return true;
        if (this.piscinas.size() > 0)
            return true;
        return false;
    }

    private void construirEdificio(String edificio, Jugador jugador, Taboleiro taboleiro, String id) throws ExcepcionesDinero, ExcepcionesEdificios, ExcepcionesDuenho, ExcepcionesNumericas {
        if (super.getDuenho() != null) {
            if (super.getDuenho().equals(jugador)) {
                int aux;
                if (super.getVecesCasilla().containsKey(jugador.getAvatar().getId())) {
                    try {
                        aux = Integer.parseInt(super.getVecesCasilla().get(jugador.getAvatar().getId())[1]);
                    } catch (NumberFormatException exc) {
                        throw new ExcepcionesNumericas("Error pasando el string a entero.");
                    }
                } else {
                    aux = 0;
                }

                if (super.tenerTodasCasillas() || aux >= 2) {
                    switch (edificio) {
                        case "casa":
                            if (this.numeroCasas < 4) {
                                new Casa(this.valorCasa, id, this);
                                consola.imprimir("El avatar " + super.getDuenho().getAvatar().getId() +
                                        " ha construído una casa en la casilla " + super.getNombreSinEspacio() +
                                        " por un valor de: " + this.valorCasa +
                                        "\nLa fortuna actual del jugador es de: " + super.getDuenho().getFortuna());
                            } else {

                                /* Intentar hacer esto con la interface de comando!!!!!!!!!!!!!!*/

                                String[] comando;
                                comando = consola.leer("Ya hay 4 casas construídas! Quiere construír un hotel? [si/no] ");

                                if (comando[0].toLowerCase().equals("si")) {
                                    construirEdificio("hotel", jugador, taboleiro, id);
                                } else
                                    consola.imprimir("De acuerdo. No se hará ninguna acción.");
                            }
                            break;

                        case "hotel":
                            if (this.numeroCasas == 4 && (super.getGrupo().getNumeroHoteles() < super.getGrupo().getNumeroSolares())) {
                                new Hotel(this.valorHotel, id, this);
                                this.eliminarCasas(jugador, taboleiro);
                                consola.imprimir("El avatar " + super.getDuenho().getAvatar().getId() +
                                        " ha construído un hotel en la casilla " + super.getNombreSinEspacio() +
                                        " por un valor de: " + this.valorHotel +
                                        "\nLa fortuna actual del jugador es de: " + super.getDuenho().getFortuna());
                            } else if (super.getGrupo().getNumeroHoteles() >= super.getGrupo().getNumeroSolares()) {
                                throw new ExcepcionesEdificios("No puedes tener más de tres hoteles en el grupo!");
                            } else {
                                throw new ExcepcionesEdificios("Para construír un hotel necesitas tener 4 casas construídas.");
                            }

                            break;
                        case "piscina":
                            if (this.numeroCasas >= 2 && this.numeroHoteles >= 1 && super.getGrupo().getNumeroPiscinas() < super.getGrupo().getNumeroSolares()) {
                                new Piscina(this.valorPiscina, id, this);
                                consola.imprimir("El avatar " + super.getDuenho().getAvatar().getId() +
                                        " ha construído una piscina en la casilla " + super.getNombreSinEspacio() +
                                        " por un valor de: " + this.valorPiscina +
                                        "\nLa fortuna actual del jugador es de: " + super.getDuenho().getFortuna());
                            } else if (super.getGrupo().getNumeroPiscinas() >= super.getGrupo().getNumeroSolares()) {
                                throw new ExcepcionesEdificios("No puedes tener más de tres piscinas en el grupo!");
                            } else
                                throw new ExcepcionesEdificios("Para construír una piscina necesitas tener al menos 2 casas y 1 hotel construído.");
                            break;
                        case "pista":
                            if (this.numeroHoteles >= 2 && super.getGrupo().getNumeroPistas() < super.getGrupo().getNumeroSolares()) {
                                new Pista(this.valorPistaDeporte, id, this);
                                consola.imprimir("El avatar " + super.getDuenho().getAvatar().getId() +
                                        " ha construído una pista de deporte en la casilla " + super.getNombreSinEspacio() +
                                        " por un valor de: " + this.valorPistaDeporte +
                                        "\nLa fortuna actual del jugador es de: " + super.getDuenho().getFortuna());
                            } else if (super.getGrupo().getNumeroPistas() >= super.getGrupo().getNumeroSolares()) {
                                throw new ExcepcionesEdificios("No puedes tener más de tres pistas de deporte en el grupo!");
                            } else
                                throw new ExcepcionesEdificios("Para construír una pista de deporte necesitas tener al menos 2 hoteles construídos.");
                            break;
                        default:
                            throw new ExcepcionesEdificios("ERROR, opción no válida.");
                    }
                } else
                    throw new ExcepcionesEdificios("No tienes todas las casillas del grupo ni has caído dos veces en la casilla por lo que no puedes hacer esto!");
            } else
                throw new ExcepcionesDuenho("No eres el dueño de esta casilla, por lo que no puedes contruír en esta casilla!");
        } else
            throw new ExcepcionesDuenho("No eres el dueño de esta casilla, por lo que no puedes construír en esta casilla!");
    }

    public void venderEdificio(String tipo, Jugador jugador, Taboleiro taboleiro, int numero) throws ExcepcionesEdificios, ExcepcionesNull, ExcepcionesDuenho {
        if ((tipo != null) && (jugador != null) && (taboleiro != null) && (numero > 0)) {
            switch (tipo.toLowerCase()) {
                case "casa":
                case "casas":
                    venderCasa(jugador, taboleiro, numero);
                    break;
                case "hotel":
                case "hoteles":
                    venderHotel(jugador, taboleiro, numero);
                    break;
                case "piscina":
                case "piscinas":
                    venderPiscina(jugador, taboleiro, numero);
                    break;
                case "pista":
                case "pistas":
                    venderPista(jugador, taboleiro, numero);
                    break;
                default:
                    throw new ExcepcionesEdificios("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Interfaces.Comandos");
            }
        } else throw new ExcepcionesNull("Se ha producido un error.");
    }

    public void venderCasa(Jugador jugador, Taboleiro taboleiro, int numero) throws ExcepcionesEdificios, ExcepcionesDuenho {
        if (super.getDuenho() != null) {
            if (jugador.equals(super.getDuenho())) {
                if (this.numeroCasas >= numero) {
                    this.numeroCasas = this.numeroCasas - numero;
                    ArrayList<Casa> cas = new ArrayList<>();
                    for (int i = 0; i < numero; i++) {
                        cas.add(this.casas.get(i));
                    }
                    for (int i = 0; i < numero; i++) {
                        this.casas.remove(cas.get(i));
                        jugador.eliminarEdificacion(cas.get(i));
                        taboleiro.eliminarCasa(cas.get(i).getId());
                    }
                    super.getDuenho().sumarFortuna((float) (numero * this.valorCasa / 2));
                    consola.imprimir(super.getDuenho().getNombre() + " ha vendido " + numero + " casa(s) en " + this.getNombreSinEspacio() + ", recibiendo "
                            + (numero * this.valorCasa / 2) + "€. En la propiedad queda(n) " + this.numeroCasas + " casa(s).");
                } else {
                    if (this.numeroCasas > 0) {
                        throw new ExcepcionesEdificios("Solamente se puede(n) vender " + this.numeroCasas + " casa(s) y se recibirían " + this.numeroCasas * this.valorCasa / 2 + "€.");
                    } else
                        throw new ExcepcionesEdificios("En esta casilla no tienes casas construidas, por lo que no puedes venderlas.");
                }
            } else
                throw new ExcepcionesDuenho("No eres el dueño de esta casilla, por lo que no puedes vender casas en esta casilla!");
        } else
            throw new ExcepcionesEdificios("No eres el dueño de esta casilla, por lo que no puedes vender casas en esta casilla!");
    }

    public void venderHotel(Jugador jugador, Taboleiro taboleiro, int numero) throws ExcepcionesDuenho, ExcepcionesEdificios {
        if (super.getDuenho() != null) {
            if (jugador.equals(super.getDuenho())) {
                if (this.numeroHoteles >= numero) {
                    this.numeroHoteles = this.numeroHoteles - numero;
                    ArrayList<Hotel> hot = new ArrayList<>();
                    for (int i = 0; i < numero; i++) {
                        hot.add(this.hoteles.get(i));
                    }
                    for (int i = 0; i < numero; i++) {
                        this.hoteles.remove(hot.get(i));
                        jugador.eliminarEdificacion(hot.get(i));
                        taboleiro.eliminarHotel(hot.get(i).getId());
                    }
                    super.getDuenho().sumarFortuna((float) (numero * this.valorHotel / 2));
                    consola.imprimir(super.getDuenho().getNombre() + " ha vendido " + numero + " hotel(es) en " +
                            this.getNombreSinEspacio() + ", recibiendo " + (numero * this.valorHotel / 2) + "€. En la propiedad queda(n) " +
                            this.numeroHoteles + " hotel(es).");
                } else {
                    if (this.numeroHoteles > 0) {
                        throw new ExcepcionesEdificios("Solamente se puede(n) vender " + this.numeroHoteles + " hotel(es) y se recibirían " + this.numeroHoteles * this.valorHotel / 2 + "€.");
                    } else
                        throw new ExcepcionesEdificios("En esta casilla no tienes hoteles construidos, por lo que no puedes venderlos.");
                }
            } else
                throw new ExcepcionesDuenho("No eres el dueño de esta casilla, por lo que no puedes vender hoteles en esta casilla!");
        } else
            throw new ExcepcionesDuenho("No eres el dueño de esta casilla, por lo que no puedes vender hoteles en esta casilla!");
    }

    public void venderPiscina(Jugador jugador, Taboleiro taboleiro, int numero) throws ExcepcionesEdificios, ExcepcionesDuenho {
        if (super.getDuenho() != null) {
            if (jugador.equals(super.getDuenho())) {
                if (this.numeroPiscinas >= numero) {
                    this.numeroPiscinas = this.numeroPiscinas - numero;
                    ArrayList<Piscina> pis = new ArrayList<>();
                    for (int i = 0; i < numero; i++) {
                        pis.add(this.piscinas.get(i));
                    }
                    for (int i = 0; i < numero; i++) {
                        this.piscinas.remove(pis.get(i));
                        jugador.eliminarEdificacion(pis.get(i));
                        taboleiro.eliminarPiscina(pis.get(i).getId());
                    }
                    super.getDuenho().sumarFortuna((float) (numero * this.valorPiscina / 2));
                    consola.imprimir(super.getDuenho().getNombre() + " ha vendido " + numero + " piscina(s) en " +
                            this.getNombreSinEspacio() + ", recibiendo " + (numero * this.valorPiscina / 2) + "€. En la propiedad queda(n) " +
                            this.numeroPiscinas + " piscina(s).");
                } else {
                    if (this.numeroPiscinas > 0) {
                        throw new ExcepcionesEdificios("Solamente se puede(n) vender " + this.numeroPiscinas + " piscina(s) y se recibirían " + this.numeroPiscinas * this.valorPiscina / 2 + "€.");
                    } else
                        throw new ExcepcionesEdificios("En esta casilla no tienes piscinas construidas, por lo que no puedes venderlas.");
                }
            } else
                throw new ExcepcionesDuenho("No eres el dueño de esta casilla, por lo que no puedes vender piscinas en esta casilla!");
        } else
            throw new ExcepcionesDuenho("No eres el dueño de esta casilla, por lo que no puedes vender piscinas en esta casilla!");
    }

    public void venderPista(Jugador jugador, Taboleiro taboleiro, int numero) throws ExcepcionesEdificios, ExcepcionesDuenho {
        if (super.getDuenho() != null) {
            if (jugador.equals(super.getDuenho())) {
                if (this.numeroPistas >= numero) {
                    this.numeroPistas = this.numeroPistas - numero;
                    ArrayList<Pista> pist = new ArrayList<>();
                    for (int i = 0; i < numero; i++) {
                        pist.add(this.pistas.get(i));
                    }
                    for (int i = 0; i < numero; i++) {
                        this.pistas.remove(pist.get(i));
                        jugador.eliminarEdificacion(pist.get(i));
                        taboleiro.eliminarPista(pist.get(i).getId());
                    }
                    super.getDuenho().sumarFortuna((float) (numero * this.valorPistaDeporte / 2));
                    consola.imprimir(super.getDuenho().getNombre() + " ha vendido " + numero + " pista(s) en " +
                            this.getNombreSinEspacio() + ", recibiendo " + (numero * this.valorPistaDeporte / 2) +
                            "€. En la propiedad queda(n) " + this.numeroPistas + " pista(s).");
                } else {
                    if (this.numeroPistas > 0) {
                        throw new ExcepcionesEdificios("Solamente se puede(n) vender " + this.numeroPistas + " pista(s) y se recibirían " + this.numeroPistas * this.valorPistaDeporte / 2 + "€.");
                    } else
                        throw new ExcepcionesEdificios("En esta casilla no tienes pistas construidas, por lo que no puedes venderlas.");
                }
            } else
                throw new ExcepcionesDuenho("No eres el dueño de esta casilla, por lo que no puedes vender pistas en esta casilla!");
        } else
            throw new ExcepcionesDuenho("No eres el dueño de esta casilla, por lo que no puedes vender pistas en esta casilla!");
    }


    public double getValorAlquiler() {
        double valor = 0;
        int aux = 0;
        aux = casas.size();
        switch (aux) {
            case 1:
                valor = 5 * super.getAlquiler();
                break;
            case 2:
                valor = 15 * super.getAlquiler();
                break;
            case 3:
                valor = 35 * super.getAlquiler();
                break;
            case 4:
                valor = 50 * super.getAlquiler();
                break;
            default:
                valor = super.getAlquiler();
                break;
        }
        aux = hoteles.size();
        switch (aux) {
            case 1:
                valor += 70 * super.getAlquiler();
                break;
            case 2:
                valor += 140 * super.getAlquiler();
                break;
            case 3:
                valor += 210 * super.getAlquiler();
                break;
            default:
                break;
        }
        valor = getValor(valor, piscinas.size());
        valor = getValor(valor, pistas.size());

        return valor;
    }

    private double getValor(double valor, int size) {
        int aux;
        aux = size;
        switch (aux) {
            case 1:
                valor += 25 * super.getAlquiler();
                break;
            case 2:
                valor += 50 * super.getAlquiler();
                break;
            case 3:
                valor += 75 * super.getAlquiler();
                break;
            default:
                break;
        }
        return valor;
    }

    @Override
    public String toString() {
        String banca, texto, edificaciones = "[ ]";
        if (super.getDuenho() == null) {
            banca = "banca";
        } else {
            if (super.getDuenho().getEdificaciones() != null) {
                edificaciones = super.getDuenho().getEdificaciones().toString();
            }
            banca = super.getDuenho().getNombre();
        }

        texto = "{\n\ttipo: solar,\n\tgrupo: " + this.getGrupo().getNumeroGrupo() + ",\n\tpropietario: " + banca +
                ",\n\tvalor: " + super.getValor() + ",\n\talquiler: " + this.getValorAlquiler() + ",\n\tvalor hotel: " +
                this.valorHotel + ",\n\tvalor casa: " + this.valorCasa + ",\n\tvalor piscina: " + this.valorPistaDeporte +
                ",\n\tvalor pista de deporte: " + this.valorPistaDeporte + ",\n\talquiler una casa: " + 5 * super.getAlquiler() +
                ",\n\talquiler dos casas: " + 15 * super.getAlquiler() + ",\n\talquiler tres casas: " + 35 * super.getAlquiler() +
                ",\n\talquiler cuatro casas: " + 50 * super.getValor() + ",\n\talquiler hotel: " + 70 * super.getAlquiler() +
                ",\n\talquiler piscina: " + 25 * super.getValor() + ",\n\talquiler pista de deporte: " + 25 * super.getAlquiler()
                + ",\n\tedificaciones: " + edificaciones + ",\n}";

        return texto;
    }
}
