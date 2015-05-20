package gauss.control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ControlFile {
	private JFileChooser fileChooserOpen;
	private JFileChooser fileChooserSave;
	
	public ControlFile(){
		this.fileChooserOpen = new JFileChooser();
		this.fileChooserSave = new JFileChooser();
	}
	
	public BufferedImage openImage(JFrame jf) throws IOException{
		File f = null;
		int chooseReturn = 0;
		BufferedImage bi = null;
		chooseReturn = this.fileChooserOpen.showDialog(jf, "OK");
		if(chooseReturn == JFileChooser.APPROVE_OPTION){
			f = this.fileChooserOpen.getSelectedFile();
			System.out.println("[LOG] Arquivo escolhido: " + f.getName());
			bi = (BufferedImage) ImageIO.read(f);
			return bi;
		}
		
		return null;
	}
}
