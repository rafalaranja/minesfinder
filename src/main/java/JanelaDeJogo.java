import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JanelaDeJogo extends JFrame {

    private JPanel painelJogo;

    private BotaoCampoMinado[][] botoes;
    private CampoMinado campoMinado;

    private TabelaRecordes recordes;


    public JanelaDeJogo(CampoMinado campoMinado, TabelaRecordes tabela) {
        this.campoMinado = campoMinado;
        this.recordes = tabela;

        var nrLinhas = campoMinado.getNrLinhas();
        var nrColunas = campoMinado.getNrColunas();

        this.botoes = new BotaoCampoMinado[nrLinhas][nrColunas];

        painelJogo.setLayout(new GridLayout(nrLinhas, nrColunas));

        //Criar os botoes e adicionar a janela
        for (int linha = 0; linha < nrLinhas; ++linha) {
            for (int coluna = 0; coluna < nrColunas; ++coluna) {
                botoes[linha][coluna] = new BotaoCampoMinado(linha, coluna);
                botoes[linha][coluna].addActionListener(this::btnCampoMinadoActionPerformed);
                botoes[linha][coluna].addMouseListener(mouseListener);
                botoes[linha][coluna].addKeyListener(keyListener);
                painelJogo.add(botoes[linha][coluna]);
            }
        }

        setContentPane(painelJogo);

        //destroi a janela removendo-a da memoria
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //coloca o tamanho da janela de acordo com o tamanho dos componentes
        pack();

        setVisible(true);
    }


    public void btnCampoMinadoActionPerformed(ActionEvent e){
        var botao = (BotaoCampoMinado) e.getSource();

        var x = botao.getLinha();
        var y = botao.getColuna();

        campoMinado.revelarQuadricula(x,y);
        actualizarEstadoBotoes();

        if (campoMinado.isJogoTerminado()){
            if(campoMinado.isJogadorDerrotado()){
                JOptionPane.showMessageDialog(null, "Oh, rebentou uma mina!","Perdeu!", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Parabéns. Conseguiu descobrir todas as minas em "
                        + (campoMinado.getDuracaoJogo()/1000) + " segundos!","Vitória!", JOptionPane.INFORMATION_MESSAGE);

                boolean novoRecorde=campoMinado.getDuracaoJogo()<recordes.getTempoJogo();

                if (novoRecorde){
                    String nome = JOptionPane.showInputDialog("Introduza o seu nome");
                    recordes.setRecorde(nome, campoMinado.getDuracaoJogo());
                }
            }
            setVisible(false);
        }
    }

    private void actualizarEstadoBotoes(){
        for (int x = 0; x < campoMinado.getNrLinhas(); x++) {
            for (int y = 0; y < campoMinado.getNrColunas(); y++) {
                botoes[x][y].setEstado(campoMinado.getEstadoQuadricula(x,y));
            }
        }
    }

    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() != MouseEvent.BUTTON3) {
                return;
            }

        var botao = (BotaoCampoMinado) e.getSource();

            var y = botao.getColuna();
            var x = botao.getLinha();

            var estadoQuadricula = campoMinado.getEstadoQuadricula(x,y);

           marcarQuadricula(x,y);

        }


        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    };

    KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            var botao = (BotaoCampoMinado) e.getSource();

            var x = botao.getLinha();  // ou var linha = botao.getLinha();
            var y = botao.getColuna(); // ou var coluna = botao.getColuna();

            switch (e.getKeyCode()){
                case KeyEvent.VK_UP -> botoes[--x < 0 ? campoMinado.getNrLinhas() - 1 : x][y].requestFocus();
                case KeyEvent.VK_DOWN -> botoes[(x +1) % campoMinado.getNrLinhas()][y].requestFocus();
                case KeyEvent.VK_LEFT -> botoes[x][--y < 0 ? campoMinado.getNrColunas() - 1 : y].requestFocus();
                case KeyEvent.VK_RIGHT -> botoes[x][(y +1) % campoMinado.getNrColunas()].requestFocus();
                case KeyEvent.VK_M -> marcarQuadricula(x,y);
            }

        }


        @Override
        public void keyReleased(java.awt.event.KeyEvent e) {
        }
    };

    private void marcarQuadricula(int x, int y){
        switch (campoMinado.getEstadoQuadricula(x,y)){
            case CampoMinado.TAPADO -> campoMinado.marcarComoTendoMina(x,y);
            case CampoMinado.MARCADO -> campoMinado.marcarComoSuspeita(x,y);
            case CampoMinado.DUVIDA -> campoMinado.desmarcarQuadricula(x,y);
        }
        actualizarEstadoBotoes();
    }

}
