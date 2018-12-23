package monopoly.mapa;

import java.util.ArrayList;

import monopoly.persona.Jugador;

import static monopoly.mapa.Juego.consola;


public class Turno {
    private ArrayList<Jugador> jugadores;
    private int turno;

    // constructores

    public Turno() {
        this.jugadores = new ArrayList<>();
    }
    
    public Turno(ArrayList<Jugador> jugadores){
        if (jugadores == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Jugadores nulo.");
            System.exit(1);
        }
        this.jugadores = jugadores;
        this.turno = 0;
    }
    
    // getters y setters
    
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        if (jugadores == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Jugadores nulo.");
            System.exit(1);
        }
        this.jugadores = jugadores;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        if (turno < 0 || turno >= jugadores.size()) {
            consola.imprimir(Valor.ANSI_ROJO + "Turno no valido.");
            System.exit(1);
        }
        this.turno = turno;
    }
    
    // metodos
    /*
     * devuelve el jugador del turno actual
     */
    public Jugador turnoActual(){
        return jugadores.get(turno);
    }
    
    public void siguienteTurno(){
        turno = (turno+1)%jugadores.size();
        consola.imprimir("Turno de " + jugadores.get(turno).getNombre());
    }
}
