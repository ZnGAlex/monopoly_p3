package monopoly.persona;

import monopoly.mapa.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Jugador {

    private String nombre;
    private Avatar avatar;
    private int fortuna;
    private HashMap<String, Casilla> propiedades;
    private HashMap<String, Casilla> hipotecas;
    private ArrayList<Edificio> edificios;
    private HashMap<String, Grupo> grupos;
    private boolean inCarcel;
    private boolean dadosTirados;
    private int dadosDobles;
    private int turnosEnCarcel;
    private boolean bancarrota;
    private int dineroInvertido;
    private int pagoDeAlquileres;
    private int pagoDeTasas;
    private int cobroDeAlquileres;
    private int pasarPorCasillaDeSalida;
    private int premiosInversionesOBote;
    private int vecesEnLaCarcel;
    private int vecesDadosTirados;
    private boolean modoEspecial;
    private int turnosDadosTiradosEspecial;
    private int turnosBloqueoModoEspecial;
    private boolean bloqueoTiroModoEspecial;
    private boolean haCompradoModoEspecial;

    // constructores
    public Jugador(String nombre, String ficha, Casilla casilla, String id) {
        if (nombre == null) {
            System.out.println(Valor.ANSI_ROJO + "Nombre nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        if (ficha == null) {
            System.out.println(Valor.ANSI_ROJO + "Ficha nula." + Valor.ANSI_RESET);
            System.exit(1);
        }
        if (ficha.equalsIgnoreCase(Valor.ESFINGE) || ficha.equalsIgnoreCase(Valor.COCHE) || ficha.equalsIgnoreCase(Valor.SOMBRERO) || ficha.equalsIgnoreCase(Valor.PELOTA)) {
            this.avatar = new Avatar(this, ficha, casilla, id);
        } else {
            System.out.println(Valor.ANSI_ROJO + "Ficha debe ser: Esfinge, Coche, Sombrero o Pelota");
            System.exit(1);
        }
        this.nombre = nombre;
        this.fortuna = Valor.FORTUNA_INICIAL;
        this.propiedades = new HashMap<>();
        this.hipotecas = new HashMap<>();
        this.edificios = new ArrayList<>();
        this.inCarcel = false;
        this.dadosTirados = false;
        this.dadosDobles = 0;
        this.turnosEnCarcel = 0;
        bancarrota = false;
        this.grupos = new HashMap<>();
        this.dineroInvertido = 0;
        this.pagoDeAlquileres = 0;
        this.cobroDeAlquileres = 0;
        this.pasarPorCasillaDeSalida = 0;
        this.vecesEnLaCarcel = 0;
        this.vecesDadosTirados = 0;
        this.modoEspecial = false;
        this.turnosDadosTiradosEspecial = 0;
        this.haCompradoModoEspecial = false;
        this.turnosBloqueoModoEspecial = 0;
        this.bloqueoTiroModoEspecial = false;
    }

    public Jugador(String nombre) {
        if (nombre == null) {
            System.out.println(Valor.ANSI_ROJO + "Nombre nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.nombre = nombre;
        this.fortuna = Valor.FORTUNA_BANCA;
        this.propiedades = new HashMap<>();
        this.hipotecas = new HashMap<>();
        this.edificios = new ArrayList<>();
        this.avatar = null;
        this.inCarcel = false;
        this.dadosTirados = false;
        this.dadosDobles = 0;
        this.turnosEnCarcel = 0;
        bancarrota = false;
        this.grupos = new HashMap<>();
        this.vecesDadosTirados = 0;
        this.modoEspecial = false;
        this.turnosDadosTiradosEspecial = 0;
        this.haCompradoModoEspecial = false;
        this.turnosBloqueoModoEspecial = 0;
        this.bloqueoTiroModoEspecial = false;
    }

    //setters y getters
    public int getPagoDeTasas() {
        return pagoDeTasas;
    }

    public void setPagoDeTasas(int pagoDeTasas) {
        if (pagoDeTasas < 0) {
            System.out.println(Valor.ANSI_ROJO + "pagoDeTasas negativo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.pagoDeTasas = pagoDeTasas;
    }

    public int getPremiosInversionesOBote() {
        return premiosInversionesOBote;
    }

    public void setPremiosInversionesOBote(int premiosInversionesOBote) {
        if (premiosInversionesOBote < 0) {
            System.out.println(Valor.ANSI_ROJO + "premiosInversionesOBote negativo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.premiosInversionesOBote = premiosInversionesOBote;
    }

    public int getDineroInvertido() {
        return dineroInvertido;
    }

    public void setDineroInvertido(int dineroInvertido) {
        if (dineroInvertido < 0) {
            System.out.println(Valor.ANSI_ROJO + "dineroInvertido negativo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.dineroInvertido = dineroInvertido;
    }

    public int getPagoDeAlquileres() {
        return pagoDeAlquileres;
    }

    public void setPagoDeAlquileres(int pagoDeAlquileres) {
        if (pagoDeAlquileres < 0) {
            System.out.println(Valor.ANSI_ROJO + "pagoDeAlquileres negativo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.pagoDeAlquileres = pagoDeAlquileres;
    }

    public int getCobroDeAlquileres() {
        return cobroDeAlquileres;
    }

    public void setCobroDeAlquileres(int cobroDeAlquileres) {
        if (cobroDeAlquileres < 0) {
            System.out.println(Valor.ANSI_ROJO + "cobroDeAlquileres negativo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.cobroDeAlquileres = cobroDeAlquileres;
    }

    public int getPasarPorCasillaDeSalida() {
        return pasarPorCasillaDeSalida;
    }

    public void setPasarPorCasillaDeSalida(int pasarPorCasillaDeSalida) {
        if (pasarPorCasillaDeSalida < 0) {
            System.out.println(Valor.ANSI_ROJO + "pasarPorCasillaDeSalida negativo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.pasarPorCasillaDeSalida = pasarPorCasillaDeSalida;
    }

    public int getVecesEnLaCarcel() {
        return vecesEnLaCarcel;
    }

    public void setVecesEnLaCarcel(int vecesEnLaCarcel) {
        if (vecesEnLaCarcel < 0) {
            System.out.println(Valor.ANSI_ROJO + "vecesEnLaCarcel negativo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.vecesEnLaCarcel = vecesEnLaCarcel;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTurnosEnCarcel() {
        return turnosEnCarcel;
    }

    public void setTurnosEnCarcel(int turnosEnCarcel) {
        this.turnosEnCarcel = turnosEnCarcel;
    }

    public boolean getBancarrota() {
        return bancarrota;
    }

    public void setBancarrota(boolean bancarrota) {
        this.bancarrota = bancarrota;
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            System.out.println(Valor.ANSI_ROJO + "Nombre nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.nombre = nombre;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        if (avatar == null) {
            System.out.println(Valor.ANSI_ROJO + "Avatar nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.avatar = avatar;
    }

    public int getFortuna() {
        return fortuna;
    }

    public void setFortuna(int fortuna) {
        if (fortuna < 0) {
            System.out.println(Valor.ANSI_ROJO + "Fortuna negativa." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.fortuna = fortuna;
    }

    public HashMap<String, Casilla> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(HashMap<String, Casilla> propiedades) {
        if (propiedades == null) {
            System.out.println(Valor.ANSI_ROJO + "Propiedades nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.propiedades = propiedades;
    }

    public HashMap<String, Casilla> getHipotecas() {
        return hipotecas;
    }

    public void setHipotecas(HashMap<String, Casilla> hipotecas) {
        if (hipotecas == null) {
            System.out.println(Valor.ANSI_ROJO + "Hipotecas nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.hipotecas = hipotecas;
    }

    public ArrayList<Edificio> getEdificios() {
        return edificios;
    }

    public void setEdificios(ArrayList<Edificio> edificios) {
        if (edificios == null) {
            System.out.println(Valor.ANSI_ROJO + "Edificios nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.edificios = edificios;
    }

    public boolean getInCarcel() {
        return inCarcel;
    }

    public void setInCarcel(boolean inCarcel) {
        this.inCarcel = inCarcel;
    }

    public boolean getDadosTirados() {
        return dadosTirados;
    }

    public void setDadosTirados(boolean dadosTirados) {
        this.dadosTirados = dadosTirados;
    }

    public int getDadosDobles() {
        return dadosDobles;
    }

    public void setDadosDobles(int dadosDobles) {
        if (dadosDobles < 0 || dadosDobles > 3) {
            System.out.println(Valor.ANSI_ROJO + "dadosDobles no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.dadosDobles = dadosDobles;
    }

    public HashMap<String, Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(HashMap<String, Grupo> grupos) {
        if (grupos == null) {
            System.out.println("grupos nulos");
            System.exit(1);
        }
        this.grupos = grupos;
    }

    public int getVecesDadosTirados() {
        return vecesDadosTirados;
    }

    public void setVecesDadosTirados(int vecesDadosTirados) {
        this.vecesDadosTirados = vecesDadosTirados;
    }

    public boolean getModoEspecial() {
        return modoEspecial;
    }

    public void setModoEspecial(boolean modoEspecial) {
        this.modoEspecial = modoEspecial;
    }

    public int getTurnosDadosTiradosEspecial() {
        return turnosDadosTiradosEspecial;
    }

    public void setTurnosDadosTiradosEspecial(int turnosDadosTiradosEspecial) {
        this.turnosDadosTiradosEspecial = turnosDadosTiradosEspecial;
    }

    public boolean getHaCompradoModoEspecial() {
        return haCompradoModoEspecial;
    }

    public void setHaCompradoModoEspecial(boolean haCompradoModoEspecial) {
        this.haCompradoModoEspecial = haCompradoModoEspecial;
    }

    public int getTurnosBloqueoModoEspecial() {
        return turnosBloqueoModoEspecial;
    }

    public void setTurnosBloqueoModoEspecial(int turnosBloqueoModoEspecial) {
        this.turnosBloqueoModoEspecial = turnosBloqueoModoEspecial;
    }

    public boolean getBloqueoTiroModoEspecial() {
        return bloqueoTiroModoEspecial;
    }

    public void setBloqueoTiroModoEspecial(boolean bloqueoTiroModoEspecial) {
        this.bloqueoTiroModoEspecial = bloqueoTiroModoEspecial;
    }

    // Metodos
    public void aumentarTurnosDadosTiradosEspecial() {
        this.turnosDadosTiradosEspecial++;
    }

    public void aumentarTurnosBloqueoTiroModoEspecial() {
        this.turnosBloqueoModoEspecial++;
    }

    // obtencion del listado de casillas del jugador
    public String estadisticasJugador() {
        String cadena = "{\n"
                + "\t dineroInvertido: " + this.dineroInvertido
                + ",\n\t pagoDeAlquileres: " + this.pagoDeAlquileres
                + ",\n\t pasarPorCasillaDeSalida: " + this.pasarPorCasillaDeSalida
                + ",\n\t pagoDeTasas: " + this.pagoDeTasas
                + "]\n\t premiosInversionesOBote: " + this.premiosInversionesOBote
                + "]\n\t vecesEnLaCarcel: " + this.vecesEnLaCarcel + "]\n}";
        return cadena;
    }

    /**
     * Obtiene el String de los nombres de las propiedaddes del jugador
     */
    public String obtenerPropiedades() {
        String cadena = "";

        if (this.propiedades.size() == 0) {
            cadena = "no tiene propiedades";
        } else {
            Iterator propiedades_i = this.propiedades.values().iterator();
            while (propiedades_i.hasNext()) {
                Casilla propiedad = (Casilla) propiedades_i.next();
                cadena = cadena.concat(propiedad.getNombre() + " ");
            }
        }

        return cadena;
    }

    // obtencion del listado de hipotecas del jugador
    /**
     * Obtiene el string de los nombres de las hipotecas del jugador
     */
    public String obtenerHipotecas() {
        String cadena = "";

        if (this.hipotecas.size() == 0) {
            cadena = "no tiene hipotecas";
        } else {
            Iterator hipotecas_i = this.hipotecas.values().iterator();
            while (hipotecas_i.hasNext()) {
                Casilla hipoteca = (Casilla) hipotecas_i.next();
                cadena = cadena.concat(hipoteca.getNombre() + " ");
            }
        }

        return cadena;
    }

    // obtencion del listado de edificios del jugador
    /**
     * Obtiene el string de los nombres de los edificios del jugador
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

    // metodo para que el jugador tire dados
    /**
     * Realiza la accion de tirar los dados y desplazar al jugador. Tiene en
     * cuenta si esta dentro de la carcel
     */
    public void tirarDadosJugador(Tablero tablero, Turno turno) {
        Dado dados = new Dado();
        int desplazamiento = 0;

        desplazamiento = dados.tirarDados();
        this.dadosTirados = true;
        this.vecesDadosTirados++;

        System.out.println("El jugador ha sacado un " + desplazamiento + "(" + dados.getDado1() + "+" + dados.getDado2() + ")");

        //cambiarAlquilerCasillas(tablero, desplazamiento);
        if (this.inCarcel) {
            this.turnosEnCarcel++;
        }
        if (this.inCarcel && this.turnosEnCarcel == 3) {
            /*Cuando el jugador ha cumplido todos sus intentos de salir de la carcel con los dados tiene que pagar*/
            System.out.println("El jugador " + this.nombre + " ha tirado tres veces en la carcel. Tiene que pagar para salir.");
            if (this.fortuna >= Valor.COSTE_SALIR_CARCEL) {
                this.fortuna -= Valor.COSTE_SALIR_CARCEL;
                this.pagoDeTasas += Valor.COSTE_SALIR_CARCEL;
                System.out.println("El jugador " + this.nombre + " ha pagado para salir de la carcel y se desplaza " + desplazamiento + " casillas.");
                this.avatar.moverAvatar(desplazamiento, tablero, turno);
                this.inCarcel = false;
            } else {
                while (Valor.COSTE_SALIR_CARCEL > this.fortuna && !this.bancarrota) {
                    /*Si no le llega el dinero para salir de la carcel debe hipotecarse o declararse en bancarrota*/
                    System.out.println("El jugador " + this.nombre + " no dispone de suficiente dinero. Que quieres hacer?");
                    System.out.println("Hipotecar propiedad (hipotecarse) o declararse en bancarrota (bancarrota): ");
                    String opcion;

                    Scanner sc = new Scanner(System.in);
                    opcion = sc.nextLine();
                    switch (opcion) {
                        case "bancarrota":
                            this.declararBancarrota(this.getAvatar().getCasilla().getPropietario(), tablero, turno);// funcion bancarrota
                            break;
                        case "hipotecarse":
                            this.hipotecar(); // el usuario se hipoteca
                            break;
                        default:
                            System.out.println("Opcion incorrecta.");
                            break;
                    }
                }
                if (!this.bancarrota) {
                    this.inCarcel = false;
                    this.fortuna -= Valor.COSTE_SALIR_CARCEL;
                    this.pagoDeTasas += Valor.COSTE_SALIR_CARCEL;
                    System.out.println("El jugador " + this.nombre + " ha pagado para salir de la carcel y se desplaza" + desplazamiento + " casillas.");
                    this.avatar.moverAvatar(desplazamiento, tablero, turno);
                }
            }
        } else if (this.inCarcel && this.turnosEnCarcel != 3) {
            /*Si esta en la crcel pero aun puede lanzar dados para intentar salir*/
            if (dados.dadosIguales()) {
                System.out.println("El jugador " + this.nombre + " ha sacado dados dobles. Sale de la carcel.");
                this.avatar.moverAvatar(desplazamiento, tablero, turno);
                this.turnosEnCarcel = 0;
            } else {
                System.out.println("El jugador no ha sacado dados dobles. Permanece en la carcel. Lleva " + this.turnosEnCarcel + " turnos en la carcel.");
            }
        } else {
            /*Si no esta en la carcel*/
            if (dados.dadosIguales()) {
                System.out.println("Dados dobles.");
                this.dadosTirados = false;
                this.setDadosDobles(this.getDadosDobles() + 1);
            }
            if (this.getDadosDobles() == 3) {
                /*Si es la tercera vez que saca dobles va a la carcel*/
                System.out.println("El jugador " + this.nombre + " ha sacado dados dobles tres veces. Va a la carcel.");
                this.encarcelarJugador(tablero);
                System.out.println("El jugador " + this.nombre + " acaba su turno.");
                turno.siguienteTurno();
            } else {
                System.out.println(this.nombre + " se desplaza " + desplazamiento + " posiciones");
                avatar.moverAvatar(desplazamiento, tablero, turno);
            }
        }
    }

    public void tirarDadosJugadorEspecial(Tablero tablero, Turno turno) {
        Dado dados = new Dado();

        int desplazamiento = dados.tirarDados();
        this.vecesDadosTirados++;

        //cambiarAlquilerCasillas(tablero, desplazamiento);
        System.out.println("El jugador ha sacado un " + desplazamiento + "(" + dados.getDado1() + "+" + dados.getDado2() + ")");

        if (this.inCarcel) {
            this.turnosEnCarcel++;
        }
        if (this.inCarcel && this.turnosEnCarcel == 3) {
            /*Cuando el jugador ha cumplido todos sus intentos de salir de la carcel con los dados tiene que pagar*/
            System.out.println("El jugador " + this.nombre + " ha tirado tres veces en la carcel. Tiene que pagar para salir.");
            if (this.fortuna >= Valor.COSTE_SALIR_CARCEL) {
                this.fortuna -= Valor.COSTE_SALIR_CARCEL;
                this.pagoDeTasas += Valor.COSTE_SALIR_CARCEL;
                System.out.println("El jugador " + this.nombre + " ha pagado para salir de la carcel y se desplaza " + desplazamiento + " casillas.");
                this.avatar.moverAvatarEspecial(desplazamiento, tablero, turno);
                this.inCarcel = false;
            } else {
                while (Valor.COSTE_SALIR_CARCEL > this.fortuna && !this.bancarrota) {
                    /*Si no le llega el dinero para salir de la carcel debe hipotecarse o declararse en bancarrota*/
                    System.out.println("El jugador " + this.nombre + " no dispone de suficiente dinero. Que quieres hacer?");
                    System.out.println("Hipotecar propiedad (hipotecarse) o declararse en bancarrota (bancarrota): ");
                    String opcion;

                    Scanner sc = new Scanner(System.in);
                    opcion = sc.nextLine();
                    switch (opcion) {
                        case "bancarrota":
                            this.declararBancarrota(this.getAvatar().getCasilla().getPropietario(), tablero, turno);// funcion bancarrota
                            break;
                        case "hipotecarse":
                            this.hipotecar(); // el usuario se hipoteca
                            break;
                        default:
                            System.out.println("Opcion incorrecta.");
                            break;
                    }
                }
                if (!this.bancarrota) {
                    this.inCarcel = false;
                    this.fortuna -= Valor.COSTE_SALIR_CARCEL;
                    this.pagoDeTasas += Valor.COSTE_SALIR_CARCEL;
                    System.out.println("El jugador " + this.nombre + " ha pagado para salir de la carcel y se desplaza" + desplazamiento + " casillas.");
                    this.avatar.moverAvatarEspecial(desplazamiento, tablero, turno);
                }
            }
        } else if (this.inCarcel && this.turnosEnCarcel != 3) {
            /*Si esta en la crcel pero aun puede lanzar dados para intentar salir*/
            if (dados.dadosIguales()) {
                System.out.println("El jugador " + this.nombre + " ha sacado dados dobles. Sale de la carcel.");
                this.avatar.moverAvatarEspecial(desplazamiento, tablero, turno);
                this.turnosEnCarcel = 0;
            } else {
                System.out.println("El jugador no ha sacado dados dobles. Permanece en la carcel. Lleva " + this.turnosEnCarcel + " turnos en la carcel.");
                this.dadosTirados = true;
            }
        } else {
            System.out.println(this.nombre + " se desplaza " + desplazamiento + " posiciones");
            avatar.moverAvatarEspecial(desplazamiento, tablero, turno);
        }
    }

    // metodo para llevar el jugador a la carcel
    /**
     * Mete al jugador en la carcel
     */
    public void encarcelarJugador(Tablero tablero) {
        this.avatar.moverAvatarCasilla(tablero.casillaByName("Carcel"));
        this.vecesEnLaCarcel += 1;
        this.inCarcel = true;
    }

    // metodo para que el jugador pueda salir de la carcel
    /**
     * Saca al jugador de la carcel cobrandole. No se usa cuando es obligatorio
     * pagar
     */
    public void salirCarcel() {
        if (this.inCarcel = true) {
            if (Valor.COSTE_SALIR_CARCEL > this.fortuna) {
                System.out.println("No tienes suficiente dinero");
            } else {
                System.out.println(nombre + " paga " + Valor.COSTE_SALIR_CARCEL + " y sale de la cárcel. Puede lanzar los dados.");
                this.fortuna -= Valor.COSTE_SALIR_CARCEL;
                this.pagoDeTasas += Valor.COSTE_SALIR_CARCEL;
                this.inCarcel = false;
                this.dadosTirados = false;
            }
        }
    }

    /**
     * Cobra el impuesto al jugador
     */
    public void pagarImpuesto(int impuesto, Tablero tablero, Turno turno) {
        while (impuesto > this.fortuna && !bancarrota) {
            /*Si no le llega el dinero se hipoteca o declara en bancarrota*/
            Scanner scanner = new Scanner(System.in);
            System.out.println("No tienes suficiente dinero. ¿Quieres hipotecar o declararte en bancarrota?: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "hipotecarse":
                    this.hipotecar();
                    break;
                case "bancarrota":
                    this.declararBancarrota(tablero.getCasillas().get(0).get(0).getPropietario(), tablero, turno);
                    break;
                default:
                    System.out.println("Opcion incorrecta, las opciones son 'hipotecarse' y 'bancarrota'");
            }

        }
        if (!bancarrota) {
            this.fortuna -= impuesto;
            this.pagoDeTasas += impuesto;
            Valor.DINERO_PARKING += impuesto;
            /*El impuesto se suma al parking*/
        }
    }

    /**
     * Da el dinero del Parking al jugador
     */
    public void cobrarParking() {
        this.fortuna += Valor.DINERO_PARKING;
        this.premiosInversionesOBote += Valor.DINERO_PARKING;
        Valor.DINERO_PARKING = 0;
    }

    /**
     * Cobra el alquiler de la casilla actual al jugador (si es que tiene que
     * pagar algo)
     */
    public void pagarAlquiler(Tablero tablero, Turno turno) {
        if (!this.avatar.getCasilla().getPropietario().getNombre().equals(this.nombre) && !this.avatar.getCasilla().getHipotecada()) {
            /*Si la casilla no pertenece al jugador*/

            if (!this.avatar.getCasilla().getPropietario().getNombre().equals("banca")) {
                /*Si la casilla no pertenece a la banca*/
                while (this.avatar.getCasilla().getAlquiler() > this.fortuna && !bancarrota) {
                    /*Si no le llega el dinero se hipoteca o declara bancarrota*/
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("No tienes suficiente dinero. ¿Quieres hipotecar o declararte en bancarrota?: ");
                    String opcion = scanner.nextLine();

                    switch (opcion) {
                        case "hipotecarse":
                            this.hipotecar();
                            break;
                        case "bancarrota":
                            this.declararBancarrota(this.avatar.getCasilla().getPropietario(), tablero, turno);
                            break;
                        default:
                            System.out.println("Opcion incorrecta, las opciones son 'hipotecarse' y 'bancarrota'");
                    }

                }
                if (!bancarrota) {
                    /*Pago del alquiler*/
                    int coste = this.getAvatar().getCasilla().getAlquiler();
                    if (this.avatar.getCasilla().getPropietario().getGrupos().containsKey(this.getAvatar().getCasilla().getGrupo().getColor())) {
                        coste = this.getAvatar().getCasilla().getAlquiler() * 2;
                    }
                    System.out.println("El jugador " + this.nombre + " paga " + coste + " a " + this.avatar.getCasilla().getPropietario().getNombre());
                    this.fortuna -= coste;
                    this.pagoDeAlquileres += coste;
                    this.avatar.getCasilla().getPropietario().cobrarAlquiler(coste);

                }
            }
        }
    }

    /**
     * Cobra el alquiler de la casilla de servicio actual al jugador
     */
    public void pagarAlquiler(Tablero tablero, Turno turno, int valorDados) {
        int valorPagar;
        if (!this.avatar.getCasilla().getPropietario().getNombre().equals(this.getNombre()) && !this.avatar.getCasilla().getHipotecada()) {
            if (!this.avatar.getCasilla().getPropietario().getNombre().equals("banca")) {
                /*Calculo del alquiler en funcion del valor de los dados y del numero de casillas de servicio poseidas por el mismo propietario*/
                if (this.avatar.getCasilla().getPropietario().getNombre().equals(tablero.getCasillas().get(1).get(2).getPropietario().getNombre()) && this.avatar.getCasilla().getPropietario().getNombre().equals(tablero.getCasillas().get(2).get(8).getPropietario().getNombre())) {
                    valorPagar = this.avatar.getCasilla().getAlquiler() * valorDados * 10;
                } else {
                    valorPagar = this.avatar.getCasilla().getAlquiler() * valorDados * 4;
                }
                while (valorPagar > this.fortuna && !bancarrota) {
                    /*Si el dinero no le llega se hipoteca o declara en bancarrota*/
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("No tienes suficiente dinero. ¿Quieres hipotecar o declararte en bancarrota?: ");
                    String opcion = scanner.nextLine();

                    switch (opcion) {
                        case "hipotecarse":
                            this.hipotecar();
                            break;
                        case "bancarrota":
                            this.declararBancarrota(this.avatar.getCasilla().getPropietario(), tablero, turno);
                            break;
                        default:
                            System.out.println("Opcion incorrecta, las opciones son 'hipotecarse' y 'bancarrota'");
                    }

                }

                if (!bancarrota) {
                    /*Pagar el alquiler*/
                    System.out.println("El jugador " + this.nombre + " paga " + valorPagar + " a " + this.avatar.getCasilla().getPropietario().getNombre());
                    this.fortuna -= valorPagar;
                    this.pagoDeAlquileres += valorPagar;
                    this.avatar.getCasilla().getPropietario().cobrarAlquiler(valorPagar);
                }
            }
        }
    }

    /**
     * Cobra el alquiler de la casilla de transporte actual al jugador
     */
    public void pagarTransporte(Tablero tablero, Turno turno) {
        int valorPagar, numEstaciones = 0;

        if (!this.avatar.getCasilla().getPropietario().getNombre().equals(this.getNombre()) && !this.avatar.getCasilla().getHipotecada()) {
            if (!this.avatar.getCasilla().getPropietario().getNombre().equals("banca")) {
                /*Calculo del numero de casillas de transporte con el mismo dueño*/
                if (this.avatar.getCasilla().getPropietario().getNombre().equals(tablero.getCasillas().get(0).get(5).getPropietario().getNombre())) {
                    numEstaciones++;
                }
                if (this.avatar.getCasilla().getPropietario().getNombre().equals(tablero.getCasillas().get(1).get(5).getPropietario().getNombre())) {
                    numEstaciones++;
                }
                if (this.avatar.getCasilla().getPropietario().getNombre().equals(tablero.getCasillas().get(2).get(5).getPropietario().getNombre())) {
                    numEstaciones++;
                }
                if (this.avatar.getCasilla().getPropietario().getNombre().equals(tablero.getCasillas().get(3).get(5).getPropietario().getNombre())) {
                    numEstaciones++;
                }
                valorPagar = (int) (this.avatar.getCasilla().getAlquiler() * numEstaciones * 0.25);
                /*Calculo del alquiler*/
                while (valorPagar > this.fortuna && !bancarrota) {
                    /*SI no le llega el dinero hipoteca/bancarrota*/
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("No tienes suficiente dinero. ¿Quieres hipotecar o declararte en bancarrota?: ");
                    String opcion = scanner.nextLine();

                    switch (opcion) {
                        case "hipotecarse":
                            this.hipotecar();
                            break;
                        case "bancarrota":
                            this.declararBancarrota(this.avatar.getCasilla().getPropietario(), tablero, turno);
                            break;
                        default:
                            System.out.println("Opcion incorrecta, las opciones son 'hipotecarse' y 'bancarrota'");
                    }

                }

                if (!bancarrota) {
                    /*Pagar*/
                    System.out.println("El jugador " + this.nombre + " paga " + valorPagar + " a " + this.avatar.getCasilla().getPropietario().getNombre());
                    this.fortuna -= valorPagar;
                    this.pagoDeAlquileres += valorPagar;
                    this.avatar.getCasilla().getPropietario().cobrarAlquiler(valorPagar);
                }
            }
        }
    }

    /**
     * Elegir una propiedad para hipotecar
     */
    public void hipotecar() {
        boolean flag = true;
        do {
            System.out.println(this.propiedades.values());
            Scanner scanner = new Scanner(System.in);
            System.out.println("Propiedar a hipotecar o cancelar: ");
            String prop = scanner.nextLine();

            if (this.propiedades.containsKey(prop)) {
                /*Hipotecar la propiedad*/
                this.fortuna += (int) (0.5 * this.propiedades.get(prop).getValor());
                this.hipotecas.put(prop, this.propiedades.get(prop));
                this.propiedades.remove(prop);
                this.hipotecas.get(prop).setHipotecada(true);
                this.hipotecas.get(prop).venderEdificios();
                flag = false;
            } else if (prop.equals("cancelar")) {
                /*Para salir del bucle sin hipotecar*/
                return;
            } else {
                System.out.println("No tienes esa propiedad");
            }
        } while (flag);
    }

    /**
     * Hipotecar la casilla cas si es posible
     */
    public void hipotecar(Casilla cas) {
        if (this.propiedades.containsKey(cas.getNombre())) {
            /*Hipotecar la propiedad*/
            this.fortuna += (int) (0.5 * this.propiedades.get(cas.getNombre()).getValor());
            this.hipotecas.put(cas.getNombre(), this.propiedades.get(cas.getNombre()));
            this.propiedades.remove(cas.getNombre());
            this.hipotecas.get(cas.getNombre()).setHipotecada(true);
            this.hipotecas.get(cas.getNombre()).venderEdificios();
            System.out.println("El jugador " + this.nombre + " hipoteca " + cas.getNombre() + " por " + (0.5 * this.hipotecas.get(cas.getNombre()).getValor())
                    + " €\nSu fortuna actual es: " + this.fortuna);
        } else {
            System.out.println("No tienes esa propiedad");
        }
    }

    public void deshipotecar(Casilla cas) {
        if (this.hipotecas.containsKey(cas.getNombre())) {
            int precio = (int) (0.5 * this.hipotecas.get(cas.getNombre()).getValor());
            if (precio > this.fortuna) {
                System.out.println("No tienes suficiente dinero para deshipotecar " + cas.getNombre()
                        + "\nCantidad necesaria: " + precio);
            } else {
                /*Deshipotecar la propiedad*/
                this.fortuna -= precio;
                this.propiedades.put(cas.getNombre(), this.hipotecas.get(cas.getNombre()));
                this.hipotecas.remove(cas.getNombre());
                System.out.println("El jugador " + this.nombre + " deshipoteca " + cas.getNombre() + " por " + precio
                        + " €\nSu fortuna actual es: " + this.fortuna);
            }
        } else {
            System.out.println("No tienes esa hipoteca");
        }
    }

    /**
     * Declara la bancarrota del jugador y da sus posesiones a 'jugador'
     */
    public void declararBancarrota(Jugador jugador, Tablero tablero, Turno turno) {
        this.bancarrota = true;
        for (Casilla prop : this.propiedades.values()) {
            /*Traspase de las propiedades*/
            jugador.getPropiedades().put(prop.getNombre(), prop);
            prop.setPropietario(jugador);
        }
        this.propiedades.clear();
        
        for (Casilla hip : this.hipotecas.values()) {
            /*Traspase de las hipotecas*/
            jugador.getHipotecas().put(hip.getNombre(), hip);
            hip.setPropietario(jugador);
        }
        this.hipotecas.clear();
        
        tablero.getJugadores().remove(this.getNombre());
        /*Eliminacion del jugador del tablero*/

        turno.getJugadores().remove(this);

        jugador.getAvatar().getCasilla().eliminarAvatar(jugador.getAvatar());
        /*Eliminacion del avatar*/
        if (tablero.getJugadores().size() == 1) {
            Iterator jug_it = tablero.getJugadores().values().iterator();
            while (jug_it.hasNext()) {
                Jugador ganador = (Jugador) jug_it.next();
                System.out.println("El ganador de la partida es " + ganador.getNombre());
                /*Si solo queda un jugador, este es el ganador*/
            }
            System.exit(0);
        }

    }

    public void hipotecarOBancarrota(Jugador jugador, Tablero tablero, Turno turno, int precio) {
        while (precio > this.fortuna && !bancarrota) {
            /*Si no le llega el dinero se hipoteca o declara bancarrota*/
            Scanner scanner = new Scanner(System.in);
            System.out.println("No tienes suficiente dinero para pagar " + precio + "€. ¿Quieres hipotecar o declararte en bancarrota?: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "hipotecarse":
                    this.hipotecar();
                    break;
                case "bancarrota":
                    this.declararBancarrota(jugador, tablero, turno);
                    break;
                default:
                    System.out.println("Opcion incorrecta, las opciones son 'hipotecarse' y 'bancarrota'");
            }
        }
    }

    /**
     * Suma la cantidad 'dinero' a la fortuna del jugador
     */
    public void cobrarAlquiler(int dinero) {
        this.fortuna += dinero;
        this.cobroDeAlquileres += dinero;
    }

    /**
     * Compra la casilla en la que se encuentre el jugador
     */
    public void comprarCasilla(Tablero tablero) {
        Casilla c = this.avatar.getCasilla();
        if (!c.getPropietario().getNombre().equals("banca") || c.getValor() == 0) {
            /*Comprobacion de que la casilla no es comprable*/
            System.out.println("La casilla no se puede comprar");
        } else if (this.fortuna > c.getValor()) {
            /*Si le llega el dinero*/
            this.fortuna = fortuna - c.getValor();
            this.propiedades.put(c.getNombre(), c);
            c.setPropietario(this);
            Iterator grupos = tablero.getGrupos().values().iterator();
            while (grupos.hasNext()) {
                int tienePropiedad = 0;
                Grupo g = (Grupo) grupos.next();
                for (Casilla casilla : g.getCasillas()) {
                    if (casilla.getPropietario().getNombre().equals(this.nombre)) {
                        tienePropiedad++;
                    }
                }
                if (tienePropiedad == g.getCasillas().size()) {
                    this.anhadirGrupo(g);
                }
            }
            //cambiarAlquilerCasillas(tablero);
            System.out.println("El jugador " + this.nombre + " compra la casilla " + c.getNombre() + " por " + c.getValor() + "€");
            this.dineroInvertido += c.getValor();
            System.out.println("Su fortuna actual es " + this.fortuna + "€");
        } else {
            System.out.println("No tienes suficiente dinero");
        }
    }

    public void cambiarModo() {
        if (modoEspecial) {
            modoEspecial = false;
        } else {
            modoEspecial = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Jugador)) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final Jugador jugador = (Jugador) o;
        if (this.nombre.equals(jugador.nombre) && this.avatar.equals(jugador.avatar)) {
            return true;
        }

        return true;
    }

    @Override
    public String toString() {
        String cadena = "{\n"
                + "\t nombre: " + this.nombre
                + ",\n\t avatar: " + this.avatar.getId()
                + ",\n\t fortuna: " + this.fortuna
                + ",\n\t propiedades: [" + this.obtenerPropiedades()
                + "]\n\t hipotecas: [" + this.obtenerHipotecas()
                + "]\n\t edificios: [" + this.obtenerEdificios() + "]\n}";
        return cadena;
    }

    public void anhadirGrupo(Grupo grupo) {
        this.grupos.put(grupo.getColor(), grupo);
    }
}
