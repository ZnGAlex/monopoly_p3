package monopoly.mapa;

import static monopoly.mapa.Juego.consola;

final public class Hotel extends Edificio {
    public Hotel(Solar solar) {
        super(solar);
        setTipo(Valor.EDIFICIO_HOTEL);
        setValor((int) (solar.getValor() * 0.6));
        setAlquiler((int) (solar.getValor() * 0.1 * 70));
        switch (solar.getNumHoteles()) {
            case 0:
                setNombre("hotel-1-" + solar.getNombre());
                break;
            case 1:
                setNombre("hotel-2-" + solar.getNombre());
                break;
            case 2:
                setNombre("hotel-3-" + solar.getNombre());
                break;
        }
        consola.imprimir("Se ha construido un hotel en " + solar.getNombre() +
                ". La fortuna de " + solar.getPropietario().getNombre() + " se reduce en " + getValor() + "€.");
    }
}
