package monopoly.persona;

import monopoly.mapa.Casilla;
import monopoly.mapa.Tablero;
import monopoly.mapa.Turno;

import static monopoly.mapa.Juego.consola;

final public class Pelota extends Avatar{

    public Pelota(Jugador jugador, Casilla casilla, String id) {
        super(jugador, casilla, id);
        setTipo("Pelota");
    }

    @Override
    public void moverAvatarEspecial(int avance, Tablero tablero, Turno turno) {
        getJugador().setDadosTirados(true);
        if (avance > 4) {
            int posicionActual = getCasilla().getPosicion();
            // Calculo de la nueva posicion
            int lado = ((posicionActual + 4) / 10) % 4;
            int posicionNueva = (posicionActual + 4) % 10;
            consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
            getCasilla().eliminarAvatar(this);
            // Cambio el avatar de una casilla a otra
            setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
            getCasilla().getAvatares().put(getId(), this);
            for (int i = 1; i <= avance - 4; i++) {
                posicionActual = getCasilla().getPosicion();
                // Calculo de la nueva posicion
                lado = ((posicionActual + 1) / 10) % 4;
                posicionNueva = (posicionActual + 1) % 10;
                consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                getCasilla().eliminarAvatar(this);
                // Cambio el avatar de una casilla a otra
                setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                getCasilla().getAvatares().put(getId(), this);
                if (i % 2 != 0) {
                    getCasilla().getVecesCaidas().put(getJugador(), getCasilla().getVecesCaidas().get(getJugador()) + 1);
                    getCasilla().realizarAccion(getJugador(), turno, avance);
                }
            }
        } else {
            for (int i = 1; i <= avance; i++) {
                int posicionActual = getCasilla().getPosicion();
                // Calculo de la nueva posicion
                posicionActual -= 1;
                if ((posicionActual) < 0) {
                    posicionActual = 40 + posicionActual;
                }
                int lado = ((posicionActual) / 10) % 4;
                int posicionNueva = (posicionActual) % 10;
                consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                getCasilla().eliminarAvatar(this);
                // Cambio el avatar de una casilla a otra
                setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                getCasilla().getAvatares().put(getId(), this);
                if (i % 2 != 0) {
                    consola.imprimir("Veces caidas en " + getCasilla().getNombre());
                    getCasilla().getVecesCaidas().forEach((k, v) -> consola.imprimir(k.getNombre() + " -> " + v));
                    getCasilla().getVecesCaidas().put(getJugador(), getCasilla().getVecesCaidas().get(getJugador()) + 1);
                    getCasilla().realizarAccion(getJugador(), turno, avance);
                }
            }
        }
    }

    @Override
    public String toString() {
        String cadena = "{\n "
                + "\t tipo: Pelota\n"
                + "\t id: " + getId()
                + ",\n\t casilla: " + getCasilla().getNombre()
                + ",\n\t jugador: " + getJugador().getNombre() + "\n}";
        return cadena;
    }
}
