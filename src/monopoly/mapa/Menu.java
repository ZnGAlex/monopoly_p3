package monopoly.mapa;

import java.util.*;

import monopoly.persona.*;
import static monopoly.Juego.consola;

public class Menu {
    
    Tablero tablero;
    HashMap<String, Avatar> avatares;
    HashMap<String, Jugador> jugadores;
    ArrayList<Jugador> jgdrs;

    public Menu() {
        tablero = new Tablero();
        avatares = new HashMap<>();
        jugadores = new HashMap<>();
        jgdrs = new ArrayList<>();
        boolean iniciarJuego = false;

        do {
            /*BUCLE DE CREACION DE JUGADORES*/
            consola.imprimir(tablero.toString());
            consola.imprimir(imprimirOpciones());
            System.out.print("$> ");
            Scanner scanner = new Scanner(System.in);
            String orden = scanner.nextLine();
            String[] partes = orden.split(" ");
            String comando = partes[0];

            switch (comando) {
                case "crear":
                    if (!partes[1].equals("jugador") || partes.length != 4) {
                        consola.imprimir("Comando incorrecto.");
                    } else {
                        Character id;
                        boolean seRepite;
                        do {
                            seRepite = false;
                            id = (char) Math.ceil(Math.random() * 255);
                            Iterator it = avatares.values().iterator();
                            while (it.hasNext()) {
                                Avatar av = (Avatar) it.next();
                                if (av.getId().equals(id.toString())) {
                                    seRepite = true;
                                }
                            }
                        } while (id < 48 || (id > 57 && id < 65) || (id > 90 && id < 97) || id > 122 || seRepite);
                        /*Limitacion de avatares para permitir su introduccion por teclado*/
                        Jugador j = new Jugador(partes[2], partes[3], tablero.getCasillas().get(0).get(0), id.toString());
                        jugadores.put(partes[2], j);
                        avatares.put(j.getAvatar().getId(), j.getAvatar());
                        jgdrs.add(j);
                        tablero.getCasillas().get(0).get(0).setAvatares(avatares);
                        if (jgdrs.size() == 6) {
                            iniciarJuego = true;
                        }
                    }
                    break;
                case "iniciar":
                    /*Iniciar juego*/
                    if (partes.length != 2) {
                        consola.imprimir("Comando incorrecto. Inicie con iniciar juego");
                    }
                    if (!partes[1].equals("juego")) {
                        consola.imprimir("Comando incorrecto.");
                    } else if (jgdrs.size() < 2) {
                        consola.imprimir("Jugadores insuficientes.");
                    } else {
                        iniciarJuego = true;
                    }
                    break;
                case "salir":
                    /*salir del programa*/
                    System.exit(0);
                    break;
                default:
                    consola.imprimir("Comando incorrecto");
                    break;
            }
        } while (!iniciarJuego);

        tablero.iniciarCaidaCasilla(jgdrs);
        Turno turno = new Turno(jgdrs);
        Iterator avatares_i = avatares.values().iterator();
        /*Insercion de avatares y jugadores en el tablero*/
        while (avatares_i.hasNext()) {
            Avatar av = (Avatar) avatares_i.next();
            this.tablero.getCasillas().get(0).get(0).getAvatares().put(av.getId(), av);
            this.tablero.getAvatares().put(av.getId(), av);
        }
        tablero.setJugadores(jugadores);

        consola.imprimir(tablero.toString());

        boolean modoCambiado = false;

        while (true) {
            /*BUCLE DE JUEGO*/
            consola.imprimir(imprimirOpcionesJugador());
            /*comandos*/
            System.out.print("$> ");
            Scanner scanner = new Scanner(System.in);
            String orden = scanner.nextLine();
            String[] partes = orden.split(" ");
            String comando = partes[0];

            switch (comando) {
                case "salir":
                    /*salir del programa*/
                    if (partes.length == 1) {
                        consola.imprimir("\nGracias por jugar.");
                        return;
                    } else if (partes[1].equals("carcel")) {
                        /*salir de carcel pagando*/
                        if (turno.turnoActual().getInCarcel()) {
                            turno.turnoActual().salirCarcel();
                        } else {
                            consola.imprimir("El jugador no esta en la carcel");
                        }
                    }
                    break;
                case "describir":
                    /*describir jugador/avatar/casilla*/
                    if (partes.length > 3) {
                        consola.imprimir("\nComando incorrecto.");
                    } else {
                        switch (partes[1]) {
                            case "jugador":
                                if (partes.length < 3) {
                                    consola.imprimir("Indique el nombre del jugador.");
                                    break;
                                }
                                if (jugadores.get(partes[2]) == null) {
                                    consola.imprimir("El jugador " + partes[2] + " no existe.");
                                } else {
                                    consola.imprimir(jugadores.get(partes[2]).toString());
                                }
                                break;
                            case "avatar":
                                if (partes.length < 3) {
                                    consola.imprimir("Indique el nombre del avatar.");
                                    break;
                                }
                                if (!tablero.getAvatares().containsKey(partes[2])) {
                                    consola.imprimir("El avatar " + partes[2] + " no existe.");
                                } else {
                                    consola.imprimir(tablero.getAvatares().get(partes[2]).toString());
                                }
                                break;
                            default:
                                if (tablero.casillaByName(partes[1]) == null) {
                                    consola.imprimir("La casilla " + partes[1] + " no existe.");
                                } else {
                                    consola.imprimir(tablero.casillaByName(partes[1]).info());
                                }
                                break;
                        }
                    }
                    break;
                case "jugador":
                    /*mostrar turno actual*/
                    consola.imprimir("\t nombre: " + turno.turnoActual().getNombre());
                    consola.imprimir("\t avatar: " + turno.turnoActual().getAvatar().getId());
                    consola.imprimir("\t modo avanzado: " + turno.turnoActual().getModoEspecial());
                    break;
                case "lanzar":
                    /*lanzar los dados*/
                    if (!partes[1].equals("dados")) {
                        consola.imprimir("\nComando incorrecto.");
                    } else if (turno.turnoActual().getDadosTirados()) {
                        consola.imprimir("El jugador " + turno.turnoActual().getNombre() + " ya ha lanzado los dados.");
                    } else {
                        if (turno.turnoActual().getBloqueoTiroModoEspecial()) {
                            consola.imprimir("El jugador esta bloqueado, no puede tirar los dados. LLeva " + turno.turnoActual().getTurnosBloqueoModoEspecial() + " turnos bloqueado.");
                        } else if (turno.turnoActual().getModoEspecial())
                            turno.turnoActual().tirarDadosJugadorEspecial(tablero, turno);
                        else
                            turno.turnoActual().tirarDadosJugador(tablero, turno);
                    }
                    consola.imprimir(tablero.toString());
                    break;
                case "acabar":
                    /*acabar turno*/
                    if (!partes[1].equals("turno"))
                        consola.imprimir("Comando incorrecto.");
                    else if (turno.turnoActual().getAvatar().getFicha().equals(Valor.COCHE) && turno.turnoActual().getModoEspecial()) {
                        turno.turnoActual().setDadosTirados(false);
                        turno.turnoActual().setTurnosDadosTiradosEspecial(0);
                        turno.siguienteTurno();
                    } else if (turno.turnoActual().getBloqueoTiroModoEspecial()) {
                        turno.turnoActual().aumentarTurnosBloqueoTiroModoEspecial();
                        if (turno.turnoActual().getTurnosBloqueoModoEspecial() == 2) {
                            consola.imprimir("El jugador acabo su bloqueo de tiros.");
                            turno.turnoActual().setBloqueoTiroModoEspecial(false);
                            turno.turnoActual().setTurnosBloqueoModoEspecial(0);
                        }
                        turno.siguienteTurno();
                    } else if (turno.turnoActual().getDadosTirados()) {
                        turno.turnoActual().setDadosTirados(false);
                        turno.siguienteTurno();
                        modoCambiado = false;
                    } else {
                        consola.imprimir("Debes lanzar los dados antes de acabar tu turno");
                    }
                    break;
                case "ver":
                    /*ver tablero*/
                    if (!partes[1].equals("tablero")) {
                        consola.imprimir("Comando incorrecto.");
                    } else {
                        consola.imprimir(tablero.toString());
                    }
                    break;
                case "listar":
                    /*listar jugadores/avatares/enventa*/
                    switch (partes[1]) {
                        case "jugadores":
                            for (Jugador jugador : tablero.getJugadores().values()) {
                                consola.imprimir(jugador.toString());
                            }
                            consola.imprimir(tablero.toString());
                            break;
                        case "avatares":
                            for (Avatar avatar : tablero.getAvatares().values()) {
                                consola.imprimir(avatar.toString());
                            }
                            consola.imprimir(tablero.toString());
                            break;
                        case "enventa":
                            consola.imprimir(tablero.toString());
                            for (Casilla casilla : tablero.casillasEnVenta()) {
                                consola.imprimir(casilla.toString());
                            }
                            break;
                        case "edificios":
                            if (partes.length == 2)
                                tablero.imprimirEdificios();
                            else if (partes.length == 3) {
                                if (tablero.getGrupos().containsKey(partes[2])) {
                                    Grupo g = tablero.getGrupos().get(partes[2]);
                                    g.imprimirEdificios();
                                } else
                                    consola.imprimir("El grupo no existe.");
                            }
                            break;
                        default:
                            consola.imprimir("Comando incorrecto.");
                    }
                    break;
                case "comprar":
                    /*comprar casilla*/
                    if (partes.length > 2) {
                        consola.imprimir("Comando incorrecto.");
                    } else {
                        if (tablero.casillaByName(partes[1]) == null)
                            consola.imprimir("La casilla " + partes[1] + " no existe.");
                        else if (turno.turnoActual().getAvatar().getCasilla().getNombre().equals(partes[1])) /*si se encuentra en la casilla que quiere comprar, la compra*/ {
                            if (!turno.turnoActual().getModoEspecial()) {
                                turno.turnoActual().comprarCasilla(tablero);
                            } else if (!turno.turnoActual().getAvatar().getFicha().equals(Valor.COCHE) && turno.turnoActual().getModoEspecial()) {
                                turno.turnoActual().comprarCasilla(tablero);
                                consola.imprimir("El jugador " + turno.turnoActual().getNombre() + " ha comprado la casilla " + partes[1]);
                            } else if (turno.turnoActual().getModoEspecial() && turno.turnoActual().getAvatar().getFicha().equals(Valor.COCHE) && !turno.turnoActual().getHaCompradoModoEspecial()) {
                                turno.turnoActual().comprarCasilla(tablero);
                                turno.turnoActual().setHaCompradoModoEspecial(true);
                            } else if (turno.turnoActual().getModoEspecial() && turno.turnoActual().getAvatar().getFicha().equals(Valor.COCHE) && turno.turnoActual().getHaCompradoModoEspecial()) {
                                consola.imprimir("El jugador ya ha comprado durante su turno en el modo especial.");
                            }
                        } else {
                            consola.imprimir("No estas en " + partes[1]);
                        }

                    }
                    break;
                case "edificar":
                    if (partes.length > 2) {
                        consola.imprimir("Comando incorrecto.");
                    } else {
                        Jugador j = turno.turnoActual();
                        Solar c = (Solar) j.getAvatar().getCasilla();
                        if (c.getNumMaximoCasas() == 0) {
                            consola.imprimir("No se puede edificar en " + turno.turnoActual().getAvatar().getCasilla().getNombre());
                        } else {
                            if (!c.getPropietario().getNombre().equals(j.getNombre())) {
                                consola.imprimir("El jugador " + j.getNombre() + " no es propietario de " + c.getNombre());
                            } else if (!c.getEdificable()) {
                                consola.imprimir("El jugador no ha caido 2 veces en " + c.getNombre());
                            } else if (c.getHipotecada()) {
                                consola.imprimir("La casilla " + c.getNombre() + " esta hipotecada. No se puede edificar en ella.");
                            } else {
                                switch (partes[1]) {
                                    case "casa":
                                        if (j.getFortuna() < (c.getValor() * 0.60)) {
                                            consola.imprimir("El jugador " + j.getNombre() + " no dispone de suficiente dinero para edificar una casa.");
                                        } else {
                                            c.edificar(Valor.EDIFICIO_CASA, j);
                                        }
                                        break;
                                    case "hotel":
                                        if (j.getFortuna() < (c.getValor() * 0.60)) {
                                            consola.imprimir("El jugador " + j.getNombre() + " no dispone de suficiente dinero para edificar un hotel.");
                                        } else {
                                            c.edificar(Valor.EDIFICIO_HOTEL, j);
                                        }
                                        break;
                                    case "pista":
                                        if (j.getFortuna() < (c.getValor() * 0.40)) {
                                            consola.imprimir("El jugador " + j.getNombre() + " no dispone de suficiente dinero para edificar una pista.");
                                        } else {
                                            c.edificar(Valor.EDIFICIO_PISTA, j);
                                        }
                                        break;
                                    case "piscina":
                                        if (j.getFortuna() < (c.getValor() * 1.25)) {
                                            consola.imprimir("El jugador " + j.getNombre() + " no dispone de suficiente dinero para edificar una piscina.");
                                        } else {
                                            c.edificar(Valor.EDIFICIO_PISCINA, j);
                                        }
                                        break;
                                    default:
                                        consola.imprimir("Comando incorrecto.");
                                        break;
                                }
                            }
                        }
                    }
                    break;

                case "hipotecar":
                    if (partes.length != 2) {
                        consola.imprimir("Comando incorrecto");
                    } else if (tablero.casillaByName(partes[1]) == null) {
                        consola.imprimir("La casilla no existe");
                    } else {
                        turno.turnoActual().hipotecar((Solar) tablero.casillaByName(partes[1]));
                    }
                    break;

                case "deshipotecar":
                    if (partes.length != 2) {
                        consola.imprimir("Comando incorrecto");
                    } else if (tablero.casillaByName(partes[1]) == null) {
                        consola.imprimir("La casilla no existe");
                    } else {
                        turno.turnoActual().deshipotecar(tablero.casillaByName(partes[1]));
                    }
                    break;
                case "estadisticas":
                    if (partes.length == 1) {
                        tablero.estadisticas();
                    } else if (partes.length == 2) {
                        if (tablero.getJugadores().get(partes[1]) == null) {
                            if (!tablero.getJugadores().containsKey(partes[1])) {
                                consola.imprimir("El jugador " + partes[1] + " no existe.");
                            } else {
                                consola.imprimir(tablero.getJugadores().get(partes[1]).estadisticasJugador());
                            }
                        } else {
                            consola.imprimir("Comando incorrecto");
                            if (!tablero.getJugadores().containsKey(partes[1])) {
                                consola.imprimir("El jugador " + partes[1] + " no existe.");
                            } else {
                                consola.imprimir(tablero.getJugadores().get(partes[1]).estadisticasJugador());
                            }
                        }
                    }
                    break;
                case "cambiar":
                    if (partes.length != 2) {
                        consola.imprimir("Comando incorrecto.");
                    } else {
                        if (modoCambiado)
                            consola.imprimir("El jugador ya ha cambiado su modo de movimiento.");
                        else if (partes[1].equals("modo")) {
                            if (!turno.turnoActual().getModoEspecial()) {
                                turno.turnoActual().cambiarModo();
                                modoCambiado = true;
                                consola.imprimir("A partir de ahora, el avatar " + turno.turnoActual().getAvatar().getId() + " de tipo " + turno.turnoActual().getAvatar().getFicha() + " se movera en modo avanzado.");
                            } else {
                                turno.turnoActual().cambiarModo();
                                modoCambiado = false;
                                consola.imprimir("A partir de ahora, el avatar " + turno.turnoActual().getAvatar().getId() + " de tipo " + turno.turnoActual().getAvatar().getFicha() + " se movera en modo normal.");
                            }
                        }
                    }
                    break;
                case "vender":
                    if (partes.length != 4) {
                        consola.imprimir("Comando incorrecto.");
                    } else {
                        if (tablero.casillaByName(partes[2]) == null) {
                            consola.imprimir("La casilla " + partes[2] + " no existe.");
                        } else if (tablero.casillaByName(partes[2]).getPropietario() != turno.turnoActual())
                            consola.imprimir("La casilla " + partes[2] + " no pertenece a " + turno.turnoActual().getNombre());
                        else {
                            Solar s = (Solar) tablero.casillaByName(partes[2]);
                            switch (partes[1]) {
                                case "casas":
                                    s.venderEdificios(Valor.EDIFICIO_CASA, Integer.parseInt(partes[3]));
                                    break;
                                case "hoteles":
                                    s.venderEdificios(Valor.EDIFICIO_HOTEL, Integer.parseInt(partes[3]));
                                    break;
                                case "piscinas":
                                    s.venderEdificios(Valor.EDIFICIO_PISCINA, Integer.parseInt(partes[3]));
                                    break;
                                case "pistas":
                                    s.venderEdificios(Valor.EDIFICIO_PISTA, Integer.parseInt(partes[3]));
                                    break;
                                default:
                                    consola.imprimir("Comando incorrecto.");
                                    break;
                            }
                        }
                    }
                    break;
                default:
                    consola.imprimir("\nComando incorrecto.");
                    break;
            }
        }
    }

    public String imprimirOpciones() {
        return "Comandos:\n crear jugador [nombre] [coche/sombrero/esfinge/pelota]\n iniciar juego\n salir";
    }

    public String imprimirOpcionesJugador() {
        return "Comandos:\n lanzar dados\n comprar [casilla]\n hipotecar [casilla]\n deshipotecar [casilla]\n edificar [casa/hotel/piscina/pista]\n vender [casas/hoteles/piscinas/pistas] [cantidad]\n listar [enventa/jugadores/avatares/edificios (grupo)]\n salir carcel\n acabar turno\n describir jugador [nombre]\n describir [casilla]\n describir avatar [avatar]\n estadisticas\n estadisticas [jugador]\n cambiar modo\n ver tablero";
    }
}
