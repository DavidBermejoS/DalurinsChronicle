package Utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <h2>Clase ResourcesCollector</h2>
 * Clase de utilidades encargada de recoger los recursos que se puedan necesitar en el
 * transcurso del juego
 *
 * @author David Bermejo Simon
 */
public class ResourcesCollector {


    public final static int HERO_TARGET = 1;
    public final static int ENEMY_TARGET = 2;


    public final static String ITEM_RESOURCES = "src/main/resources/item";
    public final static String POTION_RESOURCES = "src/main/resources/potions";

    public final static String HERO_ATTACK = "src/main/resources/hero/attack";
    public final static String HERO_BLOCK = "src/main/resources/hero/block";
    public final static String HERO_DEATH = "src/main/resources/hero/death";
    public final static String HERO_WALK = "src/main/resources/hero/walk";

    public final static String ENEMY_ATTACK = "src/main/resources/enemy/attack";
    public final static String ENEMY_BLOCK = "src/main/resources/enemy/block";
    public final static String ENEMY_DEATH = "src/main/resources/enemy/death";
    public final static String ENEMY_WALK = "src/main/resources/enemy/walk";


    public final static int ATTACK_ACTION = 1;
    public final static int BLOCK_ACTION = 2;
    public final static int DEATH_ACTION = 3;
    public final static int WALK_ACTION = 5;


    File dirTargetAction;
    String[][] matrixRoutes;
    private String[] vectorRoutes;
    String route;

    /**
     * Constructor de la clase, se instancia la matriz de rutas (String) del recurso que necesitemos
     */
    public ResourcesCollector() {
        this.matrixRoutes = new String[8][10];
        vectorRoutes = new String[10];
    }


    /**
     * Metodo encargado de rellenar el array de rutas con las rutas a las imagenes que necesitamos según
     * el target que es recibido como parámetro
     *
     * @param target
     * @return
     */
    public String[][] getRoutes(int target, int action) {
        getTargetActionDir(target, action);
        File[] files = dirTargetAction.listFiles();
        int count = -1;
        for (int i = 0; i < matrixRoutes.length; i++) {
            for (int j = 0; j < matrixRoutes[i].length; j++) {
                count++;
                matrixRoutes[i][j] = files[count].getAbsolutePath();
            }
        }
        return this.matrixRoutes;
    }

    /**
     * Metodo encargado de devolver el directorio según el target especificado y la acción que va a realizar
     *
     * @param target  : parametro numerico entero.
     *                1- El target sera el heroe
     *                2- El target sera el enemigo
     * @param action: parametro numerico entero
     *                1- accion de atacar
     *                2- accion de bloquear
     *                3- accion de muerte
     *                4- accion de andar
     */
    private void getTargetActionDir(int target, int action) {
        if (target == HERO_TARGET) {
            switch (action) {
                case ATTACK_ACTION:
                    dirTargetAction = new File(HERO_ATTACK);
                    break;
                case BLOCK_ACTION:
                    dirTargetAction = new File(HERO_BLOCK);
                    break;
                case DEATH_ACTION:
                    dirTargetAction = new File(HERO_DEATH);
                    break;
                case WALK_ACTION:
                    dirTargetAction = new File(HERO_WALK);
                    break;
            }
        } else if (target == ENEMY_TARGET) {
            switch (action) {
                case ATTACK_ACTION:
                    dirTargetAction = new File(ENEMY_ATTACK);
                    break;
                case BLOCK_ACTION:
                    dirTargetAction = new File(ENEMY_BLOCK);
                    break;
                case DEATH_ACTION:
                    dirTargetAction = new File(ENEMY_DEATH);
                    break;
                case WALK_ACTION:
                    dirTargetAction = new File(ENEMY_WALK);
                    break;
            }
        }
    }



    /**
     * Metodo encargado de devolver un array de string según el target especificado y la acción que va a realizar
     * devolviendo directamente la ruta en dicho array
     *
     * @param target  : parametro numerico entero.
     *                1- El target sera el heroe
     *                2- El target sera el enemigo
     * @param action: parametro numerico entero
     *                1- accion de atacar
     *                2- accion de bloquear
     *                3- accion de muerte
     *                4- accion de andar
     */
    public String getRouteByDirection(int target, int action, String direction) {
        getTargetActionDir(target, action);
        File[] directionDir = dirTargetAction.listFiles();
        String dirByActionTarget = null;
        int count = 0;
        for (int i = 0; i < directionDir.length; i++) {
            if (direction.equalsIgnoreCase("N")) {
                if (directionDir[i].getName().startsWith("ascend")) {
                    dirByActionTarget= directionDir[i].getAbsolutePath();
                    count++;
                }
            }
            if (direction.equalsIgnoreCase("S")) {
                if (directionDir[i].getName().startsWith("descend")) {
                    dirByActionTarget = directionDir[i].getAbsolutePath();
                    count++;
                }
            }
            if (direction.equalsIgnoreCase("E")) {
                if (directionDir[i].getName().startsWith("right")) {
                    dirByActionTarget = directionDir[i].getAbsolutePath();
                    count++;
                }
            }
            if (direction.equalsIgnoreCase("W")) {
                if (directionDir[i].getName().startsWith("left")) {
                    dirByActionTarget= directionDir[i].getAbsolutePath();
                    count++;
                }
            }

            if (direction.equalsIgnoreCase("NW") || direction.equalsIgnoreCase("WN")) {
                if (directionDir[i].getName().startsWith("upleft")) {
                    dirByActionTarget = directionDir[i].getAbsolutePath();
                    count++;
                }
            }

            if (direction.equalsIgnoreCase("NE") || direction.equalsIgnoreCase("EN")) {
                if (directionDir[i].getName().startsWith("upright")) {
                    dirByActionTarget = directionDir[i].getAbsolutePath();
                    count++;
                }
            }

            if (direction.equalsIgnoreCase("SW") || direction.equalsIgnoreCase("WS")) {
                if (directionDir[i].getName().startsWith("downleft")) {
                    dirByActionTarget = directionDir[i].getAbsolutePath();
                    count++;
                }
            }

            if (direction.equalsIgnoreCase("SE") || direction.equalsIgnoreCase("ES")) {
                if (directionDir[i].getName().startsWith("downright")) {
                    dirByActionTarget = directionDir[i].getAbsolutePath();
                    count++;
                }
            }
            if(count == 1){
                return dirByActionTarget;
            }
        }
        return dirByActionTarget;
    }


    public BufferedImage[] getImagesTargetActionDirection(int target, int action, String direction){
        String dir = getRouteByDirection(target,action,direction);
        BufferedImage[] bufferedImages = new BufferedImage[10];
        if(dir != null){
            File directory = new File(dir);
            File[] images = directory.listFiles();
            for (int i = 0; i < bufferedImages.length; i++) {
                try {
                    bufferedImages[i]= ImageIO.read(images[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bufferedImages;
        }
        return bufferedImages;
    }

    /**
     * Metodo para extraer todos los rostros del heroe
     * @return
     */
    public BufferedImage[] getHeroFaces(){
        BufferedImage bufferImageAux = null;
        try {
            bufferImageAux = ImageIO.read(new File("src/main/resources/hero/faces/hero.png"));
            BufferedImage [] images = new BufferedImage[8];
            int count = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    images[count] = new BufferedImage(96,96,BufferedImage.TYPE_INT_ARGB);
                    images[count] = bufferImageAux.getSubimage(j*96,i*96,96,96);
                    count++;
                }
            }
            return images;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public BufferedImage[] getEnemyDeathsImages(){
        BufferedImage bufferImageAux = null;
        try {
            bufferImageAux = ImageIO.read(new File("src/main/resources/enemy/death/bSpearman_Die_Down_strip8.png"));
            BufferedImage [] images = new BufferedImage[8];
            for (int i = 0; i < images.length; i++) {
                    images[i] = new BufferedImage(96,96,BufferedImage.TYPE_INT_ARGB);
                    images[i] = bufferImageAux.getSubimage(i*150,0,150,150);
            }
            return images;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public BufferedImage[][] getEnemyAttacksImages(){
        BufferedImage bufferImageAux = null;
        try {
            bufferImageAux = ImageIO.read(new File("src/main/resources/enemy/attack/tile_attack.png"));
            BufferedImage [][] images = new BufferedImage[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    images[i][j] = new BufferedImage(171,171,BufferedImage.TYPE_INT_ARGB);
                    images[i][j] = bufferImageAux.getSubimage(j*171,i*171,96,96);
                }
            }
            return images;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
