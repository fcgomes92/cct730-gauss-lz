package lz.control;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class ControlFile {
	
	private DataOutputStream dos;
	private FileOutputStream fos;
	
	public void saveString(String fileName, String data){
		try {
			
			File f = new File(fileName);
			this.fos = new FileOutputStream(f);
			this.dos = new DataOutputStream(this.fos);
			this.dos.write(data.getBytes());
			
		} catch (FileNotFoundException e) {
			System.out.println("[ERRO] Erro ao salvar o arquivo!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("[ERRO] Erro ao escrever no arquivo!");
			e.printStackTrace();
		} finally{
			try {
//				this.dos.close();
				this.fos.flush();
				this.fos.close();
			} catch (IOException e) {
				System.out.println("[ERRO] Erro ao finalizar o arquivo!");
				e.printStackTrace();
			}
		}
		
	}
}
