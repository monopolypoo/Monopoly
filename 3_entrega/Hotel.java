public final class Hotel extends Edificio{

    public Hotel(){
        super();
    }

    public Hotel(double precio, String id, Solar solar){
        super(precio, id, solar);
        solar.setNumeroCasas(0);
        solar.setNumeroHoteles(solar.getNumeroHoteles() + 1);
        solar.getDuenho().restarFortuna((float) precio);
        solar.getGrupo().setNumeroHoteles(solar.getGrupo().getNumeroHoteles() + 1);
        solar.getDuenho().añadirEdificacion(this);
        solar.añadirHotel(this);
        solar.getDuenho().sumarPremiosInversionesBote((float) precio);
        solar.getDuenho().sumarDineroInvertido((float) precio);
    }
}
