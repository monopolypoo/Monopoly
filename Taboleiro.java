import java.util.ArrayList;

public class Taboleiro {
    private Grupo ladoNorte;
    private Grupo ladoOeste;
    private Grupo ladoLeste;
    private Grupo ladoSur;

    public Taboleiro(Grupo ladoNorte, Grupo ladoOeste, Grupo ladoLeste, Grupo ladoSur){
        this.ladoNorte = ladoNorte;
        this.ladoOeste = ladoOeste;
        this.ladoLeste = ladoLeste;
        this.ladoSur = ladoSur;
    }

    public Grupo getLadoNorte(){
        return ladoNorte;
    }

    public Grupo getLadoOeste(){
        return ladoOeste;
    }

    public Grupo getLadoLeste(){
        return ladoLeste;
    }

    public Grupo getLadoSur(){
        return ladoSur;
    }

    public void setLadoNorte(Grupo lado){
        this.ladoNorte = lado;
    }

    public void setLadoOeste(Grupo lado){
        this.ladoOeste = lado;
    }

    public void setLadoLeste(Grupo lado){
        this.ladoLeste = lado;
    }

    public void setLadoSur(Grupo lado){
        this.ladoSur = lado;
    }

    @Override
    public String toString(){
        String textoTope =  " ──────────────── ──────────────── ──────────────── ──────────────── ──────────────── " +
                "──────────────── ──────────────── ──────────────── ──────────────── ──────────────── " +
                "──────────────── ";
        String textoTopeMedio = " ────────────────                                                                    " +
                "                                                                                     " +
                " ──────────────── \n";
        String textoEspaciado = "|                                                                               " +
                "                                                                         |";

        String textoNorte = textoTope + "\n";
        for (Casilla casilla: this.ladoNorte.getCasillas()){
            textoNorte += "|" + casilla.getNombre();
        }
        textoNorte += "|\n" + textoTope + "\n";

        String textoSur = "";
        for (Casilla casilla: this.ladoSur.getCasillas()){
            textoSur += "|" + casilla.getNombre();
        }
        textoSur += "|\n";

        String texto = textoNorte;
        int i = 0;
        int j;
        for (Casilla casilla: this.ladoOeste.getCasillas()){
            j = 0;
            for (Casilla casilla1: this.ladoLeste.getCasillas()){
                if (i == j){
                    texto += "|" + casilla.getNombre() + textoEspaciado + casilla1.getNombre() + "|\n";
                }
                if ((i == 8) && (j == 8)){
                    break;
                }
                j++;
            }
            texto += textoTopeMedio;
            i++;
        }
        texto += textoTope + "\n" + textoSur + textoTope;
        return texto;
    }
}




