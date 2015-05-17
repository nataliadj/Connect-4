import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class MenuFrame extends JFrame {
	public MenuFrame (String title) {
		super(title);
		
		setLayout(new BorderLayout());
		
		final JTextArea textArea = new JTextArea();
		JButton button = new JButton ("Click me");
		
		Container c = this.getContentPane();
		
		c.add(button, BorderLayout.CENTER);
		c.add(textArea, BorderLayout.NORTH);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append("Hello \n");
				
			}
			
		});
		
	}
}
