package monopoly.mapa;

import monopoly.persona.Jugador;

public class Servicio extends Propiedad {

    public Servicio(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
        setValor((int)(Valor.CANTIDAD_PASAR_SALIDA * 0.75));
        setAlquiler(getValor()/2);
    }

    @Override
    public int alquiler() {
        return getAlquiler();
    }

    @Override
    public String info() {
        return "{\n "
                + "\t tipo: Servicio"
                + ",\n\t nombre: " + getNombre()
                + ",\n\t propietario: " + getPropietario().getNombre()
                + ",\n\t valor: " + getValor()
                + ",\n\t alquiler: " + getAlquiler()
                + "\n}";
    }
}
