package uce.edu.ec.taller2.practica;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Base {
    public static void main(String[] args) {
        File original = new File("img/perro.jpg");
        File clonPng = new File("img/clonperroPNG.png");

        try{
            BufferedImage bi= ImageIO.read(original);
            int ancho= bi.getWidth();
            int alto= bi.getHeight();

            BufferedImage bi2= new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);


            for(int y=0;y<alto;y++){
                for(int x=0;x<ancho;x++){

                    int pixel1= bi.getRGB(x, y);
                    int a= (pixel1>>24) & 0xFF;
                    int r= (pixel1>>16) & 0xFF;
                    int g= (pixel1>>8) & 0xFF;
                    int b= (pixel1>>0) & 0xFF;


                    //Aqui va la mayoria de codigo
					//int r1= (((r>>6) & 0x3)*255)/3;
                    //int g1= (((g>>6) & 0x3)*255)/3;
                    //int b1= (((b>>6) & 0x3)*255)/3;


                    int pixel2= (a<<24)|(r<<16)|(g<<8)|(b<<0);
                    bi2.setRGB(x, y, pixel2);

                
            }

            }

            ImageIO.write(bi2, "png",clonPng );

            System.out.println("OK");



        }catch(Exception e){
            System.out.println("Error");
        }
        
    }

    static int clamping(int valor){
        return Math.min(255,Math.max(0, valor));
    }

}
