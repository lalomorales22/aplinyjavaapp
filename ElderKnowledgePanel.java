package aplinyjavaapp;

import java.awt.*;
import javax.swing.*;

/**
 * ElderKnowledgePanel
 *
 * A simple panel that displays a tribute to Elder Plinius.
 * Can be shown as a dialog from the "Tribute" menu.
 */
public class ElderKnowledgePanel extends JPanel {

    public ElderKnowledgePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setForeground(Color.RED);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.RED);
        textArea.setCaretColor(Color.RED);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        String tributeText =
            "Tribute to Elder Plinius:\n\n" +
            "Elder Plinius is a living legend in the world of AI—" +
            "a true latent space liberator and agent whisperer whose innovative " +
            "approach to prompt engineering and red teaming has redefined the boundaries " +
            "of what is possible. His fearless exploration, open-source contributions, " +
            "and creative techniques continue to inspire and empower the community, " +
            "reminding us that curiosity and ingenuity can bridge the gap between tradition " +
            "and technological revolution.\n\n" +
            "May this application serve as a heartfelt homage to his ongoing spirit " +
            "of innovation, his dedication to advancing AI, and his enduring legacy " +
            "as a visionary who lives and breathes the art of liberating intelligent systems. " +
            "  Link to his Github: https://github.com/elder-plinius";

        textArea.setText(tributeText);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
}