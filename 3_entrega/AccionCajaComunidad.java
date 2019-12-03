public final class AccionCajaComunidad extends Accion {
    private Comunidad carta;

    public AccionCajaComunidad(String nombre, int posicion, String colorGrupo) {
        super(nombre, posicion, colorGrupo);
        if (posicion != 2 && posicion != 17 && posicion != 33)
            System.out.println("Esta casilla no pertenece a caja de comunidad!");
        this.carta = new Comunidad();
    }

    public Comunidad getCarta() {
        return carta;
    }

    @Override
    public String toString() {
        return "No hay información sobre esta casilla!";
    }
}