package p3.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelBusquedaAvanzada extends JPanel {
    PanelBusquedaAvanzada() {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel etiqueta1 = new JLabel("Precio:");
        final JTextField numero = new JTextField("", 2);
        JCheckBox casilla1 = new JCheckBox("Ascensor ");

        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, numero, 5, SpringLayout.EAST, etiqueta1);
        // El norte de field estará a 5 pixels desde el norte del contenedor
        layout.putConstraint(SpringLayout.NORTH, numero, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, casilla1, 0, SpringLayout.EAST, etiqueta1);
        // El norte de label2 estará a 5 pixels del borde inferior (SOUTH) de label
        layout.putConstraint(SpringLayout.NORTH, casilla1, 8, SpringLayout.SOUTH, etiqueta1);

        this.add(numero);
        this.add(casilla1);
        this.setPreferredSize(new Dimension(200,100));
        this.add(etiqueta1);
    }
}
