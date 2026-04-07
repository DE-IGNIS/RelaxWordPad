import javax.swing.*;
import java.awt.*;

public class FormatToolBar {

    public static JToolBar build(FormatManager fmt, MoodManager mood) {
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setBorder(BorderFactory.createEmptyBorder(3, 4, 3, 4));

        toolbar.add(new JLabel("Size: "));

        SpinnerNumberModel sizeModel = new SpinnerNumberModel(14, 6, 96, 1);
        JSpinner sizeSpinner = new JSpinner(sizeModel);
        sizeSpinner.setMaximumSize(new Dimension(64, 26));
        sizeSpinner.setPreferredSize(new Dimension(64, 26));
        sizeSpinner.setToolTipText("Font size");
        sizeSpinner.addChangeListener(e -> fmt.applyFontSize((int) sizeSpinner.getValue()));

        toolbar.add(sizeSpinner);
        toolbar.addSeparator(new Dimension(10, 0));

        JButton colorBtn = new JButton("A");
        colorBtn.setForeground(Color.RED);
        colorBtn.setFont(colorBtn.getFont().deriveFont(Font.BOLD, 14f));
        colorBtn.setToolTipText("Text Color");
        colorBtn.setFocusable(false);
        colorBtn.addActionListener(e -> fmt.chooseAndApplyColor(colorBtn));

        toolbar.add(colorBtn);
        toolbar.addSeparator(new Dimension(16, 0));

        JLabel moodLabel = new JLabel("Mood:");
        moodLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        moodLabel.setForeground(Color.DARK_GRAY);
        toolbar.add(moodLabel);
        toolbar.addSeparator(new Dimension(4, 0));

        JButton[] moodBtns = new JButton[MoodManager.Mood.values().length];
        int i = 0;
        for (MoodManager.Mood m : MoodManager.Mood.values()) {
            JButton btn = new JButton(m.emoji);
            btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
            btn.setFocusable(false);
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn.setToolTipText(m.name().charAt(0) + m.name().substring(1).toLowerCase()
                    + " — click to change background");
            moodBtns[i++] = btn;
            toolbar.add(btn);
        }

        for (int j = 0; j < moodBtns.length; j++) {
            final MoodManager.Mood m = MoodManager.Mood.values()[j];
            final JButton[] allBtns = moodBtns;
            moodBtns[j].addActionListener(e -> {
                mood.applyMood(m);
                for (int k = 0; k < allBtns.length; k++) {
                    MoodManager.Mood mk = MoodManager.Mood.values()[k];
                    boolean active = mood.getCurrentMood() == mk;
                    allBtns[k].setOpaque(active);
                    allBtns[k].setContentAreaFilled(active);
                    allBtns[k].setBackground(active ? mk.background.darker() : null);
                    allBtns[k].repaint();
                }
            });
        }

        return toolbar;
    }
}