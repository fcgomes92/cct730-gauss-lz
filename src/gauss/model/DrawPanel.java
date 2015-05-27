package gauss.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public void setImage(BufferedImage bi){
		this.image = bi;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(this.image!=null){
			g.drawImage(image, 0, 0, null);
		}
	}
}
