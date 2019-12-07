package Trato;

import Casilla.Propiedad;
import Jugador.Jugador;

public final class Trato1 extends Tratos {
    private Propiedad propiedadOrigen;
    private Propiedad propiedadDestino;

    public Trato1() {
        super();
    }

    public Trato1(String id, Jugador jugadorOrigen, Jugador jugadorDestino, Propiedad propiedadOrigen, Propiedad propiedadDestino) {
        super(id, jugadorOrigen, jugadorDestino);
        if ((propiedadOrigen != null) && (propiedadDestino != null)) {
            this.propiedadOrigen = propiedadOrigen;
            this.propiedadDestino = propiedadDestino;
        }
    }

    public Propiedad getPropiedadOrigen() {
        return this.propiedadOrigen;
    }

    public Propiedad getPropiedadDestino() {
        return this.propiedadDestino;
    }

    //Los setters no se desean añadir

    public String textoDisponible() {
        String texto;
        texto = "{\n\tid: " + super.getId() + ",\n\t" +
                "propuestoA: " + getJugadorDestino().getNombre() + ",\n\t" +
                "trato: cambiar (" + propiedadOrigen.getNombreSinEspacio() + ", " + propiedadDestino.getNombreSinEspacio() + ")\n}";
        return texto;
    }

    public String toString() {
        String texto;
        texto = "{\n\tid: " + super.getId() + ",\n\t" +
                "jugadorPropone: " + getJugadorOrigen().getNombre() + ",\n\t" +
                "trato: cambiar (" + propiedadOrigen.getNombreSinEspacio() + ", " + propiedadDestino.getNombreSinEspacio() + ")\n}";
        return texto;
    }
}
