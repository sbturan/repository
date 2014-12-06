/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package processes;

import java.awt.image.WritableRaster;
/**
 *
 * @author Orkun
 */
public class HistogramEq extends Process{
    int[] hist;
    int[] cdf_hist;
    void processHistogramEq()
    {
        //histogram
        CalcHist();
        CalcCDFHist();
        
        outputImg = copyImage(img);
        WritableRaster data = img.getRaster();
        WritableRaster out_data = outputImg.getRaster();
        for (int i = 0; i < data.getWidth(); i++) {
            for (int j = 0; j < data.getHeight(); j++) {
                int pixel = data.getSample(i, j, 0);
                int value = (int) ((cdf_hist[pixel]-Min)*255.0)/(data.getWidth()*data.getHeight());
                out_data.setSample(i, j, 0, value);
            }
        }
    }
    
    void CalcCDFHist()
    {
        cdf_hist = new int[hist.length];
        for(int i = 0; i< cdf_hist.length;i++)
        {
            for(int j = 0; j <=i; j++)
            {
                cdf_hist[i] += hist[j];
            }
        }
    }
    
    void CalcHist()
    {
        CalcMaxMin();
        int histSize = Max - Min;
        WritableRaster data = img.getRaster();
        hist = new int[histSize+1];
        for (int i = 0; i < data.getWidth(); i++) {
            for (int j = 0; j < data.getHeight(); j++) {
                int pixel = data.getSample(i, j, 0);
                hist[pixel-Min]++;
            }
        }
    }
    
    int Max = 0;
    int Min = 0;

    private void CalcMaxMin() {
        WritableRaster data = img.getRaster();
        
        for (int i = 0; i < data.getWidth(); i++) {
            for (int j = 0; j < data.getHeight(); j++) {
                int pixel = data.getSample(i, j, 0);
                if (pixel > Max) {
                    Max = pixel;
                } else if (pixel < Min) {
                    Min = pixel;
                }
            }
        }
    }
    
    
    
    @Override
    public void run() {
        processHistogramEq();
    }
    
}
