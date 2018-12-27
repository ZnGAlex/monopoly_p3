package monopoly.mapa;

import monopoly.persona.Jugador;

public abstract class Accion extends Casilla{

    public Accion(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
    }

}
