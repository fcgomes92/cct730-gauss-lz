package gauss.control;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ControlDraw {
	public void drawImage(JPanel p, BufferedImage bi){
		Graphics2D g = (Graphics2D)p.getGraphics();
		g.drawImage(bi, 0, 0, null);
		g.dispose();
	}
}
