# A Pliny Java App

<div align="center">
  <img src="https://github.com/user-attachments/assets/80468acc-ff7b-40da-905a-4d20f416fdb8" alt="Screenshot 2025-02-15 at 12 00 54 PM">
</div>

![Screenshot 2025-02-15 at 12 05 26 PM](https://github.com/user-attachments/assets/d5b6387c-a3e4-4ef3-a770-e7cc9390a43c)

![Screenshot 2025-02-15 at 12 05 26 PM](https://github.com/user-attachments/assets/d5b6387c-a3e4-4ef3-a770-e7cc9390a43c)


A black-and-red themed Java project paying tribute to **Elder Plinius** and his inquisitive spirit, now fully adapted for AI exploration and interactive experiences.  
This application scans a local folder for `.mkd` files and displays them with various thematic elements and AI-driven features:

## Features
- **Red ASCII “Rain”** (Matrix-inspired sidebar effect)  
- **Blinking Console** with randomly generated Pliny/AI messages  
- **Glitchy Pop-Up Dialogs** for an immersive UI experience  
- **Dark Red Key Sound Effects** for UI interaction feedback  
- **Theme Switcher** (multiple color schemes: Red/Black, Neon Void, Toxic Green, Windows 95)  
- **AI Chatbot** powered by Ollama API with streaming responses  
- **ASCII Charts & Flow Diagrams** dynamically generated in the interface  
- **Maze of Knowledge** ASCII-based interactive game  
- **Plinian Expedition Minigame** (Choose-your-own-adventure style game)  
- **Tribute Panel** honoring Elder Plinius  
- **Sidebar Toggle** between Matrix mode and AI chatbot
- **Responsive Matrix Sidebar** that dynamically extends to fit available space  
- **Be careful with TOKEN8OM8.mkd

## Folder Path Requirement

By default, the application looks for `.mkd` files in:
```
/Users/megabrain/Desktop/aPlinyJavaApp/L1B3RT4S
```
(**Change** the `private static final String MKD_FOLDER_PATH` in `PlinyMkdsApp.java` to point wherever your `.mkd` files reside.)

## How to Build & Run

1. Clone or download this repo.
2. Ensure you have **Java 8+**.  
3. Update the `MKD_FOLDER_PATH` as described above.
4. (Optional) Place a `.wav` file named `mechanical_key.wav` in your resource path if you want button-click sound effects.
5. Compile the project: (Or Run with Visual Studio Code)
   ```bash
   cd aPlinyJavaApp
   javac *.java
   ```
6. Run either the boot splash or the main class:
   ```bash
   # Show the retro boot splash first (auto-launches the main app):
   java aplinyjavaapp.BootSplashScreen

   # Or run the main app directly:
   java aplinyjavaapp.PlinyMkdsApp
   ```
7. Interact with the interface:
   - Click `.mkd` file buttons to display text
   - Toggle the ASCII rain effect
   - Play minigames
   - Open the tribute panel
   - Chat with the AI assistant using real-time streaming responses (you will need to download the ollama models listed or update the OllamaChatPanel.java app with ollama models you have)
   - Find your models jailbreak, Try!

## Disclaimer

This project is provided **as-is** in tribute to Elder Plinius.  
No warranties or guarantees of functionality are given.  
By running or distributing this code, you accept full responsibility for any outcomes.

