# JSync - Application de Synchronisation de Fichiers

## 1. Présentation
**JSync** est une application Java permettant de synchroniser le contenu de deux répertoires (source → cible).  
Elle fonctionne **en ligne de commande** et via une **interface graphique Swing**.  
Elle repose sur **Java 17**, **Maven**, et utilise **Jackson** pour la gestion des fichiers JSON.

---

## 2. Objectif Pédagogique
Projet réalisé dans le cadre de l’UE **Architecture Logicielle**.  
Objectifs :
- Appliquer le modèle MVC (encapsulation, séparation des responsabilités)
- Utiliser **Maven** comme outil de gestion de projet
- Gérer des **dépendances externes**
- Manipuler des fichiers avec **Java NIO**
- Persister des données avec **Jackson / JSON**

---

## 3. Technologies
- Java 17
- Maven
- Swing
- Jackson (databind, core, annotations)

---

## ♻️ Structure du projet
```bash
JSYNC/
├── pom.xml                      
├── README.md                    
├── profile.json                 
├── source_mahad/                
├── target_mahad/                
├── target/                      
└── src/
    └── main/
        ├── main/
        │   ├── NewProfile.java     
        │   └── SyncCommand.java    
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
            └── JSyncUI.java        # Notre interface graphique en Swing

```

Compilation
```bash
1. Dans un terminal, Mr vous allez vous placer à la racine du projet JSYNC puis exécutez :
mvn clean package assembly:single

2. Cela génère un JAR exécutable avec les dépendances dans :
target/jsync-1.0-SNAPSHOT-jar-with-dependencies.jar

```

Exécution
```bash
1. Interface Graphique (Swing) :
   java -cp target/jsync-1.0-SNAPSHOT-jar-with-dependencies.jar ui.JSyncUI
   ```
Sélectionner les répertoires source et cible.   
Cliquer sur Synchroniser.   
Les fichiers seront copiés du dossier source vers le dossier ciblés.