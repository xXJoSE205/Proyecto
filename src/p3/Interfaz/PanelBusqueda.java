package p3.Interfaz;

import javax.swing.*;

public class PanelBusqueda extends JPanel {
    PanelBusqueda(){
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel etiqueta1 = new JLabel("Numero de habitaciones:");
        final JTextField numero = new JTextField("", 2);
        JLabel etiqueta2 = new JLabel("Numero de banos:");
        final JTextField numeroBan = new JTextField("", 2);
        JLabel etiqueta3 = new JLabel("Dimensiones:");
        final JTextField dim = new JTextField("", 3);
        JLabel etiqueta4 = new JLabel("Numero de habitaciones:");
        final JTextField planta = new JTextField("", 2);
        JLabel etiqueta5 = new JLabel("Direccion:");
        final JTextField direccion = new JTextField("", 100);
        JCheckBox casilla1 = new JCheckBox("Ascensor");


        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, numero, 5, SpringLayout.EAST, etiqueta1);
        // El norte de field estará a 5 pixels desde el norte del contenedor
        layout.putConstraint(SpringLayout.NORTH, numero, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, etiqueta2, 0, SpringLayout.EAST, etiqueta1);
        // El norte de label2 estará a 5 pixels del borde inferior (SOUTH) de label
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        // La izquierda de field2 alienada con la izquierda de field
        layout.putConstraint(SpringLayout.WEST, numeroBan, 0, SpringLayout.WEST, numero);
        // El norte de field2, 5 pixels más abajo de field
        layout.putConstraint(SpringLayout.NORTH, numeroBan, 5, SpringLayout.SOUTH, numero);

        layout.putConstraint(SpringLayout.EAST, etiqueta3, 0, SpringLayout.EAST, etiqueta2);
        // El norte de label2 estará a 5 pixels del borde inferior (SOUTH) de label
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 8, SpringLayout.SOUTH, etiqueta2);
        // La izquierda de field2 alienada con la izquierda de field
        layout.putConstraint(SpringLayout.WEST, dim, 0, SpringLayout.WEST, numeroBan);
        // El norte de field2, 5 pixels más abajo de field
        layout.putConstraint(SpringLayout.NORTH, dim, 5, SpringLayout.SOUTH, numeroBan);

        layout.putConstraint(SpringLayout.EAST, etiqueta4, 0, SpringLayout.EAST, etiqueta3);
        // El norte de label2 estará a 5 pixels del borde inferior (SOUTH) de label
        layout.putConstraint(SpringLayout.NORTH, etiqueta4, 8, SpringLayout.SOUTH, etiqueta3);
        // La izquierda de field2 alienada con la izquierda de field
        layout.putConstraint(SpringLayout.WEST, planta, 0, SpringLayout.WEST, dim);
        // El norte de field2, 5 pixels más abajo de field
        layout.putConstraint(SpringLayout.NORTH, planta, 5, SpringLayout.SOUTH, dim);

        layout.putConstraint(SpringLayout.EAST, etiqueta5, 0, SpringLayout.EAST, etiqueta4);
        // El norte de label2 estará a 5 pixels del borde inferior (SOUTH) de label
        layout.putConstraint(SpringLayout.NORTH, etiqueta5, 8, SpringLayout.SOUTH, etiqueta4);
        // La izquierda de field2 alienada con la izquierda de field
        layout.putConstraint(SpringLayout.WEST, direccion, 0, SpringLayout.WEST, planta);
        // El norte de field2, 5 pixels más abajo de field
        layout.putConstraint(SpringLayout.NORTH, direccion, 5, SpringLayout.SOUTH, planta);

        layout.putConstraint(SpringLayout.EAST, casilla1, 0, SpringLayout.EAST, etiqueta5);
        // El norte de label2 estará a 5 pixels del borde inferior (SOUTH) de label
        layout.putConstraint(SpringLayout.NORTH, casilla1, 8, SpringLayout.SOUTH, etiqueta5);


        this.add(etiqueta1);
        this.add(numero);
        this.add(numeroBan);
        this.add(etiqueta2);
        this.add(etiqueta3);
        this.add(dim);
        this.add(etiqueta4);
        this.add(etiqueta5);
        this.add(direccion);
        this.add(planta);
        this.add(casilla1);

    }
}
