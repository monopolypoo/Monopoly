package ExcepcionesPartida;

public abstract class ExcepcionesPartida extends Exception {

    public ExcepcionesPartida(){
        super();
    }

    public ExcepcionesPartida(String mensaje){
        super(mensaje);
    }
}
