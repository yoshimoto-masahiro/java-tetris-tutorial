
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {

    private final int BOARD_WIDTH = 10;
    private final int BOARD_HEIGHT = 20;
    private Timer timer;
    private boolean isFallingFinished = false;
    private int curX = 0;
    private int curY = 0;
    private Shape curPiece;
    private Shape.Tetrominoes[] board;

    public Board() {
        setFocusable(true);
        curPiece = new Shape();
        timer = new Timer(400, this);
        timer.start();
        board = new Shape.Tetrominoes[BOARD_WIDTH * BOARD_HEIGHT];
        addKeyListener(new TAdapter());
        clearBoard();
        newPiece();
    }

    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
        repaint();
    }

    private void clearBoard() {
        for (int i = 0; i < BOARD_WIDTH * BOARD_HEIGHT; ++i) {
            board[i] = Shape.Tetrominoes.NoShape;
        }
    }

    private void newPiece() {
        curPiece.setRandomShape();
        curX = BOARD_WIDTH / 2;
        curY = 0;
    }

    private void oneLineDown() {
        curY += 1;
    }

    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    curX -= 1;
                    break;
                case KeyEvent.VK_RIGHT:
                    curX += 1;
                    break;
                case KeyEvent.VK_DOWN:
                    oneLineDown();
                    break;
                case KeyEvent.VK_UP:
                    curPiece.rotateRight();
                    break;
            }
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPiece(g);
    }

    private void drawPiece(Graphics g) {
        if (curPiece.getShape() != Shape.Tetrominoes.NoShape) {
            for (int i = 0; i < 4; i++) {
                int x = curX + curPiece.x(i);
                int y = curY + curPiece.y(i);
                drawSquare(g, x * 20, y * 20, curPiece.getShape());
            }
        }
    }

    private void drawSquare(Graphics g, int x, int y, Shape.Tetrominoes shape) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 20, 20);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 20, 20);
    }
}
