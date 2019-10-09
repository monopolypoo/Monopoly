import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private float fortuna;
    private float dineroGastado;
    private Avatar avatar;
    private ArrayList<Casilla> propiedades;

    public Jugador() {
        this.nombre = "banca";
        this.fortuna = 1000000000;
        this.dineroGastado = 0;
        this.avatar = null;
        this.propiedades = new ArrayList<>(); //tambien se le pueden poner inicialmente todas las propiedades a la banca
    }

    public Jugador(String nombre, String tipo_avatar,  ArrayList<Jugador> jugadores, Casilla casilla){
        this.nombre = nombre;
        this.fortuna = Valor.DINERO_INICIAL;
        this.dineroGastado = 0;
        Avatar avatar = new Avatar(tipo_avatar, this, jugadores, casilla);
        this.avatar = avatar;
        this.propiedades = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Avatar getAvatar() {
        return avatar;
    }
    //No definimos un setAvatar porque no permitimos cambiar de avatar una vez asignado

    public float getDineroGastado() {
        return dineroGastado;
    }
    public void setDineroGastado(float dineroGastado) {
        this.dineroGastado = dineroGastado;
    }

    public float getFortuna() {
        return fortuna;
    }
    public void setFortuna(float fortuna) {
        this.fortuna = fortuna;
    }

    public ArrayList<Casilla> getPropiedades(){
        return (propiedades);
    }
    public void setPropiedades(ArrayList<Casilla> propiedades){
        if (propiedades == null){
            System.err.println("Error: propiedades no inicializadas.");
            return;
        }
        for (Casilla casilla: propiedades){
            if (casilla == null){
                System.err.println("Error: casilla no inicializada.");
                return;
            }
        }
        this.propiedades = propiedades;
    }

    public void anhadirPropiedad(Casilla casilla){
        if (casilla != null)
            this.propiedades.add(casilla);
    }

    @Override
    public String toString(){
        String texto;
        String prop = "";
        if (this.avatar == null){
            texto = "{\n\tNombre: " + this.nombre + "\n}";
        }
        else{
            if (propiedades.size() != 0){
                for (Casilla propi : propiedades){
                    prop += propi.getNombre() + "\t";
                }
            }
            texto = "{\n" +
                    "\tNombre: " + this.nombre +
                    "\n\tAvatar: " + this.avatar.getId() +
                    "\n\tFortuna: " + this.fortuna +
                    "\n\tGastos: " + this.dineroGastado +
                    "\n\tPropiedades: " + prop + "\n}";
        }
        return texto;
    }
}



