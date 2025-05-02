package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Profile;
import service.LatestWinsResolver;
import service.SyncManager;

import java.io.File;

/**
 * Commande principale pour lancer la synchronisation.
 * Usage : java -jar jsync.jar
 */
public class SyncCommand {

    public static void main(String[] args) {
        try {
            // 1. On lit le fichier profile.json
            ObjectMapper mapper = new ObjectMapper();
            Profile profile = mapper.readValue(new File("profile.json"), Profile.class);

            // 2. On vérifie que les dossiers existent
            if (!profile.getSourcePath().toFile().isDirectory() ||
                    !profile.getTargetPath().toFile().isDirectory()) {
                System.err.println("Les chemins source ou cible sont invalides.");
                return;
            }

            // 3. On lance la synchronisation
            SyncManager manager = new SyncManager(new LatestWinsResolver());
            System.out.println("Démarrage de la synchronisation...");
            manager.synchronize(profile);
            System.out.println("Synchronisation terminée avec succès.");

        } catch (Exception e) {
            System.err.println("Erreur pendant la synchronisation : " + e.getMessage());
        }
    }
}
