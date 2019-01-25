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
    public String[] allDirections = {"N", "W", "S", "E", "NW", "NE", "SW", "SE"};
    public final static int VAL_N = 0;
    public final static int VAL_W = 1;
    public final static int VAL_S = 2;
    public final static int VAL_E = 3;
    public final static int VAL_NW = 4;
    public final static int VAL_NE = 5;
    public final static int VAL_SW = 6;
    public final static int VAL_SE = 7;


    int posX;
    int posY;
    int width;
    int height;
    double vX;
    double vY;
    Color color;
    BufferedImage buffer;
    BufferedImage imageSprite;
    String id;
    File fileImage;
    int countAnimatorPhase;

    BufferedImage[][] walkingImagesLine;
    BufferedImage[] actualAnimationLine;
    



    public Sprite(int posX, int posY, int vX, int vY, String id) {
        this.posX = posX;
        this.posY = posY;
        this.vX = vX;
        this.vY = vY;
        this.id = id;
    }

    public Sprite() {
        countAnimatorPhase = 0;
        this.walkingImagesLine = new BufferedImage[8][10];
//        this.color = new Color(0,0,0,255);
    }


    /**
     * Metodo encargado de refrescar el buffer del sprite para las animaciones.
     */
    public void refreshBuffer() {
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();
        if(imageSprite!=null){
            g.drawImage(imageSprite, 0, 0,this.width,this.height, null);
            g.dispose();
        }else{
            g.setColor(new Color(255,255,255,0));
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


    public boolean checkCollision(Sprite other) {
        return false;
    }

    /**
     * Metodo encargado de crear un collider cuadrado y determinar si un sprite
     * colisiona con otro
     * @param s2 : sprite a comparar
     * @return check : true si colisionan, false si no.
     */

    public boolean squareCollider(Sprite s2){
        boolean collidesX =false , collidesY = false;

        //calculo de la colision en el eje horizontal
        if(this.getPosX()<s2.getPosX()){
            int rightBorder = this.getPosX()+this.getHeight()-60;
            if(rightBorder>=s2.getPosX()){
                collidesX=true;
            }
        }else{
            int rightBorder = s2.getPosX()+(s2.getWidth()-100);
            if(rightBorder>= this.getPosX()){
                collidesX=true;
            }
        }

        //calculo de la colision en el eje vertical
        if(this.getPosY()<s2.getPosY()){
            int bottomBorder = this.getPosY()+this.getHeight()-60;
            if(bottomBorder>= s2.getPosY()){
                collidesY=true;
            }
        }else{
            int bottomBorder = s2.getPosY()+s2.getWidth()-25;
            if(bottomBorder>=this.getPosY()){
                collidesY=true;
            }
        }

        return collidesX && collidesY;
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

    public void setBufferByRoute(String route){
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
}
