import java.util.ArrayList;

public class Grupo {
    private int numeroGrupo=0;
    private ArrayList<Casilla> casillas;
    private String color;
    private String lado;

    public Grupo(){}

    public Grupo(ArrayList<Casilla> casillas, int numeroGrupo, String color, String lado){
        this.casillas = casillas;
        this.numeroGrupo = numeroGrupo;
        this.color = color;
        this.lado = lado;
    }

    public Grupo(ArrayList<Casilla> casillas, int numeroGrupo,  String lado){
        this.casillas = casillas;
        this.numeroGrupo = numeroGrupo;
        this.color = null;
        this.lado = lado;
    }
    public ArrayList<Casilla> getCasillas(){
        return casillas;
    }

    public int getNumeroGrupo(){
        return numeroGrupo;
    }

    public String getColor(){
        return color;
    }

    public String getLado(){
        return lado;
    }

    public void setCasillas(ArrayList<Casilla> casillas){
        if (casillas == null){
            System.err.println("Error: casillas no inicializadas.");
            return;
        }
        for (Casilla casilla: casillas){
            if (casilla == null){
                System.err.println("Error: casilla no inicializada.");
                return;
            }
        }
        this.casillas = casillas;
    }

    public void setNumeroGrupo(int numeroGrupo){
        this.numeroGrupo = numeroGrupo;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setLado(String lado){
        this.lado = lado;
    }

    public void anhadirCasilla(Casilla casilla){
        if (casilla != null)
            this.casillas.add(casilla);
    }

    @Override
    public String toString() {
        String texto;
        String prop = "";

        if (casillas.size() != 0) {
            for (Casilla casilla : casillas) {
                prop += casilla.getNombre() + "\t";
            }
        }
        if ((this.numeroGrupo != 0) && (this.color != null)) {
            texto = "\nO grupo de casillas " + this.numeroGrupo +
                    " é de color " + this.color +
                    ".\nConta coas casillas:\n\t" + prop;
        } else
            texto = "O grupo todavía non se inicializou.";
        return texto;
    }
}
