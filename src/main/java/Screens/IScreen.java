package Screens;

import Sprites.Hero;
import Sprites.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface IScreen {
    public void startFrame();

    public void addElements();

    public void drawScreen(Graphics g);

    public void resizeScreen(Graphics g);

    public void drawBackGround(Graphics g);

    public void drawSprite(Graphics g);

    public void drawMenu();

    public void checkCollisions(Sprite s);

    public void checkEndLevel();

    public void moveSprites(Sprite s);

    public void manageGameFunctions();

    public void moveMouse(MouseEvent e);

    public void clickMouse(MouseEvent e);

    public void keyPressed(KeyEvent e);

    public void keyRelessed(KeyEvent e);

    public boolean dispatchKeyEvent(KeyEvent e);


    public Hero getHero();
}
