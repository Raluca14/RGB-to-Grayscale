package PachetDeLucru;

import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class Producer extends Thread{

	private BufferedImage image; //imaginea care urmeaza sa fie transformata in Grayscale
	private int height;  //inaltimea imaginii
	private int width;	//latimea imaginii
	private Buffer buffer;  //buffer ul care face legatura intre Producer si Consumer
	
	
	
	public Producer(BufferedImage image, int height, int width, Buffer buffer )
	{
		this.image = image;
		this.buffer = buffer;
		this.height = height;
		this.width = width;
		
	
	}
	
	public void run(){
		
		Timer timer = new Timer(); //incepe perioade de timp pentru producer
		timer.start();
		
		int heightAuxiliar =  height/4;	//variabila care tine o intaltime auxiliara a imaginii - mai exact 1 patrime 
		int counter = 1; //counter folosit pentru a tine minte la a cata parte din matrice s-a ajuns cu executia
		for(int i = 0; i <height; i++){ //se parcurge toate imaginea - dar la fiecare sfert din matrice Producer va astepta
			timer.start();
			for(int j=0;j < width;j++){
				buffer.put(image.getRGB(j, i)); //functia getRGB pune direct in buffer o variabila de tip int . Aceasta necesita coordonatele (x,y)			
			}	
			if (i == heightAuxiliar*counter && counter<4){   //se verfica ca s-a ajuns la un prag de un sfert
				try{
					System.out.println("Producer a trimis " + counter + "/4 din imagine");
					counter++;
					Thread.sleep(1000); //asteapta sa se termine executia la Consumer ca sa continue citirea
				} catch(InterruptedException e){
					throw new RuntimeException(e);
				}
			} 
		} 
		
		try {
            System.out.println("Producer a trimis imaginea complet"); // dupa trimitea ultimii parti a imaginii Producer mai asteapta odata ca sa se termine si Consumer inainte de a-si termina executia
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
		System.out.println("Producer a terminat executia!"); // in acest punct Producer si-a incheiat executia
		timer.stop(); //aici se opreste inregistrarea timpului pentru producer
		
		System.out.println("\nTimpul de rulare a lui Producer este: "+ timer.getRuntime(TimeUnit.NANOSECONDS)+"ns\n");
	}
	
}
