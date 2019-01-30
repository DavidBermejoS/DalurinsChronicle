package Utilities;

import Window.GamePane;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <h2>Clase RpgDialog</h2>
 * Clase encargada de generar un mensaje para el usuario en el nivel en el que se encuentra.
 */
public class RpgDialog {

    static boolean messageChecked;
    static JLabel canvasMessage;

    /**
     * Constructor de la clase
     */
    public RpgDialog() {

    }

    public RpgDialog(int n) {
        this.canvasMessage = new JLabel();
        this.messageChecked = false;
    }

    /**
     * Metodo encargado de crear un dialogo en modo de imagen
     *
     * @param messageNum:   numero de mensaje a imprimir
     * @param canvasMessage : JLabel sobre el que se pintará el mensaje
     */
    public static void createDialog(int messageNum, JLabel canvasMessage) {
        if (!messageChecked) {
            switch (messageNum) {
                case 0:
                    canvasMessage.setIcon(new ImageIcon("src/main/resources/messages/message0.png", ""));
                    canvasMessage.setVisible(true);
                    messageChecked = false;
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 9:
                    canvasMessage.setIcon(new ImageIcon("src/main/resources/messages/message1.png", ""));
                    canvasMessage.setVisible(true);
                    messageChecked = false;
                    break;
            }
            final JLabel finalCanvasMessage = canvasMessage;
            canvasMessage.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    messageChecked = true;
                }
            });

        }
    }


    public static boolean isMessageChecked() {
        return messageChecked;
    }

    public static void setMessageChecked(boolean messageChecked) {
        RpgDialog.messageChecked = messageChecked;
    }

    public void generateDialog(GamePane gamePane, JLabel canvasMessage, int messageNum) {
        if(canvasMessage== null){
            canvasMessage = new JLabel();
        }
        gamePane.add(canvasMessage);
        this.createDialog(messageNum, canvasMessage);
        while (!isMessageChecked()) {
            canvasMessage.setVisible(true);
        }
        canvasMessage.setVisible(false);
    }
}
