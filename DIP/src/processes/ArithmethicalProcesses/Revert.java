/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processes.ArithmethicalProcesses;

import processes.Process;

/**
 *
 * @author Orkun
 */
public class Revert extends Process {

    public enum Coordinate {

        X, Y
    };
    Coordinate coordinate = Coordinate.X;

    public Revert(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    private void revertPictureAccordingToYcoordinate() {
        int RGB;
        int imgWidht = img.getWidth();
        int imgHeight = img.getHeight();
        for (int i = 0; i < imgWidht / 2; i++) {
            for (int j = 0; j < imgHeight; j++) {
                RGB = img.getRGB(i, j);
                img.setRGB(i, j, img.getRGB((imgWidht - 1 - i), j));
                img.setRGB((imgWidht - 1 - i), j, RGB);
            }
        }
    }

    private void revertPictureAccordingToXCoordinate() {
        int RGB;
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        for (int i = 0; i < imgWidth; i++) {
            for (int j = 0; j < imgHeight / 2; j++) {
                RGB = img.getRGB(i, j);
                img.setRGB(i, j, img.getRGB(i, img.getHeight() - 1 - j));
                img.setRGB(i, imgHeight - 1 - j, RGB);
            }
        }
    }

    @Override
    public void run() {
        switch (coordinate) {
            case X:
                revertPictureAccordingToXCoordinate();
                break;
            case Y:
                revertPictureAccordingToYcoordinate();
                break;
        }
    }
}
