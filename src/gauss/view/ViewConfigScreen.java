package gauss.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class ViewConfigScreen {
	private Properties config;
	
	private JPanel panelMain;
	private JPanel panelCampos;
	private JPanel panelBotoes;
	
	private GridLayout layoutGeral;
	private GridLayout layoutCampos;
	private GridLayout layoutBotoes;
	
	private JButton btOk;
	private JButton btCancel;
	
	private JLabel lblSigma;
	private JFormattedTextField txtFieldSigma;
	private JLabel lblTamanho;
	private JFormattedTextField txtFieldTamanho;
	
	private Dimension dimension;
	
	public ViewConfigScreen() throws IOException, ParseException{
		// Load das configurações de strings
		this.config = new Properties();
		InputStream in = getClass().getResourceAsStream("/gauss/util/resources/ResourcesStringsGauss.properties");
		this.config.load(in);
		in.close();
		
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMaximumIntegerDigits(5);
		
		this.lblSigma = new JLabel(this.config.getProperty("LABEL_CONFIG_SIGMA"));
		this.txtFieldSigma = new JFormattedTextField(format);
		this.lblTamanho = new JLabel(this.config.getProperty("LABEL_CONFIG_SIZE"));
		this.txtFieldTamanho = new JFormattedTextField(format);
		
		this.layoutGeral = new GridLayout(1,1,5,5);
		this.layoutCampos = new GridLayout(2,2);
		this.layoutBotoes = new GridLayout(1,2,10,10);
		
		this.panelMain = new JPanel();
		this.panelMain.setLayout(layoutGeral);
		this.panelCampos = new JPanel();
		this.panelCampos.setLayout(layoutCampos);
		
		this.panelCampos.add(lblSigma,0,0);
		this.panelCampos.add(txtFieldSigma,0,1);
		this.panelCampos.add(lblTamanho,1,0);
		this.panelCampos.add(txtFieldTamanho,1,1);
		
		this.panelMain.add(this.panelCampos,0);
	}

	public JPanel getPanelMain() {
		return panelMain;
	}

	public void setPanelMain(JPanel panelMain) {
		this.panelMain = panelMain;
	}

	public JFormattedTextField getTxtFieldSigma() {
		return txtFieldSigma;
	}

	public void setTxtFieldSigma(JFormattedTextField txtFieldConfig1) {
		this.txtFieldSigma = txtFieldConfig1;
	}

	public JFormattedTextField getTxtFieldSize() {
		return txtFieldTamanho;
	}

	public void setTxtFieldSize(JFormattedTextField txtFieldConfig2) {
		this.txtFieldTamanho = txtFieldConfig2;
	}
}
