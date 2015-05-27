package gauss.control;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class ControlFilter {
	public BufferedImage applyGaussFilter(int size, int sigma, BufferedImage bfi){
		BufferedImage novaImagem = new BufferedImage(bfi.getWidth(),bfi.getHeight(),BufferedImage.TYPE_INT_ARGB);
		double[][] kernel = this.makeKernel(size, sigma);
		double[][] output = new double[bfi.getWidth()][bfi.getHeight()];
		System.out.println("Kernel:");
		for (double[] d1 : makeKernel(size, sigma)) {
			for (double d2 : d1) {
				System.out.print(" " + d2 + " ");
			}
			System.out.println();
		}
		for (int i = 0; i < bfi.getWidth(); i++) {
			for (int j = 0; j < bfi.getHeight(); j++) {
//				output[i][j]=
				novaImagem.setRGB(i, j, (int) this.lowPass(bfi, new Point(i,j), size, kernel, sigma));
			}
		}
		return novaImagem;
	}
	
	private double [][] makeKernel(int size, int sigma){
		double [][] kernel = new double[size][size];
		
		double mean = size/2;
		double sum = 0.0;
		
		// Calculo dos valores do kernel
		for (int x = 0; x < size; ++x) 
		    for (int y = 0; y < size; ++y) {
		        kernel[x][y] = Math.exp(
		        					-0.5*(
		        							Math.pow((x-mean)/sigma, 2.0) + Math.pow((y-mean)/sigma,2.0)
		        					)
		        				)/(2 * Math.PI * sigma * sigma);
		        sum += kernel[x][y];
		    }

		// Normalização do kernel
		for (int x = 0; x < size; ++x) 
		    for (int y = 0; y < size; ++y)
		        kernel[x][y] /= sum;
		
		return kernel;
	}
	
	private double lowPass(BufferedImage input, Point center, int size, double[][] kernel, int sigma){
		double pixel = 0.0;
		for (int i = center.x-size; i < center.x+size; i++) {
			for (int j = center.y-size; j < center.y+size; j++) {
				if(i>-1 && i<size && j>-1 && j<size){
					
					pixel += input.getRGB(i, j)*kernel[i][j];
				}
			}
		}
		pixel = pixel/sigma;
		return pixel;
	}
}
