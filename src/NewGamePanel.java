import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class NewGamePanel extends JPanel {
	JPanel menu;
	
	public NewGamePanel() {
		this.setLayout(new GridBagLayout());
		//this.setOpaque(false);
		initMenu();
		this.add(menu, new GridBagConstraints());
	}
	
	private void initMenu() {
		menu = new JPanel();
		//menu.setLayout(new BorderLayout());
		menu.setPreferredSize(new Dimension(400,200));
		menu.setBackground(Color.CYAN);
		JButton button = new JButton("but");
		menu.add(button);
	}
}
