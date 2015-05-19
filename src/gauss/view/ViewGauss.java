package gauss.view;

import gauss.control.ControlGauss;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ViewGauss {
	private Properties config;
	// Controle da interface
	private ControlGauss controlGauss;
	
	// Main Frame
	private JFrame frameMain;
		// Propriedades do frame
		private Dimension dimensionMin;
	
	// Paneis
	private JPanel panelMain;
	private JPanel panelImgOriginal;
	private JPanel panelImgAlterada;
	
	// Barra principal de menus
	private JMenuBar menuBarPrinciapl;
		// Sub menus
		private JMenu menuOpcoes;
			// Itens menu
			private JMenuItem menuItemOpcoesSair;
			private JMenuItem menuItemOpcoesSalvar;
			private JMenuItem menuItemOpcoesCarregar;
			private JMenuItem menuItemOpcoes;
		
		private JMenu menuFiltros;
		// Itens menu
			// TODO: Declarar filtros
	
	
	public ViewGauss(ControlGauss ctrl) throws IOException{
		// Load das configurações de strings
		this.config = new Properties();
		InputStream in = getClass().getResourceAsStream("/gauss/util/resources/ResourcesStringsGauss.properties");
		this.config.load(in);
		in.close();
		
		this.controlGauss = ctrl;
		
		// Propriedades do frameMain
		this.dimensionMin = new Dimension(500,500);
		
		this.frameMain = new JFrame(this.config.getProperty("TITULO_JANELA"));
		this.frameMain.setMinimumSize(dimensionMin);
		this.frameMain.setSize(dimensionMin);
		this.frameMain.setVisible(true);
	}
}
