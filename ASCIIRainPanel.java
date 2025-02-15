package aplinyjavaapp;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

/**
 * ASCIIRainPanel
 *
 * Displays a continuous "ASCII rain" of random characters,
 * in red to match the Pliny tribute theme.
 */
public class ASCIIRainPanel extends JPanel {

    private final int numColumns = 100;  // number of columns
    private final int numRows = 40;      // number of rows
    private final char[][] rain;
    private final Random random;
    private final Timer timer;

    public ASCIIRainPanel() {
        setBackground(Color.BLACK);
        setForeground(Color.RED);
        random = new Random();
        rain = new char[numRows][numColumns];

        // initialize random chars
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numColumns; c++) {
                rain[r][c] = randomChar();
            }
        }

        // Timer updates the rain
        timer = new Timer(100, e -> {
            // shift rows downward
            for (int r = numRows - 1; r > 0; r--) {
                for (int c = 0; c < numColumns; c++) {
                    rain[r][c] = rain[r - 1][c];
                }
            }
            // create a new row of random chars at the top
            for (int c = 0; c < numColumns; c++) {
                rain[0][c] = randomChar();
            }
            repaint();
        });
    }

    private char randomChar() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-=_+[]{}<>?";
        return chars.charAt(random.nextInt(chars.length()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        g.setColor(getForeground());

        int charWidth = g.getFontMetrics().charWidth('W');
        int charHeight = g.getFontMetrics().getHeight();

        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numColumns; c++) {
                g.drawString(String.valueOf(rain[r][c]), c * charWidth, (r + 1) * charHeight);
            }
        }
    }

    public void startRain() {
        timer.start();
    }

    public void stopRain() {
        timer.stop();
    }
}