package service;

import model.FileEntry;

public interface ConflictResolver {
    /**
     * Méthode de résolution de conflit entre deux fichiers.
     * @param source Fichier source
     * @param target Fichier cible
     */
    void resolve(FileEntry source, FileEntry target);
}
