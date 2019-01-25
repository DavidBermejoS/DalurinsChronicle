package Sprites;

import Utilities.ResourcesCollector;

import javax.swing.*;
import java.awt.image.BufferedImage;

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
    private String actualDirection;
    private boolean attacking;
    private double vTotal;


    /**
     * Constructor de la clase vacio
     */
    public Hero() {
        this.walkingImagesLine = new BufferedImage[8][10];
        loadWalkingImages();
        lastDirection = "";
        attacking = false;
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
     * Metodo encargado de englobar los otros metodos que establecen el movimiento del personaje
     */
    public void moveCharacter(boolean[] keys) {
        setMoveDirection(keys);
        setMoveParameters(keys);
        setMoveAnimation();


    }


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
        this.vX=0;
        this.vY = 0;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i]) {
                switch (i) {
                    case 0:
                        vY += -2;
                        break;
                    case 1:
                        vX += -2;
                        break;
                    case 2:
                        vY += 2;
                        break;
                    case 3:
                        vX += 2;
                        break;
                }
            }
        }

        if (vX == 0 && vY == 0){
            this.moving = false;
        }else{
            this.moving = true;
        }

        vTotal = Math.sqrt(Math.pow(vX,2)+Math.pow(vY,2));
        if(vTotal > 2){
            vX = Math.abs(vX)/vX * 1.44;
            vY = Math.abs(vY)/vY *1.44;
        }
    }

    /**
     * Metodo encargado de definir el array de imagenes que se deverán utilizar para la animación
     * actual
     */
    public void setMoveAnimation() {
        ResourcesCollector resCol = new ResourcesCollector();
        if (!lastDirection.equalsIgnoreCase(actualDirection) && !actualDirection.equalsIgnoreCase("")) {
            this.actualAnimationLine = resCol.getImagesTargetActionDirection(ResourcesCollector.HERO_TARGET, ResourcesCollector.WALK_ACTION, actualDirection);
            lastDirection = actualDirection;
        }
        countAnimatorPhase++;
        this.imageSprite = actualAnimationLine[countAnimatorPhase / 3 % actualAnimationLine.length];
        this.refreshBuffer();
    }


    private void loadWalkingImages() {
        ResourcesCollector resCol = new ResourcesCollector();
        for (int i = 0; i < walkingImagesLine.length; i++) {
            for (int j = 0; j < allDirections.length; j++) {
                walkingImagesLine[i] = resCol.getImagesTargetActionDirection(resCol.HERO_TARGET, resCol.WALK_ACTION, allDirections[j]);
            }
        }
    }


    //GESTION DE COLISIONES DEL HEROE

    public void checkHeroCollisions() {

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

    public int getNumItems(){
        if(items==null){
            return 0;
        }
        return items.length;
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

    public boolean isAttacking() {
        return this.attacking;
    }
}
