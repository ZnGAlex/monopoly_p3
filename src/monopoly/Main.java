package monopoly;

import monopoly.excepciones.ExcepcionCasilla;
import monopoly.excepciones.ExcepcionPersona;
import monopoly.mapa.*;

public class Main {

    ////////////////////////////////////////////////////////////
    // EMPREGAR FONTE MONOSPACED, P.E. COMO CONSOLAS          //
    ////////////////////////////////////////////////////////////
    public static void main(String[] args) throws ExcepcionPersona, ExcepcionCasilla {
        Main j = new Main();
        new Juego();
    }
    
}
