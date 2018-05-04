package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelCrearInmueble extends JPanel implements ActionListener{
    private final JTextField nHab = new JTextField("-1", 2);
    private final JTextField nBanos = new JTextField("-1", 2);
    private final JTextField dim = new JTextField("-1", 5);
    private final JTextField direccion = new JTextField(30);
    private final JTextField planta = new JTextField("-1", 2);
    private JCheckBox casilla = new JCheckBox("Ascensor");
    private JButton crear = new JButton("Crear inmueble");
    private JButton volver = new JButton("Cancelar");
    private JLabel texto = new JLabel("");
    private GuiInmobiliaria gui;

    PanelCrearInmueble(GuiInmobiliaria gui){
        this.gui=gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(crear);
        JPanel select = new JPanel(new GridLayout(1, 2));
        select.add(volver);
        select.add(crear);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("Nº Habitaciones: ");
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.WEST, nHab, 5, SpringLayout.EAST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, nHab, 5, SpringLayout.NORTH, this);
        JLabel etiqueta2 = new JLabel("Nº Banos: ");
        layout.putConstraint(SpringLayout.EAST, etiqueta2, 0, SpringLayout.EAST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, nBanos, 0, SpringLayout.WEST, nHab);
        layout.putConstraint(SpringLayout.NORTH, nBanos, 5, SpringLayout.SOUTH, nHab);
        JLabel etiqueta3 = new JLabel("Dimensiones: ");
        layout.putConstraint(SpringLayout.EAST, etiqueta3, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 8, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, dim, 0, SpringLayout.WEST, nBanos);
        layout.putConstraint(SpringLayout.NORTH, dim, 5, SpringLayout.SOUTH, nBanos);
        JLabel etiqueta4 = new JLabel("Direccion: ");
        layout.putConstraint(SpringLayout.EAST, etiqueta4, 0, SpringLayout.EAST, etiqueta3);
        layout.putConstraint(SpringLayout.NORTH, etiqueta4, 8, SpringLayout.SOUTH, etiqueta3);
        layout.putConstraint(SpringLayout.WEST, direccion, 0, SpringLayout.WEST, dim);
        layout.putConstraint(SpringLayout.NORTH, direccion, 5, SpringLayout.SOUTH, dim);
        JLabel etiqueta5 = new JLabel("Planta: ");
        layout.putConstraint(SpringLayout.EAST, etiqueta5, 0, SpringLayout.EAST, etiqueta4);
        layout.putConstraint(SpringLayout.NORTH, etiqueta5, 8, SpringLayout.SOUTH, etiqueta4);
        layout.putConstraint(SpringLayout.WEST, planta, 0, SpringLayout.WEST, direccion);
        layout.putConstraint(SpringLayout.NORTH, planta, 5, SpringLayout.SOUTH, direccion);
        layout.putConstraint(SpringLayout.WEST, casilla, 0, SpringLayout.WEST, planta);
        layout.putConstraint(SpringLayout.NORTH, casilla, 8, SpringLayout.SOUTH, etiqueta5);
        layout.putConstraint(SpringLayout.NORTH, select, 10, SpringLayout.SOUTH, casilla);
        layout.putConstraint(SpringLayout.WEST, select, 0, SpringLayout.WEST, casilla);
        layout.putConstraint(SpringLayout.NORTH, texto, 8, SpringLayout.SOUTH, select);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, select);

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
        this.add(select);
        this.add(texto);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(600, 400));
        volver.addActionListener(this);
        crear.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        texto.setVisible(false);
        if(e.getSource()==volver){
            gui.getControlador().volverOfertante();
        }else if(e.getSource()==crear){
            gui.getControlador().crearInmueble(Integer.parseInt(nHab.getText()), Integer.parseInt(nBanos.getText())
                    , Integer.parseInt(dim.getText()), direccion.getText(), Integer.parseInt(planta.getText())
                    , casilla.isSelected());
        }
    }

    public void creadoOK(String cadena){
        texto.setText(cadena);
        texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
