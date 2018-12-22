package monopoly.interfaces;

public class ConsolaNormal implements Consola{
    @Override
    public void imprimir(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public String leer(String descripcion) {
        return null;
    }
}
