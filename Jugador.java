import java.util.ArrayList;
import java.util.HashMap;

public class Jugador {
    private String nombre;
    private float fortuna;
    private float dineroGastado;
    private Avatar avatar;
    private ArrayList<Casilla> propiedades;
    private boolean estarCarcere;
    private int contadorEstarCarcere;

    public Jugador() {
        this.nombre = "banca";
        this.fortuna = 1000000000;
        this.dineroGastado = 0;
        this.avatar = null;
        this.propiedades = new ArrayList<>(); //tambien se le pueden poner inicialmente todas las propiedades a la banca
        this.estarCarcere = false;
        this.contadorEstarCarcere = 0;
    }

    public Jugador(String nombre, String tipo_avatar,  ArrayList<Jugador> jugadores, Casilla casilla){
        this.nombre = nombre;
        this.fortuna = Valor.DINERO_INICIAL;
        this.dineroGastado = 0;
        Avatar avatar = new Avatar(tipo_avatar, this, jugadores, casilla);
        this.avatar = avatar;
        this.propiedades = new ArrayList<>();
        this.estarCarcere = false;
        this.contadorEstarCarcere = 0;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Avatar getAvatar() {
        return avatar;
    }
    //No definimos un setAvatar porque no permitimos cambiar de avatar una vez asignado

    public float getDineroGastado() {
        return dineroGastado;
    }
    public void setDineroGastado(float dineroGastado) {
        this.dineroGastado = dineroGastado;
    }

    public float getFortuna() {
        return fortuna;
    }
    public void setFortuna(float fortuna) {
        this.fortuna = fortuna;
    }

    public boolean getEstarCarcere() {
        return estarCarcere;
    }

    public void setEstarCarcere(boolean estarCarcere){
        this.estarCarcere = estarCarcere;
    }

    public void setContadorEstarCarcere(int opcion){
        if (opcion == 1){
            this.contadorEstarCarcere++;
            if (this.contadorEstarCarcere >= 3){
                this.estarCarcere = false;
            }
        }
        else if (opcion == 0){
            this.contadorEstarCarcere = 0;
            this.estarCarcere = false;
        }
    }

    public int getContadorEstarCarcere(){
        return this.contadorEstarCarcere;
    }

    public void sumarFortuna(float fortuna){
        this.fortuna = this.fortuna + fortuna;
    }

    public void restarFortuna(float fortuna){
        this.fortuna = this.fortuna - fortuna;
    }

    public ArrayList<Casilla> getPropiedades(){
        return (propiedades);
    }
    public void setPropiedades(ArrayList<Casilla> propiedades){
        if (propiedades == null){
            System.err.println("Error: propiedades no inicializadas.");
            return;
        }
        for (Casilla casilla: propiedades){
            if (casilla == null){
                System.err.println("Error: casilla no inicializada.");
                return;
            }
        }
        this.propiedades = propiedades;
    }

    public void pagarAlquiler(Casilla casilla, int dadoTotal, Taboleiro taboleiro){
        int deuda;
        if(casilla.getDuenho() != null){
            if(!casilla.getDuenho().getNombre().equals(this.getNombre())){
                if ((casilla.getPosicion() == 12) || (casilla.getPosicion() == 28)){
                    if ((taboleiro.getCasillaPosicion(12).getDuenho() != null) && (taboleiro.getCasillaPosicion(28).getDuenho() != null) &&
                            (taboleiro.getCasillaPosicion(12).getDuenho().equals(taboleiro.getCasillaPosicion(28).getDuenho()))){
                        deuda = (10 * dadoTotal * (Valor.VUELTA / 200));
                        this.restarFortuna((float) deuda);
                        this.dineroGastado += deuda;
                        casilla.getDuenho().sumarFortuna((float) deuda);
                        System.out.println("Caiste en una casilla de servicios que pertenece al avatar " + casilla.getDuenho().getAvatar().getId()
                                + ", por lo que se le pagó un alquiler de " + deuda + "€.");
                    }
                    else{
                        deuda = (4 * dadoTotal * (Valor.VUELTA / 200));
                        this.restarFortuna((float) deuda);
                        this.dineroGastado += deuda;
                        casilla.getDuenho().sumarFortuna((float) deuda);
                        System.out.println("Caiste en una casilla de servicios que pertenece al avatar " + casilla.getDuenho().getAvatar().getId()
                                + ", por lo que se le pagó un alquiler de " + deuda + "€.");
                    }
                }
                else {
                    if (this.fortuna >= casilla.getValorAlquiler()) {
                        this.restarFortuna((float) casilla.getValorAlquiler());
                        this.dineroGastado += casilla.getValorAlquiler();
                        casilla.getDuenho().sumarFortuna((float) casilla.getValorAlquiler());
                        System.out.println("Caiste en una casilla que pertenece al avatar " + casilla.getDuenho().getAvatar().getId()
                                + ", por lo que se le pagó el alquiler de " + casilla.getValorAlquiler() + "€.");
                    } else {
                        System.out.println("No tienes dinero suficiente para pagar el alquiler, por lo que estás en bancarrota.");
                    }
                }
            }
        }
    }

    public void pagarImpuestos(Casilla casilla, Taboleiro taboleiro){
        if ((casilla.getPosicion() == 4) || (casilla.getPosicion() == 38)){
            if (this.fortuna >= casilla.getValor()) {
                this.restarFortuna((float) casilla.getValor());
                this.dineroGastado += casilla.getValor();
                taboleiro.getCasillaPosicion(20).sumarValor((float) casilla.getValor());
                System.out.println("Caíste en una casilla de impuestos, por lo que se realizó el pago de " + casilla.getValor() + "€.");
            }
            else{
                System.out.println("No tienes dinero suficiente para pagar el imposto, por lo que estás en bancarrota.");
            }
        }
    }

    public void cobrarParking(Casilla casilla){
        if (casilla.getPosicion() == 20){
            this.sumarFortuna((float) casilla.getValor());
            System.out.println("Caíste en el Parking, por lo que cobras el total acumulado que es de: " + casilla.getValor() + "€.");
            casilla.setValor(0);
        }
    }

    public void anhadirPropiedad(Casilla casilla){
        if (casilla != null)
            this.propiedades.add(casilla);
    }

    public void irCarcere(Taboleiro taboleiro){
        int posSig = 10;
        Casilla casillaSiguiente;

        casillaSiguiente = taboleiro.getCasillaPosicion(posSig);
        this.getAvatar().setCasilla(casillaSiguiente);

        taboleiro.getCasillaPosicion(10).setVecesCasilla(this); //lo añadimos al historial de la carcel

        this.estarCarcere = true;
    }

    public void comprarCasilla(Casilla casilla, Taboleiro taboleiro){
        if (taboleiro.sePuedeComprar(casilla)) {
            if (casilla.getDuenho() == null) {
                if (this.fortuna >= casilla.getValor()) {
                    restarFortuna((float) casilla.getValor());
                    this.dineroGastado += casilla.getValor();
                    casilla.setDuenho(this);
                    this.propiedades.add(casilla);
                    System.out.println("El jugador " + this.nombre + " compra la casilla " + casilla.getNombreSinEspacio()
                            + " por " + casilla.getValor() + "€. Su fortuna actual es: " + this.fortuna + "€.");
                    taboleiro.getCasillasEnVenta().remove(casilla);
                } else {
                    System.out.println("No tienes suficiente dinero para comprar esta casilla.");
                }
            }
            else{
                System.out.println("No puedes comprar esta casilla porque ya tiene dueño.");
            }
        }
        else{
            System.out.println("Esta casilla no se puede comprar.");
        }
    }

    @Override
    public String toString(){
        String texto;
        String prop = "";
        if (this.avatar == null){
            texto = "{\n\tNombre: " + this.nombre + "\n}";
        }
        else{
            int tam = 0, i = 1;
            if ((tam = propiedades.size()) != 0){
                for (Casilla propi : propiedades){
                    prop += propi.getNombreSinEspacio();
                    if (i != tam){
                        prop += ", ";
                    }
                    i++;
                }
            }
            texto = "{\n" +
                    "\tNombre: " + this.nombre +
                    "\n\tAvatar: " + this.avatar.getId() +
                    "\n\tFortuna: " + this.fortuna +
                    "\n\tGastos: " + this.dineroGastado +
                    "\n\tPropiedades: " + prop + "\n}";
        }
        return texto;
    }
}

