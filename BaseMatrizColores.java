package uce.edu.ec.taller2.practica;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class BaseMatrizColores {

    public static void main(String[] args) {
        File original = new File("img/perro.jpg");
        File destino = new File("img/matrizColorPerro.jpg");

        try {

            BufferedImage bi = ImageIO.read(original);
            int ancho = bi.getWidth();
            int alto = bi.getHeight();

            BufferedImage bi2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

            // Esto es nuevo
            float[][] matriz = {
                    { 1, 0, 0, 0 }, // Red'
                    { 0, 1, 0, 0}, // Green'
                    { 0, 0, 1, 0 } // Blue'
            };

            for (int y = 0; y < alto; y++) {
                for (int x = 0; x < ancho; x++) {
                    int pix1 = bi.getRGB(x, y);

                    int r = (pix1 >> 16) & 0xFF;
                    int g = (pix1 >> 8) & 0xFF;
                    int b = (pix1 >> 0) & 0xFF;

                    int r1 = clamping(Math.round(r * matriz[0][0] +
                            g * matriz[0][1] +
                            b * matriz[0][2] +
                            matriz[0][3]));

                    int g1 = clamping(Math.round(r * matriz[1][0] +
                            g * matriz[1][1] +
                            b * matriz[1][2] +
                            matriz[1][3]));

                    int b1 = clamping(Math.round(r * matriz[2][0] +
                            g * matriz[2][1] +
                            b * matriz[2][2] +
                            matriz[2][3]));

                    int pixel2 = (r1 << 16) | (g1 << 8) | b1;
                    bi2.setRGB(x, y, pixel2);

                }
            }
            ImageIO.write(bi2, "jpg", destino);
            System.out.println("OK");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int clamping(int valor) {
        return Math.min(255, Math.max(0, valor));
    }

}
