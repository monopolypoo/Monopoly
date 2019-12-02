import java.util.ArrayList;

public final class Coche extends Avatar {
    private boolean lanzarDadosCoche;
    private boolean compraCoche;
    private boolean isSubirPenalizacion;
    private int contadorCoche;
    private int penalizacion;

    public Coche() {
        super();
        this.lanzarDadosCoche = false;
        this.compraCoche = false;
        this.isSubirPenalizacion = false;
        this.contadorCoche = 0;
        this.penalizacion = 0;
    }

    public Coche(Jugador jugador, ArrayList<Jugador> jugadores, Casilla casilla) {
        super(jugador, jugadores, casilla);
        this.penalizacion = 5; // valor mayor que 2
        this.lanzarDadosCoche = false;
        this.compraCoche = false;
        this.isSubirPenalizacion = false;
        this.contadorCoche = 0;
    }

    public boolean isLanzarDadosCoche() {
        return lanzarDadosCoche;
    }

    public void setLanzarDadosCoche(boolean lanzarDadosCoche) {
        this.lanzarDadosCoche = lanzarDadosCoche;
    }

    public boolean isCompraCoche() {
        return compraCoche;
    }

    public void setCompraCoche(boolean compraCoche) {
        this.compraCoche = compraCoche;
    }

    public boolean isSubirPenalizacion() {
        return isSubirPenalizacion;
    }

    public void setSubirPenalizacion(boolean subirPenalizacion) {
        isSubirPenalizacion = subirPenalizacion;
    }

    public int getContadorCoche() {
        return contadorCoche;
    }

    public void sumarContadorCoche() {
        ++this.contadorCoche;
    }

    public void setContadorCoche(int contadorCoche) {
        if (contadorCoche == 0) {
            this.contadorCoche = 0;
        }
    }

    public int getPenalizacion() {
        return penalizacion;
    }

    public void sumarPenalizacion() {
        ++this.penalizacion;
    }

    public void setPenalizacion(int penalizacion) {
        if (penalizacion == 0)
            this.penalizacion = penalizacion;
    }

    public void sumarLanzardados(Menu menu) { //MIRAR ESTO, PORQUE CAMBIA EL NOMBRE A JUEGO
        if (this.contadorCoche < 3 && this.lanzarDadosCoche) {
            menu.setDadosLanzados(false);
            menu.setSigueTurno(true);
            System.out.println("El jugador " + menu.getJugadorActual().getNombre() + " mantiene el turno.");

            if (menu.getJugadorActual().getEstarCarcere()) {
                System.out.println("Estás en la cárcel, por lo que debes tirar los dados para obtener dobles.");
            }
            this.sumarContadorCoche();
        } else {
            System.out.println("Ya tiraste los dados el número máximo en tu modo avanzado");
            menu.setDadosLanzados(true);
            menu.setSigueTurno(false);
            this.lanzarDadosCoche = false;
            this.contadorCoche = 0;
        }
    }

    @Override
    public void moverEnAvanzado(Taboleiro taboleiro, Menu menu, Dado dado) {
        int posSiguiente, posActual = this.getCasilla().getPosicion();
        if (this.contadorCoche <= 3 && dado.getDadoTotal() > 4) {
            posSiguiente = posActual + dado.getDadoTotal();
            moverEnBasico(taboleiro, menu, posSiguiente);
            this.setLanzarDadosCoche(true);
        } else if (dado.getDadoTotal() <= 4) {
            posSiguiente = posActual - dado.getDadoTotal();
            if (posSiguiente < 0) {
                posSiguiente += 39;
            }
            moverEnBasico(taboleiro, menu, posSiguiente);
            this.setSubirPenalizacion(true);
            this.setContadorCoche(0);
            this.setCompraCoche(false);
            this.setPenalizacion(0);
            this.setLanzarDadosCoche(false);
            menu.setDadosLanzados(true);
            super.setTextoAvanzado("\nAcabas de obtener un valor en los dados igual o inferior a 4, por lo que tu turno se " +
                    "acaba y sufres la penalización de estar dos turnos sin poder lanzar dados.");
        }
    }

    @Override
    public String toString() {
        String texto = "{\n" +
                "\tid: " + super.getId() + "\n" +
                "\ttipo: coche" + "\n" +
                "\tcasilla: " + super.getCasilla().getNombre() + "\n" +
                "\tjugador: " + super.getJugador().getNombre() + "\n}";

        return texto;
    }
}
