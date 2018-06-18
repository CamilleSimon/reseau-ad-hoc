import java.io.PrintWriter;

public class Main {

	public static void main(String[] args) throws Exception{
		//Fenêtre de paramètrage
		Parameters p = new Parameters();
		//TODO lancer la fenêtre de simulation dans l'action performed du bouton "valider"
		while(p.L == 0) {System.out.println(" ");}
		//Affichage des paramètres transmit par la fenêtre de paramètrage
		System.out.println(p.l + " " + p.L + " " + p.n + " " + p.hide);
		//Si le mode sélectionné est graphique
		if(p.hide == false) {
			System.out.println("Mode graphique - Simulation prête");
			Simulation s = new Simulation(p.L, p.l, p.n, p.d, p.hide);
			System.out.println("Densité : " + new Double(p.n)/(p.L*p.l) + ", diffusion : " + s.received * 100 / p.n + "%");
		}
		//Si le mode sélectionné est analyse
		else {
			System.out.println("Mode analyse - Démarrage des tests");
			PrintWriter writer = new PrintWriter("gnuplot.txt", "UTF-8");
			for(int n = 10; n <= 250; n += 10) {
				double percentil = 0;
				int repetition = 100;
				for(int i = 1; i <= repetition; i++) {
					Simulation s = new Simulation(p.L, p.l, n, p.d, p.hide);
					percentil += s.received;
				}
				double average = percentil / repetition;
				System.out.println("Densité : " + new Double(n)/(p.L*p.l)*100000 + ", diffusion : " + average * 100 / n + "%");
				writer.println(new Double(n)/(p.L*p.l) * 100000 + " " + average * 100 / n);
			}
			writer.close();
		}
		System.out.println("Fin de la simulation");
	}
}
