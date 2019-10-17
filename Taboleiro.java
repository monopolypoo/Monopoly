import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Taboleiro {
    private Grupo ladoNorte;
    private Grupo ladoOeste;
    private Grupo ladoLeste;
    private Grupo ladoSur;
    private HashMap<String, Casilla> casillas;
    private HashMap<Integer, Casilla> casillasPosicion;
    private ArrayList<Casilla> casillasNoEnVenta;
    private ArrayList<Casilla> casillasEnVenta;


    public Taboleiro() {
        // Casillas UDC Ferrol (SUR)
        Casilla saida = new Casilla(" Salida           ", "especial", 0);
        Casilla solar1 = new Casilla("Ing. Eléctrica    ", "solar", 1);
        Casilla caixa1 = new Casilla(" Caja Comunidad   ", "caixa", 2);
        Casilla solar2 = new Casilla(" Ing. Mecánica    ", "solar", 3);
        Casilla imp1 = new Casilla(" Imp. Militar     ", "imposto", 4, Valor.VUELTA);
        Casilla trans1 = new Casilla(" Pto. Ferrol      ", "transporte", 5);
        Casilla solar3 = new Casilla(" Podología        ", "solar", 6);
        Casilla sorte1 = new Casilla(" Suerte           ", "sorte", 7);
        Casilla solar4 = new Casilla(" Humanidades      ", "solar", 8);
        Casilla solar5 = new Casilla(" Documentación    ", "solar", 9);

        // Casillas UDC Coruña (OESTE)
        Casilla carcere = new Casilla(" Cárcel           ", "especial", 10);
        Casilla solar6 = new Casilla( " Fisioterapia     ", "solar", 11);
        Casilla serv1 = new Casilla(" Serv. Teleco     ", "servizos", 12);
        Casilla solar7 = new Casilla(" Turismo          ", "solar", 13);
        Casilla solar8 = new Casilla(" Náutica          ", "solar", 14);
        Casilla trans2 = new Casilla(" Base Esp. Coruña ", "transporte", 15);
        Casilla solar9 = new Casilla(" Arquitectura     ", "solar", 16);
        Casilla caixa2 = new Casilla(" Caja Comunidad   ", "caixa", 17);
        Casilla solar10 = new Casilla(" Ing. Caminos     ", "solar", 18);
        Casilla solar11 = new Casilla(" FIC              ", "solar", 19);

        // Casillas USC Santiago (NORTE)
        Casilla parking = new Casilla(" Parking          ", "especial", 20);
        Casilla solar12 = new Casilla(" Historia         ", "solar", 21);
        Casilla sorte2 = new Casilla(" Suerte           ", "sorte", 22);
        Casilla solar13 = new Casilla(" Filología        ", "solar", 23);
        Casilla solar14 = new Casilla(" C. de Educación  ", "solar", 24);
        Casilla trans3 = new Casilla(" Apto. A Valleta  ", "transporte", 25);
        Casilla solar15 = new Casilla(" Medicina         ", "solar", 26);
        Casilla solar16 = new Casilla(" Enfermería       ", "solar", 27);
        Casilla serv2 = new Casilla(" Serv. Eléctrico  ", "servizos", 28);
        Casilla solar17 = new Casilla(" ADE              ", "solar", 29);

        // Casillas USC Santiago (LESTE)
        Casilla irCarcere = new Casilla(" Ir Cárcel        ", "especial", 30);
        Casilla solar18 = new Casilla(" Farmacia         ", "solar", 31);
        Casilla solar19 = new Casilla(" Bioloxía         ", "solar", 32);
        Casilla caixa3 = new Casilla(" Caja Comunidad   ", "solar", 33);
        Casilla solar20 = new Casilla(" Química          ", "solar", 34);
        Casilla trans4 = new Casilla(" E. tren Santiago ", "transporte", 35);
        Casilla sorte3 = new Casilla(" Suerte           ", "sorte", 36);
        Casilla solar21 = new Casilla(" Física           ", "solar", 37);
        Casilla imp2 = new Casilla(   " Imp. de la luz   ", "imposto", 38, Valor.VUELTA / 2.0);
        Casilla solar22 = new Casilla(" ETSE             ", "solar", 39);

        //Creamos los grupos
        ArrayList<Casilla> casillasGrupo1 = new ArrayList<>();
        casillasGrupo1.add(solar1);
        casillasGrupo1.add(solar2);
        Grupo grupo1 = new Grupo(casillasGrupo1, 1, Valor.BLUE, "sur", Valor.PRECIO_GRUPO1);

        ArrayList<Casilla> casillasGrupo2 = new ArrayList<>();
        casillasGrupo2.add(solar3);
        casillasGrupo2.add(solar4);
        casillasGrupo2.add(solar5);
        Grupo grupo2 = new Grupo(casillasGrupo2, 2, Valor.YELLOW, "sur", Valor.PRECIO_GRUPO2);

        ArrayList<Casilla> casillasGrupo3 = new ArrayList<>();
        casillasGrupo3.add(solar6);
        casillasGrupo3.add(solar7);
        casillasGrupo3.add(solar8);
        Grupo grupo3 = new Grupo(casillasGrupo3, 3, Valor.PURPLE, "oeste", Valor.PRECIO_GRUPO3);

        ArrayList<Casilla> casillasGrupo4 = new ArrayList<>();
        casillasGrupo4.add(solar9);
        casillasGrupo4.add(solar10);
        casillasGrupo4.add(solar11);
        Grupo grupo4 = new Grupo(casillasGrupo4, 4, Valor.YELLOW_BRIGHT, "oeste", Valor.PRECIO_GRUPO4);

        ArrayList<Casilla> casillasGrupo5 = new ArrayList<>();
        casillasGrupo5.add(solar12);
        casillasGrupo5.add(solar13);
        casillasGrupo5.add(solar14);
        Grupo grupo5 = new Grupo(casillasGrupo5, 5, Valor.GREEN, "norte", Valor.PRECIO_GRUPO5);

        ArrayList<Casilla> casillasGrupo6 = new ArrayList<>();
        casillasGrupo6.add(solar15);
        casillasGrupo6.add(solar16);
        casillasGrupo6.add(solar17);
        Grupo grupo6 = new Grupo(casillasGrupo6, 6, Valor.RED, "norte", Valor.PRECIO_GRUPO6);

        ArrayList<Casilla> casillasGrupo7 = new ArrayList<>();
        casillasGrupo7.add(solar18);
        casillasGrupo7.add(solar19);
        casillasGrupo7.add(solar20);
        Grupo grupo7 = new Grupo(casillasGrupo7, 7, Valor.GREEN_BRIGHT, "leste", Valor.PRECIO_GRUPO7);

        ArrayList<Casilla> casillasGrupo8 = new ArrayList<>();
        casillasGrupo8.add(solar21);
        casillasGrupo8.add(solar22);
        Grupo grupo8 = new Grupo(casillasGrupo8, 8, Valor.CYAN, "leste", Valor.PRECIO_GRUPO8);

        ArrayList<Casilla> casillasEspeciales = new ArrayList<>();
        casillasEspeciales.add(saida);
        casillasEspeciales.add(carcere);
        casillasEspeciales.add(parking);
        casillasEspeciales.add(irCarcere);
        Grupo grupo9 = new Grupo(casillasEspeciales, 9, 0, Valor.BLACK_BRIGHT);

        ArrayList<Casilla> casillasTransportes = new ArrayList<>();
        casillasTransportes.add(trans1);
        casillasTransportes.add(trans2);
        casillasTransportes.add(trans3);
        casillasTransportes.add(trans4);
        Grupo grupo10 = new Grupo(casillasTransportes, 10, Valor.VUELTA, Valor.BLACK_BOLD_BRIGHT);

        ArrayList<Casilla> casillasServicios = new ArrayList<>();
        casillasServicios.add(serv1);
        casillasServicios.add(serv2);
        Grupo grupo11 = new Grupo(casillasServicios, 11, 0.75 * Valor.VUELTA, Valor.CYAN_BRIGHT);

        ArrayList<Casilla> casillasImpuestos = new ArrayList<>();
        casillasImpuestos.add(imp1);
        casillasImpuestos.add(imp2);
        Grupo grupo12 = new Grupo(casillasImpuestos, Valor.RED, 12);

        ArrayList<Casilla> casillasCaixasSortes = new ArrayList<>();
        casillasCaixasSortes.add(caixa1);
        casillasCaixasSortes.add(caixa2);
        casillasCaixasSortes.add(caixa3);
        casillasCaixasSortes.add(sorte1);
        casillasCaixasSortes.add(sorte2);
        casillasCaixasSortes.add(sorte3);
        Grupo grupo13 = new Grupo(casillasCaixasSortes, 13, 0, Valor.BLACK_BOLD);


        //hacemos los grupos de los lados
        ArrayList<Casilla> casillasLadoNorte = new ArrayList<>();
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

        ArrayList<Casilla> casillasLadoOeste = new ArrayList<>();
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

        ArrayList<Casilla> casillasLadoLeste = new ArrayList<>();
        casillasLadoLeste.add(solar18);
        casillasLadoLeste.add(solar19);
        casillasLadoLeste.add(caixa3);
        casillasLadoLeste.add(solar20);
        casillasLadoLeste.add(trans4);
        casillasLadoLeste.add(sorte3);
        casillasLadoLeste.add(solar21);
        casillasLadoLeste.add(imp2);
        casillasLadoLeste.add(solar22);
        this.ladoLeste = new Grupo(casillasLadoLeste, 16, "leste");

        ArrayList<Casilla> casillasLadoSur = new ArrayList<>();
        casillasLadoSur.add(carcere);
        casillasLadoSur.add(solar5);
        casillasLadoSur.add(solar4);
        casillasLadoSur.add(sorte1);
        casillasLadoSur.add(solar3);
        casillasLadoSur.add(trans1);
        casillasLadoSur.add(imp1);
        casillasLadoSur.add(solar2);
        casillasLadoSur.add(caixa1);
        casillasLadoSur.add(solar1);
        casillasLadoSur.add(saida);
        this.ladoSur = new Grupo(casillasLadoSur, 17, "sur");


        //añadimos las casillas a un HashMap
        this.casillas = new HashMap<>();
        this.casillas.put("Salida", saida);
        this.casillas.put("Ing.Eléctrica", solar1);
        this.casillas.put("CajaComunidad", caixa1);
        this.casillas.put("Ing.Mecánica", solar2);
        this.casillas.put("Imp.Militar", imp1);
        this.casillas.put("Pto.Ferrol", trans1);
        this.casillas.put("Podología", solar3);
        this.casillas.put("Suerte", sorte1);
        this.casillas.put("Humanidades", solar4);
        this.casillas.put("Documentación", solar5);

        this.casillas.put("Cárcel", carcere);
        this.casillas.put("Fisioterapia", solar6);
        this.casillas.put("Serv.Teleco", serv1);
        this.casillas.put("Turismo", solar7);
        this.casillas.put("Náutica", solar8);
        this.casillas.put("BaseEsp.Coruña", trans2);
        this.casillas.put("Arquitectura", solar9);
        this.casillas.put("Ing.Camiños", solar10);
        this.casillas.put("FIC", solar11);

        this.casillas.put("Parking", parking);
        this.casillas.put("Historia", solar12);
        this.casillas.put("Filología", solar13);
        this.casillas.put("C.deEducación", solar14);
        this.casillas.put("Apto.AValleta", trans3);
        this.casillas.put("Medicina", solar15);
        this.casillas.put("Enfermería", solar16);
        this.casillas.put("Serv.Eléctrico", serv2);
        this.casillas.put("ADE", solar17);

        this.casillas.put("IrCárcel", irCarcere);
        this.casillas.put("Farmacia", solar18);
        this.casillas.put("Biología", solar19);
        this.casillas.put("Química", solar20);
        this.casillas.put("E.trenSantiago", trans4);
        this.casillas.put("Física", solar21);
        this.casillas.put("Imp.delaLuz", imp2);
        this.casillas.put("ETSE", solar22);


        this.casillasPosicion = new HashMap<>();
        //añadimos las casillas a un HashMap
        this.casillasPosicion.put(0, saida);
        this.casillasPosicion.put(1, solar1);
        this.casillasPosicion.put(2, caixa1);
        this.casillasPosicion.put(3, solar2);
        this.casillasPosicion.put(4, imp1);
        this.casillasPosicion.put(5, trans1);
        this.casillasPosicion.put(6, solar3);
        this.casillasPosicion.put(7, sorte1);
        this.casillasPosicion.put(8, solar4);
        this.casillasPosicion.put(9, solar5);

        this.casillasPosicion.put(10, carcere);
        this.casillasPosicion.put(11, solar6);
        this.casillasPosicion.put(12, serv1);
        this.casillasPosicion.put(13, solar7);
        this.casillasPosicion.put(14, solar8);
        this.casillasPosicion.put(15, trans2);
        this.casillasPosicion.put(16, solar9);
        this.casillasPosicion.put(17, caixa2);
        this.casillasPosicion.put(18, solar10);
        this.casillasPosicion.put(19, solar11);

        this.casillasPosicion.put(20, parking);
        this.casillasPosicion.put(21, solar12);
        this.casillasPosicion.put(22, sorte2);
        this.casillasPosicion.put(23, solar13);
        this.casillasPosicion.put(24, solar14);
        this.casillasPosicion.put(25, trans3);
        this.casillasPosicion.put(26, solar15);
        this.casillasPosicion.put(27, solar16);
        this.casillasPosicion.put(28, serv2);
        this.casillasPosicion.put(29, solar17);

        this.casillasPosicion.put(30, irCarcere);
        this.casillasPosicion.put(31, solar18);
        this.casillasPosicion.put(32, solar19);
        this.casillasPosicion.put(33, caixa3);
        this.casillasPosicion.put(34, solar20);
        this.casillasPosicion.put(35, trans4);
        this.casillasPosicion.put(36, sorte3);
        this.casillasPosicion.put(37, solar21);
        this.casillasPosicion.put(38, imp2);
        this.casillasPosicion.put(39, solar22);

        this.casillasNoEnVenta = new ArrayList<>();
        casillasNoEnVenta.add(sorte1);
        casillasNoEnVenta.add(sorte2);
        casillasNoEnVenta.add(sorte3);
        casillasNoEnVenta.add(caixa1);
        casillasNoEnVenta.add(caixa2);
        casillasNoEnVenta.add(caixa3);
        casillasNoEnVenta.add(saida);
        casillasNoEnVenta.add(carcere);
        casillasNoEnVenta.add(parking);
        casillasNoEnVenta.add(irCarcere);
        casillasNoEnVenta.add(imp1);
        casillasNoEnVenta.add(imp2);

        this.casillasEnVenta = new ArrayList<>();
        casillasEnVenta.add(solar1);
        casillasEnVenta.add(solar2);
        casillasEnVenta.add(trans1);
        casillasEnVenta.add(solar3);
        casillasEnVenta.add(solar4);
        casillasEnVenta.add(solar5);
        casillasEnVenta.add(solar6);
        casillasEnVenta.add(serv1);
        casillasEnVenta.add(solar7);
        casillasEnVenta.add(solar8);
        casillasEnVenta.add(trans2);
        casillasEnVenta.add(solar9);
        casillasEnVenta.add(solar10);
        casillasEnVenta.add(solar11);
        casillasEnVenta.add(solar12);
        casillasEnVenta.add(solar13);
        casillasEnVenta.add(solar14);
        casillasEnVenta.add(trans3);
        casillasEnVenta.add(solar15);
        casillasEnVenta.add(solar16);
        casillasEnVenta.add(serv2);
        casillasEnVenta.add(solar17);
        casillasEnVenta.add(solar18);
        casillasEnVenta.add(solar19);
        casillasEnVenta.add(solar20);
        casillasEnVenta.add(trans4);
        casillasEnVenta.add(solar21);
        casillasEnVenta.add(solar22);

    }

    public Grupo getLadoNorte() {
        return ladoNorte;
    }

    public Grupo getLadoOeste() {
        return ladoOeste;
    }

    public Grupo getLadoLeste() {
        return ladoLeste;
    }

    public Grupo getLadoSur() {
        return ladoSur;
    }

    public HashMap<String, Casilla> getCasillas() {
        return casillas;
    }

    public Casilla getCasillaPosicion(int posicion) {
        return casillasPosicion.get(posicion);
    }

    public void setLadoNorte(Grupo lado) {
        this.ladoNorte = lado;
    }

    public void setLadoOeste(Grupo lado) {
        this.ladoOeste = lado;
    }

    public void setLadoLeste(Grupo lado) {
        this.ladoLeste = lado;
    }

    public void setLadoSur(Grupo lado) {
        this.ladoSur = lado;
    }

    public ArrayList<Casilla> getCasillasNoEnVenta(){
        return casillasNoEnVenta;
    }

    public ArrayList<Casilla> getCasillasEnVenta(){
        return casillasEnVenta;
    }

    public void subirPrecios(){
        ArrayList<Grupo> todasLasCasillas = new ArrayList<>();
        todasLasCasillas.add(this.ladoNorte);
        todasLasCasillas.add(this.ladoSur);
        todasLasCasillas.add(this.ladoOeste);
        todasLasCasillas.add(this.ladoLeste);

        for (Casilla casilla : todasLasCasillas.get(0).getCasillas()){
            if (casilla.getTipo().equals("solar")){
                casilla.setValor(casilla.getValor() * 1.05);
            }
        }
        for (Casilla casilla : todasLasCasillas.get(1).getCasillas()){
            if (casilla.getTipo().equals("solar")){
                casilla.setValor(casilla.getValor() * 1.05);
            }
        }
        for (Casilla casilla : todasLasCasillas.get(2).getCasillas()){
            if (casilla.getTipo().equals("solar")){
                casilla.setValor(casilla.getValor() * 1.05);
            }
        }
        for (Casilla casilla : todasLasCasillas.get(3).getCasillas()){
            if (casilla.getTipo().equals("solar")){
                casilla.setValor(casilla.getValor() * 1.05);
            }
        }
    }

    public void listarEnVenta(){
        String texto = "";
        for (Casilla cas : this.casillasEnVenta){
            texto += "{\n\tNombre: " + cas.getNombreSinEspacio() + "\n\tTipo: " + cas.getTipo() + "\n\tValor: " + cas.getValor() + "\n}\n";
        }
        System.out.println(texto);
    }

    public boolean sePuedeComprar(Casilla casilla){
        if (this.casillasNoEnVenta.contains(casilla)){
            return false;
        }
        else
            return true;
    }

    @Override
    public String toString() {
        String BLANCO = Valor.WHITE;
        String textoAvataresNorte = "";
        String textoAvataresLados = "";
        String textoAvataresSur = "";

        String textoTope = BLANCO + "┌";
        for (int i = 0; i < 10; i++) {
            textoTope += "──────────────────┬";
        }
        textoTope += "──────────────────┐\n";

        String textoTopeAbajo = BLANCO + "└";
        for (int i = 0; i < 10; i++) {
            textoTopeAbajo += "──────────────────┴";
        }
        textoTopeAbajo += "──────────────────┘\n";


        String textoTopeMedioArriba = BLANCO + "├──────────────────┼";
        for (int i = 0; i < 8; i++) {
            textoTopeMedioArriba += "──────────────────┴";
        }
        textoTopeMedioArriba += "──────────────────┼──────────────────┤\n";

        String textoTopeMedioAbajo = BLANCO + "├──────────────────┼";
        for (int i = 0; i < 8; i++) {
            textoTopeMedioAbajo += "──────────────────┬";
        }
        textoTopeMedioAbajo += "──────────────────┼──────────────────┤\n";


        String textoEspaciado = BLANCO + "│ ";
        for (int i = 0; i < 169; i++) {
            textoEspaciado += " ";
        }
        textoEspaciado += "│";
        // ┬
        String textoTopeEspaciado = BLANCO + "├──────────────────┤";
        for (int i = 0; i < 170; i++) {
            textoTopeEspaciado += " ";
        }
        textoTopeEspaciado += "├──────────────────┤\n";

        String textoNorte = "";
        String textoSur = "";
        String textoOesteLeste = "";
        for (int i = 0; i < ladoNorte.getCasillas().size(); i++) {
            textoNorte += BLANCO + "│" + ladoNorte.getCasillas().get(i).getColorGrupo() + ladoNorte.getCasillas().get(i).getNombre();
            textoAvataresNorte += BLANCO + "│";
            textoAvataresSur += BLANCO + "|";
            if (ladoNorte.getCasillas().get(i).haiAvatar()) {
                textoAvataresNorte += ladoNorte.getCasillas().get(i).getIds();
            } else {
                for (int j = 0; j < 18; j++) {
                    textoAvataresNorte += " ";
                }
            }
            if (ladoSur.getCasillas().get(i).haiAvatar()) {
                textoAvataresSur += ladoSur.getCasillas().get(i).getIds();
            } else {
                for (int j = 0; j < 18; j++) {
                    textoAvataresSur += " ";
                }
            }
            textoSur += BLANCO + "│" + ladoSur.getCasillas().get(i).getColorGrupo() + ladoSur.getCasillas().get(i).getNombre();
            if (i < 9) {
                textoOesteLeste += BLANCO + "│" + ladoOeste.getCasillas().get(i).getColorGrupo() +
                        ladoOeste.getCasillas().get(i).getNombre() + textoEspaciado + ladoLeste.getCasillas().get(i).getColorGrupo() +
                        ladoLeste.getCasillas().get(i).getNombre() + BLANCO + "|\n|";
                if (ladoOeste.getCasillas().get(i).haiAvatar()) {
                    textoOesteLeste += ladoOeste.getCasillas().get(i).getIds();
                } else {
                    for (int j = 0; j < 18; j++) {
                        textoOesteLeste += " ";
                    }
                }
                textoOesteLeste += textoEspaciado;
                if (ladoLeste.getCasillas().get(i).haiAvatar()) {
                    textoOesteLeste += ladoLeste.getCasillas().get(i).getIds();
                } else {
                    for (int j = 0; j < 18; j++) {
                        textoOesteLeste += " ";
                    }
                }
                textoOesteLeste += BLANCO + "|\n";
            }
            if (i < 8)
                textoOesteLeste += BLANCO + textoTopeEspaciado;
        }
        String texto = BLANCO + textoTope + textoNorte + BLANCO + "│\n" + textoAvataresNorte + "|\n" + textoTopeMedioArriba + textoOesteLeste + textoTopeMedioAbajo + textoSur + BLANCO + "│\n" + textoAvataresSur + "|\n" + textoTopeAbajo;
        return texto;
    }
}




