package processes;

import java.awt.Color;
import java.awt.image.BufferedImage;

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

    @Override
    public void run() {
        processThreshold();
    }
}
