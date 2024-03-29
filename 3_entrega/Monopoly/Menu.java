package Monopoly;

import Casilla.*;
import ExcepcionesNull.*;
import ExcepcionesNumericas.ExcepcionesNumericas;
import ExcepcionesPartida.*;
import Jugador.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private Juego juego;
    private Jugador jugadorActual;
    private Jugador jugadorTurnoSiguiente;
    private boolean sigueTurno;
    private boolean dadosLanzados;
    private boolean poderComprar;
    private boolean esLeerArchivo;
    private boolean partidaEmpezada;
    private int contadorDobles;
    private boolean combinado;

    public Menu() throws InterruptedException {
        this.juego = new Juego(this);
        this.sigueTurno = false;
        this.contadorDobles = 0;
        this.dadosLanzados = false;
        this.esLeerArchivo = false;
        this.partidaEmpezada = false;
        this.combinado = false;
        boolean seguir = true;
        String[] comando, comando2;

        comando2 = Juego.consola.leer("Desea leer los comandos de un archivo (si/no): ");

        //abrir archivo de los comandos
        BufferedReader buffRead = null;

        if (comando2[0].toLowerCase().equals("si")) {
            buffRead = abrirArchivo();
            this.esLeerArchivo = true;
            Juego.consola.imprimir("Si necesita introducir un comando en el medio de la ejecución automática, podrá hacerlo " +
                    "en cada stop, tecleando 'si', donde le aparecerá la entrada típica por línea de comandos.");
        }

        while (seguir) { //mirar cuando acabar la partida

            if (this.esLeerArchivo) {
                //leer comandos del archivo
                if (!this.combinado) {
                    comando = leerComandoArchivo(buffRead);
                } else {
                    comando = Juego.consola.leer("");
                    this.combinado = false;
                }
            } else {
                //leer comandos por consola
                comando = Juego.consola.leer("$> ");
            }

            switch (comando[0].toLowerCase()) {
                case "crear":
                    try {
                        this.juego.CrearJugador(comando);
                    } catch (ExcepcionesNull excepcionesNull) {
                        Juego.consola.imprimir(excepcionesNull.getMessage());
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    }
                    break;

                case "jugador":
                    this.juego.Jugador();
                    break;

                case "listar":
                    try {
                        this.juego.Listar(comando);
                    } catch (ExcepcionesNull excepcionNull) {
                        Juego.consola.imprimir(excepcionNull.getMessage());
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    } catch (ExcepcionesNumericas excepcionesNumericas){
                        Juego.consola.imprimir(excepcionesNumericas.getMessage());
                    }
                    break;

                case "lanzar":
                    try {
                        this.juego.LanzarDados(comando);
                    } catch (ExcepcionesDinero excepcionesDinero) {
                        Juego.consola.imprimir(excepcionesDinero.getMessage());
                    } catch (ExcepcionesNull excepcionesNull) {
                        Juego.consola.imprimir(excepcionesNull.getMessage());
                    } catch (ExcepcionesJugando excepcionesJugando){
                        Juego.consola.imprimir(excepcionesJugando.getMessage());
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    } catch (ExcepcionesNumericas excepcionesNumericas){
                        Juego.consola.imprimir(excepcionesNumericas.getMessage());
                    } catch (ExcepcionesHipotecar excepcionesHipotecar){
                        Juego.consola.imprimir(excepcionesHipotecar.getMessage());
                    } catch (ExcepcionesDuenho excepcionesDuenho){
                        Juego.consola.imprimir(excepcionesDuenho.getMessage());
                    }
                    break;

                case "acabar":
                    try {
                        this.juego.AcabarTurno(comando);
                    } catch (ExcepcionesJugando excepcionesJugando){
                        Juego.consola.imprimir(excepcionesJugando.getMessage());
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    }
                    break;

                case "salir":
                    try {
                        this.getJuego().SalirCarcel(comando);
                    } catch (ExcepcionesDinero excepcionDinero) {
                        Juego.consola.imprimir(excepcionDinero.getMessage());
                    } catch (ExcepcionesJugando excepcionesJugando){
                        Juego.consola.imprimir(excepcionesJugando.getMessage());
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    }
                    break;

                case "describir":
                    try {
                        this.juego.Describir(comando);
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    }
                    break;

                case "comprar":
                    try {
                        this.juego.ComprarCasilla(comando);
                    } catch (ExcepcionesDinero excepcionesDinero) {
                        Juego.consola.imprimir(excepcionesDinero.getMessage());
                    } catch (ExcepcionesDuenho excepcionesDuenho) {
                        Juego.consola.imprimir(excepcionesDuenho.getMessage());
                    } catch (ExcepcionesHipotecar excepcionesHipotecar) {
                        Juego.consola.imprimir(excepcionesHipotecar.getMessage());
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    } catch (ExcepcionesJugando excepcionesJugando){
                        Juego.consola.imprimir(excepcionesJugando.getMessage());
                    }
                    break;

                case "ver":
                    try {
                        this.juego.Ver(comando);
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    }
                    break;

                case "edificar":
                    try {
                        this.juego.Edificar(comando);
                    } catch (ExcepcionesEdificios excepcionesEdificios) {
                        Juego.consola.imprimir(excepcionesEdificios.getMessage());
                    } catch (ExcepcionesDinero excepcionesDinero) {
                        Juego.consola.imprimir(excepcionesDinero.getMessage());
                    } catch (ExcepcionesDuenho excepcionesDuenho) {
                        Juego.consola.imprimir(excepcionesDuenho.getMessage());
                    } catch (ExcepcionesJugando excepcionesJugando){
                        Juego.consola.imprimir(excepcionesJugando.getMessage());
                    } catch (ExcepcionesNumericas excepcionesNumericas){
                        Juego.consola.imprimir(excepcionesNumericas.getMessage());
                    }
                    break;

                case "hipotecar":
                    try {
                        this.juego.HipotecarCasilla(comando);
                    } catch (ExcepcionesEdificios excepcionesEdificios) {
                        Juego.consola.imprimir(excepcionesEdificios.getMessage());
                    } catch (ExcepcionesHipotecar excepcionesHipotecar) {
                        Juego.consola.imprimir(excepcionesHipotecar.getMessage());
                    } catch (ExcepcionesDuenho excepcionesDuenho) {
                        Juego.consola.imprimir(excepcionesDuenho.getMessage());
                    } catch (ExcepcionesJugando excepcionesJugando){
                        Juego.consola.imprimir(excepcionesJugando.getMessage());
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    }
                    break;

                case "deshipotecar":
                    try {
                        this.juego.DeshipotecarCasilla(comando);
                    } catch (ExcepcionesDinero excepcionesDinero) {
                        Juego.consola.imprimir(excepcionesDinero.getMessage());
                    } catch (ExcepcionesHipotecar excepcionesHipotecar) {
                        Juego.consola.imprimir(excepcionesHipotecar.getMessage());
                    } catch (ExcepcionesDuenho excepcionesDuenho) {
                        Juego.consola.imprimir(excepcionesDuenho.getMessage());
                    } catch (ExcepcionesDeshipotecar excepcionesDeshipotecar) {
                        Juego.consola.imprimir(excepcionesDeshipotecar.getMessage());
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    } catch (ExcepcionesJugando excepcionesJugando){
                        Juego.consola.imprimir(excepcionesJugando.getMessage());
                    }
                    break;

                case "vender":
                    try {
                        this.juego.VenderEdificio(comando);
                    } catch (ExcepcionesEdificios excepcionesEdificios) {
                        Juego.consola.imprimir(excepcionesEdificios.getMessage());
                    } catch (ExcepcionesNull excepcionesNull) {
                        Juego.consola.imprimir(excepcionesNull.getMessage());
                    } catch (ExcepcionesDuenho excepcionesDuenho) {
                        Juego.consola.imprimir(excepcionesDuenho.getMessage());
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    } catch (ExcepcionesJugando excepcionesJugando){
                        Juego.consola.imprimir(excepcionesJugando.getMessage());
                    } catch (ExcepcionesNumericas excepcionesNumericas){
                        Juego.consola.imprimir(excepcionesNumericas.getMessage());
                    }
                    break;

                case "estadisticas":
                    try {
                        this.juego.Estadisticas(comando);
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    } catch (ExcepcionesNull excepcionesNull) {
                        Juego.consola.imprimir(excepcionesNull.getMessage());
                    } catch (ExcepcionesNumericas excepcionesNumericas){
                        Juego.consola.imprimir(excepcionesNumericas.getMessage());
                    }
                    break;

                case "trato":
                    try {
                        this.juego.Tratos(comando);
                    } catch (ExcepcionesDuenho excepcionesDuenho) {
                        Juego.consola.imprimir(excepcionesDuenho.getMessage());
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    } catch (ExcepcionesJugando excepcionesJugando){
                        Juego.consola.imprimir(excepcionesJugando.getMessage());
                    } catch (ExcepcionesNumericas excepcionesNumericas){
                        Juego.consola.imprimir(excepcionesNumericas.getMessage());
                    }
                    break;

                case "aceptar":
                    try {
                        this.juego.AceptarTrato(comando);
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    } catch (ExcepcionesDinero excepcionesDinero){
                        Juego.consola.imprimir(excepcionesDinero.getMessage());
                    } catch (ExcepcionesJugando excepcionesJugando){
                        Juego.consola.imprimir(excepcionesJugando.getMessage());
                    }
                    break;

                case "tratos":
                    try {
                        this.juego.TratosJugador();
                    } catch (ExcepcionesNull excepcionesNull){
                        Juego.consola.imprimir(excepcionesNull.getMessage());
                    }
                    break;

                case "eliminar":
                    try {
                        this.juego.EliminarTrato(comando);
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    } catch (ExcepcionesJugando excepcionesJugando){
                        Juego.consola.imprimir(excepcionesJugando.getMessage());
                    }
                    break;

                case "cambiar":
                    try {
                        this.juego.CambiarModo(comando);
                    } catch (ExcepcionesComandos excepcionesComandos){
                        Juego.consola.imprimir(excepcionesComandos.getMessage());
                    }
                    break;

                case "mover":
                    try {
                        this.juego.Mover(comando);
                    } catch (ExcepcionesNumericas excepcionesNumericas){
                        Juego.consola.imprimir(excepcionesNumericas.getMessage());
                    }
                    break;

                case "abandonar":
                    Juego.consola.imprimir("Abandonando partida...");
                    seguir = false;
                    break;

                case "stop":
                    this.juego.Stop();
                    break;

                default:
                    Juego.consola.imprimir("Comando incorrecto. Para ver los comandos disponibles escriba: Ver Comandos");
            }
        }

    }

    public int numeroJugadores() {
        return this.juego.getJugadores().size();
    }

    public boolean isPartidaEmpezada() {
        return partidaEmpezada;
    }

    public void setPartidaEmpezada(boolean partidaEmpezada) {
        this.partidaEmpezada = partidaEmpezada;
    }

    public void setPoderComprar(boolean poderComprar) {
        this.poderComprar = poderComprar;
    }

    public BufferedReader abrirArchivo() {
        BufferedReader buffRead = null;
        try {
            String directorio = "/Users/davidmohedano/Documents/USC/Segundo/Primer cuatrimestre/POO/proyecto1/src/";
            FileReader fileRead = new FileReader(directorio + "comandos.txt");
            buffRead = new BufferedReader(fileRead);
        } catch (FileNotFoundException notFound) {
            Juego.consola.imprimir(notFound.getMessage());
            System.exit(0);
        }
        return buffRead;
    }

    public String[] leerComandoArchivo(BufferedReader buffRead) {
        String comandoEntero = null;
        String[] comando;
        try {
            comandoEntero = buffRead.readLine();
        } catch (IOException io) {
            Juego.consola.imprimir(io.getMessage());
        }
        if (comandoEntero != null) {
            Juego.consola.imprimir("$> " + comandoEntero);
            comando = comandoEntero.split(" ");
        } else {
            Juego.consola.imprimir("ERROR leyendo el archivo. Se acabó de leer el archivo, introduce tus comandos.");
            this.esLeerArchivo = false;
            comando = Juego.consola.leer("$> ");
        }
        return comando;
    }

    public Jugador getJugadorTurnoSiguiente(Jugador jugadorActual) {
        int i = 0;
        int total = this.juego.getJugadores().size() - 1;
        for (Jugador jug : this.juego.getJugadores()) {
            if (jug.getNombre().equals(jugadorActual.getNombre())) {
                if (i == total) {
                    return this.juego.getJugadores().get(0);
                } else {
                    i++;
                    return this.juego.getJugadores().get(i);
                }
            }
            i++;
        }
        return null;
    }

    public boolean isDadosLanzados() {
        return dadosLanzados;
    }

    public void setDadosLanzados(boolean dadosLanzados) {
        this.dadosLanzados = dadosLanzados;
    }

    public void setSigueTurno(boolean sigueTurno) {
        this.sigueTurno = sigueTurno;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public void setJugadorTurnoSiguiente(Jugador jugadorTurnoSiguiente) {
        this.jugadorTurnoSiguiente = jugadorTurnoSiguiente;
    }

    public void calcularJugadores() {
        this.jugadorActual = this.jugadorTurnoSiguiente;
        this.jugadorTurnoSiguiente = getJugadorTurnoSiguiente(this.jugadorActual);
        if (this.jugadorTurnoSiguiente == null) {
            Juego.consola.imprimir("ERROR al calcular el jugador siguinte.");
        }
    }

    public Juego getJuego() {
        return this.juego;
    }

    public boolean isSigueTurno() {
        return sigueTurno;
    }

    public boolean isPoderComprar() {
        return poderComprar;
    }

    public boolean isCombinado() {
        return combinado;
    }

    public void setCombinado(boolean combinado) {
        this.combinado = combinado;
    }

    public int getContadorDobles() {
        return this.contadorDobles;
    }

    public void setContadorDobles(int contadorDobles) {
        this.contadorDobles = contadorDobles;
    }
}