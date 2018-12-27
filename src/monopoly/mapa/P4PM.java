/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.mapa;

import monopoly.excepciones.ExcepcionTrato;

/**
 *
 * @author Usuario
 */
public class P4PM extends P4PandM{
    
    public P4PM(Propiedad propiedad1, Propiedad propiedad2, int dinero) throws ExcepcionTrato {
        super(propiedad1, propiedad2, dinero);
    }
    
    @Override
    public String toString(){
        String cadena = "{\n "
                + "\t jugadorPropone: " + getPropiedad1().getPropietario().getNombre()
                + ",\n\t trato: cambiar (" + getPropiedad1().getNombre() + ", " + getPropiedad2().getNombre() + " + " + getDinero() + ")\n}";
        return cadena;
    }
    
}
