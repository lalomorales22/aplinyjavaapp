package aplinyjavaapp;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

/**
 * ASCIIRainPanel
 *
 * Displays a continuous "ASCII rain" of random characters,
 * in red to match the Pliny tribute theme.
 * Now forces zero insets and uses ceil for row/col calculations.
 */
public class ASCIIRainPanel extends JPanel {

    private char[][] rain;
    private int rows;
    private int cols;
    private final Random random;
    private final Timer timer;

    public ASCIIRainPanel() {
        // Force black background and red foreground
        setBackground(Color.BLACK);
        setForeground(Color.RED);

        // Force a known monospaced font so we can reliably measure width/height
        setFont(new Font("Monospaced", Font.PLAIN, 12));

        random = new Random();

        // Initialize with 0x0; actual size gets set in resizeCheck()
        rain = new char[0][0];

        // Timer updates the rain every 100ms
        timer = new Timer(100, e -> {
            // Recalculate rows/cols in case of resize
            resizeCheck();

            // Shift rows downward
            for (int r = rows - 1; r > 0; r--) {
                System.arraycopy(rain[r - 1], 0, rain[r], 0, cols);
            }
            // New row of random chars at the top
            for (int c = 0; c < cols; c++) {
                rain[0][c] = randomChar();
            }
            repaint();
        });
    }

    /**
     * Zero insets so the ASCII text starts at (0,0).
     */
    @Override
    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }

    /**
     * Ensures our char[][] matches the available space.
     */
    private void resizeCheck() {
        FontMetrics fm = getFontMetrics(getFont());
        int charWidth = fm.charWidth('W');
        int charHeight = fm.getHeight();
        if (charWidth <= 0 || charHeight <= 0) {
            return;
        }

        // Use ceiling so leftover pixels get included as an extra column or row
        int newCols = (int) Math.ceil((double) getWidth() / charWidth);
        int newRows = (int) Math.ceil((double) getHeight() / charHeight);

        newCols = Math.max(1, newCols);
        newRows = Math.max(1, newRows);

        if (newRows != rows || newCols != cols) {
            rows = newRows;
            cols = newCols;
            rain = new char[rows][cols];
            // Fill fresh with random chars
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    rain[r][c] = randomChar();
                }
            }
        }
    }

    private char randomChar() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-=_+[]{}<>?";
        return chars.charAt(random.nextInt(chars.length()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Double-check size on each paint
        resizeCheck();

        // Use the font we've already set
        g.setFont(getFont());
        g.setColor(getForeground());

        FontMetrics fm = g.getFontMetrics();
        int charWidth = fm.charWidth('W');
        int charHeight = fm.getHeight();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                g.drawString(String.valueOf(rain[r][c]),
                             c * charWidth,
                             (r + 1) * charHeight);
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
