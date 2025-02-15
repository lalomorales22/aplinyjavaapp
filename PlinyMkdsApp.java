package aplinyjavaapp;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.plaf.metal.*;

/**
 * PlinyMkdsApp
 *
 * A Swing-based GUI that scans a directory for .mkd files,
 * displays them as large black/red buttons on the left (2 columns, many rows),
 * shows the file contents in the center,
 * and has an ASCII rain panel on the right.
 */
public class PlinyMkdsApp extends JFrame {

    private final JPanel buttonPanel;
    private final JTextArea textArea;
    private final JLabel asciiLabelTop;
    private final ASCIIRainPanel rainPanel;

    // Path to .mkd files
    private static final String MKD_FOLDER_PATH = "/Users/megabrain/Desktop/aPlinyJavaApp/L1B3RT4S";

    public PlinyMkdsApp() {
        super("A Pliny Java App - MKD Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 900);
        setLocationRelativeTo(null);

        // Initialize global UI defaults (black and red)
        StyleUtil.initGlobalUI();

        // A quick menu bar to demonstrate theme switching & mini-features
        createMenuBar();

        // Place some ASCII art at the top
        asciiLabelTop = new JLabel("<html><pre>" + PlinyAsciiArt.PLINY_BANNER + "</pre></html>");
        asciiLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
        asciiLabelTop.setForeground(Color.RED);

        // Main layout
        getContentPane().setLayout(new BorderLayout());

        // ======== LEFT PANEL (NO SCROLLBARS), 2 COLUMNS ========
        buttonPanel = new JPanel();
        // GridLayout(0, 2) = unlimited rows, 2 columns
        buttonPanel.setLayout(new GridLayout(0, 2)); 
        buttonPanel.setBackground(Color.BLACK);
        // Give it a preferred width so it’s clearly visible
        buttonPanel.setPreferredSize(new Dimension(400, 0));
        // Add directly to WEST without a JScrollPane (no scrollbars)
        getContentPane().add(buttonPanel, BorderLayout.WEST);

        // ======== TEXT AREA (Center) ========
        textArea = new JTextArea();
        StyleUtil.styleTextArea(textArea);
        JScrollPane scrollPane = new JScrollPane(textArea);
        StyleUtil.styleScrollPane(scrollPane);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // ======== ASCII RAIN (Right) ========
        rainPanel = new ASCIIRainPanel();
        // Give it a preferred width so it shows as a sidebar
        rainPanel.setPreferredSize(new Dimension(300, 0));
        getContentPane().add(rainPanel, BorderLayout.EAST);

        // ======== TOP LABEL (North) ========
        getContentPane().add(asciiLabelTop, BorderLayout.NORTH);

        // Load the .mkd files
        loadMkdFiles(MKD_FOLDER_PATH);
    }

    /**
     * Build the main menu bar (Themes, Features, Panels, Tribute).
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.BLACK);

        // "Visual Themes" menu
        JMenu themeMenu = new JMenu("Visual Themes");
        themeMenu.setForeground(Color.RED);

        JMenuItem defaultThemeItem = new JMenuItem("Default Black & Red");
        defaultThemeItem.addActionListener(e -> ThemeManager.applyTheme(ThemeManager.Theme.RED_BLACK, this));

        JMenuItem neonVoidItem = new JMenuItem("Neon Purple");
        neonVoidItem.addActionListener(e -> ThemeManager.applyTheme(ThemeManager.Theme.NEON_VOID, this));

        JMenuItem toxicGreenItem = new JMenuItem("Toxic Green (Legacy)");
        toxicGreenItem.addActionListener(e -> ThemeManager.applyTheme(ThemeManager.Theme.TOXIC_GREEN, this));

        JMenuItem win95Item = new JMenuItem("Windows95 Gray");
        win95Item.addActionListener(e -> ThemeManager.applyTheme(ThemeManager.Theme.WIN95_GRAY, this));

        themeMenu.add(defaultThemeItem);
        themeMenu.add(neonVoidItem);
        themeMenu.add(toxicGreenItem);
        themeMenu.add(win95Item);

        // "Features" menu
        JMenu featuresMenu = new JMenu("Features");
        featuresMenu.setForeground(Color.RED);

        JMenuItem barChartItem = new JMenuItem("Pliny Bar Chart");
        barChartItem.addActionListener(e -> {
            String barMulti = ASCIICharts.generateMultiBarChart();
            textArea.append("\n" + barMulti + "\n");
        });

        JMenuItem flowItem = new JMenuItem("Run Path To Enlightenment");
        flowItem.addActionListener(e -> {
            String flow = ASCIIFlowDiagram.generateFlow("start -> gather -> analyze -> end");
            flow += "\n" + ASCIIFlowDiagram.generateFancyFlow("Pliny -> research -> discovery -> AI synergy -> success");
            textArea.append("\n" + flow + "\n");
        });

        JMenuItem expeditionItem = new JMenuItem("Plinian Expedition Minigame");
        expeditionItem.addActionListener(e -> {
            PlinianExpeditionMinigame minigame = new PlinianExpeditionMinigame();
            JDialog dialog = new JDialog(this, "Plinian Expedition", true);
            dialog.setSize(500, 300);
            dialog.setLocationRelativeTo(this);
            dialog.add(minigame);
            dialog.setVisible(true);
        });

        JMenuItem asciiMazeItem = new JMenuItem("ASCII Maze of Knowledge");
        asciiMazeItem.addActionListener(e -> {
            AsciiMazeGame maze = new AsciiMazeGame();
            JDialog dialog = new JDialog(this, "ASCII Maze", true);
            dialog.setSize(520, 800);
            dialog.setLocationRelativeTo(this);
            dialog.add(maze);
            dialog.setVisible(true);
        });

        featuresMenu.add(barChartItem);
        featuresMenu.add(flowItem);
        featuresMenu.add(expeditionItem);
        featuresMenu.add(asciiMazeItem);

        // "Panels" menu
        JMenu panelsMenu = new JMenu("Panels");
        panelsMenu.setForeground(Color.RED);

        JMenuItem toggleRain = new JMenuItem("Toggle ASCII Rain");
        toggleRain.addActionListener(e -> {
            if(rainPanel.isVisible()) {
                rainPanel.setVisible(false);
                rainPanel.stopRain();
            } else {
                rainPanel.setVisible(true);
                rainPanel.startRain();
            }
        });
        panelsMenu.add(toggleRain);

        // "Tribute" menu
        JMenu tributeMenu = new JMenu("Tribute");
        tributeMenu.setForeground(Color.RED);

        JMenuItem showTributeItem = new JMenuItem("Elder Knowledge");
        showTributeItem.addActionListener(e -> {
            ElderKnowledgePanel panel = new ElderKnowledgePanel();
            JDialog dialog = new JDialog(this, "Tribute to Elder Plinius", true);
            dialog.setSize(600, 400);
            dialog.setLocationRelativeTo(this);
            dialog.add(panel);
            dialog.setVisible(true);
        });

        tributeMenu.add(showTributeItem);

        menuBar.add(themeMenu);
        menuBar.add(featuresMenu);
        menuBar.add(panelsMenu);
        menuBar.add(tributeMenu);

        setJMenuBar(menuBar);
    }

    /**
     * Loads all .mkd files from the specified folder
     * and creates a button for each one in the left panel.
     */
    private void loadMkdFiles(String folderPath) {
        File dir = new File(folderPath);
        if (!dir.exists() || !dir.isDirectory()) {
            textArea.setText("Directory not found: " + folderPath +
                    "\n\nPlease check the path in PlinyMkdsApp.MKD_FOLDER_PATH");
            return;
        }

        File[] mkdFiles = dir.listFiles((file, name) -> name.toLowerCase().endsWith(".mkd"));
        if (mkdFiles == null || mkdFiles.length == 0) {
            textArea.setText("No .mkd files found in " + folderPath);
            return;
        }

        for (File mkdFile : mkdFiles) {
            JButton btn = new JButton(mkdFile.getName());
            StyleUtil.styleButton(btn);
            btn.setToolTipText("Click to view " + mkdFile.getName());

            // On click, load the file content into the text area
            btn.addActionListener(e -> {
                SoundEffects.playKeyClick();
                String content = FileLoader.loadFileContent(mkdFile);
                textArea.setText(content);
            });

            buttonPanel.add(btn);
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new MetalLookAndFeel());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            MetalLookAndFeel.setCurrentTheme(new PlinyMetalTheme());

            PlinyMkdsApp frame = new PlinyMkdsApp();
            frame.setVisible(true);

            // Start the ASCII rain if desired
            frame.rainPanel.startRain();
        });
    }
}