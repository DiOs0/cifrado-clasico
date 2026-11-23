package uce.edu.ec.taller2.practica;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class BaseHistograma {

    public static void main(String[] args) {
        File original = new File("img/perro.jpg");
        File destino = new File("img/histograma1.jpg");

        try {
            BufferedImage bi = ImageIO.read(original);
            
            int ancho = bi.getWidth();
            int alto = bi.getHeight();        

            BufferedImage biHisto = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

            //Cambio
            int [] histoRed= new int [256];
            int [] histoGreen= new int [256];
            int [] histoBlue= new int [256];

            for (int y = 0; y < alto; y++) {
                for (int x = 0; x < ancho; x++) {

                    int pixel1 = bi.getRGB(x, y);

                    int r= (pixel1 >> 16) & 0xFF;
                    int g= (pixel1 >> 8) & 0xFF;
                    int b= pixel1 & 0xFF;
                    
                    histoRed[r]++;
                    histoGreen[g]++;
                    histoBlue[b]++;

                }
            }
            
            int maxRed = Arrays.stream(histoRed).max().getAsInt();
            int maxGreen = Arrays.stream(histoGreen).max().getAsInt();
            int maxBlue = Arrays.stream(histoBlue).max().getAsInt();

            int maxInten = Math.max(maxRed,Math.max(maxGreen, maxBlue));
            
            Graphics2D gr= biHisto.createGraphics();
            
            //Limpiar primero
            gr.setColor(Color.WHITE);
            
            gr.fillRect(0, 0, ancho, alto);
            gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
            
            float escalaX= (float) ancho/256;
            float escalaY=(float)alto/maxInten;
            
            //Histograma rojo
            gr.setColor(Color.RED);
            for (int i=1;i<histoRed.length;i++) {
            	
            	int x1=(int) ((i-1)*escalaX);
            	int y1=(int) (alto - histoRed[i-1]*escalaY);
            	
            	int x2=(int) ((i)*escalaX);
            	int y2=(int) (alto - histoRed[i]*escalaY);
            	
            	gr.drawLine(x1, y1, x2, y2);
				
			}

            //Histograma verde
            gr.setColor(Color.GREEN);

            for (int i = 1; i < histoGreen.length; i++) {

                int x1 = (int) ((i - 1) * escalaX);
                int y1 = (int) (alto - histoGreen[i - 1] * escalaY);

                int x2 = (int) (i * escalaX);
                int y2 = (int) (alto - histoGreen[i] * escalaY);

                gr.drawLine(x1, y1, x2, y2);
            }


            //Histograma azul
            gr.setColor(Color.BLUE);

            for (int i = 1; i < histoBlue.length; i++) {

                int x1 = (int) ((i - 1) * escalaX);
                int y1 = (int) (alto - histoBlue[i - 1] * escalaY);

                int x2 = (int) (i * escalaX);
                int y2 = (int) (alto - histoBlue[i] * escalaY);

                gr.drawLine(x1, y1, x2, y2);
            }
        			
            
            ImageIO.write(biHisto, "jpg", destino);
            System.out.println("OK");

        } catch (Exception e) {
            System.err.println("Error procesando la imagen: " + e.getMessage());
        }
    }
}
