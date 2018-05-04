package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelDemandante extends JPanel implements ActionListener {
    private JLabel etiqueta1 = new JLabel("DEMANDANTE");
    private JLabel etiqueta2;
    private JLabel texto = new JLabel("Error al cerrar sesion");
    private GuiInmobiliaria gui;
    private JPanel select = new JPanel(new GridLayout(4, 1));
    private ButtonGroup grupo = new ButtonGroup();

    JButton volver = new JButton("Desconectarse");
    JButton buscar = new JButton("Busqueda");
    JButton avanzada = new JButton("Busqueda Avanzada");
    JButton reservas = new JButton("Comprobar Reserva");
    private JLabel texto2 = new JLabel("");

    PanelDemandante(GuiInmobiliaria gui){
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        etiqueta2=new JLabel("Bienvenido " + gui.getControlador().getCliente().getNombre());
        grupo.add(volver);
        grupo.add(buscar);
        grupo.add(avanzada);
        grupo.add(reservas);
        select.add(volver);
        select.add(buscar);
        select.add(avanzada);
        select.add(reservas);
        select.setVisible(true);
        texto.setVisible(false);

        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta2, 5, SpringLayout.WEST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, select, 5, SpringLayout.WEST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, select, 20, SpringLayout.NORTH, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, texto, 8, SpringLayout.SOUTH, select);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, select);
        this.add(etiqueta1);
        this.add(select);
        this.add(etiqueta2);
        this.add(texto);
        this.setVisible(true);

        volver.addActionListener(this);
        buscar.addActionListener(this);
        avanzada.addActionListener(this);
        reservas.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {
        texto.setVisible(false);
        if( evento.getSource()==volver){
            gui.getControlador().logout();
        } else if(evento.getSource()==buscar){
            gui.getControlador().goBusqueda();
        } else if(evento.getSource()==avanzada){
            gui.getControlador().goAvanzada();
        } else if(evento.getSource()==reservas){
            gui.getControlador().goComprobarReserva();
        }
    }

    public void setError(String error) {
        texto.setVisible(true);
        texto.setText(error);
        texto.setForeground(java.awt.Color.red);
    }

    public void creadaOK(String texto) {
        this.texto2.setText(texto);
        this.texto2.setVisible(true);
        this.texto2.setForeground(java.awt.Color.red);
    }
}
