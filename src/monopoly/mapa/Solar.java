package monopoly.mapa;

import monopoly.persona.Esfinge;
import monopoly.persona.Jugador;

import java.util.ArrayList;

import static monopoly.mapa.Juego.consola;

public class Solar extends Propiedad {

    private int numCasas;
    private int numMaximoCasas;
    private int numHoteles;
    private int numMaximoHoteles;
    private int numPiscinas;
    private int numMaximoPiscinas;
    private int numPistas;
    private int numMaximoPistas;
    private int numTotalEdificios;
    private ArrayList<Edificio> edificios;

    public Solar (String nombre, Grupo grupo, int posicion, Jugador banca, Tablero tablero) {
        super(nombre, posicion, banca, tablero);
        this.numMaximoCasas = 4;
        setGrupo(grupo);
        this.edificios = new ArrayList<>();
        switch (grupo.getColor()) {
            case Valor.GRUPO_NEGRO:
                super.setValor(Valor.COSTE_GRUPO_NEGRO);
                super.setAlquiler(Valor.ALQUILER_GRUPO_NEGRO);
                this.numMaximoHoteles = 2;
                this.numMaximoPiscinas = 2;
                this.numMaximoPistas = 2;
                this.numTotalEdificios = 8;
                break;
            case Valor.GRUPO_CYAN:
                super.setValor(Valor.COSTE_GRUPO_CYAN);
                super.setAlquiler(Valor.ALQUILER_GRUPO_CYAN);
                this.numMaximoHoteles = 3;
                this.numMaximoPiscinas = 3;
                this.numMaximoPistas = 3;
                this.numTotalEdificios = 12;
                break;
            case Valor.GRUPO_ROSA:
                super.setValor(Valor.COSTE_GRUPO_ROSA);
                super.setAlquiler(Valor.ALQUILER_GRUPO_ROSA);
                this.numMaximoHoteles = 3;
                this.numMaximoPiscinas = 3;
                this.numMaximoPistas = 3;
                this.numTotalEdificios = 12;
                break;
            case Valor.GRUPO_NARANJA:
                super.setValor(Valor.COSTE_GRUPO_NARANJA);
                super.setAlquiler(Valor.ALQUILER_GRUPO_NARANJA);
                this.numMaximoHoteles = 3;
                this.numMaximoPiscinas = 3;
                this.numMaximoPistas = 3;
                this.numTotalEdificios = 12;
                break;
            case Valor.GRUPO_ROJO:
                super.setValor(Valor.COSTE_GRUPO_ROJO);
                super.setAlquiler(Valor.ALQUILER_GRUPO_ROJO);
                this.numMaximoHoteles = 3;
                this.numMaximoPiscinas = 3;
                this.numMaximoPistas = 3;
                this.numTotalEdificios = 12;
                break;
            case Valor.GRUPO_AMARILLO:
                super.setValor(Valor.COSTE_GRUPO_AMARILLO);
                super.setAlquiler(Valor.ALQUILER_GRUPO_AMARILLO);
                this.numMaximoHoteles = 3;
                this.numMaximoPiscinas = 3;
                this.numMaximoPistas = 3;
                this.numTotalEdificios = 12;
                break;
            case Valor.GRUPO_VERDE:
                super.setValor(Valor.COSTE_GRUPO_VERDE);
                super.setAlquiler(Valor.ALQUILER_GRUPO_VERDE);
                this.numMaximoHoteles = 3;
                this.numMaximoPiscinas = 3;
                this.numMaximoPistas = 3;
                this.numTotalEdificios = 12;
                break;
            case Valor.GRUPO_AZUL:
                super.setValor(Valor.COSTE_GRUPO_AZUL);
                super.setAlquiler(Valor.ALQUILER_GRUPO_AZUL);
                this.numMaximoHoteles = 2;
                this.numMaximoPiscinas = 2;
                this.numMaximoPistas = 2;
                this.numTotalEdificios = 8;
                break;
        }
        this.numCasas = 0;
        this.numHoteles = 0;
        this.numPiscinas = 0;
        this.numPistas = 0;
    }

    @Override
    public int alquiler() {
        int alquiler = getGrupo().getAlquiler();
        for (Edificio edificio : edificios) {
            alquiler += edificio.getAlquiler();
        }

        return alquiler;
    }

    public int getNumCasas() {
        return numCasas;
    }

    public void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }

    public int getNumHoteles() {
        return numHoteles;
    }

    public void setNumHoteles(int numHoteles) {
        this.numHoteles = numHoteles;
    }

    public int getNumPiscinas() {
        return numPiscinas;
    }

    public void setNumPiscinas(int numPiscinas) {
        this.numPiscinas = numPiscinas;
    }

    public int getNumPistas() {
        return numPistas;
    }

    public void setNumPistas(int numPistas) {
        this.numPistas = numPistas;
    }

    public int getNumMaximoCasas() {
        return numMaximoCasas;
    }

    public void setNumMaximoCasas(int numMaximoCasas) {
        this.numMaximoCasas = numMaximoCasas;
    }

    public int getNumMaximoHoteles() {
        return numMaximoHoteles;
    }

    public void setNumMaximoHoteles(int numMaximoHoteles) {
        this.numMaximoHoteles = numMaximoHoteles;
    }

    public int getNumMaximoPiscinas() {
        return numMaximoPiscinas;
    }

    public void setNumMaximoPiscinas(int numMaximoPiscinas) {
        this.numMaximoPiscinas = numMaximoPiscinas;
    }

    public int getNumMaximoPistas() {
        return numMaximoPistas;
    }

    public void setNumMaximoPistas(int numMaximoPistas) {
        this.numMaximoPistas = numMaximoPistas;
    }

    public int getNumTotalEdificios() {
        return numTotalEdificios;
    }

    public void setNumTotalEdificios(int numTotalEdificios) {
        this.numTotalEdificios = numTotalEdificios;
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

    public void edificar(String tipo, Jugador jugador) {
        boolean construir = false;
        if (numCasas + numHoteles + numPiscinas + numPistas == numTotalEdificios) {
            consola.imprimir("No se pueden construir mas edificios en la casilla " + getNombre());
        } else {
            switch (tipo) {
                case Valor.EDIFICIO_CASA:
                    if (numCasas == numMaximoCasas) {
                        consola.imprimir("El solar " + getNombre() + " ya tiene " + numCasas + " casas. No se pueden construir mas.");
                    } else {
                        construir = true;
                    }
                    break;
                case Valor.EDIFICIO_HOTEL:
                    if (numHoteles == 3) {
                        consola.imprimir("El solar " + getNombre() + " ya tiene 3 hoteles. No se pueden construir mas.");
                    } else if (numHoteles == 2 && numMaximoHoteles == 2) {
                        consola.imprimir("En el solar " + getNombre() + " no se pueden construir mas hoteles.");
                    } else if (numCasas != 4) {
                        consola.imprimir("El solar " + getNombre() + " no tiene 4 casas. No se puede construir un hotel");
                    } else {
                        construir = true;
                        numCasas = 0;
                        getGrupo().setNumMaxCasas(getGrupo().getNumMaxCasas());
                        for (int i = 0; i < getEdificios().size(); i++) {
                            Edificio ed = getEdificios().get(i);
                            if (ed instanceof Casa) {
                                getEdificios().remove(i);
                                getPropietario().getEdificios().remove(ed);
                                getTablero().getEdificios().remove(ed);
                                getGrupo().getEdificios().remove(ed);
                                i--;
                            }
                        }
                    }
                    break;
                case Valor.EDIFICIO_PISCINA:
                    if (numPiscinas == 3) {
                        consola.imprimir("El solar " + getNombre() + " ya tiene 3 piscinas. No se pueden construir mas.");
                    } else if (numPiscinas == 2 && numMaximoPiscinas == 2) {
                        consola.imprimir("En el solar " + getNombre() + " no se pueden construir mas piscinas.");
                    } else if (numHoteles < 1 && numCasas < 2) {
                        consola.imprimir("El solar " + getNombre() + " no dispone de 1 hotel y 2 casas para construir una piscina.");
                    } else {
                        construir = true;
                    }
                    break;
                case Valor.EDIFICIO_PISTA:
                    if (numPistas == 3) {
                        consola.imprimir("El solar " + getNombre() + " ya tiene 3 pistas. No se pueden construir");
                    } else if (numPistas == 2 && numMaximoPistas == 2) {
                        consola.imprimir("En el solar " + getNombre() + " no se pueden construir mas pistas.");
                    } else if (numHoteles < 2) {
                        consola.imprimir("El solar " + getNombre() + " no tiene 2 hoteles. No se puede construir unha pista.");
                    } else {
                        construir = true;
                    }
                    break;
            }
            if (construir) {
                Edificio edificio = null;
                switch (tipo) {
                    case Valor.EDIFICIO_CASA:
                        Casa casa = new Casa(this);
                        edificio = casa;
                        break;
                    case Valor.EDIFICIO_HOTEL:
                        Hotel hotel = new Hotel(this);
                        edificio = hotel;
                        break;
                    case Valor.EDIFICIO_PISCINA:
                        Piscina piscina = new Piscina(this);
                        edificio = piscina;
                        break;
                    case Valor.EDIFICIO_PISTA:
                        PistaDeporte pista = new PistaDeporte(this);
                        edificio = pista;
                        break;
                }
                incrementarNumTipoEdificio(tipo);
                if (numHoteles == 3 && numMaximoHoteles == 3) {
                    getGrupo().setNumMaxCasas(getGrupo().getNumMaxCasas() - 1);
                    numMaximoCasas = 3;
                }
                if (numHoteles == 2 && numMaximoHoteles == 2) {

                    getGrupo().setNumMaxCasas(getGrupo().getNumMaxCasas()-2);
                    numMaximoCasas = 2;
                }
                if (jugador.getAvatar() instanceof Esfinge && jugador.getModoEspecial()) { // Si el jugador es de tipo Esfinge y esta en modo avanzado añadimos el edificio por si va a perderlo
                    ((Esfinge) jugador.getAvatar()).anhadirEdificioComprado(edificio);
                    ((Esfinge) jugador.getAvatar()).anhadirPerdida(edificio.getValor());
                }
                getEdificios().add(edificio);
                jugador.setFortuna(jugador.getFortuna() - edificio.getValor());
                jugador.setDineroInvertido(jugador.getFortuna() + edificio.getValor());
                jugador.setDineroInvertido(jugador.getDineroInvertido() + edificio.getValor());
                jugador.getEdificios().add(edificio);
                getGrupo().getEdificios().add(edificio);
                getTablero().getEdificios().add(edificio);
                actualizarAlquiler();
            }
        }
    }

    public void incrementarNumTipoEdificio(String tipo) {
        switch (tipo) {
            case Valor.EDIFICIO_CASA:
                numCasas++;
                break;
            case Valor.EDIFICIO_HOTEL:
                numHoteles++;
                break;
            case Valor.EDIFICIO_PISCINA:
                numPiscinas++;
                break;
            case Valor.EDIFICIO_PISTA:
                numPistas++;
                break;
        }
    }

    public void venderEdificios(String tipo, int cantidad) {
        int dinero = 0, numEliminados = 0;

        switch (tipo) { // control de tipo de edificio a vender
            case Valor.EDIFICIO_CASA:
                for (int i = getEdificios().size() - 1; i >= 0; i--) {
                    Edificio edificio = getEdificios().get(i);
                    if (edificio instanceof Casa) {
                        dinero += edificio.getValor() / 2; // cojemos valor de edificio/2
                        setAlquiler(edificio.getAlquiler()); // restamos alquiler de edificio a casilla
                        getPropietario().getEdificios().remove(edificio); // borramos el edificio del propietario
                        getEdificios().remove(edificio); // borramos el edificio de los edificios construidos en la casilla
                        getTablero().getEdificios().remove(edificio); // borramos edificio de lista de edificios total
                        getGrupo().getEdificios().remove(edificio); // borramos edificio de grupo
                        numCasas--; // reducimos numero de edificio
                        numEliminados++; // contador edificios eliminados
                    }
                    if (numEliminados == cantidad) { // si se venden los que se piden
                        consola.imprimir(getPropietario().getNombre() + " ha vendido " + cantidad + " casas en " + getNombre() + ", recibiendo " + dinero + "€. En la propiedad quedan " + numCasas + " casas.");
                        break;
                    }
                }
                getPropietario().setFortuna(getPropietario().getFortuna() + dinero); // incrementamos la fortuna del expropietario
                if (numEliminados > 0 && numEliminados != cantidad) { // si no se venden tantos como se pedia
                    consola.imprimir("Solamente se pueden vender " + numEliminados + " casas, recibiendo " + dinero + "€.");
                } else if (numEliminados == 0) { // si no se vende ninguno
                    consola.imprimir("No se ha vendido ninguna casa en " + getNombre());
                }
                break;
            case Valor.EDIFICIO_HOTEL:
                for (int i = getEdificios().size() - 1; i >= 0; i--) {
                    Edificio edificio = getEdificios().get(i);
                    if (edificio instanceof Hotel) {
                        dinero += edificio.getValor() / 2;
                        setAlquiler(edificio.getAlquiler()); // restamos alquiler de edificio a casilla
                        getPropietario().getEdificios().remove(edificio); // borramos el edificio del propietario
                        getEdificios().remove(edificio); // borramos el edificio de los edificios construidos en la casilla
                        getTablero().getEdificios().remove(edificio); // borramos edificio de lista de edificios total
                        getGrupo().getEdificios().remove(edificio); // borramos edificio de grupo
                        numHoteles--;
                        numEliminados++;
                    }
                    if (numEliminados == cantidad) {
                        consola.imprimir(getPropietario().getNombre() + " ha vendido " + cantidad + " hoteles en " + getPropietario() + ", recibiendo " + dinero + "€. En la propiedad quedan " + numHoteles + " hoteles.");
                        break;
                    }
                }
                getPropietario().setFortuna(getPropietario().getFortuna() + dinero);
                if (numEliminados > 0 && numEliminados != cantidad) {
                    consola.imprimir("Solamente se pueden vender " + numEliminados + " hoteles, recibiendo " + dinero + "€.");
                } else if (numEliminados == 0) {
                    consola.imprimir("No se ha vendido ningun hotel en " + getNombre());
                }
                break;
            case Valor.EDIFICIO_PISCINA:
                for (int i = getEdificios().size() - 1; i >= 0; i--) {
                    Edificio edificio = getEdificios().get(i);
                    if (edificio instanceof Piscina) {
                        dinero += edificio.getValor() / 2;
                        setAlquiler(edificio.getAlquiler()); // restamos alquiler de edificio a casilla
                        getPropietario().getEdificios().remove(edificio); // borramos el edificio del propietario
                        getEdificios().remove(edificio); // borramos el edificio de los edificios construidos en la casilla
                        getTablero().getEdificios().remove(edificio); // borramos edificio de lista de edificios total
                        getGrupo().getEdificios().remove(edificio); // borramos edificio de grupo
                        numPiscinas--;
                        numEliminados++;
                    }
                    if (numEliminados == cantidad) {
                        consola.imprimir(getPropietario().getNombre() + " ha vendido " + cantidad + " piscinas en " + getNombre() + ", recibiendo " + dinero + "€. En la propiedad quedan " + numPiscinas + " piscinas.");
                        break;
                    }
                }
                getPropietario().setFortuna(getPropietario().getFortuna() + dinero);
                if (numEliminados > 0 && numEliminados != cantidad) {
                    consola.imprimir("Solamente se pueden vender " + numEliminados + " piscinas, recibiendo " + dinero + "€.");
                } else if (numEliminados == 0) {
                    consola.imprimir("No se ha vendido ninguna piscina en " + getNombre());
                }
                break;
            case Valor.EDIFICIO_PISTA:
                for (int i = getEdificios().size() - 1; i >= 0; i--) {
                    Edificio edificio = getEdificios().get(i);
                    if (edificio instanceof PistaDeporte) {
                        dinero += edificio.getValor() / 2;
                        setAlquiler(edificio.getAlquiler()); // restamos alquiler de edificio a casilla
                        getPropietario().getEdificios().remove(edificio); // borramos el edificio del propietario
                        getEdificios().remove(edificio); // borramos el edificio de los edificios construidos en la casilla
                        getTablero().getEdificios().remove(edificio); // borramos edificio de lista de edificios total
                        getGrupo().getEdificios().remove(edificio); // borramos edificio de grupo
                        numPistas--;
                        numEliminados++;
                    }
                    if (numEliminados == cantidad) {
                        consola.imprimir(getPropietario().getNombre() + " ha vendido " + cantidad + " pistas en " + getNombre() + ", recibiendo " + dinero + "€. En la propiedad quedan " + numPistas + " pistas.");
                        break;
                    }
                }
                getPropietario().setFortuna(getPropietario().getFortuna() + dinero);
                if (numEliminados > 0 && numEliminados != cantidad) {
                    consola.imprimir("Solamente se pueden vender " + numEliminados + " pistas, recibiendo " + dinero + "€.");
                } else if (numEliminados == 0) {
                    consola.imprimir("No se ha vendido ninguna pista en " + getNombre());
                }
                break;
        }
    }

    public void venderEdificios() {
        int dinero = 0;
        int cantidad = 0;
        for (int i = getEdificios().size() - 1; i >= 0; i--) {
            Edificio edificio = getEdificios().get(i);

            dinero += edificio.getValor() / 2; // cojemos valor de edificio/2
            setAlquiler(edificio.getAlquiler()); // restamos alquiler de edificio a casilla
            getPropietario().getEdificios().remove(edificio); // borramos el edificio del propietario
            getEdificios().remove(edificio); // borramos el edificio de los edificios construidos en la casilla
            getTablero().getEdificios().remove(edificio); // borramos edificio de lista de edificios total
            getGrupo().getEdificios().remove(edificio); // borramos edificio de grupo
            cantidad++;

        }
        this.numCasas = 0;
        this.numHoteles = 0;
        this.numPiscinas = 0;
        this.numPistas = 0;
        if (cantidad > 0) {
            consola.imprimir(getPropietario().getNombre() + " ha vendido " + cantidad + " edificios en " + getNombre() + ", recibiendo " + dinero + "€.");
        }

        getPropietario().setFortuna(getPropietario().getFortuna() + dinero); // incrementamos la fortuna del expropietario

    }

    @Override
    public String toString() {
        String cadena = this.printNombreColor();

        return cadena;
    }

    @Override
    public String info() {
        return "{\n "
                + "\t tipo: Solar"
                + ",\n\t grupo: " + getGrupo()
                + ",\n\t propietario: " + getPropietario().getNombre()
                + ",\n\t valor: " + getValor()
                + ",\n\t alquiler actual: " + getAlquiler()
                + ",\n\t alquiler inicial: " + (int) (getValor() * 0.1)
                + ",\n\t edificios: [" + this.obtenerEdificios()
                + "]\n\t valor hotel: " + getValor() * 0.6 + " (mas cuatro casas)"
                + ",\n\t valor casa: " + getValor() * 0.6
                + ",\n\t valor piscina: " + getValor() * 0.4
                + ",\n\t valor pista de deporte: " + getValor() * 1.25
                + ",\n\t alquiler una casa: " + getValor() * 0.9 * 5
                + ",\n\t alquiler dos casas: " + getValor() * 0.9 * 15
                + ",\n\t alquiler tres casas: " + getValor() * 0.9 * 35
                + ",\n\t alquiler cuatro casas: " + getValor() * 0.9 * 50
                + ",\n\t alquiler hotel: " + getValor() * 0.9 * 70
                + ",\n\t alquiler piscina: " + getValor() * 25
                + ",\n\t alquiler pista de deporte: " + getValor() * 25
                + "\n}";
    }
}