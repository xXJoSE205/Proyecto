package p3.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelLogin extends JPanel{
    PanelLogin(){
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);


        JLabel etiqueta1 = new JLabel("NIF: ");
        final JTextField nif = new JTextField("nif",10);
        JLabel etiqueta2 = new JLabel("Contraseña: ");
        final JTextField pswd = new JTextField("contraseña",20);
        JButton boton = new JButton("Iniciar sesion");

        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        // El norte (NORTH) de label estará a 5 pixels del norte del contenedor
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        // La izquierda de field estará a 5 pixels desde el borde derecho (EAST) de label
        layout.putConstraint(SpringLayout.WEST, nif, 5, SpringLayout.EAST, etiqueta1);
        // El norte de field estará a 5 pixels desde el norte del contenedor
        layout.putConstraint(SpringLayout.NORTH, nif, 5, SpringLayout.NORTH, this);
        // La derecha de label2 estará a 0 pixels (alineada) del borde derecho de label
        layout.putConstraint(SpringLayout.EAST, etiqueta2, 0, SpringLayout.EAST, etiqueta1);
        // El norte de label2 estará a 5 pixels del borde inferior (SOUTH) de label
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        // La izquierda de field2 alienada con la izquierda de field
        layout.putConstraint(SpringLayout.WEST, pswd, 0, SpringLayout.WEST, nif);
        // El norte de field2, 5 pixels más abajo de field
        layout.putConstraint(SpringLayout.NORTH, pswd, 5, SpringLayout.SOUTH, nif);

        this.add(etiqueta1);
        this.add(etiqueta2);
        this.add(nif);
        this.add(pswd);
        this.add(boton);
        this.setPreferredSize(new Dimension(250,50));
        this.setVisible(true);
    }
}
