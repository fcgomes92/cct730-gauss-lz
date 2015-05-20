package gauss.control;

import gauss.view.ViewGauss;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControlGauss {
	
	private ViewGauss mainViewGauss;
	private ControlFile controlFile;
	private ControlDraw controlDraw;
	
	private BufferedImage imagemOriginal;
	private BufferedImage imagemAux;
	private BufferedImage imagemAlterada; 
	
	public ControlGauss() throws IOException{
		this.mainViewGauss = new ViewGauss(this);
		this.controlFile = new ControlFile();
		this.controlDraw = new ControlDraw();
	}
	
	public void exit(JFrame f){
		f.dispose();
		System.exit(0);
	}
	
	public void openImage(JFrame f, JPanel p){
		System.out.println("[LOG] Abrindo a imagem...");
		try {
			this.imagemOriginal = this.controlFile.openImage(f);
			this.controlDraw.drawImage(p, this.imagemOriginal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
