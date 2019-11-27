public final class Servicio extends Propiedad {
    private final double baseAlquiler;

    public Servicio() {
        super();
        this.baseAlquiler = (Valor.VUELTA / 200.0);
    }

    public Servicio(String nombre, int posicion, double valor) {
        super(nombre, posicion, valor);
        this.baseAlquiler = (Valor.VUELTA / 200.0);
    }

    public double getBaseAlquiler(){
        return this.baseAlquiler;
    }

    //el setBaseAlquiler() no se implementa ya que se trata de una constante

    public double getValorAlquiler() {
        double valor = 0;
        if (super.cuantasCasillasTiene() == 1){
            valor = 4 * this.baseAlquiler;
        } else if (super.cuantasCasillasTiene() == 2){
            valor = 10 * this.baseAlquiler;
        } else {
            valor = 0;
        }
        return valor;
    }

    @Override
    public String toString() {
        String texto;
        String banca;
        if (super.getDuenho() == null) {
            banca = "banca";
        } else {
            banca = super.getDuenho().getNombre();
        }
        texto = "{\n\ttipo: servicios" + ",\n\tgrupo: " + super.getGrupo().getNumeroGrupo() + ",\n\tpropietario: " + banca +
                ",\n\tvalor: " + super.getValor() + " €,\n\talquiler: (" + getValorAlquiler() + " * nºDados) €.\n}";

        return texto;
    }

}
