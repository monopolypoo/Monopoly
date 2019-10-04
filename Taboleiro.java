import javax.swing.*;
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
        String BLANCO = "\033[0;37m";

        String textoTope = BLANCO + " ";
        for (int i=0; i<11; i++){
            textoTope += "──────────────── ";
        }
        textoTope += "\n";

        String textoEspaciado = BLANCO + "| ";
        for (int i=0; i<151; i++){
            textoEspaciado += " ";
        }
        textoEspaciado += "|";

        String textoTopeEspaciado = BLANCO + " ────────────────";
        for (int i=0; i<154; i++){
            textoTopeEspaciado += " ";
        }
        textoTopeEspaciado += "──────────────── \n";

        String textoNorte = "";
        String textoSur = "";
        String textoOesteLeste = "";
        for (int i=0; i < ladoNorte.getCasillas().size(); i++){
            textoNorte += BLANCO + "|" + ladoNorte.getCasillas().get(i).getColorGrupo() + ladoNorte.getCasillas().get(i).getNombre();
            textoSur += BLANCO + "|" + ladoSur.getCasillas().get(i).getColorGrupo() + ladoSur.getCasillas().get(i).getNombre();
            if (i<9){
                textoOesteLeste +=  BLANCO + "|" + ladoOeste.getCasillas().get(i).getColorGrupo() +
                                    ladoOeste.getCasillas().get(i).getNombre() + textoEspaciado +
                                    ladoLeste.getCasillas().get(i).getColorGrupo() +
                                    ladoLeste.getCasillas().get(i).getNombre() + BLANCO + "|\n";
            }
            if (i<8)
                textoOesteLeste += BLANCO + textoTopeEspaciado;
        }
        String texto = BLANCO + textoTope + textoNorte + "|\n" + textoTope + textoOesteLeste + textoTope + textoSur + BLANCO + "|\n" + textoTope;
        return texto;
    }
}




