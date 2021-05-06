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

Il est possible d'ajouter facilement des métadonnées à vos fichiers HTML en les inscrivant dans un fichier JSON portant le même nom :
#### **`Index.md`**
```md
# Accueil
## Bienvenue
Bienvenue sur cette page générée automatiquement
```
#### **`Index.json`**
```json
{
  "title": "Accueil",
  "authors": "Diserens Lois, Jordil Kevin, Sciarra Daniel",
  "creationDate": "2021-04-11"
}
```

### Commande clean

Permet de supprimer la génération du site HTML : ```statique clean <path>```

### Commande version

Permet d'obtenir la version du générateur de site statique : ```statique -version```

### Commande serve

Permet de simuler un srv web en visualisant le site dans le naviguateur par defaut : ```statique serve <path>```
