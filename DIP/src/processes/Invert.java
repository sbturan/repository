/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package processes;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 *
 * @author Orkun
 */
public class Invert extends Process{
    
    void processInvert()
    {
        int imgWidht = img.getWidth();
        int imgHeight = img.getHeight();
        outputImg = copyImage(img);
        
        Raster data = img.getData();
        WritableRaster outputdata = outputImg.getRaster();
        for (int i = 0; i < imgWidht; i++) {
            for (int j = 0; j < imgHeight; j++) {
                outputdata.setSample(i, j, 0, 255 - data.getSample(i, j, 0));
            }
        }
        
        outputImg.setData(outputdata);
    }
    
    @Override
    public void run() {
        processInvert();
    }
    
}
