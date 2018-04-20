package p3.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelCrearInmueble extends JPanel{
    PanelCrearInmueble(){
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel etiqueta1 = new JLabel("Nº Habitaciones: ");
        final JTextField nHab = new JTextField(2);
        JLabel etiqueta2 = new JLabel("Nº Banos: ");
        final JTextField nBanos = new JTextField(2);
        JLabel etiqueta3 = new JLabel("Dimensiones: ");
        final JTextField dim = new JTextField(5);
        JLabel etiqueta4 = new JLabel("Direccion: ");
        final JTextField direccion = new JTextField(30);
        JLabel etiqueta5 = new JLabel("Planta: ");
        final JTextField planta = new JTextField(2);
        JCheckBox casilla = new JCheckBox("Ascensor");
        final JButton crear = new JButton("Crear inmueble");

        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, nHab, 5, SpringLayout.EAST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, nHab, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, etiqueta2, 0, SpringLayout.EAST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, nBanos, 0, SpringLayout.WEST, nHab);
        layout.putConstraint(SpringLayout.NORTH, nBanos, 5, SpringLayout.SOUTH, nHab);
        layout.putConstraint(SpringLayout.EAST, etiqueta3, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 8, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, dim, 0, SpringLayout.WEST, nBanos);
        layout.putConstraint(SpringLayout.NORTH, dim, 5, SpringLayout.SOUTH, nBanos);
        layout.putConstraint(SpringLayout.EAST, etiqueta4, 0, SpringLayout.EAST, etiqueta3);
        layout.putConstraint(SpringLayout.NORTH, etiqueta4, 8, SpringLayout.SOUTH, etiqueta3);
        layout.putConstraint(SpringLayout.WEST, direccion, 0, SpringLayout.WEST, dim);
        layout.putConstraint(SpringLayout.NORTH, direccion, 5, SpringLayout.SOUTH, dim);
        layout.putConstraint(SpringLayout.EAST, etiqueta5, 0, SpringLayout.EAST, etiqueta4);
        layout.putConstraint(SpringLayout.NORTH, etiqueta5, 8, SpringLayout.SOUTH, etiqueta4);
        layout.putConstraint(SpringLayout.WEST, planta, 0, SpringLayout.WEST, direccion);
        layout.putConstraint(SpringLayout.NORTH, planta, 5, SpringLayout.SOUTH, direccion);
        layout.putConstraint(SpringLayout.WEST, casilla, 0, SpringLayout.WEST, planta);
        layout.putConstraint(SpringLayout.NORTH, casilla, 8, SpringLayout.SOUTH, etiqueta5);
        layout.putConstraint(SpringLayout.NORTH, crear, 10, SpringLayout.SOUTH, casilla);
        layout.putConstraint(SpringLayout.WEST, crear, 0, SpringLayout.WEST, casilla);

        this.add(etiqueta1);
        this.add(nHab);
        this.add(etiqueta2);
        this.add(nBanos);
        this.add(etiqueta3);
        this.add(dim);
        this.add(etiqueta4);
        this.add(direccion);
        this.add(etiqueta5);
        this.add(planta);
        this.add(casilla);
        this.add(crear);
        this.setPreferredSize(new Dimension(200,100));
        this.setVisible(true);
    }
}
