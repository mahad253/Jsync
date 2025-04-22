package service;

import model.FileEntry;
import utils.FileSystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Implémentation de ConflictResolver qui garde le fichier le plus récent.
 */
public class LatestWinsResolver implements ConflictResolver {

    @Override
    public void resolve(FileEntry source, FileEntry target) {
        Path sourcePath = source.getPath();
        Path targetPath = target.getPath();

        try {
            if (Files.getLastModifiedTime(sourcePath).compareTo(Files.getLastModifiedTime(targetPath)) > 0) {
                // Source est plus récent -> copie vers target
                FileSystemUtils.copy(sourcePath, targetPath);
                System.out.println("[Résolu] Copie du fichier plus récent de source vers target : " + sourcePath.getFileName());
            } else {
                System.out.println("[Info] Aucun changement : target est plus récent ou identique.");
            }
        } catch (IOException e) {
            System.err.println("Erreur de résolution de conflit : " + e.getMessage());
        }
    }
}
