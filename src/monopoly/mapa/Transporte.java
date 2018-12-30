package monopoly.mapa;

import monopoly.persona.Jugador;

import java.util.Iterator;

public class Transporte extends Propiedad {

    public Transporte(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
        setValor((Valor.CANTIDAD_PASAR_SALIDA));
        setAlquiler(getValor()/2);
    }

    @Override
    public int alquiler() {
        int numEstaciones = 0;
        Jugador j = getPropietario();
        Iterator<Propiedad> c_it = j.getPropiedades().values().iterator();
        while (c_it.hasNext()) {
            Propiedad p = c_it.next();
            if (p.getNombre().contains("Transporte"))
                numEstaciones++;
        }
        int valorPagar = (int) (getAlquiler() * numEstaciones * 0.25);

        return valorPagar;
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
