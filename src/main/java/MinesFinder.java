import javax.swing.*;

public class MinesFinder extends JFrame{

    private JPanel painelPrincipal;

        public MinesFinder(String title) {
            super(title);

            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setContentPane(painelPrincipal);
            pack();
        }

        public static void main(String[] args) {
            new MinesFinder("Mines Finder").setVisible(true);

        }
    }

