package PachetDeLucru;

import java.awt.Color;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Consumer extends Thread {
    private Buffer buffer; //Bufferul care face legatura cu clasa Producer
    private int height;    //inaltimea imaginii
    private int width;	   //latimea imaginii
    private DataOutputStream out;  //variabila de iesire implementata pentru comunicarea prin Pipi cu clasa WriteImage


    public Consumer(Buffer buffer, int height, int width, DataOutputStream out) {
        this.buffer = buffer;
        this.height =height;
        this.width = width;
        this.out = out;
        
    }

    public void run() {
    	
    		Timer timer = new Timer(); //incepe inregistrarea timpului pentru consumer
    		timer.start();
    		int counter = 1; // precum la Producer, acest counter va tin cont cand s-a primit un sfert din imagine
    		int pixel = 0;
	        for(int i=0;i<height*width;i++){ //pentru Consumer nu este necesara pastrarea formei de matrice, deoarece aceasta va primi de pe buffer cate un pixel pe rand si apoi il va trimite mai departe
	            pixel = buffer.get();

	            //Metoda Convert to Grayscale folosind Media Aritmetica
	            int r = (pixel>>16)&0xff;
	            int g = (pixel>>8)&0xff;
	            int b = pixel&0xff;
	         	int avg = (r+g+b)/3;
	         	pixel =  (avg<<16) | (avg<<8) | avg; //reconstruirea pixelului
	         	
	        	try {
	        		   out.writeInt(pixel); //dupa prelucrarea pixelului curent, acesta este trimis spre clasa WriteImage 
	        	} catch (IOException e) {
	        		   e.printStackTrace ();
	        	} 
	        	
	           if(i==height/4*counter*width && counter<4) { //verificarea pentru a constata ca s-a ajuns la o patrime din imagine
	        	   System.out.println("Consumer a primit: "+counter+"/4"+"din imagine");
	        	   counter++;
	           } 
	            
	        }
	        System.out.println("Consumer a terminat de executia!");
	        timer.stop(); //se termina inregistrarea timpului pentru consumer
	        System.out.println("\nTimpul de rulare a lui Consumer este: "+ timer.getRuntime(TimeUnit.NANOSECONDS)+"ns\n");
    	
    }
}
