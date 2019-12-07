package Casilla;

import ExcepcionesNumericas.ExcepcionesNumericas;
import Juego_fisico.*;
import Jugador.*;

import java.util.HashMap;

public abstract class Casilla {
    private String nombre;
    private int posicion;
    private String colorGrupo;
    private HashMap<String, String[]> vecesCasilla;
    private HashMap<String, Avatar> avatares;
    private Grupo grupo;

    public Casilla() {
        this.nombre = null;
        this.posicion = 0;
        this.colorGrupo = null;
        this.vecesCasilla = new HashMap<>();
        this.avatares = new HashMap<>();
        this.grupo = null;
    }

    public Casilla(String nombre, int posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.colorGrupo = null;
        this.vecesCasilla = new HashMap<>();
        this.avatares = new HashMap<>();
        this.grupo = null;
    }

    public Casilla(String nombre, int posicion, String colorGrupo) {
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

    /*public void setVecesCasilla(HashMap<String, String[]> vecesCasilla) {
        this.vecesCasilla = vecesCasilla;
    }*/

    public void setVecesCasilla(Jugador jugador) throws ExcepcionesNumericas {
        String[] texto = new String[2];
        int aux;
        if (jugador != null)
            if (this.vecesCasilla.containsKey(jugador.getAvatar().getId())) {
                texto = this.vecesCasilla.get(jugador.getAvatar().getId());
                try {
                    aux = Integer.parseInt(texto[1]);
                } catch (NumberFormatException exc){
                    throw new ExcepcionesNumericas("Error pasando el string a entero.");
                }
                aux++;
                texto[1] = "" + aux;
                this.vecesCasilla.replace(jugador.getAvatar().getId(), texto);
            } else {
                texto[0] = jugador.getAvatar().getId();
                texto[1] = "1";
                this.vecesCasilla.put(jugador.getAvatar().getId(), texto);
            }
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

    public boolean isSubirPrecio() throws ExcepcionesNumericas {
        for (String[] cadena : this.vecesCasilla.values()) {
            try {
                if (Integer.parseInt(cadena[1]) < 4)
                    return false;
            } catch (NumberFormatException exc){
                throw new ExcepcionesNumericas("Error pasando el string a entero.");
            }

        }
        return true;
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

    public void setAvatar(Avatar avatar) {
        if (avatar != null)
            this.avatares.put(avatar.getId(), avatar);
    }

    public Avatar getAvatarCasilla(String id) {
        if (id != null)
            return this.avatares.get(id);
        return null;
    }

    public int frecuenciaVisita() throws ExcepcionesNumericas {
        int contador = 0;
        for (String[] aux : this.vecesCasilla.values()){
            try {
                contador += Integer.parseInt(aux[1]);
            } catch (NumberFormatException exc){
                throw new ExcepcionesNumericas("Error conviertiendo el string a entero.");
            }
        }
        return contador;
    }

    @Override
    public abstract String toString();
}
