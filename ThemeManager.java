package aplinyjavaapp;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class ThemeManager {

    public enum Theme {
        RED_BLACK,
        NEON_VOID,
        TOXIC_GREEN,
        WIN95_GRAY
    }

    public static void applyTheme(Theme theme, JFrame frame) {
        // Re-set to MetalLookAndFeel each time so color changes apply
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (theme) {
            case RED_BLACK:
                setColors(Color.BLACK, Color.RED);
                break;
            case NEON_VOID:
                setColors(new Color(10, 0, 30), new Color(150, 0, 255));
                break;
            case TOXIC_GREEN:
                setColors(Color.BLACK, new Color(0, 255, 0));
                break;
            case WIN95_GRAY:
                setColors(new Color(192,192,192), Color.BLACK);
                break;
        }

        // Force an update so all components repaint with new UI defaults
        SwingUtilities.updateComponentTreeUI(frame);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    private static void setColors(Color bg, Color fg) {
        UIManager.put("Panel.background", bg);
        UIManager.put("Panel.foreground", fg);
        UIManager.put("Button.background", bg);
        UIManager.put("Button.foreground", fg);
        UIManager.put("Label.foreground", fg);
        UIManager.put("TextField.background", bg);
        UIManager.put("TextField.foreground", fg);
        UIManager.put("TextArea.background", bg);
        UIManager.put("TextArea.foreground", fg);
        UIManager.put("OptionPane.background", bg);
        UIManager.put("OptionPane.messageForeground", fg);
        UIManager.put("ScrollPane.background", bg);
        UIManager.put("ScrollPane.foreground", fg);
    }
}