import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class TutorialPanel extends JPanel {
	
	private BufferedImage image;
	
	public TutorialPanel() {
		//URL url = this.getClass().getResource("\test.png");
		//System.out.println(url);
		try {
			image = ImageIO.read(new File("C:/Users/X450JF/workspace/2911proj/test.png"));
		} catch (IOException ex) {
			//no handling
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
