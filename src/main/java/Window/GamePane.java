package Window;

import Screens.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * <h2>Clase GamePane</h2>
 * Clase que hereda del componente JPanel y que se encargara de gestionar la entrada de los niveles/pantallas
 * "Screens". Esta clase se encarga además de gestionar el render del juego (tasa de refresco) además de controlar
 * si se ha perdido en el juego y que puntuación se ha obtenido
 * @see JPanel
 * @see IScreen
 * @implements Runnable, KeyListener
 * @author David Bermejo Simon
 *
 */
public class GamePane extends JPanel implements Runnable, KeyListener {


    public Menu menu;
    private boolean endLevel;
    private int actualLevel;
    private IScreen screen;
    private boolean gameOver;
    private double score;

    /**
     * <h3>Constructor de la clase.</h3>
     * Se inicializan las variables, se hace focus sobre el componente y se inicializan
     * sus listeners. Ademas comienza a ejecutar el run.
     */
    public GamePane(){
        this.actualLevel = 0;
        this.score = 0;
        this.endLevel = true;
        this.gameOver = false;
        this.setFocusable(true);
        this.addKeyListener(this);
        new Thread(this).start();
    }

    //METODOS PARA PINTAR LA PANTALLA

    /**
     * Metodo encargado de llamar al metodo drawScreen de la pantalla que este cargada
     * según el flujo establecido por el metodo checkLevel
     * @see #checkLevel()
     * @see IScreen
     * @param g : componentes graphicos del panel de juego
     */
    @Override
    protected void paintComponent(Graphics g) {
        //TODO implementar el pintado del elemento Screen en la pantalla
        this.screen.drawScreen(g);
    }


    //METODOS DE GESTION DEL FLUJO

    /**
     * Metodo encargado de gestionar el nivel actual en el juego
     * y cargar la pantalla en función a dicho flujo
     */
    private void checkLevel(){
        if (endLevel && !gameOver) {
            actualLevel++;
            switch (actualLevel) {
                case 0:
                    this.screen = new StartScreen(this);
                    endLevel = false;
                    break;

                case 1:
                    this.screen = new FirstScreen(this);
                    endLevel = false;
                    break;

                    //TODO gestion del flujo para la carga de los distintos niveles

                case 5:
                    this.screen = new VictoryScreen(this);
                    endLevel = false;
                    break;
            }

        } else if (endLevel && gameOver) {
            this.screen = new GameOverScreen(this);
            actualLevel = -1;
            endLevel = false;

        }
    }

    //EVENTOS DE RATÓN
    //TODO rellenar los metodos de eventos y listeners
    //TODO realizar la documentacion de los metodos
    public void keyTyped(KeyEvent keyEvent) {

    }

    public void keyPressed(KeyEvent keyEvent) {

    }

    public void keyReleased(KeyEvent keyEvent) {

    }



    //METODO RUN DE EJECUCIÓN DEL HILO
    /**
     * Metodo encargado de gestionar la tasa de refresco (render)
     */
    @Override
    public void run() {
        while(true){
            try {
                checkLevel();
                this.repaint();
                Thread.currentThread().sleep(5);
                Toolkit.getDefaultToolkit().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //GETTERS Y SETTERS DE LA CLASE
    //TODO realizar la documentacion de los metodos

    public boolean isEndLevel() {
        return endLevel;
    }

    public void setEndLevel(boolean endLevel) {
        this.endLevel = endLevel;
    }

    public int getActualLevel() {
        return actualLevel;
    }

    public void setActualLevel(int actualLevel) {
        this.actualLevel = actualLevel;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
