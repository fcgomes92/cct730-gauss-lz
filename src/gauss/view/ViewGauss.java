package gauss.view;

import gauss.control.ControlGauss;
import gauss.model.DrawPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

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
	private JScrollPane spanelImgOriginal;
	private JScrollPane spanelImgAlterada;
	private DrawPanel panelImgOriginal;
	private DrawPanel panelImgAlterada;

	// Layout
	private SpringLayout sLayoutMainPanel;
	private GridLayout grid1;
	
	// Barra principal de menus
	private JMenuBar menuBarPrincipal;
		// Sub menus
		private JMenu menuOpcoes;
			// Itens menu
			private JMenuItem menuItemOpcoesSair;
			private JMenuItem menuItemOpcoesSalvar;
			private JMenuItem menuItemOpcoesCarregar;
		
		private JMenu menuFiltros;
		// Itens menu
			private JMenuItem menuItemFiltrosGaussiano;
	
	
	public ViewGauss(ControlGauss ctrl) throws IOException{
		// Load das configurações de strings
		this.config = new Properties();
		InputStream in = getClass().getResourceAsStream("/gauss/util/resources/ResourcesStringsGauss.properties");
		this.config.load(in);
		in.close();
		
		this.controlGauss = ctrl;
		
		// Inicialização das barras de menu
		this.menuBarPrincipal = new JMenuBar();
		this.menuFiltros = new JMenu(this.config.getProperty("MENU_FILTROS"));
		this.menuOpcoes = new JMenu(this.config.getProperty("MENU_OPCOES"));
		
		this.menuItemOpcoesSalvar = new JMenuItem(this.config.getProperty("MENU_ITEM_SALVAR"));
		this.menuItemOpcoesSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Chamada do método de salvar a imagem alterada
			}
		});
		
		this.menuItemOpcoesSair = new JMenuItem(this.config.getProperty("MENU_ITEM_SAIR"));
		this.menuItemOpcoesSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlGauss.exit(frameMain);
			}
		});
		
		this.menuItemOpcoesCarregar = new JMenuItem(this.config.getProperty("MENU_ITEM_CARREGAR"));
		this.menuItemOpcoesCarregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlGauss.openImage(frameMain, spanelImgOriginal, panelImgOriginal);
			}
		});
		
		this.menuItemFiltrosGaussiano = new JMenuItem(this.config.getProperty("MENU_ITEM_FILTRO_GAUSSIANO"));
		this.menuItemFiltrosGaussiano.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controlGauss.startFilter(spanelImgAlterada,panelImgAlterada);
			}
		});
		
		this.menuOpcoes.add(this.menuItemOpcoesSalvar);
		this.menuOpcoes.add(this.menuItemOpcoesCarregar);
		this.menuOpcoes.add(this.menuItemOpcoesSair);
		
		this.menuFiltros.add(this.menuItemFiltrosGaussiano);
		
		this.menuBarPrincipal.add(this.menuOpcoes);
		this.menuBarPrincipal.add(this.menuFiltros);
		
		// Inicialização dos panels
		this.panelImgAlterada = new DrawPanel();
		this.panelImgOriginal = new DrawPanel();
		this.panelImgOriginal.setBackground(Color.WHITE);
		this.panelImgAlterada.setBackground(Color.WHITE);
		
		this.spanelImgOriginal = new JScrollPane(this.panelImgOriginal, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.spanelImgAlterada = new JScrollPane(this.panelImgAlterada, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.panelMain = new JPanel();
//		this.spanelImgAlterada.add(this.panelImgAlterada);
//		this.spanelImgOriginal.add(this.panelImgOriginal);
		this.panelMain.add(this.spanelImgOriginal);
		this.panelMain.add(this.spanelImgAlterada);
		
		// Ajustes de layout
		this.sLayoutMainPanel = new SpringLayout();
		this.sLayoutMainPanel.putConstraint(SpringLayout.WEST, this.spanelImgOriginal, 5, SpringLayout.WEST, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.EAST, this.spanelImgAlterada, -5, SpringLayout.EAST, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.NORTH, this.spanelImgOriginal, 5, SpringLayout.NORTH, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.NORTH, this.spanelImgAlterada, 5, SpringLayout.NORTH, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.SOUTH, this.spanelImgOriginal, -5, SpringLayout.SOUTH, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.SOUTH, this.spanelImgAlterada, -5, SpringLayout.SOUTH, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.EAST, this.spanelImgOriginal, 0, SpringLayout.HORIZONTAL_CENTER, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.WEST, this.spanelImgAlterada, 0, SpringLayout.HORIZONTAL_CENTER, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.WEST, this.spanelImgAlterada, 5, SpringLayout.EAST, this.spanelImgOriginal);
		
		this.panelMain.setLayout(sLayoutMainPanel);
		
		// Propriedades do frameMain
		this.dimensionMin = new Dimension(500,500);
		
		this.frameMain = new JFrame(this.config.getProperty("TITULO_JANELA"));
		this.frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frameMain.setMinimumSize(this.dimensionMin);
		this.frameMain.setSize(this.dimensionMin);
		this.frameMain.setJMenuBar(this.menuBarPrincipal);
		this.frameMain.add(this.panelMain);
		this.frameMain.setVisible(true);
	}
}
