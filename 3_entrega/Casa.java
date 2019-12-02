public final class Casa extends Edificio {

    public Casa(){
        super();
    }

    public Casa(double precio, String id, Solar solar){
        super(precio, id, solar);
        solar.getDuenho().restarFortuna((float) precio);
        solar.getDuenho().añadirEdificacion(this);
        solar.añadirCasa(this);
        solar.getDuenho().sumarDineroInvertido((float) precio);
        solar.getDuenho().sumarPremiosInversionesBote((float) precio);
        solar.setNumeroCasas(solar.getNumeroCasas() + 1);
    }
}
