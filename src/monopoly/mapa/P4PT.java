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
public class P4PT extends P4P {

    private Propiedad propiedad3;
    private int turnos;

    public P4PT(Propiedad propiedad1, Propiedad propiedad2, Propiedad propiedad3, int turnos) throws ExcepcionTrato {
        super(propiedad1, propiedad2);
        if (turnos < 0) {
            throw new ExcepcionTrato("Turnos negativo");
        }
        if (propiedad3 == null) {
            throw new ExcepcionTrato("Propiedad3 nulo");
        }
        if (!propiedad3.getPropietario().getNombre().equals(propiedad2.getPropietario().getNombre())) {
            throw new ExcepcionTrato(propiedad3.getNombre() + " no pertenece a " + propiedad2.getPropietario().getNombre());
        }
        this.propiedad3 = propiedad3;
        this.turnos = turnos;
    }

    public Propiedad getPropiedad3() {
        return propiedad3;
    }

    public void setPropiedad3(Propiedad propiedad3) {
        if (propiedad3 == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Propiedad3 nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.propiedad3 = propiedad3;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        if (turnos < 0) {
            consola.imprimir(Valor.ANSI_ROJO + "Turnos negativo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.turnos = turnos;
    }

    @Override
    public String toString() {
        String cadena = "{\n "
                + "\t jugadorPropone: " + getPropiedad1().getPropietario().getNombre()
                + ",\n\t trato: cambiar (" + getPropiedad1().getNombre() + ", " + getPropiedad2().getNombre() + ", " + propiedad3.getNombre() + " + " + turnos + " turnos)\n}";
        return cadena;
    }

}
