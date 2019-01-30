package Screens;

import Sprites.Enemy;
import Sprites.Hero;
import Sprites.Item;
import Sprites.Sprite;
import Utilities.ControlManager;
import Window.GamePane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * <h2>Clase FirstLevel</h2>
 * Esta clase compone la gestión de gráficos y de sistema de juego que se empleará para el primer nivel / pantalla
 *
 * @author David Bermejo Simon
 */

//TODO IMPLEMENTAR LA CLASE ENTERA
public class SecondLevel implements IScreen {

    private static final String BACKGROUND_GAME = "src/main/resources/floors/town_floor.png";
    private static final int NUM_ENEMIES = 1;

    private final static int ENEMY_HP = 10;
    private final static int ENEMY_ATK = 5;
    private final static int ENEMY_DEF = 5;


    private final static int MAX_NUM_ITEMS = 5;

    GamePane gamePane;
    Image backgroundImage;
    ArrayList<Sprite> sprites;

    boolean endLevel;
    boolean gameOver;
    boolean[] whatKeyPressed;

    Hero hero;
    Enemy[] enemies;
    Item[] items;
    private ControlManager controlManager;


    public SecondLevel(GamePane gamePane) {
        this.controlManager = new ControlManager(this);
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
        this.gamePane.heroMenu.putMapOnMenu(0);
        this.hero = gamePane.getHero();
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
        this.hero = gamePane.getHero();
        this.hero.setPosX(0);
        this.hero.setPosY(gamePane.getHeight()-(hero.getHeight()*2));
        sprites.add(hero);
    }

    /**
     * Metodo encargado de generar e instanciar los enemigos
     */
    private void addEnemies() {
        for (int i = 0; i < enemies.length; i++) {
            Enemy enemy = new Enemy();
            enemy.setPosX(300*(i+1));
            enemy.setPosY(400);
            //parametros de dimension
            enemy.setWidth(140);
            enemy.setHeight(140);
            enemy.setColliderTaxX(-25);
            enemy.setColliderTaxY(-100);
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
            item.setPosX(gamePane.getWidth()/2);
            item.setPosY(gamePane.getHeight()/2);
            //parametros de dimension
            item.setWidth(50);
            item.setHeight(50);
            //parametros de velocidad
            item.setvX(0);
            item.setvY(0);
            //parametros de recursos gráficos y gestion de los mismos
            item.setBufferByRoute("src/main/resources/potions/attack_potion.png");
            item.refreshBuffer();
            item.setName("health_potion");
            item.setDescription("Poción mágica, si se toma recuperas vida");
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
        //no hace nada
    }

    /**
     * Metodo implementado por la interfaz IScreen
     * Metodo encargado de mover los Sprites por la pantalla
     *
     * @param s : instancia de sprite
     */
    @Override
    public void moveSprites(Sprite s) {
        if (!(s instanceof Hero) && !(s instanceof Enemy)) {
            s.refreshBuffer();
        }
        if (s instanceof Hero && hero.isMoving()) {
            s.refreshBuffer();
            s.moveSprite();
        }
        if (s instanceof Enemy) {
            ((Enemy) s).moveCharacter(hero);
            s.moveSprite();

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
            if(s instanceof Enemy){
                if(((Enemy) s).isAlive()){
                    moveSprites(s);
                    checkCollisions(s);
                    calculateBattles();
                }else{
                    ((Enemy) s).setDeathAnimation();
                }
            }else{
                moveSprites(s);
                checkCollisions(s);
                calculateBattles();
                if (hero != null && !hero.isAlive()) {
                    hero.setDeathAnimation();
                    checkEndLevel();
                }
            }
        }
    }

    /**
     * Metodo encargado de calcular el sistema de combate entre el personaje y los enemigos
     */
    private void calculateBattles() {
        for (int i = 0; i < enemies.length; i++) {
            enemies[i].attack(hero);
            if (hero.getTotalHp() <= 0) {
                hero.setAlive(false);
                break;
            }
            if(hero.isAttacking() && hero.circleCollider(enemies[i])){
                hero.makeDamage(enemies[i]);
                if(enemies[i].getTotalHp()<= 0 ){
                    enemies[i].setAlive(false);
                    gamePane.setScore(gamePane.getScore()+150);
                }
            }

        }
    }


    /**
     * Metodo encargado de comprobar las colisiones con las paredes de la ventana y cambiar la velocidad
     * en caso de que exista dicha colision
     *
     * @param sprite
     */
    @Override
    public void checkCollisions(Sprite sprite) {
        checkWallCollisions(sprite);
        checkSpritesCollisions();
        checkItemsCollisions();

    }

    /**
     * Metodo encargado de realizar comprobaciones de como colisionan los sprites entre sid
     */
    private void checkSpritesCollisions() {
        for (Sprite s : sprites) {
            if (s instanceof Enemy) {
                Enemy enemyAux = (Enemy) s;
                if (enemyAux.circleCollider(hero)) {
//                    enemyAux.setvX(enemyAux.getvX()*-1);
//                    enemyAux.setvY(enemyAux.getvY()*-1);
                    if (new Random().nextInt(5000) < 50) {
                        System.out.println("El enemigo Intenta atacar!!!");
                        enemyAux.setMustAttack(true);
                    }

                }
            }
            if (s instanceof Hero) {
                for (int i = 0; i < enemies.length; i++) {
                    if (hero.circleCollider(enemies[i])) {
//                        hero.setvX(hero.getvX()*-1);
//                        hero.setvY(hero.getvY()*-1);
                    }
                }
            }
        }
    }


    /**
     * Metodo encargado de realizar comprobaciones de las colisiones entre los sprites y las paredes
     *
     * @param sprite : sprite a comprobar
     */
    private void checkWallCollisions(Sprite sprite) {
        if (sprite.getPosX() <= 0) {
            sprite.setCollide(true);
            if (sprite.getvX() == 0) {
                sprite.setvX(2);
            }
            sprite.setvX(sprite.getvX() * -1);
        } else if (sprite.getPosX() >= gamePane.getWidth() - sprite.getWidth()) {
            sprite.setCollide(true);
            if (sprite.getvX() == 0) {
                sprite.setvX(-2);
            }
            sprite.setvX(sprite.getvX() * -1);
        } else {
            sprite.setCollide(false);
        }

        if (sprite.getPosY() <= -150) {
            sprite.setCollide(true);
            if (sprite.getvY() == 0) {
                sprite.setvY(2);
            }
            sprite.setvY(sprite.getvY() * -1);
        } else if (sprite.getPosY() >= gamePane.getHeight() - sprite.getWidth()) {
            sprite.setCollide(true);
            if (sprite.getvY() == 0) {
                sprite.setvY(2);
            }
            sprite.setvY(sprite.getvY() * -1);
        } else {
            sprite.setCollide(false);
        }
    }

    /**
     * Metodo encargado de gestionar la colision contra los items
     */
    private void checkItemsCollisions() {
        for(Sprite s : sprites){
            if(s instanceof Item){
                Item itemAux = (Item)s;
                if(itemAux.circleCollider(hero)){
                    itemAux.makeEffectHero(hero);
                }

            }
        }
    }

    /**
     * Metodo encargado de realizar comprobaciones de si se ha terminado el juego o no
     */
    @Override
    public void checkEndLevel() {
        if (!hero.isAlive()) {
            this.gamePane.setEndLevel(false);
            this.gamePane.setGameOver(true);
        }else{
            int count = 0;
            for (int i = 0; i < enemies.length; i++) {
                if(!enemies[i].isAlive()){
                    count++;
                }
                if(count == enemies.length){
                    System.out.println("has vencido");
                    this.gamePane.setEndLevel(true);
                    this.gamePane.setGameOver(true);
                    break;
                }
            }
        }
    }

    //EVENTOS DE RATON

    @Override
    public void moveMouse(MouseEvent e) {

    }

    @Override
    public void clickMouse(MouseEvent e) {


    }


    //EVENTOS DE TECLADO

    @Override
    public void keyRelessed(KeyEvent e) {

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
        //no hace nada
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
        synchronized (GamePane.class) {

            controlManager.getKeyLogic(e);
            controlManager.getAttackControl(e,hero);
            if (!hero.isAttacking()) {
                hero.moveCharacter(controlManager.getWhatKeyPressed());
            }
            return false;
        }
    }

    @Override
    public Hero getHero() {
        return null;
    }

}