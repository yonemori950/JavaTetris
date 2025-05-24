import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisPanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private int blockX = 4;
    private int blockY = 0;
    private final int BLOCK_SIZE = 30;
    private final int FIELD_WIDTH = 10;
    private final int FIELD_HEIGHT = 20;

    // フィールド情報（0:空, 1:ブロック）
    private int[][] field = new int[FIELD_HEIGHT][FIELD_WIDTH];
    
    private int score = 0;
    private JLabel scoreLabel;


    public TetrisPanel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
        setPreferredSize(new Dimension(FIELD_WIDTH * BLOCK_SIZE, FIELD_HEIGHT * BLOCK_SIZE));
        setBackground(Color.BLACK);
        timer = new Timer(500, this);
        timer.start();
        setFocusable(true);
        addKeyListener(this);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // フィールドの描画
        for (int y = 0; y < FIELD_HEIGHT; y++) {
            for (int x = 0; x < FIELD_WIDTH; x++) {
                if (field[y][x] == 1) {
                    g.setColor(Color.GRAY);
                    g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }

        // 落下中ブロックの描画
        g.setColor(Color.RED);
        g.fillRect(blockX * BLOCK_SIZE, blockY * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 下が空なら落下、埋まってる or 一番下なら固定
        if (canMove(blockX, blockY + 1)) {
            blockY++;
        } else {
            field[blockY][blockX] = 1; // 固定
            spawnNewBlock();           // 新ブロック
        }
        repaint();
    }

    private boolean canMove(int x, int y) {
        return y < FIELD_HEIGHT && field[y][x] == 0;
    }

    private void spawnNewBlock() {
        clearLines(); // ← 追加！

        blockX = 4;
        blockY = 0;
        if (field[blockY][blockX] == 1) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "ゲームオーバー！");
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT && blockX > 0 && field[blockY][blockX - 1] == 0) {
            blockX--;
        } else if (code == KeyEvent.VK_RIGHT && blockX < FIELD_WIDTH - 1 && field[blockY][blockX + 1] == 0) {
            blockX++;
        }
        repaint();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    
   
    private void clearLines() {
        int linesCleared = 0;

        for (int y = FIELD_HEIGHT - 1; y >= 0; y--) {
            boolean full = true;
            for (int x = 0; x < FIELD_WIDTH; x++) {
                if (field[y][x] == 0) {
                    full = false;
                    break;
                }
            }

            if (full) {
                for (int moveY = y; moveY > 0; moveY--) {
                    field[moveY] = field[moveY - 1].clone();
                }
                field[0] = new int[FIELD_WIDTH];
                linesCleared++;
                y++; // 再チェック
            }
        }

        // スコア加算
        if (linesCleared > 0) {
            score += linesCleared * 100;
            updateScore();
        }
    }


    private void updateScore() {
        scoreLabel.setText("スコア：" + score);
    }
}
