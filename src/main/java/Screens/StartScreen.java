package Screens;

import Sprites.Hero;
import Sprites.Sprite;
import Window.GamePane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class StartScreen implements IScreen {
    GamePane gamePane;



    private static final String BACKGROUND_START = "src/main/resources/backgrounds/start.png";
    Color fontsColor = new Color(255,255,255,50);

    //    //PARAMETROS DE CONTROL
    Image backgroundImage;


    public StartScreen( GamePane gamePane){
        this.gamePane = gamePane;
    }

    @Override
    public void startFrame() {

    }

    @Override
    public void addElements() {

    }

    @Override
    public void drawBackGround(Graphics g) {

    }

    @Override
    public void drawSprite(Graphics g) {

    }

    @Override
    public void drawMenu() {

    }

    @Override
    public void checkCollisions(Sprite s) {

    }

    @Override
    public void checkEndLevel() {

    }

    @Override
    public void moveSprites(Sprite s) {

    }


    @Override
    public void manageGameFunctions() {

    }

    @Override
    public void moveMouse(MouseEvent e) {

    }

    @Override
    public void clickMouse(MouseEvent e) {
        gamePane.setEndLevel(true);

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyRelessed(KeyEvent e) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        return false;
    }

    @Override
    public Hero getHero() {
        return null;
    }


    @Override
    public void drawScreen(Graphics g){
        drawBackground(g);
        drawAnimationScreen(g);
        manageGameFunctions();
    }

    /**
     * Metodo encargado de pintar el fondo del panel de juego
     *
     * @param g
     */
    private void drawBackground(Graphics g) {
        File bckg;
        bckg = new File(BACKGROUND_START);
        try {
            backgroundImage = ImageIO.read(bckg);
            backgroundImage = backgroundImage.getScaledInstance(gamePane.getWidth(), gamePane.getHeight(), 4);
        } catch (IOException e) {
            System.out.println("Error al cargar la imagen de fondo");
            System.out.println("Error: " + e.getMessage());
        }
        g.drawImage(backgroundImage, 0, 0, null);
    }


    /**
     * Metodo encargado de gestionar la animacion de la pantalla principal
     */
    private void drawAnimationScreen(Graphics g) {
        //no hace nada
    }
    @Override
    public void resizeScreen(Graphics g) {
        backgroundImage = backgroundImage.getScaledInstance(gamePane.getWidth(), gamePane.getHeight(), 4);
    }

}
