package Screens;

import Window.GamePane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FirstScreen implements IScreen {

    private static final String BACKGROUND_GAME = "floors/floor_grass1.png" ;

    GamePane gamePane;
    Image backgroundImage;


    public FirstScreen(GamePane gamePane){
        this.gamePane = gamePane;
        startFrame();

    }


    @Override
    public void startFrame() {

    }

    @Override
    public void addElements() {

    }

    /**
     * Metodo encargado de pintar los componentes en la pantalla
     * @param g
     */
    @Override
    public void drawScreen(Graphics g) {
        drawBackGround(g);
        drawSprite();


    }

    /**
     * Metodo encargado de redimensionar la pantalla y los componentes que
     * se situen en su interio
     * @param g
     */
    @Override
    public void resizeScreen(Graphics g) {
        backgroundImage = backgroundImage.getScaledInstance(gamePane.getWidth(), gamePane.getHeight(), 4);
    }

    /**
     * Metodo encargado de pintar el background del nivel
     * @param g
     */
    @Override
    public void drawBackGround(Graphics g) {
        File bckg = new File(BACKGROUND_GAME);
        try {
            backgroundImage = ImageIO.read(bckg);
            resizeScreen(g);
        } catch (IOException e) {
            System.out.println("Error al cargar la imagen de fondo");
            System.out.println("Error: " + e.getMessage());
        }
        g.drawImage(backgroundImage, 0, 0, null);
    }


    @Override
    public void drawSprite() {

    }

    @Override
    public void drawMenu() {

    }

    @Override
    public void checkCollisions() {

    }

    @Override
    public void checkEndLevel() {

    }

    @Override
    public void moveSprites() {

    }

    @Override
    public void manageGameFunctions() {

    }

    @Override
    public void moveMouse(MouseEvent e) {

    }

    @Override
    public void clickMouse(MouseEvent e) {

    }

    /**
     * Metodo encargado de gestionar los eventos de teclado,
     * es decir, que sucede en la pantalla cuando se pulsa
     * alguna tecla en concreto
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){

        }
        if(e.getKeyCode() == KeyEvent.VK_A){

        }
        if(e.getKeyCode() == KeyEvent.VK_S){

        }

        if(e.getKeyCode() == KeyEvent.VK_D){

        }

    }
}
