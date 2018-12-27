/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.mapa;

import monopoly.excepciones.ExcepcionTrato;
import static monopoly.mapa.Juego.consola;

/**
 *
 * @author Usuario
 */
public class P4M extends Trato{
    private Propiedad propiedad1;
    private int dinero;

    public P4M(Propiedad propiedad1, int dinero) throws ExcepcionTrato{
        if(propiedad1 == null){
            throw new ExcepcionTrato("Propiedad1 nulo");
        }
        if(propiedad1.getPropietario().getNombre().equals("banca")){
            throw new ExcepcionTrato("Propiedad1 pertenece a la banca");
        }
        if(dinero < 0){
            throw new ExcepcionTrato("Dinero negativo");
        }
        this.propiedad1 = propiedad1;
        this.dinero = dinero;
    }

    public Propiedad getPropiedad1() {
        return propiedad1;
    }

    public void setPropiedad1(Propiedad propiedad1) {
        if (propiedad1 == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Propiedad1 nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.propiedad1 = propiedad1;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        if (dinero < 0) {
            consola.imprimir(Valor.ANSI_ROJO + "dinero negativo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.dinero = dinero;
    }
    
    @Override
    public String toString(){
        String cadena = "{\n "
                + "\t jugadorPropone: " + propiedad1.getPropietario().getNombre()
                + ",\n\t trato: cambiar (" + propiedad1.getNombre() + ", " + dinero + ")\n}";
        return cadena;
    }
}
