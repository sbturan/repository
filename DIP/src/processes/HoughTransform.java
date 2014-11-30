/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processes;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Orkun
 */
public class HoughTransform extends Process {

    double d_alfa = 1 * (Math.PI / 360);

    void processHoughTransform() {
        try {
            HoughTransform(this.img);
        } catch (Exception ex) {
            Logger.getLogger(HoughTransform.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    boolean stretchHistogram = false;
    public HoughTransform(boolean stretchHistogram) {
        this.stretchHistogram = stretchHistogram;
    }
    
    

    

    void HoughTransform(BufferedImage img) throws Exception {
        if (img.getType() != BufferedImage.TYPE_BYTE_BINARY) {
            throw new Exception("Input Image Type Must be TYPE_BYTE_BINARY");
        }

        
        int imgWidht = img.getWidth();
        int imgHeight = img.getHeight();
        // hough uzayının boyutlarını hesaplıyoruz
        int houghSpace_widht = (int) (Math.PI / d_alfa);
        double houghSpace_height_d = Math.sqrt(Math.pow(imgWidht, 2) + Math.pow(imgHeight, 2));
        int houghSpace_height = (int) houghSpace_height_d;
        int[][] HoughSpace = new int[houghSpace_widht][houghSpace_height];

        Raster raster = img.getData();
        for (int i = 0; i < imgWidht; i++) {
            for (int j = 0; j < imgHeight; j++) {
                int pixel = raster.getSample(i, j, 0);
                if (pixel == 0) {
                    for (int alfa = 0; alfa < houghSpace_widht; alfa++) {
                        int x = i - (imgWidht / 2);
                        int y = (j - imgHeight / 2);

                        int r = (int) (x * Math.cos(alfa * d_alfa) + (y * Math.sin(alfa * d_alfa)));
                        try {
                            HoughSpace[alfa][(int) (((r / 2) + (houghSpace_height / 2)))]++;
                        } catch (ArrayIndexOutOfBoundsException hata) {
                            System.err.println(houghSpace_widht + "-" + houghSpace_height + " " + alfa + "-" + String.valueOf(r / 2) + (houghSpace_height));
                        }
                    }
                }
            }
        }

        outputImg = new BufferedImage(houghSpace_widht, houghSpace_height, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < houghSpace_widht; i++) {
            for (int j = 0; j < houghSpace_height; j++) {
                outputImg.setRGB(i, j, HoughSpace[i][j]);
            }
        }
        if(stretchHistogram)
            stretch();
    }

    private void stretch() {
        BufferedImage tempImg = copyImage(outputImg);

        int Max = 0;
        int Min = 0;
        int imgWidht = tempImg.getWidth();
        int imgHeight = tempImg.getHeight();
        for (int i = 0; i < imgWidht; i++) {
            for (int j = 0; j < imgHeight; j++) {
                int pixel = tempImg.getRaster().getSample(i, j, 0);
                if (pixel > Max) {
                    Max = pixel;
                }else if(pixel < Min)
                    Min = pixel;
            }
        }
        
        for (int i = 0; i < imgWidht; i++) {
            for (int j = 0; j < imgHeight; j++) {
                int newPixel = ((tempImg.getRaster().getSample(i, j, 0)-Min)/(Max-Min))*65535;
                outputImg.getRaster().setSample(i, j, 0, newPixel);
            }
        }

    }

    @Override
    public void run() {
        processHoughTransform();
    }

}
