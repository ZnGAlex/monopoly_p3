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
public class P4PandM extends P4P{
    private int dinero;

    public P4PandM(Propiedad propiedad1, Propiedad propiedad2, int dinero) throws ExcepcionTrato {
        super(propiedad1, propiedad2);
        if (dinero < 0) {
            throw new ExcepcionTrato("Dinero negativo");
        }
        this.dinero = dinero;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        if(dinero < 0){
            consola.imprimir(Valor.ANSI_ROJO + "Dinero negativo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.dinero = dinero;
    }
}

