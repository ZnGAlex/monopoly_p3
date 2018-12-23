package monopoly.mapa;

import static monopoly.mapa.Juego.consola;

final public class Casa extends Edificio {
    public Casa(Solar solar) {
        super(solar);
        setValor((int) (solar.getValor() * 0.6));
        switch (solar.getNumCasas()) {
            case 0:
                setNombre("casa-1-" + solar.getNombre());
                setAlquiler((int) (solar.getValor() * 0.1 * 5));
                break;
            case 1:
                setNombre("casa-2-" + solar.getNombre());
                setAlquiler((int) (solar.getValor() * 0.1 * 15));
                break;
            case 2:
                setNombre("casa-3-" + solar.getNombre());
                setAlquiler((int) (solar.getValor() * 0.1 * 35));
                break;
            case 3:
                setNombre("casa-4-" + solar.getNombre());
                setAlquiler((int) (solar.getValor() * 0.1 * 50));
                break;
        }
        consola.imprimir("Se ha construido una casa en " + solar.getNombre() +
                ". La fortuna de " + solar.getPropietario().getNombre() + " se reduce en " + getValor() + "€.");
    }
}
