package monopoly.persona;

import monopoly.excepciones.ExcepcionCasilla;
import monopoly.excepciones.ExcepcionJugador;
import monopoly.mapa.Casilla;
import monopoly.mapa.Tablero;
import monopoly.mapa.Turno;
import monopoly.mapa.Valor;

import static monopoly.mapa.Juego.consola;

final public class Esfinge extends Avatar{

    private boolean avanceEste;
    private int posicionAntigua;
    private int ladoAntiguo;

    public Esfinge(Jugador jugador, Casilla casilla, String id) {
        super(jugador, casilla, id);
        setTipo("Esfinge");
        setAvanceEste(false);
    }

    private void moverAIzquierda(int avance, int posicionActual, Tablero tablero) {
        int posicionNueva = 0, lado = 0;

        if (avance >= 4) {
            lado = (posicionActual / 10) % 4;
            setLadoAntiguo(lado);
            setPosicionAntigua(posicionActual);
            for (int i = 0; i < avance; i++) {
                lado = (posicionActual / 10) % 4;
                getCasilla().eliminarAvatar(this);
                if ((lado == 0 && posicionActual == 9) || (lado == 2 && posicionActual == 21)) { // Si esta en una casilla antes del borde izquierdo, el avatar vuelve a la casilla de salida
                    posicionNueva = 0;
                    lado = 0;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                    getJugador().setFortuna(getJugador().getFortuna() + Valor.CANTIDAD_PASAR_SALIDA);
                } else if (lado == 3) { // Si el avatar esta en el lado derecho el avatar pasa a la casilla izquierda de Salida
                    posicionNueva = 1;
                    lado = 0;
                    consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 0) { // Si esta en el lado inferior se desplaza una posicion a la izquierda y pasa al lado superior
                    posicionNueva = 10 - (posicionActual + 1);
                    lado = 2;
                    consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 2) { // Si esta en el lado superior se desplaza una posicion a la izquierda y pasa al lado inferior
                    posicionNueva = 10 - ((posicionActual - 1) % 10);
                    lado = 0;
                    consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                }
            }
            getCasilla().getVecesCaidas().put(getJugador(), getCasilla().getVecesCaidas().get(getJugador()) + 1);
            consola.imprimir("Veces caidas en " + getCasilla().getNombre());
            getCasilla().getVecesCaidas().forEach((k, v) -> consola.imprimir(k.getNombre() + " -> " + v));
        } else {
            consola.imprimir("El jugador ha sacado menos de un 4. Retrocederá a su posición anterior.");
            posicionNueva = posicionAntigua;
            lado = ladoAntiguo;
            consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
            getCasilla().eliminarAvatar(this);
            setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
            getCasilla().getAvatares().put(getId(), this);
            getCasilla().getVecesCaidas().put(getJugador(), getCasilla().getVecesCaidas().get(getJugador()) + 1);
        }
    }

    private void moverADerecha(int avance, int posicionActual, Tablero tablero) {
        int posicionNueva = 0, lado = 0;

        if (avance >= 4) {
            lado = (posicionActual / 10) % 4;
            setLadoAntiguo(lado);
            setPosicionAntigua(posicionActual);
            for (int i = 0; i < avance; i++) {
                lado = (posicionActual / 10) % 4;
                getCasilla().eliminarAvatar(this);
                if ((lado == 0 && posicionActual == 1) || (lado == 2 && posicionActual == 29)) { // Si esta en una casilla antes del borde derecho, el avatar vuelve a la casilla de parking
                    posicionNueva = 0;
                    lado = 2;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 1) { // Si el avatar esta en el lado izquierdo pasa a la casilla derecha de parking en el lado superior
                    posicionNueva = 1;
                    lado = 2;
                    consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 0) { // Si esta en el lado inferior se desplaza una posicion a la derecha y pasa al lado superior
                    posicionNueva = 10 - (posicionActual - 1);
                    lado = 2;
                    consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 2) { // Si esta en el lado superior se desplaza una posicion a la derecha y pasa al lado inferior
                    posicionNueva = 10 - ((posicionActual + 1) % 10);
                    lado = 0;
                    consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                }
            }
            getCasilla().getVecesCaidas().put(getJugador(), getCasilla().getVecesCaidas().get(getJugador()) + 1);
            consola.imprimir("Veces caidas en " + getCasilla().getNombre());
            getCasilla().getVecesCaidas().forEach((k, v) -> consola.imprimir(k.getNombre() + " -> " + v));
        } else {
            consola.imprimir("El jugador ha sacado menos de un 4. Retrocederá a su posición anterior.");
            posicionNueva = posicionAntigua;
            lado = ladoAntiguo;
            consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
            getCasilla().eliminarAvatar(this);
            setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
            getCasilla().getAvatares().put(getId(), this);
            getCasilla().getVecesCaidas().put(getJugador(), getCasilla().getVecesCaidas().get(getJugador()) + 1);
        }
    }

    @Override
    public void moverEnAvanzado(int avance, Tablero tablero, Turno turno) {
        try {
            if (getJugador().getTurnosDadosTiradosEspecial() == 2)
                throw new ExcepcionJugador("El jugador ya ha tirado 2 veces en modo avanzado");
            else {
                getJugador().setDadosTirados(false);
                getJugador().aumentarTurnosDadosTiradosEspecial();
                int posicionActual = getCasilla().getPosicion();
                int lado = posicionActual % 10, posicionNueva = 0;
                /*Calculo de la nueva posicion*/
                if (!isAvanceEste())
                    moverAIzquierda(avance, posicionActual, tablero);
                else
                    moverADerecha(avance, posicionActual, tablero);
                if (getJugador().getTurnosDadosTiradosEspecial() == 2) {
                    getJugador().setDadosTirados(true);
                }
                getCasilla().realizarAccion(getJugador(), turno, avance);
            }
        } catch (ExcepcionJugador exj) {
            consola.imprimir(exj.getMessage());
        } catch (ExcepcionCasilla exc) {
            consola.imprimir(exc.getMessage());
        }

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

    public void cambiarAvanceEste() {
        if (getCasilla().getPosicion() / 10 % 4 == 1 || getCasilla().getPosicion() / 10 % 4 == 2)
            setAvanceEste(true);
        else
            setAvanceEste(false);
    }

    public boolean isAvanceEste() {
        return avanceEste;
    }

    public void setAvanceEste(boolean avanceEste) {
        this.avanceEste = avanceEste;
    }

    public int getPosicionAntigua() {
        return posicionAntigua;
    }

    public void setPosicionAntigua(int posicionAntigua) {
        this.posicionAntigua = posicionAntigua;
    }

    public int getLadoAntiguo() {
        return ladoAntiguo;
    }

    public void setLadoAntiguo(int ladoAntiguo) {
        this.ladoAntiguo = ladoAntiguo;
    }
}
