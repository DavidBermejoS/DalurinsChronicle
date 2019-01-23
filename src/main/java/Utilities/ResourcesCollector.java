package Utilities;

import java.io.File;

/**
 * <h2>Clase ResourcesCollector</h2>
 * Clase de utilidades encargada de recoger los recursos que se puedan necesitar en el
 * transcurso del juego
 *
 * @author David Bermejo Simon
 *
 */
public class ResourcesCollector {


    final static int HERO_TARGET = 1;
    final static int ENEMY_TARGET = 2;


    final static String ITEM_RESOURCES = "/resources/item";

    final static String HERO_ATTACK = "/resources/hero/attack";
    final static String HERO_BLOCK = "/resources/hero/block";
    final static String HERO_DEATH = "/resources/hero/death";
    final static String HERO_WALK = "/resources/hero/walk";

    final static String ENEMY_ATTACK = "/resources/enemy/attack";
    final static String ENEMY_BLOCK = "/resources/enemy/block";
    final static String ENEMY_DEATH = "/resources/enemy/death";
    final static String ENEMY_WALK = "/resources/enemy/walk";

    final static int ATTACK_ACTION = 1;
    final static int BLOCK_ACTION = 2;
    final static int DEATH_ACTION = 3;
    final static int WALK_ACTION = 4;



    File dirTargetAction;
    String [][] routes;
    String route;

    /**
     * Constructor de la clase, se instancia la matriz de rutas (String) del recurso que necesitemos
     */
    public ResourcesCollector(){
        this.routes = new String[8][10];
    }


    /**
     * Metodo encargado de rellenar el array de rutas con las rutas a las imagenes que necesitamos según
     * el target que es recibido como parámetro
     * @param target
     * @return
     */
    public String[][] getRoutes(int target,int action){
        getTargetActionDir(target, action);
        File [] files = dirTargetAction.listFiles();
        int count = 0;
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                count++;
                routes[i][j]=files[count].getAbsolutePath();
            }
        }
        return this.routes;
    }

    /**
     * Metodo encargado de devolver el directorio según el target especificado y la acción que va a realizar
     * @param target : parametro numerico entero.
     *               1- El target sera el heroe
     *               2- El target sera el enemigo
     * @param action: parametro numerico entero
     *              1- accion de atacar
     *              2- accion de bloquear
     *              3- accion de muerte
     *              4- accion de andar
     */
    private void getTargetActionDir(int target, int action){
        if(target == HERO_TARGET){
            switch (action){
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
        }else if(target == ENEMY_TARGET){
            switch (action){
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





}
