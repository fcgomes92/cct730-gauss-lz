package gauss.control;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class ControlFilter {
	public BufferedImage applyGaussFilter(int size, int sigma, BufferedImage bfi){
		BufferedImage novaImagem = new BufferedImage(bfi.getWidth(),bfi.getHeight(),BufferedImage.TYPE_INT_RGB);
		double[][] kernel = this.makeKernel(size, sigma);
		double[][] output = new double[bfi.getWidth()][bfi.getHeight()];
		System.out.println("Kernel:");
//		for (double[] d1 : makeKernel(size, sigma)) {
//			for (double d2 : d1) {
//				System.out.print(" " + d2 + " ");
//			}
//			System.out.println();
//		}
		for (int i = 0; i < bfi.getWidth(); i++) {
			for (int j = 0; j < bfi.getHeight(); j++) {
//				output[i][j]=
				novaImagem.setRGB(i, j, this.lowPass(bfi, new Point(i,j), size, kernel, bfi.getWidth(),bfi.getHeight()).getRGB());
			}
		}
		return novaImagem;
	}
	
	private double [][] makeKernel(int size, int sigma){
		double [][] kernel = new double[size][size];
		
		double mean = size/2;
		double sum = 0.0;
		
		// Calculo dos valores do kernel
		for (int x = 0; x < size; ++x){ 
		    for (int y = 0; y < size; ++y) {
		        kernel[x][y] = Math.exp(
		        					-0.5*(
		        							Math.pow((x-mean)/sigma, 2.0) + Math.pow((y-mean)/sigma,2.0)
		        					)
		        				)/(2 * Math.PI * sigma * sigma);
		        sum += kernel[x][y];
		    }
		}

		// Normalização do kernel
		for (int x = 0; x < size; ++x) 
		    for (int y = 0; y < size; ++y)
		        kernel[x][y] /= sum;
		
		return kernel;
	}
	
	private Color lowPass(BufferedImage input, Point center, int size, double[][] kernel, int maxWidth, int maxHeight){
		double pixel = 0.0;
		double sum = 0.0;
		double[] color = new double[] {0,0,0};
		Color tempColor;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int x = center.x-((size/2)-i);
				int y = center.y-((size/2)-j);
				if(x>0 && x<maxWidth && y>0 && y<maxHeight){
					tempColor = new Color(input.getRGB(center.x-((size/2)-i), center.y-((size/2)-j)));
					color[0] += tempColor.getRed()*kernel[i][j];
					color[1] += tempColor.getGreen()*kernel[i][j];
					color[2] += tempColor.getBlue()*kernel[i][j];
//					pixel += input.getRGB(i, j)*kernel[i][j];
					sum += kernel[i][j];
				}
			}
		}
		for (int j = 0; j < color.length; j++) {
			color[j]/=sum;
		}
		tempColor = new Color((int)color[0],(int)color[1],(int)color[2]);
//		System.out.println(tempColor.toString());
		return tempColor;
	}
}
