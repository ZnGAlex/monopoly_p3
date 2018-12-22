package monopoly.mapa;

import monopoly.persona.Jugador;

public class Especial extends Casilla{

    public Especial(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
    }

    @Override
    public String info() {
        return "{\n "
                + "\t tipo: Especial"
                + "\n}";
    }
}
