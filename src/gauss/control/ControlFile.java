package gauss.control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ControlFile {
	private JFileChooser fileChooserOpen;
	private JFileChooser fileChooserSave;
	private FileFilter ffOpen, ffSave;
	private Properties config;
	
	public ControlFile() throws IOException{
		this.config = new Properties();
		InputStream in = getClass().getResourceAsStream("/gauss/util/resources/ResourcesStringsGauss.properties");
		this.config.load(in);
		in.close();
		
		this.ffOpen = new FileNameExtensionFilter(
				config.getProperty("OPEN_FORMATS").toUpperCase(), 
				config.getProperty("OPEN_FORMATS").split(",")
				);
		this.ffSave = new FileNameExtensionFilter(
				config.getProperty("SAVE_FORMATS").toUpperCase(), 
				config.getProperty("SAVE_FORMATS").split(",")
				);
		this.fileChooserOpen = new JFileChooser();
		this.fileChooserOpen.setFileFilter(ffOpen);
		this.fileChooserSave = new JFileChooser();
		this.fileChooserSave.setFileFilter(ffSave);
	}
	
	public BufferedImage openImage(JFrame jf) throws IOException{
		File f = null;
		int chooseReturn = 0;
		BufferedImage bi = null;
		chooseReturn = this.fileChooserOpen.showDialog(jf, "OK");
		
		if(chooseReturn == JFileChooser.APPROVE_OPTION){
			f = this.fileChooserOpen.getSelectedFile();
			System.out.println("[LOG] Arquivo escolhido: " + f.getName());
			bi = ImageIO.read(f);
			return bi;
		}
		
		return null;
	}
	
	public String saveImage(JFrame jf) throws IOException{
		File f = null;
		int chooseReturn = 0;
		chooseReturn = this.fileChooserSave.showDialog(jf, "OK");
		
		if(chooseReturn == JFileChooser.APPROVE_OPTION){
			f = this.fileChooserSave.getSelectedFile();
			System.out.println("[LOG] Arquivo escolhido: " + f.getAbsolutePath());
			if(f.getAbsolutePath().endsWith(".lz77"))
				return f.getAbsolutePath();
			else
				return f.getAbsolutePath()+".lz77";
		}
		
		return null;
	}
}
