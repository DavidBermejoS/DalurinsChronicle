package Sprites;

import Utilities.RpgDialog;

/**
 * <h2>Clase Item</h2>
 * Esta clase gestiona los efectos que relizan los items sobre el heroe o los enemigos actuales del nivel.
 *
 */
public class Item extends Sprite {

    String name;
    String description;
    boolean deactivate;

    public Item(){
        this.deactivate = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Metodo encargado de gestionar los efectos del mismo item en el heroe.
     * @param hero : instancia del heroe
     */
    public void makeEffectHero(Hero hero) {
        if(!deactivate){
            if(this.name.equalsIgnoreCase("health_potion")){
                if(hero.getTotalHp() < Hero.MAX_HP-15){
                    hero.setTotalHp(hero.getTotalHp()+15);
                }else{
                    hero.setTotalHp(Hero.MAX_HP);
                }
            }
            this.setImageSprite(null);
            this.deactivate = true;
        }
    }
}
