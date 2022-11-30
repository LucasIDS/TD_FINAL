# TD_FINAL

## Prérequis 

-Pour lancer l'application, il faut commencer par télécharger javafx ici : https://gluonhq.com/products/javafx/
-Il faut aussi avoir une SDK java relativement récente, par exemple la version 18. 
-Rajouter javafx dans les dépendances du projet
-Ensuite, il faut configurer le lancement de l'application, afin d'utiliser javafx (Sur IntelliJ : ouvrir le projet => edit configuration => add new => Maven. Dans Command Line, ajouter javafx:run)

## État

Le code est fonctionnel et compile sur n'importe quel machine.

## Update

 L'IA en mode "baby-foot" marche correctement mais il y a quelques bug avec l'animation, notement lors de la mort d'un joueur.

## Gameplay

Controles joueurs team bleue : ZQSD pour le déplacement, et ESPACE pour tirer.
Controles joueurs team rouge : fleches dirrectionnelles pour le déplacement, M pour tirer.

Il est possible de faire des passes a ses alliés, pour cela, il suffit de viser l'allié et de tirer. 
Les bots tirs automatiquement.

Possibilité d'augmenter la difficulter du jeu en modifiant la variable feinte lors de la création des bots (Class Field -> ligne 180-190).

