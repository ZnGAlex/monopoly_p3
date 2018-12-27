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
public class PM4P extends P4PandM {

    public PM4P(Propiedad propiedad1, Propiedad propiedad2, int dinero) throws ExcepcionTrato {
        super(propiedad1, propiedad2, dinero);
    }

    @Override
    public String toString(){
        String cadena = "{\n "
                + "\t jugadorPropone: " + getPropiedad1().getPropietario().getNombre()
                + ",\n\t trato: cambiar (" + getPropiedad1().getNombre() + " + " + getDinero() + ", " + getPropiedad2().getNombre() + ")\n}";
        return cadena;
    }

}
