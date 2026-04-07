import javax.swing.*;

public class EditManager {

    private final JTextPane textPane;

    public EditManager(JTextPane textPane) {
        this.textPane = textPane;
    }

    public void cut() {
        textPane.cut();
    }

    public void copy() {
        textPane.copy();
    }

    public void paste() {
        textPane.paste();
    }

    public void selectAll() {
        textPane.selectAll();
    }
}