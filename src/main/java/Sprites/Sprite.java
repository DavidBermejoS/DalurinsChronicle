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


    public Sprite(int posX, int posY, int vX, int vY, String id, String[] imageRoutes) {
        this.posX = posX;
        this.posY = posY;
        this.vX = vX;
        this.vY = vY;
        this.id = id;
        this.imageRoutes = imageRoutes;
    }

    public Sprite() {

    }

    public void animateSprite() {

    }

    public void refreshBuffer() {
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();
        try {
            BufferedImage imagenSprite = ImageIO.read(fileImage);
            g.drawImage(imagenSprite.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            g.dispose();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            g.setColor(color);
            g.fillRect(0, 0, width, height);
            g.dispose();
        }
    }

    public BufferedImage getBuffer() {
        return buffer;
    }



    public boolean checkCollision(Sprite other) {
        return false;
    }

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
