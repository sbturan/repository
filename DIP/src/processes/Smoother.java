package processes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Tools.RgbColor;

public class Smoother extends Process {

	
	public int filterSize;
	
	

	public Smoother(String filePath, int filterSize) throws Exception {
		if(filterSize<=2||filterSize%2!=1)
			throw new Exception("Hatalı Filtre Boyutu");
		this.filePath = filePath;
		this.filterSize = filterSize;
        img=readImage(filePath);
		outputImg=copyImage(img);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getFilterSize() {
		return filterSize;
	}

	public void setFilterSize(int filterSize) {
		this.filterSize = filterSize;
	}
	
	public  String  saveFile(String methodName)
	{
		return super.saveFile(methodName, this.outputImg);
		
	}
	public void processMedianFilter(){
		for (int i = filterSize / 2; i < img.getWidth() - (filterSize / 2); i++)
			for (int j = filterSize / 2; j <img.getHeight() - (filterSize / 2); j++) {
				medianFilterOnAPixel(i, j);

			}

		
		
	}

	public void processAvarageFilter() {
		for (int i = filterSize / 2; i < img.getWidth() - (filterSize / 2); i++)
			for (int j = filterSize / 2; j <img.getHeight() - (filterSize / 2); j++) {
				avarageFilterOnAPixel(i, j);

			}


		
	}

	private void medianFilterOnAPixel(int x,int y)
	{
		int k=filterSize/2;
		List<Integer> red = new ArrayList<Integer>();
		List<Integer> green = new ArrayList<Integer>();
		List<Integer> blue = new ArrayList<Integer>();
		RgbColor color;
		

		for (int i = (-1) * k; i <= k; i++) {
			for (int j = (-1) * k; j <= k; j++) {
				
					color = new RgbColor(img.getRGB(x + i, y + j));
					red.add(color.getRed());
					green.add(color.getGreen());
					blue.add(color.getBlue());
					
					

				
			}
		}
		Collections.sort(red);
		Collections.sort(green);
		Collections.sort(blue);
		
		outputImg.setRGB(x, y, new Color(red.get(((filterSize*filterSize)-1)/2), green.get(((filterSize*filterSize)-1)/2), blue.get(((filterSize*filterSize)-1)/2)).getRGB());
  
		
		
	}
	private void avarageFilterOnAPixel(int x, int y) {
		int k = filterSize / 2;
		int totalRed = 0, totalGreen = 0, totalBlue = 0;
		RgbColor color;
		for (int i = (-1) * k; i <= k; i++) {
			for (int j = (-1) * k; j <= k; j++) {
				 

					color = new RgbColor(img.getRGB(x + i, y + j));
					totalRed += color.getRed() / ((filterSize*filterSize));
					totalGreen += color.getGreen() / ((filterSize*filterSize));
					totalBlue += color.getBlue() / ((filterSize*filterSize));

				
			}
		}
		outputImg.setRGB(x, y, new Color(totalRed, totalGreen, totalBlue).getRGB());

	}
	
	
	
	
	
	
	
	
	
	

}
