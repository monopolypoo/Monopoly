import java.util.Random;

public class Avatar{
    private String id;
    private String tipo;
    private Jugador jugador;
    private Casilla casilla;

    public Avatar(){}

    public Avatar(String tipo, Jugador jugador){
        if(tipo.equals("Sombrero") || tipo.equals("Esfinge") || tipo.equals("Coche") || tipo.equals("Pelota"))
            this.tipo = tipo;
        else
            this.tipo = "Coche";
        if (jugador == null){
            System.err.println("Error: jugador no inicializado.");
            System.exit(1);
        }
        this.jugador = jugador;
        this.generarId();
    }

    private int idUnico(String id){
        //comparar los ids de todos los jugadores con el que se pasa como argumento y si son distintos return 1 y si son iguales return 0
    }

    private void generarId(){
        do{
            Random ale = new Random(System.currentTimeMillis()); //pone una semilla nueva de cada vez
            int numero = ale.nextInt(20) + 65; //genera un numero aleatorio entre 65 y 65+20
            String nombre = "" + (char) numero; //se pasa a ASCII y se convierte en String concatenandolo con ""
        }while (!idUnico(nombre));

        this.id = nombre;

        //FALTA AÑADIR LA LÓGICA PARA CHEQUEAR QUE EL ID ES ÚNICO
    }

    public String getId(){
        return id;
    }

    public String getTipo(){
        return tipo;
    }

    public String getJugador(){
        return jugador.getNombre();
    }

    public Casilla getCasilla(){ return casilla; }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    @Override
    public String toString(){
        String texto = "{\n" +
                "\tid: " + this.id + "\n" +
                "\ttipo: " + this.tipo + "\n" +
                "\tjugador: " + this.jugador.getNombre() + "\n}";

        return texto;
    }
}
