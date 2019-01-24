package Sprites;

import Utilities.ResourcesCollector;

import javax.swing.*;
import java.awt.*;
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

    private String[] allDirections = {"N", "W", "S", "E", "NW", "NE", "SW", "SE"};
    final static int VAL_N = 0;
    final static int VAL_W = 1;
    final static int VAL_S = 2;
    final static int VAL_E = 3;
    final static int VAL_NW = 4;
    final static int VAL_NE = 5;
    final static int VAL_SW = 6;
    final static int VAL_SE = 7;


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
    private String actualDirection;


//CONSTRUCTORES DE LA CLASE

    /**
     * Constructor de la clase vacio
     */
    public Hero() {
        lastDirection = "";
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


    //COLECCION DE METODOS QUE GESTIONAN EL MOVIMIENTO DEL HEROE


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
                    case VAL_N:
                        directionAux = directionAux.concat("N");
                        break;
                    case VAL_W:
                        directionAux = directionAux.concat("W");
                        break;
                    case VAL_S:
                        directionAux = directionAux.concat("S");
                        break;
                    case VAL_E:
                        directionAux = directionAux.concat("E");
                        break;
                }
            }
        }
        this.actualDirection = directionAux;
    }

    /**
     * Metodo encargado de ajustar la velocidad y los parametros de movimiento segun la
     * direccion actual
     *
     * @param keys : array de booleanos con todos las teclas que han sido pulsadas y las que no
     */
    public void setMoveParameters(boolean[] keys) {
        int countFalses = 0;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i]) {
                switch (i) {
                    case 0:
                        vY = -2;
                        break;
                    case 1:
                        vX = -2;
                        break;
                    case 2:
                        vY = 2;
                        break;
                    case 3:
                        vX = 2;
                        break;
                }
            } else {
                countFalses++;
            }
        }
        if (countFalses == keys.length) {
            this.setMoving(false);
            this.vX = 0;
            this.vY = 0;
        }else{
            this.setMoving(true);
        }

    }

    /**
     * Metodo encargado de definir el array de imagenes que se deverán utilizar para la animación
     * actual
     *
     * @param direction : direccion de las cuales se quieren gestionar las imagenes
     */
    public void setMoveAnimation(String direction) {
        ResourcesCollector resCol = new ResourcesCollector();
        if (!lastDirection.equalsIgnoreCase(direction) && !direction.equalsIgnoreCase("")) {
            super.imageRoutes = resCol.getRoutesByDirection(resCol.HERO_TARGET, resCol.WALK_ACTION, direction);
            lastDirection = direction;
        }
        if (imageRoutes != null) {
            countAnimatorPhase++;
            //TODO mejorar el tiempo de carga de imagenes para evitar pestaneo en escena
            if (countAnimatorPhase == imageRoutes.length-1) {
                countAnimatorPhase = 0;
            }
            fileImage = new File(imageRoutes[countAnimatorPhase]);
            this.refreshBuffer();
//            this.getScaledInstance(width, height, Image.SCALE_SMOOTH)
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

    public String getActualDirection() {
        return actualDirection;
    }

    public void setActualDirection(String actualDirection) {
        this.actualDirection = actualDirection;
    }

}
