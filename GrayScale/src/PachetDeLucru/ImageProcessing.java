package PachetDeLucru;

import java.awt.image.BufferedImage; 
import java.util.Scanner;

public abstract class ImageProcessing { 
	//Aceasta este o clasa abstracta care are toate metodele de tip abstract
	//Este startul ierarhiei de clase care citeste imaginea
	protected BufferedImage image;
    protected String input;
    protected Scanner keyboard = new Scanner(System.in);

    public ImageProcessing() {
        this.image = null;
    }

    public abstract void readImage();	    
}
