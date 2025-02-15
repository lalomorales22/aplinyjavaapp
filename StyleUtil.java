package aplinyjavaapp;

import java.awt.*;
import javax.swing.*;

public class StyleUtil {

    /**
     * Globally sets black backgrounds and red foregrounds for common UI items.
     */
    public static void initGlobalUI() {
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.RED);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.RED);
        UIManager.put("Label.foreground", Color.RED);
        UIManager.put("TextField.background", Color.BLACK);
        UIManager.put("TextField.foreground", Color.RED);
        UIManager.put("TextArea.background", Color.BLACK);
        UIManager.put("TextArea.foreground", Color.RED);
        UIManager.put("TextPane.background", Color.BLACK);
        UIManager.put("TextPane.foreground", Color.RED);
    }

    /**
     * Apply style to a JTextArea: black BG, red FG, monospaced font, line wrap.
     */
    public static void styleTextArea(JTextArea textArea) {
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.RED);
        textArea.setCaretColor(Color.RED);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
    }

    /**
     * Style a JScrollPane to keep black background as well.
     */
    public static void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBackground(Color.BLACK);
        scrollPane.setForeground(Color.RED);
    }

    /**
     * Style a JButton with black BG, red FG, etc.
     */
    public static void styleButton(JButton button) {
        button.setForeground(Color.RED);
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Monospaced", Font.BOLD, 16));
    }
}