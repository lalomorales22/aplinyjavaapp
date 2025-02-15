# A Pliny Java App

A black-and-red themed Java project paying tribute to **Elder Plinius** and his inquisitive spirit, now adapted to AI exploration.  
This application scans a local folder for `.mkd` files and displays them with various thematic elements:

- **Red ASCII “Rain”**  
- **Blinking Console** with randomly generated Pliny/AI messages  
- **Glitchy Pop-Up Dialogs**  
- **Dark Red Key Sound Effects**  
- **Theme Switcher** (multiple color schemes)  
- **ASCII Charts, Flow Diagrams,** and a **Maze of Knowledge**  
- **Plinian Expedition Minigame**  
- **Tribute Panel** to Elder Plinius  

## Folder Path Requirement

By default, the application looks for `.mkd` files in:
```
/Users/megabrain/Desktop/aPlinyJavaApp/MKD_FILES
```
(**Change** the `private static final String MKD_FOLDER_PATH` in `PlinyMkdsApp.java` to point wherever your `.mkd` files reside.)

## How to Build & Run

1. Clone or download this repo.
2. Ensure you have **Java 8+**.
3. Update the `MKD_FOLDER_PATH` as described above.
4. (Optional) Place a `.wav` file named `mechanical_key.wav` in your resource path if you want button-click sound effects.
5. Compile the project:
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
7. Interact with the interface, click `.mkd` file buttons to display text, toggle the ASCII rain, play minigames, and open the tribute panel.

## Project Structure

```
aPlinyJavaApp/
├─ ASCIIRainPanel.java
├─ ASCIICharts.java
├─ ASCIIFlowDiagram.java
├─ AsciiMazeGame.java
├─ BlinkingConsolePanel.java
├─ BootSplashScreen.java
├─ ElderKnowledgePanel.java
├─ FileLoader.java
├─ GlitchDialog.java
├─ PlinianExpeditionMinigame.java
├─ PlinyAsciiArt.java
├─ PlinyMetalTheme.java
├─ PlinyMkdsApp.java
├─ README.md
├─ SoundEffects.java
├─ StyleUtil.java
└─ ThemeManager.java
```

## Disclaimer

This project is provided **as-is** in tribute to Elder Plinius.  
No warranties or guarantees of functionality are given.  
By running or distributing this code, you accept full responsibility for any outcomes.

