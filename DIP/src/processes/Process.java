package processes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Process implements Runnable{

    String filePath;
    protected BufferedImage img;
    protected BufferedImage outputImg;

    public BufferedImage readImage(String filePath) {
        this.filePath = filePath;

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return img;
    }
    
    public String saveFile(String methodName, String filePath) {
        this.filePath = filePath;
        return saveFile(methodName,this.outputImg, filePath);
    }

    public String saveFile(String methodName, BufferedImage outputImg, String filePath) {

        String newPath;
        String fileType;
        int m = filePath.lastIndexOf('.');
        fileType = filePath.substring(m);
        newPath = filePath.substring(0, m) + "/" + "output" + methodName + fileType;

        new File(newPath).mkdirs();

        File outputfile = new File(newPath);
        try {
           
            ImageIO.write(outputImg, fileType.replace(".", ""), outputfile);
            System.out.println("Image saved :" + newPath);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return newPath;

    }

    public static BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setImg(BufferedImage img) {
        if (img != null) {
            this.img = img;
            outputImg = copyImage(img);
        }
    }

    public BufferedImage getImg() {
        return img;
    }
    
    public BufferedImage getOutputImg() {
        return outputImg;
    }

}
