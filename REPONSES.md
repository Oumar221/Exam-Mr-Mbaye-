ge

- Matricule :
- Nom et prénom : Oumar Diallo
- N (deux derniers chiffres du matricule) : 0

> Rédigez vos justifications **avec vos propres mots**. Elles serviront de base à l'échange oral.

## Exercice 1 — A. Affichage prédit

Écrivez ci-dessous, entre les deux lignes de trois accents graves, **une ligne par ligne affichée**, dans l'ordre.
Ne modifiez pas les lignes de trois accents graves.

```text
A vehicule
F null 0
E moto 100
Moto2
vehicule
moto
C3
C2
D-vehicule
A vehicule
F null 0
E moto 100
true false
true
2
```

## Exercice 1 — A. Justifications

1. Ligne qui commence par « F » : le constructeur de Vehicule appelle demarrer(), qui est la version redéfinie dans Moto (liaison dynamique). À ce moment, les attributs de Moto ne sont pas encore initialisés : nom vaut null et cylindree vaut 0.
2. Ligne « C3 » : la surcharge est choisie à la compilation selon le type déclaré Vehicule, donc comparer(Vehicule). À l'exécution, c'est la redéfinition de Moto qui s'exécute.
3. Ligne « C2 » : l'argument est de type Moto, donc la compilation choisit comparer(Moto). Moto ne la redéfinit pas, c'est la version de Vehicule qui s'exécute.
4. Ligne qui commence par « D- » : une méthode static n'est pas redéfinie mais masquée. L'appel dépend du type déclaré de v (Vehicule).
5. Pourquoi des lignes « A », « F », « E » apparaissent avant « true false » : la chaîne passée à println est calculée entièrement avant l'affichage. Le new Moto() qu'elle contient affiche A, F, E pendant ce calcul.

## Exercice 1 — B. Transtypage

Colonne « Compile ? » : `oui` ou `non`. Colonne « Exécution » : `OK`, `exception` (en précisant laquelle), ou `-` si le code ne compile pas.

| n° | Compile ? | Exécution | Justification |
|----|-----------|-----------|---------------|
| 1  | oui | OK | Moto vers Vehicule : surclassement implicite |
| 2  | non | - | Vehicule vers Moto demande un cast explicite |
| 3  | oui | OK | l'objet réel est une Moto |
| 4  | oui | exception ClassCastException | cast autorisé depuis Object, mais l'objet réel est un Camion |
| 5  | non | - | Moto et Camion sont des classes sœurs : types inconvertibles |
| 6  | oui | exception ClassCastException | cast autorisé depuis Object, mais un Camion n'est pas un String |
| 7  | non | - | Object n'a pas de méthode type() |
| 8  | oui | OK | un Camion est un Vehicule, type() retourne "Vehicule" |
