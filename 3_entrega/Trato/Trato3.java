package Trato;

import Casilla.Propiedad;
import Jugador.Jugador;

public final class Trato3 extends Tratos {
    private double dineroOrigen;
    private Propiedad propiedadDestino;

    public Trato3() {
        super();
    }

    public Trato3(String id, Jugador jugadorOrigen, Jugador jugadorDestino, double dineroOrigen, Propiedad propiedadDestino) {
        super(id, jugadorOrigen, jugadorDestino);
        if ((dineroOrigen >= 0) && (propiedadDestino != null)) {
            this.dineroOrigen = dineroOrigen;
            this.propiedadDestino = propiedadDestino;
        }
    }

    public double getDineroOrigen() {
        return this.dineroOrigen;
    }

    public Propiedad getPropiedadDestino() {
        return this.propiedadDestino;
    }

    //Los setters no se desean añadir

    public String textoDisponible() {
        String texto;
        texto = "{\n\tid: " + super.getId() + ",\n\t" +
                "proponeA: " + getJugadorDestino().getNombre() + ",\n\t" +
                "trato: cambiar (" + dineroOrigen + "€, " + propiedadDestino.getNombreSinEspacio() + ")\n}";
        return texto;
    }

    public String toString() {
        String texto;
        texto = "{\n\tid: " + super.getId() + ",\n\t" +
                "jugadorPropone: " + getJugadorOrigen().getNombre() + ",\n\t" +
                "trato: cambiar (" + dineroOrigen + "€, " + propiedadDestino.getNombreSinEspacio() + ")\n}";
        return texto;
    }
}
