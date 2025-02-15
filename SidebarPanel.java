package aplinyjavaapp;

import java.awt.*;
import javax.swing.*;

/**
 * SidebarPanel
 *
 * A container for the right sidebar. Has a dropdown to switch
 * between "Matrix" (ASCII Rain) and the Ollama-based AI Chatbot.
 * Removed extra borders/insets so the ASCII rain can fill the panel.
 */
public class SidebarPanel extends JPanel {

    private final ASCIIRainPanel rainPanel;
    private final OllamaChatPanel chatPanel;
    private final CardLayout cardLayout;
    private final JComboBox<String> modeCombo;

    public SidebarPanel() {
        // Make sure there's no extra border or inset
        setLayout(new BorderLayout());
        setBorder(null);
        setBackground(Color.BLACK);

        // Top panel with a dropdown for "Matrix" or "AI Chatbot"
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        topPanel.setBorder(null);
        topPanel.setBackground(Color.BLACK);

        modeCombo = new JComboBox<>(new String[]{"Matrix", "AI Chatbot"});
        modeCombo.setFont(new Font("Monospaced", Font.BOLD, 14));
        modeCombo.setBackground(Color.BLACK);
        modeCombo.setForeground(Color.RED);

        topPanel.add(modeCombo);
        add(topPanel, BorderLayout.NORTH);

        // CardLayout to switch between the two panels
        cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        cardPanel.setBorder(null);
        cardPanel.setBackground(Color.BLACK);

        // Initialize the two panels
        rainPanel = new ASCIIRainPanel();
        chatPanel = new OllamaChatPanel();

        cardPanel.add(rainPanel, "Matrix");
        cardPanel.add(chatPanel, "AI Chatbot");

        add(cardPanel, BorderLayout.CENTER);

        // Listen for dropdown changes
        modeCombo.addActionListener(e -> {
            String selected = (String) modeCombo.getSelectedItem();
            cardLayout.show(cardPanel, selected);
            if ("Matrix".equals(selected)) {
                rainPanel.startRain();
            } else {
                rainPanel.stopRain();
            }
        });

        // Default to Matrix mode and start the ASCII rain
        cardLayout.show(cardPanel, "Matrix");
        rainPanel.startRain();
    }

    /**
     * Allows other classes to request stopping the ASCII rain (e.g., if the sidebar is hidden).
     */
    public void stopAll() {
        rainPanel.stopRain();
    }
}
