package monopoly.persona;

import monopoly.excepciones.ExcepcionCasilla;
import monopoly.excepciones.ExcepcionJugador;
import monopoly.mapa.*;

import java.util.ArrayList;

import static monopoly.mapa.Juego.consola;

final public class Sombrero extends Avatar {

    private boolean avanceNorte;
    private int posicionAntigua;
    private int ladoAntiguo;
    private Casilla casillaAntigua;
    private Solar solarComprado;
    private ArrayList<Edificio> edificiosComprados;
    private ArrayList<Integer> beneficios;
    private ArrayList<Integer> perdidas;

    public Sombrero(Jugador jugador, Casilla casilla, String id) {
        super(jugador, casilla, id);
        setTipo("Sombrero");
        this.edificiosComprados = new ArrayList<>();
        this.beneficios = new ArrayList<>();
        this.perdidas = new ArrayList<>();
        this.casillaAntigua = casilla;
    }

    private void moverArriba(int avance, int posicionActual, Tablero tablero) {
        int posicionNueva = 0, lado = 0;

        if (avance > 4) {
            this.casillaAntigua = getCasilla();
            for (int i = 0; i < avance; i++) {
                lado = (posicionActual / 10) % 4;
                getCasilla().eliminarAvatar(this);
                if (lado == 1 && posicionActual == 19) { // Si esta en una casilla antes del borde superior, el avatar vuelve a la casilla de salida
                    posicionNueva = 0;
                    lado = 0;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                    consola.imprimir("El jugador pasa por salida. Obtiene " + Valor.CANTIDAD_PASAR_SALIDA);
                    getJugador().setFortuna(getJugador().getFortuna() + Valor.CANTIDAD_PASAR_SALIDA);
                    anhadirBeneficio(Valor.CANTIDAD_PASAR_SALIDA);
                } else if (lado == 3 && posicionActual == 31) {
                    posicionNueva = 0;
                    lado = 1;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 0 && posicionActual == 0) { // Avatar en salida pasa a encima de carcel
                    posicionNueva = 1;
                    lado = 1;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 1 && posicionActual == 10) { // Avatar en carcel pasa a encima de salida
                    posicionNueva = 9;
                    lado = 3;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 0) { // Si el avatar esta en el lado inferior el avatar pasa a la casilla superior de Carcel
                    posicionNueva = 1;
                    lado = 1;
                    consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 1) { // Si esta en el lado izquierdo se desplaza una posicion arriba y pasa al lado derecho
                    posicionNueva = 10 - ((posicionActual + 1) % 10);
                    lado = 3;
                    consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 3) { // Si esta en el lado derecho se desplaza una posicion arriba y pasa al lado izquierdo
                    posicionNueva = 10 - ((posicionActual - 1) % 10);
                    lado = 1;
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
            deshacerUltimoTurno(tablero);
            consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + casillaAntigua.getNombre());
            getCasilla().eliminarAvatar(this);
            try {
                setCasilla(tablero.casillaByName(casillaAntigua.getNombre()));
            } catch (ExcepcionCasilla exc) {
                consola.imprimir(exc.getMessage());
            }
            getCasilla().getAvatares().put(getId(), this);
            getCasilla().getVecesCaidas().put(getJugador(), getCasilla().getVecesCaidas().get(getJugador()) + 1);
        }
    }

    private void moverAbajo(int avance, int posicionActual, Tablero tablero) {
        int posicionNueva = 0, lado = 0;

        if (avance > 4) {
            this.casillaAntigua = getCasilla();
            for (int i = 0; i < avance; i++) {
                lado = (posicionActual / 10) % 4;
                getCasilla().eliminarAvatar(this);
                if ((lado == 1 && posicionActual == 11) ) { // Si esta en una casilla izquierda antes del borde inferior, el avatar vuelve a la casilla de ircarcel
                    posicionNueva = 0;
                    lado = 3;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 3 && posicionActual == 39) { // Casilla encima de salida pasa a Parking
                    posicionNueva = 0;
                    lado = 2;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 2 && posicionActual == 20) { // Casilla Parking de salida pasa a debajo de ircarcel
                    posicionNueva = 1;
                    lado = 3;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 3 && posicionActual == 30) { // Casilla ircarcel pasa a debajo de Parking
                    posicionNueva = 9;
                    lado = 1;
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 2) { // Si esta en el lado superior se desplaza una posicion abajo de IrCarcel
                    posicionNueva = 1;
                    lado = 3;
                    consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 1) { // Si esta en el lado izquierdo se desplaza una posicion abajo y pasa al lado derecho
                    posicionNueva = 10 - ((posicionActual - 1) % 10);
                    lado = 3;
                    consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                    setCasilla(tablero.getCasillas().get(lado).get(posicionNueva));
                    posicionActual = getCasilla().getPosicion();
                    getCasilla().getAvatares().put(getId(), this);
                } else if (lado == 3) { // Si esta en el lado derecho se desplaza una posicion abajo y pasa al lado izquierdo
                    posicionNueva = 10 - ((posicionActual + 1) % 10);
                    lado = 1;
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
            deshacerUltimoTurno(tablero);
            consola.imprimir("Desde " + getCasilla().getNombre() + " hasta " + casillaAntigua.getNombre());
            getCasilla().eliminarAvatar(this);
            try {
                setCasilla(tablero.casillaByName(casillaAntigua.getNombre()));
            } catch (ExcepcionCasilla exc) {
                consola.imprimir(exc.getMessage());
            }
            getCasilla().getAvatares().put(getId(), this);
            getCasilla().getVecesCaidas().put(getJugador(), getCasilla().getVecesCaidas().get(getJugador()) + 1);
        }
    }

    @Override
    public void moverEnAvanzado(int avance, Tablero tablero, Turno turno) {
        try {
            if (getJugador().getTurnosDadosTiradosEspecial() == 3)
                throw new ExcepcionJugador("El jugador ya ha tirado 3 veces en modo avanzado");
            else {
                getJugador().setDadosTirados(false);
                getJugador().aumentarTurnosDadosTiradosEspecial();
                int posicionActual = getCasilla().getPosicion();
                /*Calculo de la nueva posicion*/
                if (isAvanceNorte())
                    moverArriba(avance, posicionActual, tablero);
                else
                    moverAbajo(avance, posicionActual, tablero);
                if (getJugador().getTurnosDadosTiradosEspecial() == 3) {
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

    public void cambiarAvanceNorte() {
        if (getCasilla().getPosicion() / 10 % 4 == 0 || getCasilla().getPosicion() / 10 % 4 == 1)
            setAvanceNorte(true);
        else
            setAvanceNorte(false);
    }

    public boolean isAvanceNorte() {
        return avanceNorte;
    }

    public void setAvanceNorte(boolean avanceNorte) {
        this.avanceNorte = avanceNorte;
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
            Grupo g = s.getGrupo();
            if (g.getEdificios().contains(edificio))
                g.getEdificios().remove(edificio);
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
