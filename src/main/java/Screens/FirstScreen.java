package Screens;

import Sprites.Hero;
import Window.GamePane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Sprites.*;

public class FirstScreen implements IScreen {

    private static final String BACKGROUND_GAME = "floors/floor_grass1.png" ;

    GamePane gamePane;
    Image backgroundImage;
    ArrayList<Sprite> sprites;

    boolean endLevel;
    boolean gameOver;
    double score;


    public FirstScreen(GamePane gamePane){
        this.gamePane = gamePane;
        startFrame();

    }


    @Override
    public void startFrame() {
        this.endLevel = false;
        this.gameOver = false;
        this.sprites = new ArrayList<Sprite>();
        addElements();
    }

    @Override
    public void addElements() {
        Hero hero = new Hero();
        sprites.add(hero);
        for (int i = 0; i < 10; i++) {
            Enemy enemy = new Enemy();
            sprites.add(enemy);
            //TODO realizar la implementacion de los parametros propios de los enemigos.
        }


        int randomGenerate = new Random().nextInt(5);

        for (int i = 0; i < randomGenerate; i++) {
            Item item = new Item();
            sprites.add(item);

            //TODO realizar la implementacion de los parametros propios de los items que apareceran
        }

    }

    /**
     * Metodo encargado de pintar los componentes en la pantalla
     * @param g
     */
    @Override
    public void drawScreen(Graphics g) {
        drawBackGround(g);
//        drawSprite();
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
    public void drawSprite(Graphics g) {
        for (Sprite s : sprites) {
            g.drawImage(
                    s.getBuffer(),
                    s.getPosX(),
                    s.getPosY(),
                    s.getWidth(),
                    s.getHeight(),
                    s.getColor(),
                    null
            );
        }
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
        public void moveSprites(){

        }

        @Override
        public void manageGameFunctions(){

        }

        @Override
        public void moveMouse(MouseEvent e){

        }

        @Override
        public void clickMouse(MouseEvent e){

        }

        /**
         * Metodo encargado de gestionar los eventos de teclado,
         * es decir, que sucede en la pantalla cuando se pulsa
         * alguna tecla en concreto
         * @param e
         */
        @Override
        public void keyPressed (KeyEvent e){
            if (e.getKeyCode() == KeyEvent.VK_W) {

            }
            if (e.getKeyCode() == KeyEvent.VK_A) {

            }
            if (e.getKeyCode() == KeyEvent.VK_S) {

            }

            if (e.getKeyCode() == KeyEvent.VK_D) {

            }

        }

    }