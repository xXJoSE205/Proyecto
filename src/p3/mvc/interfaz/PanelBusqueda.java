package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelBusqueda extends JPanel implements ActionListener {

    private JLabel etiqueta1 = new JLabel("Numero de habitaciones:");
    private final JTextField numero = new JTextField("", 2);
    private JLabel etiqueta2 = new JLabel("Numero de banos:");
    private final JTextField numeroBan = new JTextField("", 2);
    private JLabel etiqueta3 = new JLabel("Dimensiones:");
    private final JTextField dim = new JTextField("", 2);
    private JLabel etiqueta4 = new JLabel("Numero de habitaciones:");
    private final JTextField planta = new JTextField("", 2);
    private JLabel etiqueta5 = new JLabel("Direccion:");
    private final JTextField direccion = new JTextField("", 50);
    private JCheckBox casilla1 = new JCheckBox("Ascensor");
    private ButtonGroup grupo = new ButtonGroup();
    private JButton buscar = new JButton("Buscar");
    private JButton volver = new JButton("Volver");


    private JPanel select = new JPanel(new GridLayout(1, 2));
    private GuiInmobiliaria gui;

    PanelBusqueda(GuiInmobiliaria gui){
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);


        grupo.add(buscar);
        grupo.add(volver);
        select.add(buscar);
        select.add(volver);
        select.setVisible(true);


        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, numero, 5, SpringLayout.EAST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, numero, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, etiqueta2, 0, SpringLayout.EAST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, numeroBan, 0, SpringLayout.WEST, numero);
        layout.putConstraint(SpringLayout.NORTH, numeroBan, 5, SpringLayout.SOUTH, numero);

        layout.putConstraint(SpringLayout.EAST, etiqueta3, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 8, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, dim, 0, SpringLayout.WEST, numeroBan);
        layout.putConstraint(SpringLayout.NORTH, dim, 5, SpringLayout.SOUTH, numeroBan);

        layout.putConstraint(SpringLayout.EAST, etiqueta4, 0, SpringLayout.EAST, etiqueta3);
        layout.putConstraint(SpringLayout.NORTH, etiqueta4, 8, SpringLayout.SOUTH, etiqueta3);
        layout.putConstraint(SpringLayout.WEST, planta, 0, SpringLayout.WEST, dim);
        layout.putConstraint(SpringLayout.NORTH, planta, 5, SpringLayout.SOUTH, dim);

        layout.putConstraint(SpringLayout.EAST, etiqueta5, 0, SpringLayout.EAST, etiqueta4);
        layout.putConstraint(SpringLayout.NORTH, etiqueta5, 8, SpringLayout.SOUTH, etiqueta4);
        layout.putConstraint(SpringLayout.WEST, direccion, 0, SpringLayout.WEST, planta);
        layout.putConstraint(SpringLayout.NORTH, direccion, 5, SpringLayout.SOUTH, planta);

        layout.putConstraint(SpringLayout.EAST, casilla1, 0, SpringLayout.EAST, etiqueta5);
        layout.putConstraint(SpringLayout.NORTH, casilla1, 8, SpringLayout.SOUTH, etiqueta5);

        layout.putConstraint(SpringLayout.NORTH, select, 10, SpringLayout.SOUTH, casilla1);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, casilla1);


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
        this.add(select);
        this.setVisible(true);
        buscar.addActionListener(this);
        volver.addActionListener(this);

    }

    public void actionPerformed(ActionEvent evento) {
        String option1 = "";
        String option2 = "";
        String option3 = "";
        String option4 = "";
        String option5 = "";
        String option6 = "";
        if(evento.getSource()==buscar) {
            option1 = numero.getText();
            option2 = numeroBan.getText();
            option3 = dim.getText();
            option4 = planta.getText();
            option5 = direccion.getText();
            if (casilla1.isSelected()) {
                option6 = "true";
            } else {
                option6 = "false";
            }
            gui.getControlador().buscar(option1, option2, option3, option4, option5, option6);
        } else if(evento.getSource()==volver){
            gui.getControlador().volverDemandante();
        }
    }

    public void setError(String error) {

    }
}
