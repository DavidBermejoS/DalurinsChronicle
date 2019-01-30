package Window;

import Sprites.Hero;
import Utilities.ResourcesCollector;
import Utilities.RpgDialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <h2>Clase HeroMenu</h2>
 * Esta clase gestiona la barra superior de estadisticas del personaje.
 */
public class HeroMenu extends JPanel {

    GamePane gamePane;
    Font fontAttributes;
    BufferedImage[] imagesFaces;
    BufferedImage background;
    JLabel canvasHeroImage;
    JProgressBar heroLife;
    JLabel labelAttack;
    JLabel labelDefense;
    JLabel labelMaxHP;
    JLabel map;
    BufferedImage potionImage;
    Hero hero;

    /**
     * Constructor de la clase
     * @param gamePane
     */
    public HeroMenu(GamePane gamePane) {
        this.gamePane = gamePane;
        this.hero = gamePane.getHero();
        fontAttributes = new Font("Seagram",Font.BOLD,25);
        try {
            this.background = ImageIO.read(new File("src/main/resources/backgrounds/heroMenu.png"));
            this.potionImage = ImageIO.read(new File("src/main/resources/potions/attack_potion.png"));
            this.imagesFaces = new BufferedImage[8];
            addComponents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            this.background = ImageIO.read(new File("src/main/resources/backgrounds/heroMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(background.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH),0,0,null);

    }

    /**
     * Metodo para anadir los componentes del menu superior
     */
    public void addComponents() {


        this.setLayout(new GridBagLayout());
        GridBagConstraints settings = new GridBagConstraints();
        ResourcesCollector rc = new ResourcesCollector();



        this.map = new JLabel();
        settings = new GridBagConstraints();
        settings.gridx = 0;
        settings.gridy = 0;
        settings.ipadx = 5;
        settings.ipady = 5;
        this.add(map,settings);


        this.imagesFaces = rc.getHeroFaces();
        this.canvasHeroImage = new JLabel();
        this.canvasHeroImage.setIcon(new ImageIcon("src/main/resources/hero/faces/normal.png", "normalFace"));
        settings = new GridBagConstraints();
        settings.gridx = 1;
        settings.gridy = 0;
        settings.ipadx = 5;
        settings.ipady = 5;
        this.add(canvasHeroImage, settings);

        this.heroLife = new JProgressBar(0, Hero.MAX_HP);
        this.heroLife.setString("HERO HP");
        this.heroLife.setBackground(Color.RED);
        this.heroLife.setForeground(Color.GREEN);
        settings = new GridBagConstraints();
        settings.gridx = 2;
        settings.gridy = 0;
        settings.ipadx = 40;
        settings.ipady = 10;
        this.add(heroLife, settings);

        this.labelAttack = new JLabel("ATK : "+this.hero.getAtk());
        this.labelAttack.setFont(fontAttributes);
        this.labelAttack.setForeground(Color.RED);
        settings = new GridBagConstraints();
        settings.gridx = 3;
        settings.gridy=0;
        settings.ipadx = 40;
        settings.ipady = 10;
        settings.insets = new Insets(0,20,0,0);
        this.add(labelAttack, settings);

        this.labelDefense= new JLabel("DEF : "+this.hero.getDef());
        this.labelDefense.setForeground(Color.RED);
        this.labelDefense.setFont(fontAttributes);
        settings = new GridBagConstraints();
        settings.gridx = 4;
        settings.gridy=0;
        settings.ipadx = 40;
        settings.ipady = 10;
        settings.insets = new Insets(0,20,0,0);
        this.add(labelDefense, settings);

        this.labelMaxHP= new JLabel("MAX HP : "+Hero.MAX_HP);
        this.labelMaxHP.setForeground(Color.RED);
        this.labelMaxHP.setFont(fontAttributes);
        settings = new GridBagConstraints();
        settings.gridx = 5;
        settings.gridy=0;
        settings.ipadx = 40;
        settings.ipady = 10;
        settings.insets = new Insets(0,20,0,0);
        this.add(labelMaxHP, settings);


        this.repaint();

    }

    /**
     * Metodo para pintar los componentes del menu superior
     */
    public void paintMenuComponents(Hero hero, boolean endLevel) {
        this.hero = hero;
        if(hero!=null){
            if(hero.getTotalHp() == Hero.MAX_HP){
                this.canvasHeroImage.setIcon(new ImageIcon("src/main/resources/hero/faces/normal.png", "normalFace"));
            }
            if (hero.getTotalHp() < Hero.MAX_HP / 2) {
                this.canvasHeroImage.setIcon(new ImageIcon("src/main/resources/hero/faces/danger.png", "dangerFace"));
            }
            if (hero.getTotalHp() <= 0) {
                this.canvasHeroImage.setIcon(new ImageIcon("src/main/resources/hero/faces/defeat.png", "defeatFace"));
            }
            if(this.gamePane.isEndLevel()){
                this.canvasHeroImage.setIcon(new ImageIcon("src/main/resources/hero/faces/victory.png", "victoryFace"));
            }
            this.heroLife.setValue(gamePane.getHero().getTotalHp());
            this.heroLife.setString(String.valueOf(hero.getTotalHp()));
        }
    }

    /**
     * Coloca un mapa en la barra del menu
     */
    public void putMapOnMenu(int i) {
        switch (i){
            case 0:
                this.map.setBounds(0,0,this.getWidth(),this.getHeight());
                this.map.setIcon(new ImageIcon("src/main/resources/maps/worldMap_1.png","map"));
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
        }
    }
}
