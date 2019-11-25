import java.util.ArrayList;
import java.util.HashMap;

public final class Especial extends Casilla {
    private double precio;


    public Especial(String nombre, int posicion, String colorGrupo) {
        super(nombre, posicion, colorGrupo);

        if (posicion >= 0 && posicion <= 39) {
            switch (posicion) {
                case 0:
                    this.precio = Valor.VUELTA;
                    break;
                case 10:
                    this.precio = Valor.SAIR_CARCERE;
                    break;
                case 2:
                case 7:
                case 17:
                case 20:
                case 22:
                case 30:
                case 33:
                case 36:
                    this.precio = 0;
                    break;
                default:
                    System.out.println("ERROR creando la casilla especial!");
            }
        }
    }

    public double getPrecio() {
        return precio;
    }

    /*********************************************************************************************************
     ****     setPrecio no se implementa porque no se permite modificar este valor durante la partida     ****
     *********************************************************************************************************/

    public void sumarBote(double precio) {
        if (precio > 0 && super.getPosicion() == 20)
            this.precio += precio;
        else
            System.out.println("ERROR, el valor de esta casilla no se puede aumentar.");
    }

    public double vaciarBote() {
        double valor = this.precio;
        if (super.getPosicion() == 20) {
            this.precio = 0;
            return valor;
        }
        return 0;
    }

    @Override
    public String toString() {
        String texto;


        if (super.getPosicion() == 7 || super.getPosicion() == 17 || super.getPosicion() == 2 || super.getPosicion() == 22
                || super.getPosicion() == 33 || super.getPosicion() == 36 || super.getPosicion() == 30) {
            texto = "No hay información sobre esta casilla!";
        } else if (super.getPosicion() == 10) {
            texto = "";
            for (String[] nombre : super.getVecesCasilla().values()) {
                texto += "[" + nombre[0] + ", " + nombre[1] + "] ";
            }
            texto = "{\n\tsalir: " + 0.25 * Valor.VUELTA + ",\n\tjugadores: " + texto + "\n}";
        } else if (super.getPosicion() == 20) {
            texto = "[";
            for (String[] nombre : super.getVecesCasilla().values()) {
                texto += nombre[0] + " "; //mirar para ponerle la coma sin que se la ponga al ultimo tambien
            }
            texto += "]";
            texto = "{\n\tbote: " + this.precio + ",\n\tjugadores: " + texto + "\n}";
        } else if (super.getPosicion() == 0) {
            texto = "{\n\ttipo: especial,\n\tvalor a cobrar al pasar: " + Valor.VUELTA + ",\n}";
        } else
            texto = "{\n\tEsta casilla no es del tipo especial.\n}";

        return texto;
    }
}
