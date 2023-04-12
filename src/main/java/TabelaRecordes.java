public class TabelaRecordes {
    private String nomeJogador;
    private long tempoJogo;      //em milissegundos

    public TabelaRecordes() {
        this.nomeJogador = "nomeJogador";
        this.tempoJogo = 9999999;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public long getTempoJogo() {
        return tempoJogo;
    }
    
    public void setRecorde(String nomeJogador, long tempoJogo){

        if (tempoJogo < this.tempoJogo){
            this.nomeJogador = nomeJogador;
            this.tempoJogo = tempoJogo;
        }
    }
}
