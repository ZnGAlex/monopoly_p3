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
public class P4P extends Trato{
    private Propiedad propiedad1;
    private Propiedad propiedad2;

    public P4P(Propiedad propiedad1, Propiedad propiedad2) throws ExcepcionTrato {
        if(propiedad1 == null){
            throw new ExcepcionTrato("propiedad1 nulo");
        }
        if(propiedad2 == null){
            throw new ExcepcionTrato("propiedad2 nulo");
        }
        if(propiedad1.getPropietario().getNombre().equals("banca")){
            throw new ExcepcionTrato("Propiedad1 pertenece a la banca");
        }
        if(propiedad2.getPropietario().getNombre().equals("banca")){
            throw new ExcepcionTrato("Propiedad2 pertencece a la banca");
        }
        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
    }

    public Propiedad getPropiedad1() {
        return propiedad1;
    }

    public void setPropiedad1(Propiedad propiedad1) {
        if (propiedad1 == null) {
            consola.imprimir(Valor.ANSI_ROJO + "propiedad1 nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.propiedad1 = propiedad1;
    }

    public Propiedad getPropiedad2() {
        return propiedad2;
    }

    public void setPropiedad2(Propiedad propiedad2) {
        if (propiedad2 == null) {
            consola.imprimir(Valor.ANSI_ROJO + "propiedad2 nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.propiedad2 = propiedad2;
    }
    
    @Override
    public String toString(){
        String cadena = "{\n "
                + "\t jugadorPropone: " + propiedad1.getPropietario().getNombre()
                + ",\n\t trato: cambiar (" + propiedad1.getNombre() + ", " + propiedad2.getNombre() + ")\n}";
        return cadena;
    }
       
}
