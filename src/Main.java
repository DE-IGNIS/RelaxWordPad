public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            WordPadApp app = new WordPadApp();
            app.setVisible(true);
        });
    }
}