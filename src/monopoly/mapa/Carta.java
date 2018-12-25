/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.mapa;

import monopoly.excepciones.ExcepcionCasilla;
import monopoly.persona.Jugador;

import static monopoly.mapa.Juego.consola;


public abstract class Carta {

    int numCarta;
    String accion;

    // constructores
    public Carta(int numCarta) {
        if (numCarta < 0 || numCarta > Valor.ACCIONES_SUERTE.size()) {
            consola.imprimir(Valor.ANSI_ROJO + "numCarta no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.numCarta = numCarta;
    }

    //getters y setters
    public int getNumCarta() {
        return numCarta;
    }

    public void setNumCarta(int numCarta) {
        if (numCarta < 0 || numCarta > Valor.ACCIONES_SUERTE.size()) {
            consola.imprimir(Valor.ANSI_ROJO + "numCarta no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.numCarta = numCarta;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        if (accion == null) {
            consola.imprimir(Valor.ANSI_ROJO + "accion nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.accion = accion;
    }

    // metodos

    public abstract void realizarAccion(Jugador jugador, Tablero tablero, Turno turno) throws ExcepcionCasilla;

}
