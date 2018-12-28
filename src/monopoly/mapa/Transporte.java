package monopoly.mapa;

import monopoly.persona.Jugador;

public class Transporte extends Propiedad {

    public Transporte(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
        setValor((Valor.CANTIDAD_PASAR_SALIDA));
        setAlquiler(getValor()/2);
    }

    @Override
    public int alquiler() {
        return getAlquiler();
    }

    @Override
    public String info() {
        return "{\n "
                + "\t tipo: Transporte"
                + ",\n\t nombre: " + getNombre()
                + ",\n\t propietario: " + getPropietario().getNombre()
                + ",\n\t valor: " + getValor()
                + ",\n\t alquiler: " + getAlquiler()
                + "\n}";
    }
}
