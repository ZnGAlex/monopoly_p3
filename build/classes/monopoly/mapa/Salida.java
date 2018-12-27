package monopoly.mapa;

import monopoly.persona.Jugador;

public class Salida extends Accion {

    public Salida(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
    }

    @Override
    public String info() {
        return "Casilla de salida";
    }
}
