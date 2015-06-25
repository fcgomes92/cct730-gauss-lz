package app.control;

import java.io.IOException;
import java.text.ParseException;

import gauss.control.ControlGauss;

public class ControlApp {
	public static void main(String[] args) {
		try {
			new ControlGauss();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}