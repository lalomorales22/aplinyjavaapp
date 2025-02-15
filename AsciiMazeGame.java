package aplinyjavaapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/**
 * AsciiMazeGame
 *
 * A revamped ASCII-based maze game with a Pliny-inspired upgrade.
 */
public class AsciiMazeGame extends JPanel {
    private char[][] maze;
    private int playerRow, playerCol;
    private int enemyRow, enemyCol;
    private int score = 0;
    private int tokensLeft = 0;
    private boolean gameOver = false;
    private boolean gameWon = false;
    private final Timer enemyTimer;
    private Random random = new Random();

    private final int ROWS = 8 * 5;  // 40
    private final int COLS = 10 * 5; // 50

    public AsciiMazeGame() {
        setBackground(Color.BLACK);
        setForeground(Color.RED);
        initMaze();
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameOver || gameWon) {
                    if (e.getKeyCode() == KeyEvent.VK_R) {
                        restartGame();
                    }
                    return;
                }
                movePlayer(e.getKeyCode());
                repaint();
            }
        });

        enemyTimer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!gameOver && !gameWon) {
                    moveEnemy();
                    repaint();
                }
            }
        });
        enemyTimer.start();
    }

    private void initMaze() {
        maze = new char[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (i == 0 || i == ROWS - 1 || j == 0 || j == COLS - 1)
                    maze[i][j] = '#';
                else
                    maze[i][j] = ' ';
            }
        }

        // Random internal walls
        for (int i = 2; i < ROWS - 2; i++) {
            for (int j = 2; j < COLS - 2; j++) {
                if (random.nextDouble() < 0.1) {
                    maze[i][j] = '#';
                }
            }
        }

        // Random tokens
        tokensLeft = 0;
        for (int i = 1; i < ROWS - 1; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                if (maze[i][j] == ' ' && !(i == 1 && j == 1)) {
                    if (random.nextDouble() < 0.2) {
                        maze[i][j] = '*';
                        tokensLeft++;
                    }
                }
            }
        }

        // Player start
        playerRow = 1;
        playerCol = 1;
        maze[playerRow][playerCol] = '@';

        // Enemy random
        while (true) {
            int r = random.nextInt(ROWS - 2) + 1;
            int c = random.nextInt(COLS - 2) + 1;
            if ((r != playerRow || c != playerCol) && maze[r][c] != '#') {
                enemyRow = r;
                enemyCol = c;
                break;
            }
        }
    }

    private void movePlayer(int keyCode) {
        int newRow = playerRow;
        int newCol = playerCol;

        switch (keyCode) {
            case KeyEvent.VK_UP:    newRow--; break;
            case KeyEvent.VK_DOWN:  newRow++; break;
            case KeyEvent.VK_LEFT:  newCol--; break;
            case KeyEvent.VK_RIGHT: newCol++; break;
            default: return;
        }

        if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS) {
            char target = maze[newRow][newCol];
            if (target == ' ' || target == '*') {
                if (target == '*') {
                    score++;
                    tokensLeft--;
                    if (tokensLeft == 0) {
                        gameWon = true;
                    }
                }
                maze[playerRow][playerCol] = ' ';
                playerRow = newRow;
                playerCol = newCol;
                maze[playerRow][playerCol] = '@';
            }
        }

        if (playerRow == enemyRow && playerCol == enemyCol) {
            gameOver = true;
        }
    }

    private void moveEnemy() {
        int[] dRows = {-1, 1, 0, 0};
        int[] dCols = {0, 0, -1, 1};
        int dir = random.nextInt(4);
        int newRow = enemyRow + dRows[dir];
        int newCol = enemyCol + dCols[dir];

        if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS) {
            if (maze[newRow][newCol] != '#') {
                enemyRow = newRow;
                enemyCol = newCol;
            }
        }

        if (enemyRow == playerRow && enemyCol == playerCol) {
            gameOver = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Monospaced", Font.PLAIN, 16));
        int lineHeight = g.getFontMetrics().getHeight();

        for (int r = 0; r < maze.length; r++) {
            String rowString = new String(maze[r]);
            g.drawString(rowString, 10, (r + 1) * lineHeight + 10);
        }

        g.setColor(Color.YELLOW);
        int charWidth = g.getFontMetrics().charWidth('A');
        g.drawString("E", 10 + enemyCol * charWidth, (enemyRow + 1) * lineHeight + 10);

        g.setColor(Color.RED);
        g.drawString("Score: " + score + "   Tokens left: " + tokensLeft, 10, 20);

        if (gameOver) {
            g.setFont(new Font("Monospaced", Font.BOLD, 24));
            g.setColor(Color.RED);
            g.drawString("GAME OVER - The maze remains unliberated!", 10, getHeight() / 2);
            g.setFont(new Font("Monospaced", Font.PLAIN, 16));
            g.drawString("Press 'R' to restart.", 10, getHeight() / 2 + 30);
        }

        if (gameWon) {
            g.setFont(new Font("Monospaced", Font.BOLD, 24));
            g.setColor(Color.RED);
            g.drawString("LIBERATION ACHIEVED!", 10, getHeight() / 2);
            g.setFont(new Font("Monospaced", Font.PLAIN, 16));
            g.drawString("All tokens collected. Praise Pliny!", 10, getHeight() / 2 + 30);
        }
    }

    private void restartGame() {
        score = 0;
        tokensLeft = 0;
        gameOver = false;
        gameWon = false;
        initMaze();
        repaint();
    }
}