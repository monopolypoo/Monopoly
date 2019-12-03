public final class Pista extends Edificio{

    public Pista(){
        super();
    }

    public Pista(double precio, String id, Solar solar){
        super(precio, id, solar);
        solar.setNumeroPistas(solar.getNumeroPistas() + 1);
        solar.getDuenho().restarFortuna((float) precio);
        solar.getGrupo().setNumeroPistas(solar.getGrupo().getNumeroPistas() + 1);
        solar.getDuenho().añadirEdificacion(this);
        solar.añadirPistas(this);
        solar.getDuenho().sumarPremiosInversionesBote((float) precio);
        solar.getDuenho().sumarDineroInvertido((float) precio);
    }
}