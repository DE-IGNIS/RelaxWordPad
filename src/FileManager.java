import javax.swing.*;
import java.io.*;

public class FileManager {

    private final WordPadApp frame;
    private final JTextPane textPane;
    private File currentFile = null;
    private boolean modified = false;

    public FileManager(WordPadApp frame, JTextPane textPane) {
        this.frame = frame;
        this.textPane = textPane;

        textPane.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { setModified(true); }
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { setModified(true); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { setModified(true); }
        });
    }

    private void setModified(boolean val) {
        modified = val;
        String title = (currentFile != null ? currentFile.getName() : "Untitled");
        frame.setTitle("WordPad - " + title + (val ? " *" : ""));
    }

    public void newFile() {
        if (!confirmSaveIfNeeded()) return;
        textPane.setText("");
        currentFile = null;
        setModified(false);
    }

    public void openFile() {
        if (!confirmSaveIfNeeded()) return;

        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files (*.txt)", "txt"));
        if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textPane.setText("");
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                textPane.setText(sb.toString());
                textPane.setCaretPosition(0);
                currentFile = file;
                setModified(false);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error opening file:\n" + ex.getMessage(),
                        "Open Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public boolean saveFile() {
        if (currentFile == null) return saveFileAs();
        return writeToFile(currentFile);
    }

    public boolean saveFileAs() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files (*.txt)", "txt"));
        if (currentFile != null) chooser.setSelectedFile(currentFile);

        if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }
            if (writeToFile(file)) {
                currentFile = file;
                return true;
            }
        }
        return false;
    }

    private boolean writeToFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(textPane.getText());
            setModified(false);
            return true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving file:\n" + ex.getMessage(),
                    "Save Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean confirmSaveIfNeeded() {
        if (!modified) return true;
        String name = (currentFile != null ? currentFile.getName() : "Untitled");
        int choice = JOptionPane.showConfirmDialog(frame,
                "Save changes to \"" + name + "\"?",
                "Unsaved Changes",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (choice == JOptionPane.YES_OPTION)    return saveFile();
        if (choice == JOptionPane.NO_OPTION)     return true;
        return false; 
    }

    public void exit() {
        if (confirmSaveIfNeeded()) {
            System.exit(0);
        }
    }
}