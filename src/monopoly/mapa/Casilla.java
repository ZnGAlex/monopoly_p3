package monopoly.mapa;

import java.util.*;
import monopoly.excepciones.ExcepcionCarta;

import monopoly.excepciones.ExcepcionCasilla;
import monopoly.persona.*;

import static monopoly.mapa.Juego.consola;

public abstract class Casilla {

    private String nombre;
    private int posicion;
    private Grupo grupo;
    private Jugador propietario;
    private int valor;
    private int alquiler;
    private Tablero tablero;
    private HashMap<String, Avatar> avatares;
    private ArrayList<Edificio> edificios;
    private HashMap<Jugador, Integer> vecesCaidas;
    private boolean edificable;
    private boolean hipotecada;

    public Casilla(String nombre, Grupo grupo, int posicion, Jugador banca, Tablero tablero) {
        this.nombre = nombre;
        this.grupo = grupo;
        this.posicion = posicion;
        this.propietario = banca;
        this.avatares = new HashMap<>();
        this.edificios = new ArrayList<>();
        this.vecesCaidas = new HashMap<>();
        this.tablero = tablero;
        this.edificable = false;
        this.hipotecada = false;
    }

    public Casilla(String nombre, int posicion, Jugador banca, Tablero tablero) {
        if (nombre == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Nombre nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.nombre = nombre;
        this.posicion = posicion;
        this.grupo = null;
        this.propietario = banca;
        this.avatares = new HashMap<>();
        this.vecesCaidas = new HashMap<>();
        this.edificios = new ArrayList<>();
        this.tablero = tablero;
        this.edificable = false;
        this.hipotecada = false;
    }

    //Setters y getters
    public boolean getHipotecada() {
        return hipotecada;
    }

    public void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPosicion(int posicion) {
        if (posicion < 0 || posicion > 40 || posicion < 0 || posicion > 40) {
            consola.imprimir(Valor.ANSI_ROJO + "Posicion no valida." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.posicion = posicion;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Nombre nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.nombre = nombre;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        if (grupo == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Grupo nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.grupo = grupo;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public void setPropietario(Jugador propietario) {
        if (propietario == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Propietario nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.propietario = propietario;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        if (valor < 0) {
            consola.imprimir(Valor.ANSI_ROJO + "Valor no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.valor = valor;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(int alquiler) {
        if (alquiler < 0) {
            consola.imprimir(Valor.ANSI_ROJO + "Alquiler no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.alquiler = alquiler;
    }

    public HashMap<String, Avatar> getAvatares() {
        return avatares;
    }

    public void setAvatares(HashMap<String, Avatar> avatares) {
        if (avatares == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Avatares nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.avatares = avatares;
    }

    public ArrayList<Edificio> getEdificios() {
        return edificios;
    }

    public void setEdificios(ArrayList<Edificio> edificios) {
        if (edificios == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Edificios nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.edificios = edificios;
    }

    public HashMap<Jugador, Integer> getVecesCaidas() {
        return vecesCaidas;
    }

    public void setVecesCaidas(HashMap<Jugador, Integer> vecesCaidas) {
        this.vecesCaidas = vecesCaidas;
    }

    public int getPosicion() {
        return this.posicion;
    }

    public boolean getEdificable() {
        return edificable;
    }

    public void setEdificable(boolean edificable) {
        this.edificable = edificable;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    //Metodos

    /**
     * Obtiene el string de los nombres de los edificios de la casilla
     */
    public String obtenerEdificios() {
        String cadena = "";

        if (this.edificios.size() == 0) {
            cadena = "no tiene edificios";
        } else {
            for (Edificio edificio : edificios) {
                cadena = cadena.concat(edificio.getNombre() + " ");
            }
        }
        return cadena;
    }


    /**
     * Info completa de las casillas
     */
    public abstract String info();


    /**
     * Comprueba si la casilla esta a la venta
     *
     * @return True si esta a la venta
     */
    public boolean seVende() {
        return propietario.getNombre().equals("banca") && valor > 0;
    }

    /**
     * Añade un avatar a la casilla
     */
    public void anhadirAvatar(Avatar avatar) {
        if (!this.avatares.containsKey(avatar.getId())) {
            this.avatares.put(avatar.getId(), avatar);
        } else {
            consola.imprimir("El avatar ya estaba en la casilla");
        }
    }

    /**
     * Indica las veces que se ha visitado la casilla
     */
    public void frecuenciaVisita() {
        if (this.vecesCaidas.size() != 0) {
            for (Map.Entry<Jugador, Integer> visitas_map : this.vecesCaidas.entrySet())
                consola.imprimir((visitas_map.getKey().getNombre() + " -> " + visitas_map.getValue()));
        } else {
            consola.imprimir("Nadie ha caido en la casilla.");
        }
    }

    /**
     * Comprueba si un avatar está en la casilla
     */
    public boolean estaAvatar(Avatar avatar) {
        return (this.avatares.containsKey(avatar.getId()));
    }

    /**
     * Elimina un avatar de la casilla
     */
    public void eliminarAvatar(Avatar avatar) {
        if (this.avatares.containsKey(avatar.getId())) {
            this.avatares.remove(avatar.getId());
        } else {
            consola.imprimir("El avatar no esta en la casilla");
        }
    }

    /**
     * Devuelve los IDs de los avatares de la casilla
     */
    public String stringAvatares() {
        String cadena_avatares = "";
        String cadena = "";
        if (this.avatares.size() != 0) {
            Iterator avatares_it = this.avatares.values().iterator();
            while (avatares_it.hasNext()) {
                /*Recorro los avatares y añado los IDs al string*/
                Avatar a = (Avatar) avatares_it.next();
                cadena_avatares = cadena_avatares.concat("&" + a.getId());
            }
        }
        cadena = cadena.concat(cadena_avatares);
        int t = Valor.TAMANHO_CASILLA - cadena_avatares.length();
        for (int i = 0; i < t; i++) {
            cadena = cadena.concat(" ");
        }

        return cadena;
    }

    /**
     * Devuelve el nombre de la casilla con su color correspondiente
     */
    public String printNombreColor() {
        String nombre = "";
        int t;
        if (this.nombre.length() < Valor.TAMANHO_CASILLA) {
            t = Valor.TAMANHO_CASILLA - this.nombre.length();
            if (t % 2 == 0) {
                for (int i = 0; i < t / 2; i++) {
                    nombre = nombre.concat(" ");
                }
                if (this.grupo != null) {
                    nombre = nombre.concat(this.grupo.obtenerColorPrint() + this.nombre + Valor.ANSI_RESET);
                } else {
                    nombre = nombre.concat(this.nombre);
                }
                for (int i = 0; i < t / 2; i++) {
                    nombre = nombre.concat(" ");
                }
            } else {
                for (int i = 0; i < (t / 2) + 1; i++) {
                    nombre = nombre.concat(" ");
                }
                if (this.grupo != null) {
                    nombre = nombre.concat(this.grupo.obtenerColorPrint() + this.nombre + Valor.ANSI_RESET);
                } else {
                    nombre = nombre.concat(this.nombre);
                }
                for (int i = 0; i < t / 2; i++) {
                    nombre = nombre.concat(" ");
                }
            }
        } else {
            nombre = this.grupo.obtenerColorPrint() + this.nombre + Valor.ANSI_RESET;
        }
        return nombre;
    }

    public void actualizarAlquiler() {
        alquiler = grupo.getAlquiler();
        for (Edificio edificio : edificios) {
            alquiler += edificio.getAlquiler();
        }
    }

    public void realizarAccion(Jugador jugador, Turno turno, int avance) throws ExcepcionCasilla {
        switch (this.posicion) {
            /*switch de la accion que sucede al caer en cada tipo de casilla*/
            case Valor.POSICION_CASILLA_IR_CARCEL:
                jugador.setDadosTirados(false);
                jugador.encarcelarJugador(tablero);
                jugador.setDadosTirados(false);
                consola.imprimir("El jugador va a la carcel.");
                turno.siguienteTurno();
                break;
            case Valor.POSICION_CASILLA_IMPUESTO1:
                /*Impuesto1*/
                consola.imprimir("El jugador paga un impuesto de " + Valor.ALQUILER_IMPUESTO1);
                jugador.pagarImpuesto(Valor.ALQUILER_IMPUESTO1, tablero, turno);
                break;
            case Valor.POSICION_CASILLA_IMPUESTO2:
                /*Impuesto2*/
                consola.imprimir("El jugador paga un impuesto de " + Valor.ALQUILER_IMPUESTO2);
                jugador.pagarImpuesto(Valor.ALQUILER_IMPUESTO2, tablero, turno);
                break;
            case Valor.POSICION_CASILLA_CAJA1:
            case Valor.POSICION_CASILLA_CAJA2:
            case Valor.POSICION_CASILLA_CAJA3:
                /*Caja*/
                boolean repetir = false;
                do{
                    try{
                        tablero.escogerCarta('c', jugador, turno);
                        repetir = false;
                    }catch(ExcepcionCarta ex){
                        consola.imprimir(ex.getMessage());
                        repetir = true;
                    }
                }while(repetir);
                break;
            case Valor.POSICION_CASILLA_SUERTE1:
            case Valor.POSICION_CASILLA_SUERTE2:
            case Valor.POSICION_CASILLA_SUERTE3:
                /*Suerte*/
                repetir = false;
                do{
                    try{
                        tablero.escogerCarta('s', jugador, turno);
                        repetir = false;
                    }catch(ExcepcionCarta ex){
                        consola.imprimir(ex.getMessage());
                        repetir = true;
                    }
                }while(repetir);
                break;
            case Valor.POSICION_CASILLA_PARKING:
                /*Parking*/
                consola.imprimir("El jugador cobra el dinero del Parking");
                jugador.cobrarParking();
                break;
            case Valor.POSICION_CASILLA_SERVICIO1:
            case Valor.POSICION_CASILLA_SERVICIO2:
                /*Servicio*/
                jugador.pagarAlquiler(tablero, turno, avance);
                break;
            case Valor.POSICION_CASILLA_TRANSPORTE1:
            case Valor.POSICION_CASILLA_TRANSPORTE2:
            case Valor.POSICION_CASILLA_TRANSPORTE3:
            case Valor.POSICION_CASILLA_TRANSPORTE4:
                /*Transporte*/
                jugador.pagarTransporte(tablero, turno);
                break;
            default:
                /*mapa*/
                jugador.pagarAlquiler(tablero, turno);
        }
    }

    @Override
    public String toString() {
        String cadena = this.printNombreColor();

        return cadena;
    }
}
