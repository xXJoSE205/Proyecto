package p3.mvc.interfaz;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPrincipal extends JPanel implements ActionListener{
    private JButton inSes = new JButton("Iniciar Sesion");private JButton buscar = new JButton("Buscar");
    private final ImageIcon logo = new ImageIcon("definitivo3.png");
    private JLabel imagen;
    private JLabel texto = new JLabel("BIENVENIDO A MUZSKA");
    private JPanel select = new JPanel(new GridLayout(1, 2));
    private GuiInmobiliaria gui;

    PanelPrincipal(GuiInmobiliaria gui){
        this.gui = gui;

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        select.add(inSes);
        select.add(buscar);
        select.setVisible(true);

        Image newLogo = logo.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_DEFAULT);
        logo.setImage(newLogo);
        imagen = new JLabel(logo);

        layout.putConstraint(SpringLayout.NORTH, imagen, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, imagen, 0, SpringLayout.HORIZONTAL_CENTER, this);

        layout.putConstraint(SpringLayout.NORTH, texto, 15, SpringLayout.SOUTH, imagen);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, imagen);

        layout.putConstraint(SpringLayout.NORTH, select, 10, SpringLayout.SOUTH, texto);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, imagen);

        this.add(imagen);
        this.add(texto);
        this.add(select);

        inSes.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento){
        if(evento.getSource()==inSes){
            this.gui.getControlador().goLogin();
        }/* else if(evento.getSource()==buscar){
            this.gui.getControlador().busqueda();
        }*/
    }
}
