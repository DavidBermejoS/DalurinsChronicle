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

/**
 * <h2>Clase FirstScreen</h2>
 * Esta clase compone la gestión de gráficos y de sistema de juego que se empleará para el primer nivel / pantalla
 *
 * @author David Bermejo Simon
 */
public class FirstScreen implements IScreen {

    private static final String BACKGROUND_GAME = "floors/floor_grass1.png";
    private static final int NUM_ENEMIES = 1;

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
    boolean[] whatKeyPressed;

    double score;

    Hero hero;
    Enemy[] enemies;
    Item[] items;


    public FirstScreen(GamePane gamePane) {
        this.gamePane = gamePane;
        startFrame();
        addElements();


    }


    /**
     * Metodo implementado por la interfaz IScreen
     * Este metodo gestiona la instanciacion de las variables básicas para la ejecución
     * del juego en esta pantalla. Además realiza la llamada a la funcion manageGameFunctions para que
     * el sistema comience a funcionar por si mismo
     *
     * @see #manageGameFunctions()
     */
    @Override
    public void startFrame() {
        this.endLevel = false;
        this.gameOver = false;
        this.sprites = new ArrayList<Sprite>();
        this.whatKeyPressed = new boolean[4];
        for (int i = 0; i < whatKeyPressed.length; i++) {
            whatKeyPressed[i] = false;
        }
        this.enemies = new Enemy[NUM_ENEMIES];
        this.items = new Item[new Random().nextInt(MAX_NUM_ITEMS)];
        manageGameFunctions();
    }


    //COLECCION DE METODOS PARA INSTANCIAR ELEMENTOS EN LA PANTALLA

    /**
     * Metodo implementado por la interfaz IScreen
     * Este metodo gestiona la instanciacion de los elementos que se vayan a utilizar durante la partida
     */
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
        hero.setHeight(140);
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
            enemy.setPosX(500);
            enemy.setPosY(400);
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
            item.setFileImage(new File("src/main/resources/potions/attack_potion.png"));
            item.refreshBuffer();
            item.setName("potion");
            item.setDescription("Poción mágica, si se toma sube el ataque");
            items[i] = item;
            sprites.add(item);
        }
    }

    //COLECCION DE METODOS DE AJUSTES GRÁFICOS Y PINTAR ELEMENTOS EN PANTALLA

    /**
     * Metodo implementado por la interfaz IScreen
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
     * Metodo implementado por la interfaz IScreen
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
     * Metodo implementado por la interfaz IScreen
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

    /**
     * Metodo implementado por la interfaz IScreen
     * Metodo encargado de pintar los sprites en la pantalla
     *
     * @param g
     */
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

    /**
     * Metodo implementado por la interfaz IScreen
     * Metodo encargado de mover los Sprites por la pantalla
     *
     * @param s : instancia de sprite
     */
    @Override
    public void moveSprites(Sprite s) {
        if(! (s instanceof Hero) && ! (s instanceof Enemy)){
            s.refreshBuffer();
        }
        if (s instanceof Hero && hero.isMoving()) {
            s.moveSprite();
        }
        if (s instanceof Enemy) {
            ((Enemy) s).setMoveDirection(hero);
            ((Enemy) s).setMoveAnimation("SE");

        }
    }

    //COLECCION DE METODOS DE AJUSTES DE LOGICA DEL SISTEMA

    /**
     * Metodo implementado por la interfaz IScreen
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
    public void checkCollisions() {

    }

    @Override
    public void checkEndLevel() {

    }

    //EVENTOS DE RATON

    @Override
    public void moveMouse(MouseEvent e) {

    }

    @Override
    public void clickMouse(MouseEvent e) {
//        this.previousMouseY = e.getY();
//        this.previousMouseX = e.getX();
//        hero.setMoving(true);

    }


    //EVENTOS DE TECLADO

    /**
     * Metodo encargado de gestionar los eventos de teclado,
     * es decir, que sucede en la pantalla cuando se pulsa
     * alguna tecla en concreto
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        hero.setMoving(true);
    }

    @Override
    public void keyRelessed(KeyEvent e) {
        hero.setMoving(false);
    }


    /**
     * Metodo que gestiona el movimiento del personaje según la tecla del teclado pulsada.
     * Este metodo establece la velocidad en el vector de movimiento en el plano y establece además
     * que set de sprites se deben utilizar para animar dicho movimiento llamando al metodo setMoveAnimation
     * y pasandole por parámetro una dirección.
     *
     * @param e : evento de teclado
     * @return
     * @see Hero
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        //TODO implementar array de booleanos en la clase y dirigir el movimiento según el estado
        //TODO de dicha logica del array.
        synchronized (GamePane.class) {
            getKeyLogic(e);
            hero.setMoveDirection(whatKeyPressed);
            hero.setMoveParameters(whatKeyPressed);
            hero.setMoveAnimation(hero.getActualDirection());
            return false;
        }
    }

    private void getKeyLogic(KeyEvent e) {
        int keyCode;
        switch (e.getID()) {
            case KeyEvent.KEY_PRESSED:
                keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_W:
                        whatKeyPressed[0] = true;
                        break;
                    case KeyEvent.VK_A:
                        whatKeyPressed[1] = true;
                        break;
                    case KeyEvent.VK_S:
                        whatKeyPressed[2] = true;
                        break;
                    case KeyEvent.VK_D:
                        whatKeyPressed[3] = true;
                        break;
                }
                break;
            case KeyEvent.KEY_RELEASED:
                keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_W:
                        whatKeyPressed[0] = false;
                        break;
                    case KeyEvent.VK_A:
                        whatKeyPressed[1] = false;
                        break;
                    case KeyEvent.VK_S:
                        whatKeyPressed[2] = false;
                        break;
                    case KeyEvent.VK_D:
                        whatKeyPressed[3] = false;
                        break;
                }
        }
    }

}