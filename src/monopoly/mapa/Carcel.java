package monopoly.mapa;

import monopoly.persona.Avatar;
import monopoly.persona.Jugador;

import java.util.ArrayList;

public class Carcel extends Especial {
    public Carcel(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
    }

    @Override
    public String info() {
        ArrayList<String> jug = new ArrayList<>();
        for (Avatar avat : getAvatares().values()) {
            String str;
            str = avat.getJugador().getNombre();
            if (avat.getJugador().getInCarcel()) {
                str = str.concat("(" + avat.getJugador().getTurnosEnCarcel() + ")");
            }
            jug.add(str);
        }
        return "{\n "
                + "\t salir: " + Valor.COSTE_SALIR_CARCEL
                + ",\n\t jugadores: " + jug
                + "\n}";
    }
}
