package p3.Interfaz;


import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel{
    PanelPrincipal(){
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        ButtonGroup grupo = new ButtonGroup();
        JButton inSes = new JButton("Iniciar Sesion");
        JButton buscar = new JButton("Buscar");
        grupo.add(inSes);
        grupo.add(buscar);

        JPanel select = new JPanel();

        ImageIcon logo = new ImageIcon("definitivo3.png");
        Image newLogo = logo.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_DEFAULT);
        logo.setImage(newLogo);
        JLabel imagen = new JLabel(logo);
        JLabel texto = new JLabel("BIENVENIDO A MUZSKA");

        layout.putConstraint(SpringLayout.NORTH, imagen, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.NORTH, texto, 15, SpringLayout.SOUTH, imagen);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, imagen);

        this.add(imagen);
        this.add(texto);
        this.setVisible(true);
    }
}
