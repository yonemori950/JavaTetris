import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisPanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private int blockX = 4;   // 横位置（0～9）
    private int blockY = 0;   // 縦位置（0～19）
    private final int BLOCK_SIZE = 30;
    private final int FIELD_WIDTH = 10;
    private final int FIELD_HEIGHT = 20;

    public TetrisPanel() {
        setBackground(Color.BLACK);

        // タイマー設定：1000msごとに落ちる
        timer = new Timer(1000, this);
        timer.start();

        // キーイベント受け取りの準備
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 赤いブロックを描画
        g.setColor(Color.RED);
        g.fillRect(blockX * BLOCK_SIZE, blockY * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (blockY < FIELD_HEIGHT - 1) {
            blockY++;
            repaint();
        } else {
            timer.stop(); // 下まで行ったら止める
        }
    }

    // ← →キーの処理
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT && blockX > 0) {
            blockX--;
        } else if (code == KeyEvent.VK_RIGHT && blockX < FIELD_WIDTH - 1) {
            blockX++;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
