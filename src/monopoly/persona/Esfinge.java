package monopoly.persona;

import monopoly.mapa.Casilla;
import monopoly.mapa.Tablero;
import monopoly.mapa.Turno;

final public class Esfinge extends Avatar{

    public Esfinge(Jugador jugador, Casilla casilla, String id) {
        super(jugador, casilla, id);
        setTipo("Esfinge");
    }

    @Override
    public void moverEnAvanzado(int avance, Tablero tablero, Turno turno) {

    }

    @Override
    public String toString() {
        String cadena = "{\n "
                + "\t tipo: Esfinge\n"
                + "\t id: " + getId()
                + ",\n\t casilla: " + getCasilla().getNombre()
                + ",\n\t jugador: " + getJugador().getNombre() + "\n}";
        return cadena;
    }
}
