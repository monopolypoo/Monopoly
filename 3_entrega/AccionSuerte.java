public final class AccionSuerte extends Casilla {


    public AccionSuerte(String nombre, int posicion, String colorGrupo) {
        super(nombre, posicion, colorGrupo);
        if (posicion != 7 && posicion != 22 && posicion != 36)
            System.out.println("Esta casilla no pertenece a suerte!");
    }

    @Override
    public String toString() {
        return "No hay información sobre esta casilla!";
    }
}
