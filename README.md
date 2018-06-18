# Graphes dynamiques et réseaux mobiles ad hoc

## Rappel du sujet
*n* stations autonomes se déplacent dans un environnement de taille *L* x *l* dépourvu de tout obstacle. Ces stations sont toujours allumées et sans limite d'énergie. Lorsque deux stations sont à une distance inférieure ou égale à *d*, elles peuvent communiquer.

## Analyse
### Algorithme de diffusion

Prenons un environnement de taille 1000 x 1000, faisons varier le nombre de station afin d'observer le pourcentage de réception en fonction de la densité du réseau. Afin d'avoir une moyenne représantitive, chaque résultat est la moyenne de 100 simulations.

![Pourcentage de réception en fonction de la densité](https://github.com/CamilleSimon/reseau-ad-hoc/blob/master/fonction-densite.png)

Afin d'assurer la propagation de l'information, plusieurs stratégies peuvent être mises en place :
- Avant de diffuser son message, la station peut attendre d'être en contact avec au moins une station n'ayant pas reçu le message.
- De la même façon, une station peut attendre d'être en lien avec un nombre minimum de station, quel que soit leurs états.

### Distribution spatiale des stations

Construisons un maillage de notre environnement, cela consiste à découper l'espace en cellule. À chaque itération de la simulation, nous comptons le degré moyen des stations dans cette cellule. À la fin de la simulation, on fait la moyenne. On obtient le graphique suivant : 

![Distribution des degrés des stations](https://github.com/CamilleSimon/reseau-ad-hoc/blob/master/moyenne-degree.PNG)

On observe que la distribution des degrés est inégale sur l'ensemble de l'environnement ; attention, ce graphique ne représente pas la distribution des stations dans l'espace, mais bien la moyenne des connexions. Ainsi, une station a plus de chance de transmettre son message au centre de l'espace, car la probabilité de créer des connexions est plus importante.

Dans l'article [The Spatial Node Distribution of the Random Waypoint Mobility Model](http://data.bettstetter.com/publications/bettstetter-2002-wman-rwp.pdf) p.3, il est montré que le phénomène est encore plus important si l'environnement est sphérique. Il pourrait être intéressant dans une évolution de la simulation de proposer des environnements de formes et de topologies différentes.

### Références
- [Gnuplot Online](http://gnuplot.respawned.com/)
- [Documentation GraphStream](http://graphstream-project.org/doc/)
- [Exemple de déplacement de noeuds avec GraphStream](https://www.javatips.net/api/gs-test-master/src/org/graphstream/ui/viewer/test/TestMovingNodes.java)
