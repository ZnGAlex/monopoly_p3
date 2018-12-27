package monopoly.mapa;

import monopoly.persona.Jugador;

public class Impuesto extends Casilla{

    public Impuesto(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
    }

    @Override
    public String info() {
        return "{\n "
                + "\t tipo: Impuesto"
                + ",\n\t alquiler: " + getAlquiler()
                + "\n}";
    }
}
