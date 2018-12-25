package monopoly.mapa;

import monopoly.persona.Jugador;

public class CasillaIrCarcel extends Accion {

    public CasillaIrCarcel(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
    }

    @Override
    public String info() {
        return null;
    }
}
