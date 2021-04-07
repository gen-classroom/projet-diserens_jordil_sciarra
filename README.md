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
La génération du projet sera disponibles dans le dossier target.

## Utiliser le générateur de site statique

Tout d'abord, il faut soit compiler le projet, soit télécharger le zip générer dans la dernière [release](https://github.com/gen-classroom/projet-diserens_jordil_sciarra/releases).

Pour exécuter le projet il faut ouvrir un terminal à l'emplacement du projet dézippé. Puis avec la commande ```statique``` , voir les différentes commandes disponible.

### Utiliser le générateur partout
Pour utiliser le générateur de site depuis n'importe où dans le terminal, il faut ajouter le répertoire dézippé au path.
