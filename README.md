# SAE 1.1 Jeu de Master Mind



### I.U.T. Montpellier-Sète – Département informatique – BUT 1re année

## Sommaire

* [Présentation du jeu de Master Mind et du projet à réaliser](./#1)
* [Version de base](./#2)
  * [Les paramètres d’une partie](./#3)
  * [Les deux représentations d’un code](./#4)
  * [Précisions sur la fin d’une manche](./#5)
  * [Outils de base](./#6)
  * [Une manche Humain](./#7)
  * [Fonctions complémentaires sur les codes pour la manche Ordinateur](./#8)
  * [Une manche Ordinateur](./#9)
  * [Le programme principal](./#10)
* [Extensions](./#11)

## Présentation du jeu de Master Mind et du projet à réaliser

Le but de ce projet est de programmer une partie du jeu de [application.MasterMind](https://fr.wikipedia.org/wiki/Mastermind) entre un humain et l’ordinateur.

Le Master Mind est un jeu à 2 joueurs. Lors d’une manche d’une partie de Master Mind, l’un des joueurs est le codeur (ou codificateur) et l’autre le décodeur. Le codeur choisit un code secret, qui est une séquence d’un certain nombre (4 ou 5 dans les versions classiques du jeu) de pions de couleur. Le nombre de couleurs possibles est 6 ou 8 dans les versions classiques du jeu. Le décodeur doit alors déterminer ce code secret.

Pour cela, il fait des propositions de codes au codeur, qui lui indique pour chacune d’elles le nombre de pions de la bonne couleur bien placés et mal placés. Par exemple, si le code secret est (Bleu, Rouge, Jaune, Rouge) et que le décodeur propose (Jaune, Rouge, Rouge, Vert), le codeur indique 1 bien placé (le pion rouge de rang 1, en considérant que le premier pion est de rang 0) et 2 mal placés (l’autre pion rouge et le pion jaune).

La manche se termine quand le décodeur propose un code égal au code secret. Le nombre de propositions de code faites par le décodeur est alors ajouté au score du codeur. La règle impose un nombre d’essais maximum (`nbEssaisMax`) au décodeur (égal au nombre de rangées sur le plateau). Quand le décodeur atteint le nombre maximum d’essais, `nbEssaisMax` est ajouté au score, ainsi qu’un malus lié au dernier code proposé.

Une partie se compose d’un certain nombre de manches, chaque joueur étant alternativement codeur et décodeur. Le nombre de manches doit donc être pair pour équilibrer les chances des 2 joueurs. Le gagnant de la partie est le joueur ayant le plus grand score à la fin de la partie.

Vous allez d’abord programmer une version de base du jeu, entre un humain et l’ordinateur. Vous pourrez ensuite améliorer cette première version à l’aide de plusieurs extensions optionnelles permettant :

* d’évaluer la stratégie de l’ordinateur, et de la comparer éventuellement à d’autres stratégies ;
* d’améliorer la présentation visuelle du jeu ;
* de réécrire le programme en programmation objet, plus appropriée pour gérer les différents éléments du jeu ;
* de définir des contre-stratégies pour le joueur humain et l’ordinateur ;
* de définir de nouvelles stratégies pour l’ordinateur.

Il vous est demandé de programmer au minimum la version de base. Pour l’évaluation, il sera tenu compte des extensions réalisées, mais surtout de la qualité de programmation. Il vous est donc conseillé de prendre le temps de bien écrire la version de base avant d’aborder les extensions, parmi lesquelles vous pouvez choisir celles que vous souhaitez programmer, dans l’ordre que vous préférez.

Vous rendrez donc au minimum une classe `MasterMindBase` (version de base), et éventuellement une classe `MasterMindEtendu` et des classes correspondant à la version objet. Si vous avez une version étendue, vous indiquerez en commentaires au début de la classe `MasterMindEtendu` les numéros des extensions choisies, ainsi que toutes les explications nécessaires.

Un squelette de la classe `MasterMindBase` vous est donné. Ce squelette contient les en-têtes et spécifications de toutes les fonctions nécessaires à la version de base. Vous devez écrire le code de ces fonctions sans en modifier les en-têtes et en suivant scrupuleusement leurs spécifications, faute de quoi vos fonctions ne passeront pas les tests automatiques qui seront utilisés pour l’évaluation de votre projet ! Vous pouvez rajouter des fonctions à ce squelette si vous en éprouvez le besoin, à condition d’en écrire les spécifications et d’écrire aussi le code de toutes les fonctions demandées, puisque ce sont elles qui seront testées par les tests automatiques.

## Version de base

### Les paramètres d’une partie

Avant de commencer la partie, joueur (humain) saisit le nombre de pions du code secret, noté `lgCode`, le nombre de couleurs possibles, noté nbCouleurs, l’identité de ces couleurs, le nombre de manches de la partie, noté nbManches, et le nombre maximum de codes à proposer (correspondant au nombre de rangées sur le plateau), noté `nbEssaisMax`.

Les couleurs sont stockées dans un tableau de caractères `tabCouleurs` de longueur nbCouleurs contenant les initiales des noms de couleurs saisis par le joueur. Ce sont ces initiales qui seront utilisées dans les codes. Les noms de couleurs doivent donc avoir des initiales distinctes. Les entiers `lgCode`, nbCouleurs, nbManches et `nbEssaisMax` doivent être strictement positifs, et nbManches doit être pair. Ces conditions sont à vérifier au moment de la saisie, et comme indiqué dans le squelette de code, ce sont des pré-requis implicites de toutes les fonctions ayant ces données en paramètres.

Dans les exemples donnés dans le sujet et le squelette, on suppose que `nbCouleurs = 6`, `lgCode = 4` et que les couleurs saisies sont `"Rouge", "Bleu", "Jaune", "Vert", "Orange" et "Noir"` dans cet ordre, de sorte que le tableau `tabCouleurs` contient `(’R’, ’B’, ’J’, ’V’, ’O’, ’N’)`.

### Les deux représentations d’un code

Un code est représenté soit par un mot (de type String) de longueur lgCode dont chaque caractère est un élément de `tabCouleurs` (pour la saisie ou l’affichage du code), soit par un tableau d’entiers obtenu à partir de ce mot en remplaçant chaque caractère par son indice dans `tabCouleurs`, appelé "numéro" de la couleur correspondante (pour les calculs sur ce code).

Pour l’exemple ci-dessus, le code (Bleu, Rouge, Jaune, Rouge) est représenté, à l’écran, par le mot "BRJR" et, en machine, par le tableau d’entiers (1, 0, 2, 0).

### Précisions sur la fin d’une manche

Rappelons qu’une manche se termine quand le décodeur propose un code égal au code secret ou qu’il a fait `nbEssaisMax` propositions.

Dans le premier cas (code trouvé), le nombre de propositions de code faites par le décodeur est ajouté au score du codeur.

Dans le deuxième cas, on ajoute `nbEssaisMax` (qui est aussi le nombre de propositions de code) au score, plus un malus qui dépend de la réponse au dernier code proposé :

```
rep[nbEssaisMax − 1] = (nbBienPlaces, nbMalPlaces)
```

On considèrera dans le programme (dans les fonctions mancheHumain et mancheOrdinateur) :

```
malus = nbMalPlaces + 2 × (lgCode − (nbBienPlaces + nbMalPlaces))
```

Ce qui incitera le décodeur à faire une dernière proposition avec un maximum de pions de la bonne couleur...

### Outils de base

Vous allez écrire des fonctions utiles pour votre programme de jeu : d’abord des fonctions classiques sur les tableaux, puis des fonctions plus spécifiques pour la gestion des codes sous forme de mots ou de tableaux d’entiers.

Le nombre de pions mal placés dans un code par rapport au code secret est le nombre d’éléments communs aux deux tableaux d’entiers indépendamment de leur position, qui ne sont pas bien placés. Le nombre d’éléments communs aux deux tableaux se calcule efficacement à l’aide de leurs tableaux de fréquence (le tableau de fréquence d’un code contient à l’indice i le nombre de pions de ce code de la couleur numéro i).

Ecrire et tester les fonctions de la rubrique Outils de base.

### Une manche Humain

On appelle manche Humain une manche au cours de laquelle c’est le joueur humain qui "joue", c’est-à-dire qui est le décodeur. L’ordinateur choisit un code secret sous forme d’un tableau d’entiers choisis aléatoirement entre 0 et nbCouleurs − 1. Ensuite, pour chaque code proposé par le joueur sous forme de mot, il vérifie que ce code est correctement formé (s’il ne l’est pas, le joueur doit le re-saisir jusqu’à ce qu’il le soit), puis il transforme le mot en un tableau d’entiers et affiche à l’écran les nombres de pions bien et mal placés dans le code proposé par le joueur.

Ecrire et tester la fonction mancheHumain.

### Fonctions complémentaires sur les codes pour la manche Ordinateur

Après quelques fonctions utiles pour l’affichage et la saisie, on vous propose d’écrire les fonctions gérant la stratégie de l’ordinateur.

La stratégie de l’ordinateur pour trouver le code secret S est la suivante. Il propose tous les codes candidats possibles dans un certain ordre, sauf ceux qui ne sont pas compatibles avec les propositions et les réponses précédentes du joueur, c’est-à-dire telles que si le code à proposer était le code secret, le joueur n’aurait pas répondu exactement les mêmes réponses aux propositions précédentes. Autrement dit, si on compare un code à proposer candidat C avec un code déjà proposé O et que la réponse (nombre de pions bien et mal placés) est différente de la réponse obtenue en comparant S et O, alors on sait que le code C ne peut pas être le code secret S et l’ordinateur ne le propose pas.

Une telle stratégie peut sembler assez inefficace a priori, ce sera à vous d’en juger par expérimentation (et calcul dans l’extension 3.2). Pour pouvoir déterminer la compatibilité d’une proposition de code, vous définirez la matrice cod, de dimensions `nbEssaisMax × lgCode`, qui contient l’historique des propositions de codes. Vous définirez également la matrice rep de dimensions `nbEssaisMax × 2` contenant les réponses du joueur. Pour toute ligne i :

* `cod[i]` contient la (i + 1)ème proposition de code ;
* `rep[i][0]` contient le nombre de pions bien placés à cette proposition ;
* `rep[i][1]` contient le nombre de pions mal placés à cette proposition.

L’ordre choisi sur l’ensemble des codes représentés par des tableaux d’entiers est l’ordre lexicographique. Késako ?

Etant donnés deux tableaux t1 et t2 de longueur `lgCode` contenant des entiers naturels inférieurs à nbCouleurs, t1 est inférieur à t2 dans l’ordre lexicographique si t1\[i] < t2\[i], où i est le plus petit indice tel que t1\[i] , t2\[i]. Dans l’exemple ci-dessus, si t1 = (3, 2, 3, 4) et t2 = (3, 2, 4, 0), t1 est inférieur à t2 car le plus petit indice i tel que t1\[i] , t2\[i] est 2 et t1\[2] = 3 < 4 = t2\[2]. Cet ordre correspond à l’ordre usuel sur les entiers naturels inférieurs à nbCouleurslgCode si on voit un code comme l’écriture d’un tel entier en base `nbCouleurs` sur `lgCode` chiffres, en complétant par des 0 à gauche. C’est aussi en ordre lexicographique que sont rangés les mots dans un dictionnaire, en considérant l’ordre alphabétique entre les lettres.

Remarque importante : La fonction `passeCodeSuivantLexico` modifie le paramètre `cod1` en le remplaçant par le code suivant. De même, la fonction `passePropSuivante` remplit une ligne de la matrice `cod` donnée en paramètre. Donc, contrairement à ce qui était souvent fait en cours et en TD, vous allez définir des fonctions modifiant les tableaux (ou matrices) donnés en paramètres. En Java, un tableau transmis en paramètre d’une fonction f2 et modifié dans f2 est aussi modifié dans la fonction f1 appelant f2, ce qui est justement l’effet souhaité ici.

Ecrire et tester les fonctions complémentaires sur les codes pour la manche Ordinateur.

### Une manche Ordinateur

Une manche Ordinateur est une manche au cours de laquelle c’est l’ordinateur qui "joue" (décode). Le joueur humain choisit un code "dans sa tête", mais ne le saisit pas au clavier. L’ordinateur fait une suite de propositions de codes affichées sous forme de mots, et pour chacune d’elles, il demande au joueur humain le nombre de pions bien et mal placés et vérifie que les réponses sont correctement formées (si elles ne le sont pas, le joueur doit les re-saisir jusqu’à ce qu’elles le soient). Les propositions de code de l’ordinateur suivent la stratégie définie ci-dessus.

Si l’ordinateur atteint le dernier code possible dans l’ordre lexicographique sans trouver le code secret, c’est que le joueur s’est trompé dans au moins une réponse, ou a volontairement triché. Dans ce cas, le programme n’ajoute aucun point au score du joueur pour cette manche.

Voici un exemple d’exécution d’une manche Ordinateur. Le joueur a choisi le code secret VJBR. Les propositions de l’ordinateur et les réponses du joueur (nombre de pions bien placés (BP) et mal placés (MP) respectivement), sont les suivantes :

| application.Code | BP | MP |
| ---------------- | -- | -- |
| RRRR             | 1  | 0  |
| RBBB             | 1  | 1  |
| RJBJ             | 1  | 2  |
| JRBJ             | 0  | 4  |
| VRJB             | 1  | 3  |
| VJBR             | 4  | 0  |

La fonction retourne 6, qui sera ajouté au score du joueur.

On remarque sur cet exemple (et vous pourrez remarquer sur vos propres exécutions de cette fonction) que la stratégie fait que l’ordinateur détermine le code secret en deux phases : une première phase où il détermine le nombre de pions de chaque couleur du code secret, dans l’ordre croissant des numéros de couleur (4 premières propositions dans l’exemple cidessus) et une deuxième phase où il détermine leur position par permutations des pions dans la proposition précédente. Cette remarque permet d’amélorer l’efficacité de l’algorithme de recherche de la proposition de code suivante (sans modifier la stratégie) ce qui fera l’objet d’une extension.

Ecrire et tester la fonction `mancheOrdinateur`.

### Le programme principal

Ecrire et tester les fonctions de saisie pour le programme principal, puis la fonction `main`.

## Extensions
