import javax.swing.JFrame;

public class TetrisFrame extends JFrame {
    public TetrisFrame() {
        setTitle("Javaテトリス STEP1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        TetrisPanel panel = new TetrisPanel();
        add(panel);
        pack(); // ← これでピッタリサイズに
    }

    public static void main(String[] args) {
        new TetrisFrame().setVisible(true);
    }
}
