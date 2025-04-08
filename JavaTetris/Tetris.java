
import javax.swing.JFrame;

public class Tetris extends JFrame {

    public Tetris() {
        initUI();
    }

    private void initUI() {
        add(new Board());
        setTitle("Java Tetris");
        setSize(200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            Tetris game = new Tetris();
            game.setVisible(true);
        });
    }
}
