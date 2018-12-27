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
    String fraseAccion;

    // constructores
    public Carta() {
        numCarta = 0;
        fraseAccion = "";
    }

    //getters y setters
    public int getNumCarta() {
        return numCarta;
    }

    public abstract void setNumCarta(int numCarta);

    public String getFraseAccion() {
        return fraseAccion;
    }

    public void setFraseAccion(String fraseAccion) {
        if (fraseAccion == null) {
            consola.imprimir(Valor.ANSI_ROJO + "accion nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.fraseAccion = fraseAccion;
    }

    // metodos

    public abstract void accion(Jugador jugador, Tablero tablero, Turno turno) throws ExcepcionCasilla;

}
