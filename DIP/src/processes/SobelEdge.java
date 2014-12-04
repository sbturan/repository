/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processes;

import java.awt.image.Raster;

/**
 *
 * @author Orkun
 */
public class SobelEdge extends Process {

    static int[][] Gx = new int[][]{{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
    static int[][] Gy = new int[][]{{1, 0, -1}, {2, 0, -2}, {1, 0, -1}};

    void processSobelOperator() {
        outputImg = copyImage(img);
        int imgHeight = img.getHeight();
        int imgWidth = img.getWidth();
        Raster data = img.getData();

        for (int y = 1; y < imgHeight - 1; y++) {
            for (int x = 1; x < imgWidth - 1; x++) {
                int sumX = 0;
                int sumY = 0;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        try {
                            sumX += data.getSample(x + i, y + j, 0) * Gx[i + 1][j + 1];
                            sumY += data.getSample(x + i, y + j, 0) * Gy[i + 1][j + 1];
                        }
                        catch(Exception hata)
                        {
                            System.out.println( x +" " + y +" " + i +" " + j +" ");
                        }

                    }
                }

                int Mag = (int) Math.sqrt(Math.pow(sumX, 2) + Math.pow(sumY, 2));

                outputImg.setRGB(x, y, Mag);
            }
        }
    }

    @Override
    public void run() {
        processSobelOperator();
    }

}
