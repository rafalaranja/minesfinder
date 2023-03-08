import javax.swing.*;

public class JanelaDeJogo extends JFrame {

    private JPanel painelJogo;

    public JanelaDeJogo(){
        setContentPane(painelJogo);

        //destroi a janela removendo-a da memoria
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(true);
    }

}
