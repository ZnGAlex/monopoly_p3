package monopoly.mapa;

import static monopoly.mapa.Juego.consola;

final public class Piscina extends Edificio {
    public Piscina(Solar solar) {
        super(solar);
        setValor((int) (solar.getValor() * 0.4));
        setAlquiler((int) (solar.getValor() * 0.1 * 25));
        switch (solar.getNumPiscinas()) {
            case 0:
                setNombre("piscina-1-" + solar.getNombre());
                break;
            case 1:
                setNombre("piscina-2-" + solar.getNombre());
                break;
            case 2:
                setNombre("piscina-3-" + solar.getNombre());
                break;
        }
        consola.imprimir("Se ha construido una piscina en " + solar.getNombre() +
                ". La fortuna de " + solar.getPropietario().getNombre() + " se reduce en " + getValor() + "€.");
    }
}
