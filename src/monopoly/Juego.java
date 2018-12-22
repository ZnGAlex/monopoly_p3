package monopoly;

import monopoly.interfaces.ConsolaNormal;
import monopoly.mapa.*;

public class Juego {

    public static ConsolaNormal consola;

    public Juego() {
        consola = new ConsolaNormal();
    }
    
    ////////////////////////////////////////////////////////////
    // EMPREGAR FONTE MONOSPACED, P.E. COMO CONSOLAS          //
    ////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        Juego j = new Juego();
        new Menu();
    }
    
}
