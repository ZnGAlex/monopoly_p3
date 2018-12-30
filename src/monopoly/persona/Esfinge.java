package monopoly.persona;

import monopoly.excepciones.ExcepcionCasilla;
import monopoly.excepciones.ExcepcionJugador;
import monopoly.mapa.*;

import java.util.ArrayList;

import static monopoly.mapa.Juego.consola;

final public class Esfinge extends Avatar{

    private boolean avanceEste;
    private int posicionAntigua;
    private int ladoAntiguo;
    private Solar solarComprado;
    private ArrayList<Edificio> edificiosComprados;
    private ArrayList<Integer> beneficios;
    private ArrayList<Integer> perdidas;

    public Esfinge(Jugador jugador, Casilla casilla, String id) {
        super(jugador, casilla, id);
        setTipo("Esfinge");
        setAvanceEste(false);
        this.edificiosComprados = new ArrayList<>();
        this.beneficios = new ArrayList<>();
        this.perdidas = new ArrayList<>();
    }

    private void moverAIzquierda(int avance, int posicionActual, Tablero tablero) {
        int posicionNueva = 0, lado = 0;

        if (avance >= 4) {
            lado = (posicionActual / 10) % 4;
            setLadoAntiguo(lado);
            setPosicionAntigua(posicionActual / 10);
            for (int i = 0; i < avance; i++) {
                lado = (posicionActual / 10) % 4;
                getCasilla().eliminarAvatar(this);
                if (lado == 0 && posicionActual == 9) { // Si esta en una casilla superior antes del borde izquierdo, el avatar vuelve a la casilla de ircarcel
                    posicionNueva = 0;
                    lado = 3;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 2 && posicionActual == 21) { // Si esta en una casilla inferior antes del borde izquierdo, el avatar vuelve a la casilla de salida
                    posicionNueva = 0;
                    lado = 0;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                    consola.imprimir("El jugador pasa por salida. Obtiene " + Valor.CANTIDAD_PASAR_SALIDA);
                    getJugador().setFortuna(getJugador().getFortuna() + Valor.CANTIDAD_PASAR_SALIDA);
                    anhadirBeneficio(Valor.CANTIDAD_PASAR_SALIDA);
                } else if (lado == 3 && posicionActual == 30) { // Si esta en ircarcel, el avatar va a la izquierda de salida
                    posicionNueva = 1;
                    lado = 0;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 0 && posicionActual == 0) { // Si esta en salida, el avatar va a la izquierda de ircarcel
                    posicionNueva = 9;
                    lado = 2;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                    consola.imprimir("El jugador pasa por salida. Obtiene " + Valor.CANTIDAD_PASAR_SALIDA);
                    getJugador().setFortuna(getJugador().getFortuna() + Valor.CANTIDAD_PASAR_SALIDA);
                    anhadirBeneficio(Valor.CANTIDAD_PASAR_SALIDA);
                }else if (lado == 3) { // Si el avatar esta en el lado derecho el avatar pasa a la casilla izquierda de Salida
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
            if (getCasilla().getPropietario().getNombre().equals(getJugador().getNombre()) && (getCasilla().getVecesCaidas().get(getJugador()).equals(3))) {
                getCasilla().setEdificable(true);
            }
            consola.imprimir("Veces caidas en " + getCasilla().getNombre());
            getCasilla().getVecesCaidas().forEach((k, v) -> consola.imprimir(k.getNombre() + " -> " + v));
        } else {
            consola.imprimir("El jugador ha sacado menos de un 4. Retrocederá a su posición anterior y perdera lo que ha hecho en su ultimo turno.");
            posicionNueva = posicionAntigua;
            lado = ladoAntiguo;
            deshacerUltimoTurno(tablero);
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
            setPosicionAntigua(posicionActual / 10);
            for (int i = 0; i < avance; i++) {
                lado = (posicionActual / 10) % 4;
                getCasilla().eliminarAvatar(this);
                if ((lado == 0 && posicionActual == 1)) { // Si esta en una casilla inferior antes del borde derecho, el avatar vuelve a la casilla de parking
                    posicionNueva = 0;
                    lado = 2;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 2 && posicionActual == 29) { // Si esta en una casilla superior antes del borde derecho, el avatar vuelve a la casilla de Carcel
                    posicionNueva = 0;
                    lado = 1;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if ((lado == 1 && posicionActual == 10)) { // Si esta en Carcel, el avatar va a la derecha de Parking
                    posicionNueva = 1;
                    lado = 2;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 2 && posicionActual == 20) { // Si esta en Parking superior antes del borde derecho, el avatar vuelve a la casilla de Carcel
                    posicionNueva = 9;
                    lado = 0;
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
            if (getCasilla().getPropietario().getNombre().equals(getJugador().getNombre()) && (getCasilla().getVecesCaidas().get(getJugador()).equals(3))) {
                getCasilla().setEdificable(true);
            }
            consola.imprimir("Veces caidas en " + getCasilla().getNombre());
            getCasilla().getVecesCaidas().forEach((k, v) -> consola.imprimir(k.getNombre() + " -> " + v));
        } else {
            consola.imprimir("El jugador ha sacado menos de un 4. Retrocederá a su posición anterior y perdera lo que ha hecho en su ultimo turno.");
            posicionNueva = posicionAntigua;
            lado = ladoAntiguo;
            deshacerUltimoTurno(tablero);
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
                avance = 5;
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

    /**
     * Añade el solar comprado en el ultimo movimiento especial
     * @param solar
     */
    public void anhadirSolarComprado(Solar solar) {
        this.solarComprado = solar;
    }

    /**
     * Añade el edificio comprado en el ultimo movimiento especial
     * @param edificio
     */
    public void anhadirEdificioComprado(Edificio edificio) {
        this.edificiosComprados.add(edificio);
    }

    /**
     * Añade el beneficio obtenido en el ultimo movimiento especial
     * @param beneficio
     */
    public void anhadirBeneficio(Integer beneficio) {
        this.beneficios.add(beneficio);
    }

    /**
     * Añade la perdida en el ultimo movimiento especial
     * @param perdida
     */
    public void anhadirPerdida(Integer perdida) {
        this.perdidas.add(perdida);
    }

    /**
     * Vaciado de las acciones del ultimo turno en movimiento especial
     */
    public void vaciarUltimoTurno() {
        this.solarComprado = null;
        for (int i = 0; i < this.edificiosComprados.size(); i++) {
            this.edificiosComprados.remove(i);
            i--;
        }
        for (int i = 0; i < this.beneficios.size(); i++) {
            this.beneficios.remove(i);
            i--;
        }
        for (int i = 0; i < this.perdidas.size(); i++) {
            this.perdidas.remove(i);
            i--;
        }
    }

    /**
     * Se deshacen los movimientos del ultimo turno en modo especial
     * @param tablero Para eliminar los edificios del tablero y pasar la propiedad a la banca del solar
     */
    public void deshacerUltimoTurno(Tablero tablero) {
        // Eliminacion de la compra del solar y devolucion del coste
        if (this.solarComprado != null) {
            getJugador().setFortuna(getJugador().getFortuna() + this.solarComprado.getValor());
            getJugador().getPropiedades().remove(this.solarComprado.getNombre());
            this.solarComprado.setPropietario(tablero.getCasillas().get(0).get(0).getPropietario());
        }

        // Eliminacion de la construccion de edificios y devolucion del coste
        for (Edificio edificio : this.edificiosComprados) {
            getJugador().getEdificios().remove(edificio);
            Solar s = (Solar) edificio.getCasilla();
            s.getEdificios().remove(edificio);
            tablero.getEdificios().remove(edificio);
        }

        // Eliminacion de los beneficios
        for (Integer beneficio : this.beneficios)
            getJugador().setFortuna(getJugador().getFortuna() - beneficio);

        // Devolucion de las perdidas
        for (Integer perdida : this.perdidas)
            getJugador().setFortuna(getJugador().getFortuna() + perdida);

        vaciarUltimoTurno();
    }
}
