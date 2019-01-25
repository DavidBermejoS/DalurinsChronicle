package Window;

import Sprites.Hero;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class HeroMenu {

    public HeroMenu(){

    }


    public void statsBar(Graphics g, Hero hero){
        Random r = new Random();
        g.setFont(new Font("MonoSpace", Font.BOLD, 24));
        g.setColor(Color.RED);
        g.drawString("Potions: ", 0, 0);
        g.drawString("x "+ hero.getNumItems(),30,0);
        BufferedImage potionRed = new BufferedImage(20,20,BufferedImage.TYPE_INT_ARGB);
        Graphics graphPotion = potionRed.getGraphics();
        try {
            BufferedImage imageAux = ImageIO.read(new File("src/main/resources/potions/attack_potion.png"));
            graphPotion.drawImage(imageAux,20,20,null);
            graphPotion.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
