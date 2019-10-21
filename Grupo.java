import java.util.ArrayList;

public class Grupo {
    private int numeroGrupo = 0;
    private ArrayList<Casilla> casillas;
    private double valor;
    private String color;
    private String lado;

    public Grupo() {
    }

    public Grupo(ArrayList<Casilla> casillas, int numeroGrupo, String color, String lado, double valor) {
        this.casillas = casillas;
        this.numeroGrupo = numeroGrupo;
        setColor(color);
        this.lado = lado;
        setValor(valor);
        SetGrupo(this);
    }

    public Grupo(ArrayList<Casilla> casillas, int numeroGrupo, String color, String lado) {
        this.casillas = casillas;
        this.numeroGrupo = numeroGrupo;
        this.valor = 100000 * Math.pow(1.3, this.numeroGrupo - 1);
        setValor(valor);
        this.lado = lado;
        setColor(color);
        SetGrupo(this);
    }

    public Grupo(ArrayList<Casilla> casillas, int numeroGrupo, double valor, String color) {
        this.casillas = casillas;
        this.numeroGrupo = numeroGrupo;
        this.valor = valor;
        setValor(valor);
        this.lado = null;
        setColor(color);
        SetGrupo(this);
    }

    public Grupo(ArrayList<Casilla> casillas, int numeroGrupo, String lado) {
        this.casillas = casillas;
        this.numeroGrupo = numeroGrupo;
        this.lado = lado;
        SetGrupo(this);
    }

    public Grupo(ArrayList<Casilla> casillas, String color, int numeroGrupo) {
        this.casillas = casillas;
        this.numeroGrupo = numeroGrupo;
        setColor(color);
        SetGrupo(this);
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
        this.color = color;
        for (Casilla casilla : casillas) {
            casilla.setColorGrupo(color);
        }
    }

    public void setValor(double valor) {
        for (Casilla casilla : casillas) {
            casilla.setValor(valor);
        }
    }

    public void SetGrupo(Grupo grupo) {
        for (Casilla casilla : casillas) {
            casilla.setGrupo(grupo);
        }
    }

    // Se debería borrar que no permitimos cambiar el lado por el medio de la partida
    public void setLado(String lado) {
        this.lado = lado;
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
        for (Casilla cas : casillas) {
            if (cas.getDuenho().getAvatar().getId().equals(jugador.getAvatar().getId())) {
                contador++;
            }
        }
        return contador;
    }

    @Override
    public String toString() {
        String texto;
        String prop = "";

        if (casillas.size() != 0) {
            for (Casilla casilla : casillas) {
                prop += casilla.getNombre() + "\t\t";
            }
        }
        if ((this.numeroGrupo != 0) && (this.color != null)) {
            texto = "\nO grupo de casillas " + this.numeroGrupo +
                    " é de color " + this.color +
                    ".\nConta coas casillas:\n\t" + prop;
        } else
            texto = "O grupo todavía non se inicializou.";
        return texto;
    }

    public boolean equals(Object obj) {
        if (this.getNumeroGrupo() == ((Grupo) obj).getNumeroGrupo()) {
            return true;
        } else {
            return false;
        }
    }
}
