import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class FormatManager {

    private final JTextPane textPane;

    public FormatManager(JTextPane textPane) {
        this.textPane = textPane;
    }

    public void applyFontSize(int size) {
        applyAttribute(StyleConstants.FontSize, size);
    }

    public void applyColor(Color color) {
        if (color != null) {
            applyAttribute(StyleConstants.Foreground, color);
        }
    }

    public void chooseAndApplyColor(Component parent) {
        Color chosen = JColorChooser.showDialog(parent, "Choose Text Color",
                getCurrentColor());
        applyColor(chosen);
    }

    private <T> void applyAttribute(Object key, T value) {
        StyledDocument doc = textPane.getStyledDocument();
        int start  = textPane.getSelectionStart();
        int end    = textPane.getSelectionEnd();

        if (start == end) {
            MutableAttributeSet attr = new SimpleAttributeSet(textPane.getCharacterAttributes());
            attr.addAttribute(key, value);
            textPane.setCharacterAttributes(attr, false);
        } else {
            SimpleAttributeSet attr = new SimpleAttributeSet();
            attr.addAttribute(key, value);
            doc.setCharacterAttributes(start, end - start, attr, false);
        }
        textPane.requestFocusInWindow();
    }

    private Color getCurrentColor() {
        AttributeSet attrs = textPane.getCharacterAttributes();
        Color c = StyleConstants.getForeground(attrs);
        return (c != null) ? c : Color.BLACK;
    }

    public int getCurrentFontSize() {
        AttributeSet attrs = textPane.getCharacterAttributes();
        int size = StyleConstants.getFontSize(attrs);
        return (size > 0) ? size : 14;
    }
}