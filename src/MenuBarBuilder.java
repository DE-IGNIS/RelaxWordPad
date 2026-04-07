import javax.swing.*;
import java.awt.event.*;

public class MenuBarBuilder {

    public static JMenuBar build(FileManager fm, EditManager em, FormatManager fmt) {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        JMenuItem newItem  = new JMenuItem("New",  KeyEvent.VK_N);
        JMenuItem openItem = new JMenuItem("Open", KeyEvent.VK_O);
        JMenuItem saveItem = new JMenuItem("Save", KeyEvent.VK_S);
        JMenuItem saveAsItem = new JMenuItem("Save As...");
        JMenuItem exitItem = new JMenuItem("Exit");

        newItem .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        newItem .addActionListener(e -> fm.newFile());
        openItem.addActionListener(e -> fm.openFile());
        saveItem.addActionListener(e -> fm.saveFile());
        saveAsItem.addActionListener(e -> fm.saveFileAs());
        exitItem.addActionListener(e -> fm.exit());

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');

        JMenuItem cutItem   = new JMenuItem("Cut");
        JMenuItem copyItem  = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");
        JMenuItem selAllItem = new JMenuItem("Select All");

        cutItem  .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        copyItem .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        selAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        cutItem  .addActionListener(e -> em.cut());
        copyItem .addActionListener(e -> em.copy());
        pasteItem.addActionListener(e -> em.paste());
        selAllItem.addActionListener(e -> em.selectAll());

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        editMenu.addSeparator();
        editMenu.add(selAllItem);

        JMenu formatMenu = new JMenu("Format");
        formatMenu.setMnemonic('O');

        JMenuItem colorItem = new JMenuItem("Text Color...");
        colorItem.addActionListener(e -> fmt.chooseAndApplyColor(null));
        formatMenu.add(colorItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        return menuBar;
    }
}