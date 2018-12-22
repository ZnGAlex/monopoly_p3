package monopoly.mapa;

import monopoly.persona.Jugador;

public class Servicio extends Propiedad {

    public Servicio(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
    }

    @Override
    public int alquiler() {
        return 0;
    }

    @Override
    public String info() {
        return "{\n "
                + "\t tipo: Servicio"
                + ",\n\t propietario: " + getPropietario().getNombre()
                + ",\n\t valor: " + getValor()
                + ",\n\t alquiler: " + getAlquiler()
                + "\n}";
    }
}
