package Utilities;

import Screens.FirstLevel;
import Screens.IScreen;
import Sprites.Hero;
import Window.GamePane;

import java.awt.event.KeyEvent;

/**
 * Clase encargada de gestionar los controles de usuario
 */
public class ControlManager {
    IScreen screen;
    boolean[] whatKeyPressed;
    Hero hero;
    private GamePane gamePane;


    public ControlManager(GamePane gamePane, IScreen screen) {
        this.gamePane = gamePane;
        this.whatKeyPressed = new boolean[4];
        for (int i = 0; i < whatKeyPressed.length; i++) {
            whatKeyPressed[i] = false;
        }
        this.screen = screen;
        this.hero = this.screen.getHero();
    }

    /**
     * Metodo que gestiona el movimiento del personaje según la tecla del teclado pulsada.
     * Este metodo establece la velocidad en el vector de movimiento en el plano y establece además
     * que set de sprites se deben utilizar para animar dicho movimiento llamando al metodo setMoveAnimation
     * y pasandole por parámetro una dirección.
     *
     * @param e : evento del mouse.
     */
    public void getKeyLogic(KeyEvent e) {
        this.hero = gamePane.getHero();
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
                        this.hero.setMoving(false);
                        break;
                    case KeyEvent.VK_A:
                        whatKeyPressed[1] = false;
                        this.hero.setMoving(false);
                        break;
                    case KeyEvent.VK_S:
                        whatKeyPressed[2] = false;
                        this.hero.setMoving(false);
                        break;
                    case KeyEvent.VK_D:
                        whatKeyPressed[3] = false;
                        this.hero.setMoving(false);
                        break;
                    case KeyEvent.VK_J:
                        this.hero.setAttacking(false);
                        break;
                }
        }
    }
    
    /**
     * Metodo encargado de gestionar el ataque del heroe al pulsar sobre la tecla J.
     *
     * @param e    : evento del mouse.
     * @param hero : objeto heroe.
     */
    public void getAttackControl(KeyEvent e, Hero hero) {
        if (e.getKeyCode() == KeyEvent.VK_J) {
            if (!hero.isMoving()) {
                hero.setAttacking(true);
                hero.attackCharacter();
            }
        }
    }

    public boolean[] getWhatKeyPressed() {
        return whatKeyPressed;
    }

    public void setWhatKeyPressed(boolean[] whatKeyPressed) {
        this.whatKeyPressed = whatKeyPressed;
    }
}
