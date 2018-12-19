package monopoly.mapa;

import java.util.Random;

public class Dado {

    private int dado1;
    private int dado2;

    //constructores
    public Dado() {
        dado1 = 0;
        dado2 = 0;
    }

    public Dado(int dado1, int dado2) {
        if (dado1 < 0 || dado1 > 6 || dado2 < 0 || dado2 > 6) {
            System.out.println(Valor.ANSI_ROJO + "Dado no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.dado1 = dado1;
        this.dado2 = dado2;
    }

    //getters y setters
    public int getDado1() {
        return dado1;
    }

    public void setDado1(int dado1) {
        if (dado1 < 0 || dado1 > 6) {
            System.out.println(Valor.ANSI_ROJO + "Dado no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.dado1 = dado1;
    }

    public int getDado2() {
        return dado2;
    }

    public void setDado2(int dado2) {
        if (dado2 < 0 || dado2 > 6) {
            System.out.println(Valor.ANSI_ROJO + "Dado no valido." + Valor.ANSI_RESET);
            System.exit(1);
        }
        this.dado2 = dado2;
    }

    //metodos
    /**
     * Modifica el valor de los dados de manera aleatoria
     *
     * @return Valor total de ambos dados
     */
    public int tirarDados() {
        Random random = new Random();
        dado1 = random.nextInt((6-1) + 1) + 1;
        dado2 = random.nextInt((6-1) + 1) + 1;
        return dado1 + dado2;
    }

    /**
     * Comprueba si dado1 == dado2
     *
     * @return True si dado1 == dado2, False si dado1 != dado2
     */
    public boolean dadosIguales() {
        return dado1 == dado2;
    }
}
