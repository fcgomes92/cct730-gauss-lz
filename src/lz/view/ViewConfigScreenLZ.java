package lz.view;

import java.awt.GridLayout;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Properties;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewConfigScreenLZ {
private Properties config;
	
	private JPanel panelMain;
	private JPanel panelCampos;
	
	private GridLayout layoutGeral;
	private GridLayout layoutCampos;
	
	private JLabel lblBuffer;
	private JFormattedTextField txtFieldBuffer;
	private JLabel lblDicionario;
	private JFormattedTextField txtFieldDicionario;
	
	public ViewConfigScreenLZ() throws IOException, ParseException{
		// Load das configurações de strings
		this.config = new Properties();
		InputStream in = getClass().getResourceAsStream("/lz/util/resources/ResourcesStringsLZ.properties");
		this.config.load(in);
		in.close();
		
		NumberFormat format = NumberFormat.getNumberInstance();
		format.setMaximumIntegerDigits(5);
		
		this.lblBuffer = new JLabel(this.config.getProperty("LABEL_CONFIG_BUFFER"));
		this.txtFieldBuffer = new JFormattedTextField(format);
		this.lblDicionario = new JLabel(this.config.getProperty("LABEL_CONFIG_DICIONARIO"));
		this.txtFieldDicionario = new JFormattedTextField(format);
		
		this.layoutGeral = new GridLayout(1,1,5,5);
		this.layoutCampos = new GridLayout(2,2);
		
		this.panelMain = new JPanel();
		this.panelMain.setLayout(layoutGeral);
		this.panelCampos = new JPanel();
		this.panelCampos.setLayout(layoutCampos);
		
		this.panelCampos.add(this.lblBuffer,0,0);
		this.panelCampos.add(this.txtFieldBuffer,0,1);
		this.panelCampos.add(this.lblDicionario,1,0);
		this.panelCampos.add(this.txtFieldDicionario,1,1);
		
		this.panelMain.add(this.panelCampos,0);
	}

	public JPanel getPanelMain() {
		return panelMain;
	}

	public void setPanelMain(JPanel panelMain) {
		this.panelMain = panelMain;
	}

	public JFormattedTextField getTxtFieldBuffer() {
		return txtFieldBuffer;
	}

	public void setTxtFieldBuffer(JFormattedTextField txtFieldConfig1) {
		this.txtFieldBuffer = txtFieldConfig1;
	}

	public JFormattedTextField getTxtFieldDicionario() {
		return txtFieldDicionario;
	}

	public void setTxtFieldDicionario(JFormattedTextField txtFieldConfig2) {
		this.txtFieldDicionario = txtFieldConfig2;
	}
}
