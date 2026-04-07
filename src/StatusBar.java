import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class StatusBar {

    public static JPanel build(JTextPane textPane) {
        JLabel label = new JLabel("Words: 0   Characters: 0");
        label.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        label.setFont(new Font("SansSerif", Font.PLAIN, 11));
        label.setForeground(Color.DARK_GRAY);

        textPane.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e)  { update(); }
            public void removeUpdate(DocumentEvent e)  { update(); }
            public void changedUpdate(DocumentEvent e) { update(); }

            private void update() {
                String text = textPane.getText().trim();
                int chars = text.length();
                int words = text.isEmpty() ? 0 : text.split("\\s+").length;
                SwingUtilities.invokeLater(() ->
                        label.setText("Words: " + words + "   Characters: " + chars));
            }
        });

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        panel.add(label);
        return panel;
    }
}