package ExcepcionesNumericas;

public final class ExcepcionesNumericas extends Exception {
    private int numeroDefecto;

    public ExcepcionesNumericas(){
        super();
    }

    public ExcepcionesNumericas(String mensaje){
        super(mensaje);
    }

    public ExcepcionesNumericas(String mensaje, int numeroDefecto){
        super(mensaje);
        this.numeroDefecto = numeroDefecto;
    }

    public int getNumeroDefecto() {
        return numeroDefecto;
    }
}
