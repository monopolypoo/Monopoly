import java.util.HashMap;

public abstract class Casilla {
    private String nombre;
    private int posicion;
    private String colorGrupo;
    private HashMap<String, String[]> vecesCasilla;
    private HashMap<String, Avatar> avatares;
    private Grupo grupo;

    public Casilla(){
        this.nombre = null;
        this.posicion = 0;
        this.colorGrupo = null;
        this.vecesCasilla = new HashMap<>();
        this.avatares = new HashMap<>();
        this.grupo = null;
    }

    public Casilla(String nombre, int posicion){
        this.nombre = nombre;
        this.posicion = posicion;
        this.colorGrupo = null;
        this.vecesCasilla = new HashMap<>();
        this.avatares = new HashMap<>();
        this.grupo = null;
    }

    public Casilla(String nombre, int posicion, String colorGrupo){
        this.nombre = nombre;
        this.posicion = posicion;
        this.colorGrupo = colorGrupo;
        this.vecesCasilla = new HashMap<>();
        this.avatares = new HashMap<>();
        this.grupo = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getColorGrupo() {
        return colorGrupo;
    }

    public void setColorGrupo(String colorGrupo) {
        this.colorGrupo = colorGrupo;
    }

    public HashMap<String, String[]> getVecesCasilla() {
        return vecesCasilla;
    }

    public void setVecesCasilla(HashMap<String, String[]> vecesCasilla) {
        this.vecesCasilla = vecesCasilla;
    }

    public HashMap<String, Avatar> getAvatares() {
        return avatares;
    }

    public void setAvatares(HashMap<String, Avatar> avatares) {
        this.avatares = avatares;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public boolean haiAvatar() {
        return this.avatares.size() != 0;
    }

    public void eliminarAvatar(String id) {
        this.avatares.remove(id);
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

    public abstract String toString();
}
