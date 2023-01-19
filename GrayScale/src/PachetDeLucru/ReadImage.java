package PachetDeLucru;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class ReadImage  extends ImageProcessing{
	//Aceasat este o clasa care citeste imaginea ce va fi folosita pentru procesare in Grayscale
    protected int height;
    protected int width;

    public ReadImage() {
        super();
        this.height = 0;
        this.width = 0;
    }

    public void readImage() {
        while (image == null) {
            System.out.print("Va rog inserati imaginea: ");
            input = keyboard.next(); //numele imaginii este dat de la tastatura

            try {
                image = ImageIO.read(new File(input));
                height = image.getHeight();
                width = image.getWidth();
            } catch (IOException e) {
                System.out.println("Nume incorect! Incercati din nou.");
            }
        }
    }
    public abstract BufferedImage getImage();
   

}
