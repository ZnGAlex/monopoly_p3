package monopoly.mapa;

import monopoly.persona.Jugador;

import static monopoly.mapa.Juego.consola;

public abstract class Propiedad extends Casilla{

    private int valor;
    private int alquiler;

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

    public int getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(int alquiler) {
        if (alquiler < 0) {
            consola.imprimir(Valor.ANSI_ROJO + "Alquiler no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.alquiler = alquiler;
    }

}
