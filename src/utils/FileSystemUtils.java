package utils;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitaire pour les opérations sur le système de fichiers.
 */
public class FileSystemUtils {

    public static void copy(Path source, Path target) throws IOException {
        Files.createDirectories(target.getParent()); // Crée les dossiers parents si nécessaires
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void delete(Path file) throws IOException {
        Files.deleteIfExists(file);
    }

    public static List<Path> listFiles(Path directory) throws IOException {
        List<Path> files = new ArrayList<>();
        if (Files.exists(directory) && Files.isDirectory(directory)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
                for (Path entry : stream) {
                    if (Files.isRegularFile(entry)) {
                        files.add(entry);
                    }
                }
            }
        }
        return files;
    }
}
