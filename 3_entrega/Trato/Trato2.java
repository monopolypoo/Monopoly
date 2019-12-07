package Trato;

import Casilla.Propiedad;
import Jugador.Jugador;

public final class Trato2 extends Tratos {
    private Propiedad propiedadOrigen;
    private double dineroDestino;

    public Trato2() {
        super();
    }

    public Trato2(String id, Jugador jugadorOrigen, Jugador jugadorDestino, Propiedad propiedadOrigen, double dineroDestino) {
        super(id, jugadorOrigen, jugadorDestino);
        if ((propiedadOrigen != null) && (dineroDestino >= 0)) {
            this.propiedadOrigen = propiedadOrigen;
            this.dineroDestino = dineroDestino;
        }
    }

    public Propiedad getPropiedadOrigen() {
        return this.propiedadOrigen;
    }

    public double getDineroDestino() {
        return this.dineroDestino;
    }

    //Los setters no se desean añadir

    public String textoDisponible() {
        String texto;
        texto = "{\n\tid: " + super.getId() + ",\n\t" +
                "proponeA: " + getJugadorDestino().getNombre() + ",\n\t" +
                "trato: cambiar (" + propiedadOrigen.getNombreSinEspacio() + ", " + dineroDestino + "€)\n}";
        return texto;
    }

    public String toString() {
        String texto;
        texto = "{\n\tid: " + super.getId() + ",\n\t" +
                "jugadorPropone: " + getJugadorOrigen().getNombre() + ",\n\t" +
                "trato: cambiar (" + propiedadOrigen.getNombreSinEspacio() + ", " + dineroDestino + "€)\n}";
        return texto;
    }
}
