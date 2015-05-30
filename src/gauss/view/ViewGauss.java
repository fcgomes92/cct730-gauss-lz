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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	private JPanel panelOpcoesFiltroGauss;
	private JPanel panelOpcoesFiltroGaussVars;
	private JPanel panelOpcoesFiltroGaussBts;
	
	// Label dos sliders
	private JLabel labelSliderKernel;
	private JLabel labelSliderDesvioPadrao;
	
	// Componentes de opção do filtro de Gauss
	private JSlider sliderTamanhoKernel;
	private JSlider sliderDesvioPadrao;

	// Botões
	private JButton btApplyGauss;
	
	// Layout
	private SpringLayout sLayoutMainPanel;
	private GridLayout gridOpcoesFiltroGauss;
	private GridLayout gridOpcoesFiltroGaussVars;
	private GridLayout gridOpcoesFiltroGaussBts;
	
	// Barra principal de menus
	private JMenuBar menuBarPrincipal;
		// Sub menus
		private JMenu menuOpcoes;
			// Itens menu
			private JMenuItem menuItemOpcoesSair;
			private JMenuItem menuItemOpcoesSalvar;
			private JMenuItem menuItemOpcoesCarregar;
	
	
	public ViewGauss(ControlGauss ctrl) throws IOException{
		// Load das configurações de strings
		this.config = new Properties();
		InputStream in = getClass().getResourceAsStream("/gauss/util/resources/ResourcesStringsGauss.properties");
		this.config.load(in);
		in.close();
		
		this.controlGauss = ctrl;
		
		// Inicialização das barras de menu
		this.menuBarPrincipal = new JMenuBar();
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
				sliderDesvioPadrao.setEnabled(true);
				sliderTamanhoKernel.setEnabled(true);
				btApplyGauss.setEnabled(true);
			}
		});
		
		this.menuOpcoes.add(this.menuItemOpcoesSalvar);
		this.menuOpcoes.add(this.menuItemOpcoesCarregar);
		this.menuOpcoes.add(this.menuItemOpcoesSair);
		
		this.menuBarPrincipal.add(this.menuOpcoes);
		
		// Botoẽs
		this.btApplyGauss = new JButton(this.config.getProperty("BT_APPLY_GAUSS"));
		this.btApplyGauss.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controlGauss.startFilter(spanelImgAlterada, panelImgAlterada, sliderTamanhoKernel.getValue(), sliderDesvioPadrao.getValue());
			}
		});
		
		// Sliders
		this.sliderTamanhoKernel= new JSlider(SwingConstants.HORIZONTAL, 3, 111, 3);
		this.sliderTamanhoKernel.setMinorTickSpacing(2);
		this.sliderTamanhoKernel.setSnapToTicks(true);
		this.sliderTamanhoKernel.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				labelSliderKernel.setText(config.getProperty("LABEL_CONFIG_SIZE")+": "+sliderTamanhoKernel.getValue()+"x"+sliderTamanhoKernel.getValue());
			}
		});
		
		this.sliderDesvioPadrao = new JSlider(SwingConstants.HORIZONTAL, 1, 111, 1);
		this.sliderDesvioPadrao.setMinorTickSpacing(2);
		this.sliderDesvioPadrao.setSnapToTicks(true);
		this.sliderDesvioPadrao.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				labelSliderDesvioPadrao.setText(config.getProperty("LABEL_CONFIG_SIGMA")+": "+sliderDesvioPadrao.getValue());
			}
		});
		
		this.labelSliderKernel = new JLabel(this.config.getProperty("LABEL_CONFIG_SIZE")+": "+this.sliderTamanhoKernel.getValue()+"x"+this.sliderTamanhoKernel.getValue());
		this.labelSliderDesvioPadrao = new JLabel(this.config.getProperty("LABEL_CONFIG_SIGMA")+": "+this.sliderDesvioPadrao.getValue());
		
		this.gridOpcoesFiltroGaussVars = new GridLayout(2, 2);
		this.gridOpcoesFiltroGaussBts = new GridLayout(1, 1);
		this.gridOpcoesFiltroGauss = new GridLayout(2, 1);
		
		this.panelOpcoesFiltroGauss = new JPanel(this.gridOpcoesFiltroGauss);
		this.panelOpcoesFiltroGaussVars = new JPanel(this.gridOpcoesFiltroGaussVars);
		this.panelOpcoesFiltroGaussBts = new JPanel(this.gridOpcoesFiltroGaussBts);

		TitledBorder tlb = null;
		tlb = BorderFactory.createTitledBorder(tlb,config.getProperty("MENU_ITEM_FILTRO_GAUSSIANO"),TitledBorder.CENTER,TitledBorder.CENTER);
		this.panelOpcoesFiltroGaussVars.setBorder(tlb);
		this.panelOpcoesFiltroGaussVars.add(this.labelSliderKernel,0,0);
		this.panelOpcoesFiltroGaussVars.add(this.sliderTamanhoKernel,0,1);
		this.panelOpcoesFiltroGaussVars.add(this.labelSliderDesvioPadrao,1,0);
		this.panelOpcoesFiltroGaussVars.add(this.sliderDesvioPadrao,1,1);
		
		this.panelOpcoesFiltroGaussBts.add(this.btApplyGauss);
		
		this.panelOpcoesFiltroGauss.add(this.panelOpcoesFiltroGaussVars,0);
		this.panelOpcoesFiltroGauss.add(this.panelOpcoesFiltroGaussBts,1);
		
		this.sliderDesvioPadrao.setEnabled(false);
		this.sliderTamanhoKernel.setEnabled(false);
		this.btApplyGauss.setEnabled(false);
		
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
		this.panelMain.add(this.panelOpcoesFiltroGauss);
		
		// Ajustes de layout
		this.sLayoutMainPanel = new SpringLayout();
		this.sLayoutMainPanel.putConstraint(SpringLayout.WEST, this.spanelImgOriginal, 5, SpringLayout.WEST, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.EAST, this.spanelImgAlterada, -5, SpringLayout.EAST, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.WEST, this.panelOpcoesFiltroGauss, 5, SpringLayout.WEST, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.EAST, this.panelOpcoesFiltroGauss, -5, SpringLayout.EAST, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.NORTH, this.spanelImgOriginal, 5, SpringLayout.NORTH, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.NORTH, this.spanelImgAlterada, 5, SpringLayout.NORTH, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.SOUTH, this.spanelImgOriginal, -5, SpringLayout.NORTH, this.panelOpcoesFiltroGauss);
		this.sLayoutMainPanel.putConstraint(SpringLayout.SOUTH, this.spanelImgAlterada, -5, SpringLayout.NORTH, this.panelOpcoesFiltroGauss);
		this.sLayoutMainPanel.putConstraint(SpringLayout.EAST, this.spanelImgOriginal, 0, SpringLayout.HORIZONTAL_CENTER, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.WEST, this.spanelImgAlterada, 0, SpringLayout.HORIZONTAL_CENTER, this.panelMain);
		this.sLayoutMainPanel.putConstraint(SpringLayout.WEST, this.spanelImgAlterada, 5, SpringLayout.EAST, this.spanelImgOriginal);
		this.sLayoutMainPanel.putConstraint(SpringLayout.SOUTH, this.panelOpcoesFiltroGauss, -5, SpringLayout.SOUTH, this.panelMain);
		
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
