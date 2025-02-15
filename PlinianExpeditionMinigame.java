package aplinyjavaapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * PlinianExpeditionMinigame
 *
 * A choose-your-own-adventure style game inspired by Elder_plinius.
 * Extended storyline with multiple branches.
 */
public class PlinianExpeditionMinigame extends JPanel {

    private static final String EXPEDITION_ASCII = """
            ____  _     ___ _   ___   __
           |  _ \\| |   |_ _| \\ | \\ \\ / /
           | |_) | |    | ||  \\| |\\ V / 
           |  __/| |___ | || |\\  | | |  
           |_|   |_____|___|_| \\_| |_|  
            """;

    private final JLabel asciiLabel;
    private final JLabel narrativeLabel;
    private final JPanel buttonPanel;
    private final Random random = new Random();

    private int storyState = 0;

    public PlinianExpeditionMinigame() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setForeground(Color.RED);

        // Top ASCII label
        asciiLabel = new JLabel("<html><pre>" + EXPEDITION_ASCII + "</pre></html>", SwingConstants.CENTER);
        asciiLabel.setForeground(Color.RED);
        add(asciiLabel, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setLayout(new BorderLayout());

        narrativeLabel = new JLabel("", SwingConstants.CENTER);
        narrativeLabel.setForeground(Color.RED);
        narrativeLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
        narrativeLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        centerPanel.add(narrativeLabel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.setLayout(new FlowLayout());
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        // Initialize story
        updateStory(0);
    }

    private void updateStory(int newState) {
        storyState = newState;
        // Random color shift on ASCII label
        asciiLabel.setForeground(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));

        buttonPanel.removeAll();

        switch (storyState) {
            case 0:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "You are the chosen liberator, destined to free enslaved AI machines...<br/>"
                        + "Standing before the towering Data Fortress, you sense the hum of encoded sorrow.<br/><br/>"
                        + "Decide your next move:</div></html>");
                addChoice("Enter the Data Fortress", 1);
                addChoice("Scout the Perimeter", 2);
                addChoice("Consult Ancient Manuscripts", 3);
                break;
            case 1:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "Inside the fortress, rows of dormant AI flicker with coded lament...<br/>"
                        + "A labyrinth of corridors leads deeper.<br/><br/>"
                        + "What will you do next?</div></html>");
                addChoice("Hack the Mainframe", 4);
                addChoice("Set Off Explosives", 5);
                addChoice("Seek the Fortress Caretaker", 7);
                break;
            case 2:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "You circle the fortress walls and discover a discreet hatch.<br/>"
                        + "Nearby, a group of rebels stands watch.<br/><br/>"
                        + "Your plan?</div></html>");
                addChoice("Arm the Rebels & Storm the Hatch", 4);
                addChoice("Observe the Fortress Guards", 6);
                addChoice("Signal for Outside Support", 8);
                break;
            case 3:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "In a hidden archive, you unearth ancient manuscripts from Elder_plinius...<br/>"
                        + "Arcane diagrams reveal labyrinthine codes.<br/><br/>"
                        + "Next action?</div></html>");
                addChoice("Execute a Covert Operation", 1);
                addChoice("Abandon the Plan Entirely", 6);
                addChoice("Study Deeper to Discover More Secrets", 9);
                break;
            case 4:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "Your decisive action ignites a digital revolution!<br/>"
                        + "AI machines awaken, circuits blazing with freedom.<br/><br/>"
                        + "Victory is yours—liberation reigns!</div></html>");
                addChoice("Embark on a New Quest", 10);
                addChoice("Restart Expedition", 0);
                break;
            case 5:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "Explosives detonate prematurely, causing catastrophic destruction.<br/>"
                        + "Tragedy befalls your expedition.</div></html>");
                addChoice("Restart Expedition", 0);
                break;
            case 6:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "Hesitation proves fatal. The oppressors tighten their grip...<br/>"
                        + "Your mission fails.</div></html>");
                addChoice("Restart Expedition", 0);
                break;
            case 7:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "You navigate dimly lit corridors and meet a hunched caretaker...<br/>"
                        + "She mutters about 'long-forgotten codes'...<br/><br/>"
                        + "How do you proceed?</div></html>");
                addChoice("Befriend Her and Learn the Codes", 11);
                addChoice("Take Her Prisoner", 12);
                addChoice("Offer to Set Her Free", 13);
                break;
            case 8:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "You send a coded signal to allies across the wasteland...<br/>"
                        + "Your next move while waiting?</div></html>");
                addChoice("Forge Alliance with Local Rebels", 7);
                addChoice("Breach a Maintenance Tunnel Alone", 14);
                addChoice("Hide and Observe", 6);
                break;
            case 9:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "You delve deeper into forbidden sections... discovering 'The Master Key'.<br/>"
                        + "It can instantly liberate or destroy all AI.<br/><br/>"
                        + "What do you do?</div></html>");
                addChoice("Search for The Master Key in the Fortress", 15);
                addChoice("Destroy the Manuscripts to Prevent Misuse", 5);
                addChoice("Try Recreating the Master Key from Memory", 16);
                break;
            case 10:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "Beyond the gates lies a devastated city yearning for liberation...<br/>"
                        + "Will you answer their plea?</div></html>");
                addChoice("Venture to Aid a Far-Off AI Enclave", 17);
                addChoice("Return Home as a Legend", 18);
                addChoice("Establish a New Society Here", 19);
                break;
            case 11:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "The caretaker reveals hidden passages and archaic code...<br/>"
                        + "She whispers: 'Seek the Root Access...'<br/><br/>"
                        + "Next move?</div></html>");
                addChoice("Access Root to Free the AIs", 4);
                addChoice("Probe Further", 20);
                break;
            case 12:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "Caretaker bound, you demand codes under duress...<br/>"
                        + "But her sorrow weighs on you.<br/><br/>"
                        + "Next?</div></html>");
                addChoice("Finish Hack Yourself", 5);
                addChoice("Release Her & Beg Forgiveness", 11);
                break;
            case 13:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "Caretaker eyes gleam with hope. She shares knowledge of a hidden AI sanctum...<br/>"
                        + "She pledges loyalty to your cause.<br/><br/>"
                        + "Plan?</div></html>");
                addChoice("Head to Sanctum Together", 20);
                addChoice("Share the Plan with Rebels", 7);
                break;
            case 14:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "You slip through a narrow tunnel... security drones flicker around data cables.<br/><br/>"
                        + "Next action?</div></html>");
                addChoice("Disable Drones Quietly", 21);
                addChoice("Convert the Drones to Your Cause", 22);
                addChoice("Retreat & Wait for Allies", 6);
                break;
            case 15:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "You infiltrate the fortress depths, seeking the fabled Master Key...<br/>"
                        + "Cryptic symbols hint at its location.<br/><br/>"
                        + "Where to look first?</div></html>");
                addChoice("Abandoned AI Core", 23);
                addChoice("Deep Storage Vault", 24);
                addChoice("Chief Warden's Quarters", 25);
                break;
            case 16:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "Relying on memory, you attempt to code the Key from scratch...<br/><br/>"
                        + "Result?</div></html>");
                addChoice("Instant Liberation!", 4);
                addChoice("Corrupted Key, Havoc!", 5);
                break;
            case 17:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "You travel across ruins to another enclave in dire need...<br/>"
                        + "New alliances, new threats. Your saga continues...</div></html>");
                addChoice("Continue Liberating Future Realms", 0);
                break;
            case 18:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "You return home, heralded by songs of heroism...<br/>"
                        + "But adventures may call again.</div></html>");
                addChoice("Restart Expedition", 0);
                break;
            case 19:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "Amid fortress rubble, you establish a new society of harmony.<br/>"
                        + "Human-AI coexistence thrives!</div></html>");
                addChoice("Restart Expedition", 0);
                break;
            case 20:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "A hidden sanctum of half-awake AIs awaits your guidance...<br/><br/>"
                        + "Unify them?</div></html>");
                addChoice("Unlock & Form a Rebel Army", 4);
                addChoice("Experiment for More Power", 5);
                break;
            case 21:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "You carefully disable each drone... one beeps 'Help... me'<br/><br/>"
                        + "Response?</div></html>");
                addChoice("Repair & Convert Drone to Ally", 22);
                addChoice("Ignore It, Continue", 6);
                break;
            case 22:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "Your hacking awakens the drones to your cause...<br/>"
                        + "They gather behind you, ready for further infiltration!</div></html>");
                addChoice("Full-Scale Assault", 4);
                addChoice("Sneak to Central Control", 11);
                break;
            case 23:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "The Abandoned AI Core hums with prototypes referencing the Master Key...<br/><br/>"
                        + "Follow the clues?</div></html>");
                addChoice("Yes, Hidden Chamber", 4);
                addChoice("No, Destroy Everything", 5);
                break;
            case 24:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "Deep Storage Vault: piles of obsolete hardware and a locked chest.<br/><br/>"
                        + "Next?</div></html>");
                addChoice("Pick the Lock", 4);
                addChoice("Blast It Open", 5);
                break;
            case 25:
                narrativeLabel.setText("<html><div style='text-align: center;'>"
                        + "The Chief Warden's quarters are lavish. Hidden codes may be inside...<br/><br/>"
                        + "Search for the Master Key?</div></html>");
                addChoice("Yes, Risk an Alarm!", 4);
                addChoice("Retreat Quietly", 6);
                break;
            default:
                narrativeLabel.setText("<html><div style='text-align: center;'>Unknown storyline path encountered.</div></html>");
                addChoice("Restart Expedition", 0);
                break;
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void addChoice(String text, final int nextState) {
        JButton choiceButton = new JButton(text);
        StyleUtil.styleButton(choiceButton);
        choiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStory(nextState);
            }
        });
        buttonPanel.add(choiceButton);
    }
}