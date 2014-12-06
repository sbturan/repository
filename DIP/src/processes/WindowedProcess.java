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
public class WindowedProcess extends Process{
    
    int w_widht, w_height;
    int d_width, d_height;
    boolean drawLines;
    
    Process process;
    
    public WindowedProcess(Process process, int width, int height, int d_width, int d_height)
    {
        super();
        w_widht = width;
        w_height = height;
        this.d_height = d_height;
        this.d_width = d_width;
        this.process = process;
        
    }
    
    void processWindowedProcess()
    {
        int i_width = img.getWidth();
        int i_height = img.getHeight();
        for(int i = 0; i< i_width-w_widht;i += d_width)
        {
            for(int j = 0; j<i_height-w_height; j += d_height)
            {
                BufferedImage subimage = img.getSubimage(i, j, w_widht, w_height);
                process.setImg(subimage);
                
                process.run();
                saveFile("windowed_"+i+"_"+j, process.getOutputImg(), filePath);
            }
        }
    }
    
    @Override
    public void run() {
        processWindowedProcess();
    }
    
    
}
