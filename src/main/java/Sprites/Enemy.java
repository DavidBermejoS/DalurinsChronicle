package Sprites;

import Utilities.ResourcesCollector;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * <h2>Clase Enemy</h2>
 * Esta clase gestiona los parametros y valores de los elementos enemigos.
 * @see Sprite
 * @author David Bermejo Simon
 *
 */
public class Enemy extends Sprite {
    boolean isAlive;
    boolean moving;
    int totalHp;
    int atk;
    int def;
    String lastDirection;
    String actualDirection;
    JProgressBar life;
    private boolean mustAttack;
    private double vTotal;
    private boolean followHero;
    BufferedImage[] actualAnimationLine;
    BufferedImage[][] walkingImagesLine;
    BufferedImage[][] attackingImagesLine;
    private int paramDirection;


    /**
     * Constructor de la clase vacio
     */
    public Enemy() {
        this.walkingImagesLine = new BufferedImage[8][10];
        loadWalkingImages();
        this.attackingImagesLine = new BufferedImage[8][18];
//        loadAttackingImages();
        this.isAlive = true;
        this.moving = false;
        this.mustAttack = false;
        this.followHero = false;
        this.lastDirection = "";
        this.actualDirection = "";

    }

    /**
     * Constructor de la clase con todos los parametros tanto de la clase como de la clase Sprite de la que hereda
     * @param posX
     * @param posY
     * @param vX
     * @param vY
     * @param id
     * @param imageRoutes
     * @param isAlive
     * @param totalHp
     * @param atk
     * @param def
     * @param life
     */
    public Enemy(int posX, int posY, int vX, int vY, String id, String[] imageRoutes, boolean isAlive, int totalHp, int atk, int def, JProgressBar life) {
        super(posX, posY, vX, vY, id);
        this.isAlive = isAlive;
        this.totalHp = totalHp;
        this.atk = atk;
        this.def = def;
        this.life = life;
    }

    public Enemy(boolean isAlive, int totalHp, int atk, int def, JProgressBar life) {
        this.isAlive = isAlive;
        this.totalHp = totalHp;
        this.atk = atk;
        this.def = def;
        this.life = life;
    }


    //COLECCION DE METODOS QUE GESTIONAN EL MOVIMIENTO DEL ENEMIGO

    /**
     * Metodo encargado de englobar los otros metodos que establecen el movimiento del personaje
     */
    public void moveCharacter(Hero hero) {
        setMoveDirection(hero);
        setParamDirection();
        setMoveAnimation();
        setMoveParameters(hero);


    }

    /**
     * Metodo encargado de definir que dirección toma el enemigo segun la posicion actual del hero
     *
     * @param hero : instancia del heroe para extraer su posicion
     */
    public void setMoveDirection(Hero hero) {
        int diffX = this.posX - hero.getPosX();
        int diffY = this.posY - hero.getPosY();
        float angle = (float) Math.toDegrees(Math.atan2(diffY, diffX));
        if (angle > 30 && angle < 60) {
            this.actualDirection = "NW";
            vY = -2;
            vX = -2;
        }
        if (angle > 120 && angle < 150) {
            this.actualDirection = "NE";
            vY = -2;
            vX = 2;
        }
        if(angle>-150 && angle < -120){
            this.actualDirection = "SE";
            vY = 2;
            vX = 2;
        }
        if (angle < -30 && angle > -60) {
            this.actualDirection = "SW";
            vY = 2;
            vX = -2;
        }
        if((angle > -30 && angle <0) || (angle < 30 && angle>0)){
            this.actualDirection = "W";
            vX = -2;
        }
        if ((angle < - 150 && angle > -180) || (angle > 150 && angle < 180)){
            this.actualDirection = "E";
            vX= 2;
        }
        if (angle > 60 && angle < 120){
            this.actualDirection = "N";
            vY=-2;
        }
        if(angle > -120 && angle < -60 ){
            this.actualDirection = "S";
            vY = 2;
        }

        vTotal = Math.sqrt(Math.pow(vX,2)+Math.pow(vY,2));
        if(vTotal > 2){
            vX = Math.abs(vX)/vX * Math.cos(angle);
            vY = Math.abs(vY)/vY * Math.cos(angle);
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
     */
    public void setMoveParameters(Hero hero) {
        if(followHero && !collide){
            int diffX = this.posX - hero.getPosX();
            int diffY = this.posY - hero.getPosY();
            float angle = (float)Math.atan2(diffY, diffX);
            vTotal = Math.sqrt(Math.pow(vX,2)+Math.pow(vY,2));
            if(vTotal > 2){
                vX = Math.abs(vX)/vX * Math.cos(angle);
                vY = Math.abs(vY)/vY * Math.cos(angle);
            }
        }else if (!followHero && !collide){
            int diffX = this.posX - hero.getPosX();
            int diffY = this.posY - hero.getPosY();
            float angle = (float)Math.atan2(diffY, diffX);
            vX = hero.getvX()*-1;
            vY = hero.getvY()*-1;
            vX*= Math.cos(angle);
            vY*= Math.sin(angle);
        }


    }

    /**
     * Metodo encargado de definir el array de imagenes que se deverán utilizar para la animación
     * actual
     *
     */
    public void setMoveAnimation() {
        if (!actualDirection.equalsIgnoreCase("")) {
            this.actualAnimationLine = this.walkingImagesLine[paramDirection];
            lastDirection = actualDirection;
        }
        countAnimatorPhase++;
        this.imageSprite = actualAnimationLine[countAnimatorPhase / 4 % actualAnimationLine.length];
        this.refreshBuffer();

    }


    //METODOS ENCARGADO DE GUARDAR LAS IMAGENES QUE USARÁ EL enemigo
    /**
     * Este metodo se encarga de cargar las imagenes del enemigo andando en el array de la clase walkingImagesLine
     */
    private void loadWalkingImages() {
        ResourcesCollector resCol = new ResourcesCollector();
        for (int i = 0; i < allDirections.length; i++) {
            walkingImagesLine[i] = resCol.getImagesTargetActionDirection(resCol.ENEMY_TARGET, resCol.WALK_ACTION, allDirections[i]);
        }
    }
//    /**
//     * Este metodo se encarga de cargar las imagenes del heroe atacando en el array de la clase attackingImagesLine
//     */
//    private void loadAttackingImages(){
//        ResourcesCollector resCol = new ResourcesCollector();
//        for (int i = 0; i < attackingImagesLine.length; i++) {
//            for (int j = 0; j < allDirections.length; j++) {
//                attackingImagesLine[i] = resCol.getImagesTargetActionDirection(resCol.ENEMY_TARGET, resCol.ATTACK_ACTION, allDirections[j]);
//            }
//        }

//    }

    public void attack(Hero hero) {
        this.followHero = true;
        if(mustAttack){
            System.out.println("El enemigo ataca!");
            hero.setTotalHp(hero.getTotalHp()-(this.atk - hero.getDef()));
            System.out.println("Al heroe le quedan: " + hero.getTotalHp());

            mustAttack = false;
        }

    }

    //GETTERS Y SETTERS DE LA CLASE

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
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

    public JProgressBar getLife() {
        return life;
    }

    public void setLife(JProgressBar life) {
        this.life = life;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public String getLastDirection() {
        return lastDirection;
    }

    public void setLastDirection(String lastDirection) {
        this.lastDirection = lastDirection;
    }

    public String getActualDirection() {
        return actualDirection;
    }

    public void setActualDirection(String actualDirection) {
        this.actualDirection = actualDirection;
    }

    public void setMustAttack(boolean b) {
        this.mustAttack = b;
    }

    public boolean isMustAttack() {
        return mustAttack;
    }
}
