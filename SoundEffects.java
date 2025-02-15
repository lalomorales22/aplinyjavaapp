package aplinyjavaapp;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;

public class SoundEffects {

    public static void playKeyClick() {
        playSound("/mechanical_key.wav");
        // Provide your .wav resource path or handle differently
    }

    private static void playSound(String resourcePath) {
        try {
            URL soundURL = SoundEffects.class.getResource(resourcePath);
            if(soundURL == null) {
                // If not found, just ignore or log
                return;
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}