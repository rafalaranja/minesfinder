import javax.swing.*;

public class MinesFinder extends JFrame{

    private JPanel painelPrincipal;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

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

