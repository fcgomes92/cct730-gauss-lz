package lz.control;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

import javax.swing.JOptionPane;

import lz.view.ViewConfigScreenLZ;

public class ControlLZ {
	
	private ViewConfigScreenLZ configDialog;
	private int sizeDict;
	private int sizeBuf;
	private String input;
	private Properties config;
	
	public ControlLZ() throws IOException, ParseException{
		this.configDialog = new ViewConfigScreenLZ();
		this.sizeBuf = 0;
		this.sizeDict = 0;
	}
	
	public void saveUsingLZ77(String input, String path) throws IOException{
		this.config = new Properties();
		InputStream in = getClass().getResourceAsStream("/lz/util/resources/ResourcesStringsLZ.properties");
		this.config.load(in);
		in.close();		
		// GET VARS COMPR
		int result = JOptionPane.showConfirmDialog(null, this.configDialog.getPanelMain(), 
	               "Config", JOptionPane.OK_CANCEL_OPTION);
		
		 if(result == JOptionPane.OK_OPTION 
				 && this.configDialog.getTxtFieldDicionario() != null
			     && this.configDialog.getTxtFieldBuffer() != null
				 && !this.configDialog.getTxtFieldBuffer().getText().isEmpty()
				 && !this.configDialog.getTxtFieldDicionario().getText().isEmpty()){
			 
			 this.sizeBuf = Integer.parseInt(this.configDialog.getTxtFieldBuffer().getText());
			 this.sizeDict = Integer.parseInt(this.configDialog.getTxtFieldDicionario().getText());
			 this.input = input;
			 
			 // COMPR
			 this.compressLZ77();
			 JOptionPane.showMessageDialog(null, this.config.getProperty("SAVE_CONFIRM_TEXT"), this.config.getProperty("SAVE_CONFIRM_TEXT"), JOptionPane.INFORMATION_MESSAGE);
		 }
		 else JOptionPane.showMessageDialog(null, this.config.getProperty("SAVE_ERR_TEXT"), this.config.getProperty("SAVE_ERR_TEXT"), JOptionPane.ERROR_MESSAGE);
	}
	
	public void compressLZ77(){
		this.input = "Testando esssa budega";
	}
	
	public int getSizeDict() {
		return sizeDict;
	}

	public void setSizeDict(int sizeDict) {
		this.sizeDict = sizeDict;
	}

	public int getSizeBuf() {
		return sizeBuf;
	}

	public void setSizeBuf(int sizeBuf) {
		this.sizeBuf = sizeBuf;
	}
}
