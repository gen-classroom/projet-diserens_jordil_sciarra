# Labo 1 - Introduction
## Introduction
Le but de ce labo est de mettre en place un compte Github fonctionnel avec une signature GPG.
Il faut aussi initialiser un projet maven et le committer sur le repo Gtihub.

## Pratique de collaboration
### Pourquoi signer vos commits avec GPG?
Bien que Github soit cryptographiquement sûr, il n'est pas infaillible.
La clé GPG permet de signer et vérifier les commits d'une personne sur Github.

Le fait de signer ces commits sur Github permet de s'assurer que c'est bien nous qui mettons à jour le contenu de notre projet.
Cet aspect est important sur des projets sensibles.

### Que signifie "commit early, commit often"?
Cela veut dire que chaque commit doit concerner une modification simple.
Il est préférable d'avoir beaucoup de petits commits "simple" sur un projet pour pouvoir retracer facilement toutes les modifications le cas échéant.
Ce n'est pas toujours facile de trouver le juste milieu entre un commit trop petit ou trop grand.
Avec l'entraînement nos commits vont devenir meilleurs et contenir la bonne quantité de modification/code.

### Que souhaitez-vous voir (ou pas) dans un message de commit?
Lors des premières utilisations de git, les messages de commit sont souvent du type : "update file.x", "fix bugs".
En soit, ces messages ne sont pas faux, cependant ils n'apportent aucunes informations précises, on ne sait pas ce qui a été mis à jour, ni ce qui a été corrigé.
Dès lors, on se rend vite compte qu'il est intéressant d'avoir de petits messages simples qui explique ce qui est modifié.
Par exemple : "Fix UTF-8 bugs with output file" au lieu de "fix bugs". On a rapidement une idée de ce qui est fait dans ce commit.
Cependant, il n'est pas toujours évident d'expliquer ce qui a été réalisé en une phrase, lors d'un commit.


# Labo 2 - Software processes
## Introduction
Ce labo à pour but de nous familiariser avec le workflow de GitHub et de nous habituer à travailler en groupe.

## Type de processus choisi
Nous travaillons avec les deux types, à savoir processus piloté et agile. Au début nous avons pris le temps d'établir les différentes tâches sur le Kaban et de nous les attribuer, cela correspond donc à un processus pilotés. Puis, en fonction des avis de chacun sur les différentes parties pour les reviews, nous nous sommes adaptés et cela correspond donc à un processus agile.

Pour communiquer entre nous, nous utilisons un groupe Telegram dédier à ce cours. C'est efficace, rapide et disponible en tout temps grâce à notre smartphone.
Sur Github le code review est mis en place avec une une validation nécessaire par une autre personne résponsable afin de pouvoir merger une branche sur le main.
