package Interfaces;

import ExcepcionesNull.*;
import ExcepcionesNumericas.ExcepcionesNumericas;
import ExcepcionesPartida.*;
import Monopoly.Menu;

import java.util.concurrent.atomic.AtomicReference;

public interface Comandos {
    public void CrearJugador(String[] comando) throws ExcepcionesNull, ExcepcionesComandos;

    public void Jugador();

    public void Listar(String[] comandos) throws ExcepcionesNull, ExcepcionesComandos, ExcepcionesNumericas;

    public void LanzarDados(String[] comando) throws InterruptedException, ExcepcionesDinero, ExcepcionesNull, ExcepcionesJugando, ExcepcionesComandos, ExcepcionesNumericas, ExcepcionesHipotecar, ExcepcionesDuenho;

    public void AcabarTurno(String[] comando) throws ExcepcionesJugando, ExcepcionesComandos;

    public void SalirCarcel(String[] comando) throws ExcepcionesDinero, ExcepcionesJugando, ExcepcionesComandos;

    public void Describir(String[] comando) throws ExcepcionesComandos;

    public void ComprarCasilla(String[] comando) throws ExcepcionesDuenho, ExcepcionesHipotecar, ExcepcionesDinero, ExcepcionesJugando, ExcepcionesComandos;

    public void Ver(String[] comando) throws ExcepcionesComandos;

    public void Edificar(String[] comando) throws ExcepcionesEdificios, ExcepcionesDuenho, ExcepcionesDinero, ExcepcionesJugando, ExcepcionesNumericas;

    public void HipotecarCasilla(String[] comando) throws ExcepcionesDuenho, ExcepcionesHipotecar, ExcepcionesEdificios, ExcepcionesJugando, ExcepcionesComandos;

    public void DeshipotecarCasilla(String[] comando) throws ExcepcionesDuenho, ExcepcionesDeshipotecar, ExcepcionesHipotecar, ExcepcionesDinero, ExcepcionesJugando, ExcepcionesComandos;

    public void VenderEdificio(String[] comando) throws ExcepcionesEdificios, ExcepcionesDuenho, ExcepcionesNull, ExcepcionesJugando, ExcepcionesComandos, ExcepcionesNumericas;

    public void Estadisticas(String[] comando) throws ExcepcionesComandos, ExcepcionesNull, ExcepcionesNumericas;

    public void CambiarModo(String[] comando) throws ExcepcionesComandos;

    public void Mover(String[] comando) throws ExcepcionesNumericas;

    public void Tratos(String[] comando) throws ExcepcionesComandos, ExcepcionesNumericas, ExcepcionesJugando, ExcepcionesDuenho;

    public void AceptarTrato(String[] comando) throws ExcepcionesComandos, ExcepcionesDinero, ExcepcionesJugando;

    public void TratosJugador() throws ExcepcionesNull;

    public void EliminarTrato(String[] comando) throws ExcepcionesComandos, ExcepcionesJugando;

    public void Stop();
}
