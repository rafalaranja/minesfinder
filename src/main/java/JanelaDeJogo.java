import javax.swing.*;
import java.awt.*;

public class JanelaDeJogo extends JFrame {

    private JPanel painelJogo;

    private BotaoCampoMinado[][] botoes;
    private CampoMinado campoMinado;

    public JanelaDeJogo(CampoMinado campoMinado) {
        this.campoMinado = campoMinado;

        var nrLinhas = campoMinado.getNrLinhas();
        var nrColunas = campoMinado.getNrColunas();

        this.botoes = new BotaoCampoMinado[nrLinhas][nrColunas];

        painelJogo.setLayout(new GridLayout(nrLinhas, nrColunas));

        //Criar os botoes e adicionar a janela
        for (int linha = 0; linha < nrLinhas; linha++) {
            for (int coluna = 0; coluna < nrColunas; coluna++) {
                var botao = new BotaoCampoMinado();
                botao.setEstado(campoMinado.getEstadoQuadricula(linha, coluna));
                botoes[linha][coluna] = botao;
                painelJogo.add(botao);
            }
        }

        setContentPane(painelJogo);

        //destroi a janela removendo-a da memoria
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //coloca o tamanho da janela de acordo com o tamanho dos componentes
        pack();

        setVisible(true);
    }

}
