public final class Piscina extends Edificio{

    public Piscina(){
        super();
    }

    public Piscina(double precio, String id, Solar solar){
        super(precio, id, solar);
        solar.setNumeroPiscinas(solar.getNumeroPiscinas() + 1);
        solar.getDuenho().restarFortuna((float) precio);
        solar.getGrupo().setNumeroPiscinas(solar.getGrupo().getNumeroPiscinas() + 1);
        solar.getDuenho().añadirEdificacion(this);
        solar.añadirPiscina(this);
        solar.getDuenho().sumarPremiosInversionesBote((float) precio);
        solar.getDuenho().sumarDineroInvertido((float) precio);
    }
}
