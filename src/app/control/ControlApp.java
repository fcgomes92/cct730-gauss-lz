package app.control;

import java.io.IOException;

import gauss.control.ControlGauss;

public class ControlApp {
	public static void main(String[] args) {
		try {
			ControlGauss controlFilters = new ControlGauss();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
