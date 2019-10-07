import java.util.Random;

public class Dado {
    private int dado1;
    private int dado2;

    void generarValorDados() throws InterruptedException {
        Random ale = new Random(System.currentTimeMillis()); //pone una semilla nueva de cada vez

        this.dado1 = ale.nextInt(1) + 5;
        Thread.sleep(50);
        ale = new Random(System.currentTimeMillis());
        this.dado2 = ale.nextInt(1) + 5;

    }

    public String toString() {
        String texto;

        if (this.dado2 != this.dado1) {
            texto = String.valueOf(this.dado1 + this.dado2);
        } else {
            texto = "O valor do primeiro dado é: " + this.dado1 + "O valor do segundo dado é: " + this.dado2;
        }


        return texto;
    }


}
