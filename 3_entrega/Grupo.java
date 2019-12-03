import java.util.ArrayList;

public class Grupo {
    private int numeroGrupo;
    private ArrayList<Casilla> casillas;
    private String color;
    private String lado;
    private int numeroHoteles;
    private int nmeroPistas;
    private int numeroPiscinas;

    public Grupo() {
    }

    /*********************************************************************************************************
     ********** MODIFICAR LOS CONSTRUCTORES DE GRUPO Y TENER CUIDADO CON SETVALOR()!!!!!!!!!! ****************
     *********************************************************************************************************/

    public Grupo(ArrayList<Casilla> casillas, int numeroGrupo, String color, String lado) {
        this.casillas = casillas;
        if (numeroGrupo >= 0) {
            this.numeroGrupo = numeroGrupo;
        }
        setColor(color);
        if (lado.equals("norte") || lado.equals("sur") || lado.equals("este") || lado.equals("oeste")) {
            this.lado = lado;
        }
        this.numeroHoteles = 0;
        this.nmeroPistas = 0;
        this.numeroPiscinas = 0;
    }

    public Grupo(int numeroGrupo, ArrayList<Casilla> casillas, String color) {
        this.casillas = casillas;
        this.numeroGrupo = numeroGrupo;
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

    public void SetGrupo() {
        for (Casilla casilla : casillas) {
            casilla.setGrupo(this);
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

    public int cuantasCasillasTiene(Jugador jugador) {
        int contador = 0;
        if (jugador != null) {
            for (Casilla cas : this.casillas) {
                if (cas instanceof Propiedad) {
                    Propiedad prop = ((Propiedad) cas);
                    if (prop.getDuenho() != null) {
                        if (prop.getDuenho().equals(jugador)) {
                            contador++;
                        }
                    }
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
                texto += "\n{\n\tPropiedad: " + casilla.getNombreSinEspacio();
                if (casilla instanceof Solar) {
                    texto += ",\n\tCasas: " + ((Solar) casilla).getCasas().toString() +
                            ",\n\tHoteles: " + ((Solar) casilla).getHoteles().toString() +
                            ",\n\tPiscinas: " + ((Solar) casilla).getPiscinas().toString() +
                            ",\n\tPista: " + ((Solar) casilla).getPistas().toString();
                    casas += 4 - ((Solar) casilla).getCasas().size();
                    hoteles -= ((Solar) casilla).getHoteles().size();
                    pistas -= ((Solar) casilla).getPistas().size();
                    piscinas -= ((Solar) casilla).getPiscinas().size();
                }
                texto += ",\n\tAlquiler: " + ((Solar) casilla).getValorAlquiler() + ",\n}\n";
            }
            if (this.casillas.get(0) instanceof Solar) {
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
