package Consola;

import Consola.Consola;

import java.util.Scanner;

public class ConsolaNormal implements Consola {

    @Override
    public void imprimir(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public String[] leer(String descripcion) {
        String comando;
        Scanner teclado = new Scanner(System.in);
        comando = teclado.nextLine();
        return comando.split(" ");
    }
}
