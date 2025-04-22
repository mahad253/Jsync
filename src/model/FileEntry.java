package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Objects;

public class FileEntry {
    private String relativePath;
    private long size;
    private FileTime lastModified;
    private Path path;

    /**
     * Constructeur utilisé pour la désérialisation JSON ou tests.
     */
    public FileEntry(String relativePath, long size, FileTime lastModified) {
        this.relativePath = relativePath;
        this.size = size;
        this.lastModified = lastModified;
        this.path = null; // utilisé uniquement si le chemin réel n'est pas fourni
    }

    /**
     * Constructeur automatique : lit les métadonnées à partir du fichier donné.
     */
    public FileEntry(Path path) {
        try {
            this.relativePath = path.getFileName().toString();
            this.size = Files.size(path);
            this.lastModified = Files.getLastModifiedTime(path);
            this.path = path;
        } catch (IOException e) {
            throw new RuntimeException("Impossible de lire les métadonnées du fichier : " + path, e);
        }
    }

    /**
     * Constructeur utilisé dans SyncManager quand les métadonnées sont déjà connues.
     */
    public FileEntry(Path path, long size, FileTime lastModified) {
        this.relativePath = path.getFileName().toString();
        this.size = size;
        this.lastModified = lastModified;
        this.path = path;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public long getSize() {
        return size;
    }

    public FileTime getLastModified() {
        return lastModified;
    }

    public Path getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileEntry)) return false;
        FileEntry that = (FileEntry) o;
        return size == that.size &&
                Objects.equals(relativePath, that.relativePath) &&
                Objects.equals(lastModified, that.lastModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(relativePath, size, lastModified);
    }

    @Override
    public String toString() {
        return "FileEntry{" +
                "relativePath='" + relativePath + '\'' +
                ", size=" + size +
                ", lastModified=" + lastModified +
                ", path=" + path +
                '}';
    }
}
