package Sprites;

/**
 * <h2>Clase Skill</h2>
 * Esta clase sirve para recoger las habilidades utilizadas por el personaje
 * @author David Bermejo Simon
 **/
public class Skill extends Sprite{

    String name;
    String description;

    public Skill(){

    }

    /**
     * Metodo encargado de calcular los efectos de la habilidad y aplicar sus efectos sobre los componentes
     *
     */
    public void setAction(){

    }

    public void animateSkill(){

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
}
