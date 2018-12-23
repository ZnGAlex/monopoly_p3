package monopoly.persona;

import monopoly.mapa.*;

import static monopoly.mapa.Juego.consola;

public abstract class Avatar {

    private String id;
    private Jugador jugador;
    private Casilla casilla;
    private String tipo;

    // constructores
    public Avatar(Jugador jugador, Casilla casilla, String id) {
        if (jugador != null) {
            this.id = id;
            this.jugador = jugador;
            this.casilla = casilla;
        } else {
            consola.imprimir("Error creando avatar.");
            System.exit(1);
        }
    }

    // getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null) {
            consola.imprimir("Id nulo.");
            System.exit(1);
        }
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        if (jugador == null) {
            consola.imprimir("Jugador nulo.");
            System.exit(1);
        }
        this.jugador = jugador;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        if (casilla == null) {
            consola.imprimir("Casilla nula.");
            System.exit(1);
        }
        this.casilla = casilla;
    }

    // metodos
    /**
     * Mueve el avatar 'avance' casillas en el tablero
     */
    public void moverAvatar(int avance, Tablero tablero, Turno turno) {
        if (avance < 0) {
            consola.imprimir(Valor.ANSI_ROJO + "Avance negativo.");
            System.exit(1);
        }
        if (tablero == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Tablero nulo.");
            System.exit(1);

        }
        int posicionActual = this.casilla.getPosicion();
        /*Calculo de la nueva posicion*/
        int lado = ((posicionActual + avance) / 10) % 4;
        int posicionNueva = (posicionActual + avance) % 10;
        if (lado * 10 + posicionNueva < this.casilla.getPosicion()) {
            /*Si el jugador pasa por salida, cobra*/
            consola.imprimir(this.jugador.getNombre() + " pasa por salida y cobra " + Valor.CANTIDAD_PASAR_SALIDA + " €");
            this.jugador.setPasarPorCasillaDeSalida(this.jugador.getPasarPorCasillaDeSalida() + Valor.CANTIDAD_PASAR_SALIDA);
        }
        
        consola.imprimir("Desde " + this.casilla.getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
        this.casilla.eliminarAvatar(this);
        /*Cambio el avatar de una casilla a otra*/
        this.casilla = tablero.getCasillas().get(lado).get(posicionNueva);
        this.casilla.getAvatares().put(this.id, this);
        this.casilla.getVecesCaidas().put(this.jugador, this.casilla.getVecesCaidas().get(this.jugador) + 1);

        if (this.casilla.getPropietario().getNombre().equals(this.jugador.getNombre()) && (this.casilla.getVecesCaidas().get(this.jugador).equals(3))) {
            this.casilla.setEdificable(true);
        }

        consola.imprimir("Veces caidas en " + this.casilla.getNombre());
        this.casilla.frecuenciaVisita();

        this.casilla.realizarAccion(jugador, turno, avance);
    }

    public abstract void moverAvatarEspecial(int avance, Tablero tablero, Turno turno);

    /*
    public void moverAvatarEspecial(int avance, Tablero tablero, Turno turno) {
        switch (this.ficha) {
            case Valor.COCHE:
                if (this.jugador.getTurnosDadosTiradosEspecial() == 4) {
                    consola.imprimir("El jugador ya ha tirado 4 veces en modo avanzado. No puede tirar mas.");
                } else if (avance > 4) {
                    this.jugador.setDadosTirados(false);
                    this.jugador.aumentarTurnosDadosTiradosEspecial();
                    int posicionActual = this.casilla.getPosicion();
                    // Calculo de la nueva posicion
                    int lado = ((posicionActual + avance) / 10) % 4;
                    int posicionNueva = (posicionActual + avance) % 10;
                    consola.imprimir("Desde " + this.casilla.getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                    this.casilla.eliminarAvatar(this);
                    // Cambio el avatar de una casilla a otra
                    this.casilla = tablero.getCasillas().get(lado).get(posicionNueva);
                    this.casilla.getAvatares().put(this.id, this);
                    this.casilla.getVecesCaidas().put(this.jugador, this.casilla.getVecesCaidas().get(this.jugador) + 1);
                    consola.imprimir("Veces caidas en " + this.casilla.getNombre());
                    this.casilla.getVecesCaidas().forEach((k, v) -> consola.imprimir(k.getNombre() + " -> " + v));
                    if (this.jugador.getTurnosDadosTiradosEspecial() == 4) {
                        this.jugador.setDadosTirados(true);
                    }
                    this.casilla.realizarAccion(jugador, turno, avance);
                } else {
                    consola.imprimir("El jugador ha sacado 4 o menos. Pasara los siguientes 2 turnos sin poder tirar.");
                    int posicionActual = this.casilla.getPosicion();
                    // Calculo de la nueva posicion
                    posicionActual -= avance;
                    if ((posicionActual) < 0) {
                        posicionActual = 40 + posicionActual;
                    }
                    int lado = ((posicionActual) / 10) % 4;
                    int posicionNueva = (posicionActual) % 10;
                    consola.imprimir("Desde " + this.casilla.getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                    this.casilla.eliminarAvatar(this);
                    // Cambio el avatar de una casilla a otra
                    this.casilla = tablero.getCasillas().get(lado).get(posicionNueva);
                    this.casilla.getAvatares().put(this.id, this);
                    this.casilla.getVecesCaidas().put(this.jugador, this.casilla.getVecesCaidas().get(this.jugador) + 1);
                    this.jugador.setBloqueoTiroModoEspecial(true);
                    this.casilla.realizarAccion(jugador, turno, avance);
                }
                break;
            case Valor.PELOTA:
                this.jugador.setDadosTirados(true);
                if (avance > 4) {
                    int posicionActual = this.casilla.getPosicion();
                    // Calculo de la nueva posicion
                    int lado = ((posicionActual + 4) / 10) % 4;
                    int posicionNueva = (posicionActual + 4) % 10;
                    consola.imprimir("Desde " + this.casilla.getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                    this.casilla.eliminarAvatar(this);
                    // Cambio el avatar de una casilla a otra
                    this.casilla = tablero.getCasillas().get(lado).get(posicionNueva);
                    this.casilla.getAvatares().put(this.id, this);
                    for (int i = 1; i <= avance - 4; i++) {
                        posicionActual = this.casilla.getPosicion();
                        // Calculo de la nueva posicion
                        lado = ((posicionActual + 1) / 10) % 4;
                        posicionNueva = (posicionActual + 1) % 10;
                        consola.imprimir("Desde " + this.casilla.getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                        this.casilla.eliminarAvatar(this);
                        // Cambio el avatar de una casilla a otra
                        this.casilla = tablero.getCasillas().get(lado).get(posicionNueva);
                        this.casilla.getAvatares().put(this.id, this);
                        if (i % 2 != 0) {
                            this.casilla.getVecesCaidas().put(this.jugador, this.casilla.getVecesCaidas().get(this.jugador) + 1);
                            this.casilla.realizarAccion(jugador, turno, avance);
                        }
                    }
                } else {
                    for (int i = 1; i <= avance; i++) {
                        int posicionActual = this.casilla.getPosicion();
                        // Calculo de la nueva posicion
                        posicionActual -= 1;
                        if ((posicionActual) < 0) {
                            posicionActual = 40 + posicionActual;
                        }
                        int lado = ((posicionActual) / 10) % 4;
                        int posicionNueva = (posicionActual) % 10;
                        consola.imprimir("Desde " + this.casilla.getNombre() + " hasta " + tablero.getCasillas().get(lado).get(posicionNueva).getNombre());
                        this.casilla.eliminarAvatar(this);
                        // Cambio el avatar de una casilla a otra
                        this.casilla = tablero.getCasillas().get(lado).get(posicionNueva);
                        this.casilla.getAvatares().put(this.id, this);
                        if (i % 2 != 0) {
                            consola.imprimir("Veces caidas en " + this.casilla.getNombre());
                            this.casilla.getVecesCaidas().forEach((k, v) -> consola.imprimir(k.getNombre() + " -> " + v));
                            this.casilla.getVecesCaidas().put(this.jugador, this.casilla.getVecesCaidas().get(this.jugador) + 1);
                            this.casilla.realizarAccion(jugador, turno, avance);
                        }
                    }
                }
                break;
        }
    }
    */

    /**
     * Mueve el avatar a la casilla destino sin realizar ninguna accion en ella
     *
     * @param destino casilla a la que mover el avatar
     */
    public void moverAvatarCasilla(Casilla destino) {
        if (casilla == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Avance negativo.");
            System.exit(1);
        }
        this.casilla.eliminarAvatar(this);
        /*Muevo el avatar de una casilla a otra*/
        this.casilla = destino;
        this.casilla.anhadirAvatar(this);
    }

    /**
     * Mueve el avatar a la casilla destino realizando la accion correspondiente
     * (pagar alquiler, pagar impuesto...)
     */
    public void moverAvatarCasilla(Casilla destino, Turno turno) {
        if (casilla == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Avance negativo.");
            System.exit(1);
        }
        int avance = destino.getPosicion() - this.casilla.getPosicion();
        if (avance < 0) {
            avance += 40;
        }

        this.casilla.eliminarAvatar(this);
        /*Muevo el avatar de una casilla a otra*/
        this.casilla = destino;
        this.casilla.anhadirAvatar(this);

        this.casilla.realizarAccion(this.jugador, turno, avance);
    }

    @Override
    public String toString() {
        String cadena = "{\n "
                + "\t id: " + this.id
                + ",\n\t casilla: " + this.casilla.getNombre()
                + ",\n\t jugador: " + this.jugador.getNombre() + "\n}";
        return cadena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
