/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.mapa;

import java.util.ArrayList;
import monopoly.excepciones.ExcepcionTrato;
import static monopoly.mapa.Juego.consola;
import monopoly.persona.Jugador;

/**
 *
 * @author Usuario
 */
public class Trato {

    private String tipo;
    private Jugador jugador1;
    private Jugador jugador2;
    private Propiedad propiedad1;
    private Propiedad propiedad2;
    private Propiedad propiedad3;
    private int dinero;
    private int turnos;

    public Trato() {
        this.tipo = "";
        this.jugador1 = null;
        this.jugador2 = null;
        this.propiedad1 = null;
        this.propiedad2 = null;
        this.propiedad3 = null;
        this.dinero = 0;
        this.turnos = 0;
    }

    public Trato(Propiedad propiedad1, Propiedad propiedad2) throws ExcepcionTrato {
        if (propiedad1 == null) {
            throw new ExcepcionTrato("propiedad1 nulo");
        }
        if (propiedad2 == null) {
            throw new ExcepcionTrato("propiedad2 nulo");
        }
        if (propiedad1.getPropietario().getNombre().equals("banca")) {
            throw new ExcepcionTrato("Propiedad1 pertenece a la banca");
        }
        if (propiedad2.getPropietario().getNombre().equals("banca")) {
            throw new ExcepcionTrato("Propiedad2 pertencece a la banca");
        }
        if (propiedad1.getPropietario().getNombre().equals(propiedad2.getPropietario().getNombre())) {
            throw new ExcepcionTrato("Ambas propiedades tienen el mismo dueño");
        }
        this.tipo = "P4P";
        this.jugador1 = propiedad1.getPropietario();
        this.jugador2 = propiedad2.getPropietario();
        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
        this.propiedad3 = null;
        this.dinero = 0;
        this.turnos = 0;

    }

    public Trato(Jugador jugador1, int dinero, Propiedad propiedad2) throws ExcepcionTrato {
        if (propiedad2 == null) {
            throw new ExcepcionTrato("Propiedad2 nulo");
        }
        if (jugador1 == null) {
            throw new ExcepcionTrato("Jugador1 nulo");
        }
        if (propiedad2.getPropietario().getNombre().equals("banca")) {
            throw new ExcepcionTrato("Propiedad2 pertenece a la banca");
        }
        if (propiedad2.getPropietario().getNombre().equals(jugador1.getNombre())) {
            throw new ExcepcionTrato("No puedes tratar contigo mismo");
        }
        if (dinero < 0) {
            throw new ExcepcionTrato("Dinero negativo");
        }
        this.tipo = "M4P";
        this.jugador1 = jugador1;
        this.jugador2 = propiedad2.getPropietario();
        this.propiedad1 = null;
        this.propiedad2 = propiedad2;
        this.propiedad3 = null;
        this.dinero = dinero;
        this.turnos = 0;

    }

    public Trato(Propiedad propiedad1, int dinero, Jugador jugador2) throws ExcepcionTrato {
        if (propiedad1 == null) {
            throw new ExcepcionTrato("Propiedad1 nulo");
        }
        if (jugador2 == null) {
            throw new ExcepcionTrato("Jugador2 nulo");
        }
        if (propiedad1.getPropietario().getNombre().equals("banca")) {
            throw new ExcepcionTrato("Propiedad1 pertenece a la banca");
        }
        if (propiedad1.getPropietario().getNombre().equals(jugador2.getNombre())) {
            throw new ExcepcionTrato("No puedes tratar contigo mismo");
        }
        if (dinero < 0) {
            throw new ExcepcionTrato("Dinero negativo");
        }

        this.tipo = "P4M";
        this.jugador1 = propiedad1.getPropietario();
        this.jugador2 = jugador2;
        this.propiedad1 = propiedad1;
        this.propiedad2 = null;
        this.propiedad3 = null;
        this.dinero = dinero;
        this.turnos = 0;
    }

    public Trato(Propiedad propiedad1, int dinero, Propiedad propiedad2) throws ExcepcionTrato {
        if (propiedad1 == null) {
            throw new ExcepcionTrato("propiedad1 nulo");
        }
        if (propiedad2 == null) {
            throw new ExcepcionTrato("propiedad2 nulo");
        }
        if (propiedad1.getPropietario().getNombre().equals("banca")) {
            throw new ExcepcionTrato("Propiedad1 pertenece a la banca");
        }
        if (propiedad2.getPropietario().getNombre().equals("banca")) {
            throw new ExcepcionTrato("Propiedad2 pertencece a la banca");
        }
        if (propiedad1.getPropietario().getNombre().equals(propiedad2.getPropietario().getNombre())) {
            throw new ExcepcionTrato("Ambas propiedades tienen el mismo dueño");
        }
        if (dinero < 0) {
            throw new ExcepcionTrato("Dinero negativo");
        }
        this.tipo = "PM4P";
        this.jugador1 = propiedad1.getPropietario();
        this.jugador2 = propiedad2.getPropietario();
        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
        this.propiedad3 = null;
        this.dinero = dinero;
        this.turnos = 0;
    }

    public Trato(Propiedad propiedad1, Propiedad propiedad2, int dinero) throws ExcepcionTrato {
        if (propiedad1 == null) {
            throw new ExcepcionTrato("propiedad1 nulo");
        }
        if (propiedad2 == null) {
            throw new ExcepcionTrato("propiedad2 nulo");
        }
        if (propiedad1.getPropietario().getNombre().equals("banca")) {
            throw new ExcepcionTrato("Propiedad1 pertenece a la banca");
        }
        if (propiedad2.getPropietario().getNombre().equals("banca")) {
            throw new ExcepcionTrato("Propiedad2 pertencece a la banca");
        }
        if (propiedad1.getPropietario().getNombre().equals(propiedad2.getPropietario().getNombre())) {
            throw new ExcepcionTrato("Ambas propiedades tienen el mismo dueño");
        }
        if (dinero < 0) {
            throw new ExcepcionTrato("Dinero negativo");
        }
        this.tipo = "P4PM";
        this.jugador1 = propiedad1.getPropietario();
        this.jugador2 = propiedad2.getPropietario();
        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
        this.propiedad3 = null;
        this.dinero = dinero;
        this.turnos = 0;
    }

    public Trato(Propiedad propiedad1, Propiedad propiedad2, Propiedad propiedad3, int turnos) throws ExcepcionTrato {
        if (propiedad1 == null) {
            throw new ExcepcionTrato("propiedad1 nulo");
        }
        if (propiedad2 == null) {
            throw new ExcepcionTrato("propiedad2 nulo");
        }
        if (propiedad3 == null) {
            throw new ExcepcionTrato("Propiedad3 nulo");
        }
        if (propiedad1.getPropietario().getNombre().equals("banca")) {
            throw new ExcepcionTrato("Propiedad1 pertenece a la banca");
        }
        if (propiedad2.getPropietario().getNombre().equals("banca")) {
            throw new ExcepcionTrato("Propiedad2 pertencece a la banca");
        }
        if (propiedad1.getPropietario().getNombre().equals(propiedad2.getPropietario().getNombre())) {
            throw new ExcepcionTrato("Ambas propiedades tienen el mismo dueño");
        }
        if (!propiedad3.getPropietario().getNombre().equals(propiedad2.getPropietario().getNombre())) {
            throw new ExcepcionTrato(propiedad3.getNombre() + " no pertenece a " + propiedad2.getPropietario().getNombre());
        }
        if (propiedad2.getNombre().equals(propiedad3.getNombre())) {
            throw new ExcepcionTrato(propiedad2.getNombre() + " se repite");
        }
        if (turnos < 0) {
            throw new ExcepcionTrato("Turnos negativo");
        }
        this.tipo = "P4PT";
        this.jugador1 = propiedad1.getPropietario();
        this.jugador2 = propiedad2.getPropietario();
        this.propiedad1 = propiedad1;
        this.propiedad2 = propiedad2;
        this.propiedad3 = propiedad3;
        this.dinero = 0;
        this.turnos = turnos;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        if (jugador1 == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Jugador nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        if (jugador2 == null) {
            consola.imprimir(Valor.ANSI_ROJO + "Jugador nulo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.jugador2 = jugador2;
    }

    public Propiedad getPropiedad1() {
        return propiedad1;
    }

    public void setPropiedad1(Propiedad propiedad1) {
        this.propiedad1 = propiedad1;
    }

    public Propiedad getPropiedad2() {
        return propiedad2;
    }

    public void setPropiedad2(Propiedad propiedad2) {
        this.propiedad2 = propiedad2;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        if (dinero < 0) {
            consola.imprimir(Valor.ANSI_ROJO + "dinero negativo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.dinero = dinero;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        if (turnos < 0) {
            consola.imprimir(Valor.ANSI_ROJO + "Turnos negativo." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.turnos = turnos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (!tipo.equals("P4P") && !tipo.equals("M4P") && !tipo.equals("P4M") && !tipo.equals("PM4P") && !tipo.equals("P4PM") && !tipo.equals("P4PT")) {
            consola.imprimir(Valor.ANSI_ROJO + "Tipo no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.tipo = tipo;
    }

    public Propiedad getPropiedad3() {
        return propiedad3;
    }

    public void setPropiedad3(Propiedad propiedad3) {
        this.propiedad3 = propiedad3;
    }

    public void aceptarTrato() throws ExcepcionTrato {
        switch (this.tipo) {
            case "P4P":
                cambiarDuenho(this.propiedad1, this.jugador2);
                cambiarDuenho(this.propiedad2, this.jugador1);
                consola.imprimir("Se ha aceptado el siguiente trato con " + this.jugador1.getNombre() + ": le doy " + this.propiedad2.getNombre() + " y " + this.jugador1.getNombre() + " me da " + this.propiedad1.getNombre() + ".");
                break;
            case "M4P":
                if (this.jugador1.getFortuna() < this.dinero) {
                    throw new ExcepcionTrato("El trato no puede ser aceptado: " + this.jugador1.getNombre() + " no dispone de " + this.dinero);
                }
                cambiarDuenho(this.propiedad2, this.jugador1);
                this.jugador1.setFortuna(this.jugador1.getFortuna() - this.dinero);
                this.jugador2.setFortuna(this.jugador2.getFortuna() + this.dinero);
                consola.imprimir("Se ha aceptado el siguiente trato con " + this.jugador1.getNombre() + ": le doy " + this.propiedad2.getNombre() + " y " + this.jugador1.getNombre() + " me da " + this.dinero + "€.");
                break;
            case "P4M":
                if (this.jugador2.getFortuna() < this.dinero) {
                    throw new ExcepcionTrato("El trato no puede ser aceptado: " + this.jugador2.getNombre() + " no dispone de " + this.dinero);
                }
                cambiarDuenho(this.propiedad1, this.jugador2);
                this.jugador2.setFortuna(this.jugador2.getFortuna() - this.dinero);
                this.jugador1.setFortuna(this.jugador2.getFortuna() + this.dinero);
                consola.imprimir("Se ha aceptado el siguiente trato con " + this.jugador1.getNombre() + ": le doy " + this.dinero + "€ y " + this.jugador1.getNombre() + " me da " + this.propiedad1.getNombre() + ".");
                break;
            case "PM4P":
                if (this.jugador1.getFortuna() < this.dinero) {
                    throw new ExcepcionTrato("El trato no puede ser aceptado: " + this.jugador1.getNombre() + " no dispone de " + this.dinero);
                }
                cambiarDuenho(this.propiedad1, this.jugador2);
                cambiarDuenho(this.propiedad2, this.jugador1);
                this.jugador1.setFortuna(this.jugador1.getFortuna() - this.dinero);
                this.jugador2.setFortuna(this.jugador2.getFortuna() + this.dinero);
                consola.imprimir("Se ha aceptado el siguiente trato con " + this.jugador1.getNombre() + ": le doy " + this.propiedad2.getNombre() + " y " + this.jugador1.getNombre() + " me da " + this.propiedad1.getNombre() + " y " + this.dinero + "€.");
                break;
            case "P4PM":
                if (this.jugador2.getFortuna() < this.dinero) {
                    throw new ExcepcionTrato("El trato no puede ser aceptado: " + this.jugador2.getNombre() + " no dispone de " + this.dinero);
                }
                cambiarDuenho(this.propiedad1, this.jugador2);
                cambiarDuenho(this.propiedad2, this.jugador1);
                this.jugador2.setFortuna(this.jugador2.getFortuna() - this.dinero);
                this.jugador1.setFortuna(this.jugador2.getFortuna() + this.dinero);
                consola.imprimir("Se ha aceptado el siguiente trato con " + this.jugador1.getNombre() + ": le doy " + this.propiedad2.getNombre() + " y " + this.dinero + "€ y " + this.jugador1.getNombre() + " me da " + this.propiedad1.getNombre() + ".");
                break;
            case "P4PT":
                cambiarDuenho(this.propiedad1, this.jugador2);
                cambiarDuenho(this.propiedad2, this.jugador1);

                if (this.jugador1.getNopaga().containsKey(this.propiedad3.getNombre())) {
                    this.jugador1.getNopaga().put(this.propiedad3.getNombre(), this.jugador1.getNopaga().get(this.propiedad3.getNombre()) + this.turnos);
                } else {
                    this.jugador1.getNopaga().put(this.propiedad3.getNombre(), this.turnos);
                }
                consola.imprimir("Se ha aceptado el siguiente trato con " + this.jugador1.getNombre() + ": le doy " + this.propiedad2.getNombre() + " y " + this.turnos + " turnos sin pagar alquiler en " + this.propiedad3.getNombre() + " y " + this.jugador1.getNombre() + " me da " + this.propiedad1.getNombre() + ".");
                break;
        }
    }

    public void cambiarDuenho(Propiedad propiedad, Jugador jugador) throws ExcepcionTrato {
        ArrayList<Edificio> eliminables = new ArrayList<>();

        if (propiedad.getPropietario().getPropiedades().containsKey(propiedad.getNombre())) {
            jugador.getPropiedades().put(propiedad.getNombre(), propiedad);
            if (propiedad instanceof Solar) {
                for (int i = 0; i < propiedad.getPropietario().getEdificios().size(); i++) {
                    if (propiedad.getPropietario().getEdificios().get(i).getCasilla() == propiedad) {
                        eliminables.add(propiedad.getPropietario().getEdificios().get(i));
                        jugador.getEdificios().add(propiedad.getPropietario().getEdificios().get(i));
                    }
                }
                for (Edificio ed : eliminables) {
                    propiedad.getPropietario().getEdificios().remove(ed);
                }
            }
            propiedad.getPropietario().getPropiedades().remove(propiedad.getNombre());
        } else if (propiedad.getPropietario().getHipotecas().containsKey(propiedad.getNombre())) {
            jugador.getHipotecas().put(propiedad.getNombre(), propiedad);
            propiedad.getPropietario().getHipotecas().remove(propiedad.getNombre());
        } else {
            throw new ExcepcionTrato("Error en la propiedad de " + propiedad.getNombre());
        }

        propiedad.setPropietario(jugador);

    }

    public String pregunta() {
        String cadena = jugador2.getNombre() + ", ¿te doy ";

        switch (this.tipo) {
            case "P4P":
                cadena += propiedad1.getNombre() + " y tú me das " + propiedad2.getNombre() + "?";
                break;
            case "M4P":
                cadena += dinero + "€ y tú me das " + propiedad2.getNombre() + "?";
                break;
            case "P4M":
                cadena += propiedad1.getNombre() + " y tú me das " + dinero + "€?";
                break;
            case "PM4P":
                cadena += propiedad1.getNombre() + " y " + dinero + "€ y tú me das " + propiedad2.getNombre() + "?";
                break;
            case "P4PM":
                cadena += propiedad1.getNombre() + " y tú me das " + propiedad2.getNombre() + " y " + dinero + "€?";
                break;
            case "P4PT":
                cadena += propiedad1.getNombre() + " y tú me das " + propiedad2.getNombre() + " y no pago alquiler en " + propiedad3.getNombre() + " durante " + turnos + " turnos?";
                break;
        }
        return cadena;
    }

    @Override
    public String toString() {
        String cadena = new String();

        switch (this.tipo) {
            case "P4P":
                cadena = "{\n "
                        + "\t jugadorPropone: " + propiedad1.getPropietario().getNombre()
                        + ",\n\t trato: cambiar (" + propiedad1.getNombre() + ", " + propiedad2.getNombre() + ")\n}";
                break;
            case "M4P":
                cadena = "{\n "
                        + "\t jugadorPropone: " + propiedad2.getPropietario().getNombre()
                        + ",\n\t trato: cambiar (" + dinero + ", " + propiedad2.getNombre() + ")\n}";
                break;
            case "P4M":
                cadena = "{\n "
                        + "\t jugadorPropone: " + propiedad1.getPropietario().getNombre()
                        + ",\n\t trato: cambiar (" + propiedad1.getNombre() + ", " + dinero + ")\n}";
                break;
            case "PM4P":
                cadena = "{\n "
                        + "\t jugadorPropone: " + getPropiedad1().getPropietario().getNombre()
                        + ",\n\t trato: cambiar (" + getPropiedad1().getNombre() + " + " + getDinero() + ", " + getPropiedad2().getNombre() + ")\n}";
                break;
            case "P4PM":
                cadena = "{\n "
                        + "\t jugadorPropone: " + getPropiedad1().getPropietario().getNombre()
                        + ",\n\t trato: cambiar (" + getPropiedad1().getNombre() + ", " + getPropiedad2().getNombre() + " + " + getDinero() + ")\n}";
                break;
            case "P4PT":
                cadena = "{\n "
                        + "\t jugadorPropone: " + getPropiedad1().getPropietario().getNombre()
                        + ",\n\t trato: cambiar (" + getPropiedad1().getNombre() + ", " + getPropiedad2().getNombre() + " + " + turnos + " turnos en " + propiedad3.getNombre() + ")\n}";
                break;
        }
        return cadena;
    }

}
