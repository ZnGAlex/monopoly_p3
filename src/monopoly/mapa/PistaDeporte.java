package monopoly.mapa;

import static monopoly.mapa.Juego.consola;

final public class PistaDeporte extends Edificio {
    public PistaDeporte(Solar solar) {
        if (solar == null) {
            consola.imprimir("Casilla nula.");
            System.exit(1);
        }
        setCasilla(solar);
        setGrupo(solar.getGrupo());
        setValor((int) (solar.getValor() * 1.25));
        setAlquiler((int) (solar.getValor() * 0.1 * 25));
        switch (solar.getNumPistas()) {
            case 0:
                setNombre("pista-1-" + solar.getNombre());
                break;
            case 1:
                setNombre("pista-2-" + solar.getNombre());
                break;
            case 2:
                setNombre("pista-3-" + solar.getNombre());
                break;
        }
        consola.imprimir("Se ha construido una pista en " + solar.getNombre() +
                ". La fortuna de " + solar.getPropietario().getNombre() + " se reduce en " + getValor() + "€.");
    }
}
