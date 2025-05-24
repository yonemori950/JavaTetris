import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisPanel extends JPanel implements ActionListener {

    private Timer timer;
    private int blockX = 4;   // 横位置（0～9）
    private int blockY = 0;   // 縦位置（0～19）
    private final int BLOCK_SIZE = 30; // ブロックのサイズ

    public TetrisPanel() {
        setBackground(Color.BLACK);

        // タイマー設定：1000msごとにactionPerformedが呼ばれる
        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // ブロックを描画（赤）
        g.setColor(Color.RED);
        g.fillRect(blockX * BLOCK_SIZE, blockY * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // ブロックを1マス下へ移動（最大19行まで）
        if (blockY < 19) {
            blockY++;
            repaint(); // 再描画
        } else {
            timer.stop(); // 一番下まで来たら止める
        }
    }
}
