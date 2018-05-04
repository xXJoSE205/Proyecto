package p3.mvc.interfaz;

import p3.mvc.modelo.Demandante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelDesbloquearUsuario extends JPanel implements ActionListener {
    private JLabel etiqueta1 = new JLabel("Nombre: ");
    private final JTextField nombre;
    private JLabel etiqueta2 = new JLabel("Apellidos: ");
    private final JTextField apellidos;
    private JLabel etiqueta3 = new JLabel("NIF: ");
    private final JTextField nif;
    private JLabel etiqueta4 = new JLabel("Tarjeta: ");
    private final JTextField tarjeta;
    private JButton desb = new JButton("Desbloquear");
    private JButton desbYMod = new JButton("Modificar");
    private JButton volver = new JButton("Cancelar");
    private JPanel select = new JPanel(new GridLayout(1, 3));
    private ButtonGroup grupo = new ButtonGroup();
    private JLabel texto = new JLabel("");
    private Demandante demandante;
    private GuiInmobiliaria gui;

    PanelDesbloquearUsuario(GuiInmobiliaria gui) {
        this.gui = gui;

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        nombre = new JTextField(String.valueOf(demandante.getNombre()), 10);
        apellidos = new JTextField(String.valueOf(demandante.getApellidos()), 10);
        nif = new JTextField(String.valueOf(demandante.getNif()), 10);
        tarjeta = new JTextField(String.valueOf(demandante.getTarjeta()), 10);

        select.add(volver);
        select.add(desb);
        select.add(desbYMod);
        grupo.add(volver);
        grupo.add(desb);
        grupo.add(desbYMod);
        texto.setVisible(false);

        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, etiqueta1, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, nombre, 0, SpringLayout.WEST, apellidos);
        layout.putConstraint(SpringLayout.NORTH, nombre, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta2, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, apellidos, 5, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, apellidos, 5, SpringLayout.SOUTH, nombre);
        layout.putConstraint(SpringLayout.EAST, etiqueta3, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 8, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, nif, 0, SpringLayout.WEST, apellidos);
        layout.putConstraint(SpringLayout.NORTH, nif, 5, SpringLayout.SOUTH, apellidos);
        layout.putConstraint(SpringLayout.EAST, etiqueta4, 0, SpringLayout.EAST, etiqueta3);
        layout.putConstraint(SpringLayout.NORTH, etiqueta4, 8, SpringLayout.SOUTH, etiqueta3);
        layout.putConstraint(SpringLayout.WEST, tarjeta, 0, SpringLayout.WEST, nif);
        layout.putConstraint(SpringLayout.NORTH, tarjeta, 5, SpringLayout.SOUTH, nif);
        layout.putConstraint(SpringLayout.NORTH, texto, 5, SpringLayout.SOUTH, tarjeta);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, tarjeta);
        layout.putConstraint(SpringLayout.NORTH, select, 10, SpringLayout.SOUTH, texto);
        layout.putConstraint(SpringLayout.WEST, select, 0, SpringLayout.WEST, texto);

        this.add(etiqueta1);
        this.add(nombre);
        this.add(etiqueta2);
        this.add(apellidos);
        this.add(etiqueta3);
        this.add(nif);
        this.add(etiqueta4);
        this.add(tarjeta);
        this.add(select);
        this.add(texto);
        this.setVisible(true);

        volver.addActionListener(this);
        desb.addActionListener(this);
        desbYMod.addActionListener(this);
    }

    public void setOferta(Demandante demandante){
        this.demandante = demandante;
    }

    public void actionPerformed(ActionEvent e){
        texto.setVisible(false);
        if(e.getSource()==volver){
            gui.getControlador().volverOfertante();
        }else if(e.getSource()==desb){
            gui.getControlador().desbloquearUsuario(demandante);
        }else if(e.getSource()==desbYMod){
            gui.getControlador().desbloquearUsuario(demandante, tarjeta.getText());
        }
    }

    public void setError(String texto){
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
