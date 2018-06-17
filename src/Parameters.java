import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Parameters extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panel = new JPanel();
	private JPanel parameters = new JPanel();
	private JPanel buttons = new JPanel();
	
	public int L = 0;
	public int l = 0;
	public double d = 0.0;
	public int n = 0;
	public Boolean hide = false;

	public Parameters() {
		
		this.setTitle("Paramétrage du réseau");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		parameters.setLayout(new GridLayout(0, 2));
		
		JTextField largeurText = new JTextField("1000");
		parameters.add(new JLabel(" Largeur de l'environnement (L) : "));
		parameters.add(largeurText);
		
		JTextField longueurText = new JTextField("1000");
		parameters.add(new JLabel(" Longeur de l'environnement (l) : "));
		parameters.add(longueurText);
		
		JTextField distanceText = new JTextField("125");
		parameters.add(new JLabel(" Distance maximale de connexion (d) : "));
		parameters.add(distanceText);
		
		JTextField nbText = new JTextField("100");
		parameters.add(new JLabel(" Nombre de stations (n) : "));
		parameters.add(nbText);
		
		JComboBox<Boolean> test = new JComboBox<Boolean>();
		test.addItem(true);
		test.addItem(false);
		parameters.add(new JLabel(" Mode analyse ? : "));
		parameters.add(test);
		
		JButton valider = new JButton("Valider");
		JButton annuler = new JButton("Annuler");
		
		valider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				L = Integer.parseInt(largeurText.getText());
				l = Integer.parseInt(longueurText.getText());
				d = Double.parseDouble(distanceText.getText());
				n = Integer.parseInt(nbText.getText());
				hide = (Boolean) test.getSelectedItem();
				close();
			}
		});
		
		annuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		
		buttons.add(valider);
		buttons.add(annuler);
		
		panel.add(parameters);
		panel.add(buttons);
		
		this.setContentPane(panel);
		
		this.pack();
		this.setMinimumSize(new Dimension(this.getWidth(), this.getHeight()));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	private void close() {
		this.dispose();
	}

}
