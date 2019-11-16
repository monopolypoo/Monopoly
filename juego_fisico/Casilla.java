package juego_fisico;

import monopoly.*;
import partida_virtual.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Casilla {
    private String nombre;
    private String tipo;
    private double valor;
    private int posicion;
    private Jugador duenho;
    private Jugador duenhoAnterior;
    private String colorGrupo;
    private double valorCasa;
    private double valorPiscina;
    private double valorPistaDeporte;
    private double valorHotel;
    private double valorAlquiler;
    private HashMap<String, String[]> vecesCasilla;
    private HashMap<String, Avatar> avatares;
    private ArrayList<String> idCasas;
    private ArrayList<String> idHoteles;
    private ArrayList<String> idPiscinas;
    private ArrayList<String> idPistas;
    private Grupo grupo;
    private int numeroCasas;
    private int numeroHoteles;
    private int numeroPiscinas;
    private int numeroPistas;
    private boolean esHiportecado;

    public Casilla() {
    }

    public Casilla(String nombre, String tipo, int posicion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = 0;
        this.colorGrupo = null;
        this.vecesCasilla = new HashMap<>();
        this.avatares = new HashMap<>();
        this.duenho = null;
        this.duenhoAnterior = null;
        this.grupo = null;
        this.numeroCasas = 0;
        this.numeroHoteles = 0;
        this.numeroPiscinas = 0;
        this.numeroPistas = 0;
        this.idCasas = new ArrayList<>();
        this.idHoteles = new ArrayList<>();
        this.idPiscinas = new ArrayList<>();
        this.idPistas = new ArrayList<>();
        this.esHiportecado = false;
    }


    public Casilla(String nombre, String tipo, int posicion, double valor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = valor;
        this.colorGrupo = null;
        this.duenho = null;
        this.duenhoAnterior = null;
        this.vecesCasilla = new HashMap<>();
        this.avatares = new HashMap<>();
        this.grupo = null;
        this.numeroCasas = 0;
        this.numeroHoteles = 0;
        this.numeroPiscinas = 0;
        this.numeroPistas = 0;
        this.idCasas = new ArrayList<>();
        this.idHoteles = new ArrayList<>();
        this.idPiscinas = new ArrayList<>();
        this.idPistas = new ArrayList<>();
        this.esHiportecado = false;
    }

    public Casilla(String nombre, String tipo, int posicion, double valor, String colorGrupo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = valor;
        this.colorGrupo = colorGrupo;
        this.vecesCasilla = new HashMap<>();
        this.avatares = new HashMap<>();
        this.duenho = null;
        this.duenhoAnterior = null;
        this.grupo = null;
        this.numeroCasas = 0;
        this.numeroHoteles = 0;
        this.numeroPiscinas = 0;
        this.numeroPistas = 0;
        this.idCasas = new ArrayList<>();
        this.idHoteles = new ArrayList<>();
        this.idPiscinas = new ArrayList<>();
        this.idPistas = new ArrayList<>();
        this.esHiportecado = false;
    }

    public double getValorCasa() {
        return valorCasa;
    }

    public double getValorHotel() {
        return valorHotel;
    }

    public double getValorPiscina() {
        return valorPiscina;
    }

    public double getValorPistaDeporte() {
        return valorPistaDeporte;
    }

    public boolean EsHiportecado() {
        return esHiportecado;
    }

    public void setEsHiportecado(boolean esHiportecado) {
        this.esHiportecado = esHiportecado;
    }

    public Jugador getDuenhoAnterior() {
        return this.duenhoAnterior;
    }

    public void setDuenhoAnterior(Jugador duenhoAnterior) {
        this.duenhoAnterior = duenhoAnterior;
    }

    // Los setters de: Nombre, Avatares, VecesCasilla(HashMap), Posicion, Tipo y los valores de las diferentes
    // estructuras que se pueden construir.
    // no los añadimos ya que estos campos se meten en el contructor de la casilla y no se permiten modificar durante
    // el transcurso de la partida

    public Jugador getDuenho() {
        return duenho;
    }

    public void setDuenho(Jugador jugador) {
        this.duenho = jugador;
    }

    public void setVecesCasilla(Jugador jugador) {
        String[] texto = new String[2];
        int aux;

        if (this.vecesCasilla.containsKey(jugador.getAvatar().getId())) {
            texto = this.vecesCasilla.get(jugador.getAvatar().getId());
            aux = Integer.parseInt(texto[1]);
            aux++;
            texto[1] = "" + aux;
            this.vecesCasilla.replace(jugador.getAvatar().getId(), texto);
        } else {
            texto[0] = jugador.getAvatar().getId();
            texto[1] = "1";
            this.vecesCasilla.put(jugador.getAvatar().getId(), texto);
        }
    }

    public boolean isSubirPrecio() {
        for (String[] cadena : this.vecesCasilla.values()) {
            if (Integer.parseInt(cadena[1]) < 4)
                return false;
        }
        return true;
    }

    public boolean haiAvatar() {
        return this.avatares.size() != 0;
    }

    public void setAvatar(Avatar avatar) {
        this.avatares.put(avatar.getId(), avatar);
    }

    public Avatar getAvatarCasilla(String id) {
        return avatares.get(id);
    }

    public void eliminarAvatar(String id) {
        this.avatares.remove(id);
    }

    public Grupo getGrupo() {
        return this.grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public HashMap<String, Avatar> getAvatares() {
        return avatares;
    }

    public String getIds() {
        String texto = " &";
        int tam, i = 1;
        tam = this.avatares.size();

        for (Avatar avatar : this.avatares.values()) {
            texto += avatar.getId();
            if (tam != i) {
                texto += ",";
            }
            i++;
        }
        for (int j = 0; j < 18 - (tam * 2 + 1); j++) {
            texto += " ";
        }

        return texto;
    }

    public double getValorAlquiler() {
        double valor = 0;
        int aux = 0;
        aux = idCasas.size();
        switch (aux) {
            case 1:
                valor = 5 * valorAlquiler;
                break;
            case 2:
                valor = 15 * valorAlquiler;
                break;
            case 3:
                valor = 35 * valorAlquiler;
                break;
            case 4:
                valor = 50 * valorAlquiler;
                break;
            default:
                valor = valorAlquiler;
                break;
        }
        aux = idHoteles.size();
        switch (aux) {
            case 1:
                valor += 70 * valorAlquiler;
                break;
            case 2:
                valor += 140 * valorAlquiler;
                break;
            case 3:
                valor += 210 * valorAlquiler;
                break;
            default:
                break;
        }
        aux = idPiscinas.size();
        switch (aux) {
            case 1:
                valor += 25 * valorAlquiler;
                break;
            case 2:
                valor += 50 * valorAlquiler;
                break;
            case 3:
                valor += 75 * valorAlquiler;
                break;
            default:
                break;
        }
        aux = idPistas.size();
        switch (aux) {
            case 1:
                valor += 25 * valorAlquiler;
                break;
            case 2:
                valor += 50 * valorAlquiler;
                break;
            case 3:
                valor += 75 * valorAlquiler;
                break;
            default:
                break;
        }

        return valor;
    }

    public HashMap<String, String[]> getVecesCasilla() {
        return this.vecesCasilla;
    }

    public String getNombre() {
        return nombre;
    }

    public double getValor() {
        return valor;
    }

    public String getNombreSinEspacio() {
        String[] nom;
        nom = nombre.split(" ");
        if (nom.length == 2) {
            return nom[0] + " " + nom[1];
        } else if (nom.length == 3) {
            return nom[0] + " " + nom[1] + " " + nom[2];
        } else if (nom.length == 4) {
            return nom[0] + " " + nom[1] + " " + nom[2] + " " + nom[3];
        } else if (nom.length == 5) {
            return nom[0] + " " + nom[1] + " " + nom[2] + " " + nom[3] + " " + nom[4];
        } else {
            return nom[0];
        }
    }

    public String getNombreSinEspacios() {
        String[] nom;
        nom = nombre.split(" ");
        if (nom.length == 2) {
            return nom[0] + nom[1];
        } else if (nom.length == 3) {
            return nom[0] + nom[1] + nom[2];
        } else if (nom.length == 4) {
            return nom[0] + nom[1] + nom[2] + nom[3];
        } else if (nom.length == 5) {
            return nom[0] + nom[1] + nom[2] + nom[3] + nom[4];
        } else {
            return nom[0];
        }
    }

    public String getTipo() {
        return tipo;
    }

    public String getColorGrupo() {
        return colorGrupo;
    }

    public void setColorGrupo(String colorGrupo) {
        this.colorGrupo = colorGrupo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void SetValor(double valor) {
        this.valor = valor;
        this.valorCasa = 0.6 * this.valor;
        this.valorPiscina = 0.4 * this.valor;
        this.valorPistaDeporte = 1.25 * this.valor;
        this.valorHotel = this.valorCasa;
        this.valorAlquiler = 0.1 * this.valor;
    }

    public boolean pertence(Jugador jugador) {
        return this.duenho.getAvatar().getId().equals(jugador.getAvatar().getId());
    }

    public int getPosicion() {
        return posicion;
    }

    public void sumarValor(float valor) {
        this.valor += valor;
    }


    public ArrayList<String> getIdCasas() {
        return idCasas;
    }

    public ArrayList<String> getIdHoteles() {
        return idHoteles;
    }

    public ArrayList<String> getIdPiscinas() {
        return idPiscinas;
    }

    public ArrayList<String> getIdPistas() {
        return idPistas;
    }

    public void setIdCasas(ArrayList<String> idCasas) {
        this.idCasas = idCasas;
    }

    public void setIdHoteles(ArrayList<String> idHoteles) {
        this.idHoteles = idHoteles;
    }

    public void setIdPiscinas(ArrayList<String> idPiscinas) {
        this.idPiscinas = idPiscinas;
    }

    public void setIdPistas(ArrayList<String> idPistas) {
        this.idPistas = idPistas;
    }

    public void añadirIdCasas(String id) {
        this.idCasas.add(id);
    }

    public void añadirIdHoteles(String id) {
        this.idHoteles.add(id);
    }

    public void añadirIdPiscinas(String id) {
        this.idPiscinas.add(id);
    }

    public void añadirIdPistas(String id) {
        this.idPistas.add(id);
    }


    public void eliminarCasas(Jugador jugador, Taboleiro taboleiro) {
        if (this.idCasas.size() >= 4) {
            for (String id : this.idCasas) {
                taboleiro.eliminarCasa(id);
                this.idCasas.remove(id);
                jugador.eliminarEdificacion(id);
            }
        }
    }

    public void construirCasa(Jugador jugador, Taboleiro taboleiro) {
        if (this.duenho != null) {
            if (jugador.getAvatar().getId().equals(this.duenho.getAvatar().getId())) {
                int aux;
                if (this.getVecesCasilla().containsKey(jugador.getAvatar().getId())) {
                    aux = Integer.parseInt(this.getVecesCasilla().get(jugador.getAvatar().getId())[1]);
                } else {
                    aux = 0;
                }

                if (this.grupo.tenerTodasCasillas() || aux >= 2) {
                    if (jugador.getFortuna() >= this.valorCasa) {
                        if (this.numeroCasas < 4) {
                            String id;
                            this.numeroCasas++;
                            this.duenho.restarFortuna((float) this.valorCasa);
                            id = taboleiro.idCasa(this);
                            jugador.añadirEdificacion(id);
                            this.idCasas.add(id);
                            jugador.sumarDineroInvertido((float) this.getValorCasa());
                            jugador.sumarPremiosInversionesBote((float) this.getValorCasa());
                            System.out.println("El avatar " + this.duenho.getAvatar().getId() + " ha construído una casa en la casilla " + this.getNombreSinEspacio() + " por un valor de: " + this.valorCasa +
                                    "\nLa fortuna actual del jugador es de: " + this.duenho.getFortuna());
                        } else {
                            System.out.print("Ya hay 4 casas construídas! Quiere construír un hotel? [si/no] ");
                            String comando;
                            Scanner leer = new Scanner(System.in);
                            comando = leer.nextLine();
                            if (comando.toLowerCase().equals("si")) {
                                construirHotel(jugador, taboleiro);
                            } else {
                                System.out.println("De acuerdo. No se hará ninguna acción.");
                            }
                        }
                    } else {
                        System.out.println("No tienes suficiente dinero para construir una casa.");
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

    public void construirHotel(Jugador jugador, Taboleiro taboleiro) {
        if (this.duenho != null) {
            if (jugador.getAvatar().getId().equals(this.duenho.getAvatar().getId())) {
                int aux;
                if (this.getVecesCasilla().containsKey(jugador.getAvatar().getId())) {
                    aux = Integer.parseInt(this.getVecesCasilla().get(jugador.getAvatar().getId())[1]);
                } else {
                    aux = 0;
                }

                if (this.grupo.tenerTodasCasillas() || aux >= 2) {
                    if (jugador.getFortuna() >= this.valorHotel) {
                        if (this.numeroCasas == 4 && this.grupo.getNumeroHoteles() < this.grupo.getNumeroSolares()) {
                            this.numeroCasas = 0;
                            this.numeroHoteles++;
                            this.duenho.restarFortuna((float) this.valorHotel);
                            this.grupo.setNumeroHoteles(this.grupo.getNumeroHoteles() + 1);
                            String id = taboleiro.idHotel(this);
                            jugador.añadirEdificacion(id);
                            this.idHoteles.add(id);
                            jugador.sumarPremiosInversionesBote((float) this.getValorHotel());
                            jugador.sumarDineroInvertido((float) this.getValorHotel());
                            eliminarCasas(jugador, taboleiro);
                            System.out.println("El avatar " + this.duenho.getAvatar().getId() + " ha construído un hotel en la casilla " + this.getNombreSinEspacio() + " por un valor de: " + this.valorHotel +
                                    "\nLa fortuna actual del jugador es de: " + this.duenho.getFortuna());
                        } else if (this.grupo.getNumeroHoteles() >= this.grupo.getNumeroSolares()) {
                            System.out.println("No puedes tener más de tres hoteles en el grupo!");
                        } else {
                            System.out.println("Para construír un hotel necesitas tener 4 casas construídas.");
                        }
                    } else {
                        System.out.println("No tienes suficiente dinero para construir un hotel.");
                    }
                } else {
                    System.out.println("No tienes todas las casillas del grupo por lo que no puedes hacer esto!");
                }
            } else {
                System.out.println("No eres el dueño de esta casilla, por lo que no puedes contruír en esta casilla!");
            }
        } else {
            System.out.println("No eres el dueño de esta casilla, por lo que no puedes construír en esta casilla!");
        }
    }

    public void construirPiscina(Jugador jugador, Taboleiro taboleiro) {
        if (this.duenho != null) {
            if (jugador.getAvatar().getId().equals(this.duenho.getAvatar().getId())) {
                int aux;
                if (this.getVecesCasilla().containsKey(jugador.getAvatar().getId())) {
                    aux = Integer.parseInt(this.getVecesCasilla().get(jugador.getAvatar().getId())[1]);
                } else {
                    aux = 0;
                }

                if (this.grupo.tenerTodasCasillas() || aux >= 2) {
                    if (jugador.getFortuna() >= this.valorPiscina) {
                        if (this.numeroCasas >= 2 && this.numeroHoteles >= 1 && this.grupo.getNumeroPiscinas() < this.grupo.getNumeroSolares()) {
                            this.numeroPiscinas++;
                            this.duenho.restarFortuna((float) this.valorPiscina);
                            this.grupo.setNumeroPiscinas(this.grupo.getNumeroPiscinas() + 1);
                            String id = taboleiro.idPiscina(this);
                            jugador.añadirEdificacion(id);
                            jugador.sumarDineroInvertido((float) this.getValorPiscina());
                            jugador.sumarPremiosInversionesBote((float) this.getValorPiscina());
                            this.idPiscinas.add(id);
                            System.out.println("El avatar " + this.duenho.getAvatar().getId() + " ha construído una piscina en la casilla " + this.getNombreSinEspacio() + " por un valor de: " + this.valorPiscina +
                                    "\nLa fortuna actual del jugador es de: " + this.duenho.getFortuna());
                        } else if (this.grupo.getNumeroPiscinas() >= this.grupo.getNumeroSolares()) {
                            System.out.println("No puedes tener más de tres piscinas en el grupo!");
                        } else {
                            System.out.println("Para construír una piscina necesitas tener al menos 2 casas y 1 hotel construído.");
                        }
                    } else {
                        System.out.println("No tienes suficiente dinero para construir una piscina.");
                    }
                } else {
                    System.out.println("No tienes todas las casillas del grupo por lo que no puedes hacer esto!");
                }
            } else {
                System.out.println("No eres el dueño de esta casilla, por lo que no puedes contruír en esta casilla!");
            }
        } else {
            System.out.println("No eres el dueño de esta casilla, por lo que no puedes construír en esta casilla!");
        }
    }

    public void construirPista(Jugador jugador, Taboleiro taboleiro) {
        if (this.duenho != null) {
            if (jugador.getAvatar().getId().equals(this.duenho.getAvatar().getId())) {
                int aux;
                if (this.getVecesCasilla().containsKey(jugador.getAvatar().getId())) {
                    aux = Integer.parseInt(this.getVecesCasilla().get(jugador.getAvatar().getId())[1]);
                } else {
                    aux = 0;
                }

                if (this.grupo.tenerTodasCasillas() || aux >= 2) {
                    if (jugador.getFortuna() >= this.valorPistaDeporte) {
                        if (this.numeroHoteles >= 2 && this.grupo.getNumeroPistas() < this.grupo.getNumeroSolares()) {
                            this.numeroPistas++;
                            this.duenho.restarFortuna((float) this.valorPistaDeporte);
                            this.grupo.setNumeroPistas(this.grupo.getNumeroPistas() + 1);
                            String id = taboleiro.idPista(this);
                            jugador.añadirEdificacion(id);
                            jugador.sumarPremiosInversionesBote((float) this.getValorPistaDeporte());
                            jugador.sumarDineroInvertido((float) this.getValorPistaDeporte());
                            this.idPistas.add(id);
                            System.out.println("El avatar " + this.duenho.getAvatar().getId() + " ha construído una pista de deporte en la casilla " + this.getNombreSinEspacio() + " por un valor de: " + this.valorPistaDeporte +
                                    "\nLa fortuna actual del jugador es de: " + this.duenho.getFortuna());
                        } else if (this.grupo.getNumeroPistas() >= this.grupo.getNumeroSolares()) {
                            System.out.println("No puedes tener más de tres pistas de deporte en el grupo!");
                        } else {
                            System.out.println("Para construír una pista de deporte necesitas tener al menos 2 hoteles construídos.");
                        }
                    } else {
                        System.out.println("No tienes suficiente dinero para construir una pista de deporte.");
                    }
                } else {
                    System.out.println("No tienes todas las casillas del grupo por lo que no puedes hacer esto!");
                }
            } else {
                System.out.println("No eres el dueño de esta casilla, por lo que no puedes contruír en esta casilla!");
            }
        } else {
            System.out.println("No eres el dueño de esta casilla, por lo que no puedes construír en esta casilla!");
        }
    }

    public void venderCasa(Jugador jugador, Taboleiro taboleiro, int numero) {
        if (this.duenho != null) {
            if (jugador.getAvatar().getId().equals(this.duenho.getAvatar().getId())) {
                if (this.numeroCasas >= numero) {
                    this.numeroCasas = this.numeroCasas - numero;
                    String id[] = new String[numero];
                    for (int i = 0; i < numero; i++) {
                        id[i] = this.idCasas.get(i);
                    }
                    for (int i = 0; i < numero; i++) {
                        this.idCasas.remove(id[i]);
                        jugador.eliminarEdificacion(id[i]);
                    }
                    this.duenho.sumarFortuna((float) (numero * this.valorCasa / 2));
                    System.out.println(this.duenho.getNombre() + " ha vendido " + numero + " casa(s) en " + this.getNombreSinEspacio() + ", recibiendo " + (numero * this.valorCasa / 2) + "€. En la propiedad queda(n) " + this.numeroCasas + " casa(s).");
                } else {
                    if (this.numeroCasas > 0) {
                        System.out.println("Solamente se puede(n) vender " + this.numeroCasas + " casa(s) y se recibirían " + this.numeroCasas * this.valorCasa / 2 + "€.");
                    } else {
                        System.out.println("En esta casilla no tienes casas construidas, por lo que no puedes venderlas.");
                    }
                }
            } else {
                System.out.println("No eres el dueño de esta casilla, por lo que no puedes vender casas en esta casilla!");
            }
        } else {
            System.out.println("No eres el dueño de esta casilla, por lo que no puedes vender casas en esta casilla!");
        }
    }

    public void venderHotel(Jugador jugador, Taboleiro taboleiro, int numero) {
        if (this.duenho != null) {
            if (jugador.getAvatar().getId().equals(this.duenho.getAvatar().getId())) {
                if (this.numeroHoteles >= numero) {
                    this.numeroHoteles = this.numeroHoteles - numero;
                    String id[] = new String[numero];
                    for (int i = 0; i < numero; i++) {
                        id[i] = this.idHoteles.get(i);
                    }
                    for (int i = 0; i < numero; i++) {
                        this.idHoteles.remove(id[i]);
                        jugador.eliminarEdificacion(id[i]);
                    }
                    this.duenho.sumarFortuna((float) (numero * this.valorHotel / 2));
                    System.out.println(this.duenho.getNombre() + " ha vendido " + numero + " hotel(es) en " + this.getNombreSinEspacio() + ", recibiendo " + (numero * this.valorHotel / 2) + "€. En la propiedad queda(n) " + this.numeroHoteles + " hotel(es).");
                } else {
                    if (this.numeroHoteles > 0) {
                        System.out.println("Solamente se puede(n) vender " + this.numeroHoteles + " hotel(es) y se recibirían " + this.numeroHoteles * this.valorHotel / 2 + "€.");
                    } else {
                        System.out.println("En esta casilla no tienes hoteles construidos, por lo que no puedes venderlos.");
                    }
                }
            } else {
                System.out.println("No eres el dueño de esta casilla, por lo que no puedes vender hoteles en esta casilla!");
            }
        } else {
            System.out.println("No eres el dueño de esta casilla, por lo que no puedes vender hoteles en esta casilla!");
        }
    }

    public void venderPiscina(Jugador jugador, Taboleiro taboleiro, int numero) {
        if (this.duenho != null) {
            if (jugador.getAvatar().getId().equals(this.duenho.getAvatar().getId())) {
                if (this.numeroPiscinas >= numero) {
                    this.numeroPiscinas = this.numeroPiscinas - numero;
                    String id[] = new String[numero];
                    for (int i = 0; i < numero; i++) {
                        id[i] = this.idPiscinas.get(i);
                    }
                    for (int i = 0; i < numero; i++) {
                        this.idPiscinas.remove(id[i]);
                        jugador.eliminarEdificacion(id[i]);
                    }
                    this.duenho.sumarFortuna((float) (numero * this.valorPiscina / 2));
                    System.out.println(this.duenho.getNombre() + " ha vendido " + numero + " piscina(s) en " + this.getNombreSinEspacio() + ", recibiendo " + (numero * this.valorPiscina / 2) + "€. En la propiedad queda(n) " + this.numeroPiscinas + " piscina(s).");
                } else {
                    if (this.numeroPiscinas > 0) {
                        System.out.println("Solamente se puede(n) vender " + this.numeroPiscinas + " piscina(s) y se recibirían " + this.numeroPiscinas * this.valorPiscina / 2 + "€.");
                    } else {
                        System.out.println("En esta casilla no tienes piscinas construidas, por lo que no puedes venderlas.");
                    }
                }
            } else {
                System.out.println("No eres el dueño de esta casilla, por lo que no puedes vender piscinas en esta casilla!");
            }
        } else {
            System.out.println("No eres el dueño de esta casilla, por lo que no puedes vender piscinas en esta casilla!");
        }
    }

    public void venderPista(Jugador jugador, Taboleiro taboleiro, int numero) {
        if (this.duenho != null) {
            if (jugador.getAvatar().getId().equals(this.duenho.getAvatar().getId())) {
                if (this.numeroPistas >= numero) {
                    this.numeroPistas = this.numeroPistas - numero;
                    String id[] = new String[numero];
                    for (int i = 0; i < numero; i++) {
                        id[i] = this.idPistas.get(i);
                    }
                    for (int i = 0; i < numero; i++) {
                        this.idPistas.remove(id[i]);
                        jugador.eliminarEdificacion(id[i]);
                    }
                    this.duenho.sumarFortuna((float) (numero * this.valorPistaDeporte / 2));
                    System.out.println(this.duenho.getNombre() + " ha vendido " + numero + " pista(s) en " + this.getNombreSinEspacio() + ", recibiendo " + (numero * this.valorPistaDeporte / 2) + "€. En la propiedad queda(n) " + this.numeroPistas + " pista(s).");
                } else {
                    if (this.numeroPistas > 0) {
                        System.out.println("Solamente se puede(n) vender " + this.numeroPistas + " pista(s) y se recibirían " + this.numeroPistas * this.valorPistaDeporte / 2 + "€.");
                    } else {
                        System.out.println("En esta casilla no tienes pistas construidas, por lo que no puedes venderlas.");
                    }
                }
            } else {
                System.out.println("No eres el dueño de esta casilla, por lo que no puedes vender pistas en esta casilla!");
            }
        } else {
            System.out.println("No eres el dueño de esta casilla, por lo que no puedes vender pistas en esta casilla!");
        }
    }

    public boolean hayEdificios() {
        if (this.idCasas.size() > 0)
            return true;
        if (this.idHoteles.size() > 0)
            return true;
        if (this.idPistas.size() > 0)
            return true;
        if (this.idPiscinas.size() > 0)
            return true;
        return false;
    }

    public boolean hayAlgunaHipoteca() {
        for (Casilla casilla : this.grupo.getCasillas()) {
            if (casilla.esHiportecado) {
                return true;
            }
        }
        return false;
    }

    public void hipotecarCasilla(Jugador jugador, Taboleiro taboleiro) {
        if (taboleiro.sePuedeComprar(this)) {
            if (this.duenho != null) {
                if (this.duenho.getAvatar().getId().equals(jugador.getAvatar().getId())) {
                    if (!this.esHiportecado) {
                        if (!hayEdificios()) {
                            setEsHiportecado(true);
                            jugador.eliminarPropiedad(this);
                            this.duenho = null;
                            this.duenhoAnterior = jugador;
                            jugador.sumarFortuna((float) this.valor / 2);
                            System.out.println(jugador.getNombre() + " recibe " + this.valor / 2 + "€ por la hipoteca de " + this.getNombreSinEspacio() +
                                    ". No puede recibir alquileres ni edificar en el grupo " + this.grupo.getNumeroGrupo() + ".");
                        } else {
                            System.out.println(jugador.getNombre() + " no puede hipotecar " + this.getNombreSinEspacio() + ". No puedes hipotecar la casilla porque existen edificios en ella, antes de hipotecarla debes venderlos todos.");
                        }
                    } else {
                        System.out.println(jugador.getNombre() + " no puede hipotecar " + this.getNombreSinEspacio() + ". No puedes hipotecar esta casilla porque ya está hipotecada.");
                    }
                } else {
                    System.out.println(jugador.getNombre() + " no puede hipotecar " + this.getNombreSinEspacio() + ". No puedes hipotecar una casilla que no te pertenece.");
                }
            } else {
                System.out.println(jugador.getNombre() + " no puede hipotecar " + this.getNombreSinEspacio() + ". No se puede hipotecar una casilla que no tiene dueño.");
            }
        } else {
            System.out.println(jugador.getNombre() + " no puede hipotecar " + this.getNombreSinEspacio() + ". Esta casilla no se puede hipotecar, ya que tampoco puede ser comprada.");
        }
    }

    public void deshipotecarCasilla(Jugador jugador, Taboleiro taboleiro) {
        if (taboleiro.sePuedeComprar(this)) {
            if (this.duenho == null) {
                if (this.duenhoAnterior.getAvatar().getId().equals(jugador.getAvatar().getId())) {
                    if (this.esHiportecado) {
                        if (jugador.getFortuna() >= this.valor / 2) {
                            setEsHiportecado(false);
                            jugador.anhadirPropiedad(this);
                            this.duenho = jugador;
                            this.duenhoAnterior = null;
                            jugador.restarFortuna((float) this.valor / 2);
                            System.out.println(jugador.getNombre() + " paga " + this.valor / 2 + "€ por deshipotecar " + this.getNombreSinEspacio() +
                                    ". Ahora puede recibir alquileres y edificar en el grupo " + this.grupo.getNumeroGrupo() + ".");
                        } else {
                            System.out.println(jugador.getNombre() + " no puede deshipotecar " + this.getNombreSinEspacio() + ". No puedes deshipotecar esta casilla porque no tienes suficiente dinero.");
                        }
                    } else {
                        System.out.println(jugador.getNombre() + " no puede deshipotecar " + this.getNombreSinEspacio() + ". No puedes deshipotecar esta casilla porque no está hipotecada.");
                    }
                } else {
                    System.out.println(jugador.getNombre() + " no puede deshipotecar " + this.getNombreSinEspacio() + ". No puedes deshipotecar esta casilla porque no era tuya antes de que fuese hipotecada.");
                }
            } else {
                System.out.println(jugador.getNombre() + " no puede deshipotecar " + this.getNombreSinEspacio() + ". No puedes deshipotecar esta casilla porque no está hipotecada.");
            }
        } else {
            System.out.println(jugador.getNombre() + " no puede deshipotecar " + this.getNombreSinEspacio() + ". Esta casilla no se puede hipotecar, ya que tampoco puede ser comprada.");
        }
    }

    public String getIdsEdificaciones() {
        String texto = "";
        String aux;
        if (hayEdificios()) {
            if(idCasas.size() > 0){
                texto += "C-";
                while (idCasas.iterator().hasNext()){
                    aux = idCasas.iterator().next().split("-")[1];
                    texto += aux;
                }
            }
            if (idHoteles.size() > 0){
                texto += " H-";
                while (idHoteles.iterator().hasNext()){
                    aux = idHoteles.iterator().next().split("-")[1];
                    texto += aux;
                }
            }
            if (idPiscinas.size() > 0){
                texto += " P-";
                while (idPiscinas.iterator().hasNext()){
                    aux = idPiscinas.iterator().next().split("-")[1];
                    texto += aux;
                }
            }
            if(idPistas.size() > 0){
                texto += " PD-";
                while(idPistas.iterator().hasNext()){
                    aux = idPistas.iterator().next().split("-")[1];
                    texto += aux;
                }
            }
            int nLetras = texto.length();
            if(nLetras < 18){
                for (int i = nLetras; i < 18; ++i){
                    texto += " ";
                }
            }
        } else {
            texto += "                  ";
        }

        return texto;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        String texto;
        if (this.posicion == 7 || this.posicion == 17 || this.posicion == 2 || this.posicion == 22 || this.posicion == 33 || this.posicion == 36 || this.posicion == 30) {
            texto = "No hay información sobre esta casilla!";
        } else if (this.posicion == 10) {
            texto = "";
            for (String[] nombre : this.vecesCasilla.values()) {
                texto += "[" + nombre[0] + ", " + nombre[1] + "] ";
            }
            texto = "{\n\tsalir: " + 0.25 * Valor.VUELTA + ",\n\tjugadores: " + texto + "\n}";
        } else if (this.posicion == 20) {
            texto = "[";
            for (String[] nombre : this.vecesCasilla.values()) {
                texto += nombre[0] + " "; //mirar para ponerle la coma sin que se la ponga al ultimo tambien
            }
            texto += "]";
            texto = "{\n\tbote: " + this.valor + ",\n\tjugadores: " + texto + "\n}";
        } else if (this.posicion == 4 || this.posicion == 38) {
            texto = "{\n\ttipo: " + this.tipo + ",\n\tapagar: " + this.valor + ",\n}";
        } else if (this.posicion == 0) {
            texto = "{\n\ttipo: especial,\n\tvalor a cobrar al pasar: " + Valor.VUELTA + ",\n}";
        } else {
            String banca, edificaciones = "[ ]";
            if (this.duenho == null) {
                banca = "banca";
            } else {
                if (this.duenho.getEdificaciones() != null) {
                    edificaciones = this.duenho.getEdificaciones().toString();
                }
                banca = this.duenho.getNombre();
            }

            texto = "{\n\ttipo: " + this.getTipo() + ",\n\tgrupo: " + this.getGrupo().getNumeroGrupo() + ",\n\tpropietario: " + banca +
                    ",\n\tvalor: " + this.valor + ",\n\talquiler: " + this.valorAlquiler + ",\n\tvalor hotel: " + this.valorHotel +
                    ",\n\tvalor casa: " + this.valorCasa + ",\n\tvalor piscina: " + this.valorPistaDeporte + ",\n\tvalor pista de deporte: "
                    + this.valorPistaDeporte + ",\n\talquiler una casa: " + 5 * this.valor + ",\n\talquiler dos casas: " + 15 * this.valor
                    + ",\n\talquiler tres casas: " + 35 * this.valor + ",\n\talquiler cuatro casas: " + 50 * this.valor + ",\n\talquiler hotel: "
                    + 70 * this.valor + ",\n\talquiler piscina: " + 25 * this.valor + ",\n\talquiler pista de deporte: " + 25 * this.valor
                    + ",\n\tedificaciones: " + edificaciones + ",\n}";
        }
        return texto;
    }


    /*@Override
    public boolean equals(Object obj){
        if (obj instanceof juego_fisico.Casilla){
            if(((juego_fisico.Casilla)obj).getNombre().equals(this.nombre)){
                return true;
            }
        }
        return false;
    }*/


}