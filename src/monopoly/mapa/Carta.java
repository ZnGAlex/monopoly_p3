/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.mapa;

import java.util.ArrayList;
import monopoly.persona.Jugador;

/**
 *
 * @author Usuario
 */
public class Carta {

    String tipo;
    int numCarta;
    String accion;

    // constructores
    public Carta(String tipo, int numCarta) {
        if (tipo == null) {
            System.out.println(Valor.ANSI_ROJO + "tipo nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        if (!tipo.equals("suerte") && !tipo.equals("caja")) {
            System.out.println(Valor.ANSI_ROJO + "tipo no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        if (numCarta < 0 || numCarta > Valor.ACCIONES_SUERTE.size()) {
            System.out.println(Valor.ANSI_ROJO + "numCarta no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.tipo = tipo;
        this.numCarta = numCarta;
        if (tipo.equals("suerte")) {
            this.accion = Valor.ACCIONES_SUERTE.get(numCarta - 1);
        } else {
            this.accion = Valor.ACCIONES_CAJA.get(numCarta - 1);
        }
    }

    //getters y setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo == null) {
            System.out.println(Valor.ANSI_ROJO + "tipo nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        if (!tipo.equals("suerte") && !tipo.equals("caja")) {
            System.out.println(Valor.ANSI_ROJO + "tipo no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.tipo = tipo;
    }

    public int getNumCarta() {
        return numCarta;
    }

    public void setNumCarta(int numCarta) {
        if (numCarta < 0 || numCarta > Valor.ACCIONES_SUERTE.size()) {
            System.out.println(Valor.ANSI_ROJO + "numCarta no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.numCarta = numCarta;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        if (accion == null) {
            System.out.println(Valor.ANSI_ROJO + "accion nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.accion = accion;
    }

    // metodos
    public void realizarAccion(Jugador jugador, Tablero tablero, Turno turno) {
        if (jugador == null) {
            System.out.println(Valor.ANSI_ROJO + "jugador nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        System.out.println("Accion: " + this.accion);
        /*Se imprime la accion*/

        if (this.tipo.equals("suerte")) {
            /*Se realiza la accion, ya sea de suerte o de caja*/
            switch (this.numCarta) {
                case 1:
                    if (tablero.pasaPorSalida(jugador.getAvatar().getCasilla(), tablero.casillaByName("Transporte2"))) {
                        /*Si pasa por Salida, cobra*/
                        System.out.println(jugador.getNombre() + " pasa por Salida y cobra " + Valor.CANTIDAD_PASAR_SALIDA + "€");
                        jugador.setFortuna(jugador.getFortuna() + Valor.CANTIDAD_PASAR_SALIDA);
                        jugador.setPasarPorCasillaDeSalida(jugador.getPasarPorCasillaDeSalida() + Valor.CANTIDAD_PASAR_SALIDA);
                    }
                    jugador.getAvatar().moverAvatarCasilla(tablero.casillaByName("Tansporte2"));
                    /*Mover avatar a la casilla Transporte2*/
                    break;
                case 2:
                    jugador.getAvatar().moverAvatarCasilla(tablero.casillaByName("LosBaldios"), turno);
                    /*Mover avatar a la casilla LosBaldios*/
                    break;
                case 3:
                    jugador.setFortuna(jugador.getFortuna() + 5000);
                    jugador.setPremiosInversionesOBote(jugador.getPremiosInversionesOBote() + 5000);
                    break;
                case 4:
                    if (tablero.pasaPorSalida(jugador.getAvatar().getCasilla(), tablero.casillaByName("Dalaran"))) {
                        /*Si pasa por Salida, cobra*/
                        System.out.println(jugador.getNombre() + " pasa por Salida y cobra " + Valor.CANTIDAD_PASAR_SALIDA + "€");
                        jugador.setFortuna(jugador.getFortuna() + Valor.CANTIDAD_PASAR_SALIDA);
                        jugador.setPasarPorCasillaDeSalida(jugador.getPasarPorCasillaDeSalida() + Valor.CANTIDAD_PASAR_SALIDA);
                    }
                    jugador.getAvatar().moverAvatarCasilla(tablero.casillaByName("Dalaran"));
                    jugador.getAvatar().moverAvatarCasilla(tablero.casillaByName("Dalaran"), turno);
                    /*Mover avatar a la casilla Transporte2*/
                    break;
                case 5:
                    jugador.encarcelarJugador(tablero);
                    turno.siguienteTurno();
                    /*Encarcelar al jugador*/
                    break;
                case 6:
                    jugador.setFortuna(jugador.getFortuna() + 50000);
                    jugador.setPremiosInversionesOBote(jugador.getPremiosInversionesOBote() + 50000);
                    break;
                case 7:
                    int dinero = 0;
                    for(Edificio edificio: jugador.getEdificios()){
                        switch(edificio.getTipo()){
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
                        System.out.println(jugador.getNombre() + " paga " + dinero + " €");
                    }
                    
                    break;
            }
        } else {
            switch (this.numCarta) {
                case 1:
                    if (jugador.getFortuna() < 5000) {
                        jugador.hipotecarOBancarrota(tablero.getCasillas().get(0).get(0).getPropietario(), tablero, turno, 5000);
                    }
                    if (!jugador.getBancarrota()) {
                        jugador.setFortuna(jugador.getFortuna() - 5000);
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
                    jugador.setPasarPorCasillaDeSalida(jugador.getPasarPorCasillaDeSalida() + Valor.CANTIDAD_PASAR_SALIDA);
                    break;
                case 4:
                    jugador.setFortuna(jugador.getFortuna() + 20000);
                    jugador.setPremiosInversionesOBote(jugador.getPremiosInversionesOBote() + 20000);
                    break;
                case 5:
                    if (jugador.getFortuna() < 10000) {
                        jugador.hipotecarOBancarrota(tablero.getCasillas().get(0).get(0).getPropietario(), tablero, turno, 10000);
                    }
                    if (!jugador.getBancarrota()) {
                        jugador.setFortuna(jugador.getFortuna() - 10000);
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
    }

}
