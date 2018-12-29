package monopoly.mapa;

import static monopoly.mapa.Juego.consola;

public class Edificio {
    private String nombre;
    private Casilla casilla;
    private Grupo grupo;
    private int valor;
    private int alquiler;

    public Edificio(Solar solar) {
        if (solar == null) {
            consola.imprimir("Casilla nula.");
            System.exit(1);
        }
        this.casilla = solar;
        this.grupo = solar.getGrupo();
    }

    public Edificio() {

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
