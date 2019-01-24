package Screens;

import Sprites.Hero;
import Window.GamePane;

import javax.imageio.ImageIO;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Sprites.*;

public class FirstScreen implements IScreen {

    private static final String BACKGROUND_GAME = "floors/floor_grass1.png";
    private static final int NUM_ENEMIES = 10;

    private final static int ENEMY_HP = 10;
    private final static int ENEMY_ATK = 5;
    private final static int ENEMY_DEF = 5;

    private final static int HERO_HP = 10;
    private final static int HERO_ATK = 5;
    private final static int HERO_DEF = 5;

    private final static int MAX_NUM_ITEMS = 5;


    GamePane gamePane;
    Image backgroundImage;
    ArrayList<Sprite> sprites;

    boolean endLevel;
    boolean gameOver;
    double score;

    Hero hero;
    Enemy[] enemies;
    Item[] items;
    private boolean isKeyPressed;


    public FirstScreen(GamePane gamePane) {
        this.gamePane = gamePane;
        startFrame();
        addElements();


    }


    @Override
    public void startFrame() {

        this.endLevel = false;
        this.gameOver = false;
        this.sprites = new ArrayList<Sprite>();
        this.enemies = new Enemy[NUM_ENEMIES];
        this.items = new Item[new Random().nextInt(MAX_NUM_ITEMS)];
        manageGameFunctions();
    }


    //COLECCION DE METODOS PARA INSTANCIAR ELEMENTOS EN LA PANTALLA
    @Override
    public void addElements() {
        addHero();
        addEnemies();
        addItems();
    }

    /**
     * Metodo encargado de instanciar al personaje "heroe" en el juego
     */
    private void addHero() {
        Random rd = new Random();
        this.hero = new Hero();
        //parametros de posicion
        hero.setPosX(0);
        hero.setPosY(0);
        //parametros de dimension
        hero.setWidth(80);
        hero.setHeight(120);
        //parametros de velocidad

        hero.setvX(0);
        hero.setvY(0);
        //parametros de recursos gráficos y gestion de los mismos
        hero.setFileImage(new File("src/main/resources/hero/walk/walk_00000.png"));
        hero.refreshBuffer();
        //asignacion de id
        hero.setId("hero");
        //asignacion de atributos del heroe
        hero.setAlive(true);
        hero.setAtk(HERO_ATK);
        hero.setDef(HERO_DEF);
        hero.setTotalHp(HERO_HP);
        hero.setUser(gamePane.getUserName());
        sprites.add(hero);
    }

    /**
     * Metodo encargado de generar e instanciar los enemigos
     */
    private void addEnemies() {
        //TODO realizar la implementacion de los parametros propios de los enemigos.
        for (int i = 0; i < enemies.length; i++) {
            Enemy enemy = new Enemy();
            enemy.setPosX(gamePane.getWidth() / 2);
            enemy.setPosY(gamePane.getHeight() / 2);
            //parametros de dimension
            enemy.setWidth(80);
            enemy.setHeight(120);
            //parametros de velocidad
            enemy.setvX(0);
            enemy.setvY(0);
            //parametros de recursos gráficos y gestion de los mismos
            enemy.setFileImage(new File("src/main/resources/enemy/walk/walk_00000.png"));
            enemy.refreshBuffer();
            //asignacion de id
            enemy.setId("enemy-" + i);
            //asignacion de atributos del enemigo
            enemy.setAlive(true);
            enemy.setAtk(ENEMY_ATK);
            enemy.setDef(ENEMY_DEF);
            enemy.setTotalHp(ENEMY_HP);
            enemies[i] = enemy;
            sprites.add(enemy);
        }
    }

    /**
     * Metodo encargado de generar e instanciar los enemigos
     */
    private void addItems() {

        for (int i = 0; i < items.length; i++) {
            Item item = new Item();
            //parametros de posicion
            item.setPosX(800);
            item.setPosY(600);
            //parametros de dimension
            item.setWidth(30);
            item.setHeight(30);
            //parametros de velocidad
            item.setvX(0);
            item.setvY(0);
            //parametros de recursos gráficos y gestion de los mismos
            item.setFileImage(new File("src/main/resources/attack_potion.png"));
            item.refreshBuffer();
            item.setName("potion");
            item.setDescription("Poción mágica, si se toma sube el ataque");
            items[i] = item;
            sprites.add(item);
        }
    }


    /**
     * Metodo encargado de pintar los componentes en la pantalla
     *
     * @param g
     */
    @Override
    public void drawScreen(Graphics g) {
        drawBackGround(g);
        drawSprite(g);
    }

    /**
     * Metodo encargado de redimensionar la pantalla y los componentes que
     * se situen en su interio
     *
     * @param g
     */
    @Override
    public void resizeScreen(Graphics g) {
        backgroundImage = backgroundImage.getScaledInstance(gamePane.getWidth(), gamePane.getHeight(), 4);
    }

    /**
     * Metodo encargado de pintar el background del nivel
     *
     * @param g
     */
    @Override
    public void drawBackGround(Graphics g) {
        File bckg = new File("src/main/resources/floors/floor_grass1.png");
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
    public void moveSprites(Sprite s) {
        s.refreshBuffer();
        if (s instanceof Hero && hero.isMoving()) {
            s.moveSprite();
            s.refreshBuffer();
        }
        if (s instanceof Enemy) {

        }
    }


    /**
     * Este metodo se encargará de gestionar todas las funciones del sistema de
     * juego en esta pantalla.
     */
    @Override
    public void manageGameFunctions() {
        for (Sprite s : sprites) {
            moveSprites(s);
            // checkWallsCollision();
            // checkAsteroidShooted();
        }
        //destroySprites();
        //checkEndGame();
    }

    @Override
    public void moveMouse(MouseEvent e) {

    }

    @Override
    public void clickMouse(MouseEvent e) {
//        this.previousMouseY = e.getY();
//        this.previousMouseX = e.getX();
//        hero.setMoving(true);

    }

    /**
     * Metodo encargado de gestionar los eventos de teclado,
     * es decir, que sucede en la pantalla cuando se pulsa
     * alguna tecla en concreto
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        isKeyPressed = true;
        hero.setMoving(true);
    }

    @Override
    public void keyRelessed(KeyEvent e) {
        isKeyPressed = false;
        hero.setMoving(false);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        synchronized (GamePane.class) {
            switch (e.getID()) {
                case KeyEvent.KEY_PRESSED:
                    int keyCode = e.getKeyCode();
                    switch (keyCode) {
                        case KeyEvent.VK_W:
                            hero.setMoving(true);
                            hero.setMoveAnimation("N");
                            hero.selectAnimationSprite();
                            hero.setvX(0);
                            hero.setvY(-5);
                            break;
                        case KeyEvent.VK_S:
                            hero.setMoving(true);
                            hero.setMoveAnimation("S");
                            hero.selectAnimationSprite();
                            hero.setvX(0);
                            hero.setvY(5);
                            break;

                        case KeyEvent.VK_A:
                            hero.setMoveAnimation("W");
                            hero.selectAnimationSprite();
                            hero.setvX(-5);
                            hero.setvY(0);
                            hero.setMoving(true);
                            break;
                        case KeyEvent.VK_D:
                            hero.setMoving(true);
                            hero.setMoveAnimation("E");
                            hero.selectAnimationSprite();
                            hero.setvX(5);
                            hero.setvY(0);
                            break;
                    }
                    break;
                case KeyEvent.KEY_RELEASED:
                    isKeyPressed = false;
                    hero.setMoving(false);
                    break;
            }
            return false;
        }
    }

}