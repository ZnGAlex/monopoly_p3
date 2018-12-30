package monopoly.mapa;

import monopoly.excepciones.ExcepcionCasilla;
import monopoly.persona.Esfinge;
import monopoly.persona.Jugador;
import monopoly.persona.Sombrero;

import static monopoly.mapa.Juego.consola;

final public class CajaComunidad extends Carta {

    public CajaComunidad(int numCarta) {
        if (numCarta < 0 || numCarta > Valor.ACCIONES_CAJA.size()) {
            consola.imprimir(Valor.ANSI_ROJO + "numCarta no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        
        this.numCarta = numCarta;
        setFraseAccion(Valor.ACCIONES_CAJA.get(numCarta - 1));
    }

    @Override
    public void accion(Jugador jugador, Tablero tablero, Turno turno) throws ExcepcionCasilla {
        consola.imprimir("Accion: " + this.fraseAccion);
        switch (this.numCarta) {
            case 1:
                if (jugador.getFortuna() < 5000) {
                    jugador.hipotecarOBancarrota(tablero.getCasillas().get(0).get(0).getPropietario(), tablero, turno, 5000);
                }
                if (!jugador.getBancarrota()) {
                    jugador.setFortuna(jugador.getFortuna() - 5000);
                    if (jugador.getAvatar() instanceof Esfinge && jugador.getModoEspecial())
                        ((Esfinge) jugador.getAvatar()).anhadirPerdida(5000);
                    else if (jugador.getAvatar() instanceof Sombrero && jugador.getModoEspecial())
                        ((Sombrero) jugador.getAvatar()).anhadirPerdida(5000);
                    Valor.DINERO_PARKING += 5000;
                    jugador.setPagoDeTasas(jugador.getPagoDeTasas() + 5000);
                }
                break;
            case 2:
                jugador.encarcelarJugador(tablero);
                turno.siguienteTurno();
                break;
            case 3:
                jugador.getAvatar().moverAvatarCasilla(tablero.casillaByName("Salida"));
                jugador.setFortuna(jugador.getFortuna() + Valor.CANTIDAD_PASAR_SALIDA);
                if (jugador.getAvatar() instanceof Esfinge && jugador.getModoEspecial())
                    ((Esfinge) jugador.getAvatar()).anhadirBeneficio(Valor.CANTIDAD_PASAR_SALIDA);
                else if (jugador.getAvatar() instanceof Sombrero && jugador.getModoEspecial())
                    ((Sombrero) jugador.getAvatar()).anhadirBeneficio(Valor.CANTIDAD_PASAR_SALIDA);
                jugador.setPasarPorCasillaDeSalida(jugador.getPasarPorCasillaDeSalida() + Valor.CANTIDAD_PASAR_SALIDA);
                break;
            case 4:
                jugador.setFortuna(jugador.getFortuna() + 20000);
                if (jugador.getAvatar() instanceof Esfinge && jugador.getModoEspecial())
                    ((Esfinge) jugador.getAvatar()).anhadirBeneficio(20000);
                else if (jugador.getAvatar() instanceof Sombrero && jugador.getModoEspecial())
                    ((Sombrero) jugador.getAvatar()).anhadirBeneficio(20000);
                jugador.setPremiosInversionesOBote(jugador.getPremiosInversionesOBote() + 20000);
                break;
            case 5:
                if (jugador.getFortuna() < 10000) {
                    jugador.hipotecarOBancarrota(tablero.getCasillas().get(0).get(0).getPropietario(), tablero, turno, 10000);
                }
                if (!jugador.getBancarrota()) {
                    jugador.setFortuna(jugador.getFortuna() - 10000);
                    if (jugador.getAvatar() instanceof Esfinge && jugador.getModoEspecial())
                        ((Esfinge) jugador.getAvatar()).anhadirPerdida(10000);
                    else if (jugador.getAvatar() instanceof Sombrero && jugador.getModoEspecial())
                        ((Sombrero) jugador.getAvatar()).anhadirPerdida(10000);
                    Valor.DINERO_PARKING += 10000;
                    jugador.setPagoDeTasas(jugador.getPagoDeTasas() + 10000);
                }
                break;
            case 6:
                jugador.setFortuna(jugador.getFortuna() + 5000);
                jugador.setPremiosInversionesOBote(jugador.getPremiosInversionesOBote() + 5000);
                break;
        }
    }

    @Override
    public void setNumCarta(int numCarta) {
        if (numCarta < 0 || numCarta > Valor.ACCIONES_CAJA.size()) {
            consola.imprimir(Valor.ANSI_ROJO + "numCarta no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.numCarta = numCarta;
    }

}
