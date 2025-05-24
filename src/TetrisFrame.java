import javax.swing.JFrame;

public class TetrisFrame extends JFrame {
    public TetrisFrame() {
        setTitle("Javaテトリス STEP1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        add(new TetrisPanel()); // パネル追加
    }

    public static void main(String[] args) {
        new TetrisFrame().setVisible(true);
    }
}
