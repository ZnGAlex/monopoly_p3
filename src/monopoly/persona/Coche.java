package monopoly.persona;

import monopoly.mapa.Casilla;
import monopoly.mapa.Tablero;
import monopoly.mapa.Turno;

import static monopoly.mapa.Juego.consola;

final public class Coche extends Avatar {

    public Coche(Jugador jugador, Casilla casilla, String id) {
        super(jugador, casilla, id);
        setTipo("Coche");
    }

    @Override
    public void moverAvatarEspecial(int avance, Tablero tablero, Turno turno) {
        if (getJugador().getTurnosDadosTiradosEspecial() == 4) {
            consola.imprimir("El jugador ya ha tirado 4 veces en modo avanzado. No puede tirar mas.");
        } else if (avance > 4) {
            getJugador().setDadosTirados(false);
            getJugador().aumentarTurnosDadosTiradosEspecial();
            int posicionActual = getCasilla().getPosicion();
            /*Calculo de la nueva posicion*/
            int lado = ((posicionActual + avance) / 10) % 4;
            int posicionNueva = (posicionActual + avance) % 10;
            consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
            getCasilla().eliminarAvatar(this);
            /*Cambio el avatar de una casilla a otra*/
            setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
            getCasilla().getAvatares().put(getId(), this);
            getCasilla().getVecesCaidas().put(getJugador(), getCasilla().getVecesCaidas().get(getJugador()) + 1);
            consola.imprimir("Veces caidas en " + getCasilla().getNombre());
            getCasilla().getVecesCaidas().forEach((k, v) -> consola.imprimir(k.getNombre() + " -> " + v));
            if (getJugador().getTurnosDadosTiradosEspecial() == 4) {
                getJugador().setDadosTirados(true);
            }
            getCasilla().realizarAccion(getJugador(), turno, avance);
        } else {
            consola.imprimir("El jugador ha sacado 4 o menos. Pasara los siguientes 2 turnos sin poder tirar.");
            int posicionActual = getCasilla().getPosicion();
            // Calculo de la nueva posicion
            posicionActual -= avance;
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
            getCasilla().getVecesCaidas().put(getJugador(), getCasilla().getVecesCaidas().get(getJugador()) + 1);
            getJugador().setBloqueoTiroModoEspecial(true);
            getCasilla().realizarAccion(getJugador(), turno, avance);
        }
    }

    @Override
    public String toString() {
        String cadena = "{\n "
                + "\t tipo: Coche\n"
                + "\t id: " + getId()
                + ",\n\t casilla: " + getCasilla().getNombre()
                + ",\n\t jugador: " + getJugador().getNombre() + "\n}";
        return cadena;
    }
}
