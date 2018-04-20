package p3.Interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelLogin extends JPanel{
    PanelLogin(){
        JRadioButton demandante = new JRadioButton("Demandante");
        JRadioButton ofertante = new JRadioButton("Ofertante");
        JRadioButton gerente = new JRadioButton("Gerente");

        ButtonGroup grupo = new ButtonGroup();

        grupo.add(demandante);
        grupo.add(ofertante);
        grupo.add(gerente);

        JPanel selectLogin = new JPanel(new GridLayout(3, 1));
        selectLogin.add(demandante);
        selectLogin.add(ofertante);
        selectLogin.add(gerente);

        selectLogin.setVisible(true);

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel etiqueta1 = new JLabel("NIF: ");
        final JTextField nif = new JTextField(10);
        JLabel etiqueta2 = new JLabel("Contrasena: ");
        final JPasswordField pswd = new JPasswordField(15);
        JButton boton = new JButton("Iniciar sesion");

        layout.putConstraint(SpringLayout.EAST, etiqueta1, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nif, 0, SpringLayout.WEST, pswd);
        layout.putConstraint(SpringLayout.NORTH, nif, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta2, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, pswd, 5, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, pswd, 5, SpringLayout.SOUTH, nif);
        layout.putConstraint(SpringLayout.NORTH, selectLogin, 10, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, selectLogin, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, boton, 10, SpringLayout.SOUTH, selectLogin);
        layout.putConstraint(SpringLayout.WEST, boton, 0, SpringLayout.WEST, selectLogin);

        this.add(etiqueta1);
        this.add(etiqueta2);
        this.add(nif);
        this.add(pswd);
        this.add(boton);
        this.add(selectLogin);
        this.setPreferredSize(new Dimension(200,100));
        this.setVisible(true);
    }
}
