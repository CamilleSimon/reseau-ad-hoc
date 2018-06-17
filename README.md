# Graphes dynamiques et réseaux mobiles ad hoc

## Rappel du sujet
*n* stations autonomes se déplacent dans un environnement de taille *L* x *l* dépourvu de tout obstacle. Ces stations sont toujours allumées et sans limite d'énergie. Lorsque deux stations sont à une distance inférieure ou égale à *d*, elles peuvent communiquer.

## Analyse
### Algorithme de diffusion

Prenons un environnement de taille 1000 x 1000, faisons varier le nombre de station afin d'observer le pourcentage de réception en fonction de la densité du réseau. Afin d'avoir une moyenne représentitive, chaque résultat est la moyenne de 100 simulation.

![Pourcentage de réception en fonction de la densité](https://github.com/CamilleSimon/reseau-ad-hoc/blob/master/fonction-densite.png)

Afin d'assurer la propagation de l'information, plusieurs stratégies peuvent être mise en place :
- Avant de diffuser son message, la station peut attendre d'être en contact avec au moins une station n'ayant pas reçu le message
- De la même façon, une station peut attendre d'être en lien avec un nombre minimum de station, quelque soit leurs états

### Distribution spatiale des stations



### Références
- [Gnuplot Online](http://gnuplot.respawned.com/)
- [Documentation GraphStream](http://graphstream-project.org/doc/)
- [Exemple de déplacement de noeuds avec GraphStream](https://www.javatips.net/api/gs-test-master/src/org/graphstream/ui/viewer/test/TestMovingNodes.java)
