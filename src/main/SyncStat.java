package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.FileEntry;
import model.Profile;
import utils.FileSystemUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Commande de diagnostic permettant d'afficher les métadonnées
 * des fichiers contenus dans le profil actuel.
 */
public class SyncStat {

    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Profile profile = mapper.readValue(new File("profile.json"), Profile.class);

            System.out.println("Profil chargé :");
            System.out.println("Source : " + profile.getSourcePath());
            System.out.println("Cible  : " + profile.getTargetPath());
            System.out.println(" ");

            List<Path> sourceFiles = FileSystemUtils.listFiles(profile.getSourcePath());
            List<Path> targetFiles = FileSystemUtils.listFiles(profile.getTargetPath());

            System.out.println("Fichiers dans le répertoire source :");
            for (Path path : sourceFiles) {
                FileEntry fe = new FileEntry(path);
                System.out.println("- " + fe.getRelativePath() + " | modifié : " + fe.getLastModified());
            }

            System.out.println("\nFichiers dans le répertoire cible :");
            for (Path path : targetFiles) {
                FileEntry fe = new FileEntry(path);
                System.out.println("- " + fe.getRelativePath() + " | modifié : " + fe.getLastModified());
            }

        } catch (Exception e) {
            System.err.println("Erreur dans l'affichage des statistiques : " + e.getMessage());
        }
    }
}
