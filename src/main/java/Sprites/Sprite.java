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
    int vX;
    int vY;
    Color color;
    BufferedImage buffer;
    String id;
    String[] imageRoutes;
    File fileImage;
    int countAnimatorPhase;
    //TODO hacer los tiempos de descanso de las animaciones
    int countAnimatorPhaseRest;


    public Sprite(int posX, int posY, int vX, int vY, String id, String[] imageRoutes) {
        countAnimatorPhase = 0;
        this.posX = posX;
        this.posY = posY;
        this.vX = vX;
        this.vY = vY;
        this.id = id;
        this.imageRoutes = imageRoutes;
    }

    public Sprite() {


    }

    /**
     * Metodo encargado de refrescar el buffer del sprite para las animaciones.
     */
    public void refreshBuffer() {
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();
        try {
            //TODO realizar el cambio de ImageIO y realizar la lectura en la instanciacion del sprite.
            //TODO una vez realizado el cambio señalar el bufferImage segun la imagen que sea
            BufferedImage imagenSprite = ImageIO.read(fileImage);
            g.drawImage(imagenSprite, 0, 0,this.width,this.height, null);
            g.dispose();
        } catch (IOException ex) {
            g.setColor(color);
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

    public int getvX() {
        return vX;
    }

    public void setvX(int vX) {
        this.vX = vX;
    }

    public int getvY() {
        return vY;
    }

    public void setvY(int vY) {
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

    public String[] getImageRoutes() {
        return imageRoutes;
    }

    public void setImageRoutes(String[] imageRoutes) {
        this.imageRoutes = imageRoutes;
    }


}
