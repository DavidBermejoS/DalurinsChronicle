package Main;

import java.awt.*;
import Window.Window;

/**
 * <h2>Clase Main</h2>
 * Instancia la clase ventana y gestiona la ejecución del hilo principal del
 * programa
 * @see Window
 * @author David Bermejo Simon
 */
public class Main {
    /**
     * Hilo principal "main"
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Window window = new Window();
                window.startGame();
            }
        });

    }

}
