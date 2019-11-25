public abstract class Accion extends Casilla {

    public Accion(String nombre, int posicion, String colorGrupo) {
        super(nombre, posicion, colorGrupo);
    }

    public abstract String toString();
}
