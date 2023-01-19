package PachetDeTest;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;
import java.util.Timer;

import javax.imageio.ImageIO;

import PachetDeLucru.Buffer;
import PachetDeLucru.Consumer;
import PachetDeLucru.ImageInfo;
import PachetDeLucru.Producer;
import PachetDeLucru.WriteImage;

public class TestareTema {

	public static void main(String[] args) throws IOException {
		//Pipe
		PipedOutputStream pipeOut = new PipedOutputStream();
		PipedInputStream pipeIn = new PipedInputStream(pipeOut);
		DataOutputStream out = new DataOutputStream(pipeOut);
		DataInputStream in = new DataInputStream(pipeIn);
		
		//Citirea imaginii
		Buffer buffer = new Buffer();

		ImageInfo imageInfo = new ImageInfo();
	    imageInfo.readImage();
	    BufferedImage image = imageInfo.getImage();
	    int h = imageInfo.getHeight();
		int w = imageInfo.getWidth();
	    
		
		//Thereduri
		
		Producer producer = new Producer( image, h, w, buffer);
		Consumer consumer = new Consumer (buffer,h,w,out);
		WriteImage writeImage = new WriteImage(w,h,in);
		
		
		producer.start();
		consumer.start();
		writeImage.start();
	}

}
