/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package processes;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Orkun
 */
public class Pipeline extends Process{
    ArrayList<Process> Processes = new ArrayList<Process>();
    
    public Pipeline(BufferedImage img)
    {
        this.img = img;
        outputImg = copyImage(img);
    }
    
    public void AddProcess(Process process)
    {
        Processes.add(process);
    }
    
    @Override
    public void run() {
        BufferedImage imgAcu = copyImage(img);
        for(Process process : Processes)
        {
            process.setImg(imgAcu);
            process.run();  
            imgAcu = process.getOutputImg();
        }
        
        outputImg = copyImage(imgAcu);
    }
    
    
}
