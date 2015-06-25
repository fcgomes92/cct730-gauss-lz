package gauss.control;

import gauss.model.DrawPanel;
import gauss.view.ViewGauss;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import lz.control.ControlLZ;

public class ControlGauss {

	private ControlFile controlFile;
	private ControlDraw controlDraw;
	private ControlFilter controlFilter;
	
	private BufferedImage imagemOriginal;
	private BufferedImage imagemAlterada;
	
	private ControlLZ controlLZ;
	
	public ControlGauss() throws IOException, ParseException{
		new ViewGauss(this);
		this.controlFile = new ControlFile();
		this.controlDraw = new ControlDraw();
		this.controlFilter = new ControlFilter();
		this.controlLZ = new ControlLZ();
	}
	
	public void exit(JFrame f){
		f.dispose();
		System.exit(0);
	}
	
	public void saveImage(JFrame jf){
		try {
			String path = controlFile.saveImage(jf);
			if(path != null && !path.isEmpty())
				controlLZ.saveUsingLZ77(this.imageToString(),path,controlFile.getSize());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void openImage(JFrame f, JScrollPane sp, DrawPanel p){
		System.out.println("[LOG] Abrindo a imagem...");
		try {
			this.imagemOriginal = this.controlFile.openImage(f);
			this.controlDraw.drawImage(sp, p, this.imagemOriginal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String imageToString(){
		String output;
		//int count = 0;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(imagemAlterada, "jpg", baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] b = baos.toByteArray();
		output = Arrays.toString(b);
		output = output.replaceAll(", ", "").replace("[", "").replace("]", "").replace("-", "");
		System.out.println("Imagem alterada: "+output);
		return output;
	}
	
	public void startFilter(JScrollPane sp, DrawPanel p, int sz, int sig){
		 // GET CONFI	G
		 System.out.println("Setando valores entrados pelo usuário...");
		 int sigma = sig;
		 int size = sz;
		 System.out.println("Valores setados!");
		 
		 // APPLY FILTER
		 System.out.println("Aplicando filtro...");
		 this.imagemAlterada=this.controlFilter.applyGaussFilter(size, sigma, imagemOriginal);
		 System.out.println("Filtro aplicado!");
		 
		 // DRAW ALTER IMAGE
		 System.out.println("Desenhando imagem...");
		 this.controlDraw.drawImage(sp, p, this.imagemAlterada);
		 System.out.println("Imagem desenhada!");
		 System.out.println("");
	}
}
