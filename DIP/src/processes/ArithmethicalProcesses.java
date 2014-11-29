package processes;

import java.awt.image.BufferedImage;

public class ArithmethicalProcesses extends Process{
	
	
	
	public ArithmethicalProcesses(String filePath){
		
		img=readImage(filePath);
		
		
	}
	public String  saveFile(String methodName)
	{
		
		return super.saveFile(methodName, this.outputImg==null?img:outputImg);
	}
	
	public void revertPictureAccordingToYcoordinate()
	{
		int RGB;
		int imgWidht=img.getWidth();
		int imgHeight=img.getHeight();
		for(int i=0;i<imgWidht/2;i++)
			for(int j=0;j<imgHeight;j++)
			{
				RGB=img.getRGB(i, j);
				img.setRGB(i, j, img.getRGB((imgWidht-1-i), j));
				img.setRGB((imgWidht-1-i), j, RGB);
			}
		
		
	}
	
	public void revertPictureAccordingToXCoordinate()
	{
		int RGB;
		int imgWidth=img.getWidth();
		int imgHeight=img.getHeight();
		for(int i=0;i<imgWidth;i++)
			for(int j=0;j<imgHeight/2;j++)
			{
				RGB=img.getRGB(i, j);
				img.setRGB(i, j, img.getRGB(i, img.getHeight()-1-j));
				img.setRGB(i, imgHeight-1-j, RGB);
			}
		
	}
	
	public void rotateRight()
	{
		outputImg=new BufferedImage(img.getHeight(), img.getWidth(), BufferedImage.TYPE_INT_RGB);
  	 
		
		for(int i=0;i<img.getWidth();i++)
			for(int j=0;j<img.getHeight();j++)
			{
				outputImg.setRGB(j, i, img.getRGB(i, j));
				
				
			}
		img=outputImg;
		revertPictureAccordingToYcoordinate();
		
	 
		
	}
	
	public void rotateLeft()
	{
             outputImg=new BufferedImage(img.getHeight(), img.getWidth(), BufferedImage.TYPE_INT_RGB);
  	 
		
		for(int i=0;i<img.getWidth();i++)
			for(int j=0;j<img.getHeight();j++)
			{
				outputImg.setRGB(j, i, img.getRGB(i, j));
				
				
			}
		img=outputImg;
		revertPictureAccordingToXCoordinate();
		
	}
	
	
	
	
	

}
