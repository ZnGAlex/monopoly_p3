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
public class M4P extends Trato{
    private Propiedad propiedad2;
    private int dinero;

    public M4P(Propiedad propiedad2, int dinero) throws ExcepcionTrato{
        if(propiedad2 == null){
            throw new ExcepcionTrato("Propiedad2 nulo");
        }
        if(propiedad2.getPropietario().getNombre().equals("banca")){
            throw new ExcepcionTrato("Propiedad2 pertenece a la banca");
        }
        if(dinero < 0){
            throw new ExcepcionTrato("Dinero negativo");
        }
        this.propiedad2 = propiedad2;
        this.dinero = dinero;
    }

    public Propiedad getPropiedad2() {
        return propiedad2;
    }

    public void setPropiedad2(Propiedad propiedad2) {
        if (propiedad2 == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Propiedad2 nulo." + Valor.ANSI_RESET);
            System.exit(2);
        }
        this.propiedad2 = propiedad2;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        if (dinero < 0) {
            consola.imprimir(Valor.ANSI_ROJO + "dinero negativo." + Valor.ANSI_RESET);
            System.exit(2);
        }
        this.dinero = dinero;
    }
    
    @Override
    public String toString(){
        String cadena = "{\n "
                + "\t jugadorPropone: " + propiedad2.getPropietario().getNombre()
                + ",\n\t trato: cambiar (" + dinero + ", " + propiedad2.getNombre() + ")\n}";
        return cadena;
    }
    
}
