public class Casilla {
    private String nombre;
    private String tipo;
    private float valor;
    private int posicion;
    private Jugador duenho;

    public Casilla(){}

    public Casilla(String nombre, String tipo, int posicion, float valor){
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = valor;
        //duenho = banca??
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

    public String getTipo(){
        return tipo;
    }
    public boolean pertence(Jugador jugador) {
        return this.duenho.getAvatar().equals(jugador.getAvatar());
    }

    public int getPosicion() {
        return posicion;
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
