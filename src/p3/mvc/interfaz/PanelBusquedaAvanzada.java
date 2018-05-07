package p3.mvc.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase contiene la informacion del panel Busqueda avanzada
 * Crea un panel en el que se muestran los campos de busqueda y unos botones para volver y buscar
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
class PanelBusquedaAvanzada extends JPanel implements ActionListener {
    private final JTextField numero = new JTextField("-1", 2);
    private final JTextField numeroBan = new JTextField("-1", 2);
    private final JTextField dim = new JTextField("-1", 2);
    private final JTextField planta = new JTextField("-1", 2);
    private final JTextField direccion = new JTextField("", 30);
    private final JTextField precio = new JTextField("-1",4);
    private final JCheckBox casilla2 = new JCheckBox("Vacacional");
    private final JCheckBox casilla1 = new JCheckBox("Ascensor");
    private final JButton buscar = new JButton("Buscar");
    private final JButton volver = new JButton("Volver");
    private final GuiInmobiliaria gui;
    private final JLabel texto = new JLabel("");

    /**
     * Constructor de PanelBusquedaAvanzada
     * @param gui GUI de la inmobiliaria
     */
    PanelBusquedaAvanzada(GuiInmobiliaria gui) {
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(buscar);
        grupo.add(volver);
        JPanel select = new JPanel(new GridLayout(1, 2));
        select.add(buscar);
        select.add(volver);
        select.setVisible(true);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("Numero de habitaciones:");
        layout.putConstraint(SpringLayout.EAST, etiqueta1, -100, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, numero, 5, SpringLayout.EAST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, numero, 5, SpringLayout.NORTH, this);
        JLabel etiqueta2 = new JLabel("Numero de banos:");
        layout.putConstraint(SpringLayout.EAST, etiqueta2, 0, SpringLayout.EAST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, numeroBan, 0, SpringLayout.WEST, numero);
        layout.putConstraint(SpringLayout.NORTH, numeroBan, 5, SpringLayout.SOUTH, numero);
        JLabel etiqueta3 = new JLabel("Dimensiones:");
        layout.putConstraint(SpringLayout.EAST, etiqueta3, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 8, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, dim, 0, SpringLayout.WEST, numeroBan);
        layout.putConstraint(SpringLayout.NORTH, dim, 5, SpringLayout.SOUTH, numeroBan);
        JLabel etiqueta4 = new JLabel("Planta:");
        layout.putConstraint(SpringLayout.EAST, etiqueta4, 0, SpringLayout.EAST, etiqueta3);
        layout.putConstraint(SpringLayout.NORTH, etiqueta4, 8, SpringLayout.SOUTH, etiqueta3);
        layout.putConstraint(SpringLayout.WEST, planta, 0, SpringLayout.WEST, dim);
        layout.putConstraint(SpringLayout.NORTH, planta, 5, SpringLayout.SOUTH, dim);
        JLabel etiqueta5 = new JLabel("Direccion:");
        layout.putConstraint(SpringLayout.EAST, etiqueta5, 0, SpringLayout.EAST, etiqueta4);
        layout.putConstraint(SpringLayout.NORTH, etiqueta5, 8, SpringLayout.SOUTH, etiqueta4);
        layout.putConstraint(SpringLayout.WEST, direccion, 0, SpringLayout.WEST, planta);
        layout.putConstraint(SpringLayout.NORTH, direccion, 5, SpringLayout.SOUTH, planta);
        JLabel etiqueta6 = new JLabel("Precio");
        layout.putConstraint(SpringLayout.EAST, etiqueta6, 0, SpringLayout.EAST, etiqueta5);
        layout.putConstraint(SpringLayout.NORTH, etiqueta6, 8, SpringLayout.SOUTH, etiqueta5);
        layout.putConstraint(SpringLayout.WEST, precio, 0, SpringLayout.WEST, direccion);
        layout.putConstraint(SpringLayout.NORTH, precio, 5, SpringLayout.SOUTH, direccion);
        layout.putConstraint(SpringLayout.EAST, casilla1, 0, SpringLayout.EAST, etiqueta6);
        layout.putConstraint(SpringLayout.NORTH, casilla1, 8, SpringLayout.SOUTH, etiqueta6);
        layout.putConstraint(SpringLayout.WEST, casilla2, 0, SpringLayout.WEST, casilla1);
        layout.putConstraint(SpringLayout.NORTH, casilla2, 8, SpringLayout.SOUTH, casilla1);
        layout.putConstraint(SpringLayout.NORTH, texto, 8, SpringLayout.SOUTH, casilla2);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, select, 10, SpringLayout.SOUTH, texto);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, this);

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
        this.add(casilla2);
        this.add(etiqueta6);
        this.add(precio);
        this.add(texto);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        buscar.addActionListener(this);
        volver.addActionListener(this);
    }

    /**
     * Manejador de las acciones de los botones
     * @param evento Accion que se activa
     */
    public void actionPerformed(ActionEvent evento) {
        boolean ascensor;
        boolean vacacional;
        if(evento.getSource()==buscar) {
            texto.setVisible(false);
            ascensor = casilla1.isSelected();
            vacacional = casilla2.isSelected();
            gui.getControlador().avanzada(Integer.parseInt(numero.getText()), Integer.parseInt(numeroBan.getText()),
                    Integer.parseInt(dim.getText()), Integer.parseInt(planta.getText()), ascensor, direccion.getText(),
                    Integer.parseInt(precio.getText()),vacacional);
        } else if(evento.getSource()==volver){
            texto.setVisible(false);
            gui.getControlador().volverDemandante();
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
