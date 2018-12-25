package monopoly.mapa;

import monopoly.persona.Jugador;

public class CasillaSuerte extends Accion {

    public CasillaSuerte(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
    }

    @Override
    public String info() {
        return "nombre: " + getNombre() +
                "tipo: Suerte";
    }
}
