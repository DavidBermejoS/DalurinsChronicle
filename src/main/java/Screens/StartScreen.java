package Screens;

import Sprites.Sprite;
import Window.GamePane;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class StartScreen implements IScreen {
    GamePane gamePane;


    public StartScreen( GamePane gamePane){
        this.gamePane = gamePane;
    }

    @Override
    public void startFrame() {

    }

    @Override
    public void addElements() {

    }

    @Override
    public void drawScreen(Graphics g) {

    }

    @Override
    public void resizeScreen(Graphics g) {

    }

    @Override
    public void drawBackGround(Graphics g) {

    }

    @Override
    public void drawSprite(Graphics g) {

    }


    @Override
    public void drawMenu() {

    }

    @Override
    public void checkCollisions() {

    }

    @Override
    public void checkEndLevel() {

    }

    @Override
    public void moveSprites(Sprite s) {

    }


    @Override
    public void manageGameFunctions() {

    }

    @Override
    public void moveMouse(MouseEvent e) {

    }

    @Override
    public void clickMouse(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyRelessed(KeyEvent e) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        return false;
    }
}
