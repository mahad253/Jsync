# JSync - Application de Synchronisation de Fichiers

## ✨ Présentation
**JSync** est une application Java permettant de synchroniser le contenu de deux répertoires (source → cible).  
Elle fonctionne **en ligne de commande** et via une **interface graphique Swing**.  
Elle repose sur **Java 17**, **Maven**, et utilise **Jackson** pour la gestion des fichiers JSON.

---

## 🎓 Objectif Pédagogique
Projet réalisé dans le cadre de l’UE **Architecture Logicielle**.  
Objectifs :
- Appliquer le modèle MVC (encapsulation, séparation des responsabilités)
- Utiliser **Maven** comme outil de gestion de projet
- Gérer des **dépendances externes**
- Manipuler des fichiers avec **Java NIO**
- Persister des données avec **Jackson / JSON**

---

## ⚙️ Technologies
- Java 17
- Maven
- Swing
- Jackson (databind, core, annotations)

---

## ♻️ Structure du projet
```bash
JSYNC/
├── pom.xml                      # Fichier Maven
├── README.md                    # Fichier de documentation
├── profile.json                 # Fichier de configuration généré
├── source_mahad/                # Répertoire source (à synchroniser)
├── target_mahad/                # Répertoire cible (synchronisé)
├── target/                      # Répertoire de compilation Maven
└── src/
    └── main/
        ├── main/
        │   ├── NewProfile.java     # Création du profil
        │   └── SyncCommand.java    # Lancement de la synchro (CLI)
        ├── model/
        │   ├── FileEntry.java
        │   ├── Profile.java
        │   └── Registry.java
        ├── service/
        │   ├── ConflictResolver.java
        │   ├── LatestWinsResolver.java
        │   └── SyncManager.java
        ├── utils/
        │   └── FileSystemUtils.java
        └── ui/
            └── JSyncUI.java        # Interface graphique Swing

```

⚡ Compilation
```bash
1. Dans un terminal, place-toi à la racine du projet JSYNC puis exécute :
mvn clean package assembly:single

2. Cela génère un JAR exécutable avec les dépendances dans :
target/jsync-1.0-SNAPSHOT-jar-with-dependencies.jar

```

🚀 Exécution
```bash
1. Interface Graphique (Swing) :
   java -cp target/jsync-1.0-SNAPSHOT-jar-with-dependencies.jar ui.JSyncUI
   ```
Sélectionner les répertoires source et cible.   
Cliquer sur Synchroniser.   
Les fichiers seront copiés du dossier source vers le dossier ciblés.