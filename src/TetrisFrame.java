import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TetrisFrame extends JFrame {
    public TetrisFrame() {
        setTitle("Javaテトリス STEP5");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // スコア表示ラベル
        JLabel scoreLabel = new JLabel("スコア：0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(Color.DARK_GRAY);
        scoreLabel.setOpaque(true);

        TetrisPanel panel = new TetrisPanel(scoreLabel);

        setLayout(new BorderLayout());
        add(scoreLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        pack();
    }

    public static void main(String[] args) {
        new TetrisFrame().setVisible(true);
    }
}
