package Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import processes.Pipeline;
import processes.Smoother;
import processes.Threshold;

public class ProcessAnImageTest {

    final static String filePath = "C:/Users/Orkun/Desktop/plaka.jpg";

    public static void main(String[] Args) throws Exception {
        
        /*Smoother smoothFilter = new Smoother(filePath, 5);
        smoothFilter.run();
        Threshold threshold = new Threshold(filePath);
        threshold.setImg(smoothFilter.getOutputImg());
        threshold.run();
        threshold.saveFile("Avarage5xfilter&threshold");*/
        Pipeline pipeline = new Pipeline(LoadImage(filePath));
        Smoother smoothFilter = new Smoother(5, Smoother.FilterType.MedianFilter);
        Threshold threshold = new Threshold();
        pipeline.AddProcess(smoothFilter);
        pipeline.AddProcess(threshold);
        pipeline.run();
        pipeline.saveFile("pipeline",filePath);
        
    }
    
    public static BufferedImage LoadImage(String filePath)
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return img;
    }
    
/*
    public static int processAvarageFilter(int filterSize) throws Exception {

        Smoother smoothFilter = new Smoother(filePath, filterSize);
        smoothFilter.processAvarageFilter();
        smoothFilter.saveFile(filterSize + "x" + filterSize + " AvarageFilter");
        return 1;
    }

    public static int processMedianFilter(int filterSize) throws Exception {
        Smoother smoothFilter = new Smoother(filePath, filterSize);
        smoothFilter.processMedianFilter();
        smoothFilter.saveFile(filterSize + "x" + filterSize + " MedianFilter");
        return 1;

    }

    public static int revertPictureAccordingToYcoordinate() {
        ArithmethicalProcesses arithmethicalProcesses = new ArithmethicalProcesses(filePath);
        arithmethicalProcesses.revertPictureAccordingToYcoordinate();
        arithmethicalProcesses.saveFile("revertPictureAccordingToYcoordinate");

        return 1;
    }

    public static int revertPictureAccordingToXcoordinate() {
        ArithmethicalProcesses arithmethicalProcesses = new ArithmethicalProcesses(filePath);

        arithmethicalProcesses.revertPictureAccordingToXCoordinate();
        arithmethicalProcesses.saveFile("revertPictureByXcoordinate");
        return 1;
    }

    public static int rotateRight() {
        ArithmethicalProcesses arithmethicalProcesses = new ArithmethicalProcesses(filePath);
        arithmethicalProcesses.rotateRight();
        arithmethicalProcesses.saveFile("rotateRight");
        return 1;
    }

    public static int rotateLeft() {
        ArithmethicalProcesses arithmethicalProcesses = new ArithmethicalProcesses(filePath);
        arithmethicalProcesses.rotateLeft();
        arithmethicalProcesses.saveFile("rotateLeft");
        return 1;
    }

    public static int processThreshold() {
        Threshold threshold = new Threshold(filePath);
        threshold.processThreshold();
        threshold.saveFile("threshold");

        return 1;
    }
*/
}
