package gauss.control;

import gauss.model.DrawPanel;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JScrollPane;

public class ControlDraw {
	public void drawImage(JScrollPane sp, DrawPanel p, BufferedImage bi){
//		Graphics g = p.getGraphics();
//		g.drawImage(bi, 0, 0, null);
//		p.paintComponents(g);
		sp.setPreferredSize(new Dimension(bi.getWidth(),bi.getHeight()));
		p.setPreferredSize(new Dimension(bi.getWidth(),bi.getHeight()));
		p.setImage(bi);
		p.repaint();
	}
}
