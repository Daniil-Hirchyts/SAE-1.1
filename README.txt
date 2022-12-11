IUT Montpellier-Sète – Département informatique – BUT 1re année
HIRCHYTS Daniil
&
OULMEKKI Youssera

2022-2023
-------------------------------------------------------------

Le projet consiste à programmer une version du jeu Master Mind, où un joueur est le codeur et l'ordinateur est le décodeur et l'inverse. Le codeur choisit un code secret composé d'une séquence de broches de couleur. Le décodeur doit alors essayer de deviner le code en proposant des codes, et le codeur indique pour chaque proposition si les couleurs sont correctes et dans la bonne position. La partie se termine lorsque le décodeur trouve le code ou atteint le nombre maximal d'essais. L'objectif est de programmer une version de base du jeu, puis d'ajouter des extensions facultatives.

-------------------------------------------------------------

Travail effectué 

Le jeu complet, à l'exception d'une extension 3.7 qui est fait 50%

-------------------------------------------------------------

Pour lancer 3.5 il faut utiliser IntelliJ IDEA et JavaFX.
Impossible de lancer les application JavaFX via terminal sans JavaFX package.

srs/main/java/application/MasterMind.java

-------------------------------------------------------------

Répartition des tâches 

HIRCHYTS Daniil : version basique + toutes extensions
&
OULMEKKI Youssera : version basique


VB  - 6h
3.1 - 30min
3.2 - 1h
3.3 - 2h
3.4 - 35h
3.5 - 4h
3.6 - 20min
3.7 - 4h (pas fini // ne marche pas comme il faut)

-------------------------------------------------------------

Explication d'extensions

- 3.1 : 

Methode affichePlateau

════════════════════════════════════ Plateau de jeux ════════════════════════════════════
rrrr - 1 bien placé(s), 0 mal placé(s)
jjjj - 2 bien placé(s), 0 mal placé(s)
bbbb - 0 bien placé(s), 0 mal placé(s)
═════════════════════════════════════════════════════════════════════════════════════════

- 3.2 :

Methodes : 
* afficheErreurs
* saisirCodeAleatoire
* verifCode

Erreur : le code proposé est incompatible avec les réponses précédentes

- 3.3

Methodes :
* nbCoupsStat
* statsMasterMindIA

Nombre de codes secrets possibles : 256
Nombre maximum de propositions de codes : 4
Codes secrets réalisant ce maximum : 226
Moyenne des nombres de propositions de codes : 3.859375

- 3.4

Pour placer la couleur il faut cliquer sur la couleur et sur la case blanche.

Noire - Bien Place
Blanc - Mal Place
Gris - Aucun

Pour la manche il faut choisir le code avant de jouer.

- 3.5

POO classique

- 3.6 

Ordinateur commence a proposer les codes par code aléatoire, modifications sont dans mancheOrinateur

- 3.7 

Pas fini, essaie d'application stratégie cfc



