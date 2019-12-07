package Trato;

import Casilla.Propiedad;
import Jugador.Jugador;

public final class Trato6 extends Tratos {
    private Propiedad propiedadOrigen;
    private Propiedad propiedadDestino;
    private Propiedad propiedadAlquiler;
    private int turnos;

    public Trato6() {
        super();
    }

    public Trato6(String id, Jugador jugadorOrigen, Jugador jugadorDestino, Propiedad propiedadOrigen, Propiedad propiedadDestino, Propiedad propiedadAlquiler, int turnos) {
        super(id, jugadorOrigen, jugadorDestino);
        if ((propiedadOrigen != null) && (propiedadDestino != null) && (propiedadAlquiler != null) && (turnos >= 0)) {
            this.propiedadOrigen = propiedadOrigen;
            this.propiedadDestino = propiedadDestino;
            this.propiedadAlquiler = propiedadAlquiler;
            this.turnos = turnos;
        }
    }

    public Propiedad getPropiedadOrigen() {
        return this.propiedadOrigen;
    }

    public Propiedad getPropiedadDestino() {
        return propiedadDestino;
    }

    public Propiedad getPropiedadAlquiler() {
        return this.propiedadAlquiler;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        if (turnos >= 0) {
            this.turnos = turnos;
        }
    }

    //Los setters no se desean añadir

    public String textoDisponible() {
        String texto;
        texto = "{\n\tid: " + super.getId() + ",\n\t" +
                "proponeA: " + getJugadorDestino().getNombre() + ",\n\t" +
                "trato: cambiar (" + propiedadOrigen.getNombreSinEspacio() + ", " + propiedadDestino.getNombreSinEspacio() + ") " +
                "y noAlquiler(" + propiedadAlquiler.getNombreSinEspacio() + ", " + turnos + ")\n}";
        return texto;
    }

    public String toString() {
        String texto;
        texto = "{\n\tid: " + super.getId() + ",\n\t" +
                "jugadorPropone: " + getJugadorOrigen().getNombre() + ",\n\t" +
                "trato: cambiar (" + propiedadOrigen.getNombreSinEspacio() + ", " + propiedadDestino.getNombreSinEspacio() + ") " +
                "y noAlquiler(" + propiedadAlquiler.getNombreSinEspacio() + ", " + turnos + ")\n}";
        return texto;
    }
}
