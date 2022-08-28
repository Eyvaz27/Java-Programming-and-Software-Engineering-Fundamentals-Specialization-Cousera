
/**
 * Write a description of ImageSaver here.
 * 
 * @author Eyvaz Najafli 
 * @version 08/19/2022
 */
import edu.duke.*;
import java.io.File;

public class ImageSaver {
    public void doSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            String inName = inImage.getFileName();
            String newName = "copy-" + inName;
            inImage.setFileName(newName);
            inImage.draw();
            inImage.save();
        }
    }
}
