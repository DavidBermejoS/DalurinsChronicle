package Utilities;

import java.io.File;
import java.util.List;

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
    public final static int WALK_ACTION = 4;


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
    public String[] getRoutesByDirection(int target, int action, String direction) {
        getTargetActionDir(target, action);
        File[] contentDir = dirTargetAction.listFiles();
        int count = 0;
        for (int i = 0; i < contentDir.length; i++) {
            if (direction.equalsIgnoreCase("N")) {
                if (contentDir[i].getName().startsWith("walk_3")) {
                    vectorRoutes[count] = contentDir[i].getAbsolutePath();
                    count++;
                }
            }
            if (direction.equalsIgnoreCase("S")) {
                if (contentDir[i].getName().startsWith("walk_7")) {
                    vectorRoutes[count] = contentDir[i].getAbsolutePath();
                    count++;
                }
            }
            if (direction.equalsIgnoreCase("E")) {
                if (contentDir[i].getName().startsWith("walk_1")) {
                    vectorRoutes[count] = contentDir[i].getAbsolutePath();
                    count++;
                }
            }
            if (direction.equalsIgnoreCase("W")) {
                if (contentDir[i].getName().startsWith("walk_5")) {
                    vectorRoutes[count] = contentDir[i].getAbsolutePath();
                    count++;
                }
            }
            if(count == 10){
                return this.vectorRoutes;
            }
        }
        return this.vectorRoutes;
    }


}
