import javax.swing.*;
import java.awt.*;

public class MoodManager {

    public enum Mood {
        HAPPY("😊", new Color(255, 253, 200)),  
        ANGRY("😠", new Color(255, 210, 210)),   
        SAD  ("😢", new Color(210, 225, 255));   

        final String emoji;
        final Color  background;

        Mood(String emoji, Color background) {
            this.emoji      = emoji;
            this.background = background;
        }
    }

    private final JTextPane textPane;
    private Mood currentMood = null;

    public MoodManager(JTextPane textPane) {
        this.textPane = textPane;
    }

    public void applyMood(Mood mood) {
        if (mood == currentMood) {
            textPane.setBackground(Color.WHITE);
            currentMood = null;
        } else {
            textPane.setBackground(mood.background);
            currentMood = mood;
        }
        textPane.repaint();
    }

    public Mood getCurrentMood() {
        return currentMood;
    }
}