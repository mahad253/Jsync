package service;

import model.FileEntry;
import model.Profile;
import model.Registry;
import utils.FileSystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyncManager {

    private final ConflictResolver conflictResolver;

    public SyncManager(ConflictResolver conflictResolver) {
        this.conflictResolver = conflictResolver;
    }

    public void synchronize(Profile profile) {
        try {
            Path source = profile.getSourcePath();
            Path target = profile.getTargetPath();

            List<Path> sourceFiles = FileSystemUtils.listFiles(source);
            Map<String, FileEntry> sourceEntries = new HashMap<>();

            for (Path file : sourceFiles) {
                Path relative = source.relativize(file);
                long size = Files.size(file);
                FileTime lastModified = Files.getLastModifiedTime(file);
                sourceEntries.put(relative.toString(), new FileEntry(file, size, lastModified));
            }

            Registry registry = new Registry();

            for (Map.Entry<String, FileEntry> entry : sourceEntries.entrySet()) {
                Path targetFile = target.resolve(entry.getKey());

                FileEntry targetEntry = Files.exists(targetFile)
                        ? new FileEntry(targetFile, Files.size(targetFile), Files.getLastModifiedTime(targetFile))
                        : null;

                if (targetEntry != null) {
                    conflictResolver.resolve(entry.getValue(), targetEntry);
                } else {
                    FileSystemUtils.copy(entry.getValue().getPath(), targetFile);
                    System.out.println("Copié : " + targetFile);
                }

                // ✅ Correction ici : ajout de la clé et de la valeur
                registry.addEntry(entry.getKey(), entry.getValue());
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de la synchronisation : " + e.getMessage());
        }
    }
}
