package aplinyjavaapp;

import java.awt.*;
import javax.swing.*;

/**
 * GlitchDialog
 *
 * A static utility to show glitchy popups instead of normal JOptionPanes.
 * Draws the text with a small "glitch" offset animation.
 */
public class GlitchDialog {

    /**
     * Show a glitchy message in a custom JDialog.
     */
    public static void showMessage(Component parent, String title, String message) {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), title, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setUndecorated(true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(parent);

        JPanel panel = new JPanel() {
            float glitchPhase = 0;
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.RED);
                g.drawRect(0, 0, getWidth()-1, getHeight()-1);

                // "Glitch" effect by shifting text slightly
                glitchPhase += 0.5f;
                int offsetX = (int)(Math.sin(glitchPhase)*5);
                int offsetY = (int)(Math.cos(glitchPhase)*3);

                g.setFont(new Font("Monospaced", Font.BOLD, 16));
                g.setColor(Color.RED);
                g.drawString(message, 10 + offsetX, getHeight()/2 + offsetY);
            }
        };
        panel.setBackground(Color.BLACK);

        dialog.add(panel);
        dialog.setVisible(true);

        // auto-close after a short time
        new Timer(2000, e -> dialog.dispose()).start();
    }
}