package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Profile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Programme autonome pour créer un nouveau profil de synchronisation.
 * Usage : java -jar jsync.jar new-profile /chemin/source /chemin/cible
 */
public class NewProfile {

    public static void main(String[] args) {
        if (args.length != 3 || !args[0].equalsIgnoreCase("new-profile")) {
            System.err.println("Usage : java -jar jsync.jar new-profile <source> <target>");
            return;
        }

        Path source = Paths.get(args[1]).toAbsolutePath();
        Path target = Paths.get(args[2]).toAbsolutePath();

        System.out.println("🔍 Vérification des chemins...");
        System.out.println("➡️ Source : " + source);
        System.out.println("➡️ Cible  : " + target);

        if (!Files.exists(source) || !Files.isDirectory(source)) {
            System.err.println("Le répertoire source est invalide ou n'existe pas.");
            return;
        }

        if (!Files.exists(target) || !Files.isDirectory(target)) {
            System.err.println("Le répertoire cible est invalide ou n'existe pas.");
            return;
        }

        try {
            Profile profile = new Profile(source, target);

            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("profile.json"), profile);

            System.out.println("Profil enregistré avec succès dans le fichier 'profile.json'.");
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du profil : " + e.getMessage());
        }
    }
}
