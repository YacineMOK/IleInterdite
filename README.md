# IleInterdite :
## Fonctionnalité : 
Dans ce jeu, nous avons respecté et implémenté toutes les consignes citées dans l'énoncé. En ce qui concerne la partie 4, nous avons choisi d'implémenter les personnages particuliers et automatiquement un échange de clé s'est mis en oeuvre. 

## Points Créatifs:
- Implémentation d'un fenêtre de début 'Menu' qui permet au joueurs de choisir le nombre de joueurs (de 1 à 4 joueurs).
- Ajout de divers *controllers* : 
        ->  **Exit :** quitter la partie 
        -> **Rejouer :** refaire une partie en cas de perte de la 
         première
- Ajout d'un *tutoriel*, qui permet au joueur, pendant la partie, d'accéder aux règles du jeu ainsi qu'aux fonctionnalités de contrôle.
- Création d'un graphisme similaire à celui du vrai jeu (tuile du jeu, artefact,...)
- Insertion d'une musique de fond (Bande sonore du film 'Pirate des Caraïbes') 

## Conception du jeu (répartition des tâches) : 
Chacun avait une branche spécifique du MVC, Lynda pour le Model, Yacine pour la Vue et Kevin pour le Controller. 

Pour autant au début, nous nous sommes répartie le boulot équitablement sans spécialité entre l'étape 1 (On va se couler douce) à l'étape 3 (Sa place est dans un musée).

## Tâches individuelles: 

### Lynda : 
* Classes de model et leurs optimisations au fur à mesure. 
* La fonctionnalité fin de partie.
* Fourniture des images des zones.
* Le fond sonore.
* Les personnages particuliers (hormis navigateur et messager) et  Répartition des rôles en début de jeu. 
* Tutoriel.

### Kevin :
 * Bouton (Rejouer, Ramasse) et ses liaisions (système d'artefactes et clés) avec le Model. 
 * Design pattern Oberver/Observable
 * Fenêtre du Menu. 

### Yacine :
 * Création de la fenêtre principale ainsi que les classes du package Vue (sauf ClassFrame et Menu)
 * Bouton ("fin de tour")
 * Les images des options (Coffre fort et Artefacts) mais aussi des tours restants et des joueurs.
 * La créations des png "C'est à toi" suivant la couleur du joueur en cours ainsi que les rôles.
 * Le choix des dimensions et la gestions des Layout ainsi que le positionnement des JPanel.
 * Création d'un conteneur graphique qui contiens tous les objets graphique cités ci-dessus
 * Implémentation de la classe OptionsView qui agira sur les bouton graphique (Coffre Fort et Artefacts Ramassés)
 
## Tâches en collaboration et travail d'équipe : 
**Lynda et Yacine:** Implémentation du controller Mouse. 

**Lynda et Kevin:** Rôle Navigateur et Messager. 

**Yacine et Kevin:** Passage des Tours (interraction Controller/Vue). 

Chacun a aussi clarifié la partie dont il s'occupait. 


> "Globalement, nous avons tous participé à toutes les partie soit suite aux échanges d'idées, implémentation ou encore lors du débogage."


## Difficultés :
Au début, on a mal construit notre code. On a dû le restructurer à la fin de l'étape 2. La construction de l'interfâce graphique avec les différents Layout. L'ajout des KeyListener et des MouseAdapter. Faire des réunions régulières et l'utilisation du GitLab (qu'on a finalement abandonné).

## Décomposition MVC
#### Model
- Grille
- Case
- EtatZone (Enum)
- Joueur 
- CoffreFort
- Artefact 
- Cle 
- Element (Enum)
-Rôle 

#### Vue
- Vue (JFrame: cette classe est notre fenêtre de jeu) 
- RootPane (JPanel: Support du plateau) 
- ContentPane (JPanel: visuel du plateau)
- Action Restantes (JPanel: visuel des actions en bas à droite)
- Tour (JPanel: Indique tour en haut)
- Options (JPanel: visuel du coffre fort et des artefacts à gauche)
- OptionsView (JPanel: visuel des clés des joueurs et des artefacts ramassés après avoir appuyé sur le coffre ou les artefacts des Options)
- ClassFrame (JFrame: fenêtre du menu en début de partie) 
- Menu (JPanel: visuel de fond du menu) 

### Controller
- Bouton (JButton: affiche les boutons "Fin de Tour" (actionne fin de tour), "Trésor" (ramasse un artefact" et "Rejouer" (relance une partie à nouveau randomisée))
- Echange (KeyListener: permet au Messager l'action d'échange) 
- Exit (KeyListener: permet de quitter à tout moment le jeu)
- Mouse (MouseAdapter: permet au joueur de changer de case et d'autres fonctionnalités) 
- OptionsActions (MouseAdapter: permet d'ouvrir le visuel d'OptionsView)
