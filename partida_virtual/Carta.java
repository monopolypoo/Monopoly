package partida_virtual;

import java.util.ArrayList;

public class Carta {
    private ArrayList<Carta> cartasSuerte;
    private ArrayList<Carta> cartasComunidad;
    private int tipo;
    private String clase;

    public Carta(int num, String cartas) {
        if (cartas != null) {
            this.cartasSuerte = new ArrayList<>();
            this.cartasComunidad = new ArrayList<>();
            this.clase = null;
            if (cartas.equals("suerte")) {
                this.clase = cartas;
                if (num >= 1 && num <= 14)
                    this.tipo = num;
                else
                    this.tipo = 0;
                this.iniciarCartas(this.clase);
            } else if (cartas.equals("cajaDeComunidad")) {
                this.clase = cartas;
                if (num >= 1 && num <= 10) {
                    this.tipo = num;
                } else
                    this.tipo = 0;
                this.iniciarCartas(this.clase);
            }
        }
    }

    private void iniciarCartas(String tipo) {
        if (tipo.equals("suerte")) {
            Carta cartaSuerte1 = new Carta(1, "suerte");
            Carta cartaSuerte2 = new Carta(2, "suerte");
            Carta cartaSuerte3 = new Carta(3, "suerte");
            Carta cartaSuerte4 = new Carta(4, "suerte");
            Carta cartaSuerte5 = new Carta(5, "suerte");
            Carta cartaSuerte6 = new Carta(6, "suerte");
            Carta cartaSuerte7 = new Carta(7, "suerte");
            Carta cartaSuerte8 = new Carta(8, "suerte");
            Carta cartaSuerte9 = new Carta(9, "suerte");
            Carta cartaSuerte10 = new Carta(10, "suerte");
            Carta cartaSuerte11 = new Carta(11, "suerte");
            Carta cartaSuerte12 = new Carta(12, "suerte");
            Carta cartaSuerte13 = new Carta(13, "suerte");
            Carta cartaSuerte14 = new Carta(14, "suerte");

            this.cartasSuerte.add(cartaSuerte1);
            this.cartasSuerte.add(cartaSuerte2);
            this.cartasSuerte.add(cartaSuerte3);
            this.cartasSuerte.add(cartaSuerte4);
            this.cartasSuerte.add(cartaSuerte5);
            this.cartasSuerte.add(cartaSuerte6);
            this.cartasSuerte.add(cartaSuerte7);
            this.cartasSuerte.add(cartaSuerte8);
            this.cartasSuerte.add(cartaSuerte9);
            this.cartasSuerte.add(cartaSuerte10);
            this.cartasSuerte.add(cartaSuerte11);
            this.cartasSuerte.add(cartaSuerte12);
            this.cartasSuerte.add(cartaSuerte13);
            this.cartasSuerte.add(cartaSuerte14);
            
        } else if(tipo.equals("cajaDeComunidad")){
            Carta cartaComunidad1 = new Carta(1, "cajaDeComunidad");
            Carta cartaComunidad2 = new Carta(2, "cajaDeComunidad");
            Carta cartaComunidad3 = new Carta(3, "cajaDeComunidad");
            Carta cartaComunidad4 = new Carta(4, "cajaDeComunidad");
            Carta cartaComunidad5 = new Carta(5, "cajaDeComunidad");
            Carta cartaComunidad6 = new Carta(6, "cajaDeComunidad");
            Carta cartaComunidad7 = new Carta(7, "cajaDeComunidad");
            Carta cartaComunidad8 = new Carta(8, "cajaDeComunidad");
            Carta cartaComunidad9 = new Carta(9, "cajaDeComunidad");
            Carta cartaComunidad10 = new Carta(10, "cajaDeComunidad");

            this.cartasComunidad.add(cartaComunidad1);
            this.cartasComunidad.add(cartaComunidad2);
            this.cartasComunidad.add(cartaComunidad3);
            this.cartasComunidad.add(cartaComunidad4);
            this.cartasComunidad.add(cartaComunidad5);
            this.cartasComunidad.add(cartaComunidad6);
            this.cartasComunidad.add(cartaComunidad7);
            this.cartasComunidad.add(cartaComunidad8);
            this.cartasComunidad.add(cartaComunidad9);
            this.cartasComunidad.add(cartaComunidad10);

        }
    }

}

