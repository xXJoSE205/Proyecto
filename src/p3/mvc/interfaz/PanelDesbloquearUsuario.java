package p3.mvc.interfaz;

import p3.mvc.modelo.Demandante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase contiene la informacion del panel Desbloquear usuario
 * Crea un panel en el que se muestran los datos del demandante y unos botones para volver(cancelar), modificar
 * y desbloquear
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
class PanelDesbloquearUsuario extends JPanel implements ActionListener {
    private final JTextField tarjeta;
    private final JButton desb = new JButton("Desbloquear");
    private final JButton desbYMod = new JButton("Modificar");
    private final JButton volver = new JButton("Cancelar");
    private final JLabel texto = new JLabel("");
    private Demandante demandante;
    private final GuiInmobiliaria gui;

    /**
     * Constructor de PanelDesbloquearUsuario
     * @param gui GUI de la inmobiliaria
     */
    PanelDesbloquearUsuario(GuiInmobiliaria gui) {
        this.gui = gui;
        this.demandante=this.gui.getControlador().getDemandante();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JTextField nombre = new JTextField(String.valueOf(demandante.getNombre()), 10);
        JTextField apellidos = new JTextField(String.valueOf(demandante.getApellidos()), 10);
        JTextField nif = new JTextField(String.valueOf(demandante.getNif()), 10);
        tarjeta = new JTextField(String.valueOf(demandante.getTarjeta()), 12);

        JPanel select = new JPanel(new GridLayout(1, 3));
        select.add(volver);
        select.add(desb);
        select.add(desbYMod);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(desb);
        grupo.add(desbYMod);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("Nombre: ");
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        JLabel etiqueta2 = new JLabel("Apellidos: ");
        layout.putConstraint(SpringLayout.EAST, etiqueta1, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, nombre, 0, SpringLayout.WEST, apellidos);
        layout.putConstraint(SpringLayout.NORTH, nombre, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta2, -80, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, apellidos, 5, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, apellidos, 5, SpringLayout.SOUTH, nombre);
        JLabel etiqueta3 = new JLabel("NIF: ");
        layout.putConstraint(SpringLayout.EAST, etiqueta3, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 8, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, nif, 0, SpringLayout.WEST, apellidos);
        layout.putConstraint(SpringLayout.NORTH, nif, 5, SpringLayout.SOUTH, apellidos);
        JLabel etiqueta4 = new JLabel("Tarjeta: ");
        layout.putConstraint(SpringLayout.EAST, etiqueta4, 0, SpringLayout.EAST, etiqueta3);
        layout.putConstraint(SpringLayout.NORTH, etiqueta4, 8, SpringLayout.SOUTH, etiqueta3);
        layout.putConstraint(SpringLayout.WEST, tarjeta, 0, SpringLayout.WEST, nif);
        layout.putConstraint(SpringLayout.NORTH, tarjeta, 5, SpringLayout.SOUTH, nif);
        layout.putConstraint(SpringLayout.NORTH, texto, 5, SpringLayout.SOUTH, tarjeta);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, select, 10, SpringLayout.SOUTH, texto);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, this);

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
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
        desb.addActionListener(this);
        desbYMod.addActionListener(this);
    }

    /**
     * Establece el demandante que se quiere desbloquear
     * @param demandante Demandante a desbloquear
     */
    public void setDemandante(Demandante demandante){
        this.demandante = demandante;
    }

    /**
     * Manejador de las acciones de los botones
     * @param e Accion que se activa
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==volver){
            texto.setVisible(false);
            gui.getControlador().volverUsuariosBloqueados();
        }else if(e.getSource()==desb){
            texto.setVisible(false);
            gui.getControlador().desbloquearUsuario(demandante);
        }else if(e.getSource()==desbYMod){
            texto.setVisible(false);
            gui.getControlador().desbloquearUsuario(demandante, tarjeta.getText());
        }
    }

    /**
     * Establece el mensaje que se va a mostrar por pantalla
     * @param texto Cadena con el mensaje a mostrar
     */
    public void setError(String texto){
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
