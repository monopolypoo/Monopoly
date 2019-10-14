import java.util.ArrayList;
import java.util.HashMap;

public class Casilla {
    private String nombre;
    private String tipo;
    private double valor;
    private int posicion;
    private Jugador duenho;
    private String colorGrupo;
    private double valorCasa;
    private double valorPiscina;
    private double valorPistaDeporte;
    private double valorHotel;
    private double valorAlquiler;
    private ArrayList<String> vecesCasilla;
    private HashMap<String, Avatar> avatares;

    public Casilla() {
    }

    public Casilla(String nombre, String tipo, int posicion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = 0;
        this.colorGrupo = null;
        this.vecesCasilla = new ArrayList<>();
        this.avatares = new HashMap<>();
    }


    public Casilla(String nombre, String tipo, int posicion, double valor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = valor;
        this.colorGrupo = null;
        this.duenho = null;
        this.vecesCasilla = new ArrayList<>();
        this.avatares = new HashMap<>();
    }

    public Casilla(String nombre, String tipo, int posicion, double valor, String colorGrupo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = valor;
        this.colorGrupo = colorGrupo;
        this.vecesCasilla = new ArrayList<>();
        this.avatares = new HashMap<>();
        //this.duenho = banca
    }

    public Jugador getDuenho() {
        return duenho;
    }

    public void setDuenho(Jugador jugador) {
        this.duenho = jugador;
    }

    public void setVecesCasilla(Jugador jugador) {
        this.vecesCasilla.add(jugador.getNombre());
    }

    public int getVecesCasilla(Jugador jugador) {
        int veces = 0;
        for (String nombre : this.vecesCasilla){
            if(nombre.equals(jugador.getNombre())){
                veces++;
            }
        }
        return veces;
    }

    /*public String GetVecesCasilla(){
        for (String nombre : this.vecesCasilla){
            if()
        }
        return "";
    }*/

    public String getNombre() {
        return nombre;
    }

    public String getNombreSinEspacio() {
        String[] nom;
        nom = nombre.split(" ");
        if (nom.length == 2) {
            return nom[0] + " " + nom[1];
        } else if (nom.length == 3) {
            return nom[0] + " " + nom[1] + " " + nom[2];
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
        this.valorCasa = 0.6 * this.valor;
        this.valorPiscina = 0.4 * this.valor;
        this.valorPistaDeporte = 1.25 * this.valor;
        this.valorHotel = this.valorCasa;
        this.valorAlquiler = 0.1 * this.valor;
    }

    public boolean pertence(Jugador jugador) {
        return this.duenho.getAvatar().equals(jugador.getAvatar());
    }

    public int getPosicion() {
        return posicion;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    //escribir toString()

    @Override
    public String toString() {
        String texto;
        if (this.posicion == 7 || this.posicion == 17 || this.posicion == 2 || this.posicion == 22 || this.posicion == 33 || this.posicion == 36 || this.posicion == 30) {
            texto = "No hay información sobre esta casilla!";
        } else if (this.posicion == 10) {

                texto = "{\n\tsalir: " + 0.25 * Valor.VUELTA + ",\n\tjugadores: ";
        } else {
            String banca;
            if (this.duenho == null) {
                banca = "banca";
            } else {
                banca = this.duenho.getNombre();
            }
            texto = "{\n\ttipo: " + this.getTipo() + ",\n\tgrupo: " + /*this.colorGrupo + */ ",\n\tpropietario: " + banca +
                    ",\n\tvalor: " + this.valor + ",\n\talquiler: " + this.valorAlquiler + ",\n\tvalor hotel: " + this.valorHotel +
                    ",\n\tvalor casa: " + this.valorCasa + ",\n\tvalor piscina: " + this.valorPistaDeporte + ",\n\tvalor pista de deporte: "
                    + this.valorPistaDeporte + ",\n\talquiler una casa: " + 5 * this.valor + ",\n\talquiler dos casas: " + 15 * this.valor
                    + ",\n\talquiler tres casas: " + 35 * this.valor + ",\n\talquiler cuatro casas: " + 50 * this.valor + ",\n\talquiler hotel: "
                    + 70 * this.valor + ",\n\talquiler piscina: " + 25 * this.valor + ",\n\talquiler pista de deporte: " + 25 * this.valor
                    + ",\n}";
        }
        return texto;
    }


    /*@Override
    public boolean equals(Object obj){
        if (obj instanceof Casilla){
            if(((Casilla)obj).getNombre().equals(this.nombre)){
                return true;
            }
        }
        return false;
    }*/


}
