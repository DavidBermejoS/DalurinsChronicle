package Sprites;

import Utilities.ResourcesCollector;

import javax.swing.*;
import java.io.File;

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


    /**
     * Constructor de la clase vacio
     */
    public Enemy(){
        this.isAlive = true;
        this.moving = false;
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
        super(posX, posY, vX, vY, id, imageRoutes);
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
        }
        if (angle > 120 && angle < 150) {
            this.actualDirection = "NE";
        }
        if(angle>-150 && angle < -120){
            this.actualDirection = "SE";
        }
        if (angle < -30 && angle > -60) {
            this.actualDirection = "SW";
        }
        if((angle > -30 && angle <0) || (angle < 30 && angle>0)){
            this.actualDirection = "W";
        }
        if ((angle < - 150 && angle > -180) || (angle > 150 && angle < 180)){
            this.actualDirection = "E";
        }
        if (angle > 60 && angle < 120){
            this.actualDirection = "N";
        }
        if(angle > -120 && angle < -60 ){
            this.actualDirection = "S";
        }
//        System.out.println(angle);
        System.out.println(actualDirection);



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
            super.imageRoutes = resCol.getRoutesByDirection(resCol.ENEMY_TARGET, resCol.WALK_ACTION, direction);
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
}
