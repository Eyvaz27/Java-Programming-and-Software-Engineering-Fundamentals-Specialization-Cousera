
/**
 * Write a description of GrayScaleConverter here.
 * 
 * @author Eyvaz Najafli
 * @version 08/19/2022
 */

import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        // for each pixel in outImage
        for(Pixel pixel: outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            pixel.setRed(average);
            pixel.setBlue(average);
            pixel.setGreen(average);
        }
        return outImage;
    }
    public void testGray(){
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            gray.draw();
        }
    }
    
}
