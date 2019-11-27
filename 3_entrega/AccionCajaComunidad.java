public final class AccionCajaComunidad extends Accion {


    public AccionCajaComunidad(String nombre, int posicion, String colorGrupo) {
        super(nombre, posicion, colorGrupo);
        if (posicion != 2 && posicion != 17 && posicion != 33)
            System.out.println("Esta casilla no pertenece a caja de comunidad!");
    }

    @Override
    public String toString() {
        return "No hay información sobre esta casilla!";
    }
}