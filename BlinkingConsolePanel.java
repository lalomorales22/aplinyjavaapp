package aplinyjavaapp;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 * BlinkingConsolePanel
 *
 * A small console panel with a blinking cursor that prints
 * random Pliny/AI-themed messages to simulate activity.
 */
public class BlinkingConsolePanel extends JPanel {
    private String consoleText = "PlinyAI> ";
    private boolean showCursor = true;
    private final Timer timer;
    private final Random random;

    private static final String[] MESSAGES = {
        "Initiating AI subroutine...",
        "Collecting Plinian manuscripts...",
        "Analyzing textual data for insights...",
        "Expanding knowledge base...",
        "Compiling ancient references...",
        "Translating manuscripts to modern code..."
    };

    public BlinkingConsolePanel() {
        setBackground(Color.BLACK);
        setForeground(Color.RED);
        setPreferredSize(new Dimension(800, 100));
        random = new Random();

        // Blink the cursor every 500 ms
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                showCursor = !showCursor;
                repaint();
            }
        }, 500, 500);

        // Add random message every 4 seconds
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                int idx = random.nextInt(MESSAGES.length);
                consoleText += "\n" + MESSAGES[idx] + "\nPlinyAI> ";
                repaint();
            }
        }, 4000, 4000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Monospaced", Font.PLAIN, 14));
        g.setColor(getForeground());

        String displayText = consoleText;
        if (showCursor) {
            displayText += "_";
        }

        int lineHeight = g.getFontMetrics().getHeight();
        int y = lineHeight;
        for (String line : displayText.split("\n")) {
            g.drawString(line, 5, y);
            y += lineHeight;
        }
    }
}