package PachetDeLucru;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class WriteImage extends Thread {
	private DataInputStream in; //variabila de intrare folosita pentru comunicarea prin Pipe cu clasa Consumer
	private int width; //latimea imaginii
	private int height; //inaltimea imaginii
	private String name; //numele fisierului de isire


    { //exemplu de bloc de initiere
        name = "Result.bmp";
    }
	
	public WriteImage(int width, int height, DataInputStream in){
		this.width = width;
		this.height = height;
		this.in = in;
	}
	public WriteImage(int width, int height, DataInputStream in,String name){
		this.width = width;
		this.height = height;
		this.in = in;
		this.name = name;
	}
	public void run(){ 
		Timer timer = new Timer(); //incepe inregistrarea timpului pentru WriteImage
		 timer.start();
		int heightAuxiliar = height/4; 
        int counter = 1;
        
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //variabila folosita pentru a scrie imaginea
        for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				try {			
					resultImage.setRGB(j, i, in.readInt());
				}			
				catch (IOException e) {
					e.printStackTrace();
				}
		
			}
			
			if (i == heightAuxiliar * counter) { //verificare daca s-a ajuns la o patrime din imagine
                System.out.println("WriteResult received " + counter + "/4 from result image");
                counter++;          
                
            }
        }
        try {
            System.out.println("WriteResult a primit intreaga imagine"); //Dupa ce a primit intreaga imagine WriteImage asteapata ca aceasta sa se si scrie
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        try {
            File file = new File(name); //fisiereul in care va fi scrisa imaginea
            ImageIO.write(resultImage, "bmp", file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("WriteResult a terminat executia!"); 
        timer.stop(); //se termina inregistrarea timpului pentru WriteImage
        System.out.println("\nTimpul de runtime a lui WriteImage este:"+timer.getRuntime(TimeUnit.NANOSECONDS)+"ns\n");
	}
	
}



