package gauss.control;

import java.io.IOException;

import gauss.view.ViewGauss;

public class ControlGauss {
	
	private ViewGauss mainViewGauss;
	
	public ControlGauss() throws IOException{
		mainViewGauss = new ViewGauss(this);
	}
}
