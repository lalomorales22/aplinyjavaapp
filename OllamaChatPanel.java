package aplinyjavaapp;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

/**
 * OllamaChatPanel
 *
 * Revised to:
 *  - Immediately send the message on Enter or "Send" click
 *  - Stream the AI's response line-by-line so you see partial output
 *  - Log connection errors to chatArea
 */
public class OllamaChatPanel extends JPanel {

    private static final String[] AVAILABLE_MODELS = {
        "deepseek-r1:8b",
        "deepseek-r1:14b",
        "llama3.1:latest",
        "command-r7b:latest",
        "dolphin3:latest",
        "phi4:latest",
        "granite3-moe:3b",
        "qwen2.5-coder:7b",
        "granite3-dense:latest",
        "phi3.5:latest",
        "smallthinker:latest",
        "gemma:latest",
        "llama3.2:3b",
        "qwq:latest"
    };

    private final JComboBox<String> modelCombo;
    private final JTextArea chatArea;
    private final JTextField userInput;
    private final JButton sendButton;

    // Optional user "context" fields
    private final String userName = "TestUser";
    private final String userDescription = "Curious Java dev exploring AI chat.";

    public OllamaChatPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Top panel (model selection)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.BLACK);

        JLabel modelLabel = new JLabel("Model: ");
        modelLabel.setForeground(Color.RED);
        modelLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        topPanel.add(modelLabel);

        modelCombo = new JComboBox<>(AVAILABLE_MODELS);
        modelCombo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        modelCombo.setBackground(Color.BLACK);
        modelCombo.setForeground(Color.RED);
        // Show all models (so no scrollbar)
        modelCombo.setMaximumRowCount(AVAILABLE_MODELS.length);

        topPanel.add(modelCombo);
        add(topPanel, BorderLayout.NORTH);

        // Center panel: chat display (read-only but copy-enabled)
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBackground(Color.BLACK);
        chatArea.setForeground(Color.RED);
        chatArea.setCaretColor(Color.RED);
        chatArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        // Right-click menu for copying text from chatArea
        chatArea.setComponentPopupMenu(createContextMenu(false /* no paste */));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setForeground(Color.RED);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.RED));
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel: user input (paste-enabled)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.BLACK);

        userInput = new JTextField();
        userInput.setBackground(Color.BLACK);
        userInput.setForeground(Color.RED);
        userInput.setCaretColor(Color.RED);
        userInput.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Right-click menu for user input (copy & paste)
        userInput.setComponentPopupMenu(createContextMenu(true));

        sendButton = new JButton("Send");
        sendButton.setBackground(Color.BLACK);
        sendButton.setForeground(Color.RED);
        sendButton.setFont(new Font("Monospaced", Font.BOLD, 14));
        sendButton.setFocusPainted(false);

        bottomPanel.add(userInput, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        // Send button action
        sendButton.addActionListener(e -> sendMessage());

        // Also send on Enter key
        userInput.addActionListener(e -> sendMessage());
    }

    /**
     * Creates a small right-click menu for copy/paste.
     *
     * @param enablePaste If true, includes a Paste option. If false, only Copy.
     */
    private JPopupMenu createContextMenu(boolean enablePaste) {
        JPopupMenu menu = new JPopupMenu();

        // Copy
        JMenuItem copyItem = new JMenuItem(new DefaultEditorKit.CopyAction());
        copyItem.setText("Copy");
        menu.add(copyItem);

        if (enablePaste) {
            // Paste
            JMenuItem pasteItem = new JMenuItem(new DefaultEditorKit.PasteAction());
            pasteItem.setText("Paste");
            menu.add(pasteItem);
        }

        // "Select All"
        JMenuItem selectAllItem = new JMenuItem("Select All");
        selectAllItem.addActionListener(e -> {
            Component invoker = menu.getInvoker();
            if (invoker instanceof JTextComponent textComp) {
                textComp.selectAll();
            }
        });
        menu.add(selectAllItem);

        return menu;
    }

    /**
     * Called when the user presses "Send" or hits Enter.
     */
    private void sendMessage() {
        String text = userInput.getText().trim();
        if (text.isEmpty()) {
            return;
        }

        // Show user text in chat
        chatArea.append("You: " + text + "\n");
        userInput.setText("");

        // Build the prompt for Ollama
        String model = (String) modelCombo.getSelectedItem();
        String prompt = buildUserContextPrompt(userName, userDescription, text);

        // Kick off streaming in a background thread
        new Thread(() -> callOllamaAPIStreaming(model, prompt)).start();
    }

    /**
     * Example prompt with user context and user message.
     */
    private String buildUserContextPrompt(String userName, String userDesc, String message) {
        return """
            You are a helpful AI assistant.
            The user you are chatting with:
            Name: %s
            Short Description: %s

            Now, the user says: "%s"
            Please respond with helpful and relevant information based on this context.
            """
            .formatted(userName, userDesc, message);
    }

    /**
     * Stream-based call to Ollama. We post {"stream": true} and read the
     * partial responses line-by-line, appending them to the chat as they arrive.
     */
    private void callOllamaAPIStreaming(String model, String prompt) {
        HttpURLConnection conn = null;
        BufferedReader br = null;
        try {
            // Prepare JSON body with "stream": true
            String requestBody = "{"
                + "\"model\":\"" + escapeJsonString(model) + "\","
                + "\"prompt\":\"" + escapeJsonString(prompt) + "\","
                + "\"stream\": true"
                + "}";

            URL url = new URL("http://localhost:11434/api/generate");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            // Send JSON
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Read line by line (streaming)
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

            String line;
            while ((line = br.readLine()) != null) {
                // Each line is typically a small JSON object with "response" field
                String partial = extractResponseField(line);

                // Update chatArea on Swing's event-dispatch thread:
                SwingUtilities.invokeLater(() -> {
                    if (!partial.isEmpty()) {
                        chatArea.append(partial);
                    }
                });
            }

            // Finally, add a newline after finishing
            SwingUtilities.invokeLater(() -> chatArea.append("\n\n"));

        } catch (Exception e) {
            e.printStackTrace();
            String errorMsg = "Error calling Ollama API: " + e.getMessage() + "\n";
            SwingUtilities.invokeLater(() -> chatArea.append(errorMsg));
        } finally {
            if (br != null) {
                try { br.close(); } catch (IOException ignored) {}
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * From a single JSON line, parse out the "response" value.
     * Often looks like: {"model":"...","response":"...","done":false}
     */
    private String extractResponseField(String jsonLine) {
        // Simple regex to find "response":"some text"
        Pattern pattern = Pattern.compile("\"response\"\\s*:\\s*\"(.*?)\"");
        Matcher matcher = pattern.matcher(jsonLine);
        if (matcher.find()) {
            // Unescape any \n, \r
            String text = matcher.group(1);
            text = text.replace("\\n", "\n")
                       .replace("\\r", "\r");
            return text;
        }
        return "";
    }

    /**
     * Escapes backslashes, quotes, and newlines for safe JSON embedding.
     */
    private String escapeJsonString(String text) {
        if (text == null) {
            return "";
        }
        return text
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r");
    }
}
