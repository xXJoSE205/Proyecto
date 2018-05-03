package p3.mvc.interfaz;

import p3.mvc.modelo.Demandante;
import p3.mvc.modelo.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCReserva extends JPanel implements ActionListener {
    private JLabel etiqueta1 = new JLabel("DEMANDANTE");
    private JTextArea etiqueta2;
    private GuiInmobiliaria gui;
    private JPanel select = new JPanel(new GridLayout(1, 2));
    private ButtonGroup grupo = new ButtonGroup();
    JButton volver = new JButton("Volver");
    JButton cancelar = new JButton("Cancelar Reserva");
    private Reserva reserva;
    private JLabel texto = new JLabel("");

    PanelCReserva(GuiInmobiliaria gui){
        this.gui = gui;
        this.reserva=((Demandante)gui.getControlador().getCliente()).getReserva();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        etiqueta2= new JTextArea(reserva.toString()+ "\n"+ reserva.getOferta().toString() +  "\n" +reserva.getOferta().getInmueble().toString());
        grupo.add(volver);
        grupo.add(cancelar);
        select.add(volver);
        select.add(cancelar);
        select.setVisible(true);

        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta2, 5, SpringLayout.WEST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, select, 5, SpringLayout.WEST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, select, 220, SpringLayout.NORTH, etiqueta2);
        this.add(etiqueta2);
        this.add(etiqueta1);
        this.add(select);
        this.setVisible(true);
        volver.addActionListener(this);
        cancelar.addActionListener(this);
    }


    public void actionPerformed(ActionEvent evento) {
        if(evento.getSource()==volver){
            gui.getControlador().volverDemandante();
        } else if(evento.getSource()==cancelar){
            gui.getControlador().cancelarReserva();
        }
    }

    public void creadaOK(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
