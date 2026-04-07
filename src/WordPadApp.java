import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WordPadApp extends JFrame {

    private JTextPane textPane;
    private JScrollPane scrollPane;
    private FileManager fileManager;
    private EditManager editManager;
    private FormatManager formatManager;
    private MoodManager moodManager;

    public WordPadApp() {
        setTitle("WordPad - Untitled");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        textPane = new JTextPane();
        textPane.setFont(new Font("Serif", Font.PLAIN, 14));
        textPane.setMargin(new Insets(8, 10, 8, 10));

        scrollPane = new JScrollPane(textPane);

        fileManager   = new FileManager(this, textPane);
        editManager   = new EditManager(textPane);
        formatManager = new FormatManager(textPane);
        moodManager   = new MoodManager(textPane);

        setJMenuBar(MenuBarBuilder.build(fileManager, editManager, formatManager));
        add(FormatToolBar.build(formatManager, moodManager), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(StatusBar.build(textPane), BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fileManager.exit();
            }
        });
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }
}