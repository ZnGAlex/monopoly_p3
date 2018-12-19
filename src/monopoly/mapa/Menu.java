package monopoly.mapa;

import java.util.*;

import monopoly.persona.*;

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
            System.out.println(tablero);
            System.out.println(imprimirOpciones());
            System.out.print("$> ");
            Scanner scanner = new Scanner(System.in);
            String orden = scanner.nextLine();
            String[] partes = orden.split(" ");
            String comando = partes[0];

            switch (comando) {
                case "crear":
                    if (!partes[1].equals("jugador") || partes.length != 4) {
                        System.out.println("Comando incorrecto.");
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
                        System.out.println("Comando incorrecto. Inicie con iniciar juego");
                    }
                    if (!partes[1].equals("juego")) {
                        System.out.println("Comando incorrecto.");
                    } else if (jgdrs.size() < 2) {
                        System.out.println("Jugadores insuficientes.");
                    } else {
                        iniciarJuego = true;
                    }
                    break;
                case "salir":
                    /*salir del programa*/
                    System.exit(0);
                    break;
                default:
                    System.out.println("Comando incorrecto");
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

        System.out.println(tablero);

        boolean modoCambiado = false;

        while (true) {
            /*BUCLE DE JUEGO*/
            System.out.println(imprimirOpcionesJugador());
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
                        System.out.println("\nGracias por jugar.");
                        return;
                    } else if (partes[1].equals("carcel")) {
                        /*salir de carcel pagando*/
                        if (turno.turnoActual().getInCarcel()) {
                            turno.turnoActual().salirCarcel();
                        } else {
                            System.out.println("El jugador no esta en la carcel");
                        }
                    }
                    break;
                case "describir":
                    /*describir jugador/avatar/casilla*/
                    if (partes.length > 3) {
                        System.out.println("\nComando incorrecto.");
                    } else {
                        switch (partes[1]) {
                            case "jugador":
                                if (partes.length < 3) {
                                    System.out.println("Indique el nombre del jugador.");
                                    break;
                                }
                                if (jugadores.get(partes[2]) == null) {
                                    System.out.println("El jugador " + partes[2] + " no existe.");
                                } else {
                                    System.out.println(jugadores.get(partes[2]));
                                }
                                break;
                            case "avatar":
                                if (partes.length < 3) {
                                    System.out.println("Indique el nombre del avatar.");
                                    break;
                                }
                                if (!tablero.getAvatares().containsKey(partes[2])) {
                                    System.out.println("El avatar " + partes[2] + " no existe.");
                                } else {
                                    System.out.println(tablero.getAvatares().get(partes[2]));
                                }
                                break;
                            default:
                                if (tablero.casillaByName(partes[1]) == null) {
                                    System.out.println("La casilla " + partes[1] + " no existe.");
                                } else {
                                    System.out.println(tablero.casillaByName(partes[1]).info());
                                }
                                break;
                        }
                    }
                    break;
                case "jugador":
                    /*mostrar turno actual*/
                    System.out.println("\t nombre: " + turno.turnoActual().getNombre());
                    System.out.println("\t avatar: " + turno.turnoActual().getAvatar().getId());
                    System.out.println("\t modo avanzado: " + turno.turnoActual().getModoEspecial());
                    break;
                case "lanzar":
                    /*lanzar los dados*/
                    if (!partes[1].equals("dados")) {
                        System.out.println("\nComando incorrecto.");
                    } else if (turno.turnoActual().getDadosTirados()) {
                        System.out.println("El jugador " + turno.turnoActual().getNombre() + " ya ha lanzado los dados.");
                    } else {
                        if (turno.turnoActual().getBloqueoTiroModoEspecial()) {
                            System.out.println("El jugador esta bloqueado, no puede tirar los dados. LLeva " + turno.turnoActual().getTurnosBloqueoModoEspecial() + " turnos bloqueado.");
                        } else if (turno.turnoActual().getModoEspecial())
                            turno.turnoActual().tirarDadosJugadorEspecial(tablero, turno);
                        else
                            turno.turnoActual().tirarDadosJugador(tablero, turno);
                    }
                    System.out.println(tablero);
                    break;
                case "acabar":
                    /*acabar turno*/
                    if (!partes[1].equals("turno"))
                        System.out.println("Comando incorrecto.");
                    else if (turno.turnoActual().getAvatar().getFicha().equals(Valor.COCHE) && turno.turnoActual().getModoEspecial()) {
                        turno.turnoActual().setDadosTirados(false);
                        turno.turnoActual().setTurnosDadosTiradosEspecial(0);
                        turno.siguienteTurno();
                    } else if (turno.turnoActual().getBloqueoTiroModoEspecial()) {
                        turno.turnoActual().aumentarTurnosBloqueoTiroModoEspecial();
                        if (turno.turnoActual().getTurnosBloqueoModoEspecial() == 2) {
                            System.out.println("El jugador acabo su bloqueo de tiros.");
                            turno.turnoActual().setBloqueoTiroModoEspecial(false);
                            turno.turnoActual().setTurnosBloqueoModoEspecial(0);
                        }
                        turno.siguienteTurno();
                    } else if (turno.turnoActual().getDadosTirados()) {
                        turno.turnoActual().setDadosTirados(false);
                        turno.siguienteTurno();
                        modoCambiado = false;
                    } else {
                        System.out.println("Debes lanzar los dados antes de acabar tu turno");
                    }
                    break;
                case "ver":
                    /*ver tablero*/
                    if (!partes[1].equals("tablero")) {
                        System.out.println("Comando incorrecto.");
                    } else {
                        System.out.println(tablero);
                    }
                    break;
                case "listar":
                    /*listar jugadores/avatares/enventa*/
                    switch (partes[1]) {
                        case "jugadores":
                            for (Jugador jugador : tablero.getJugadores().values()) {
                                System.out.println(jugador);
                            }
                            System.out.println(tablero);
                            break;
                        case "avatares":
                            for (Avatar avatar : tablero.getAvatares().values()) {
                                System.out.println(avatar);
                            }
                            System.out.println(tablero);
                            break;
                        case "enventa":
                            System.out.println(tablero);
                            for (Casilla casilla : tablero.casillasEnVenta()) {
                                System.out.println(casilla.shortInfo());
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
                                    System.out.println("El grupo no existe.");
                            }
                            break;
                        default:
                            System.out.println("Comando incorrecto.");
                    }
                    break;
                case "comprar":
                    /*comprar casilla*/
                    if (partes.length > 2) {
                        System.out.println("Comando incorrecto.");
                    } else {
                        if (tablero.casillaByName(partes[1]) == null)
                            System.out.println("La casilla " + partes[1] + " no existe.");
                        else if (turno.turnoActual().getAvatar().getCasilla().getNombre().equals(partes[1])) /*si se encuentra en la casilla que quiere comprar, la compra*/ {
                            if (!turno.turnoActual().getModoEspecial()) {
                                turno.turnoActual().comprarCasilla(tablero);
                            } else if (!turno.turnoActual().getAvatar().getFicha().equals(Valor.COCHE) && turno.turnoActual().getModoEspecial()) {
                                turno.turnoActual().comprarCasilla(tablero);
                                System.out.println("El jugador " + turno.turnoActual().getNombre() + " ha comprado la casilla " + partes[1]);
                            } else if (turno.turnoActual().getModoEspecial() && turno.turnoActual().getAvatar().getFicha().equals(Valor.COCHE) && !turno.turnoActual().getHaCompradoModoEspecial()) {
                                turno.turnoActual().comprarCasilla(tablero);
                                turno.turnoActual().setHaCompradoModoEspecial(true);
                            } else if (turno.turnoActual().getModoEspecial() && turno.turnoActual().getAvatar().getFicha().equals(Valor.COCHE) && turno.turnoActual().getHaCompradoModoEspecial()) {
                                System.out.println("El jugador ya ha comprado durante su turno en el modo especial.");
                            }
                        } else {
                            System.out.println("No estas en " + partes[1]);
                        }

                    }
                    break;
                case "edificar":
                    if (partes.length > 2) {
                        System.out.println("Comando incorrecto.");
                    } else {
                        if (turno.turnoActual().getAvatar().getCasilla().getNumMaximoCasas() == 0) {
                            System.out.println("No se puede edificar en " + turno.turnoActual().getAvatar().getCasilla().getNombre());
                        } else {
                            Jugador j = turno.turnoActual();
                            Casilla c = j.getAvatar().getCasilla();
                            if (!c.getPropietario().getNombre().equals(j.getNombre())) {
                                System.out.println("El jugador " + j.getNombre() + " no es propietario de " + c.getNombre());
                            } else if (!c.getEdificable()) {
                                System.out.println("El jugador no ha caido 2 veces en " + c.getNombre());
                            } else if (c.getHipotecada()) {
                                System.out.println("La casilla " + c.getNombre() + " esta hipotecada. No se puede edificar en ella.");
                            } else {
                                switch (partes[1]) {
                                    case "casa":
                                        if (j.getFortuna() < (c.getValor() * 0.60)) {
                                            System.out.println("El jugador " + j.getNombre() + " no dispone de suficiente dinero para edificar una casa.");
                                        } else {
                                            c.edificar(Valor.EDIFICIO_CASA, j);
                                        }
                                        break;
                                    case "hotel":
                                        if (j.getFortuna() < (c.getValor() * 0.60)) {
                                            System.out.println("El jugador " + j.getNombre() + " no dispone de suficiente dinero para edificar un hotel.");
                                        } else {
                                            c.edificar(Valor.EDIFICIO_HOTEL, j);
                                        }
                                        break;
                                    case "pista":
                                        if (j.getFortuna() < (c.getValor() * 0.40)) {
                                            System.out.println("El jugador " + j.getNombre() + " no dispone de suficiente dinero para edificar una pista.");
                                        } else {
                                            c.edificar(Valor.EDIFICIO_PISTA, j);
                                        }
                                        break;
                                    case "piscina":
                                        if (j.getFortuna() < (c.getValor() * 1.25)) {
                                            System.out.println("El jugador " + j.getNombre() + " no dispone de suficiente dinero para edificar una piscina.");
                                        } else {
                                            c.edificar(Valor.EDIFICIO_PISCINA, j);
                                        }
                                        break;
                                    default:
                                        System.out.println("Comando incorrecto.");
                                        break;
                                }
                            }
                        }
                    }
                    break;

                case "hipotecar":
                    if (partes.length != 2) {
                        System.out.println("Comando incorrecto");
                    } else if (tablero.casillaByName(partes[1]) == null) {
                        System.out.println("La casilla no existe");
                    } else {
                        turno.turnoActual().hipotecar(tablero.casillaByName(partes[1]));
                    }
                    break;

                case "deshipotecar":
                    if (partes.length != 2) {
                        System.out.println("Comando incorrecto");
                    } else if (tablero.casillaByName(partes[1]) == null) {
                        System.out.println("La casilla no existe");
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
                                System.out.println("El jugador " + partes[1] + " no existe.");
                            } else {
                                System.out.println(tablero.getJugadores().get(partes[1]).estadisticasJugador());
                            }
                        } else {
                            System.out.println("Comando incorrecto");
                            if (!tablero.getJugadores().containsKey(partes[1])) {
                                System.out.println("El jugador " + partes[1] + " no existe.");
                            } else {
                                System.out.println(tablero.getJugadores().get(partes[1]).estadisticasJugador());
                            }
                        }
                    }
                    break;
                case "cambiar":
                    if (partes.length != 2) {
                        System.out.println("Comando incorrecto.");
                    } else {
                        if (modoCambiado)
                            System.out.println("El jugador ya ha cambiado su modo de movimiento.");
                        else if (partes[1].equals("modo")) {
                            if (!turno.turnoActual().getModoEspecial()) {
                                turno.turnoActual().cambiarModo();
                                modoCambiado = true;
                                System.out.println("A partir de ahora, el avatar " + turno.turnoActual().getAvatar().getId() + " de tipo " + turno.turnoActual().getAvatar().getFicha() + " se movera en modo avanzado.");
                            } else {
                                turno.turnoActual().cambiarModo();
                                modoCambiado = false;
                                System.out.println("A partir de ahora, el avatar " + turno.turnoActual().getAvatar().getId() + " de tipo " + turno.turnoActual().getAvatar().getFicha() + " se movera en modo normal.");
                            }
                        }
                    }
                    break;
                case "vender":
                    if (partes.length != 4) {
                        System.out.println("Comando incorrecto.");
                    } else {
                        if (tablero.casillaByName(partes[2]) == null) {
                            System.out.println("La casilla " + partes[2] + " no existe.");
                        } else if (tablero.casillaByName(partes[2]).getPropietario() != turno.turnoActual())
                            System.out.println("La casilla " + partes[2] + " no pertenece a " + turno.turnoActual().getNombre());
                        else {
                            switch (partes[1]) {
                                case "casas":
                                    tablero.casillaByName(partes[2]).venderEdificios(Valor.EDIFICIO_CASA, Integer.parseInt(partes[3]));
                                    break;
                                case "hoteles":
                                    tablero.casillaByName(partes[2]).venderEdificios(Valor.EDIFICIO_HOTEL, Integer.parseInt(partes[3]));
                                    break;
                                case "piscinas":
                                    tablero.casillaByName(partes[2]).venderEdificios(Valor.EDIFICIO_PISCINA, Integer.parseInt(partes[3]));
                                    break;
                                case "pistas":
                                    tablero.casillaByName(partes[2]).venderEdificios(Valor.EDIFICIO_PISTA, Integer.parseInt(partes[3]));
                                    break;
                                default:
                                    System.out.println("Comando incorrecto.");
                                    break;
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("\nComando incorrecto.");
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
