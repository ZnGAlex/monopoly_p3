package monopoly.mapa;

import monopoly.excepciones.ExcepcionCasilla;
import monopoly.persona.Esfinge;
import monopoly.persona.Jugador;
import static monopoly.mapa.Juego.consola;

final public class Suerte extends Carta {

    public Suerte(int numCarta) {
        if (numCarta < 0 || numCarta > Valor.ACCIONES_SUERTE.size()) {
            consola.imprimir(Valor.ANSI_ROJO + "numCarta no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.numCarta = numCarta;
        setFraseAccion(Valor.ACCIONES_SUERTE.get(numCarta - 1));
    }

    @Override
    public void accion(Jugador jugador, Tablero tablero, Turno turno) throws ExcepcionCasilla {
        consola.imprimir("Accion: " + this.fraseAccion);
        switch (this.numCarta) {
            case 1:
                if (tablero.pasaPorSalida(jugador.getAvatar().getCasilla(), tablero.casillaByName("Transporte2"))) {
                    // Si pasa por Salida, cobra
                    consola.imprimir(jugador.getNombre() + " pasa por Salida y cobra " + Valor.CANTIDAD_PASAR_SALIDA + "€");
                    jugador.setFortuna(jugador.getFortuna() + Valor.CANTIDAD_PASAR_SALIDA);
                    if (jugador.getAvatar() instanceof Esfinge && jugador.getModoEspecial())
                        ((Esfinge) jugador.getAvatar()).anhadirBeneficio(Valor.CANTIDAD_PASAR_SALIDA);
                    jugador.setPasarPorCasillaDeSalida(jugador.getPasarPorCasillaDeSalida() + Valor.CANTIDAD_PASAR_SALIDA);
                }
                jugador.getAvatar().moverAvatarCasilla(tablero.casillaByName("Transporte2"));
                // Mover avatar a la casilla Transporte2
                break;
            case 2:
                jugador.getAvatar().moverAvatarCasilla(tablero.casillaByName("LosBaldios"), turno);
                // Mover avatar a la casilla LosBaldios
                break;
            case 3:
                jugador.setFortuna(jugador.getFortuna() + 5000);
                if (jugador.getAvatar() instanceof Esfinge && jugador.getModoEspecial())
                    ((Esfinge) jugador.getAvatar()).anhadirBeneficio(5000);
                jugador.setPremiosInversionesOBote(jugador.getPremiosInversionesOBote() + 5000);
                break;
            case 4:
                if (tablero.pasaPorSalida(jugador.getAvatar().getCasilla(), tablero.casillaByName("Dalaran"))) {
                    // Si pasa por Salida, cobra
                    consola.imprimir(jugador.getNombre() + " pasa por Salida y cobra " + Valor.CANTIDAD_PASAR_SALIDA + "€");
                    jugador.setFortuna(jugador.getFortuna() + Valor.CANTIDAD_PASAR_SALIDA);
                    if (jugador.getAvatar() instanceof Esfinge && jugador.getModoEspecial())
                        ((Esfinge) jugador.getAvatar()).anhadirBeneficio(Valor.CANTIDAD_PASAR_SALIDA);
                    jugador.setPasarPorCasillaDeSalida(jugador.getPasarPorCasillaDeSalida() + Valor.CANTIDAD_PASAR_SALIDA);
                }
                jugador.getAvatar().moverAvatarCasilla(tablero.casillaByName("Dalaran"));
                jugador.getAvatar().moverAvatarCasilla(tablero.casillaByName("Dalaran"), turno);
                // Mover avatar a la casilla Transporte2
                break;
            case 5:
                jugador.encarcelarJugador(tablero);
                turno.siguienteTurno();
                // Encarcelar al jugador
                break;
            case 6:
                jugador.setFortuna(jugador.getFortuna() + 50000);
                if (jugador.getAvatar() instanceof Esfinge && jugador.getModoEspecial())
                    ((Esfinge) jugador.getAvatar()).anhadirBeneficio(50000);
                jugador.setPremiosInversionesOBote(jugador.getPremiosInversionesOBote() + 50000);
                break;
            case 7:
                int dinero = 0;
                for (Edificio edificio : jugador.getEdificios()) {
                    switch (edificio.getTipo()) {
                        case "casa":
                            dinero += 4000;
                            break;
                        case "hotel":
                            dinero += 11500;
                            break;
                        case "piscina":
                            dinero += 2000;
                            break;
                        case "pista":
                            dinero += 7500;
                            break;
                    }
                }
                if (jugador.getFortuna() < dinero) {
                    jugador.hipotecarOBancarrota(tablero.getCasillas().get(0).get(0).getPropietario(), tablero, turno, dinero);
                }
                if (!jugador.getBancarrota()) {
                    jugador.setFortuna(jugador.getFortuna() - dinero);
                    Valor.DINERO_PARKING += dinero;
                    jugador.setPagoDeTasas(jugador.getPagoDeTasas() + dinero);
                    consola.imprimir(jugador.getNombre() + " paga " + dinero + " €");
                }

                break;
        }
    }

    @Override
    public void setNumCarta(int numCarta) {
        if (numCarta < 0 || numCarta > Valor.ACCIONES_SUERTE.size()) {
            consola.imprimir(Valor.ANSI_ROJO + "numCarta no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.numCarta = numCarta;
    }
}
