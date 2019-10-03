import java.util.Random;

public class Avatar{
    private String id;
    private String tipo;
    private Jugador jugador;

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

    private void generarId(){
        Random ale = new Random(System.currentTimeMillis()); //pone una semilla nueva de cada vez
        int numero = ale.nextInt(20) + 65; //genera un numero aleatorio entre 65 y 65+20
        String nombre = "" + (char) numero; //se pasa a ASCII y se convierte en String concatenandolo con ""
        id = nombre;

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

    @Override
    public String toString(){
        String texto = "{\n" +
                "\tid: " + this.id + "\n" +
                "\ttipo: " + this.tipo + "\n" +
                "\tjugador: " + this.jugador.getNombre() + "\n}";

        return texto;
    }
}
