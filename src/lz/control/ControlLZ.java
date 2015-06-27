package lz.control;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

import javax.swing.JOptionPane;

import lz.view.ViewConfigScreenLZ;

public class ControlLZ {

	private ViewConfigScreenLZ configDialog;
	private ControlFile controlFile;
	private int sizeDict;
	private int sizeBuf;
	private String input;
	private Properties config;
	private int qtdTuples = 0;
	private long oSize = 0;

	public ControlLZ() throws IOException, ParseException {
		this.configDialog = new ViewConfigScreenLZ();
		this.controlFile = new ControlFile();
		this.sizeBuf = 0;
		this.sizeDict = 0;
	}

	public void saveUsingLZ77(String input, String path, long originalSize) throws IOException {
		String compressao = "";
		this.oSize = originalSize;
		this.input = input;
		this.config = new Properties();
		InputStream in = getClass().getResourceAsStream(
				"/lz/util/resources/ResourcesStringsLZ.properties");
		this.config.load(in);
		in.close();
		// GET VARS COMPR
		int result = JOptionPane.showConfirmDialog(null,
				this.configDialog.getPanelMain(), "Config",
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION
				&& this.configDialog.getTxtFieldDicionario() != null
				&& this.configDialog.getTxtFieldBuffer() != null
				&& !this.configDialog.getTxtFieldBuffer().getText().isEmpty()
				&& !this.configDialog.getTxtFieldDicionario().getText()
						.isEmpty()) {

			this.sizeBuf = Integer.parseInt(this.configDialog
					.getTxtFieldBuffer().getText().replace(",", ""));
			this.sizeDict = Integer.parseInt(this.configDialog
					.getTxtFieldDicionario().getText().replace(",", ""));
			this.input = input;

			// COMPR
			String stringComprimida = "";
			stringComprimida = this.LZ77();
			this.controlFile.saveString(path, stringComprimida);
			
			System.out.println("Quantidade de tuplas: " + qtdTuples);
			compressao = (String.format("Porcentagem compressão: %.2f %", ((double)((long)qtdTuples)/this.oSize)*100));
			
			JOptionPane.showMessageDialog(null,
					compressao,
					this.config.getProperty("SAVE_CONFIRM_TEXT"),
					JOptionPane.INFORMATION_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null,
					this.config.getProperty("SAVE_ERR_TEXT"),
					this.config.getProperty("SAVE_ERR_TEXT"),
					JOptionPane.ERROR_MESSAGE);
	}

	public void compressLZ77() {
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

	private String LZ77() {
		String output = "";
		String lookfor = "";
		String literal = "";
		String dicionario = "";
		String buffer = "";	
		this.qtdTuples = 0;
		int k;
		int i = 0;
		while(i < input.length()){
			int dictInicial = i-this.getSizeDict();
			if(dictInicial < 0) dictInicial = 0;
			if(i > input.length() || dictInicial > i) dicionario = "";
			else dicionario = input.substring(dictInicial, i);
			if(i+this.getSizeBuf()>this.input.length()) buffer = this.input.substring(i, this.input.length());
			else buffer = this.input.substring(i, i+this.getSizeBuf());
			lookfor = ("0,0"+input.charAt(i));
			for(k = buffer.length();k>0;k--){
				int indice = dicionario.lastIndexOf(buffer.substring(0, k));
				if(indice >=0){
					literal = "";
					if((i+k)<input.length()) literal = ""+input.charAt(i+k);
					lookfor = (dicionario.length()-indice-1+","+k+","+literal);
					break;
				}
			}
			i = i+ k + 1;
			output += lookfor+";";
			qtdTuples++;
		}
		return output;
	}
}
