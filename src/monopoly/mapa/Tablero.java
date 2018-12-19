package monopoly.mapa;

import monopoly.persona.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Tablero {

    private ArrayList<ArrayList<Casilla>> casillas;
    private HashMap<String, Avatar> avatares;
    private HashMap<String, Jugador> jugadores;
    private HashMap<String, Grupo> grupos;
    private ArrayList<Edificio> edificios;
    private ArrayList<Carta> cartasSuerte;
    private ArrayList<Carta> cartasCaja;

    // constructores
    public Tablero() {
        Jugador banca = new Jugador("banca");
        this.casillas = new ArrayList<>();
        this.avatares = new HashMap<>();
        this.edificios = new ArrayList<>();
        ArrayList<Casilla> abajo = new ArrayList<>();
        ArrayList<Casilla> izquierda = new ArrayList<>();
        ArrayList<Casilla> derecha = new ArrayList<>();
        ArrayList<Casilla> arriba = new ArrayList<>();
        grupos = new HashMap<>();
        Grupo grupo_negro = new Grupo(Valor.GRUPO_NEGRO, Valor.COSTE_GRUPO_NEGRO, Valor.ALQUILER_GRUPO_NEGRO);
        Grupo grupo_cyan = new Grupo(Valor.GRUPO_CYAN, Valor.COSTE_GRUPO_CYAN, Valor.ALQUILER_GRUPO_CYAN);
        Grupo grupo_rosa = new Grupo(Valor.GRUPO_ROSA, Valor.COSTE_GRUPO_ROSA, Valor.ALQUILER_GRUPO_ROSA);
        Grupo grupo_naranja = new Grupo(Valor.GRUPO_NARANJA, Valor.COSTE_GRUPO_NARANJA, Valor.ALQUILER_GRUPO_NARANJA);
        Grupo grupo_rojo = new Grupo(Valor.GRUPO_ROJO, Valor.COSTE_GRUPO_ROJO, Valor.ALQUILER_GRUPO_ROJO);
        Grupo grupo_amarillo = new Grupo(Valor.GRUPO_AMARILLO, Valor.COSTE_GRUPO_AMARILLO, Valor.ALQUILER_GRUPO_AMARILLO);
        Grupo grupo_verde = new Grupo(Valor.GRUPO_VERDE, Valor.COSTE_GRUPO_VERDE, Valor.ALQUILER_GRUPO_VERDE);
        Grupo grupo_azul = new Grupo(Valor.GRUPO_AZUL, Valor.COSTE_GRUPO_AZUL, Valor.ALQUILER_GRUPO_AZUL);
        grupos.put(Valor.GRUPO_NEGRO, grupo_negro);
        grupos.put(Valor.GRUPO_CYAN, grupo_cyan);
        grupos.put(Valor.GRUPO_ROSA, grupo_rosa);
        grupos.put(Valor.GRUPO_NARANJA, grupo_naranja);
        grupos.put(Valor.GRUPO_ROJO, grupo_rojo);
        grupos.put(Valor.GRUPO_AMARILLO, grupo_amarillo);
        grupos.put(Valor.GRUPO_VERDE, grupo_verde);
        grupos.put(Valor.GRUPO_AZUL, grupo_azul);
        Casilla solar1GrupoNegro = new Casilla(Valor.SOLAR1_GRUPO_NEGRO, Valor.CASILLA_TIPO_SOLAR, grupo_negro, Valor.POSICION_SOLAR1_GRUPO_NEGRO, banca, this);
        Casilla solar2GrupoNegro = new Casilla(Valor.SOLAR2_GRUPO_NEGRO, Valor.CASILLA_TIPO_SOLAR, grupo_negro, Valor.POSICION_SOLAR2_GRUPO_NEGRO, banca, this);
        Casilla solar1GrupoCyan = new Casilla(Valor.SOLAR1_GRUPO_CYAN, Valor.CASILLA_TIPO_SOLAR, grupo_cyan, Valor.POSICION_SOLAR1_GRUPO_CYAN, banca, this);
        Casilla solar2GrupoCyan = new Casilla(Valor.SOLAR2_GRUPO_CYAN, Valor.CASILLA_TIPO_SOLAR, grupo_cyan, Valor.POSICION_SOLAR2_GRUPO_CYAN, banca, this);
        Casilla solar3GrupoCyan = new Casilla(Valor.SOLAR3_GRUPO_CYAN, Valor.CASILLA_TIPO_SOLAR, grupo_cyan, Valor.POSICION_SOLAR3_GRUPO_CYAN, banca, this);
        Casilla solar1GrupoRosa = new Casilla(Valor.SOLAR1_GRUPO_ROSA, Valor.CASILLA_TIPO_SOLAR, grupo_rosa, Valor.POSICION_SOLAR1_GRUPO_ROSA, banca, this);
        Casilla solar2GrupoRosa = new Casilla(Valor.SOLAR2_GRUPO_ROSA, Valor.CASILLA_TIPO_SOLAR, grupo_rosa, Valor.POSICION_SOLAR2_GRUPO_ROSA, banca, this);
        Casilla solar3GrupoRosa = new Casilla(Valor.SOLAR3_GRUPO_ROSA, Valor.CASILLA_TIPO_SOLAR, grupo_rosa, Valor.POSICION_SOLAR3_GRUPO_ROSA, banca, this);
        Casilla solar1GrupoNaranja = new Casilla(Valor.SOLAR1_GRUPO_NARANJA, Valor.CASILLA_TIPO_SOLAR, grupo_naranja, Valor.POSICION_SOLAR1_GRUPO_NARANJA, banca, this);
        Casilla solar2GrupoNaranja = new Casilla(Valor.SOLAR2_GRUPO_NARANJA, Valor.CASILLA_TIPO_SOLAR, grupo_naranja, Valor.POSICION_SOLAR2_GRUPO_NARANJA, banca, this);
        Casilla solar3GrupoNaranja = new Casilla(Valor.SOLAR3_GRUPO_NARANJA, Valor.CASILLA_TIPO_SOLAR, grupo_naranja, Valor.POSICION_SOLAR3_GRUPO_NARANJA, banca, this);
        Casilla solar1GrupoRojo = new Casilla(Valor.SOLAR1_GRUPO_ROJO, Valor.CASILLA_TIPO_SOLAR, grupo_rojo, Valor.POSICION_SOLAR1_GRUPO_ROJO, banca, this);
        Casilla solar2GrupoRojo = new Casilla(Valor.SOLAR2_GRUPO_ROJO, Valor.CASILLA_TIPO_SOLAR, grupo_rojo, Valor.POSICION_SOLAR2_GRUPO_ROJO, banca, this);
        Casilla solar3GrupoRojo = new Casilla(Valor.SOLAR3_GRUPO_ROJO, Valor.CASILLA_TIPO_SOLAR, grupo_rojo, Valor.POSICION_SOLAR3_GRUPO_ROJO, banca, this);
        Casilla solar1GrupoAmarillo = new Casilla(Valor.SOLAR1_GRUPO_AMARILLO, Valor.CASILLA_TIPO_SOLAR, grupo_amarillo, Valor.POSICION_SOLAR1_GRUPO_AMARILLO, banca, this);
        Casilla solar2GrupoAmarillo = new Casilla(Valor.SOLAR2_GRUPO_AMARILLO, Valor.CASILLA_TIPO_SOLAR, grupo_amarillo, Valor.POSICION_SOLAR2_GRUPO_AMARILLO, banca, this);
        Casilla solar3GrupoAmarillo = new Casilla(Valor.SOLAR3_GRUPO_AMARILLO, Valor.CASILLA_TIPO_SOLAR, grupo_amarillo, Valor.POSICION_SOLAR3_GRUPO_AMARILLO, banca, this);
        Casilla solar1GrupoVerde = new Casilla(Valor.SOLAR1_GRUPO_VERDE, Valor.CASILLA_TIPO_SOLAR, grupo_verde, Valor.POSICION_SOLAR1_GRUPO_VERDE, banca, this);
        Casilla solar2GrupoVerde = new Casilla(Valor.SOLAR2_GRUPO_VERDE, Valor.CASILLA_TIPO_SOLAR, grupo_verde, Valor.POSICION_SOLAR2_GRUPO_VERDE, banca, this);
        Casilla solar3GrupoVerde = new Casilla(Valor.SOLAR3_GRUPO_VERDE, Valor.CASILLA_TIPO_SOLAR, grupo_verde, Valor.POSICION_SOLAR3_GRUPO_VERDE, banca, this);
        Casilla solar1GrupoAzul = new Casilla(Valor.SOLAR1_GRUPO_AZUL, Valor.CASILLA_TIPO_SOLAR, grupo_azul, Valor.POSICION_SOLAR1_GRUPO_AZUL, banca, this);
        Casilla solar2GrupoAzul = new Casilla(Valor.SOLAR2_GRUPO_AZUL, Valor.CASILLA_TIPO_SOLAR, grupo_azul, Valor.POSICION_SOLAR2_GRUPO_AZUL, banca, this);
        grupo_negro.getCasillas().add(solar1GrupoNegro);
        grupo_negro.getCasillas().add(solar2GrupoNegro);
        grupo_cyan.getCasillas().add(solar1GrupoCyan);
        grupo_cyan.getCasillas().add(solar2GrupoCyan);
        grupo_cyan.getCasillas().add(solar3GrupoCyan);
        grupo_rosa.getCasillas().add(solar1GrupoRosa);
        grupo_rosa.getCasillas().add(solar2GrupoRosa);
        grupo_rosa.getCasillas().add(solar3GrupoRosa);
        grupo_naranja.getCasillas().add(solar1GrupoNaranja);
        grupo_naranja.getCasillas().add(solar2GrupoNaranja);
        grupo_naranja.getCasillas().add(solar3GrupoNaranja);
        grupo_rojo.getCasillas().add(solar1GrupoRojo);
        grupo_rojo.getCasillas().add(solar2GrupoRojo);
        grupo_rojo.getCasillas().add(solar3GrupoRojo);
        grupo_amarillo.getCasillas().add(solar1GrupoAmarillo);
        grupo_amarillo.getCasillas().add(solar2GrupoAmarillo);
        grupo_amarillo.getCasillas().add(solar3GrupoAmarillo);
        grupo_verde.getCasillas().add(solar1GrupoVerde);
        grupo_verde.getCasillas().add(solar2GrupoVerde);
        grupo_verde.getCasillas().add(solar3GrupoVerde);
        grupo_azul.getCasillas().add(solar1GrupoAzul);
        grupo_azul.getCasillas().add(solar2GrupoAzul);
        Casilla salida = new Casilla(Valor.CASILLA_SALIDA, Valor.CASILLA_TIPO_SALIDA, Valor.POSICION_CASILLA_SALIDA, banca, this);
        Casilla suerte1 = new Casilla(Valor.CASILLA_SUERTE, Valor.CASILLA_TIPO_SUERTE, Valor.POSICION_CASILLA_SUERTE1, banca, this);
        Casilla caja1 = new Casilla(Valor.CASILLA_CAJA, Valor.CASILLA_TIPO_CAJA, Valor.POSICION_CASILLA_CAJA1, banca, this);
        Casilla impuesto1 = new Casilla(Valor.CASILLA_IMPUESTO, Valor.CASILLA_TIPO_IMPUESTO, Valor.POSICION_CASILLA_IMPUESTO1, banca, this);
        Casilla transporte1 = new Casilla(Valor.CASILLA_TRANSPORTE, Valor.CASILLA_TIPO_TRANSPORTE, Valor.POSICION_CASILLA_TRANSPORTE1, banca, this);
        Casilla carcel = new Casilla(Valor.CASILLA_CARCEL, Valor.CASILLA_TIPO_CARCEL, Valor.POSICION_CASILLA_CARCEL, banca, this);
        Casilla servicio1 = new Casilla(Valor.CASILLA_SERVICIO, Valor.CASILLA_TIPO_SERVICIO, Valor.POSICION_CASILLA_SERVICIO1, banca, this);
        Casilla transporte2 = new Casilla(Valor.CASILLA_TRANSPORTE2, Valor.CASILLA_TIPO_TRANSPORTE, Valor.POSICION_CASILLA_TRANSPORTE2, banca, this);
        Casilla caja2 = new Casilla(Valor.CASILLA_CAJA, Valor.CASILLA_TIPO_CAJA, Valor.POSICION_CASILLA_CAJA2, banca, this);
        Casilla parking = new Casilla(Valor.CASILLA_PARKING, Valor.CASILLA_TIPO_PARKING, Valor.POSICION_CASILLA_PARKING, banca, this);
        Casilla suerte2 = new Casilla(Valor.CASILLA_SUERTE, Valor.CASILLA_TIPO_SUERTE, Valor.POSICION_CASILLA_SUERTE2, banca, this);
        Casilla transporte3 = new Casilla(Valor.CASILLA_TRANSPORTE3, Valor.CASILLA_TIPO_TRANSPORTE, Valor.POSICION_CASILLA_TRANSPORTE3, banca, this);
        Casilla servicio2 = new Casilla(Valor.CASILLA_SERVICIO, Valor.CASILLA_TIPO_SERVICIO, Valor.POSICION_CASILLA_SERVICIO2, banca, this);
        Casilla ircarcel = new Casilla(Valor.CASILLA_IR_CARCEL, Valor.CASILLA_TIPO_IR_CARCEL, Valor.POSICION_CASILLA_IR_CARCEL, banca, this);
        Casilla caja3 = new Casilla(Valor.CASILLA_CAJA, Valor.CASILLA_TIPO_CAJA, Valor.POSICION_CASILLA_CAJA3, banca, this);
        Casilla transporte4 = new Casilla(Valor.CASILLA_TRANSPORTE4, Valor.CASILLA_TIPO_TRANSPORTE, Valor.POSICION_CASILLA_TRANSPORTE4, banca, this);
        Casilla suerte3 = new Casilla(Valor.CASILLA_SUERTE, Valor.CASILLA_TIPO_SUERTE, Valor.POSICION_CASILLA_SUERTE3, banca, this);
        Casilla impuesto2 = new Casilla(Valor.CASILLA_IMPUESTO2, Valor.CASILLA_TIPO_IMPUESTO, Valor.POSICION_CASILLA_IMPUESTO2, banca, this);
        abajo.add(salida);
        abajo.add(solar1GrupoNegro);
        abajo.add(caja1);
        abajo.add(solar2GrupoNegro);
        abajo.add(impuesto1);
        abajo.add(transporte1);
        abajo.add(solar1GrupoCyan);
        abajo.add(suerte1);
        abajo.add(solar2GrupoCyan);
        abajo.add(solar3GrupoCyan);
        this.casillas.add(abajo);
        izquierda.add(carcel);
        izquierda.add(solar1GrupoRosa);
        izquierda.add(servicio1);
        izquierda.add(solar2GrupoRosa);
        izquierda.add(solar3GrupoRosa);
        izquierda.add(transporte2);
        izquierda.add(solar1GrupoNaranja);
        izquierda.add(caja2);
        izquierda.add(solar2GrupoNaranja);
        izquierda.add(solar3GrupoNaranja);
        this.casillas.add(izquierda);
        arriba.add(parking);
        arriba.add(solar1GrupoRojo);
        arriba.add(suerte2);
        arriba.add(solar2GrupoRojo);
        arriba.add(solar3GrupoRojo);
        arriba.add(transporte3);
        arriba.add(solar1GrupoAmarillo);
        arriba.add(solar2GrupoAmarillo);
        arriba.add(servicio2);
        arriba.add(solar3GrupoAmarillo);
        this.casillas.add(arriba);
        derecha.add(ircarcel);
        derecha.add(solar1GrupoVerde);
        derecha.add(solar2GrupoVerde);
        derecha.add(caja3);
        derecha.add(solar3GrupoVerde);
        derecha.add(transporte4);
        derecha.add(suerte3);
        derecha.add(solar1GrupoAzul);
        derecha.add(impuesto2);
        derecha.add(solar2GrupoAzul);
        this.casillas.add(derecha);

        /*Cartas de suerte*/
        Carta cartaS1 = new Carta("suerte", 1);
        Carta cartaS2 = new Carta("suerte", 2);
        Carta cartaS3 = new Carta("suerte", 3);
        Carta cartaS4 = new Carta("suerte", 4);
        Carta cartaS5 = new Carta("suerte", 5);
        Carta cartaS6 = new Carta("suerte", 6);
        Carta cartaS7 = new Carta("suerte", 7);
        this.cartasSuerte = new ArrayList<>();
        this.cartasSuerte.add(cartaS1);
        this.cartasSuerte.add(cartaS2);
        this.cartasSuerte.add(cartaS3);
        this.cartasSuerte.add(cartaS4);
        this.cartasSuerte.add(cartaS5);
        this.cartasSuerte.add(cartaS6);
        this.cartasSuerte.add(cartaS7);

        /*Cartas de caja*/
        Carta cartaC1 = new Carta("caja", 1);
        Carta cartaC2 = new Carta("caja", 2);
        Carta cartaC3 = new Carta("caja", 3);
        Carta cartaC4 = new Carta("caja", 4);
        Carta cartaC5 = new Carta("caja", 5);
        Carta cartaC6 = new Carta("caja", 6);
        this.cartasCaja = new ArrayList<>();
        this.cartasCaja.add(cartaC1);
        this.cartasCaja.add(cartaC2);
        this.cartasCaja.add(cartaC3);
        this.cartasCaja.add(cartaC4);
        this.cartasCaja.add(cartaC5);
        this.cartasCaja.add(cartaC6);

    }

    //getters y setters
    public ArrayList<ArrayList<Casilla>> getCasillas() {
        return casillas;
    }

    public void setCasillas(ArrayList<ArrayList<Casilla>> casillas) {
        if (casillas == null) {
            System.out.println("Casillas nulas.");
            System.exit(1);
        }
        this.casillas = casillas;
    }

    public HashMap<String, Avatar> getAvatares() {
        return avatares;
    }

    public void setAvatares(HashMap<String, Avatar> avatares) {
        if (avatares == null) {
            System.out.println("Avatares nulos.");
            System.exit(1);
        }
        this.avatares = avatares;
    }

    public HashMap<String, Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(HashMap<String, Jugador> jugadores) {
        if (jugadores == null) {
            System.out.println("Jugadores nulos.");
            System.exit(1);
        }
        this.jugadores = jugadores;
    }

    public ArrayList<Edificio> getEdificios() {
        return edificios;
    }

    public void setEdificios(ArrayList<Edificio> edificios) {
        this.edificios = edificios;
    }

    //metodos
    public void barajarCartas(char c) {
        if (c != 's' && c != 'c') {
            System.out.println("c no valido.");
            System.exit(1);
        }
        ArrayList<Carta> cartas;
        Carta[] cartasAux;
        if (c == 's') {
            cartas = this.cartasSuerte;
        } else {
            cartas = this.cartasCaja;
        }
        cartasAux = new Carta[cartas.size()];
        boolean seRepite = false;
        int posicion;
        Random random = new Random();

        for (Carta carta : cartas) {
            do {
                seRepite = false;
                posicion = random.nextInt((5 - 0) + 1) + 0;

                if (cartasAux[posicion] != null) {
                    seRepite = true;
                }
            } while (seRepite);
            cartasAux[posicion] = carta;
        }
        for (int i = 0; i < cartas.size(); i++) {
            cartas.set(i, cartasAux[i]);
        }
    }

    public void escogerCarta(char c, Jugador jugador, Turno turno) {
        if (c != 's' && c != 'c') {
            System.out.println("c no valido.");
            System.exit(1);
        }
        
        this.barajarCartas(c); /*Se barajan las cartas*/
        
        ArrayList<Carta> cartas; /*Se coge la baraja necesaria*/
        if (c == 's') {
            cartas = this.cartasSuerte;
        } else {
            cartas = this.cartasCaja;
        }

        int numero;
        do {
            System.out.println("Escoge un numero del 1 al " + cartas.size() + ": ");
            Scanner scanner = new Scanner(System.in);
            numero = scanner.nextInt();
        } while (numero < 1 || numero > cartas.size());

        cartas.get(numero-1).realizarAccion(jugador, this, turno);

    }

    /**
     * Busca una casilla del tablero por su nombre
     *
     * @param nombre Nombre de la casila a buscar
     * @return Casilla si existe. Si no existe return null
     */
    public Casilla casillaByName(String nombre) {
        if (nombre == null) {
            System.out.println(Valor.ANSI_ROJO + "Nombre nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        for (ArrayList<Casilla> n : casillas) {
            for (Casilla cas : n) {
                if (cas.getNombre().equals(nombre)) {
                    return cas;
                }
            }
        }
        return null;
    }

    public void iniciarCaidaCasilla(ArrayList<Jugador> jugadores) {
        for (ArrayList<Casilla> lado : casillas) {
            for (Casilla casilla : lado) {
                for (Jugador jugador : jugadores) {
                    casilla.getVecesCaidas().put(jugador, 0);
                }
            }
        }
    }

    public ArrayList<Casilla> casillasEnVenta() {
        ArrayList<Casilla> casVent = new ArrayList<>();
        for (ArrayList<Casilla> n : casillas) {
            for (Casilla cas : n) {
                if (cas.seVende()) {
                    casVent.add(cas);
                }
            }
        }
        return casVent;
    }

    public String imprimirBordeSuperior() {
        String cadena = Valor.ESQUINA_SUPERIOR_IZQUIERDA;
        for (int i = 0; i < Valor.TAMANHO_LINEA; i++) {
            if (i != 0 && i % Valor.TAMANHO_CASILLA == 0) {
                cadena = cadena.concat(Valor.INTERSECCION_SUPERIOR);
            }
            cadena = cadena.concat(Valor.LINEA_HORIZONTAL);
        }
        cadena = cadena.concat(Valor.ESQUINA_SUPERIOR_DERECHA);

        return cadena;
    }

    public String imprimirBordeInferior() {
        String cadena = Valor.ESQUINA_INFERIOR_IZQUIERDA;
        for (int i = 0; i < Valor.TAMANHO_LINEA; i++) {
            if (i != 0 && i % Valor.TAMANHO_CASILLA == 0) {
                cadena = cadena.concat(Valor.INTERSECCION_INFERIOR);
            }
            cadena = cadena.concat(Valor.LINEA_HORIZONTAL);
        }
        cadena = cadena.concat(Valor.ESQUINA_INFERIOR_DERECHA);

        return cadena;
    }

    public String imprimirEspacio() {
        String cadena = "";
        /*for (int i = 0; i < Valor.TAMANHO_LINEA - (Valor.TAMANHO_CASILLA  * 2) + 8 ; i++)
            cadena = cadena.concat(" ");*/
        for (int i = 0; i < (Valor.TAMANHO_CASILLA * 9) + 8; i++) {
            cadena = cadena.concat(" ");
        }

        return cadena;
    }

    public String imprimirEspacio(boolean medio) {
        String cadena = "";
        for (int i = 0; i < Valor.TAMANHO_CASILLA; i++) {
            cadena = cadena.concat(Valor.LINEA_HORIZONTAL);
        }

        return cadena;
    }

    public String imprimirHorizontal() {
        String cadena = "";
        for (int i = 0; i < Valor.TAMANHO_CASILLA; i++) {
            cadena = cadena.concat(Valor.LINEA_HORIZONTAL);
        }

        return cadena;
    }

    public void imprimirEdificios() {
        if (edificios.size() == 0) {
            System.out.println("No se ha construido ningun edificio.");
        }
        for (Edificio edificio : edificios) {
            System.out.println(edificio);
        }
    }

    public void estadisticas() {
        int vecesDadosTirados = 0;
        int vecesCaidas = 0;
        int rentabilidadCasilla = 0;
        int rentabilidadGrupo = 0;
        int fortuna = 0;
        int pasosSalida = 0;

        Jugador mas_dados_tirados = null;
        Jugador mas_rico = null;
        Jugador pasos_por_salida = null;
        Iterator j_it = jugadores.values().iterator();
        while (j_it.hasNext()) {
            Jugador j = (Jugador) j_it.next();
            if (j.getPasarPorCasillaDeSalida() > pasosSalida) {
                pasosSalida = j.getPasarPorCasillaDeSalida();
                pasos_por_salida = j;
            }
            if (j.getFortuna() > fortuna) {
                fortuna = j.getFortuna();
                mas_rico = j;
            }
            if (j.getVecesDadosTirados() > vecesDadosTirados) {
                mas_dados_tirados = j;
                vecesDadosTirados = j.getVecesDadosTirados();
            }
        }

        Casilla casilla = null;
        Casilla masFrecuentada = null;
        for (ArrayList<Casilla> lados : casillas) {
            for (Casilla c : lados) {
                int veces = 0;
                Iterator caidas = c.getVecesCaidas().values().iterator();
                while (caidas.hasNext()) {
                    veces += (Integer) caidas.next();
                }
                if (veces > vecesCaidas) {
                    vecesCaidas = veces;
                    masFrecuentada = c;
                }
                if (c.getValor() + c.getAlquiler() > rentabilidadCasilla) {
                    casilla = c;
                    rentabilidadCasilla = c.getValor() + c.getAlquiler();
                }
            }
        }

        Grupo grupo = null;
        Collection<Grupo> coleccion_grupos = grupos.values();
        for (Grupo g : coleccion_grupos) {
            int rent = 0;
            for (Casilla c : g.getCasillas()) {
                rent += c.getValor() + c.getAlquiler();
            }
            if (rent > rentabilidadGrupo) {
                rentabilidadGrupo = rent;
                grupo = g;
            }
        }

        System.out.println("Casilla mas rentable: " + ((casilla != null ) ? casilla.getNombre() : "ninguna."));
        System.out.println("Grupo mas rentable: " + ((grupo != null) ? grupo.getColor() : "ninguno."));
        System.out.println("Casilla mas frecuentada: " + ((masFrecuentada != null) ? masFrecuentada.getNombre() : "ninuna."));
        System.out.println("Jugador mas vueltas: " + ((pasos_por_salida != null) ? pasos_por_salida.getNombre() : "ninguno."));
        System.out.println("Jugador mas veces dados tirados: " + ((mas_dados_tirados != null) ? mas_dados_tirados.getNombre() : "ninguno."));
        System.out.println("Jugador en cabeza: " + ((mas_rico != null) ? mas_rico.getNombre() : "ninguno."));

    }

    @Override
    public String toString() {
        return this.imprimirBordeSuperior() + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(2).get(0) + Valor.LINEA_VERTICAL + this.casillas.get(2).get(1) + Valor.LINEA_VERTICAL + this.casillas.get(2).get(2) + Valor.LINEA_VERTICAL + this.casillas.get(2).get(3) + Valor.LINEA_VERTICAL + this.casillas.get(2).get(4) + Valor.LINEA_VERTICAL + this.casillas.get(2).get(5) + Valor.LINEA_VERTICAL + this.casillas.get(2).get(6) + Valor.LINEA_VERTICAL + this.casillas.get(2).get(7) + Valor.LINEA_VERTICAL + this.casillas.get(2).get(8) + Valor.LINEA_VERTICAL + this.casillas.get(2).get(9) + Valor.LINEA_VERTICAL + this.casillas.get(3).get(0) + Valor.LINEA_VERTICAL + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(2).get(0).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(2).get(1).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(2).get(2).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(2).get(3).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(2).get(4).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(2).get(5).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(2).get(6).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(2).get(7).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(2).get(8).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(2).get(9).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(0).stringAvatares() + Valor.LINEA_VERTICAL + "\n"
                + Valor.INTERSECCION_IZQUIERDA + this.imprimirEspacio(true) + Valor.INTERSECCION_TOTAL + this.imprimirHorizontal() + Valor.INTERSECCION_INFERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_INFERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_INFERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_INFERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_INFERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_INFERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_INFERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_INFERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_TOTAL + this.imprimirHorizontal() + Valor.INTERSECCION_DERECHA + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(9) + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(1) + Valor.LINEA_VERTICAL + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(9).stringAvatares() + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(1).stringAvatares() + Valor.LINEA_VERTICAL + "\n"
                + Valor.INTERSECCION_IZQUIERDA + this.imprimirEspacio(true) + Valor.INTERSECCION_DERECHA + this.imprimirEspacio() + Valor.INTERSECCION_IZQUIERDA + this.imprimirHorizontal() + Valor.INTERSECCION_DERECHA + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(8) + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(2) + Valor.LINEA_VERTICAL + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(8).stringAvatares() + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(2).stringAvatares() + Valor.LINEA_VERTICAL + "\n"
                + Valor.INTERSECCION_IZQUIERDA + this.imprimirEspacio(true) + Valor.INTERSECCION_DERECHA + this.imprimirEspacio() + Valor.INTERSECCION_IZQUIERDA + this.imprimirHorizontal() + Valor.INTERSECCION_DERECHA + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(7) + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(3) + Valor.LINEA_VERTICAL + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(7).stringAvatares() + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(3).stringAvatares() + Valor.LINEA_VERTICAL + "\n"
                + Valor.INTERSECCION_IZQUIERDA + this.imprimirEspacio(true) + Valor.INTERSECCION_DERECHA + this.imprimirEspacio() + Valor.INTERSECCION_IZQUIERDA + this.imprimirHorizontal() + Valor.INTERSECCION_DERECHA + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(6) + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(4) + Valor.LINEA_VERTICAL + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(6).stringAvatares() + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(4).stringAvatares() + Valor.LINEA_VERTICAL + "\n"
                + Valor.INTERSECCION_IZQUIERDA + this.imprimirEspacio(true) + Valor.INTERSECCION_DERECHA + this.imprimirEspacio() + Valor.INTERSECCION_IZQUIERDA + this.imprimirHorizontal() + Valor.INTERSECCION_DERECHA + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(5) + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(5) + Valor.LINEA_VERTICAL + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(5).stringAvatares() + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(5).stringAvatares() + Valor.LINEA_VERTICAL + "\n"
                + Valor.INTERSECCION_IZQUIERDA + this.imprimirEspacio(true) + Valor.INTERSECCION_DERECHA + this.imprimirEspacio() + Valor.INTERSECCION_IZQUIERDA + this.imprimirHorizontal() + Valor.INTERSECCION_DERECHA + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(4) + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(6) + Valor.LINEA_VERTICAL + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(4).stringAvatares() + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(6).stringAvatares() + Valor.LINEA_VERTICAL + "\n"
                + Valor.INTERSECCION_IZQUIERDA + this.imprimirEspacio(true) + Valor.INTERSECCION_DERECHA + this.imprimirEspacio() + Valor.INTERSECCION_IZQUIERDA + this.imprimirHorizontal() + Valor.INTERSECCION_DERECHA + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(3) + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(7) + Valor.LINEA_VERTICAL + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(3).stringAvatares() + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(7).stringAvatares() + Valor.LINEA_VERTICAL + "\n"
                + Valor.INTERSECCION_IZQUIERDA + this.imprimirEspacio(true) + Valor.INTERSECCION_DERECHA + this.imprimirEspacio() + Valor.INTERSECCION_IZQUIERDA + this.imprimirHorizontal() + Valor.INTERSECCION_DERECHA + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(2) + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(8) + Valor.LINEA_VERTICAL + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(2).stringAvatares() + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(8).stringAvatares() + Valor.LINEA_VERTICAL + "\n"
                + Valor.INTERSECCION_IZQUIERDA + this.imprimirEspacio(true) + Valor.INTERSECCION_DERECHA + this.imprimirEspacio() + Valor.INTERSECCION_IZQUIERDA + this.imprimirHorizontal() + Valor.INTERSECCION_DERECHA + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(1) + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(9) + Valor.LINEA_VERTICAL + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(1).stringAvatares() + Valor.LINEA_VERTICAL + this.imprimirEspacio() + Valor.LINEA_VERTICAL + this.casillas.get(3).get(9).stringAvatares() + Valor.LINEA_VERTICAL + "\n"
                + Valor.INTERSECCION_IZQUIERDA + this.imprimirEspacio(true) + Valor.INTERSECCION_TOTAL + this.imprimirHorizontal() + Valor.INTERSECCION_SUPERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_SUPERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_SUPERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_SUPERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_SUPERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_SUPERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_SUPERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_SUPERIOR + this.imprimirHorizontal() + Valor.INTERSECCION_TOTAL + this.imprimirHorizontal() + Valor.INTERSECCION_DERECHA + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(0) + Valor.LINEA_VERTICAL + this.casillas.get(0).get(9) + Valor.LINEA_VERTICAL + this.casillas.get(0).get(8) + Valor.LINEA_VERTICAL + this.casillas.get(0).get(7) + Valor.LINEA_VERTICAL + this.casillas.get(0).get(6) + Valor.LINEA_VERTICAL + this.casillas.get(0).get(5) + Valor.LINEA_VERTICAL + this.casillas.get(0).get(4) + Valor.LINEA_VERTICAL + this.casillas.get(0).get(3) + Valor.LINEA_VERTICAL + this.casillas.get(0).get(2) + Valor.LINEA_VERTICAL + this.casillas.get(0).get(1) + Valor.LINEA_VERTICAL + this.casillas.get(0).get(0) + Valor.LINEA_VERTICAL + "\n"
                + Valor.LINEA_VERTICAL + this.casillas.get(1).get(0).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(0).get(9).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(0).get(8).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(0).get(7).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(0).get(6).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(0).get(5).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(0).get(4).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(0).get(3).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(0).get(2).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(0).get(1).stringAvatares() + Valor.LINEA_VERTICAL + this.casillas.get(0).get(0).stringAvatares() + Valor.LINEA_VERTICAL + "\n"
                + this.imprimirBordeInferior() + "\n";
    }

    public HashMap<String, Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(HashMap<String, Grupo> grupos) {
        this.grupos = grupos;
    }

    public boolean pasaPorSalida(Casilla casIni, Casilla casFin) {
        return casFin.getPosicion() < casIni.getPosicion();
    }
}
