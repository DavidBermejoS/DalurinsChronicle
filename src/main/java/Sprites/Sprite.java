package Sprites;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <h2>Clase Sprite</h2>
 * Esta clase recogera los atributos basicos de un sprite para
 * su pintado.
 *
 * @author David Bermejo Simon
 */
public class Sprite {
    public String[] allDirections = {"N", "S", "SW", "SE", "W", "E", "NW", "NE"};
    public final static int KEY_N = 0;
    public final static int KEY_W = 1;
    public final static int KEY_S = 2;
    public final static int KEY_E = 3;


    int posX;
    int posY;
    int width;
    int height;
    double vX;
    double vY;
    double vTotal;

    int colliderTaxX;
    int colliderTaxY;

    boolean collide;

    Color color;
    BufferedImage buffer;
    BufferedImage imageSprite;
    String id;
    File fileImage;
    int countAnimatorPhase;


    public Sprite(int posX, int posY, int vX, int vY, String id) {
        this.posX = posX;
        this.posY = posY;
        this.vX = vX;
        this.vY = vY;
        this.id = id;
    }

    public Sprite() {
        countAnimatorPhase = 0;
        //todo METIDO FONDO DE COLOR AZUL PARA VER LAS SUPERFICIES DE LOS COLLIDERS DE LOS PERSONAJES
        this.color = new Color(0, 0, 255, 255);
    }


    /**
     * Metodo encargado de refrescar el buffer del sprite para las animaciones.
     */
    public void refreshBuffer() {
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();
        if (imageSprite != null) {
            g.drawImage(imageSprite, 0, 0, this.width - 20, this.height, null);
            g.dispose();
        } else {
            g.setColor(new Color(255, 255, 255, 0));
            g.fillRect(0, 0, width, height);
            g.dispose();

        }

    }


    /**
     * Metodo encargado de mover el sprite por la pantalla (metodo generico, otras clases pueden
     * tener el suyo propio)
     */
    public void moveSprite() {
        this.posX += vX;
        this.posY += vY;
    }


    /**
     * Metodo encargado de crear un collider cuadrado y determinar si un sprite
     * colisiona con otro
     *
     * @param s2 : sprite a comparar
     * @return check : true si colisionan, false si no.
     */

//    public boolean squareCollider(Sprite s2) {
//        boolean collidesX = false, collidesY = false;
//
//        //calculo de la colision en el eje horizontal
//        if (this.getPosX() < s2.getPosX()) {
//            int rightBorder = this.getPosX() + this.getWidth();
//            if (rightBorder >= s2.getPosX()) {
//                collidesX = true;
//            }
//        } else {
//            int rightBorder = s2.getPosX() + s2.getWidth();
//            if (rightBorder >= this.getPosX()) {
//                collidesX = true;
//            }
//        }
//
//        //calculo de la colision en el eje vertical
//        if (this.getPosY() < s2.getPosY()) {
//            int bottomBorder = this.getPosY() + this.getHeight();
//            if (bottomBorder >= s2.getPosY()) {
//                collidesY = true;
//            }
//        } else {
//            int bottomBorder = s2.getPosY() + s2.getHeight();
//            if (bottomBorder >= this.getPosY()) {
//                collidesY = true;
//            }
//        }
//
//        return collidesX && collidesY;
//    }


    /**
     * Metodo encargado de crear un collider circular y determinar si un sprite
     * colisiona con otro
     * @return check : true si colisionan, false si no.
     */
    public boolean circleCollider(Sprite s2) {
        //valor entero para representar la distancia
        double d;
        int radioS1=this.getWidth()/2;
        int radioS2=s2.getWidth()/2;

        //valor entero para representar la suma de los radios
        int plusRadios = radioS1 + radioS2;
        boolean collides;

        //vector de 2 dimensiones con el valor x e y del centro del sprite original.
        int[] center1 = {this.getPosX() - radioS1, this.getPosY() - radioS1};
        //vector de 2 dimensiones con el valor x e y del centro del sprite s2.
        int[] center2 = {s2.getPosX() - radioS2, s2.getPosY() - radioS2};

        if (checkCenterCloseness(center1, center2) == 0) {
            d = Math.sqrt(Math.pow(center2[0]-center1[0], 2) + Math.pow(center2[1]-center1[1], 2));
        } else {
            d = Math.sqrt(Math.pow(center1[0]-center2[0], 2) + Math.pow(center1[1]-center2[1], 2));
        }

        if (d <= plusRadios) {
            collides = true;
        } else {
            collides = false;
        }
        return collides;
    }

    /**
     * Metodo encargado de comprobar cual de los puntos es más cercano al eje de coordenadas 0,0
     *
     * @param center1
     * @param center2
     * @return 0 si el centro 1 es el mas cercano, 1 en caso contrario.
     */
    private int checkCenterCloseness(int[] center1, int[] center2) {
        double d1, d2;
        int result;
        d1 = Math.sqrt(Math.pow(center1[0], 2) + Math.pow(center1[1], 2));
        d2 = Math.sqrt(Math.pow(center2[0], 2) + Math.pow(center2[1], 2));
        if (d1 <= d2) {
            result = 0;
        } else {
            result = 1;
        }
        return result;
    }

    //GETTERS Y SETTERS

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public double getvX() {
        return vX;
    }

    public void setvX(double vX) {
        this.vX = vX;
    }

    public double getvY() {
        return vY;
    }

    public void setvY(double vY) {
        this.vY = vY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public void setBufferByRoute(String route) {
        try {
            imageSprite = ImageIO.read(new File(route));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public File getFileImage() {
        return fileImage;
    }

    public void setFileImage(File fileImage) {
        this.fileImage = fileImage;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getImageSprite() {
        return imageSprite;
    }

    public void setImageSprite(BufferedImage imageSprite) {
        this.imageSprite = imageSprite;
    }

    public int getColliderTaxX() {
        return colliderTaxX;
    }

    public void setColliderTaxX(int colliderTaxX) {
        this.colliderTaxX = colliderTaxX;
    }

    public int getColliderTaxY() {
        return colliderTaxY;
    }

    public void setColliderTaxY(int colliderTaxY) {
        this.colliderTaxY = colliderTaxY;
    }

    public boolean isCollide() {
        return collide;
    }

    public void setCollide(boolean collide) {
        this.collide = collide;
    }
}
