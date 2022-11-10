<h1 align="center">Développement initiatique</h1>
<h2 align="center"> SAE 1.1 Jeu de Master Mind</h2><br>
<p align="center">
I.U.T. Montpellier-Sète – Département informatique – BUT 1re année
</p>


## Table of Contents

- [Présentation du jeu](#Présentation-du-jeu-de-Master-Mind-et-du-projet-à-réaliser)
- [Version de base](#Version-de-base)
  - [Les paramètres d’une partie](#Les-paramètres-d’une-partie)
  - [Les deux représentations d’un code](#Les-deux-représentations-d’un-code)
  - [Précisions sur la fin d’une manche](#Précisions-sur-la-fin-d’une-manche)
  - [Outils de base](#Outils-de-base)
  - [Une manche Humain](#Une-manche-Humain)
  - [Fonctions complémentaires sur les codes pour la manche Ordinateur](#Fonctions-complémentaires-sur-les-codes-pour-la-manche-Ordinateur)
  - [Une manche Ordinateur](#Une-manche-Ordinateur)
  - [Le programme principal](#Le-programme-principal)
- [Extensions](#Extensions)


## Présentation du jeu de Master Mind et du projet à réaliser

  <p>Le but de ce projet est de programmer une partie du jeu de <a href="https://fr.wikipedia.org/wiki/Mastermind">MasterMind</a> entre un humain et
l’ordinateur.</p>
  <p>Le Master Mind est un jeu à 2 joueurs. Lors d’une manche d’une partie de Master Mind, l’un
des joueurs est le codeur (ou codificateur) et l’autre le décodeur. Le codeur choisit un code
secret, qui est une séquence d’un certain nombre (4 ou 5 dans les versions classiques du jeu)
de pions de couleur. Le nombre de couleurs possibles est 6 ou 8 dans les versions classiques
du jeu. Le décodeur doit alors déterminer ce code secret.</p>
  <p>Pour cela, il fait des propositions de codes au codeur, qui lui indique pour chacune d’elles le
nombre de pions de la bonne couleur bien placés et mal placés. Par exemple, si le code secret
est (Bleu, Rouge, Jaune, Rouge) et que le décodeur propose (Jaune, Rouge, Rouge, Vert), le
codeur indique 1 bien placé (le pion rouge de rang 1, en considérant que le premier pion est
de rang 0) et 2 mal placés (l’autre pion rouge et le pion jaune).</p>
  <p>La manche se termine quand le décodeur propose un code égal au code secret. Le nombre
de propositions de code faites par le décodeur est alors ajouté au score du codeur. La règle
impose un nombre d’essais maximum (<code>nbEssaisMax</code>) au décodeur (égal au nombre de rangées
sur le plateau). Quand le décodeur atteint le nombre maximum d’essais, <code>nbEssaisMax</code>
est ajouté au score, ainsi qu’un malus lié au dernier code proposé.</p>
  <p>Une partie se compose d’un certain nombre de manches, chaque joueur étant alternativement
codeur et décodeur. Le nombre de manches doit donc être pair pour équilibrer les chances
des 2 joueurs. Le gagnant de la partie est le joueur ayant le plus grand score à la fin de la
partie.</p>
  <p>Vous allez d’abord programmer une version de base du jeu, entre un humain et l’ordinateur.
Vous pourrez ensuite améliorer cette première version à l’aide de plusieurs extensions optionnelles
permettant :</p>

* d’évaluer la stratégie de l’ordinateur, et de la comparer éventuellement à d’autres stratégies
  ;
* d’améliorer la présentation visuelle du jeu ;
* de réécrire le programme en programmation objet, plus appropriée pour gérer les différents
  éléments du jeu ;
* de définir des contre-stratégies pour le joueur humain et l’ordinateur ;
* de définir de nouvelles stratégies pour l’ordinateur.

<p>Il vous est demandé de programmer au minimum la version de base. Pour l’évaluation, il sera
tenu compte des extensions réalisées, mais surtout de la qualité de programmation. Il vous
est donc conseillé de prendre le temps de bien écrire la version de base avant d’aborder les
extensions, parmi lesquelles vous pouvez choisir celles que vous souhaitez programmer, dans
l’ordre que vous préférez.</p>
  <p>Vous rendrez donc au minimum une classe <code>MasterMindBase</code> (version de base), et éventuellement
une classe <code>MasterMindEtendu</code> et des classes correspondant à la version objet.
Si vous avez une version étendue, vous indiquerez en commentaires au début de la classe
<code>MasterMindEtendu</code> les numéros des extensions choisies, ainsi que toutes les explications
nécessaires.</p>
  <p>Un squelette de la classe <code>MasterMindBase</code> vous est donné. Ce squelette contient les en-têtes
et spécifications de toutes les fonctions nécessaires à la version de base. Vous devez écrire
le code de ces fonctions sans en modifier les en-têtes et en suivant scrupuleusement leurs
spécifications, faute de quoi vos fonctions ne passeront pas les tests automatiques qui seront
utilisés pour l’évaluation de votre projet ! Vous pouvez rajouter des fonctions à ce squelette
si vous en éprouvez le besoin, à condition d’en écrire les spécifications et d’écrire aussi le
code de toutes les fonctions demandées, puisque ce sont elles qui seront testées par les tests
automatiques.</p>

## Version de base

### Les paramètres d’une partie

  <p>Avant de commencer la partie, joueur (humain) saisit le nombre de pions du code secret,
noté <code>lgCode</code>, le nombre de couleurs possibles, noté nbCouleurs, l’identité de ces couleurs,
le nombre de manches de la partie, noté nbManches, et le nombre maximum de codes à
proposer (correspondant au nombre de rangées sur le plateau), noté <code>nbEssaisMax</code>.</p>
  <p>Les couleurs sont stockées dans un tableau de caractères <code>tabCouleurs</code> de longueur nbCouleurs
contenant les initiales des noms de couleurs saisis par le joueur. Ce sont ces initiales qui seront
utilisées dans les codes. Les noms de couleurs doivent donc avoir des initiales distinctes.
Les entiers <code>lgCode</code>, nbCouleurs, nbManches et <code>nbEssaisMax</code> doivent être strictement positifs,
et nbManches doit être pair. Ces conditions sont à vérifier au moment de la saisie, et comme
indiqué dans le squelette de code, ce sont des pré-requis implicites de toutes les fonctions
ayant ces données en paramètres.</p>
  <p>Dans les exemples donnés dans le sujet et le squelette, on suppose que <code>nbCouleurs = 6</code>,
<code>lgCode = 4</code> et que les couleurs saisies sont <code>"Rouge", "Bleu", "Jaune", "Vert", "Orange" et
"Noir"</code> dans cet ordre, de sorte que le tableau <code>tabCouleurs</code> contient <code>(’R’, ’B’, ’J’, ’V’, ’O’, ’N’)</code>.</p>

### Les deux représentations d’un code
  <p>Un code est représenté soit par un mot (de type String) de longueur lgCode dont chaque
caractère est un élément de <code>tabCouleurs</code> (pour la saisie ou l’affichage du code), soit par un
tableau d’entiers obtenu à partir de ce mot en remplaçant chaque caractère par son indice
dans <code>tabCouleurs</code>, appelé "numéro" de la couleur correspondante (pour les calculs sur ce
code).</p>
  <p>Pour l’exemple ci-dessus, le code (Bleu, Rouge, Jaune, Rouge) est représenté, à l’écran, par
le mot "BRJR" et, en machine, par le tableau d’entiers (1, 0, 2, 0).</p>

### Précisions sur la fin d’une manche
  <p>Rappelons qu’une manche se termine quand le décodeur propose un code égal au code
secret ou qu’il a fait <code>nbEssaisMax</code> propositions.</p>
  <p>Dans le premier cas (code trouvé), le nombre de propositions de code faites par le décodeur
est ajouté au score du codeur.</p>
  <p>Dans le deuxième cas, on ajoute <code>nbEssaisMax</code> (qui est aussi le nombre de propositions de
code) au score, plus un malus qui dépend de la réponse au dernier code proposé :</p>

```text
rep[nbEssaisMax − 1] = (nbBienPlaces, nbMalPlaces)
```
  <p>On considèrera dans le programme (dans les fonctions mancheHumain et mancheOrdinateur) :</p>

```text
malus = nbMalPlaces + 2 × (lgCode − (nbBienPlaces + nbMalPlaces))
```
<p>Ce qui incitera le décodeur à faire une dernière proposition avec un maximum de pions de la
bonne couleur...</p>

### Outils de base
  <p>Vous allez écrire des fonctions utiles pour votre programme de jeu : d’abord des fonctions
classiques sur les tableaux, puis des fonctions plus spécifiques pour la gestion des codes
sous forme de mots ou de tableaux d’entiers.</p>
  <p>Le nombre de pions mal placés dans un code par rapport au code secret est le nombre d’éléments
communs aux deux tableaux d’entiers indépendamment de leur position, qui ne sont
pas bien placés. Le nombre d’éléments communs aux deux tableaux se calcule efficacement
à l’aide de leurs tableaux de fréquence (le tableau de fréquence d’un code contient à l’indice i
le nombre de pions de ce code de la couleur numéro i).</p>
<p>Ecrire et tester les fonctions de la rubrique Outils de base.</p>

### Une manche Humain
<p>On appelle manche Humain une manche au cours de laquelle c’est le joueur humain qui
"joue", c’est-à-dire qui est le décodeur. L’ordinateur choisit un code secret sous forme d’un tableau d’entiers choisis aléatoirement entre 0 et nbCouleurs − 1. Ensuite, pour chaque code
proposé par le joueur sous forme de mot, il vérifie que ce code est correctement formé (s’il
ne l’est pas, le joueur doit le re-saisir jusqu’à ce qu’il le soit), puis il transforme le mot en un
tableau d’entiers et affiche à l’écran les nombres de pions bien et mal placés dans le code
proposé par le joueur.</p>
<p>Ecrire et tester la fonction mancheHumain.</p>

### Fonctions complémentaires sur les codes pour la manche Ordinateur
<p>Après quelques fonctions utiles pour l’affichage et la saisie, on vous propose d’écrire les fonctions
gérant la stratégie de l’ordinateur.</p>
<p>La stratégie de l’ordinateur pour trouver le code secret S est la suivante. Il propose tous les
codes candidats possibles dans un certain ordre, sauf ceux qui ne sont pas compatibles avec
les propositions et les réponses précédentes du joueur, c’est-à-dire telles que si le code à
proposer était le code secret, le joueur n’aurait pas répondu exactement les mêmes réponses
aux propositions précédentes. Autrement dit, si on compare un code à proposer candidat C
avec un code déjà proposé O et que la réponse (nombre de pions bien et mal placés) est
différente de la réponse obtenue en comparant S et O, alors on sait que le code C ne peut
pas être le code secret S et l’ordinateur ne le propose pas.</p>
<p>Une telle stratégie peut sembler assez inefficace a priori, ce sera à vous d’en juger par expérimentation
(et calcul dans l’extension 3.2). Pour pouvoir déterminer la compatibilité d’une
proposition de code, vous définirez la matrice cod, de dimensions <code>nbEssaisMax × lgCode</code>, qui
contient l’historique des propositions de codes. Vous définirez également la matrice rep de
dimensions <code>nbEssaisMax × 2</code> contenant les réponses du joueur. Pour toute ligne i :</p>

* <code>cod[i]</code> contient la (i + 1)<sup><small>ème</small></sup> proposition de code ;
* <code>rep[i][0]</code> contient le nombre de pions bien placés à cette proposition ;
* <code>rep[i][1]</code> contient le nombre de pions mal placés à cette proposition.
<p>L’ordre choisi sur l’ensemble des codes représentés par des tableaux d’entiers est l’ordre
lexicographique. Késako ?</p>
<p>Etant donnés deux tableaux t<sub><small>1</small></sub> et t<sub><small>2</small></sub> de longueur <code>lgCode</code> contenant des entiers naturels inférieurs
à nbCouleurs, t<sub><small>1</small></sub> est inférieur à t<sub><small>2</small></sub> dans l’ordre lexicographique si t<sub><small>1</small></sub>[i] < t<sub><small>2</small></sub>[i], où
i est le plus petit indice tel que t<sub><small>1</small></sub>[i] , t<sub><small>2</small></sub>[i]. Dans l’exemple ci-dessus, si t<sub><small>1</small></sub> = (3, 2, 3, 4)
et t<sub><small>2</small></sub> = (3, 2, 4, 0), t1 est inférieur à t<sub><small>2</small></sub> car le plus petit indice i tel que t<sub><small>1</small></sub>[i] , t<sub><small>2</small></sub>[i] est 2 et
t<sub><small>1</small></sub>[2] = 3 < 4 = t<sub><small>2</small></sub>[2]. Cet ordre correspond à l’ordre usuel sur les entiers naturels inférieurs
à nbCouleurs<sup><small>lgCode</small></sup> si on voit un code comme l’écriture d’un tel entier en base <code>nbCouleurs</code> sur
<code>lgCode</code> chiffres, en complétant par des 0 à gauche. C’est aussi en ordre lexicographique
que sont rangés les mots dans un dictionnaire, en considérant l’ordre alphabétique entre les
lettres.</p>
<p><b>Remarque importante</b> : La fonction <code>passeCodeSuivantLexico</code> modifie le paramètre <code>cod1</code>
en le remplaçant par le code suivant. De même, la fonction <code>passePropSuivante</code> remplit une
ligne de la matrice <code>cod</code> donnée en paramètre. Donc, contrairement à ce qui était souvent fait en cours et en TD, vous allez définir des fonctions modifiant les tableaux (ou matrices) donnés
en paramètres. En Java, un tableau transmis en paramètre d’une fonction f<sub><small>2</small></sub> et modifié dans
f<sub><small>2</small></sub> est aussi modifié dans la fonction f<sub><small>1</small></sub> appelant f<sub><small>2</small></sub>, ce qui est justement l’effet souhaité ici.</p>
<p>Ecrire et tester les fonctions complémentaires sur les codes pour la manche Ordinateur.</p>
<p></p>


### Une manche Ordinateur
<p>Une manche Ordinateur est une manche au cours de laquelle c’est l’ordinateur qui "joue"
(décode). Le joueur humain choisit un code "dans sa tête", mais ne le saisit pas au clavier.
L’ordinateur fait une suite de propositions de codes affichées sous forme de mots, et pour
chacune d’elles, il demande au joueur humain le nombre de pions bien et mal placés et vérifie
que les réponses sont correctement formées (si elles ne le sont pas, le joueur doit les re-saisir
jusqu’à ce qu’elles le soient). Les propositions de code de l’ordinateur suivent la stratégie
définie ci-dessus.</p>
<p>Si l’ordinateur atteint le dernier code possible dans l’ordre lexicographique sans trouver le
code secret, c’est que le joueur s’est trompé dans au moins une réponse, ou a volontairement
triché. Dans ce cas, le programme n’ajoute aucun point au score du joueur pour cette manche.</p>
<p>Voici un exemple d’exécution d’une manche Ordinateur. Le joueur a choisi le code secret
VJBR. Les propositions de l’ordinateur et les réponses du joueur (nombre de pions bien placés
(BP) et mal placés (MP) respectivement), sont les suivantes :</p>

| Code | BP  | MP  |
|------|-----|-----|
| RRRR | 1   | 0   |
| RBBB | 1   | 1   |
| RJBJ | 1   | 2   |
| JRBJ | 0   | 4   |
| VRJB | 1   | 3   |
| VJBR | 4   | 0   |

<p>La fonction retourne 6, qui sera ajouté au score du joueur.</p>
<p>On remarque sur cet exemple (et vous pourrez remarquer sur vos propres exécutions de
cette fonction) que la stratégie fait que l’ordinateur détermine le code secret en deux phases :
une première phase où il détermine le nombre de pions de chaque couleur du code secret,
dans l’ordre croissant des numéros de couleur (4 premières propositions dans l’exemple cidessus)
et une deuxième phase où il détermine leur position par permutations des pions dans
la proposition précédente. Cette remarque permet d’amélorer l’efficacité de l’algorithme de
recherche de la proposition de code suivante (sans modifier la stratégie) ce qui fera l’objet
d’une extension.</p>
<p>Ecrire et tester la fonction <code>mancheOrdinateur</code>.</p> 
<p></p>


### Le programme principal
<p>Ecrire et tester les fonctions de saisie pour le programme principal, puis la fonction <code>main</code>.</p>

## Extensions
