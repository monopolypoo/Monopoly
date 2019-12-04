package ExcepcionesPartida;

public final class ExcepcionesHipotecar extends ExcepcionesHipotecas{
    private String nombreCasilla;

    public ExcepcionesHipotecar(){
        super();
        this.nombreCasilla = null;
    }

    public ExcepcionesHipotecar(String nombreCasilla, String mensaje){
        super(nombreCasilla + mensaje);
        this.nombreCasilla = nombreCasilla;
    }

    public String getNombreCasilla() {
        return this.nombreCasilla;
    }
}
