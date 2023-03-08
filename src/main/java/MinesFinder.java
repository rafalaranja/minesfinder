import javax.swing.*;
import java.awt.event.ActionEvent;

public class MinesFinder extends JFrame{

    private JPanel painelPrincipal;
    private JButton btnJogoFacil;
    private JButton btnJogoMedio;
    private JButton btnJogoDificl;
    private JButton btnSair;

    public MinesFinder(String title) {
        super(title);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(painelPrincipal);
        pack();

        btnSair.addActionListener(this::btnSairActionPerformed);
        btnJogoFacil.addActionListener(this::btnJogoFacilActionPerformed);
        btnJogoMedio.addActionListener(this::btnJogoMedioActionPerformed);
        btnJogoDificl.addActionListener(this::btnJogoDificilActionPerformed);
    }

    private void btnSairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void btnJogoFacilActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(9,9,10));
    }

    private void btnJogoMedioActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(16,16,40));
    }

    private void btnJogoDificilActionPerformed(ActionEvent e) {
        var janela = new JanelaDeJogo(new CampoMinado(16,30,90));
    }


    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);

    }
}

