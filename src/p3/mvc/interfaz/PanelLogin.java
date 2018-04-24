package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelLogin extends JPanel{
    PanelLogin(){
        JRadioButton demandante = new JRadioButton("Demandante");
        JRadioButton ofertante = new JRadioButton("Ofertante");
        JRadioButton gerente = new JRadioButton("Gerente");

        ButtonGroup grupo1 = new ButtonGroup();

        grupo1.add(demandante);
        grupo1.add(ofertante);
        grupo1.add(gerente);

        JPanel selectType = new JPanel(new GridLayout(3, 1));
        selectType.add(demandante);
        selectType.add(ofertante);
        selectType.add(gerente);

        selectType.setVisible(true);

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JLabel etiqueta1 = new JLabel("NIF: ");
        final JTextField nif = new JTextField(10);
        JLabel etiqueta2 = new JLabel("Contrasena: ");
        final JPasswordField pswd = new JPasswordField(15);
        ButtonGroup grupo2 = new ButtonGroup();
        JButton inSes = new JButton("Iniciar Sesion");
        JButton volver = new JButton("Volver");
        grupo2.add(inSes);
        grupo2.add(volver);
        JPanel selectLogin = new JPanel(new GridLayout(1, 2));
        selectLogin.add(inSes);
        selectLogin.add(volver);
        selectLogin.setVisible(true);

        layout.putConstraint(SpringLayout.EAST, etiqueta1, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, nif, 0, SpringLayout.WEST, pswd);
        layout.putConstraint(SpringLayout.NORTH, nif, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta2, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, pswd, 5, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, pswd, 5, SpringLayout.SOUTH, nif);
        layout.putConstraint(SpringLayout.NORTH, selectType, 10, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, selectType, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, selectLogin, 10, SpringLayout.SOUTH, selectType);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, selectLogin, 0, SpringLayout.HORIZONTAL_CENTER, pswd);

        this.add(etiqueta1);
        this.add(etiqueta2);
        this.add(nif);
        this.add(pswd);
        this.add(selectLogin);
        this.add(selectType);
        this.setVisible(true);
    }
}
