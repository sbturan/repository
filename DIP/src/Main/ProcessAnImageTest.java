package Main;

import processes.ArithmethicalProcesses;
import processes.Smoother;
import processes.Threshold;

public  class ProcessAnImageTest  {
	
	
	final static String filePath="C://Users/baran.turan/Desktop/dip/LeBron-License-Plates.jpg";
	public static void main(String[] Args) throws Exception
	{
		Smoother smoothFilter=new Smoother(filePath, 5);
		smoothFilter.processAvarageFilter();
      Threshold threshold=new Threshold(filePath);
      threshold.img=smoothFilter.outputImg;
      threshold.processThreshold();
      threshold.saveFile("Avarage5xfilter&threshold");
        
        
		
	}

	public static int processAvarageFilter(int filterSize) throws Exception
	{
		
		Smoother smoothFilter=new Smoother(filePath, filterSize);
		smoothFilter.processAvarageFilter();
		smoothFilter.saveFile(filterSize+"x"+filterSize+" AvarageFilter");
		return 1;
	}
	
	
	public static int processMedianFilter(int filterSize) throws Exception
	{
		Smoother smoothFilter=new Smoother(filePath, filterSize);
		smoothFilter.processMedianFilter();
		smoothFilter.saveFile(filterSize+"x"+filterSize+" MedianFilter");
		return 1;
		
	}
	
	
	public static int revertPictureAccordingToYcoordinate()
	{
		ArithmethicalProcesses arithmethicalProcesses=new ArithmethicalProcesses(filePath);
		arithmethicalProcesses.revertPictureAccordingToYcoordinate();
		arithmethicalProcesses.saveFile("revertPictureAccordingToYcoordinate");
		
		return 1;
	}
	
	public static int revertPictureAccordingToXcoordinate()
	{
		ArithmethicalProcesses arithmethicalProcesses=new ArithmethicalProcesses(filePath);
		
		arithmethicalProcesses.revertPictureAccordingToXCoordinate();
		arithmethicalProcesses.saveFile("revertPictureByXcoordinate");
		return 1;
	}
	
	public static int rotateRight()
	{
		ArithmethicalProcesses arithmethicalProcesses=new ArithmethicalProcesses(filePath);
		arithmethicalProcesses.rotateRight();
		arithmethicalProcesses.saveFile("rotateRight");
		return 1;
	}
	
	public static int rotateLeft()
	{
		ArithmethicalProcesses arithmethicalProcesses=new ArithmethicalProcesses(filePath);
		arithmethicalProcesses.rotateLeft();
		arithmethicalProcesses.saveFile("rotateLeft");
		return 1;
	}
	
	public static int processThreshold()
	{ 
		Threshold threshold=new Threshold(filePath);
		threshold.processThreshold();
		threshold.saveFile("threshold");
		
		return 1;
		
		
	}
	
	
}
