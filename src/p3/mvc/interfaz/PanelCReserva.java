package p3.mvc.interfaz;

import p3.mvc.modelo.Demandante;
import p3.mvc.modelo.Reserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase contiene la informacion del panel Desbloquear usuario
 * Crea un panel en el que se muestran la reserva activa y la oferta correspondiente, y unos botones para volver,
 * cancelar reserva y alquilar
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
class PanelCReserva extends JPanel implements ActionListener {
    private final GuiInmobiliaria gui;
    private final JButton volver = new JButton("Volver");
    private final JButton cancelar = new JButton("Cancelar Reserva");
    private  final JButton alquilar = new JButton("Alquilar");
    private final JLabel texto = new JLabel("");

    /**
     * Constructor de PanelCReserva
     * @param gui GUI de la inmobiliaria
     */
    PanelCReserva(GuiInmobiliaria gui){
        this.gui = gui;
        Reserva reserva = ((Demandante) gui.getControlador().getCliente()).getReserva();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        JTextArea etiqueta2;
        if(reserva ==null){
            etiqueta2 = new JTextArea("No hay ninguna reserva activa");
        } else {
            etiqueta2 = new JTextArea(reserva.toString() + "\n" + reserva.getOferta().toString() + "\n" + reserva.getOferta().getInmueble().toString());
        }
        etiqueta2.setEditable(false);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(cancelar);
        grupo.add(alquilar);
        JPanel select = new JPanel(new GridLayout(1, 3));
        select.add(volver);
        select.add(cancelar);
        select.add(alquilar);
        select.setVisible(true);

        JLabel etiqueta1 = new JLabel("RESERVA");
        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta2, 5, SpringLayout.WEST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, select, 5, SpringLayout.WEST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, select, 220, SpringLayout.NORTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, texto, 5, SpringLayout.WEST, select);
        layout.putConstraint(SpringLayout.NORTH, texto, 220, SpringLayout.NORTH, select);

        this.add(etiqueta2);
        this.add(etiqueta1);
        this.add(select);
        this.setVisible(true);
        this.add(texto);
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
        cancelar.addActionListener(this);
        alquilar.addActionListener(this);
    }

    /**
     * Manejador de las acciones de los botones
     * @param evento Accion que se activa
     */
    public void actionPerformed(ActionEvent evento) {
        if(evento.getSource()==volver){
            texto.setVisible(false);
            gui.getControlador().volverDemandante();
        } else if(evento.getSource()==cancelar){
            texto.setVisible(false);
            gui.getControlador().cancelarReserva();
        } else if(evento.getSource()==alquilar){
            texto.setVisible(false);
            try{
                gui.getControlador().goAlquilarR(((Demandante)gui.getControlador().getCliente()).getReserva().getOferta());
            } catch (Exception e){
                texto.setText("No tienes ningna reserva");
            }
        }
    }

    /**
     * Establece el mensaje que se va a mostrar por pantalla
     * @param texto Cadena con el mensaje a mostrar
     */
    public void setError(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
