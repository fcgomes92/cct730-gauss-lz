package gauss.control;

import gauss.model.DrawPanel;
import gauss.view.ViewConfigScreen;
import gauss.view.ViewGauss;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class ControlGauss {
	
	private ViewGauss mainViewGauss;
	private ViewConfigScreen configScreen;
	private ControlFile controlFile;
	private ControlDraw controlDraw;
	private ControlFilter controlFilter;
	
	private BufferedImage imagemOriginal;
	private BufferedImage imagemAux;
	private BufferedImage imagemAlterada; 
	
	// Parâmetros do filtro
	private int maskSize;
	private int desvioPadrao;
	
	public ControlGauss() throws IOException, ParseException{
		this.mainViewGauss = new ViewGauss(this);
		this.configScreen = new ViewConfigScreen();
		this.controlFile = new ControlFile();
		this.controlDraw = new ControlDraw();
		this.controlFilter = new ControlFilter();
	}
	
	public void exit(JFrame f){
		f.dispose();
		System.exit(0);
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
	
	public void startFilter(JScrollPane sp, DrawPanel p){
		// ABRE JANELA DE CONFIG
		 int result = JOptionPane.showConfirmDialog(null, configScreen.getPanelMain(), 
	               "Config", JOptionPane.OK_CANCEL_OPTION);
		 if(result == JOptionPane.OK_OPTION && imagemOriginal != null
				 && !configScreen.getTxtFieldSigma().getText().isEmpty()
				 && !configScreen.getTxtFieldSize().getText().isEmpty()){
			 System.out.println("Setando valores entrados pelo usuário...");
			 // GET CONFIG
			 int sigma = Integer.parseInt(configScreen.getTxtFieldSigma().getText());
			 int size = Integer.parseInt(configScreen.getTxtFieldSize().getText());
			 // Ajuste do tamanho da máscara
			 if(size%2 == 0) size++;
			 // APPLY FILTER
			 this.imagemAlterada=this.controlFilter.applyGaussFilter(size, sigma, imagemOriginal);
			 // DRAW ALTER IMAGE
			 this.controlDraw.drawImage(sp, p, this.imagemAlterada);
		 }
	}
}
