package Trato;

import Casilla.Propiedad;
import Jugador.Jugador;

public final class Trato5 extends Tratos {
    private Propiedad propiedadOrigen;
    private double dineroOrigen;
    private Propiedad propiedadDestino;

    public Trato5() {
        super();
    }

    public Trato5(String id, Jugador jugadorOrigen, Jugador jugadorDestino, Propiedad propiedadOrigen, double dineroOrigen, Propiedad propiedadDestino) {
        super(id, jugadorOrigen, jugadorDestino);
        if ((propiedadOrigen != null) && (propiedadDestino != null) && (dineroOrigen >= 0)) {
            this.propiedadOrigen = propiedadOrigen;
            this.dineroOrigen = dineroOrigen;
            this.propiedadDestino = propiedadDestino;
        }
    }

    public Propiedad getPropiedadOrigen() {
        return this.propiedadOrigen;
    }

    public Propiedad getPropiedadDestino() {
        return propiedadDestino;
    }

    public double getDineroOrigen() {
        return this.dineroOrigen;
    }

    //Los setters no se desean añadir

    public String textoDisponible() {
        String texto;
        texto = "{\n\tid: " + super.getId() + ",\n\t" +
                "proponeA: " + getJugadorDestino().getNombre() + ",\n\t" +
                "trato: cambiar ( (" + propiedadOrigen.getNombreSinEspacio() + ", " + dineroOrigen + "€), " + propiedadDestino.getNombreSinEspacio() + ")\n}";
        return texto;
    }

    public String toString() {
        String texto;
        texto = "{\n\tid: " + super.getId() + ",\n\t" +
                "jugadorPropone: " + getJugadorOrigen().getNombre() + ",\n\t" +
                "trato: cambiar ( (" + propiedadOrigen.getNombreSinEspacio() + ", " + dineroOrigen + "€), " + propiedadDestino.getNombreSinEspacio() + ")\n}";
        return texto;
    }
}