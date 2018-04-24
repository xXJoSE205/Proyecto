package p3.Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class MiVentana extends JFrame  {

    public MiVentana() {
        super("Muzska");
        Container contenedor = this.getContentPane();
        contenedor.setLayout(new BorderLayout());

        Image icon = new ImageIcon("definitivo3.png").getImage();
        setIconImage(icon);

        JPanel panel = new PanelComentario(5);

        contenedor.add(panel, BorderLayout.CENTER);

        this.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 600);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame ventana = new MiVentana();
    }
}
