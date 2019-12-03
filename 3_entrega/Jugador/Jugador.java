package Jugador;

import Casilla.*;
import Edificio.*;
import Juego_fisico.*;
import Monopoly.*;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private float fortuna;
    private float dineroGastado;
    private Avatar avatar;
    private ArrayList<Casilla> propiedades;
    private ArrayList<Edificio> edificaciones;
    private boolean estarCarcere;
    private int contadorEstarCarcere;
    private float dineroInvertido;
    private float tasasImpuestos;
    private float pagoAlquileres;
    private float cobroAlquileres;
    private int vecesSalida;
    private float premiosInversionesBote;
    private int vecesDados;
    private int vecesCarcel;

    public Jugador() {
        this.nombre = "banca";
        this.fortuna = 1000000000;
        this.dineroGastado = 0;
        this.avatar = new Coche();
        this.propiedades = new ArrayList<>(); //tambien se le pueden poner inicialmente todas las propiedades a la banca
        this.edificaciones = new ArrayList<>();
        this.estarCarcere = false;
        this.contadorEstarCarcere = 0;
        this.dineroInvertido = 0;
        this.tasasImpuestos = 0;
        this.pagoAlquileres = 0;
        this.cobroAlquileres = 0;
        this.vecesSalida = 0;
        this.premiosInversionesBote = 0;
        this.vecesDados = 0;
        this.vecesCarcel = 0;
    }

    public Jugador(String nombre, String tipo_avatar, ArrayList<Jugador> jugadores, Casilla casilla) {
        if (nombre != null) {
            this.nombre = nombre;
        } else {
            this.nombre = "Jugador_random";
        }
        this.fortuna = Valor.DINERO_INICIAL;
        this.dineroGastado = 0;
        this.propiedades = new ArrayList<>();
        this.edificaciones = new ArrayList<>();
        this.estarCarcere = false;
        this.contadorEstarCarcere = 0;
        this.dineroInvertido = 0;
        this.tasasImpuestos = 0;
        this.pagoAlquileres = 0;
        this.cobroAlquileres = 0;
        this.vecesSalida = 0;
        this.premiosInversionesBote = 0;
        this.vecesDados = 0;
        this.vecesCarcel = 0;
        if (tipo_avatar != null) {
            switch (tipo_avatar.toLowerCase()) {
                case "coche":
                    this.avatar = new Coche(this, jugadores, casilla);
                    break;
                case "pelota":
                    this.avatar = new Pelota(this, jugadores, casilla);
                    break;
                case "esfinge":
                    this.avatar = new Esfinge(this, jugadores, casilla);
                    break;
                case "sombrero":
                    this.avatar = new Sombrero(this, jugadores, casilla);
                    break;
                default:
                    this.avatar = new Coche(this, jugadores, casilla);
                    break;
            }
        } else {
            this.avatar = new Coche(this, jugadores, casilla);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null) {
            this.nombre = nombre;
        } else {
            this.nombre = "jugador_random";
        }
    }

    public int getVecesDados() {
        return vecesDados;
    }

    public void sumarVecesdados() {
        this.vecesDados++;
    }

    public float getDineroInvertido() {
        return dineroInvertido;
    }

    public void sumarDineroInvertido(float dineroInvertido) {
        if (dineroInvertido > 0) {
            this.dineroInvertido += dineroInvertido;
        }
    }

    public int getVecesCarcel() {
        return vecesCarcel;
    }

    public void sumarVecesCarcel() {
        this.vecesCarcel++;
    }

    public float getPagoAlquileres() {
        return pagoAlquileres;
    }

    public void sumarPagoAlquileres(float pagoAlquileres) {
        if (pagoAlquileres > 0) {
            this.pagoAlquileres += pagoAlquileres;
        }
    }

    public float getCobroAlquileres() {
        return cobroAlquileres;
    }

    public void sumarCobroAlquileres(float cobroAlquileres) {
        if (cobroAlquileres > 0) {
            this.cobroAlquileres += cobroAlquileres;
        }
    }

    public float getTasasImpuestos() {
        return tasasImpuestos;
    }

    public void sumarTasasImpuestos(float tasasImpuestos) {
        if (tasasImpuestos > 0) {
            this.tasasImpuestos += tasasImpuestos;
        }
    }

    public int getVecesSalida() {
        return vecesSalida;
    }

    public void sumarVecesSalida() {
        this.vecesSalida++;
    }

    public float getPremiosInversionesBote() {
        return premiosInversionesBote;
    }

    public void sumarPremiosInversionesBote(float premiosInversionesBote) {
        if (premiosInversionesBote > 0) {
            this.premiosInversionesBote += premiosInversionesBote;
        }
    }

    public Avatar getAvatar() {
        if (this.avatar == null) {
            this.avatar = new Coche(); //coche porque es el que usamos por defecto
        }
        return this.avatar;
    }
    //No definimos un setAvatar porque no permitimos cambiar de avatar una vez asignado

    public float getDineroGastado() {
        return dineroGastado;
    }

    public void setDineroGastado(float dineroGastado) {
        if (dineroGastado > 0) {
            this.dineroGastado = dineroGastado;
        }
    }

    public float getFortuna() {
        return fortuna;
    }

    public void setFortuna(float fortuna) {
        if (fortuna > 0) {
            this.fortuna = fortuna;
        }
    }

    public boolean getEstarCarcere() {
        return estarCarcere;
    }

    public void setEstarCarcere(boolean estarCarcere) {
        this.estarCarcere = estarCarcere;
    }

    public void setContadorEstarCarcere(int opcion) {
        if (opcion == 1) {
            this.contadorEstarCarcere++;
            if (this.contadorEstarCarcere >= 3) {
                this.estarCarcere = false;
            }
        } else if (opcion == 0) {
            this.contadorEstarCarcere = 0;
            this.estarCarcere = false;
        }
    }

    public int getContadorEstarCarcere() {
        return this.contadorEstarCarcere;
    }

    public void sumarFortuna(float fortuna) {
        if (fortuna > 0) {
            this.fortuna = this.fortuna + fortuna;
        }
    }

    public void restarFortuna(float fortuna) {
        if (fortuna > 0) {
            this.fortuna = this.fortuna - fortuna;
        }
    }

    public ArrayList<Casilla> getPropiedades() {
        return (propiedades);
    }

    public void setPropiedades(ArrayList<Casilla> propiedades) {
        if (propiedades == null) {
            System.err.println("Error: propiedades no inicializadas.");
            return;
        }
        for (Casilla casilla : propiedades) {
            if (casilla == null) {
                System.err.println("Error: casilla no inicializada.");
                return;
            }
        }
        this.propiedades = propiedades;
    }


    public ArrayList<Edificio> getEdificaciones() {
        return this.edificaciones;
    }

    public void añadirEdificacion(Edificio edificio) {
        if (edificio != null) {
            this.edificaciones.add(edificio);
        }
    }

    public void eliminarEdificacion(Edificio edificio) {
        if (edificio != null) {
            this.edificaciones.remove(edificio);
        }
    }

    public void pagarAlquiler(Casilla casilla, int dadoTotal) {
        int contador;
        float deuda;
        if (casilla instanceof Propiedad) {
            Propiedad propiedad = (Propiedad) casilla;
            if (propiedad.getDuenho() != null) {
                if (!propiedad.getDuenho().equals(this)) {
                    if (!propiedad.hayAlgunaHipoteca()) {
                        if (propiedad instanceof Servicio) {
                            if (propiedad.tenerTodasCasillas()) {
                                deuda = (float) (10 * dadoTotal * (Valor.VUELTA / 200));
                                this.restarFortuna(deuda);
                                this.dineroGastado += deuda;
                                this.sumarPagoAlquileres(deuda);
                                propiedad.getDuenho().sumarCobroAlquileres(deuda);
                                propiedad.getDuenho().sumarFortuna(deuda);
                                System.out.println("Caiste en una casilla de servicios que pertenece al avatar " + propiedad.getDuenho().getAvatar().getId()
                                        + ", por lo que se le pagó un alquiler de " + deuda + "€.");
                            } else {
                                deuda = (float) (4 * dadoTotal * (Valor.VUELTA / 200));
                                this.restarFortuna(deuda);
                                this.dineroGastado += deuda;
                                propiedad.getDuenho().sumarFortuna(deuda);
                                System.out.println("Caiste en una casilla de servicios que pertenece al avatar " + propiedad.getDuenho().getAvatar().getId()
                                        + ", por lo que se le pagó un alquiler de " + deuda + "€.");
                            }

                        } else if (propiedad instanceof Transportes) { //si es le de transportes
                            contador = propiedad.getGrupo().cuantasCasillasTiene(this);
                            deuda = (float) (contador * 0.25 * propiedad.getValorAlquiler());
                            this.restarFortuna(deuda);
                            this.dineroGastado += deuda;
                            propiedad.getDuenho().sumarFortuna(deuda);
                            System.out.println("Caiste en una casilla de transportes que pertenece al avatar " + propiedad.getDuenho().getAvatar().getId()
                                    + ". Este avatar tiene " + contador + "casillas de transportes, por lo que se le pagó un alquiler de " + deuda + "€.");

                        } else if (propiedad instanceof Solar) {
                            if (this.fortuna >= propiedad.getValorAlquiler()) {
                                if (propiedad.tenerTodasCasillas()) {
                                    this.restarFortuna((float) (2 * propiedad.getValorAlquiler()));
                                    this.dineroGastado += 2 * propiedad.getValorAlquiler();
                                    propiedad.getDuenho().sumarFortuna((float) (2 * propiedad.getValorAlquiler()));
                                    System.out.println("Caiste en una casilla que pertenece al avatar " + propiedad.getDuenho().getAvatar().getId()
                                            + ", y además todas las casillas de ese grupo le pertenecen, por lo que se le pagó el alquiler de "
                                            + 2 * propiedad.getValorAlquiler() + "€.");
                                } else {
                                    this.restarFortuna((float) propiedad.getValorAlquiler());
                                    this.dineroGastado += propiedad.getValorAlquiler();
                                    propiedad.getDuenho().sumarFortuna((float) propiedad.getValorAlquiler());
                                    System.out.println("Caiste en una casilla que pertenece al avatar " + propiedad.getDuenho().getAvatar().getId()
                                            + ", por lo que se le pagó el alquiler de " + propiedad.getValorAlquiler() + "€.");
                                }
                            } else {
                                System.out.println("No tienes dinero suficiente para pagar el alquiler, por lo que debes hipotecar tus propiedades o estarás en bancarrota.");
                            }
                        }
                    } else {
                        System.out.println("No se paga alquiler por esta casilla porque existe alguna que está hipotecada en el grupo.");
                    }
                }
            }
        }
    }

    public void pagarImpuestos(Casilla casilla, Taboleiro taboleiro) {
        if (casilla instanceof Impuesto) {
            Impuesto impuesto = (Impuesto) casilla;
            if (taboleiro != null) {
                if (this.fortuna >= impuesto.getImpuesto()) {
                    this.restarFortuna((float) impuesto.getImpuesto());
                    this.dineroGastado += impuesto.getImpuesto();
                    ((Especial) taboleiro.getCasillaPosicion(20)).sumarBote((float) impuesto.getImpuesto());
                    this.sumarTasasImpuestos((float) impuesto.getImpuesto());
                    System.out.println("Caíste en una casilla de impuestos, por lo que se realizó el pago de " + impuesto.getImpuesto() + "€.");
                } else {
                    System.out.println("No tienes dinero suficiente para pagar el impuesto, por lo que debes hipotecar tus propiedades o estarás en bancarrota.");
                }
            }
        }
    }

    public void cobrarParking(Casilla casilla) {
        if (casilla.getPosicion() == 20) {
            Especial especial = (Especial) casilla;
            this.sumarFortuna((float) especial.getValor());
            especial.setVecesCasilla(this);
            this.sumarPremiosInversionesBote((float) especial.getValor());
            System.out.println("Caíste en el Parking, por lo que cobras el total acumulado que es de: " + especial.getValor() + "€.");
            especial.vaciarBote();
        }
    }

    public void anhadirPropiedad(Casilla casilla) {
        if (casilla != null)
            this.propiedades.add(casilla);
    }

    public void irCarcere(Taboleiro taboleiro) {
        int posSig = 10;
        Casilla casillaSiguiente;

        if (taboleiro != null) {
            casillaSiguiente = taboleiro.getCasillaPosicion(posSig);
            this.getAvatar().setCasilla(casillaSiguiente);

            taboleiro.getCasillaPosicion(10).setVecesCasilla(this); //lo añadimos al historial de la carcel

            this.estarCarcere = true;
        }
    }

    public void eliminarPropiedad(Casilla casilla) {
        if (this.propiedades.contains(casilla)) {
            this.propiedades.remove(casilla);
        } else {
            System.out.println("No se le puede quitar a un jugador una propiedad que ya no le pertenecía.");
        }
    }

    @Override
    public String toString() {
        String texto;
        String prop = "";
        String edif = "";
        if (this.avatar == null) {
            texto = "{\n\tNombre: " + this.nombre + "\n}";
        } else {
            int tam = 0, i = 1;
            if ((tam = this.propiedades.size()) != 0) {
                for (Casilla propi : this.propiedades) {
                    prop += propi.getNombreSinEspacio();
                    if (i != tam) {
                        prop += ", ";
                    }
                    i++;
                }
            } else
                prop = "[ ]";
            if ((tam = this.edificaciones.size()) != 0) {
                i = 0;
                for (Edificio edificio : this.edificaciones) {
                    edif += edificio.getId();
                    if (i != tam) {
                        edif += ", ";
                    }
                    i++;
                }
            } else
                edif = "[ ]";
            texto = "{\n" +
                    "\tNombre: " + this.nombre +
                    "\n\tAvatar: " + this.avatar.getId() +
                    "\n\tFortuna: " + this.fortuna +
                    "\n\tGastos: " + this.dineroGastado +
                    "\n\tPropiedades: " + prop +
                    "\n\tEdificaciones: " + edif + "\n}";
        }
        return texto;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Jugador)
            return ((Jugador) obj).getAvatar().getId().equals(this.avatar.getId());
        return false;
    }
}