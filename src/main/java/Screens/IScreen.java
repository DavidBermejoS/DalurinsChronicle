package Screens;

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

    public void checkCollisions();

    public void checkEndLevel();

    public void moveSprites();

    public void manageGameFunctions();

    public void moveMouse(MouseEvent e);

    public void clickMouse(MouseEvent e);

    public void keyPressed(KeyEvent e);


}
