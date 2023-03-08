import java.util.Random;

public class CampoMinado {

    private boolean[][] minas;
    public static final int VAZIO = 0;
    /* de 1 a 8 são os números de minas ao redor */
    public static final int TAPADO  = 9;
    public static final int DUVIDA  = 10;
    public static final int MARCADO = 11;
    public static final int REBENTADO = 12;

    private int[][] estado;

    private int nrLinhas;       //a.k.a largura
    private int nrColunas;      //a.k.a altura
    private int nrMinas;

    private boolean primeiraJogada;  //indica se é ou não a primeira jogada

    //construtor
    public CampoMinado(int nrLinhas, int nrColunas, int nrMinas) {
        this.nrLinhas = nrLinhas;
        this.nrColunas = nrColunas;
        this.nrMinas = nrMinas;

        this.minas = new boolean[nrLinhas][nrColunas];      //valores começam a false
        this.estado = new int[nrLinhas][nrColunas];         //valores começam a 0

        primeiraJogada = true;      //inicia o construtor considerando que é a primeira jogada

        for (var x = 0; x < nrLinhas; ++x) {
            for (var y = 0; y < nrColunas; ++y) {
                estado[x][y] = TAPADO;
            }
        }
    }

    //determinar o estado de uma quadricula
    public int getEstadoQuadricula(int x, int y) {
        return estado[x][y];
    }

    //saber se uma quadricula tem uma mina
    public boolean hasMinas(int x, int y) {
        return minas[x][y];
    }

    //obter o numero de linhas
    public int getNrLinhas() {
        return nrLinhas;
    }

    //obter o numero de colunas
    public int getNrColunas() {
        return nrColunas;
    }

    //revelar uma quadricula
    public void revelarQuadricula(int x, int y) {
        if (estado[x][y] < TAPADO) {
            return;
        }
        if (primeiraJogada) {
            primeiraJogada = false;
            colocarMinas(x, y);
        }

    }

    private void colocarMinas(int exceptoX, int exceptoY) {
        var aleatorio = new Random();
        var x = 0;
        var y = 0;

        for (var i = 0; i < nrMinas; ++i) {
            do {
                x = aleatorio.nextInt(nrLinhas);
                y = aleatorio.nextInt(nrColunas);
            } while (minas[x][y] || (x == exceptoX && y == exceptoY));
            minas[x][y] = true;
        }
    }
}

