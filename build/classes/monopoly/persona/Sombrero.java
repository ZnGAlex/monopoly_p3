package monopoly.persona;

import monopoly.mapa.Casilla;
import monopoly.mapa.Tablero;
import monopoly.mapa.Turno;

final public class Sombrero extends Avatar {

    public Sombrero(Jugador jugador, Casilla casilla, String id) {
        super(jugador, casilla, id);
        setTipo("Sombrero");
    }

    @Override
    public void moverAvatarEspecial(int avance, Tablero tablero, Turno turno) {

    }

    @Override
    public String toString() {
        String cadena = "{\n "
                + "\t tipo: Sombrero\n"
                + "\t id: " + getId()
                + ",\n\t casilla: " + getCasilla().getNombre()
                + ",\n\t jugador: " + getJugador().getNombre() + "\n}";
        return cadena;
    }
}
