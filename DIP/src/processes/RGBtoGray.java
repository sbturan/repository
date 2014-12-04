/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package processes;

import java.awt.image.BufferedImage;

/**
 *
 * @author Orkun
 */
public class RGBtoGray extends Process{
    
    void processRGBtoGray()
    {
        outputImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        outputImg.getGraphics().drawImage(img, 0, 0, null);
    }
    
    @Override
    public void run() {
        processRGBtoGray();
    }
    
}
