package Sprites;

import Utilities.ResourcesCollector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * <h2>Clase Hero</h2>
 * Clase que define los parametros del personaje.
 * Hereda de la clase Sprite, y es encargada de guardar las estadisticas del personaje
 *
 * @author David Bermejo Simon
 */
public class Hero extends Sprite {

    public final static int MAX_HP = 30;


    boolean isAlive;
    String user;
    int totalHp;
    int atk;
    int def;
    int acc;

    Item[] items;
    Skill[] skills;
    String[][] matrixAnimation;
    String[] routesAnimation;

    private String lastDirection;
    private String actualDirection;
    private int paramDirection;

    private boolean moving;
    private boolean attacking;
    private boolean collides;

    BufferedImage[] actualAnimationLine;
    BufferedImage[][] walkingImagesLine;
    BufferedImage[][] attackingImagesLine;
    boolean perpetuallyDeath;
    private boolean isAttackAnimatorRunning;
    private Thread threadAttackAnimation;


    /**
     * Constructor de la clase vacio
     */
    public Hero() {
        this.walkingImagesLine = new BufferedImage[8][10];
        loadWalkingImages();
        this.attackingImagesLine = new BufferedImage[8][18];
        loadAttackingImages();
        lastDirection = "";
        attacking = false;
        isAttackAnimatorRunning = false;
        lastDirection = "S";
        matrixAnimation = new String[10][8];
        routesAnimation = new String[10];
        this.threadAttackAnimation = new Thread();
    }

    /**
     * Constructor con los parametros propios de la clase y los de la clase Sprite
     *
     * @param posX
     * @param posY
     * @param vX
     * @param vY
     * @param id
     * @param imageRoutes
     * @param isAlive
     * @param user
     * @see Sprite
     */
    public Hero(int posX, int posY, int vX, int vY, String id, String[] imageRoutes, boolean isAlive, String user) {

        super(posX, posY, vX, vY, id);
        matrixAnimation = new String[10][8];
        routesAnimation = new String[10];
        this.isAlive = isAlive;
        this.user = user;
    }

    /**
     * Constructor con los parametros propios de la clase
     *
     * @param isAlive
     * @param user
     */
    public Hero(boolean isAlive, String user) {
        matrixAnimation = new String[10][8];
        routesAnimation = new String[10];
        this.isAlive = isAlive;
        this.user = user;
    }


    //COLECCION DE METODOS QUE GESTIONAN LAS ACCIONES DEL HEROE

    /**
     * Metodo encargado de englobar los otros metodos que establecen el movimiento del personaje
     */
    public void moveCharacter(boolean[] keys) {
        if (isAlive()) {
            this.setAttacking(false);
            setMoveDirection(keys);
            setParamDirection();
            if (!collide) {
                setMoveParameters(keys);
            }
            setMoveAnimation();
        }
    }

    /**
     * Metodo que gestiona el ataque del heroe
     */
    public void attackCharacter() {
        if (isAlive()) {
            this.setMoving(false);
            setParamDirection();
            setAttackAnimation();
        }
    }

    /**
     * Metodo que comprueba si el personaje esta vivo o muerto según su vida
     */
    public boolean checkAlive() {
        if (this.getTotalHp() <= 0) {
            this.isAlive = false;
        } else {
            this.isAlive = false;
        }

        return this.isAlive;
    }

    /**
     * Metodo que gestiona la muerte del enemigo
     */
    public void setDeathAnimation() {
        if (!isAlive && !perpetuallyDeath) {
            try {
                this.buffer = ImageIO.read(new File("src/main/resources/hero/death/death_40006.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //COLECCION DE METODOS QUE GESTIONAN LAS ANIMACIONES DEL HEROE


    /**
     * Metodo encargado de definir que dirección toma el heroe segun la combinacion de teclas que tenga pulsada
     *
     * @param keys : array de booleanos con todos las teclas que han sido pulsadas y las que no
     */
    public void setMoveDirection(boolean[] keys) {
        String directionAux = "";
        int count = 0;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] && count < 3) {
                count++;
                switch (i) {
                    case KEY_N:
                        directionAux = directionAux.concat("N");
                        break;
                    case KEY_W:
                        directionAux = directionAux.concat("W");
                        break;
                    case KEY_S:
                        directionAux = directionAux.concat("S");
                        break;
                    case KEY_E:
                        directionAux = directionAux.concat("E");
                        break;
                }
            }
            this.actualDirection = directionAux;
        }
    }

    /**
     * Metodo encargado de establecer una direccion como parametro para hacer referencia al array de imagenes
     * que necesita para cargar la animacion
     */
    private void setParamDirection() {
        if (actualDirection.equalsIgnoreCase("N")) {
            this.paramDirection = 0;

        }
        if (actualDirection.equalsIgnoreCase("S")) {
            this.paramDirection = 1;

        }
        if (actualDirection.equalsIgnoreCase("E")) {
            this.paramDirection = 5;

        }
        if (actualDirection.equalsIgnoreCase("W")) {
            this.paramDirection = 4;

        }
        if (actualDirection.equalsIgnoreCase("NW") || actualDirection.equalsIgnoreCase("WN")) {
            this.paramDirection = 6;
        }
        if (actualDirection.equalsIgnoreCase("NE") || actualDirection.equalsIgnoreCase("EN")) {
            this.paramDirection = 7;
        }
        if (actualDirection.equalsIgnoreCase("SW") || actualDirection.equalsIgnoreCase("WS")) {
            this.paramDirection = 2;
        }
        if (actualDirection.equalsIgnoreCase("SE") || actualDirection.equalsIgnoreCase("ES")) {
            this.paramDirection = 3;
        }
        if (actualDirection.equalsIgnoreCase("")) {
            this.actualDirection = lastDirection;
            setParamDirection();
        }
    }

    /**
     * Metodo encargado de ajustar la velocidad y los parametros de movimiento segun la
     * direccion actual
     *
     * @param keys : array de booleanos con todos las teclas que han sido pulsadas y las que no
     */
    public void setMoveParameters(boolean[] keys) {
        this.vX = 0;
        this.vY = 0;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i]) {
                switch (i) {
                    case 0:
                        vY += -3;
                        break;
                    case 1:
                        vX += -3;
                        break;
                    case 2:
                        vY += 3;
                        break;
                    case 3:
                        vX += 3;
                        break;
                }
            }
        }
        if (vX == 0 && vY == 0) {
            this.moving = false;
        } else {
            this.moving = true;
        }
        vTotal = Math.sqrt(Math.pow(vX, 2) + Math.pow(vY, 2));
        if (vTotal > 3) {
            vX = Math.abs(vX) / vX * 1.44;
            vY = Math.abs(vY) / vY * 1.44;
        }
    }

    /**
     * Metodo encargado de definir el array de imagenes que se deverán utilizar para la animación
     * actual de movimiento
     */
    public void setMoveAnimation() {
        if (isMoving()) {
            if (!actualDirection.equalsIgnoreCase("")) {
                this.actualAnimationLine = this.walkingImagesLine[paramDirection];
                lastDirection = actualDirection;
            }
            countAnimatorPhase++;
            this.imageSprite = actualAnimationLine[countAnimatorPhase / 3 % actualAnimationLine.length];

            this.width = 150;
            this.height = 150;
            this.refreshBuffer();
        }

    }


    /**
     * Metodo encargado de definir el array de imagenes que se deverán utilizar para la animación
     * actual de ataque
     */
    public void setAttackAnimation() {
        if (!isAttackAnimatorRunning) {
            threadAttackAnimation = new Thread(new Runnable() {
                @Override
                public void run() {
                    isAttackAnimatorRunning = true;
                    try {
                        for (int i = 0; i < attackingImagesLine.length; i++) {
                            if (attacking) {
                                sleep(120);
                                if (!actualDirection.equalsIgnoreCase("")) {
                                    actualAnimationLine = attackingImagesLine[paramDirection];
                                }
                                imageSprite = actualAnimationLine[i];
                                imageSprite = imageSprite.getSubimage(41, 26, 137, 157);
                                width = 160;
                                height = 160;
                                refreshBuffer();
                                if (i == attackingImagesLine.length - 1) {
                                    setAttacking(false);
                                    isAttackAnimatorRunning = false;
                                }
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threadAttackAnimation.start();
            if (!isAttackAnimatorRunning) {
                threadAttackAnimation.yield();
            }
        }
    }


    /**
     * Metodo que gestiona el daño que realiza el heroe al atacar a un enemigo
     *
     * @param enemy
     */
    public void makeDamage(Enemy enemy) {
        if(isAttackAnimatorRunning){
            Random r = new Random();
            int probabilityAttack = r.nextInt(1000);
            if (this.getAcc() > probabilityAttack) {
                int damage = enemy.getTotalHp() - (this.getAtk() - enemy.getDef());
                enemy.setTotalHp(damage);
                enemy.setRefreshDamageTime(System.nanoTime());
                enemy.setDamage(damage);
                System.out.println("El enemigo tiene un total de :" + enemy.getTotalHp());
            }
            if (enemy.getTotalHp() <= 0) {
                enemy.setAlive(false);
            }
        }
    }


    //METODOS ENCARGADO DE GUARDAR LAS IMAGENES QUE USARÁ EL HEROE

    /**
     * Este metodo se encarga de cargar las imagenes del heroe andando en el array de la clase walkingImagesLine
     */
    private void loadWalkingImages() {
        ResourcesCollector resCol = new ResourcesCollector();
        for (int i = 0; i < allDirections.length; i++) {
            walkingImagesLine[i] = resCol.getImagesTargetActionDirection(resCol.HERO_TARGET, resCol.WALK_ACTION, allDirections[i]);
        }
    }

    /**
     * Este metodo se encarga de cargar las imagenes del heroe atacando en el array de la clase attackingImagesLine
     */
    private void loadAttackingImages() {
        ResourcesCollector resCol = new ResourcesCollector();
        for (int j = 0; j < allDirections.length; j++) {
            attackingImagesLine[j] = resCol.getImagesTargetActionDirection(resCol.HERO_TARGET, resCol.ATTACK_ACTION, allDirections[j]);
        }
    }


    //GETTERS Y SETTERS

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getTotalHp() {
        return totalHp;
    }

    public void setTotalHp(int totalHp) {
        this.totalHp = totalHp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getAcc() {
        return acc;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isAttacking() {
        return this.attacking;
    }

    private boolean getAttacking() {
        return this.attacking;
    }


    public boolean isAttackAnimatorRunning() {
        return this.isAttackAnimatorRunning;
    }
}
