package monopoly.mapa;


// Esta clase todavia no se utiliza
public class Edificio {
    private String nombre;
    private String tipo;
    private Casilla casilla;
    private Grupo grupo;
    private int valor;
    private int alquiler;

    public Edificio(String tipo, Casilla casilla) {
        if (tipo == null) {
            System.out.println("Tipo nulo.");
            System.exit(1);
        }
        if (casilla == null) {
            System.out.println("Casilla nula.");
            System.exit(1);
        }
        this.tipo = tipo;
        this.casilla = casilla;
        this.grupo = casilla.getGrupo();
        switch (tipo) {
            case Valor.EDIFICIO_CASA:
                this.valor = (int) (casilla.getValor() * 0.6);
                switch (casilla.getNumCasas()) {
                    case 0:
                        this.nombre = "casa-1-" + casilla.getNombre();
                        this.alquiler = (int) (casilla.getValor() * 0.1 * 5);
                        break;
                    case 1:
                        this.nombre = "casa-2-" + casilla.getNombre();
                        this.alquiler = (int) (casilla.getValor() * 0.1 * 15);
                        break;
                    case 2:
                        this.nombre = "casa-3-" + casilla.getNombre();
                        this.alquiler = (int) (casilla.getValor() * 0.1 * 35);
                        break;
                    case 3:
                        this.nombre = "casa-4-" + casilla.getNombre();
                        this.alquiler = (int) (casilla.getValor() * 0.1 * 50);
                        break;
                }
                System.out.println("Se ha construido una casa en " + casilla.getNombre() +
                        ". La fortuna de " + casilla.getPropietario().getNombre() + " se reduce en " + valor + "€.");
                break;
            case Valor.EDIFICIO_HOTEL:
                this.valor = (int) (casilla.getValor() * 0.6);
                this.alquiler = (int) (casilla.getValor() * 0.1 * 70);
                switch (casilla.getNumHoteles()) {
                    case 0:
                        this.nombre = "hotel-1-" + casilla.getNombre();
                        break;
                    case 1:
                        this.nombre = "hotel-2-" + casilla.getNombre();
                        break;
                    case 2:
                        this.nombre = "hotel-3-" + casilla.getNombre();
                        break;
                }
                System.out.println("Se ha construido un hotel en " + casilla.getNombre() +
                        ". La fortuna de " + casilla.getPropietario().getNombre() + " se reduce en " + valor + "€.");
                break;
            case Valor.EDIFICIO_PISCINA:
                this.valor = (int) (casilla.getValor() * 0.4);
                this.alquiler = (int) (casilla.getValor() * 0.1 * 25);
                switch (casilla.getNumPiscinas()) {
                    case 0:
                        this.nombre = "piscina-1-" + casilla.getNombre();
                        break;
                    case 1:
                        this.nombre = "piscina-2-" + casilla.getNombre();
                        break;
                    case 2:
                        this.nombre = "piscina-3-" + casilla.getNombre();
                        break;
                }
                System.out.println("Se ha construido una piscina en " + casilla.getNombre() +
                        ". La fortuna de " + casilla.getPropietario().getNombre() + " se reduce en " + valor + "€.");
                break;
            case Valor.EDIFICIO_PISTA:
                this.valor = (int) (casilla.getValor() * 1.25);
                this.alquiler = (int) (casilla.getValor() * 0.1 * 25);
                switch (casilla.getNumPistas()) {
                    case 0:
                        this.nombre = "pista-1-" + casilla.getNombre();
                        break;
                    case 1:
                        this.nombre = "pista-2-" + casilla.getNombre();
                        break;
                    case 2:
                        this.nombre = "pista-3-" + casilla.getNombre();
                        break;
                }
                System.out.println("Se ha construido una pista en " + casilla.getNombre() +
                        ". La fortuna de " + casilla.getPropietario().getNombre() + " se reduce en " + valor + "€.");
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
            System.out.println("Grupo nulo.");
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
