package monopoly.mapa;

import java.util.*;

import monopoly.excepciones.*;

import monopoly.interfaces.Comando;
import monopoly.interfaces.ConsolaNormal;
import monopoly.persona.*;

public class Juego implements Comando {

    public static ConsolaNormal consola;
    private Tablero tablero;
    private HashMap<String, Avatar> avatares;
    private HashMap<String, Jugador> jugadores;
    private ArrayList<Jugador> jgdrs;
    private HashMap<String, Trato> tratos;
    private Turno turno;

    public Juego() throws ExcepcionJugador, ExcepcionCasilla {
        consola = new ConsolaNormal();
        tablero = new Tablero();
        avatares = new HashMap<>();
        jugadores = new HashMap<>();
        jgdrs = new ArrayList<>();
        tratos = new HashMap<>();
        boolean iniciarJuego = false;

        do {
            /*BUCLE DE CREACION DE JUGADORES*/
            consola.imprimir(tablero.toString());
            consola.imprimir(imprimirOpciones());
            String orden = consola.leer("$> ");
            String[] partes = orden.split(" ");
            String comando = partes[0];

            switch (comando) {
                case "crear":
                    if (!partes[1].equals("jugador") || partes.length != 4) {
                        consola.imprimir("Comando incorrecto.");
                    } else {
                        crearJugador(jugadores, avatares, jgdrs, tablero, partes[2], partes[3]);
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
        turno = new Turno(jgdrs);
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

            String orden = consola.leer("$> ");
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
                                    describirJugador(jugadores, partes[2]);
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
                                    describirAvatar(tablero.getAvatares(), partes[2]);
                                }
                                break;
                            default:
                                if (tablero.casillaByName(partes[1]) == null) {
                                    consola.imprimir("La casilla " + partes[1] + " no existe.");
                                } else {
                                    try {
                                        describirCasilla(tablero.casillaByName(partes[1]));
                                    } catch (ExcepcionCasilla ex) {
                                        consola.imprimir(ex.getMessage());
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case "jugador":
                    /*mostrar turno actual*/
                    turnoActual(turno);
                    break;
                case "lanzar":
                    /*lanzar los dados*/
                    if (!partes[1].equals("dados")) {
                        consola.imprimir("\nComando incorrecto.");
                    } else {
                        try {
                            lanzarDados(turno);
                        } catch (ExcepcionDados ex) {
                            consola.imprimir(ex.getMessage());
                        }
                    }
                    consola.imprimir(tablero.toString());
                    break;
                case "acabar":
                    /*acabar turno*/
                    if (!partes[1].equals("turno")) {
                        consola.imprimir("Comando incorrecto.");
                    } else {
                        acabarTurno(turno, modoCambiado);
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
                            if (partes.length == 2) {
                                tablero.imprimirEdificios();
                            } else if (partes.length == 3) {
                                if (tablero.getGrupos().containsKey(partes[2])) {
                                    Grupo g = tablero.getGrupos().get(partes[2]);
                                    g.imprimirEdificios();
                                } else {
                                    consola.imprimir("El grupo no existe.");
                                }
                            }
                            break;
                        case "tratos":
                            if (partes.length != 2) {
                                consola.imprimir("Comando incorrecto");
                            } else {
                                for (String clave : tratos.keySet()) {
                                    if (tratos.get(clave).getJugador2() == turno.turnoActual()) {
                                        consola.imprimir("\t" + clave);
                                        consola.imprimir(tratos.get(clave).toString());
                                    }
                                }
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
                        if (tablero.casillaByName(partes[1]) == null) {
                            consola.imprimir("La casilla " + partes[1] + " no existe.");
                        } else if (turno.turnoActual().getAvatar().getCasilla().getNombre().equals(partes[1])) /*si se encuentra en la casilla que quiere comprar, la compra*/ {
                            comprar(turno, partes[1]);
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
                            edificar(j, c, partes[1]);
                        }
                    }
                    break;

                case "hipotecar":
                    if (partes.length != 2) {
                        consola.imprimir("Comando incorrecto");
                    } else if (tablero.casillaByName(partes[1]) == null) {
                        consola.imprimir("La casilla no existe");
                    } else {
                        hipotecar(turno, tablero, partes[1]);
                    }
                    break;

                case "deshipotecar":
                    if (partes.length != 2) {
                        consola.imprimir("Comando incorrecto");
                    } else if (tablero.casillaByName(partes[1]) == null) {
                        consola.imprimir("La casilla no existe");
                    } else {
                        deshipotecar(turno, tablero, partes[1]);
                    }
                    break;
                case "estadisticas":
                    if (partes.length == 1) {
                        tablero.estadisticas();
                    } else if (partes.length == 2) {
                        estadisticas(tablero, partes[1]);
                    }
                    break;
                case "cambiar":
                    if (partes.length != 2) {
                        consola.imprimir("Comando incorrecto.");
                    } else {
                        if (modoCambiado) {
                            consola.imprimir("El jugador ya ha cambiado su modo de movimiento.");
                        } else if (partes[1].equals("modo")) {
                            cambiarModo(turno, modoCambiado);
                        }
                    }
                    break;
                case "vender":
                    if (partes.length != 4) {
                        consola.imprimir("Comando incorrecto.");
                    } else {
                        if (tablero.casillaByName(partes[2]) == null) {
                            consola.imprimir("La casilla " + partes[2] + " no existe.");
                        } else if (tablero.casillaByName(partes[2]).getPropietario() != turno.turnoActual()) {
                            consola.imprimir("La casilla " + partes[2] + " no pertenece a " + turno.turnoActual().getNombre());
                        } else {
                            Solar s = (Solar) tablero.casillaByName(partes[2]);
                            vender(s, partes[1], Integer.parseInt(partes[3]));
                        }
                    }
                    break;
                case "trato":
                    if (partes.length < 5 || partes.length > 8) {
                        consola.imprimir("Comando incorrecto" + partes.length);
                    } else {
                        try {
                            String clave = "trato" + (tratos.size() + 1);
                            Trato trato = crearTrato(partes);
                            if (!seRepiteTrato(trato)) {
                                consola.imprimir(clave + ": " + trato.pregunta());
                                tratos.put(clave, crearTrato(partes));
                                for (Trato tr : tratos.values()) {
                                    consola.imprimir(tr.toString());
                                }
                            } else {
                                consola.imprimir("Trato repetido");
                            }
                        } catch (ExcepcionTrato ex) {
                            consola.imprimir(ex.getMessage());
                        }
                    }

                    break;
                case "eliminar":
                    if (partes.length != 2) {
                        consola.imprimir("Comando incorrecto");
                    } else if (tratos.get(partes[1]) == null) {
                        consola.imprimir("El " + partes[1] + " no existe");
                    } else if (tratos.get(partes[1]).getJugador1() != turno.turnoActual()) {
                        consola.imprimir("No has propuesto el " + partes[1]);
                    } else {
                        eliminarTrato(partes[1]);
                    }
                    break;
                case "aceptar":
                    if (partes.length != 2) {
                        consola.imprimir("Comando incorrecto");
                    } else if (tratos.get(partes[1]) == null) {
                        consola.imprimir("El " + partes[1] + " no existe");
                    } else if (tratos.get(partes[1]).getJugador2() != turno.turnoActual()) {
                        consola.imprimir("No te corresponde el " + partes[1]);
                    } else {
                        try {
                            tratos.get(partes[1]).aceptarTrato();
                            limpiarTratos();
                        } catch (ExcepcionTrato ex) {
                            consola.imprimir(ex.getMessage());
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
        return "Comandos:\n lanzar dados\n comprar [casilla]\n hipotecar [casilla]\n deshipotecar [casilla]\n edificar [casa/hotel/piscina/pista]\n vender [casas/hoteles/piscinas/pistas] [cantidad]\n listar [enventa/jugadores/avatares/edificios (grupo)]\n salir carcel\n acabar turno\n describir jugador [nombre]\n describir [casilla]\n describir avatar [avatar]\n estadisticas\n estadisticas [jugador]\n trato [jugador]: cambiar ([Prop1] (y [dinero]), [Prop2] (y dinero)) (y noalquiler([Prop3], [turnos]))\n aceptar [trato]\n eliminar [trato]\n cambiar modo\n ver tablero";
    }

    @Override
    public void crearJugador(HashMap<String, Jugador> jugadores, HashMap<String, Avatar> avatares, ArrayList<Jugador> jgdrs, Tablero tablero, String nombre, String tipo) {
        try {
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
            Jugador j = new Jugador(nombre, tipo, tablero.getCasillas().get(0).get(0), id.toString());
            jugadores.put(nombre, j);
            avatares.put(j.getAvatar().getId(), j.getAvatar());
            jgdrs.add(j);
            tablero.getCasillas().get(0).get(0).setAvatares(avatares);
        } catch (ExcepcionJugador ex) {
            consola.imprimir(ex.getMessage());
        }
    }

    @Override
    public void describirJugador(HashMap<String, Jugador> jugadores, String nombre) {
        consola.imprimir(jugadores.get(nombre).toString());
    }

    @Override
    public void describirAvatar(HashMap<String, Avatar> avatares, String nombre) {
        consola.imprimir(avatares.get(nombre).toString());
    }

    @Override
    public void describirCasilla(Casilla casilla) {
        consola.imprimir(casilla.info());
    }

    @Override
    public void turnoActual(Turno turno) {
        consola.imprimir("\t nombre: " + turno.turnoActual().getNombre());
        consola.imprimir("\t avatar: " + turno.turnoActual().getAvatar().getId());
        consola.imprimir("\t modo avanzado: " + turno.turnoActual().getModoEspecial());
    }

    @Override
    public void lanzarDados(Turno turno) throws ExcepcionCasilla, ExcepcionDados {
        if (turno.turnoActual().getDadosTirados()) {
            throw new ExcepcionDados("El jugador ya ha tirado los dados.");
        } else {
            if (turno.turnoActual().getBloqueoTiroModoEspecial()) {
                throw new ExcepcionDados("El jugador esta bloqueado, no puede tirar los dados. LLeva " + turno.turnoActual().getTurnosBloqueoModoEspecial() + " turnos bloqueado.");
            } else if (turno.turnoActual().getModoEspecial()) {
                turno.turnoActual().tirarDadosJugadorEspecial(tablero, turno);
            } else {
                turno.turnoActual().tirarDadosJugador(tablero, turno);
            }
        }
    }

    @Override
    public void acabarTurno(Turno turno, boolean modoCambiado) {
        if (turno.turnoActual().getAvatar() instanceof Coche && turno.turnoActual().getModoEspecial()) {
            turno.turnoActual().setDadosTirados(false);
            turno.turnoActual().setTurnosDadosTiradosEspecial(0);
            turno.turnoActual().setDadosDobles(0);
            turno.turnoActual().reducirNoPaga();
            turno.siguienteTurno();
            imprimirTratos();
        } else if (turno.turnoActual().getAvatar() instanceof Esfinge && turno.turnoActual().getModoEspecial()) {
            ((Esfinge) turno.turnoActual().getAvatar()).setLadoAntiguo(turno.turnoActual().getAvatar().getCasilla().getPosicion() / 10 % 4); // Restauramos la posicion anterior
            ((Esfinge) turno.turnoActual().getAvatar()).setPosicionAntigua((turno.turnoActual().getAvatar().getCasilla().getPosicion() / 10));
            ((Esfinge) turno.turnoActual().getAvatar()).vaciarUltimoTurno();
            turno.turnoActual().setTurnosDadosTiradosEspecial(0);
            turno.turnoActual().setDadosDobles(0);
            turno.turnoActual().setDadosTirados(false);
            turno.siguienteTurno();
            imprimirTratos();
        } else if (turno.turnoActual().getAvatar() instanceof Sombrero && turno.turnoActual().getModoEspecial()) {
            ((Sombrero) turno.turnoActual().getAvatar()).setLadoAntiguo(turno.turnoActual().getAvatar().getCasilla().getPosicion() / 10 % 4); // Restauramos la posicion anterior
            ((Sombrero) turno.turnoActual().getAvatar()).setPosicionAntigua((turno.turnoActual().getAvatar().getCasilla().getPosicion() / 10));
            ((Sombrero) turno.turnoActual().getAvatar()).vaciarUltimoTurno();
            turno.turnoActual().setTurnosDadosTiradosEspecial(0);
            turno.turnoActual().setDadosDobles(0);
            turno.turnoActual().setDadosTirados(false);
            turno.siguienteTurno();
            imprimirTratos();
        } else if (turno.turnoActual().getBloqueoTiroModoEspecial()) {
            turno.turnoActual().aumentarTurnosBloqueoTiroModoEspecial();
            if (turno.turnoActual().getTurnosBloqueoModoEspecial() == 2) {
                consola.imprimir("El jugador acabo su bloqueo de tiros.");
                turno.turnoActual().setBloqueoTiroModoEspecial(false);
                turno.turnoActual().setTurnosBloqueoModoEspecial(0);
            }
            turno.turnoActual().setTurnosDadosTiradosEspecial(0);
            turno.turnoActual().setDadosDobles(0);
            turno.turnoActual().reducirNoPaga();
            turno.siguienteTurno();
            imprimirTratos();
        } else if (turno.turnoActual().getDadosTirados()) {
            turno.turnoActual().setTurnosDadosTiradosEspecial(0);
            turno.turnoActual().setDadosDobles(0);
            turno.turnoActual().setDadosTirados(false);
            turno.turnoActual().reducirNoPaga();
            turno.siguienteTurno();
            imprimirTratos();
            modoCambiado = false;
        } else {
            consola.imprimir("Debes lanzar los dados antes de acabar tu turno");
        }
    }

    @Override
    public void verTablero(Tablero tablero) {
        consola.imprimir(tablero.toString());
    }

    @Override
    public void listarJugadores(HashMap<String, Jugador> jugadores) {
        for (Jugador jugador : tablero.getJugadores().values()) {
            consola.imprimir(jugador.toString());
        }
        consola.imprimir(tablero.toString());
    }

    @Override
    public void listarAvatares(HashMap<String, Avatar> avatares) {
        for (Avatar avatar : tablero.getAvatares().values()) {
            consola.imprimir(avatar.toString());
        }
        consola.imprimir(tablero.toString());
    }

    @Override
    public void listarEnVenta(Tablero tablero) {
        for (Casilla casilla : tablero.casillasEnVenta()) {
            consola.imprimir(casilla.toString());
        }
    }

    @Override
    public void listarEdificios(Tablero tablero) {
        tablero.imprimirEdificios();
    }

    @Override
    public void listarEdificiosGrupo(Grupo grupo) {
        grupo.imprimirEdificios();
    }

    @Override
    public void comprar(Turno turno, String casilla) {
        try {
            if (!turno.turnoActual().getModoEspecial()) {
                turno.turnoActual().comprarCasilla(tablero);
            } else if (turno.turnoActual().getAvatar() instanceof Esfinge && turno.turnoActual().getModoEspecial()) {
                turno.turnoActual().comprarCasilla(tablero);
                ((Esfinge) turno.turnoActual().getAvatar()).anhadirSolarComprado((Solar) turno.turnoActual().getAvatar().getCasilla());
            } else if (turno.turnoActual().getAvatar() instanceof Sombrero && turno.turnoActual().getModoEspecial()) {
                turno.turnoActual().comprarCasilla(tablero);
                ((Sombrero) turno.turnoActual().getAvatar()).anhadirSolarComprado((Solar) turno.turnoActual().getAvatar().getCasilla());
            } else if (!(turno.turnoActual().getAvatar() instanceof Coche) && turno.turnoActual().getModoEspecial()) {
                turno.turnoActual().comprarCasilla(tablero);
                consola.imprimir("El jugador " + turno.turnoActual().getNombre() + " ha comprado la casilla " + casilla);
            } else if (turno.turnoActual().getModoEspecial() && turno.turnoActual().getAvatar() instanceof Coche && !turno.turnoActual().getHaCompradoModoEspecial()) {
                turno.turnoActual().comprarCasilla(tablero);
                turno.turnoActual().setHaCompradoModoEspecial(true);
            } else if (turno.turnoActual().getModoEspecial() && turno.turnoActual().getAvatar() instanceof Coche && turno.turnoActual().getHaCompradoModoEspecial()) {
                consola.imprimir("El jugador ya ha comprado durante su turno en el modo especial.");
            }
        } catch (ExcepcionCompraCasilla ex) {
            consola.imprimir(ex.getMessage());
        }
    }

    @Override
    public void edificar(Jugador j, Solar c, String tipo) {
        if (!c.getPropietario().getNombre().equals(j.getNombre())) {
            consola.imprimir("El jugador " + j.getNombre() + " no es propietario de " + c.getNombre());
        } else if (!c.getEdificable()) {
            consola.imprimir("El jugador no ha caido 2 veces en " + c.getNombre());
        } else if (c.getHipotecada()) {
            consola.imprimir("La casilla " + c.getNombre() + " esta hipotecada. No se puede edificar en ella.");
        } else {
            switch (tipo) {
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

    @Override
    public void hipotecar(Turno turno, Tablero tablero, String casilla) {
        try {
            turno.turnoActual().hipotecar((Solar) tablero.casillaByName(casilla));
        } catch (ExcepcionHipoteca ex) {
            consola.imprimir(ex.getMessage());
        } catch (ExcepcionCasilla ex) {
            consola.imprimir(ex.getMessage());
        }
    }

    @Override
    public void deshipotecar(Turno turno, Tablero tablero, String casilla) {
        try {
            turno.turnoActual().deshipotecar(tablero.casillaByName(casilla));
        } catch (ExcepcionHipoteca ex) {
            consola.imprimir(ex.getMessage());
        } catch (ExcepcionCasilla ex) {
            consola.imprimir(ex.getMessage());
        }
    }

    @Override
    public void estadisticas(Tablero tablero, String nombre) {
        if (tablero.getJugadores().get(nombre) == null) {
            if (!tablero.getJugadores().containsKey(nombre)) {
                consola.imprimir("El jugador " + nombre + " no existe.");
            } else {
                consola.imprimir(tablero.getJugadores().get(nombre).estadisticasJugador());
            }
        } else {
            if (!tablero.getJugadores().containsKey(nombre)) {
                consola.imprimir("El jugador " + nombre + " no existe.");
            } else {
                consola.imprimir(tablero.getJugadores().get(nombre).estadisticasJugador());
            }
        }
    }

    @Override
    public void cambiarModo(Turno turno, boolean modoCambiado) {
        if (!turno.turnoActual().getModoEspecial()) {
            turno.turnoActual().cambiarModo();
            modoCambiado = true;
            if (turno.turnoActual().getAvatar() instanceof Esfinge) {
                ((Esfinge) turno.turnoActual().getAvatar()).cambiarAvanceEste();
            } else if (turno.turnoActual().getAvatar() instanceof Sombrero) {
                ((Sombrero) turno.turnoActual().getAvatar()).cambiarAvanceNorte();
            }
            consola.imprimir("A partir de ahora, el avatar " + turno.turnoActual().getAvatar().getId() + " de tipo " + turno.turnoActual().getAvatar().getTipo() + " se movera en modo avanzado.");
        } else {
            if (turno.turnoActual().getAvatar() instanceof Esfinge)
                ((Esfinge) turno.turnoActual().getAvatar()).vaciarUltimoTurno();
            else if (turno.turnoActual().getAvatar() instanceof Sombrero)
                ((Sombrero) turno.turnoActual().getAvatar()).vaciarUltimoTurno();
            turno.turnoActual().cambiarModo();
            modoCambiado = false;
            consola.imprimir("A partir de ahora, el avatar " + turno.turnoActual().getAvatar().getId() + " de tipo " + turno.turnoActual().getAvatar().getTipo() + " se movera en modo normal.");
        }
    }

    @Override
    public void vender(Solar s, String tipo, Integer cantidad) {
        switch (tipo) {
            case "casas":
                s.venderEdificios(Valor.EDIFICIO_CASA, cantidad);
                break;
            case "hoteles":
                s.venderEdificios(Valor.EDIFICIO_HOTEL, cantidad);
                break;
            case "piscinas":
                s.venderEdificios(Valor.EDIFICIO_PISCINA, cantidad);
                break;
            case "pistas":
                s.venderEdificios(Valor.EDIFICIO_PISTA, cantidad);
                break;
            default:
                consola.imprimir("Comando incorrecto.");
                break;
        }
    }

    @Override
    public Trato crearTrato(String[] comando) throws ExcepcionTrato {
        Trato trato = new Trato();

        for (int i = 0; i < comando.length; i++) {
            comando[i] = comando[i].replace(":", "");
            comando[i] = comando[i].replace("(", "");
            comando[i] = comando[i].replace(")", "");
            comando[i] = comando[i].replace(",", "");
            comando[i] = comando[i].replace("noalquiler", "");
        }

        try {

            if (tablero.getJugadores().get(comando[1]) == null) {
                throw new ExcepcionTrato(comando[1] + " no es un jugador");
            }
            if (turno.turnoActual().getNombre().equals(comando[1])) {
                throw new ExcepcionTrato("No puedes hacer un trato contigo mismo");
            }
            switch (comando.length) {
                case 5:
                    if (Valor.esNumero(comando[3])) {
                        if (tablero.casillaByName(comando[4]) == null) {
                            throw new ExcepcionTrato(comando[4] + " no existe");
                        }
                        if (!tablero.casillaByName(comando[4]).getPropietario().getNombre().equals(comando[1])) {
                            throw new ExcepcionTrato(comando[1] + " no es el dueño de " + comando[4]);
                        }
                        trato = new Trato(turno.turnoActual(), Integer.parseInt(comando[3]), (Propiedad) tablero.casillaByName(comando[4]));
                    } else if (Valor.esNumero(comando[4])) {
                        if (tablero.casillaByName(comando[3]) == null) {
                            throw new ExcepcionTrato(comando[3] + " no existe");
                        }
                        if (!turno.turnoActual().getNombre().equals(tablero.casillaByName(comando[3]).getPropietario().getNombre())) {
                            throw new ExcepcionTrato(turno.turnoActual().getNombre() + " no es el dueño de " + comando[3]);
                        }
                        trato = new Trato((Propiedad) tablero.casillaByName(comando[3]), Integer.parseInt(comando[4]), tablero.getJugadores().get(comando[1]));
                    } else {
                        if (tablero.casillaByName(comando[4]) == null) {
                            throw new ExcepcionTrato(comando[4] + " no existe");
                        }
                        if (!tablero.casillaByName(comando[4]).getPropietario().getNombre().equals(comando[1])) {
                            throw new ExcepcionTrato(comando[1] + " no es el dueño de " + comando[4]);
                        }
                        if (tablero.casillaByName(comando[3]) == null) {
                            throw new ExcepcionTrato(comando[3] + " no existe");
                        }
                        if (!turno.turnoActual().getNombre().equals(tablero.casillaByName(comando[3]).getPropietario().getNombre())) {
                            throw new ExcepcionTrato(turno.turnoActual().getNombre() + " no es el dueño de " + comando[3]);
                        }
                        trato = new Trato((Propiedad) tablero.casillaByName(comando[3]), (Propiedad) tablero.casillaByName(comando[4]));
                    }
                    break;
                case 7:
                    if (tablero.casillaByName(comando[3]) == null) {
                        throw new ExcepcionTrato(comando[3] + " no existe");
                    }
                    if (!turno.turnoActual().getNombre().equals(tablero.casillaByName(comando[3]).getPropietario().getNombre())) {
                        throw new ExcepcionTrato(turno.turnoActual().getNombre() + " no es el dueño de " + comando[3]);
                    }
                    if (Valor.esNumero(comando[5])) {
                        if (tablero.casillaByName(comando[6]) == null) {
                            throw new ExcepcionTrato(comando[6] + " no existe");
                        }
                        if (!tablero.casillaByName(comando[6]).getPropietario().getNombre().equals(comando[1])) {
                            throw new ExcepcionTrato(comando[1] + " no es el dueño de " + comando[6]);
                        }
                        trato = new Trato((Propiedad) tablero.casillaByName(comando[3]), Integer.parseInt(comando[5]), (Propiedad) tablero.casillaByName(comando[6]));
                    } else if (Valor.esNumero(comando[6])) {
                        if (tablero.casillaByName(comando[4]) == null) {
                            throw new ExcepcionTrato(comando[4] + " no existe");
                        }
                        if (!tablero.casillaByName(comando[4]).getPropietario().getNombre().equals(comando[1])) {
                            throw new ExcepcionTrato(comando[1] + " no es el dueño de " + comando[4]);
                        }
                        trato = new Trato((Propiedad) tablero.casillaByName(comando[3]), (Propiedad) tablero.casillaByName(comando[4]), Integer.parseInt(comando[6]));
                    } else {
                        throw new ExcepcionTrato("No se puede intercambiar mas de una propiedad por jugador");
                    }
                    break;
                case 8:
                    if (tablero.casillaByName(comando[3]) == null) {
                        throw new ExcepcionTrato(comando[3] + " no existe");
                    }
                    if (!turno.turnoActual().getNombre().equals(tablero.casillaByName(comando[3]).getPropietario().getNombre())) {
                        throw new ExcepcionTrato(turno.turnoActual().getNombre() + " no es el dueño de " + comando[3]);
                    }
                    if (tablero.casillaByName(comando[4]) == null) {
                        throw new ExcepcionTrato(comando[4] + " no existe");
                    }
                    if (!tablero.casillaByName(comando[4]).getPropietario().getNombre().equals(comando[1])) {
                        throw new ExcepcionTrato(comando[1] + " no es el dueño de " + comando[4]);
                    }
                    if (tablero.casillaByName(comando[6]) == null) {
                        throw new ExcepcionTrato(comando[6] + " no existe");
                    }
                    if (!tablero.casillaByName(comando[6]).getPropietario().getNombre().equals(comando[1])) {
                        throw new ExcepcionTrato(comando[1] + " no es el dueño de " + comando[6]);
                    }
                    if (Integer.parseInt(comando[7]) <= 0) {
                        throw new ExcepcionTrato("Numero de turnos no valido");
                    }
                    trato = new Trato((Propiedad) tablero.casillaByName(comando[3]), (Propiedad) tablero.casillaByName(comando[4]), (Propiedad) tablero.casillaByName(comando[6]), Integer.parseInt(comando[7]));
                    break;
            }

            return trato;
        } catch (ExcepcionCasilla ex) {
            consola.imprimir(ex.getMessage());
        }

        return null;
    }

    public boolean seRepiteTrato(Trato trato) {
        for (Trato tr : tratos.values()) {
            if (trato.getTipo().equals(tr.getTipo()) && trato.getPropiedad1() == tr.getPropiedad1() && trato.getPropiedad2() == tr.getPropiedad2() && trato.getPropiedad3() == tr.getPropiedad3() && trato.getDinero() == tr.getDinero() && trato.getJugador1() == tr.getJugador1() && trato.getJugador2() == tr.getJugador2() && trato.getTurnos() == tr.getTurnos()) {
                return true;
            }
        }
        return false;
    }

    public void limpiarTratos() {
        ArrayList<String> eliminables = new ArrayList<>();

        for (String clave : tratos.keySet()) {
            Trato trato = tratos.get(clave);

            if ((trato.getPropiedad1() != null && trato.getPropiedad1().getPropietario() != trato.getJugador1()) || (trato.getPropiedad2() != null && trato.getPropiedad2().getPropietario() != trato.getJugador2()) || (trato.getPropiedad3() != null && trato.getPropiedad3().getPropietario() != trato.getJugador2())) {
                eliminables.add(clave);
            }
        }

        for (String clave : eliminables) {
            tratos.remove(clave);
        }
    }

    @Override
    public void eliminarTrato(String nombre) {
        tratos.remove(nombre);
        consola.imprimir(nombre + " eliminado");
    }

    @Override
    public void imprimirTratos() {
        consola.imprimir("Tratos: ");
        for (String clave : tratos.keySet()) {
            if (tratos.get(clave).getJugador2() == turno.turnoActual()) {
                consola.imprimir("\t" + clave);
                consola.imprimir(tratos.get(clave).toString());
            }
        }
        consola.imprimir("\n");
    }
}
