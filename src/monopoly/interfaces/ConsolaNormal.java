package monopoly.interfaces;

import java.util.Scanner;

public class ConsolaNormal implements Consola{
    @Override
    public void imprimir(String mensaje) {
        System.out.print(mensaje);
    }

    @Override
    public String leer(String descripcion) {
        imprimir(descripcion);
        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();

        return entrada;
    }
}
