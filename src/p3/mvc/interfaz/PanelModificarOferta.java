package p3.mvc.interfaz;

import p3.mvc.modelo.Oferta;
import p3.mvc.modelo.Ofertante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * Esta clase contiene la informacion del panel Modificar oferta
 * Crea un panel en el que se muestran los campos de la oferta a modificar y unos botones para volver(cancelar)
 * y modificar oferta
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
class PanelModificarOferta extends JPanel implements ActionListener {
    private final JTextField precio;
    private final JTextField fIni;
    private final JTextField fFin;
    private final JTextField fianza;
    private final JCheckBox casilla = new JCheckBox("Vacacional");
    private final JButton modificar = new JButton("Modificar oferta");
    private final JButton volver = new JButton("Cancelar");
    private final JLabel texto = new JLabel("");
    private Oferta oferta;
    private final GuiInmobiliaria gui;

    /**
     * Constructor de PanelModificarOferta
     * @param gui GUI de la inmobiliaria
     */
    PanelModificarOferta(GuiInmobiliaria gui) {
        this.gui = gui;
        this.oferta=gui.getControlador().getOferta();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JTextArea modificaciones = new JTextArea(20,100);
        modificaciones.setText(((Ofertante)gui.getControlador().getCliente()).getModificaciones());
        precio = new JTextField(String.valueOf(oferta.getPrecio()), 10);
        fIni = new JTextField(String.valueOf(oferta.getFechaInicio()), 10);
        fFin = new JTextField(String.valueOf(oferta.getFechaFin()), 10);
        fianza = new JTextField(String.valueOf(oferta.getFianza()), 10);
        casilla.setSelected(oferta.isVacacional());

        JPanel select = new JPanel(new GridLayout(1, 2));
        select.add(volver);
        select.add(modificar);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(modificar);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("Precio: ");
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        JLabel etiqueta2 = new JLabel("Fecha inicio: ");
        layout.putConstraint(SpringLayout.EAST, etiqueta1, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, precio, 0, SpringLayout.WEST, fIni);
        layout.putConstraint(SpringLayout.NORTH, precio, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta2, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, fIni, 5, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, fIni, 5, SpringLayout.SOUTH, precio);
        JLabel etiqueta3 = new JLabel("Fecha final: ");
        layout.putConstraint(SpringLayout.EAST, etiqueta3, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 8, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, fFin, 0, SpringLayout.WEST, fIni);
        layout.putConstraint(SpringLayout.NORTH, fFin, 5, SpringLayout.SOUTH, fIni);
        JLabel etiqueta4 = new JLabel("Fianza: ");
        layout.putConstraint(SpringLayout.EAST, etiqueta4, 0, SpringLayout.EAST, etiqueta3);
        layout.putConstraint(SpringLayout.NORTH, etiqueta4, 8, SpringLayout.SOUTH, etiqueta3);
        layout.putConstraint(SpringLayout.WEST, fianza, 0, SpringLayout.WEST, fFin);
        layout.putConstraint(SpringLayout.NORTH, fianza, 5, SpringLayout.SOUTH, fFin);
        layout.putConstraint(SpringLayout.WEST, casilla, 0, SpringLayout.WEST, fianza);
        layout.putConstraint(SpringLayout.NORTH, casilla, 8, SpringLayout.SOUTH, etiqueta4);
        JScrollPane scrollBar = new JScrollPane(modificaciones);
        layout.putConstraint(SpringLayout.NORTH, scrollBar, 5, SpringLayout.SOUTH, casilla);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollBar, 0, SpringLayout.HORIZONTAL_CENTER, casilla);
        layout.putConstraint(SpringLayout.NORTH, texto, 50, SpringLayout.SOUTH, scrollBar);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, scrollBar);
        layout.putConstraint(SpringLayout.NORTH, select, 10, SpringLayout.SOUTH, scrollBar);
        layout.putConstraint(SpringLayout.WEST, select, 10, SpringLayout.WEST, scrollBar);

        this.add(etiqueta1);
        this.add(precio);
        this.add(etiqueta2);
        this.add(fIni);
        this.add(etiqueta3);
        this.add(fFin);
        this.add(etiqueta4);
        this.add(fianza);
        this.add(casilla);
        this.add(select);
        this.add(texto);
        this.add(scrollBar);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
        modificar.addActionListener(this);
    }

    /**
     * Establece la oferta la cual se va a modificar
     * @param oferta Oferta a modificar
     */
    public void setOferta(Oferta oferta){
        this.oferta = oferta;
    }

    /**
     * Manejador de las acciones de los botones
     * @param e Accion que se activa
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==volver){
            texto.setVisible(false);
            gui.getControlador().volverOfertante();
        }else if(e.getSource()==modificar){
            texto.setVisible(false);
            gui.getControlador().modificarOferta(Double.parseDouble(precio.getText()), LocalDate.parse(fIni.getText())
                    , LocalDate.parse(fFin.getText()), casilla.isSelected(), Double.parseDouble(fianza.getText())
                    , oferta);
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
