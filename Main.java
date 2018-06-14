public class Main {

	public static void main(String[] args) {
		Parameters p = new Parameters();
		while(p.l == 0) {System.out.println(" ");}
		new Simulation(p.L, p.l, p.n, p.d);
	}
}
