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


    //construtor
    public CampoMinado(int nrLinhas, int nrColunas, int nrMinas) {
        this.nrLinhas = nrLinhas;
        this.nrColunas = nrColunas;
        this.nrMinas = nrMinas;

        this.minas = new boolean[nrLinhas][nrColunas];      //valores começam a false
        this.estado = new int[nrLinhas][nrColunas];         //valores começam a 0
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
}

