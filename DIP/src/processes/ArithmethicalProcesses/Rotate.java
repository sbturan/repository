/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package processes.ArithmethicalProcesses;

import java.awt.image.BufferedImage;
import processes.Process;

/**
 *
 * @author Orkun
 */
public class Rotate extends Process{
    public enum Rotation {Left, Right}
    Rotation rotation;
    public void rotateRight()
	{
		outputImg=new BufferedImage(img.getHeight(), img.getWidth(), BufferedImage.TYPE_INT_RGB);
  	 
		
		for(int i=0;i<img.getWidth();i++)
			for(int j=0;j<img.getHeight();j++)
			{
				outputImg.setRGB(j, i, img.getRGB(i, j));
				
				
			}
		img=outputImg;
                Revert revert = new Revert(Revert.Coordinate.Y);
                revert.setImg(outputImg);
                revert.run();
                outputImg = copyImage(revert.getOutputImg());
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
		Revert revert = new Revert(Revert.Coordinate.X);
                revert.setImg(outputImg);
                revert.run();
                outputImg = copyImage(revert.getOutputImg());
		
	}

    @Override
    public void run() {
        switch (rotation) {
            case Left:
                rotateLeft();
                break;
            case Right:
                rotateRight();
                break;
        }
    }
}
