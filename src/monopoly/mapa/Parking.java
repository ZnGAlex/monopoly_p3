package monopoly.mapa;

import monopoly.persona.Avatar;
import monopoly.persona.Jugador;

import java.util.ArrayList;

public class Parking extends Especial {
    public Parking(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
    }

    @Override
    public String info() {
        ArrayList<String> jugad = new ArrayList<>();
        for (Avatar avat : getAvatares().values()) {
            jugad.add(avat.getJugador().getNombre());
        }
        return "{\n "
                + "\t bote: " + Valor.DINERO_PARKING
                + ",\n\t jugadores: " + jugad
                + "\n}";
    }
}
