package processes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Threshold extends Process {

    public Threshold(String filePath) {
        img = readImage(filePath);
        outputImg = copyImage(img);
    }
    
    public Threshold(BufferedImage img) {
        this.img = img;
        outputImg = copyImage(img);
    }
    
    public Threshold()
    {
        super();
    }

    private void processThreshold() {
        int red = 128;
        int green = 128;
        int blue = 128;

        Color color = new Color(red, green, blue);
        Color whiteColor = new Color(0, 0, 0);
        Color blackColor = new Color(255, 255, 255);

        int imgWidht = img.getWidth();
        int imgHeight = img.getHeight();
        for (int i = 0; i < imgWidht; i++) {
            for (int j = 0; j < imgHeight; j++) {
                if (img.getRGB(i, j) < color.getRGB()) {
                    outputImg.setRGB(i, j, whiteColor.getRGB());
                } else {
                    outputImg.setRGB(i, j, blackColor.getRGB());
                }
            }
        }
    }
    
    public void iterativeThreshold(int thresholdLevel)
	{  // threshold level, iterasyonun durma noktasý
		
		

		
		Color threshold=new Color(128,128,128);
		
		Color lastThreshold=iterate(threshold, thresholdLevel);
		int imgWidht=img.getWidth();
 		int imgHeight=img.getHeight();
 		Color white=new Color(0,0,0);
 		Color black=new Color(255,255,255);
 		
 		for(int i=0;i<imgWidht;i++){
 			for(int j=0;j<imgHeight;j++){
 				if(img.getRGB(i, j)<lastThreshold.getRGB()){
 					outputImg.setRGB(i, j, white.getRGB());
 				}else{
 					outputImg.setRGB(i, j, black.getRGB());
 				}
 			}
 		}
		
	
	}
	private Color iterate(Color oldThreshold,int thresholdLevel){
		
     	ArrayList<Integer> g1=new ArrayList<Integer>();
 		ArrayList<Integer> g2=new ArrayList<Integer>();
 		
 		int imgWidht=img.getWidth();
 		int imgHeight=img.getHeight();
 		for(int i=0;i<imgWidht;i++){
 			for(int j=0;j<imgHeight;j++){
 				if(img.getRGB(i,j)<oldThreshold.getRGB()){
 					g1.add(img.getRGB(i,j));
 					
 				}else{
 					g2.add(img.getRGB(i,j));
 				}
 				
 			}}
 		
 		
         int c1=getAvarage(g1);
        int c2=getAvarage(g2);
        Color newThreshold=new Color((c1+c2/2));
		int itl=newThreshold.getRGB()-oldThreshold.getRGB();
		if(itl<0){
			itl=itl*(-1);
		}
		System.out.println("oldThreshold//New threshold//c1//c2//g1//g2");
		System.out.println(oldThreshold.getRGB()+"//"+newThreshold.getRGB()+"//"+c1+"//"+c2+"//"+g1.size()+"//"+g2.size());
		if(itl>thresholdLevel){
			newThreshold=iterate(newThreshold,thresholdLevel);
			
		}
		
		
	return newThreshold;	
	}
	private int getAvarage(List<Integer> list){
		int t=0;
		for(int i=0;i<list.size();i++){
			t =t+list.get(i);
			
		}
		
		return t/list.size();
	}

    @Override
    public void run() {
        processThreshold();
    }
}
