package ui;

import model.Profile;
import service.LatestWinsResolver;
import service.SyncManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;

public class JSyncUI extends JFrame {

    private JTextField sourceField;
    private JTextField targetField;
    private JTextArea consoleArea;

    public JSyncUI() {
        setTitle("JSync - Synchronisation de fichiers");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 3, 10, 10));

        sourceField = new JTextField();
        JButton browseSource = new JButton("Parcourir...");
        browseSource.addActionListener(e -> chooseDirectory(sourceField));

        targetField = new JTextField();
        JButton browseTarget = new JButton("Parcourir...");
        browseTarget.addActionListener(e -> chooseDirectory(targetField));

        JButton syncButton = new JButton("Synchroniser");
        syncButton.addActionListener(e -> launchSync());

        inputPanel.add(new JLabel("Répertoire source :"));
        inputPanel.add(sourceField);
        inputPanel.add(browseSource);
        inputPanel.add(new JLabel("Répertoire cible :"));
        inputPanel.add(targetField);
        inputPanel.add(browseTarget);
        inputPanel.add(new JLabel());
        inputPanel.add(syncButton);

        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void chooseDirectory(JTextField field) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            field.setText(file.getAbsolutePath());
        }
    }

    private void launchSync() {
        String source = sourceField.getText().trim();
        String target = targetField.getText().trim();

        if (source.isEmpty() || target.isEmpty()) {
            log("Veuillez sélectionner les deux répertoires.");
            return;
        }

        try {
            Profile profile = new Profile(Path.of(source), Path.of(target));
            SyncManager manager = new SyncManager(new LatestWinsResolver());

            log("🔄 Synchronisation en cours...");
            manager.synchronize(profile);
            log("✅ Synchronisation terminée !");
        } catch (Exception e) {
            log("❌ Erreur : " + e.getMessage());
        }
    }

    private void log(String message) {
        consoleArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JSyncUI().setVisible(true);
        });
    }
}
