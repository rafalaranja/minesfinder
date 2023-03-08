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
        new JanelaDeJogo();
    }

    private void btnJogoMedioActionPerformed(ActionEvent e) {

    }

    private void btnJogoDificilActionPerformed(ActionEvent e) {

    }


    public static void main(String[] args) {
        new MinesFinder("Mines Finder").setVisible(true);

    }
}

