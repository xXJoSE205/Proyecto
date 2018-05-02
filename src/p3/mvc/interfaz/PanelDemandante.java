package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelDemandante extends JPanel implements ActionListener {
    private JLabel etiqueta1 = new JLabel("DEMANDANTE");
    private JLabel etiqueta2;
    private GuiInmobiliaria gui;
    private JPanel select = new JPanel(new GridLayout(4, 1));
    private ButtonGroup grupo = new ButtonGroup();

    JButton volver = new JButton("Desconectarse");
    JButton buscar = new JButton("Busqueda");
    JButton avanzada = new JButton("Busqueda Avanzada");
    JButton reservas = new JButton("Comprobar Reserva");

    PanelDemandante(GuiInmobiliaria gui, String nombre){
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        etiqueta2=new JLabel("Bienvenido "+nombre);
        grupo.add(volver);
        grupo.add(buscar);
        grupo.add(avanzada);
        grupo.add(reservas);
        select.add(volver);
        select.add(buscar);
        select.add(avanzada);
        select.add(reservas);
        select.setVisible(true);

        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta2, 5, SpringLayout.WEST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, select, 5, SpringLayout.WEST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, select, 20, SpringLayout.NORTH, etiqueta2);
        this.add(etiqueta1);
        this.add(select);
        this.add(etiqueta2);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent evento) {
        if( evento.getSource()==volver){
            gui.getControlador().logout();
        } else if(evento.getSource()==buscar){
            gui.getControlador().goBuscar();
        } else if(evento.getSource()==avanzada){
            //gui.getControlador().goAvanzada();
        } else if(evento.getSource()==reservas){
            //gui.getControlador().goComprobarReserva();
        }
    }
}
