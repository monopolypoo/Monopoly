import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        // Casillas UDC Ferrol (SUR)
        Casilla saida = new Casilla( "Saída           ", "especial", 0, 0, "\033[0;90m");
        Casilla solar1 = new Casilla("Enx. Eléctrica  ", "solar", 1, 100000, "\033[0;34m");
        Casilla caixa1 = new Casilla("Caixa Comunidade", "caixa", 2, 0, "\033[1;30m");
        Casilla solar2 = new Casilla("Enx. Mecánica   ", "solar", 3, 100000, "\033[0;34m");
        Casilla imp1 = new Casilla(  "Imp. Militar    ", "imposto", 4,300000, "\033[1;31m");
        Casilla trans1 = new Casilla("Pto. Ferrol     ", "transporte", 5, 300000, "\033[1;90m");
        Casilla solar3 = new Casilla("Podoloxía       ", "solar", 6, 130000, "\033[0;33m");
        Casilla sorte1 = new Casilla("Sorte           ", "sorte", 7, 0, "\033[1;30m");
        Casilla solar4 = new Casilla("Humanidades     ", "solar", 8, 130000, "\033[0;33m");
        Casilla solar5 = new Casilla("Documentación   ", "solar", 9, 130000, "\033[0;33m");

        // Casillas UDC Coruña (OESTE)
        Casilla carcere = new Casilla("Cárcere         ", "especial", 10, 0, "\033[0;90m");
        Casilla solar6 = new Casilla( "Fisioterapia    ", "solar", 11, 170000, "\033[0;35m");
        Casilla serv1 = new Casilla(  "Serv. Teleco    ", "servizos", 12, 0, "\033[0;96m");
        Casilla solar7 = new Casilla( "Turismo         ", "solar", 13, 170000, "\033[0;35m");
        Casilla solar8 = new Casilla( "Náutica         ", "solar", 14, 170000, "\033[0;35m");
        Casilla trans2 = new Casilla( "Base Esp. Coruña", "transporte", 15, 300000, "\033[1;90m");
        Casilla solar9 = new Casilla( "Arquitectura    ", "solar", 16, 220000, "\033[0;93m");
        Casilla caixa2 = new Casilla( "Caixa Comunidade", "caixa", 17, 0, "\033[1;30m");
        Casilla solar10 = new Casilla("Enx. Camiños    ", "solar", 18, 220000, "\033[0;93m");
        Casilla solar11 = new Casilla("FIC             ", "solar", 19, 220000, "\033[0;93m");

        // Casillas USC Santiago (NORTE)
        Casilla parking = new Casilla("Parking         ", "especial", 20, 0, "\033[0;90m");
        Casilla solar12 = new Casilla("Historia        ", "solar", 21, 290000, "\033[0;32m");
        Casilla sorte2 = new Casilla( "Sorte           ", "sorte", 22, 0, "\033[1;30m");
        Casilla solar13 = new Casilla("Filoloxía       ", "solar", 23, 290000, "\033[0;32m");
        Casilla solar14 = new Casilla("C. da Educación ", "solar", 24, 290000, "\033[0;32m");
        Casilla trans3 = new Casilla( "Apto. A Valleta ", "transporte", 25, 300000, "\033[1;90m");
        Casilla solar15 = new Casilla("Medicina        ", "solar", 26, 380000, "\033[0;31m");
        Casilla solar16 = new Casilla("Enfermería      ", "solar", 27, 380000, "\033[0;31m");
        Casilla serv2 = new Casilla(  "Serv. Eléctrico ", "servizos", 28, 0, "\033[0;96m");
        Casilla solar17 = new Casilla("ADE             ", "solar", 29, 380000, "\033[0;31m");

        // Casillas USC Santiago (LESTE)
        Casilla irCarcere = new Casilla("Ir Cárcere      ", "especial", 30, 0, "\033[0;90m");
        Casilla solar18 = new Casilla(  "Farmacia        ", "solar", 31, 500000, "\033[0;92m");
        Casilla solar19 = new Casilla(  "Bioloxía        ", "solar", 32, 500000, "\033[0;92m");
        Casilla caixa3 = new Casilla(   "Caixa Comunidade", "solar", 33, 0, "\033[1;30m");
        Casilla solar20 = new Casilla(  "Química         ", "solar", 34, 500000, "\033[0;92m");
        Casilla trans4 = new Casilla(   "E. tren Santiago", "transporte", 35, 300000, "\033[1;90m");
        Casilla sorte3 = new Casilla(   "Sorte           ", "sorte", 36, 0, "\033[1;30m");
        Casilla solar21 = new Casilla(  "Física          ", "solar", 37, 650000, "\033[0;36m");
        Casilla imp2 = new Casilla(     "Imp. da luz     ", "imposto", 38, 150000, "\033[1;31m");
        Casilla solar22 = new Casilla(  "ETSE            ", "solar", 39, 650000, "\033[0;36m");

        //Creamos los grupos
        ArrayList<Casilla> casillasGrupo1 = new ArrayList<>();
        casillasGrupo1.add(solar1);
        casillasGrupo1.add(solar2);
        Grupo grupo1 = new Grupo(casillasGrupo1, 1, "\033[0;34m", "sur");
        ArrayList<Casilla> casillasGrupo2 = new ArrayList<>();
        casillasGrupo2.add(solar3);
        casillasGrupo2.add(solar4);
        casillasGrupo2.add(solar5);
        Grupo grupo2 = new Grupo(casillasGrupo2, 2, "\033[0;33m", "sur");
        ArrayList<Casilla> casillasGrupo3 = new ArrayList<>();
        casillasGrupo3.add(solar6);
        casillasGrupo3.add(solar7);
        casillasGrupo3.add(solar8);
        Grupo grupo3 = new Grupo(casillasGrupo3, 3, "\033[0;35m", "oeste");
        ArrayList<Casilla> casillasGrupo4 = new ArrayList<>();
        casillasGrupo4.add(solar9);
        casillasGrupo4.add(solar10);
        casillasGrupo4.add(solar11);
        Grupo grupo4 = new Grupo(casillasGrupo4, 4, "\033[0;93m", "oeste");
        ArrayList<Casilla>  casillasGrupo5 = new ArrayList<>();
        casillasGrupo5.add(solar12);
        casillasGrupo5.add(solar13);
        casillasGrupo5.add(solar14);
        Grupo grupo5 = new Grupo(casillasGrupo5,5, "\033[0;32m", "norte");
        ArrayList<Casilla>  casillasGrupo6 = new ArrayList<>();
        casillasGrupo6.add(solar15);
        casillasGrupo6.add(solar16);
        casillasGrupo6.add(solar17);
        Grupo grupo6 = new Grupo(casillasGrupo6,6, "\033[0;31m", "norte");
        ArrayList<Casilla>  casillasGrupo7 = new ArrayList<>();
        casillasGrupo7.add(solar18);
        casillasGrupo7.add(solar19);
        casillasGrupo7.add(solar20);
        Grupo grupo7 = new Grupo(casillasGrupo7,7, "\033[0;92m", "leste");
        ArrayList<Casilla>  casillasGrupo8 = new ArrayList<>();
        casillasGrupo8.add(solar21);
        casillasGrupo8.add(solar22);
        Grupo grupo8 = new Grupo(casillasGrupo8,8, "\033[0;36m", "leste");
        //crear grupo de servicio, transportes, impuestos y especiales

        /*System.out.println(grupo1);
        Jugador jugador1 = new Jugador("Pepe", "Esfinge", 100000);
        solar1.setDuenho(jugador1);
        System.out.println(solar1.pertence(jugador1));
        Jugador jugador2 = new Jugador("Dani", "Coche", 100000);
        System.out.println(solar1.pertence(jugador2));
         */

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
        Grupo ladoNorte = new Grupo(casillasLadoNorte,9, "norte");

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
        Grupo ladoOeste = new Grupo(casillasLadoOeste,10, "oeste");

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
        Grupo ladoLeste = new Grupo(casillasLadoLeste,11, "leste");

        ArrayList<Casilla>  casillasLadoSur = new ArrayList<>();
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
        Grupo ladoSur = new Grupo(casillasLadoSur,12, "sur");

        Taboleiro taboleiro = new Taboleiro(ladoNorte, ladoOeste, ladoLeste, ladoSur);

        System.out.println(taboleiro);

    }
}
