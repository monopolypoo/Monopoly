import java.util.Random;

public class Dado {
    private int dado1;
    private int dado2;
    private boolean iguales;
    private int posActual;
    private int posSiguiente;
    private int dadoTotal;
    private int vecesPelota;
    private int numeroPelota;
    private boolean seguirPelota;
    private String texto;

    public Dado() {
        this.dado1 = 0;
        this.dado2 = 0;
        this.iguales = false;
        this.posActual = 0;
        this.posSiguiente = 0;
        this.dadoTotal = 0;
        this.vecesPelota = 0;
        this.numeroPelota = 0;
        this.seguirPelota = false;
        this.texto = "";
    }

    public Dado(Dado dado) throws InterruptedException {
        dado.generarValorDados(dado);
    }
    // NO se permite ponerle valor predeterminado a ningún campo del dado ya que sería trucar la "aletoriedad" de
    // los mismo    // los mismos


    // Tampoco se permite saber el valor de los dados por separado, no hay que saberlo, ni los campos calculados
    // de posActual y posSiguiente

    public void generarValorDados(Dado dado) throws InterruptedException {
        Random ale = new Random(System.currentTimeMillis()); //pone una semilla nueva de cada vez

        dado.dado1 = (int) (Math.random() * 6 + 1);
        Thread.sleep(50);
        //ale = new Random(System.currentTimeMillis());
        dado.dado2 = (int) (Math.random() * 6 + 1);

    }

    public int getDadoTotal() {
        return this.dadoTotal;
    }

    public int getVecesPelota() {
        return vecesPelota;
    }

    public void setVecesPelota(int vecesPelota) {
        this.vecesPelota = vecesPelota;
    }

    public int getNumeroPelota() {
        return numeroPelota;
    }

    public void setNumeroPelota(int numeroPelota) {
        this.numeroPelota = numeroPelota;
    }

    public boolean isSeguirPelota() {
        return seguirPelota;
    }

    public void setSeguirPelota(boolean seguirPelota) {
        this.seguirPelota = seguirPelota;
    }

    public int lanzarLosDados() throws InterruptedException {
        this.iguales = false;
        this.dado1 = (int) Math.floor(Math.random() * 6 + 1);
        Thread.sleep(50);
        this.dado2 = (int) Math.floor(Math.random() * 6 + 1);
        this.iguales = this.dado2 == this.dado1;
        return this.dado1 + this.dado2;
    }

    public void lanzarDados(Jugador jugador, Taboleiro taboleiro, Menu menu) throws InterruptedException {

        this.dadoTotal = lanzarLosDados();
        jugador.sumarVecesdados();

        this.posActual = jugador.getAvatar().getCasilla().getPosicion();
        taboleiro.getCasillaPosicion(this.posActual).eliminarAvatar(jugador.getAvatar().getId());

        jugador.getAvatar().mover(taboleiro, menu);
    }

    public boolean getIguales() {
        return this.iguales;
    }

    public int getPosActual() {
        return posActual;
    }

    public void setPosActual(int posActual) {
        if (posActual >= 0 && posActual < 40)
            this.posActual = posActual;
    }

    public int getPosSiguiente() {
        return posSiguiente;
    }

    public void setPosSiguiente(int posSiguiente) {
        if (posSiguiente >= 0 && posSiguiente < 40)
            this.posSiguiente = posSiguiente;
    }

    public String textoAuxLanzarDados(Taboleiro taboleiro, Jugador jugador, Menu menu, String texto) {
        String aux;
        if (taboleiro != null && jugador != null && texto != null)
            if (taboleiro.getCasillaPosicion(this.posSiguiente) instanceof Accion) {

                if (taboleiro.getCasillaPosicion(this.posSiguiente) instanceof AccionSuerte) {
                    texto += "\n" + jugador.getNombre() + ", elige una carta de suerte (1-13): ";
                    aux = "Acción: " + ((AccionSuerte) taboleiro.getCasillaPosicion(this.posSiguiente)).getCarta().getTexto() + "\n";
                } else {
                    texto += "\n" + jugador.getNombre() + ", elige una carta de caja de comunidad (1-10): ";
                    aux = "Acción: " + ((AccionCajaComunidad) taboleiro.getCasillaPosicion(this.posSiguiente)).getCarta().getTexto() + "\n";
                }
                System.out.print(texto);
                menu.leerComando();
                texto = aux;
            }

        return texto;
    }

    public String textoLanzarDados(Taboleiro taboleiro, Jugador jugador, Menu menu) {
        String texto = taboleiro + "\n";
        int sumaDados = this.dado1 + this.dado2;
        if ((this.posSiguiente == 7) || (this.posSiguiente == 22) || (this.posSiguiente == 36)) {
            texto = "";
        } else if ((this.posSiguiente == 2) || (this.posSiguiente == 17) || (this.posSiguiente == 33)) {
            texto = "";
        }
        if (jugador.getAvatar().isModoAvanzado() && jugador.getAvatar() instanceof Coche && sumaDados <= 4) {
            texto += Valor.RESET + "El avatar " + menu.getJugadorActual().getAvatar().getId() + " retrocede " + sumaDados +
                    " posiciones, desde " + taboleiro.getCasillaPosicion(this.posActual).getNombreSinEspacio() +
                    " hasta " + taboleiro.getCasillaPosicion(this.posSiguiente).getNombreSinEspacio() + ". " + this.texto;
        } else {
            texto += Valor.RESET + "El avatar " + menu.getJugadorActual().getAvatar().getId() + " avanza " + sumaDados +
                    " posiciones, desde " + taboleiro.getCasillaPosicion(this.posActual).getNombreSinEspacio() +
                    " hasta " + taboleiro.getCasillaPosicion(this.posSiguiente).getNombreSinEspacio() + ". ";
        }
        texto = textoAuxLanzarDados(taboleiro, jugador, menu, texto);

        return texto;
    }
}