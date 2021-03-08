# Labo 1 - Introduction
## Introduction
Dans ce labo, le but est de mettre en place un compte Github fonctionnel avec une signature GPG.
Il faut aussi initialiser un projet maven et le committer sur le repo Gtihub.

## Pratique de collaboration
### Pourquoi signer vos commits avec GPG?
Bien que Github soit cryptographiquement sûr, il n'est pas infaillible.
La clé GPG permet de signer et vérifier les commits d'une personne sur Github.

Le fait de signer ces commits sur Github permet de s'assurer que c'est bien nous qui mettons à jour du contenu de notre projet.
C'est surtout très intéressant sur des projets sensibles.

### Que signifie "commit early, commit often"?
Cela veut dire que chaque commit doit concerner une modification simple.
Il doit donc y avoir beaucoup de petits commits sur un projet pour pouvoir retracer facilement ce qui c'est passé.
Ce n'est pas toujours facile de trouver le juste milieu entre un commit trop petit ou trop grand.
Avec l'entraînement nos commits vont devenir meilleurs et contenir la bonne quantité de code.

### Que souhaitez-vous voir (ou pas) dans un message de commit?
La première fois que nous utilisons git, les messages de commit sont souvent "update file.x", "fix bugs".
Finalement ces messages ne sont pas faux, cependant on ne sait pas ce qui a été mis à jour ni ce qui a été corriger.
C'est à ce moment-là que l'on se rend compte qu'il est intéressant d'avoir de petits messages simples qui explique ce qui est fait.
Par exemple : "Fix UTF-8 bugs with output file" au lieu de "fix bugs". On a rapidement une idée de ce qui est fait dans ce commit.
Cependant, il n'est pas toujours évident d'expliquer ce qui a été réalisé en une phrase lors d'un commit.


# Labo 2 - Software processes
## Introduction
Ce labo à pour but de nous familiarisé avec le workflow de GitHub et de nous y habituer à travailler en groupe.

## Type de processus choisi
Nous travaillons avec les deux types. Tout dépend du moment, au début nous avons pris le temps d'établir les différentes tâches sur le Kaban et de nous les attribuer, 
cela correspond à un processus pilotés. Puis, en fonction des avis de chacun sur les différentes parties pour les reviews, nous nous sommes adaptés (cela correspond à un processus agile).

Pour communiquer entre nous, nous utilisons un groupe Telegram dédier à ce cours. C'est efficace, rapide et disponible en tout temps grâce à notre smartphone.
Sur Github le code review est en place avec une une validation nécessaire pour pouvoir merger une branche.
