package Sprites;

import Utilities.ResourcesCollector;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * <h2>Clase Hero</h2>
 * Clase que define los parametros del personaje.
 * Hereda de la clase Sprite, y es encargada de guardar las estadisticas del personaje
 *
 * @author David Bermejo Simon
 */
public class Hero extends Sprite {


    boolean isAlive;
    String user;
    int totalHp;
    int atk;
    int def;
    JProgressBar life;
    Item[] items;
    Skill[] skills;
    String[][] matrixAnimation;
    String[] routesAnimation;
    private boolean moving;
    private boolean collisioned;
    private String lastDirection;


//CONSTRUCTORES DE LA CLASE

    /**
     * Constructor de la clase vacio
     */
    public Hero() {
        lastDirection = "E";
        collisioned = false;
        matrixAnimation = new String[10][8];
        routesAnimation = new String[10];
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

        super(posX, posY, vX, vY, id, imageRoutes);
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

    /**
     * Metodo encargado de devolver el item
     * cuyo nombre se recibe por parametro
     *
     * @param name : nombre del item
     * @return
     */
    public Item getSelectedItem(String name) {
        Item itemReturn = null;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getName().equalsIgnoreCase(name)) {
                itemReturn = items[i];
            }
        }
        return itemReturn;
    }


    /**
     * Metodo encargado de devolver el item
     * cuyo nombre se recibe por parametro
     *
     * @param name : nombre de la habilidad
     * @return
     */
    public Skill getSelectedSkill(String name) {
        Skill skillReturn = null;
        for (int i = 0; i < skills.length; i++) {
            if (skills[i].getName().equalsIgnoreCase(name)) {
                skillReturn = skills[i];
            }
        }
        return skillReturn;
    }

    /**
     * Metodo encargado de definir el array de imagenes que se deverán utilizar para la animación
     * actual
     *
     * @param direction
     */
    public void setMoveAnimation(String direction) {
        ResourcesCollector resCol = new ResourcesCollector();
        if (!lastDirection.equalsIgnoreCase(direction)) {
            super.imageRoutes = resCol.getRoutesByDirection(resCol.HERO_TARGET, resCol.WALK_ACTION, direction);
            lastDirection = direction;
        }
        if (imageRoutes != null) {
            countAnimatorPhase++;
            if (countAnimatorPhase == imageRoutes.length) {
                countAnimatorPhase = 0;
            }
            fileImage = new File(imageRoutes[countAnimatorPhase]);
        }
    }


    //GETTERS Y SETTERS

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getUser() {
        return user;
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

    public BufferedImage getBufferedImage() {
        return buffer;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.buffer = bufferedImage;
    }

    public JProgressBar getLife() {
        return life;
    }

    public void setLife(JProgressBar life) {
        this.life = life;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
