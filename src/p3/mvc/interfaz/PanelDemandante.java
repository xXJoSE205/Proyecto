package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase contiene la informacion del panel Demandante
 * Crea un panel en el que se muestran las distintas acciones que puede realizar el demandante, unos botones para
 * cerrar sesion(desconectarse), buscar, buscar avanzado, y comprobar reservas
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
class PanelDemandante extends JPanel implements ActionListener {
    private final JLabel texto = new JLabel("Error al cerrar sesion");
    private final GuiInmobiliaria gui;
    private final JButton volver = new JButton("Desconectarse");
    private final JButton buscar = new JButton("Busqueda");
    private final JButton avanzada = new JButton("Busqueda Avanzada");
    private final JButton reservas = new JButton("Comprobar Reserva");

    /**
     * Constructor de PanelDemandante
     * @param gui GUI de la inmobiliaria
     */
    PanelDemandante(GuiInmobiliaria gui){
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        JLabel etiqueta2 = new JLabel("Bienvenido " + gui.getControlador().getCliente().getNombre());
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(buscar);
        grupo.add(avanzada);
        grupo.add(reservas);
        JPanel select = new JPanel(new GridLayout(4, 1));
        select.add(volver);
        select.add(buscar);
        select.add(avanzada);
        select.add(reservas);
        select.setVisible(true);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("DEMANDANTE");
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta2, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, select, 8, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, texto, 8, SpringLayout.SOUTH, select);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, select);
        this.add(etiqueta1);
        this.add(select);
        this.add(etiqueta2);
        this.add(texto);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
        buscar.addActionListener(this);
        avanzada.addActionListener(this);
        reservas.addActionListener(this);
    }

    /**
     * Manejador de las acciones de los botones
     * @param evento Accion que se activa
     */
    public void actionPerformed(ActionEvent evento) {
        if( evento.getSource()==volver){
            texto.setVisible(false);
            gui.getControlador().logout();
        } else if(evento.getSource()==buscar){
            texto.setVisible(false);
            gui.getControlador().goBusqueda();
        } else if(evento.getSource()==avanzada){
            texto.setVisible(false);
            gui.getControlador().goAvanzada();
        } else if(evento.getSource()==reservas){
            texto.setVisible(false);
            gui.getControlador().goComprobarReserva();
        }
    }

    /**
     * Establece el mensaje que se va a mostrar por pantalla
     * @param error Cadena con el mensaje a mostrar
     */
    public void setError(String error) {
        texto.setVisible(true);
        texto.setText(error);
        texto.setForeground(java.awt.Color.red);
    }
}
