import java.util.ArrayList;
import java.util.Iterator;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;

public class Simulation {

	protected static final double MAX_SPEED = 1;	//La vitesse TODO ajouter aux paramètre modifiable ?
	protected static final long MAX_STEPS = 5000;	//Durée de la simulation TODO ajouter un deuxième arrêt au cas où la diffusion est terminé
	
	protected boolean loop = true;					
	protected long steps = 0;
	
	protected int n;								//Le nombre de stations
	protected double d;								//
	protected int L;								//Longueur de l'environnement (width)
	protected int l;								//Hauteur de l'environnement (height)
	
	/*
	 * Générateur de simulation
	 */
	public Simulation(int L, int l, int n, double d) {
//		sleep(30000);	// Wait 30sec.
		
		Graph graph = new SingleGraph("Ad Hoc Network");
		
		//Les sprites servent à empêcher GraphStream de redimentionner en permanence l'affichage
		SpriteManager sm = new SpriteManager(graph);
		Sprite s1 = sm.addSprite("S1");
		Sprite s2 = sm.addSprite("S2");
		Sprite s3 = sm.addSprite("S3");
		Sprite s4 = sm.addSprite("S4");
		
		s1.setPosition(0, 0, 0);
		s2.setPosition(0, l, 0);
		s3.setPosition(L, l, 0);
		s4.setPosition(L, 0, 0);
		
		//Paramètre de l'interface GraphStream
		graph.addAttribute("ui.title", "GraphStream: Moving Nodes");
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		graph.addAttribute("ui.log", "movingFPS.log");
		graph.addAttribute("ui.stylesheet", styleSheet);
		graph.addAttribute("ui.fps", 70);
		graph.display(false);
		
		//Création des noeuds
		for(int i=0; i<n; i++) {
			Node node = graph.addNode(String.format("%d", i));
			node.addAttribute("mov", new Movement(node, L, l, MAX_SPEED));
		}
		
		//Coeur de la simulation
		while(loop) {

			for(Node node: graph) {
				Movement mov = node.getAttribute("mov");
				removeInvalidEdges(node, d);				//Retire les arrêtes
				addCloseEdges(node, d);						//Ajoute les arrêtes
				mov.move(node, L, l);						//Déplace les noeuds
			}
			
			//Pause de 5 ms
			sleep(5);
			steps += 1;
			
			if(steps >= MAX_STEPS) loop = false;
		}
		
		graph.removeAttribute("ui.log");
		//System.exit(0);
	}
	
	/**
	 * Retire les arêtes entre les noeuds si la distance euclidienne entre eux est supérieur à d
	 * @param node 
	 * @param d
	 */
	protected  void removeInvalidEdges(Node node, double d) {
		Movement mov = node.getAttribute("mov");
		Iterator<? extends Node> neighbors = node.getNeighborNodeIterator();
		ArrayList<Edge> willDieSoon = new ArrayList<Edge>();

		while(neighbors.hasNext()) {
			Node neighbor = neighbors.next();
			Movement nmov = neighbor.getAttribute("mov");
			if(mov.distance(nmov) > d) {
				willDieSoon.add(node.getEdgeBetween(neighbor.getIndex()));
			}
		}
		
		for(Edge edge: willDieSoon) node.getGraph().removeEdge(edge.getIndex());
	}
	
	/**
	 * Ajoute des arêtes entre les noeuds dont la distance euclidienne est inférieur à d
	 * @param node
	 * @param d
	 */
	protected  void addCloseEdges(Node node, double d) {
		Movement mov = node.getAttribute("mov");
		
		for(Node other: node.getGraph()) {
			if(other != node && (!node.hasEdgeBetween(other.getIndex()))) {
				Movement omov = other.getAttribute("mov");
				if(mov.distance(omov) <= d) {
					node.getGraph().addEdge(String.format("%s_%s", node.getId(), other.getId()), node.getIndex(), other.getIndex());
				}
			}
		}
	}
	
	/**
	 * Fonction de mise en pause du thread conserné
	 * @param ms
	 */
	protected  void sleep(long ms) {
		try { Thread.sleep(ms); } catch(InterruptedException e) {}
	}
	
	/**
	 * Style 
	 */
	protected static final String styleSheet = 
		"sprite { fill-color: white; size: 3px; }" +
		"node { fill-color: #AAA; size: 5px; stroke-mode: plain; stroke-color: white; stroke-width: 1px; }" +
		"edge { fill-color: #0003; }";
}