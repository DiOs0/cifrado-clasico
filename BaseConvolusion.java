package uce.edu.ec.taller2.practica;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class BaseConvolusion {
    public static void main(String[] args) {
        File original = new File("img/perro.jpg");
        File clonPng = new File("img/perroConvolusion.png");

        try {
            BufferedImage bi = ImageIO.read(original);
            int ancho = bi.getWidth();
            int alto = bi.getHeight();

            BufferedImage bi2 = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);

            // Agregado de convolusion
            int borde = 1;

            float[][] deteccionBorde = {
                    { 0, 1, 0 },
                    { 1, -4, 1 },
                    { 0, 1, 0 }
            };

            for (int y = 0; y < alto; y++) {
                for (int x = 0; x < ancho; x++) {

                    int sumaR = 0;
                    int sumaG = 0;
                    int sumaB = 0;

                    for (int i = -borde; i <= borde; i++) {
                        for (int j = -borde; j <= borde; j++) {

                            int px = Math.max(0, Math.min(ancho - 1, x + i));
                            int py = Math.max(0, Math.min(alto - 1, y + j));

                            int pixel1 = bi.getRGB(px, py);
                            int a = (pixel1 >> 24) & 0xFF;
                            int r = (pixel1 >> 16) & 0xFF;
                            int g = (pixel1 >> 8) & 0xFF;
                            int b = (pixel1 >> 0) & 0xFF;

                            // Codigo Convolusion
                            sumaR += r * deteccionBorde[i + borde][j + borde];
                            sumaG += g * deteccionBorde[i + borde][j + borde];
                            sumaB += b * deteccionBorde[i + borde][j + borde];

                        }

                    }

                    int a1 = 255;
                    int r1 = clamping(sumaR);
                    int g1 = clamping(sumaG);
                    int b1 = clamping(sumaB);

                    int pixel2 = (a1 << 24) | (r1 << 16) | (g1 << 8) | (b1 << 0);
                    bi2.setRGB(x, y, pixel2);

                }
            }
            ImageIO.write(bi2, "png", clonPng);

            System.out.println("OK");

        } catch (Exception e) {
            System.out.println("Error");
        }

    }

    static int clamping(int valor) {
        return Math.min(255, Math.max(0, valor));
    }

}
