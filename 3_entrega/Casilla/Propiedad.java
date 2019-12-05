package Casilla;

import Consola.*;
import ExcepcionesPartida.*;
import Juego_fisico.*;
import Jugador.*;

public abstract class Propiedad extends Casilla {
    private double valor;
    private double alquiler;
    private Jugador duenho;
    private Jugador duenhoAnterior;
    private boolean esHipotecado;
    private Consola consola;

    public Propiedad() {
        super();
        this.valor = 0;
        this.alquiler = 0;
        this.duenho = null;
        this.duenhoAnterior = null;
        this.esHipotecado = false;
        this.consola = new ConsolaNormal();
    }

    public Propiedad(String nombre, int posicion, double valor) {
        super(nombre, posicion);
        if (valor >= 0) {
            this.valor = valor;
            this.alquiler = 0.1 * this.valor;
        } else {
            this.valor = 0;
            this.alquiler = 0;
        }
        this.duenho = null;
        this.duenhoAnterior = null;
        this.esHipotecado = false;
        this.consola = new ConsolaNormal();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor >= 0)
            this.valor = valor;
    }

    public double getAlquiler() {
        return this.alquiler;
    }

    public void setAlquiler(double alquiler) {
        this.alquiler = alquiler;
    }

    public Jugador getDuenho() {
        return this.duenho;
    }

    public void setDuenho(Jugador duenho) {
        if (duenho != null)
            this.duenho = duenho;
    }

    public Jugador getDuenhoAnterior() {
        return this.duenhoAnterior;
    }

    public void setDuenhoAnterior(Jugador duenhoAnterior) {
        if (duenhoAnterior != null)
            this.duenhoAnterior = duenhoAnterior;
    }

    public boolean isEsHipotecado() {
        return this.esHipotecado;
    }

    public void setEsHipotecado(boolean esHipotecado) {
        this.esHipotecado = esHipotecado;
    }

    public void comprarCasilla(Jugador jugador, Taboleiro taboleiro) throws ExcepcionesDinero, ExcepcionesDuenho, ExcepcionesHipotecar {
        if (jugador != null && taboleiro != null) {
            if (this.duenho == null) {
                if (!this.esHipotecado) {
                    jugador.restarFortuna((float) this.valor);
                    jugador.setDineroGastado((float) (jugador.getDineroGastado() + this.valor));
                    this.duenho = jugador;
                    jugador.getPropiedades().add(this);
                    jugador.sumarDineroInvertido((float) this.valor);
                    jugador.sumarPremiosInversionesBote((float) this.valor);
                    consola.imprimir("El jugador " + jugador.getNombre() + " compra la casilla " + super.getNombre()
                            + " por " + this.valor + "€. Su fortuna actual es: " + jugador.getFortuna() + "€.");
                    //taboleiro.getCasillasEnVenta().remove(this);
                    taboleiro.setContadorVueltas(0);
                } else
                    throw new ExcepcionesHipotecar(super.getNombreSinEspacio(), " no se puede comprar porque está hipotecada.");
            } else throw new ExcepcionesDuenho("No puedes comprar esta casilla porque ya tiene dueño.");
        }
    }

    public boolean perteneceAJugador(Jugador jugador) {
        if (jugador != null) {
            return jugador.equals(this.duenho);
        }
        return false;
    }

    public boolean tenerTodasCasillas() {
        Propiedad propiedad;
        if (this.duenho == null)
            return false;
        for (Casilla casilla : super.getGrupo().getCasillas()) {
            propiedad = (Propiedad) casilla;
            if (!this.duenho.equals(propiedad.getDuenho()))
                return false;
        }
        return true;
    }

    public int cuantasCasillasTiene() {
        int contador = 0;
        if (this.getDuenho() != null)
            for (Casilla cas : super.getGrupo().getCasillas())
                if (this.getDuenho().equals(((Servicio) cas).getDuenho()))
                    contador++;
        return contador;
    }

    public void hipotecarCasilla(Jugador jugador, Taboleiro taboleiro) throws ExcepcionesHipotecar, ExcepcionesDuenho, ExcepcionesEdificios {
        if (this.duenho != null) {
            if (this.duenho.equals(jugador)) {
                if (!this.esHipotecado) {
                    if (this instanceof Solar)
                        if (!((Solar) this).hayEdificios()) {
                            this.esHipotecado = true;
                            jugador.eliminarPropiedad(this);
                            this.duenho = null;
                            this.duenhoAnterior = jugador;
                            jugador.sumarFortuna((float) this.valor / 2);
                            consola.imprimir(jugador.getNombre() + " recibe " + this.valor / 2 + "€ por la hipoteca de " + this.getNombreSinEspacio() +
                                    ". No puede recibir alquileres ni edificar en el grupo " + super.getGrupo().getNumeroGrupo() + ".");
                        } else
                            throw new ExcepcionesEdificios("No puedes hipotecar la casilla porque existen edificios en ella, antes de hipotecarla debes venderlos todos.");
                } else
                    throw new ExcepcionesHipotecar(super.getNombreSinEspacio(), " no se puede hipotecar porque ya está hipotecada.");
            } else throw new ExcepcionesDuenho("No puedes hipotecar esta casilla porque no eres su dueño.");
        } else throw new ExcepcionesDuenho("No puedes hipotecar esta casilla porque no eres el dueño.");
    }

    public void deshipotecarCasilla(Jugador jugador, Taboleiro taboleiro) throws ExcepcionesDinero, ExcepcionesHipotecar, ExcepcionesDuenho, ExcepcionesDeshipotecar {
        if (this.duenhoAnterior != null) {
            if (this.duenho == null) {
                if (this.duenhoAnterior.equals(jugador)) {
                    if (this.esHipotecado) {
                        this.esHipotecado = false;
                        jugador.anhadirPropiedad(this);
                        this.duenho = jugador;
                        this.duenhoAnterior = null;
                        jugador.restarFortuna((float) this.valor / 2);
                        consola.imprimir(jugador.getNombre() + " paga " + this.valor / 2 + "€ por deshipotecar " + this.getNombreSinEspacio() +
                                ". Ahora puede recibir alquileres y edificar en el grupo " + super.getGrupo().getNumeroGrupo() + ".");

                    } else
                        throw new ExcepcionesHipotecar(super.getNombreSinEspacio(), " no se puede deshipotecar porque ya lo está.");
                } else
                    throw new ExcepcionesDuenho("No puedes deshipotecar esta casilla porque no era tuya antes de que fuese hipotecada.");
            } else throw new ExcepcionesDuenho("No puedes hipotecar esta casilla porque no eres su dueño.");
        } else throw new ExcepcionesDeshipotecar("Esta casilla no se puede deshipotecar porque no está hipotecada.");
    }

    public boolean hayAlgunaHipoteca() {
        for (Casilla casilla : super.getGrupo().getCasillas()) {
            if (casilla instanceof Propiedad) {
                if (((Propiedad) casilla).isEsHipotecado()) {
                    return true;
                }
            }
        }
        return false;
    }

    public abstract double getValorAlquiler();

    public abstract String toString();


}

