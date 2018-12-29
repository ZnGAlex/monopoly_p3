package monopoly.interfaces;

import monopoly.excepciones.ExcepcionCasilla;
import monopoly.excepciones.ExcepcionDados;
import monopoly.mapa.*;
import monopoly.persona.Avatar;
import monopoly.persona.Jugador;

import java.util.ArrayList;
import java.util.HashMap;
import monopoly.excepciones.ExcepcionTrato;

public interface Comando {

    void crearJugador(HashMap<String, Jugador> jugadores, HashMap<String, Avatar> avatares, ArrayList<Jugador> jgdrs, Tablero tablero, String nombre, String tipo);

    void describirJugador(HashMap<String, Jugador> jugadores, String nombre);

    void describirAvatar(HashMap<String, Avatar> avatares, String nombre);

    void describirCasilla(Casilla casillas);

    void turnoActual(Turno turno);

    void lanzarDados(Turno turno) throws ExcepcionDados, ExcepcionCasilla;

    void acabarTurno(Turno turno, boolean modoCambiado);

    void verTablero(Tablero tablero);

    void listarJugadores(HashMap<String, Jugador> jugadores);

    void listarAvatares(HashMap<String, Avatar> avatares);

    void listarEnVenta(Tablero tablero);

    void listarEdificios(Tablero tablero);

    void listarEdificiosGrupo(Grupo grupo);

    void comprar(Turno turno, String casilla);

    void edificar(Jugador j, Solar c, String tipo);

    void hipotecar(Turno turno, Tablero tablero, String casilla);

    void deshipotecar(Turno turno, Tablero tablero, String casilla);

    void estadisticas(Tablero tablero, String nombre);

    void cambiarModo(Turno turno, boolean modoCambiado);

    void vender(Solar s, String tipo, Integer cantidad);

    Trato crearTrato(String[] comando) throws ExcepcionTrato;
    
    void imprimirTratos();
    
    void eliminarTrato(String nombre);

}
