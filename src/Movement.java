import java.util.Random;

import org.graphstream.graph.Node;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.geom.Vector3;

public class Movement {
	public Point3 pos = new Point3();
	public Point3 target = new Point3();
	public double speed;
	protected Random random = new Random();

	public Movement(Node node, int L, int l, double speed) {
		this.speed = speed;
		pos.set(random.nextDouble()*L, random.nextDouble()*l, 0);
		target.set(random.nextDouble()*L, random.nextDouble()*l, 0);
		node.addAttribute("xy", pos.x, pos.y);
	}

	public  double distance(Movement other) {
		return pos.distance(other.pos);
	}

	public  void move(Node node, int L, int l) {
		Vector3 dir = direction(pos, target);
		dir.normalize();
		dir.scalarMult(speed);
		pos.x += dir.data[0];
		pos.y += dir.data[1];
		
		node.setAttribute("xy", pos.x, pos.y);
		
		if(target.distance(pos) < speed) {
			target.set(random.nextDouble()*L, random.nextDouble()*l);
		}
	}
	
	public Vector3 direction(Point3 a, Point3 b) {
		return new Vector3(b.x - a.x, b.y - a.y, b.z - a.z);
	}
	
	public String toString() {
		return "pos "+pos+" target "+target;
	}
}