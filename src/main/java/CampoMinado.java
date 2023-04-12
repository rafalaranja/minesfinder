import java.util.Random;

public class CampoMinado {

    private boolean[][] minas;
    public static final int VAZIO = 0;
    /* de 1 a 8 são os números de minas ao redor */
    public static final int TAPADO = 9;
    public static final int DUVIDA = 10;
    public static final int MARCADO = 11;
    public static final int REBENTADO = 12;

    private int[][] estado;

    private int nrLinhas;       //a.k.a largura
    private int nrColunas;      //a.k.a altura
    private int nrMinas;

    private boolean primeiraJogada;  //indica se é ou não a primeira jogada

    private boolean jogoTerminado;  //indica se o jogo terminou ou não
    private boolean jogadorDerrotado;   //indica se o jogador perdeu ou não

    private long instanteInicioJogo;    //guarda o instante em que o jogo começou
    private long duracaoJogo;           //guarda a duração do jogo

    //construtor
    public CampoMinado(int nrLinhas, int nrColunas, int nrMinas) {
        this.nrLinhas = nrLinhas;
        this.nrColunas = nrColunas;
        this.nrMinas = nrMinas;

        this.minas = new boolean[nrLinhas][nrColunas];      //valores começam a false
        this.estado = new int[nrLinhas][nrColunas];         //valores começam a 0

        primeiraJogada = true;      //inicia o construtor considerando que é a primeira jogada

        jogoTerminado = false;      //inicia o construtor considerando que o jogo não terminou
        jogadorDerrotado = false;   //inicia o construtor considerando que o jogador não perdeu


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
        if (jogoTerminado || estado[x][y] < TAPADO) {
            return;
        }
        if (primeiraJogada) {
            primeiraJogada = false;
            colocarMinas(x, y);

            instanteInicioJogo = System.currentTimeMillis();
        }
        if (minas[x][y]) {
            estado[x][y] = REBENTADO;
            jogoTerminado = true;
            jogadorDerrotado = true;
            duracaoJogo = System.currentTimeMillis() - instanteInicioJogo;
        }
        if (estado[x][y] == TAPADO && !minas[x][y]) {
            estado[x][y] = contarMinasVizinhas(x, y);
            if (estado[x][y] == VAZIO) {
                revelarQuadriculasVizinhas(x, y);
            }
        }
        if (isVitoria() == true) {
            jogoTerminado = true;
            jogadorDerrotado = false;
            duracaoJogo = System.currentTimeMillis() - instanteInicioJogo;
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

    public boolean isJogoTerminado() {
        return jogoTerminado;
    }

    public boolean isJogadorDerrotado() {
        return jogadorDerrotado;
    }

    private int contarMinasVizinhas(int x, int y) {
        var numMinasVizinhas = 0;

        for (var i = Math.max(0, x - 1); i < Math.min(nrLinhas, x + 2); ++i) {
            for (var j = Math.max(0, y - 1); j < Math.min(nrColunas, y + 2); ++j) {
                if (minas[i][j]) {
                    ++numMinasVizinhas;
                }
            }
        }
        return numMinasVizinhas;
    }

    private int revelarQuadriculasVizinhas(int x, int y) {
        for (var i = Math.max(0, x - 1); i < Math.min(nrLinhas, x + 2); ++i) {
            for (var j = Math.max(0, y - 1); j < Math.min(nrColunas, y + 2); ++j) {
                if (!minas[i][j] && estado[i][j] == TAPADO) {
                    revelarQuadricula(i, j);
                }
            }
        }
        return 0;
    }

    public boolean isVitoria() {
        for (int i = 0; i < nrLinhas; ++i) {
            for (int j = 0; j < nrColunas; ++j) {
                if (estado[i][j] >= TAPADO && !minas[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void marcarComoTendoMina(int x, int y) {
        if (estado[x][y] == TAPADO || estado[x][y] == DUVIDA) {
            estado[x][y] = MARCADO;
        }
    }

    public void marcarComoSuspeita(int x, int y) {
        if (estado[x][y] == TAPADO || estado[x][y] == MARCADO) {
            estado[x][y] = DUVIDA;
        }
    }

    public void desmarcarQuadricula(int x, int y) {
        if (estado[x][y] == MARCADO || estado[x][y] == DUVIDA) {
            estado[x][y] = TAPADO;
        }
    }

    public long getDuracaoJogo() {
        if (primeiraJogada){
            return 0;
        }

        if (!jogoTerminado){
            return System.currentTimeMillis() - instanteInicioJogo;
        }

        return duracaoJogo;
    }
}

