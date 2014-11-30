/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package processes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Orkun
 */
public class ThresholdIterative extends Process{
    int thresholdLevel;
    public ThresholdIterative(int thresholdLevel)
    {
        this.thresholdLevel = thresholdLevel;
    }
    public void iterativeThreshold() {  // threshold level, iterasyonun durma noktas�

        Color threshold = new Color(128, 128, 128);

        Color lastThreshold = iterate(threshold, thresholdLevel);
        int imgWidht = img.getWidth();
        int imgHeight = img.getHeight();
        Color white = new Color(0, 0, 0);
        Color black = new Color(255, 255, 255);

        for (int i = 0; i < imgWidht; i++) {
            for (int j = 0; j < imgHeight; j++) {
                if (img.getRGB(i, j) < lastThreshold.getRGB()) {
                    outputImg.setRGB(i, j, white.getRGB());
                } else {
                    outputImg.setRGB(i, j, black.getRGB());
                }
            }
        }

    }

    private Color iterate(Color oldThreshold, int thresholdLevel) {

        ArrayList<Integer> g1 = new ArrayList<Integer>();
        ArrayList<Integer> g2 = new ArrayList<Integer>();

        int imgWidht = img.getWidth();
        int imgHeight = img.getHeight();
        for (int i = 0; i < imgWidht; i++) {
            for (int j = 0; j < imgHeight; j++) {
                if (img.getRGB(i, j) < oldThreshold.getRGB()) {
                    g1.add(img.getRGB(i, j));

                } else {
                    g2.add(img.getRGB(i, j));
                }

            }
        }

        int c1 = getAvarage(g1);
        int c2 = getAvarage(g2);
        Color newThreshold = new Color((c1 + c2) / 2);
        int itl = newThreshold.getRGB() - oldThreshold.getRGB();
        if (itl < 0) {
            itl = itl * (-1);
        }
        System.out.println("oldThreshold//New threshold//c1//c2//g1//g2");
        System.out.println(oldThreshold.getRGB() + "//" + newThreshold.getRGB() + "//" + c1 + "//" + c2 + "//" + g1.size() + "//" + g2.size());
        if (itl > thresholdLevel) {
            newThreshold = iterate(newThreshold, thresholdLevel);

        }

        return newThreshold;
    }

    private int getAvarage(List<Integer> list) {
        int t = 0;
        for (int i = 0; i < list.size(); i++) {
            t = t + (list.get(i) / list.size());
        }

        return t;
    }

    @Override
    public void run() {
        iterativeThreshold();
    }
}
