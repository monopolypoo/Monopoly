import javax.swing.*;
import java.util.ArrayList;

public class Taboleiro {
    private Grupo ladoNorte;
    private Grupo ladoOeste;
    private Grupo ladoLeste;
    private Grupo ladoSur;

    public Taboleiro(){
        // Casillas UDC Ferrol (SUR)
        Casilla saida = new Casilla( "Saída           ", "especial", 0);
        Casilla solar1 = new Casilla("Enx. Eléctrica  ", "solar", 1);
        Casilla caixa1 = new Casilla("Caixa Comunidade", "caixa", 2);
        Casilla solar2 = new Casilla("Enx. Mecánica   ", "solar", 3);
        Casilla imp1 = new Casilla(  "Imp. Militar    ", "imposto", 4, Valor.VUELTA);
        Casilla trans1 = new Casilla("Pto. Ferrol     ", "transporte", 5);
        Casilla solar3 = new Casilla("Podoloxía       ", "solar", 6);
        Casilla sorte1 = new Casilla("Sorte           ", "sorte", 7);
        Casilla solar4 = new Casilla("Humanidades     ", "solar", 8);
        Casilla solar5 = new Casilla("Documentación   ", "solar", 9);

        // Casillas UDC Coruña (OESTE)
        Casilla carcere = new Casilla("Cárcere         ", "especial", 10);
        Casilla solar6 = new Casilla( "Fisioterapia    ", "solar", 11);
        Casilla serv1 = new Casilla(  "Serv. Teleco    ", "servizos", 12);
        Casilla solar7 = new Casilla( "Turismo         ", "solar", 13);
        Casilla solar8 = new Casilla( "Náutica         ", "solar", 14);
        Casilla trans2 = new Casilla( "Base Esp. Coruña", "transporte", 15);
        Casilla solar9 = new Casilla( "Arquitectura    ", "solar", 16);
        Casilla caixa2 = new Casilla( "Caixa Comunidade", "caixa", 17);
        Casilla solar10 = new Casilla("Enx. Camiños    ", "solar", 18);
        Casilla solar11 = new Casilla("FIC             ", "solar", 19);

        // Casillas USC Santiago (NORTE)
        Casilla parking = new Casilla("Parking         ", "especial", 20);
        Casilla solar12 = new Casilla("Historia        ", "solar", 21);
        Casilla sorte2 = new Casilla( "Sorte           ", "sorte", 22);
        Casilla solar13 = new Casilla("Filoloxía       ", "solar", 23);
        Casilla solar14 = new Casilla("C. da Educación ", "solar", 24);
        Casilla trans3 = new Casilla( "Apto. A Valleta ", "transporte", 25);
        Casilla solar15 = new Casilla("Medicina        ", "solar", 26);
        Casilla solar16 = new Casilla("Enfermería      ", "solar", 27);
        Casilla serv2 = new Casilla(  "Serv. Eléctrico ", "servizos", 28);
        Casilla solar17 = new Casilla("ADE             ", "solar", 29);

        // Casillas USC Santiago (LESTE)
        Casilla irCarcere = new Casilla("Ir Cárcere      ", "especial", 30);
        Casilla solar18 = new Casilla(  "Farmacia        ", "solar", 31);
        Casilla solar19 = new Casilla(  "Bioloxía        ", "solar", 32);
        Casilla caixa3 = new Casilla(   "Caixa Comunidade", "solar", 33);
        Casilla solar20 = new Casilla(  "Química         ", "solar", 34);
        Casilla trans4 = new Casilla(   "E. tren Santiago", "transporte", 35);
        Casilla sorte3 = new Casilla(   "Sorte           ", "sorte", 36);
        Casilla solar21 = new Casilla(  "Física          ", "solar", 37);
        Casilla imp2 = new Casilla(     "Imp. da luz     ", "imposto", 38, Valor.VUELTA / 2.0);
        Casilla solar22 = new Casilla(  "ETSE            ", "solar", 39);

        //Creamos los grupos
        ArrayList<Casilla> casillasGrupo1 = new ArrayList<>();
        casillasGrupo1.add(solar1);
        casillasGrupo1.add(solar2);
        Grupo grupo1 = new Grupo(casillasGrupo1, 1, Valor.BLUE, "sur");

        ArrayList<Casilla> casillasGrupo2 = new ArrayList<>();
        casillasGrupo2.add(solar3);
        casillasGrupo2.add(solar4);
        casillasGrupo2.add(solar5);
        Grupo grupo2 = new Grupo(casillasGrupo2, 2, Valor.YELLOW, "sur");

        ArrayList<Casilla> casillasGrupo3 = new ArrayList<>();
        casillasGrupo3.add(solar6);
        casillasGrupo3.add(solar7);
        casillasGrupo3.add(solar8);
        Grupo grupo3 = new Grupo(casillasGrupo3, 3, Valor.PURPLE, "oeste");

        ArrayList<Casilla> casillasGrupo4 = new ArrayList<>();
        casillasGrupo4.add(solar9);
        casillasGrupo4.add(solar10);
        casillasGrupo4.add(solar11);
        Grupo grupo4 = new Grupo(casillasGrupo4, 4, Valor.YELLOW_BRIGHT, "oeste");

        ArrayList<Casilla>  casillasGrupo5 = new ArrayList<>();
        casillasGrupo5.add(solar12);
        casillasGrupo5.add(solar13);
        casillasGrupo5.add(solar14);
        Grupo grupo5 = new Grupo(casillasGrupo5,5, Valor.GREEN, "norte");

        ArrayList<Casilla>  casillasGrupo6 = new ArrayList<>();
        casillasGrupo6.add(solar15);
        casillasGrupo6.add(solar16);
        casillasGrupo6.add(solar17);
        Grupo grupo6 = new Grupo(casillasGrupo6,6, Valor.RED, "norte");

        ArrayList<Casilla>  casillasGrupo7 = new ArrayList<>();
        casillasGrupo7.add(solar18);
        casillasGrupo7.add(solar19);
        casillasGrupo7.add(solar20);
        Grupo grupo7 = new Grupo(casillasGrupo7,7, Valor.GREEN_BRIGHT, "leste");

        ArrayList<Casilla>  casillasGrupo8 = new ArrayList<>();
        casillasGrupo8.add(solar21);
        casillasGrupo8.add(solar22);
        Grupo grupo8 = new Grupo(casillasGrupo8,8, Valor.CYAN, "leste");

        ArrayList<Casilla>  casillasEspeciales = new ArrayList<>();
        casillasEspeciales.add(saida);
        casillasEspeciales.add(carcere);
        casillasEspeciales.add(parking);
        casillasEspeciales.add(irCarcere);
        Grupo grupo9 = new Grupo(casillasEspeciales, 9, 0, Valor.BLACK_BRIGHT);

        ArrayList<Casilla>  casillasTransportes = new ArrayList<>();
        casillasTransportes.add(trans1);
        casillasTransportes.add(trans2);
        casillasTransportes.add(trans3);
        casillasTransportes.add(trans4);
        Grupo grupo10 = new Grupo(casillasTransportes, 10, Valor.VUELTA, Valor.BLACK_BOLD_BRIGHT);

        ArrayList<Casilla>  casillasServicios = new ArrayList<>();
        casillasServicios.add(serv1);
        casillasServicios.add(serv2);
        Grupo grupo11 = new Grupo(casillasServicios, 11, 0.75 * Valor.VUELTA, Valor.CYAN_BRIGHT);

        ArrayList<Casilla>  casillasImpuestos = new ArrayList<>();
        casillasImpuestos.add(imp1);
        casillasImpuestos.add(imp2);
        Grupo grupo12 = new Grupo(casillasImpuestos, 12, 0, Valor.RED);

        ArrayList<Casilla>  casillasCaixasSortes = new ArrayList<>();
        casillasCaixasSortes.add(caixa1);
        casillasCaixasSortes.add(caixa2);
        casillasCaixasSortes.add(caixa3);
        casillasCaixasSortes.add(sorte1);
        casillasCaixasSortes.add(sorte2);
        casillasCaixasSortes.add(sorte3);
        Grupo grupo13 = new Grupo(casillasCaixasSortes, 13, 0, Valor.BLACK_BOLD);


        //hacemos los grupos de los lados
        ArrayList<Casilla>  casillasLadoNorte = new ArrayList<>();
        casillasLadoNorte.add(parking);
        casillasLadoNorte.add(solar12);
        casillasLadoNorte.add(sorte2);
        casillasLadoNorte.add(solar13);
        casillasLadoNorte.add(solar14);
        casillasLadoNorte.add(trans3);
        casillasLadoNorte.add(solar15);
        casillasLadoNorte.add(solar16);
        casillasLadoNorte.add(serv2);
        casillasLadoNorte.add(solar17);
        casillasLadoNorte.add(irCarcere);
        this.ladoNorte = new Grupo(casillasLadoNorte, 14, "norte");

        ArrayList<Casilla>  casillasLadoOeste = new ArrayList<>();
        casillasLadoOeste.add(solar11);
        casillasLadoOeste.add(solar10);
        casillasLadoOeste.add(caixa2);
        casillasLadoOeste.add(solar9);
        casillasLadoOeste.add(trans2);
        casillasLadoOeste.add(solar8);
        casillasLadoOeste.add(solar7);
        casillasLadoOeste.add(serv1);
        casillasLadoOeste.add(solar6);
        this.ladoOeste = new Grupo(casillasLadoOeste, 15, "oeste");

        ArrayList<Casilla>  casillasLadoLeste = new ArrayList<>();
        casillasLadoLeste.add(solar19);
        casillasLadoLeste.add(solar20);
        casillasLadoLeste.add(caixa3);
        casillasLadoLeste.add(solar21);
        casillasLadoLeste.add(trans4);
        casillasLadoLeste.add(sorte3);
        casillasLadoLeste.add(solar21);
        casillasLadoLeste.add(imp2);
        casillasLadoLeste.add(solar22);
        this.ladoLeste = new Grupo(casillasLadoLeste, 16, "leste");

        ArrayList<Casilla>  casillasLadoSur = new ArrayList<>();
        casillasLadoSur.add(saida);
        casillasLadoSur.add(solar1);
        casillasLadoSur.add(caixa1);
        casillasLadoSur.add(solar2);
        casillasLadoSur.add(imp1);
        casillasLadoSur.add(trans1);
        casillasLadoSur.add(solar3);
        casillasLadoSur.add(sorte1);
        casillasLadoSur.add(solar4);
        casillasLadoSur.add(solar5);
        casillasLadoSur.add(carcere);
        this.ladoSur = new Grupo(casillasLadoSur, 17, "sur");

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

        String textoTope = BLANCO + "┌";
        for (int i=0; i<10; i++){
            textoTope += "────────────────┬";
        }
        textoTope += "────────────────┐\n";

        String textoTopeAbajo = BLANCO + "└";
        for (int i=0; i<10; i++){
            textoTopeAbajo += "────────────────┴";
        }
        textoTopeAbajo += "────────────────┘\n";


        String textoTopeMedioArriba = BLANCO + "├────────────────┼";
        for (int i=0; i<8; i++){
            textoTopeMedioArriba += "────────────────┴";
        }
        textoTopeMedioArriba += "────────────────┼────────────────┤\n";

        String textoTopeMedioAbajo = BLANCO + "├────────────────┼";
        for (int i=0; i<8; i++){
            textoTopeMedioAbajo += "────────────────┬";
        }
        textoTopeMedioAbajo += "────────────────┼────────────────┤\n";


        String textoEspaciado = BLANCO + "│ ";
        for (int i=0; i<151; i++){
            textoEspaciado += " ";
        }
        textoEspaciado += "│";
        // ┬
        String textoTopeEspaciado = BLANCO + "├────────────────┤";
        for (int i=0; i<152; i++){
            textoTopeEspaciado += " ";
        }
        textoTopeEspaciado += "├────────────────┤\n";

        String textoNorte = "";
        String textoSur = "";
        String textoOesteLeste = "";
        for (int i=0; i < ladoNorte.getCasillas().size(); i++){
            textoNorte += BLANCO + "│" + ladoNorte.getCasillas().get(i).getColorGrupo() + ladoNorte.getCasillas().get(i).getNombre();
            textoSur += BLANCO + "│" + ladoSur.getCasillas().get(i).getColorGrupo() + ladoSur.getCasillas().get(i).getNombre();
            if (i<9){
                textoOesteLeste +=  BLANCO + "│" + ladoOeste.getCasillas().get(i).getColorGrupo() +
                                    ladoOeste.getCasillas().get(i).getNombre() + textoEspaciado +
                                    ladoLeste.getCasillas().get(i).getColorGrupo() +
                                    ladoLeste.getCasillas().get(i).getNombre() + BLANCO + "|\n";
            }
            if (i<8)
                textoOesteLeste += BLANCO + textoTopeEspaciado;
        }
        String texto = BLANCO + textoTope + textoNorte + "│\n" + textoTopeMedioArriba + textoOesteLeste + textoTopeMedioAbajo + textoSur + BLANCO + "│\n" + textoTopeAbajo;
        return texto;
    }
}




