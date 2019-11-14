
package juego_fisico;

import partida_virtual.*;
import monopoly.*;

import java.util.ArrayList;
import java.util.Objects;

public class Grupo {
    private int numeroGrupo = 0;
    private ArrayList<Casilla> casillas;
    private double valor;
    private String color;
    private String lado;
    private int numeroHoteles;
    private int nmeroPistas;
    private int numeroPiscinas;

    public Grupo() {
    }

    public Grupo(ArrayList<Casilla> casillas, int numeroGrupo, String color, String lado, double valor) {
        this.casillas = casillas;
        if (numeroGrupo >= 0) {
            this.numeroGrupo = numeroGrupo;
        }
        setColor(color);
        if (lado.equals("norte") || lado.equals("sur") || lado.equals("este") || lado.equals("oeste")) {
            this.lado = lado;
        }
        setValor(valor);
        this.numeroHoteles = 0;
        this.nmeroPistas = 0;
        this.numeroPiscinas = 0;
    }

    public Grupo(ArrayList<Casilla> casillas, int numeroGrupo, String color, String lado) {
        this.casillas = casillas;
        if (numeroGrupo >= 0) {
            this.numeroGrupo = numeroGrupo;
            this.valor = 100000 * Math.pow(1.3, this.numeroGrupo - 1);
            setValor(valor);
        }
        if (lado.equals("norte") || lado.equals("sur") || lado.equals("este") || lado.equals("oeste")) {
            this.lado = lado;
        }
        setColor(color);
        this.numeroHoteles = 0;
        this.nmeroPistas = 0;
        this.numeroPiscinas = 0;
    }

    public Grupo(ArrayList<Casilla> casillas, int numeroGrupo, double valor, String color) {
        this.casillas = casillas;
        this.numeroGrupo = numeroGrupo;
        this.valor = valor;
        setValor(valor);
        this.lado = null;
        setColor(color);
        this.numeroHoteles = 0;
        this.nmeroPistas = 0;
        this.numeroPiscinas = 0;
    }

    public Grupo(ArrayList<Casilla> casillas, int numeroGrupo, String lado) {
        this.casillas = casillas;
        this.numeroGrupo = numeroGrupo;
        if (lado.equals("norte") || lado.equals("sur") || lado.equals("este") || lado.equals("oeste")) {
            this.lado = lado;
        }
        this.numeroHoteles = 0;
        this.nmeroPistas = 0;
        this.numeroPiscinas = 0;
    }

    public Grupo(ArrayList<Casilla> casillas, String color, int numeroGrupo) {
        this.casillas = casillas;
        this.numeroGrupo = numeroGrupo;
        setColor(color);
        this.numeroHoteles = 0;
        this.nmeroPistas = 0;
        this.numeroPiscinas = 0;
    }

    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    public int getNumeroGrupo() {
        return numeroGrupo;
    }

    public String getColor() {
        return color;
    }

    public String getLado() {
        return lado;
    }

    public double getValor() {
        return valor;
    }

    public int getNumeroPistas() {
        return nmeroPistas;
    }

    public int getNumeroHoteles() {
        return numeroHoteles;
    }

    public int getNumeroPiscinas() {
        return numeroPiscinas;
    }

    public void setNumeroPistas(int nmeroPistas) {
        if (nmeroPistas >= 0 && nmeroPistas <= this.casillas.size()) {
            this.nmeroPistas = nmeroPistas;
        }
    }

    public int getNumeroSolares() {
        return this.casillas.size();
    }

    public void setNumeroHoteles(int numeroHoteles) {
        if (numeroHoteles >= 0 && numeroHoteles <= this.casillas.size()) {
            this.numeroHoteles = numeroHoteles;

        }
    }

    public void setNumeroPiscinas(int numeroPiscinas) {
        if (numeroPiscinas >= 0 && numeroPiscinas <= this.casillas.size()) {
            this.numeroPiscinas = numeroPiscinas;
        }
    }

    public void setCasillas(ArrayList<Casilla> casillas) {
        if (casillas == null) {
            System.err.println("Error: casillas no inicializadas.");
            return;
        }
        for (Casilla casilla : casillas) {
            if (casilla == null) {
                System.err.println("Error: casilla no inicializada.");
                return;
            }
        }
        this.casillas = casillas;
    }

    // Se debería borrar que no permitimos cambiar el número del grupo por el medio de la partida
    public void setNumeroGrupo(int numeroGrupo) {
        this.numeroGrupo = numeroGrupo;
    }

    public void setColor(String color) {
        if (color != null) {
            this.color = color;
            for (Casilla casilla : casillas) {
                casilla.setColorGrupo(color);
            }
        }
    }

    public void setValor(double valor) {
        if (valor > 0) {
            for (Casilla casilla : casillas) {
                casilla.SetValor(valor);
            }
        }
    }

    public void SetGrupo() {
        if (this != null) {
            for (Casilla casilla : casillas) {
                casilla.setGrupo(this);
            }
        }
    }

    // Se debería borrar que no permitimos cambiar el lado por el medio de la partida
    public void setLado(String lado) {
        if (lado.equals("norte") || lado.equals("sur") || lado.equals("este") || lado.equals("oeste")) {
            this.lado = lado;
        }
    }

    public void anhadirCasilla(Casilla casilla) {
        if (casilla != null)
            this.casillas.add(casilla);
    }

    public boolean tenerTodasCasillas() {
        Jugador jugadorActual, jugadorAnterior = null;
        for (Casilla cas : casillas) {
            if (cas.getDuenho() != null) {
                jugadorActual = cas.getDuenho();
                if (jugadorAnterior != null) {
                    if (!jugadorAnterior.getAvatar().getId().equals(jugadorActual.getAvatar().getId())) {
                        return false;
                    }
                }
                jugadorAnterior = jugadorActual;
            } else {
                return false;
            }
        }
        return true;
    }

    public int cuantasCasillasTiene(Jugador jugador) {
        int contador = 0;
        if (jugador != null) {
            for (Casilla cas : casillas) {
                if (cas.getDuenho().getAvatar().getId().equals(jugador.getAvatar().getId())) {
                    contador++;
                }
            }
        }
        return contador;
    }

    @Override
    public String toString() {
        String texto = "";
        int casas = 0, hoteles = 3, piscinas = 3, pistas = 3, numGrupo = this.casillas.size();

        if (this.casillas.size() != 0) {
            for (Casilla casilla : this.casillas) {
                texto += "\n{\n\tPropiedad: " + casilla.getNombreSinEspacio() +
                        ",\n\tCasas: " + casilla.getIdCasas().toString() +
                        ",\n\tHoteles: " + casilla.getIdHoteles().toString() +
                        ",\n\tPiscinas: " + casilla.getIdPiscinas().toString() +
                        ",\n\tPista: " + casilla.getIdPistas().toString() +
                        ",\n\tAlquiler: " + casilla.getValorAlquiler() + ",\n}\n";
                casas += 4 - casilla.getIdCasas().size();
                hoteles -= casilla.getIdHoteles().size();
                pistas -= casilla.getIdPistas().size();
                piscinas -= casilla.getIdPiscinas().size();
            }
            if (casas != 0) {
                texto += "Se pueden edificar " + casas + " casas más. ";
            } else {
                texto += "No se pueden edificar más casas. ";
            }
            if (hoteles != 0) {
                texto += "Se pueden edificar " + hoteles + " hoteles más. \n";
            } else {
                texto += "No se pueden edificar más hoteles. \n";
            }
            if (pistas != 0) {
                texto += "Se pueden edificar " + pistas + " pistas de deporte más. ";
            } else {
                texto += "No se pueden edificar más pistas de deporte. ";
            }
            if (piscinas != 0) {
                texto += "Se pueden edificar " + piscinas + " piscinas más. ";
            } else {
                texto += "No se pueden edificar más piscinas. ";
            }
        }

        return texto;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getNumeroGrupo() == ((Grupo) obj).getNumeroGrupo()) {
            return true;
        } else {
            return false;
        }
    }
}