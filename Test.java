import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        // Casillas UDC Ferrol (SUR)
        Casilla saida = new Casilla("Saída", "especial", 0, 0);
        Casilla solar1 = new Casilla("Enx. Eléctrica", "solar", 1, 100000);
        Casilla caixa1 = new Casilla("Caixa de comunidade", "caixa", 2, 0);
        Casilla solar2 = new Casilla("Enx. Mecánica", "solar", 3, 100000);
        Casilla imp1 = new Casilla("Imposto Militar", "imposto", 4,300000);
        Casilla trans1 = new Casilla("Porto de Ferrol", "transporte", 5, 300000);
        Casilla solar3 = new Casilla("Podoloxía", "solar", 6, 130000);
        Casilla sorte1 = new Casilla("Sorte", "sorte", 7, 0);
        Casilla solar4 = new Casilla("Humanidades", "solar", 8, 130000);
        Casilla solar5 = new Casilla("Información e Documentación", "solar", 9, 130000);

        // Casillas UDC Coruña (OESTE)
        Casilla carcere = new Casilla("Cárcere", "especial", 10, 0);
        Casilla solar6 = new Casilla("Fisioterapia", "solar", 11, 170000);
        Casilla serv1 = new Casilla("Telecomunicacións", "servizos", 12, 0); // A outra é electrica
        Casilla solar7 = new Casilla("Turismo", "solar", 13, 170000);
        Casilla solar8 = new Casilla("Náutica", "solar", 14, 170000);
        Casilla trans2 = new Casilla("Base Espacial da Coruña", "transporte", 15, 300000);
        Casilla solar9 = new Casilla("Arquitectura", "solar", 16, 220000);
        Casilla caixa2 = new Casilla("Caixa de comunidade", "caixa", 17, 0);
        Casilla solar10 = new Casilla("Enx. Camiños", "solar", 18, 220000);
        Casilla solar11 = new Casilla("FIC", "solar", 19, 220000);

        // Casillas USC Santiago (NORTE)
        Casilla parking = new Casilla("Parking", "especial", 20, 0);
        Casilla solar12 = new Casilla("Historia", "solar", 21, 290000);
        Casilla sorte2 = new Casilla("Sorte", "sorte", 22, 0);
        Casilla solar13 = new Casilla("Filoloxía", "solar", 23, 290000);
        Casilla solar14 = new Casilla("Ciencias da Educación", "solar", 24, 290000);
        Casilla trans3 = new Casilla("Aeroporto da Valleta", "transporte", 25, 300000);
        Casilla solar15 = new Casilla("Medicina", "solar", 26, 380000);
        Casilla solar16 = new Casilla("Enfermería", "solar", 27, 380000);
        Casilla serv2 = new Casilla("Eléctrica", "servizos", 28, 0);
        Casilla solar17 = new Casilla("ADE", "solar", 29, 380000);

        // Casillas USC Santiago (LESTE)
        Casilla irCarcere = new Casilla("Ir ao cárcere", "especial", 30, 0);
        Casilla solar18 = new Casilla("Farmacia", "solar", 31, 500000);
        Casilla solar19 = new Casilla("Bioloxía", "solar", 32, 500000);
        Casilla caixa3 = new Casilla("Caixa de comunidade", "solar", 33, 0);
        Casilla solar20 = new Casilla("Química", "solar", 34, 500000);
        Casilla trans4 = new Casilla("Estación de tren de Santiago", "transporte", 35, 300000);
        Casilla sorte3 = new Casilla("Sorte", "sorte", 36, 0);
        Casilla solar21 = new Casilla("Física", "solar", 37, 650000);
        Casilla imp2 = new Casilla("Imposto da luz", "imposto", 38, 150000);
        Casilla solar22 = new Casilla("ETSE", "solar", 39, 650000);

        //Creamos los grupos
        ArrayList<Casilla> casillasGrupo1 = new ArrayList<>();
        casillasGrupo1.add(solar1);
        casillasGrupo1.add(solar2);
        Grupo grupo1 = new Grupo(casillasGrupo1, 1, "azul", "sur");
        ArrayList<Casilla> casillasGrupo2 = new ArrayList<>();
        casillasGrupo2.add(solar3);
        casillasGrupo2.add(solar4);
        casillasGrupo2.add(solar5);
        Grupo grupo2 = new Grupo(casillasGrupo2, 2, "amarillo", "sur");
        ArrayList<Casilla> casillasGrupo3 = new ArrayList<>();
        casillasGrupo3.add(solar6);
        casillasGrupo3.add(solar7);
        casillasGrupo3.add(solar8);
        Grupo grupo3 = new Grupo(casillasGrupo3, 3, "violeta", "oeste");
        ArrayList<Casilla> casillasGrupo4 = new ArrayList<>();
        casillasGrupo4.add(solar9);
        casillasGrupo4.add(solar10);
        casillasGrupo4.add(solar11);
        Grupo grupo4 = new Grupo(casillasGrupo4, 4, "naranja", "oeste");
        ArrayList<Casilla>  casillasGrupo5 = new ArrayList<>();
        casillasGrupo5.add(solar12);
        casillasGrupo5.add(solar13);
        casillasGrupo5.add(solar14);
        Grupo grupo5 = new Grupo(casillasGrupo5,5, "verde", "norte");
        ArrayList<Casilla>  casillasGrupo6 = new ArrayList<>();
        casillasGrupo6.add(solar15);
        casillasGrupo6.add(solar16);
        casillasGrupo6.add(solar17);
        Grupo grupo6 = new Grupo(casillasGrupo6,6, "rojo", "norte");
        ArrayList<Casilla>  casillasGrupo7 = new ArrayList<>();
        casillasGrupo7.add(solar18);
        casillasGrupo7.add(solar19);
        casillasGrupo7.add(solar20);
        Grupo grupo7 = new Grupo(casillasGrupo7,7, "marron", "leste");
        ArrayList<Casilla>  casillasGrupo8 = new ArrayList<>();
        casillasGrupo8.add(solar21);
        casillasGrupo8.add(solar22);
        Grupo grupo8 = new Grupo(casillasGrupo8,8, "gris", "leste");
    }
}
