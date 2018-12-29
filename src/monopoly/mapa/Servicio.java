package monopoly.mapa;

import monopoly.persona.Jugador;

import java.util.Iterator;

public class Servicio extends Propiedad {

    public Servicio(String nombre, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
        setValor((int)(Valor.CANTIDAD_PASAR_SALIDA * 0.75));
        setAlquiler(getValor()/2);
    }

    @Override
    public int alquiler() {
        int numEstaciones = 0, valor = 0;
        Jugador j = getPropietario();
        Iterator<Propiedad> c_it = j.getPropiedades().values().iterator();
        while (c_it.hasNext()) {
            Propiedad p = c_it.next();
            if (p.getNombre().equals(Valor.CASILLA_SERVICIO))
                numEstaciones++;
        }
        if (numEstaciones == 1)
            valor = Valor.FACTOR_SERVICIO * 4;
        else
            valor = Valor.FACTOR_SERVICIO * 10;

        return valor;
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
