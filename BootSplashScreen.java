package aplinyjavaapp;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

/**
 * BootSplashScreen
 *
 * Displays a retro-style “boot sequence” splash with ASCII.
 * After a few seconds, it launches the main Pliny app.
 */
public class BootSplashScreen extends JFrame {

    public BootSplashScreen() {
        super("Booting A Pliny Java System...");
        setUndecorated(true);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BorderLayout());
        add(panel);

        JLabel label = new JLabel("<html><pre>" + PlinyAsciiArt.BOOT_SPLASH + "</pre></html>");
        label.setForeground(Color.RED);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        JLabel loading = new JLabel("Loading Plinian modules...");
        loading.setForeground(Color.RED);
        loading.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(loading, BorderLayout.SOUTH);

        setVisible(true);

        // Auto-close after 3 seconds and launch main
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dispose();
                PlinyMkdsApp.main(null);
            }
        }, 3000);
    }

    public static void main(String[] args) {
        new BootSplashScreen();
    }
}