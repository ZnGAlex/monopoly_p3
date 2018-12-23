package monopoly.mapa;

import monopoly.persona.Jugador;

public abstract class Propiedad extends Casilla{

    public Propiedad(String nombre, Grupo grupo, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, grupo, posicion, banca, tablero);
    }

    public Propiedad(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
    }

    public boolean perteneceAJugador(Jugador jugador) {
        return (super.getPropietario() == jugador);
    }

    public abstract int alquiler();

    public int valor() {
        return super.getValor();
    }

    public void comprar() {

    }

}
