import java.util.HashMap;

public class Casilla {
    private String nombre;
    private String tipo;
    private double valor;
    private int posicion;
    private Jugador duenho;
    private String colorGrupo;
    HashMap<String, Avatar> avatares;

    public Casilla(){}

    public Casilla(String nombre, String tipo, int posicion){
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = 0;
        this.colorGrupo = null;
        avatares = new HashMap<>();
    }

    public Casilla(String nombre, String tipo, int posicion, double valor){
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = valor;
        this.colorGrupo = null;
        avatares = new HashMap<>();
    }

    public Casilla(String nombre, String tipo, int posicion, double valor, String colorGrupo){
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = valor;
        this.colorGrupo = colorGrupo;
        avatares = new HashMap<>();
        //this.duenho = banca
    }

    public Jugador getDuenho(){
        return duenho;
    }

    public void setDuenho(Jugador jugador){
        this.duenho = jugador;
    }

    public String getNombre(){
        return nombre;
    }

    public String getNombreSinEspacio(){
        String[] nom;
        nom = nombre.split(" ");
        return nom[0];
    }

    public String getTipo(){
        return tipo;
    }

    public String getColorGrupo(){ return colorGrupo;}

    public void setColorGrupo(String colorGrupo){
        this.colorGrupo = colorGrupo;
    }

    public void setValor(double valor){
        this.valor = valor;
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
