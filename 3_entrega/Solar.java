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

    public void edificar(String tipo, Jugador jugador, Taboleiro taboleiro) {
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
                System.out.println("ERROR, tipo de edificio erróneo.");
                break;
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

    private void construirEdificio(String edificio, Jugador jugador, Taboleiro taboleiro, String id) {
        if (super.getDuenho() != null) {
            if (super.getDuenho().equals(jugador)) {
                int aux;
                if (super.getVecesCasilla().containsKey(jugador.getAvatar().getId())) {
                    aux = Integer.parseInt(super.getVecesCasilla().get(jugador.getAvatar().getId())[1]);
                } else {
                    aux = 0;
                }

                if (super.tenerTodasCasillas() || aux >= 2) {
                    switch (edificio) {
                        case "casa":
                            if (jugador.getFortuna() >= this.valorCasa) {
                                if (this.numeroCasas < 4) {
                                    new Casa(this.valorCasa, id, this);
                                    System.out.println("El avatar " + super.getDuenho().getAvatar().getId() +
                                            " ha construído una casa en la casilla " + super.getNombreSinEspacio() +
                                            " por un valor de: " + this.valorCasa +
                                            "\nLa fortuna actual del jugador es de: " + super.getDuenho().getFortuna());
                                } else {
                                    System.out.print("Ya hay 4 casas construídas! Quiere construír un hotel? [si/no] ");

                                    /* Intentar hacer esto con la interface de comando!!!!!!!!!!!!!!*/

                                    String comando;
                                    Scanner leer = new Scanner(System.in);
                                    comando = leer.nextLine();
                                    if (comando.toLowerCase().equals("si")) {
                                        construirEdificio("hotel", jugador, taboleiro, id);
                                    } else {
                                        System.out.println("De acuerdo. No se hará ninguna acción.");
                                    }
                                }
                            } else {
                                System.out.println("No tienes suficiente dinero para construir una casa.");
                            }
                            break;

                        case "hotel":
                            if (jugador.getFortuna() >= this.valorHotel) {
                                if (this.numeroCasas == 4 && (super.getGrupo().getNumeroHoteles() < super.getGrupo().getNumeroSolares())) {
                                    new Hotel(this.valorHotel, id, this);
                                    this.eliminarCasas(jugador, taboleiro);
                                    System.out.println("El avatar " + super.getDuenho().getAvatar().getId() +
                                            " ha construído un hotel en la casilla " + super.getNombreSinEspacio() +
                                            " por un valor de: " + this.valorHotel +
                                            "\nLa fortuna actual del jugador es de: " + super.getDuenho().getFortuna());
                                } else if (super.getGrupo().getNumeroHoteles() >= super.getGrupo().getNumeroSolares()) {
                                    System.out.println("No puedes tener más de tres hoteles en el grupo!");
                                } else {
                                    System.out.println("Para construír un hotel necesitas tener 4 casas construídas.");
                                }
                            } else {
                                System.out.println("No tienes suficiente dinero para construir un hotel.");
                            }
                            break;
                        case "piscina":
                            if (jugador.getFortuna() >= this.valorPiscina) {
                                if (this.numeroCasas >= 2 && this.numeroHoteles >= 1 && super.getGrupo().getNumeroPiscinas() < super.getGrupo().getNumeroSolares()) {
                                    new Piscina(this.valorPiscina, id, this);
                                    System.out.println("El avatar " + super.getDuenho().getAvatar().getId() +
                                            " ha construído una piscina en la casilla " + super.getNombreSinEspacio() +
                                            " por un valor de: " + this.valorPiscina +
                                            "\nLa fortuna actual del jugador es de: " + super.getDuenho().getFortuna());
                                } else if (super.getGrupo().getNumeroPiscinas() >= super.getGrupo().getNumeroSolares()) {
                                    System.out.println("No puedes tener más de tres piscinas en el grupo!");
                                } else {
                                    System.out.println("Para construír una piscina necesitas tener al menos 2 casas y 1 hotel construído.");
                                }
                            } else {
                                System.out.println("No tienes suficiente dinero para construir una piscina.");
                            }
                            break;
                        case "pista":
                            if (jugador.getFortuna() >= this.valorPistaDeporte) {
                                if (this.numeroHoteles >= 2 && super.getGrupo().getNumeroPistas() < super.getGrupo().getNumeroSolares()) {
                                    new Pista(this.valorPistaDeporte, id, this);
                                    System.out.println("El avatar " + super.getDuenho().getAvatar().getId() +
                                            " ha construído una pista de deporte en la casilla " + super.getNombreSinEspacio() +
                                            " por un valor de: " + this.valorPistaDeporte +
                                            "\nLa fortuna actual del jugador es de: " + super.getDuenho().getFortuna());
                                } else if (super.getGrupo().getNumeroPistas() >= super.getGrupo().getNumeroSolares()) {
                                    System.out.println("No puedes tener más de tres pistas de deporte en el grupo!");
                                } else {
                                    System.out.println("Para construír una pista de deporte necesitas tener al menos 2 hoteles construídos.");
                                }
                            } else {
                                System.out.println("No tienes suficiente dinero para construir una pista de deporte.");
                            }
                            break;
                        default:
                            System.out.println("ERROR, opción no válida.");
                            break;
                    }
                } else {
                    System.out.println("No tienes todas las casillas del grupo ni has caído dos veces en la casilla por lo que no puedes hacer esto!");
                }
            } else {
                System.out.println("No eres el dueño de esta casilla, por lo que no puedes contruír en esta casilla!");
            }
        } else {
            System.out.println("No eres el dueño de esta casilla, por lo que no puedes construír en esta casilla!");
        }
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
        aux = piscinas.size();
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
        aux = pistas.size();
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
