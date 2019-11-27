public final class Transportes extends Propiedad {

    public Transportes(String nombre, int posicion, double valor) {
        super(nombre, posicion, valor);
    }

    public double getValorAlquiler() {
        return super.getAlquiler() * 0.25 * super.cuantasCasillasTiene();
    }

    @Override
    public String toString() {
        String texto;
        String banca;
        if (super.getDuenho() == null)
            banca = "banca";
        else
            banca = super.getDuenho().getNombre();

        texto = "{\n\ttipo: Transportes,\n\tgrupo: " + this.getGrupo().getNumeroGrupo() + ",\n\tpropietario: " + banca +
                ",\n\tvalor: " + super.getValor() + " €,\n\talquiler: " + this.getAlquiler() + " €.\n}";
        return texto;
    }
}
