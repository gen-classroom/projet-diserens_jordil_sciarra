# HEIG-VD academic project
Project to learn GitHub features and collaborative work.
<br>
<br>

<div align="center">

# Générateur de site statique
![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)
![forthebadge](https://forthebadge.com/images/badges/uses-brains.svg)

</div>

Générateur de site statique en ligne de commande. Permet de générer du HTML à partir de fichiers [Markdown](https://fr.wikipedia.org/wiki/Markdown).

## Compiler le projet

### Prérequis

Il faut tout d'abord télécharger le repository Github (https, ssh, zip)

De plus, pour compiler le projet il faut installer [Maven](https://maven.apache.org/install.html).

### Compilation

Pour compiler le projet, il faut ouvrir un invite de commande à l'emplacement du projet et exécuter la commande suivante
```sh
mvn package
```
La génération du projet sera disponible dans le dossier target.

## Utiliser le générateur de site statique

Tout d'abord, il faut soit compiler le projet, soit télécharger le zip générer dans la dernière [release](https://github.com/gen-classroom/projet-diserens_jordil_sciarra/releases).

Pour exécuter le projet il faut ouvrir un terminal à l'emplacement du projet dézippé. Puis avec la commande ```statique``` , voir les différentes commandes disponibles.

### Utiliser le générateur partout
Pour utiliser le générateur de site depuis n'importe où dans le terminal, il faut ajouter le répertoire dézippé au path.

## Commandes disponibles

Pour obtenir la liste des commandes, il faut simplement lancer ```statique``` (```statique.bat``` sur Windows) :
```
Usage: statique [COMMAND]
A brand new static site generator.
Commands:
  init      Initialize a static site directory
  clean     Clean a static site
  build     Build a static site
  serve     Serve a static site
  -version  Get version of static site generator
```

### Commande init

Pour initialiser la structure nécessaire, il faut utiliser la commande ```statique init <path>```. Par exemple : ```statique init ./Mon/Site```

### Commande build

Permet de générer le dossier build (fichiers Markdown convertir en HTML) : ```statique build <path>```.
Le paramètre ```--watch``` peut être ajouté pour permettre de régénérer le site automatiquement lorsqu'un fichier est modifié.

### Commande clean

Permet de supprimer la génération du site HTML : ```statique clean <path>```

### Commande version

Permet d'obtenir la version du générateur de site statique : ```statique -version```

### Commande serve

Permet de simuler un serveur web en visualisant le site dans le navigateur par defaut : ```statique serve <path>```
Le paramètre ```--watch``` peut être ajouté pour permettre d'actualiser automatiquement le site si des modifications sont effectuées.

## Utilisation des metadonnées

Pour ajouter des titres à vos pages, il faut créer un fichier du même nom que votre page avec l'extension .json.
Dans ce fichier, vous pouvez indiquer votre titre comme ceci :
```json
{
  "title": "Page"
}
```

Il y a également un titre de site. C'est-à-dire que chaque page aura le titre au format suivant ```Titre du site - Titre de la page```.
Pour renseigner ce titre de site, il faut éditer le fichier config.json qui se trouve à la racine et renseigner le titre voulu :
```json
{
  "title": "Website Generator"
}
```

--- 

# Utiliser SonarQube pour l'analyse de la qualité du code et du code coverage

Le rapport de la couverture de code par l'outil [JaCoCo](https://www.jacoco.org/jacoco/) a été intégrer à SonarQube.

Vous pouvez vous référer au site officiel [documentation](https://docs.sonarqube.org/latest/setup/install-server/) pour plus de détails et pour une configuration avancée.

## Installation de SonarQube depuis le fichier ZIP

Tout d'abord, vérifiez les [exigences](https://docs.sonarqube.org/latest/requirements/requirements/). Ensuite, téléchargez et décompressez la [distribution](https://www.sonarqube.org/downloads/) (ne décompressez pas dans un répertoire commençant par un chiffre).

SonarQube ne peut pas être exécuté en tant que root sur les systèmes basés sur Unix, créez donc un compte utilisateur dédié pour SonarQube si nécessaire.

$SONARQUBE-HOME (ci-dessous) fait référence au chemin du répertoire dans lequel la distribution SonarQube a été décompressée.

## Démarrage du serveur Web
Le port par défaut est "9000" et le chemin du contexte est "/". Ces valeurs peuvent être modifiées dans $SONARQUBE-HOME/conf/sonar.properties.

Exécutez le script suivant pour démarrer le serveur :
- Sous Windows : bin/windows-x86-64/StartSonar.bat
- Sous Linux : bin/linux-x86-64/sonar.sh start

Vous pouvez maintenant accéder à SonarQube à l'adresse http://localhost:9000 (les informations d'identification de l'administrateur système par défaut sont admin/admin).

Une fois sur le serveur SonarQube, vous devez ajouter un projet (en suivant les instructions), ce qui générera un jeton de connexion. Ensuite, vous pouvez lancer l'analyse de code avec la commande suivante dans un terminal (dans le dossier du projet) :

> En remplaçant `groupId:artifactId` et `generatedToken` par les valeurs appropriées
> 
> Par défaut `groupId:artifactId` : statique

```
mvn sonar:sonar -Dsonar.projectKey=<groupId:artifactId> -Dsonar.host.url=http://localhost:9000 -Dsonar.login=<generatedToken>
```
