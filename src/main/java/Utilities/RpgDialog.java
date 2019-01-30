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
    public static void createDialog(int messageNum, final JLabel canvasMessage) {
        if (!messageChecked) {
            switch (messageNum) {
                case 0:
                    canvasMessage.setIcon(new ImageIcon("src/main/resources/messages/message0.png", ""));
                    canvasMessage.setVisible(true);
                    setMessageChecked(false);
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 9:
                    canvasMessage.setIcon(new ImageIcon("src/main/resources/messages/message1.png", ""));
                    canvasMessage.setVisible(true);
                    setMessageChecked(false);
                    break;
            }
            final JLabel finalCanvasMessage = canvasMessage;
            canvasMessage.addMouseListener(new MouseAdapter() {
                boolean messageChecked = RpgDialog.messageChecked;
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    messageChecked = true;
                    finalCanvasMessage.setVisible(false);
                    setMessageChecked(messageChecked);
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

    public static void generateDialog(GamePane gamePane, JLabel canvasMessage, int messageNum) {
        if(canvasMessage== null){
            canvasMessage = new JLabel();
        }
        gamePane.add(canvasMessage);
        createDialog(messageNum, canvasMessage);
        while (!messageChecked) {
            canvasMessage.setVisible(true);
            System.out.println("pillado aqui");
        }
        canvasMessage.setVisible(false);
    }
}
