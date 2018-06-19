import java.util.Random;

import org.graphstream.graph.Node;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.geom.Vector3;

/**
 * Classe Movement. Permet de gérer le déplacement des noeuds dans la simulation.
 */
public class Movement {
	public Point3 pos = new Point3();		//Position du point sur la simulation
	public Point3 target = new Point3();		//Position de la destination
	public double speed;				//Vitesse de déplacement du point
	protected Random random = new Random();		
	
	/**
	 * Constructeur d'un Movement.
	 * @param node Le noeud concerné
	 * @param L Longueur de l'envorinnement
	 * @param l Largeur de l'environnement
	 * @param speed La vitesse de déplacement des noeuds 
	 */
	public Movement(Node node, int L, int l, double speed) {
		this.speed = speed;
		pos.set(random.nextDouble()*L, random.nextDouble()*l, 0);
		target.set(random.nextDouble()*L, random.nextDouble()*l, 0);
		node.addAttribute("xy", pos.x, pos.y);
	}
	
	/**
	 * Calcule la distance entre la position du Movement courant et un autre Movement passé en paramètre.
	 * @param other  
	 */
	public  double distance(Movement other) {
		return pos.distance(other.pos);
	}
	
	/**
	 * Déplace le noeud vers sa destination.
	 * @param node Le noeud concerné
	 * @param L Longueur de l'envorinnement
	 * @param l Largeur de l'environnement
	 */
	public  void move(Node node, int L, int l) {
		Vector3 dir = direction(pos, target);
		dir.normalize();
		dir.scalarMult(speed);
		pos.x += dir.data[0];
		pos.y += dir.data[1];
		
		node.setAttribute("xy", pos.x, pos.y);
		//Si le noeud est proche de sa cible, on détermine une nouvelle destination
		if(target.distance(pos) < speed) {
			target.set(random.nextDouble()*L, random.nextDouble()*l);
		}
	}
	
	/**
	 * Détermine le vecteur direction entre la position et la cible.
	 * @param pos La position courante
	 * @param target La destination
	 */
	public Vector3 direction(Point3 pos, Point3 target) {
		return new Vector3(target.x - pos.x, target.y - pos.y, targt.z - pos.z);
	}
	
	public String toString() {
		return "pos "+pos+" target "+target;
	}
}
