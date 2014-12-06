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
    
    public Pipeline(BufferedImage img, String filePath)
    {
        this.filePath = filePath;
        this.img = img;
        outputImg = copyImage(img);
    }
    
    public void AddProcess(Process process)
    {
        if(process.filePath == null)
                    process.filePath = filePath;
        Processes.add(process);
        
    }
    
    @Override
    public void run() {
        BufferedImage imgAcu = copyImage(img);
        int i = 0;
        
        for(Process process : Processes)
        {
            long begin = System.currentTimeMillis();
            process.setImg(imgAcu);
            process.run();  
            long end = System.currentTimeMillis();
            imgAcu = process.getOutputImg();
            
            saveFile(String.valueOf(i), imgAcu, filePath);
            
            System.out.println((end-begin) +"ms");
            
            i++;
        }
        
        outputImg = copyImage(imgAcu);
    }
    
    
}
