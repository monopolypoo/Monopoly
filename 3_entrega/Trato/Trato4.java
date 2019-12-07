package Trato;

import Casilla.Propiedad;
import Jugador.Jugador;

public final class Trato4 extends Tratos {
    private Propiedad propiedadOrigen;
    private Propiedad propiedadDestino;
    private double dineroDestino;

    public Trato4() {
        super();
    }

    public Trato4(String id, Jugador jugadorOrigen, Jugador jugadorDestino, Propiedad propiedadOrigen, Propiedad propiedadDestino, double dineroDestino) {
        super(id, jugadorOrigen, jugadorDestino);
        if ((propiedadOrigen != null) && (propiedadDestino != null) && (dineroDestino >= 0)) {
            this.propiedadOrigen = propiedadOrigen;
            this.propiedadDestino = propiedadDestino;
            this.dineroDestino = dineroDestino;
        }
    }

    public Propiedad getPropiedadOrigen() {
        return this.propiedadOrigen;
    }

    public Propiedad getPropiedadDestino() {
        return propiedadDestino;
    }

    public double getDineroDestino() {
        return this.dineroDestino;
    }

    //Los setters no se desean añadir

    public String textoDisponible() {
        String texto;
        texto = "{\n\tid: " + super.getId() + ",\n\t" +
                "proponeA: " + getJugadorDestino().getNombre() + ",\n\t" +
                "trato: cambiar (" + propiedadOrigen.getNombreSinEspacio() + ", (" + propiedadDestino.getNombreSinEspacio() + ", " + dineroDestino + "€) )\n}";
        return texto;
    }

    public String toString() {
        String texto;
        texto = "{\n\tid: " + super.getId() + ",\n\t" +
                "jugadorPropone: " + getJugadorOrigen().getNombre() + ",\n\t" +
                "trato: cambiar (" + propiedadOrigen.getNombreSinEspacio() + ", (" + propiedadDestino.getNombreSinEspacio() + ", " + dineroDestino + "€) )\n}";
        return texto;
    }
}
