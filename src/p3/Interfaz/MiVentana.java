package p3.Interfaz;

import javax.swing.*;
import java.awt.*;

public class MiVentana extends JFrame  {

    public MiVentana() {
        super("Muzska");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Image icon = new ImageIcon(("definitivo.png")).getImage();
        setIconImage(icon);

        setSize(350, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MiVentana();
    }


}
