package Sprites;

import javax.swing.*;

/**
 * <h2>Clase Enemy</h2>
 * Esta clase gestiona los parametros y valores de los elementos enemigos.
 * @see Sprite
 * @author David Bermejo Simon
 *
 */
public class Enemy extends Sprite {
    boolean isAlive;
    int totalHp;
    int atk;
    int def;
    JProgressBar life;


    /**
     * Constructor de la clase vacio
     */
    public Enemy(){

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
}
