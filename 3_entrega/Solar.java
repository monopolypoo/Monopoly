import java.util.ArrayList;

public final class Solar extends Propiedad {
    private double valorCasa;
    private double valorPiscina;
    private double valorPistaDeporte;
    private double valorHotel;
    private ArrayList<Casa> casas;
    private ArrayList<Hotel> hoteles;
    private ArrayList<Piscina> piscinas;
    private ArrayList<Pista> pistas;
    private int numeroCasas;
    private int numeroHoteles;
    private int numeroPiscinas;
    private int numeroPistas;

    public Solar() {
        super();
        this.valorCasa = 0;
        this.valorPiscina = 0;
        this.valorPistaDeporte = 0;
        this.valorHotel = 0;
        this.casas = new ArrayList<>();
        this.hoteles = new ArrayList<>();
        this.piscinas = new ArrayList<>();
        this.pistas = new ArrayList<>();
        this.numeroCasas = 0;
        this.numeroHoteles = 0;
        this.numeroPiscinas = 0;
        this.numeroPistas = 0;
    }

    public Solar(String nombre, int posicion, double valor) {
        super(nombre, posicion, valor);
        this.valorCasa = 0.6 * super.getValor();
        this.valorPiscina = 0.4 * super.getValor();
        this.valorPistaDeporte = 1.25 * super.getValor();
        this.valorHotel = this.valorCasa;
        this.casas = new ArrayList<>();
        this.hoteles = new ArrayList<>();
        this.piscinas = new ArrayList<>();
        this.pistas = new ArrayList<>();
        this.numeroCasas = 0;
        this.numeroHoteles = 0;
        this.numeroPiscinas = 0;
        this.numeroPistas = 0;
    }

    public void edificar(Jugador jugador, Taboleiro taboleiro, String tipo){
        String id;
        switch (tipo){
            case "casa":
                id = taboleiro.idCasa(this); /* MODIFICAR ESTAS COSAS PARA QUE SE LE PASE UN SOLAR Y NO UNA CASILLA!!!!!  */
                break;
            case "hotel":
                id = taboleiro.idHotel(this);
                break;
            case "piscina":
                id = taboleiro.idPiscina(this);
                break;
            case "pista":
                id = taboleiro.idPista(this);
                break;
            default:
                System.out.println("ERROR, tipo de edificio erróneo.");
                break;
        }
    }


    @Override
    public double getValorAlquiler() {
        double valor = 0;
        int aux = 0;
        aux = casas.size();
        switch (aux) {
            case 1:
                valor = 5 * super.getAlquiler();
                break;
            case 2:
                valor = 15 * super.getAlquiler();
                break;
            case 3:
                valor = 35 * super.getAlquiler();
                break;
            case 4:
                valor = 50 * super.getAlquiler();
                break;
            default:
                valor = super.getAlquiler();
                break;
        }
        aux = hoteles.size();
        switch (aux) {
            case 1:
                valor += 70 * super.getAlquiler();
                break;
            case 2:
                valor += 140 * super.getAlquiler();
                break;
            case 3:
                valor += 210 * super.getAlquiler();
                break;
            default:
                break;
        }
        aux = piscinas.size();
        switch (aux) {
            case 1:
                valor += 25 * super.getAlquiler();
                break;
            case 2:
                valor += 50 * super.getAlquiler();
                break;
            case 3:
                valor += 75 * super.getAlquiler();
                break;
            default:
                break;
        }
        aux = pistas.size();
        switch (aux) {
            case 1:
                valor += 25 * super.getAlquiler();
                break;
            case 2:
                valor += 50 * super.getAlquiler();
                break;
            case 3:
                valor += 75 * super.getAlquiler();
                break;
            default:
                break;
        }

        return valor;
    }

    @Override
    public String toString() {
        String banca, texto, edificaciones = "[ ]";
        if (super.getDuenho() == null) {
            banca = "banca";
        } else {
            if (super.getDuenho().getEdificaciones() != null) {
                edificaciones = super.getDuenho().getEdificaciones().toString();
            }
            banca = super.getDuenho().getNombre();
        }

        texto = "{\n\ttipo: solar,\n\tgrupo: " + this.getGrupo().getNumeroGrupo() + ",\n\tpropietario: " + banca +
                ",\n\tvalor: " + super.getValor() + ",\n\talquiler: " + this.getValorAlquiler() + ",\n\tvalor hotel: " +
                this.valorHotel + ",\n\tvalor casa: " + this.valorCasa + ",\n\tvalor piscina: " + this.valorPistaDeporte +
                ",\n\tvalor pista de deporte: " + this.valorPistaDeporte + ",\n\talquiler una casa: " + 5 * super.getAlquiler() +
                ",\n\talquiler dos casas: " + 15 * super.getAlquiler() + ",\n\talquiler tres casas: " + 35 * super.getAlquiler() +
                ",\n\talquiler cuatro casas: " + 50 * super.getValor() + ",\n\talquiler hotel: " + 70 * super.getAlquiler() +
                ",\n\talquiler piscina: " + 25 * super.getValor() + ",\n\talquiler pista de deporte: " + 25 * super.getAlquiler()
                + ",\n\tedificaciones: " + edificaciones + ",\n}";

        return texto;
    }
}
