package processes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import sun.awt.image.WritableRasterNative;

public class Threshold extends Process {

    public Threshold(String filePath) {
        img = readImage(filePath);
        outputImg = copyImage(img);
    }

    public Threshold(BufferedImage img) {
        this.img = img;
        outputImg = copyImage(img);
    }
    
    int threshold;
    public Threshold(int threshold) {
        super();
        this.threshold = threshold;
    }

    private void processThreshold() {
        int red = 128;
        int green = 128;
        int blue = 128;

        Color color = new Color(red, green, blue);
        Color whiteColor = new Color(0, 0, 0);
        Color blackColor = new Color(255, 255, 255);
        
        BufferedImage outputImg = copyImage(img);
        
        
        int imgWidht = img.getWidth();
        int imgHeight = img.getHeight();
        for (int i = 0; i < imgWidht; i++) {
            for (int j = 0; j < imgHeight; j++) {
                if (img.getRGB(i, j) < color.getRGB()) {
                    outputImg.setRGB(i, j, blackColor.getRGB());
                } else {
                    outputImg.setRGB(i, j, whiteColor.getRGB());
                }
            }
        }
        
        this.outputImg = new BufferedImage(outputImg.getWidth(), outputImg.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        this.outputImg.getGraphics().drawImage(outputImg, 0, 0, null);
    }
    
    private void processThresholdGray() {
        int imgWidht = img.getWidth();
        int imgHeight = img.getHeight();
        
        outputImg = new BufferedImage(imgWidht, imgHeight, BufferedImage.TYPE_BYTE_BINARY);
        
        Raster data = img.getData();
        WritableRaster outputdata = outputImg.getRaster();
        for (int i = 0; i < imgWidht; i++) {
            for (int j = 0; j < imgHeight; j++) {
                if (data.getSample(i, j, 0) < threshold) {
                    outputdata.setSample(i, j, 0, 1);
                } else {
                    outputdata.setSample(i, j, 0, 0);
                }
            }
        }
        
        outputImg.setData(outputdata);
        
        //this.outputImg = new BufferedImage(outputImg.getWidth(), outputImg.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        //this.outputImg.getGraphics().drawImage(outputImg, 0, 0, null);
    }

    @Override
    public void run() {
        if(img.getType() == BufferedImage.TYPE_BYTE_GRAY)
            processThresholdGray();
        else
            processThreshold();
    }
}
