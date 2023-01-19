package PachetDeLucru;

import java.awt.image.BufferedImage;

public class ImageInfo extends ReadImage implements InfoImageInterface { 
	//Aceasta este clasa care termina ierhia de clase care citeste imaginea
	//De asemenea tot aceasta clasa imenteaza si interfata InfoImageInterface
	public ImageInfo(){
		super();
	}
	
    public BufferedImage getImage(){ 
        return this.image;
    }
	
	@Override
    public int getWidth(){
        return this.width;
    }
	 @Override
    public int getHeight(){
        return this.height;
    }


}
