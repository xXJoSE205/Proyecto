package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase contiene la informacion del panel Principal
 * Crea un panel en el que se muestran el logo de la empresa, un mensaje y unos botones para iniciar sesion y buscar
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
class PanelPrincipal extends JPanel implements ActionListener{
    private final JButton inSes = new JButton("Iniciar Sesion");
    private final JButton buscar = new JButton("Buscar");
    private final GuiInmobiliaria gui;

    /**
     * Constructor de PanelPrincipal
     * @param gui GUI de la inmobiliaria
     */
    PanelPrincipal(GuiInmobiliaria gui){
        this.gui = gui;

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JPanel select = new JPanel(new GridLayout(1, 2));
        select.add(inSes);
        select.add(buscar);
        select.setVisible(true);

        ImageIcon logo = new ImageIcon("logo.jpg");
        Image newLogo = logo.getImage().getScaledInstance(400, 400, java.awt.Image.SCALE_DEFAULT);
        logo.setImage(newLogo);
        JLabel imagen = new JLabel(logo);

        layout.putConstraint(SpringLayout.NORTH, imagen, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, imagen, 0, SpringLayout.HORIZONTAL_CENTER, this);

        JLabel texto = new JLabel("BIENVENIDO A MUZSKA");
        layout.putConstraint(SpringLayout.NORTH, texto, 15, SpringLayout.SOUTH, imagen);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, imagen);

        layout.putConstraint(SpringLayout.NORTH, select, 10, SpringLayout.SOUTH, texto);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, imagen);

        this.add(select);
        this.add(texto);
        this.add(imagen);

        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));

        inSes.addActionListener(this);
        buscar.addActionListener(this);
    }

    /**
     * Manejador de las acciones de los botones
     * @param evento Accion que se activa
     */
    public void actionPerformed(ActionEvent evento){
        if(evento.getSource()==inSes){
            this.gui.getControlador().goLogin();
        } else if(evento.getSource()==buscar){
            this.gui.getControlador().goBusqueda();
        }
    }
}
