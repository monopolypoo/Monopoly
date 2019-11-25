import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public abstract class Casilla {
    private String nombre;
    private int posicion;
    private String colorGrupo;
    private HashMap<String, String[]> vecesCasilla;
    private HashMap<String, Avatar> avatares;

    public Casilla() {
        this.nombre = null;
        this.posicion = 0;
        this.colorGrupo = null;
        this.vecesCasilla = new HashMap<>();
        this.avatares = new HashMap<String, Avatar>();
    }

    public Casilla(String nombre, int posicion, String colorGrupo) {
        if (nombre != null)
            this.nombre = nombre;
        if (posicion >= 0 && posicion <= 39)
            this.posicion = posicion;
        if (colorGrupo != null)
            this.colorGrupo = colorGrupo;
        this.vecesCasilla = new HashMap<>();
        this.avatares = new HashMap<String, Avatar>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null)
            this.nombre = nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        if (posicion >= 0 && posicion <= 39)
            this.posicion = posicion;
    }

    public String getColorGrupo() {
        return colorGrupo;
    }

    public void setColorGrupo(String colorGrupo) {
        if (colorGrupo != null)
            this.colorGrupo = colorGrupo;
    }

    public HashMap<String, String[]> getVecesCasilla() {
        return vecesCasilla;
    }

    public void setVecesCasilla(HashMap<String, String[]> vecesCasilla) {
        if (vecesCasilla != null)
            this.vecesCasilla = vecesCasilla;
    }

    public HashMap<String, Avatar> getAvatares() {
        return avatares;
    }

    public void setAvatares(HashMap<String, Avatar> avatares) {
        if (avatares != null)
            this.avatares = avatares;
    }

    public boolean estaAvatar() {
        return this.avatares.size() != 0;
    }

    public int frecuenciaVisita(String id) {
        if (id != null)
            if (this.vecesCasilla.containsKey(id)) {
                return Integer.parseInt(this.vecesCasilla.get(id)[1]);
            }

        return 0;
    }

    @Override
    public abstract String toString();
}
