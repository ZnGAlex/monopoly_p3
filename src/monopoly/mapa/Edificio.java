package monopoly.mapa;

import static monopoly.Juego.consola;

public class Edificio {
    private String nombre;
    private String tipo;
    private Casilla casilla;
    private Grupo grupo;
    private int valor;
    private int alquiler;

    public Edificio(String tipo, Solar solar) {
        if (tipo == null) {
            consola.imprimir("Tipo nulo.");
            System.exit(1);
        }
        if (solar == null) {
            consola.imprimir("Casilla nula.");
            System.exit(1);
        }
        this.tipo = tipo;
        this.casilla = solar;
        this.grupo = solar.getGrupo();
        switch (tipo) {
            case Valor.EDIFICIO_CASA:
                this.valor = (int) (solar.getValor() * 0.6);
                switch (solar.getNumCasas()) {
                    case 0:
                        this.nombre = "casa-1-" + solar.getNombre();
                        this.alquiler = (int) (solar.getValor() * 0.1 * 5);
                        break;
                    case 1:
                        this.nombre = "casa-2-" + solar.getNombre();
                        this.alquiler = (int) (solar.getValor() * 0.1 * 15);
                        break;
                    case 2:
                        this.nombre = "casa-3-" + solar.getNombre();
                        this.alquiler = (int) (solar.getValor() * 0.1 * 35);
                        break;
                    case 3:
                        this.nombre = "casa-4-" + solar.getNombre();
                        this.alquiler = (int) (solar.getValor() * 0.1 * 50);
                        break;
                }
                consola.imprimir("Se ha construido una casa en " + solar.getNombre() +
                        ". La fortuna de " + solar.getPropietario().getNombre() + " se reduce en " + valor + "€.");
                break;
            case Valor.EDIFICIO_HOTEL:
                this.valor = (int) (solar.getValor() * 0.6);
                this.alquiler = (int) (solar.getValor() * 0.1 * 70);
                switch (solar.getNumHoteles()) {
                    case 0:
                        this.nombre = "hotel-1-" + solar.getNombre();
                        break;
                    case 1:
                        this.nombre = "hotel-2-" + solar.getNombre();
                        break;
                    case 2:
                        this.nombre = "hotel-3-" + solar.getNombre();
                        break;
                }
                consola.imprimir("Se ha construido un hotel en " + solar.getNombre() +
                        ". La fortuna de " + solar.getPropietario().getNombre() + " se reduce en " + valor + "€.");
                break;
            case Valor.EDIFICIO_PISCINA:
                this.valor = (int) (solar.getValor() * 0.4);
                this.alquiler = (int) (solar.getValor() * 0.1 * 25);
                switch (solar.getNumPiscinas()) {
                    case 0:
                        this.nombre = "piscina-1-" + solar.getNombre();
                        break;
                    case 1:
                        this.nombre = "piscina-2-" + solar.getNombre();
                        break;
                    case 2:
                        this.nombre = "piscina-3-" + solar.getNombre();
                        break;
                }
                consola.imprimir("Se ha construido una piscina en " + solar.getNombre() +
                        ". La fortuna de " + solar.getPropietario().getNombre() + " se reduce en " + valor + "€.");
                break;
            case Valor.EDIFICIO_PISTA:
                this.valor = (int) (solar.getValor() * 1.25);
                this.alquiler = (int) (solar.getValor() * 0.1 * 25);
                switch (solar.getNumPistas()) {
                    case 0:
                        this.nombre = "pista-1-" + solar.getNombre();
                        break;
                    case 1:
                        this.nombre = "pista-2-" + solar.getNombre();
                        break;
                    case 2:
                        this.nombre = "pista-3-" + solar.getNombre();
                        break;
                }
                consola.imprimir("Se ha construido una pista en " + solar.getNombre() +
                        ". La fortuna de " + solar.getPropietario().getNombre() + " se reduce en " + valor + "€.");
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(int alquiler) {
        this.alquiler = alquiler;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        if (grupo == null) {
            consola.imprimir("Grupo nulo.");
            System.exit(1);
        }
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        String cadena = "{\n " +
                            "\n\t id: " + this.nombre +
                            "\n\t propietario: " + this.casilla.getPropietario().getNombre() +
                            "\n\t casilla: " + this.casilla.getNombre() +
                            "\n\t grupo: " + this.grupo.getColor() +
                            ",\n\t coste: " + this.valor +
                            ",\n\t alquiler: " + this.alquiler +
                            "\n}";
        return cadena;
    }

}
