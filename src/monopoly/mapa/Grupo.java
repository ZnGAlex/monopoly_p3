package monopoly.mapa;

import java.util.ArrayList;

public class Grupo {
    private String color;
    private ArrayList<Edificio> edificios;
    private ArrayList<Casilla> casillas;
    private int alquiler;
    private int precio;
    private int numMaxCasas;
    private int numMaxHoteles;
    private int numMaxPiscinas;
    private int numMaxPistas;

    public Grupo(String color, int precio, int alquiler) {
        this.color = color;
        this.edificios = new ArrayList<>();
        this.casillas = new ArrayList<>();
        this.precio = precio;
        this.alquiler = alquiler;
        switch (color) {
            case Valor.GRUPO_NEGRO:
            case Valor.GRUPO_AZUL:
                this.numMaxCasas = 8;
                this.numMaxHoteles = 4;
                this.numMaxPiscinas = 4;
                this.numMaxPistas = 4;
                break;
            default:
                this.numMaxCasas = 12;
                this.numMaxHoteles = 9;
                this.numMaxPiscinas = 9;
                this.numMaxPistas = 9;
                break;
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<Edificio> getEdificios() {
        return edificios;
    }

    public void setEdificios(ArrayList<Edificio> edificios) {
        this.edificios = edificios;
    }

    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    public void setCasillas(ArrayList<Casilla> casillas) {
        this.casillas = casillas;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(int alquiler) {
        this.alquiler = alquiler;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getNumMaxCasas() {
        return numMaxCasas;
    }

    public void setNumMaxCasas(int numMaxCasas) {
        this.numMaxCasas = numMaxCasas;
    }

    public int getNumMaxHoteles() {
        return numMaxHoteles;
    }

    public void setNumMaxHoteles(int numMaxHoteles) {
        this.numMaxHoteles = numMaxHoteles;
    }

    public int getNumMaxPiscinas() {
        return numMaxPiscinas;
    }

    public void setNumMaxPiscinas(int numMaxPiscinas) {
        this.numMaxPiscinas = numMaxPiscinas;
    }

    public int getNumMaxPistas() {
        return numMaxPistas;
    }

    public void setNumMaxPistas(int numMaxPistas) {
        this.numMaxPistas = numMaxPistas;
    }

    public String obtenerColorPrint() {
        switch(this.color) {
            case Valor.GRUPO_NEGRO:
                return Valor.ANSI_NEGRO;
            case Valor.GRUPO_CYAN:
                return Valor.ANSI_CYAN;
            case Valor.GRUPO_ROSA:
                return Valor.ANSI_ROSA;
            case Valor.GRUPO_NARANJA:
                return Valor.ANSI_NARANJA;
            case Valor.GRUPO_ROJO:
                return Valor.ANSI_ROJO;
            case Valor.GRUPO_AMARILLO:
                return Valor.ANSI_AMARILLO;
            case Valor.GRUPO_VERDE:
                return Valor.ANSI_VERDE;
            case Valor.GRUPO_AZUL:
                return Valor.ANSI_AZUL;
            default:
                return "";
        }
    }

    public void imprimirEdificios() {
        if (edificios.size() == 0)
            System.out.println("No se han construido edificios en el grupo " + color);
        int casas = 0, hoteles = 0, piscinas = 0, pistas = 0;
        for (Edificio edificio : edificios) {
            switch (edificio.getTipo()) {
                case Valor.EDIFICIO_CASA:
                    casas++;
                    break;
                case Valor.EDIFICIO_HOTEL:
                    hoteles++;
                    break;
                case Valor.EDIFICIO_PISCINA:
                    piscinas++;
                    break;
                case Valor.EDIFICIO_PISTA:
                    pistas++;
                    break;
            }
            System.out.println(edificio);
        }
        System.out.println("Se pueden edificar " + (numMaxCasas - casas) + " casas.");
        System.out.println("Se pueden edificar " + (numMaxHoteles - hoteles) + " hoteles.");
        System.out.println("Se pueden edificar " + (numMaxPiscinas - piscinas) + " piscinas.");
        System.out.println("Se pueden edificar " + (numMaxPistas - pistas) + " pistas.");
    }

    @Override
    public String toString() {
        return this.color;
    }
}
