package p3.mvc.interfaz;

import p3.src.Inmueble;
import p3.src.Ofertante;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MiVentana extends JFrame  {

    public MiVentana() {
        super("Muzska");
        Container contenedor = this.getContentPane();
        contenedor.setLayout(new BorderLayout());

        Image icon = new ImageIcon("definitivo3.png").getImage();
        setIconImage(icon);

        List<Inmueble> lista = new ArrayList<>();
        Ofertante ofertante = new Ofertante("Tony", "Stark", "12345678P", "IronMan",
                "0123456789012345");
        Inmueble inmueble = new Inmueble(5,2,150,"C/ del diamante 5",5,
                true, ofertante);
        lista.add(inmueble);
        JPanel panel = new PanelDemandante(new GuiSimple("Muzska"),"Jorge");

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
