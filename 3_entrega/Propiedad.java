public abstract class Propiedad extends Casilla {
    private double valor;
    private Jugador duenho;
    private Jugador duenhoAnterior;
    private boolean esHipotecado;

    public Propiedad() {
        this.valor = 0;
        this.duenho = null;
        this.duenhoAnterior = null;
        this.esHipotecado = false;
    }

    public Propiedad(double valor) {
        if (valor >= 0) {
            this.valor = valor;
        } else {
            this.valor = 0;
        }
        this.duenho = null;
        this.duenhoAnterior = null;
        this.esHipotecado = false;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor >= 0) {
            this.valor = valor;
        }
    }

    public Jugador getDuenho() {
        return this.duenho;
    }

    public void setDuenho(Jugador duenho) {
        if (duenho != null) {
            this.duenho = duenho;
        }
    }

    public Jugador getDuenhoAnterior() {
        return this.duenhoAnterior;
    }

    public void setDuenho(Jugador duenhoAnterior) {
        if (duenhoAnterior != null) {
            this.duenhoAnterior = duenhoAnterior;
        }
    }

    public boolean isEsHipotecado() {
        return this.esHipotecado;
    }

    public void setEsHipotecado(boolean esHipotecado) {
        this.esHipotecado = esHipotecado;
    }

    public void comprarCasilla(Jugador jugador, Taboleiro taboleiro) {
        if (jugador != null && taboleiro != null) {
            if (this.duenho == null) {
                if (!this.esHipotecado) {
                    if (jugador.getFortuna() >= this.valor) {
                        jugador.restarFortuna((float) this.valor);
                        jugador.setDineroGastado((float) (jugador.getDineroGastado() + this.valor));
                        this.duenho = jugador;
                        jugador.getPropiedades().add(this);
                        jugador.sumarDineroInvertido((float) this.valor);
                        jugador.sumarPremiosInversionesBote((float) this.valor);
                        System.out.println("El jugador " + jugador.getNombre() + " compra la casilla " + super.getNombre()
                                + " por " + this.valor + "€. Su fortuna actual es: " + jugador.getFortuna() + "€.");
                        taboleiro.getCasillasEnVenta().remove(this);
                        taboleiro.setContadorVueltas(0);
                    } else {
                        System.out.println("No tienes suficiente dinero para comprar esta casilla.");
                    }
                } else {
                    if (jugador.equals(this.duenhoAnterior)) {
                        System.out.println("Esta casilla está hipotecada. Si quieres hacerte con ella debes deshipotecarla.");
                    } else {
                        System.out.println("Esta casilla está hipotecada. Para poder comprarla debes ser su dueño anterior.");
                    }
                }
            } else {
                System.out.println("No puedes comprar esta casilla porque ya tiene dueño.");
            }
        }
    }

    public boolean perteneceAJugador(Jugador jugador){
        if (jugador.equals(this.duenho)){
            return true;
        }
        return false;
    }

    public abstract double getAlquiler();

    public abstract String toString();


}
