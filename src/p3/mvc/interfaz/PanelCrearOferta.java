package p3.mvc.interfaz;

import p3.mvc.modelo.Inmueble;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * Esta clase contiene la informacion del panel Crear oferta
 * Crea un panel en el que se muestran los campos de la oferta y unos botones para volver(cancelar) y crear oferta
 *
 * @author Jorge Mateo Segura y Jose Antonio Munoz Ortega
 */
class PanelCrearOferta extends JPanel implements ActionListener {
    private final JTextField precio = new JTextField(10);
    private final JTextField fIni = new JTextField(10);
    private final JTextField fFin = new JTextField(10);
    private final JTextField fianza = new JTextField(10);
    private final JCheckBox casilla = new JCheckBox("Vacacional");
    private final JButton crear = new JButton("Crear oferta");
    private final JButton volver = new JButton("Cancelar");
    private final JLabel texto = new JLabel("");
    private Inmueble inmueble;
    private final GuiInmobiliaria gui;

    /**
     * Constructor de PanelCrearOferta
     * @param gui GUI de la inmobiliaria
     */
    PanelCrearOferta(GuiInmobiliaria gui) {
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        JPanel select = new JPanel(new GridLayout(1, 3));
        select.add(crear);
        select.add(volver);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(crear);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("Precio: ");
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        JLabel etiqueta2 = new JLabel("Fecha inicio: ");
        layout.putConstraint(SpringLayout.EAST, etiqueta1, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, precio, 0, SpringLayout.WEST, fIni);
        layout.putConstraint(SpringLayout.NORTH, precio, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta2, -100, SpringLayout.HORIZONTAL_CENTER, this);
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
        layout.putConstraint(SpringLayout.NORTH, select, 10, SpringLayout.SOUTH, texto);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, texto, 8, SpringLayout.SOUTH, casilla);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, this);

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
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
        crear.addActionListener(this);
    }

    /**
     * Establece el inmueble al que se quiere anadir una oferta
     * @param inmueble Inmueble al que anadir oferta
     */
    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    /**
     * Manejador de las acciones de los botones
     * @param e Accion que se activa
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == volver) {
            texto.setVisible(false);
            gui.getControlador().volverVerInmuebles();
        } else if (e.getSource() == crear) {
            texto.setVisible(false);
            try {
                gui.getControlador().crearOferta(Double.parseDouble(precio.getText()), LocalDate.parse(fIni.getText())
                        , LocalDate.parse(fFin.getText()), casilla.isSelected(), Double.parseDouble(fianza.getText())
                        , inmueble);

            }catch (Exception ex){
                this.gui.creadoOK("Error al crear la oferta, revisa los campos");
            }
        }
    }

    /**
     * Establece el mensaje que se va a mostrar por pantalla
     * @param texto Cadena con el mensaje a mostrar
     */
    public void creadaOK(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
