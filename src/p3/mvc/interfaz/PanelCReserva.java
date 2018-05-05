package p3.mvc.interfaz;

import p3.mvc.modelo.Demandante;
import p3.mvc.modelo.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCReserva extends JPanel implements ActionListener {
    private GuiInmobiliaria gui;
    private JButton volver = new JButton("Volver");
    private JButton cancelar = new JButton("Cancelar Reserva");
    private JLabel texto = new JLabel("");

    PanelCReserva(GuiInmobiliaria gui){
        this.gui = gui;
        Reserva reserva = ((Demandante) gui.getControlador().getCliente()).getReserva();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        final JTextArea etiqueta2;
        if(reserva ==null){
            etiqueta2 = new JTextArea("No hay ninguna reserva activa");
        } else {
            etiqueta2 = new JTextArea(reserva.toString() + "\n" + reserva.getOferta().toString() + "\n" + reserva.getOferta().getInmueble().toString());
        }
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(cancelar);
        JPanel select = new JPanel(new GridLayout(1, 2));
        select.add(volver);
        select.add(cancelar);
        select.setVisible(true);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("Tu Reserva");
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta2, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, texto, 8, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, select, 8, SpringLayout.SOUTH, texto);
        this.add(etiqueta2);
        this.add(etiqueta1);
        this.add(select);
        this.add(texto);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
        cancelar.addActionListener(this);
    }


    public void actionPerformed(ActionEvent evento) {
        if(evento.getSource()==volver){
            texto.setVisible(false);
            gui.getControlador().volverDemandante();
        } else if(evento.getSource()==cancelar){
            texto.setVisible(false);
            gui.getControlador().cancelarReserva();
        }
    }

    public void setError(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
