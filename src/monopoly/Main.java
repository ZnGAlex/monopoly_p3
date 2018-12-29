package monopoly;

import monopoly.excepciones.ExcepcionCasilla;
import monopoly.excepciones.ExcepcionJugador;
import monopoly.mapa.*;

public class Main {

    ////////////////////////////////////////////////////////////
    // EMPREGAR FONTE MONOSPACED, P.E. COMO CONSOLAS          //
    ////////////////////////////////////////////////////////////
    public static void main(String[] args) throws ExcepcionJugador, ExcepcionCasilla {
        Main j = new Main();
        new Juego();
    }
    
}
