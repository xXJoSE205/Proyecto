package p3.mvc.interfaz;

import p3.mvc.modelo.Inmueble;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class PanelCrearOferta extends JPanel implements ActionListener {
    private JLabel etiqueta1 = new JLabel("Precio: ");
    private final JTextField precio = new JTextField(10);
    private JLabel etiqueta2 = new JLabel("Fecha inicio: ");
    private final JTextField fIni = new JTextField(10);
    private JLabel etiqueta3 = new JLabel("Fecha final: ");
    private final JTextField fFin = new JTextField(10);
    private JLabel etiqueta4 = new JLabel("Fianza: ");
    private final JTextField fianza = new JTextField(10);
    private JCheckBox casilla = new JCheckBox("Vacacional");
    private JButton crear = new JButton("Crear oferta");
    private JButton volver = new JButton("Cancelar");
    private JPanel select = new JPanel(new GridLayout(1, 3));
    private ButtonGroup grupo = new ButtonGroup();
    private JLabel texto = new JLabel("");
    private Inmueble inmueble;
    private GuiInmobiliaria gui;
    private JLabel texto2 = new JLabel("");

    PanelCrearOferta(GuiInmobiliaria gui) {
        this.gui = gui;
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        select.add(crear);
        select.add(volver);
        grupo.add(volver);
        grupo.add(crear);
        texto.setVisible(false);

        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, etiqueta1, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, precio, 0, SpringLayout.WEST, fIni);
        layout.putConstraint(SpringLayout.NORTH, precio, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, etiqueta2, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta2, 8, SpringLayout.SOUTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, fIni, 5, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, fIni, 5, SpringLayout.SOUTH, precio);
        layout.putConstraint(SpringLayout.EAST, etiqueta3, 0, SpringLayout.EAST, etiqueta2);
        layout.putConstraint(SpringLayout.NORTH, etiqueta3, 8, SpringLayout.SOUTH, etiqueta2);
        layout.putConstraint(SpringLayout.WEST, fFin, 0, SpringLayout.WEST, fIni);
        layout.putConstraint(SpringLayout.NORTH, fFin, 5, SpringLayout.SOUTH, fIni);
        layout.putConstraint(SpringLayout.EAST, etiqueta4, 0, SpringLayout.EAST, etiqueta3);
        layout.putConstraint(SpringLayout.NORTH, etiqueta4, 8, SpringLayout.SOUTH, etiqueta3);
        layout.putConstraint(SpringLayout.WEST, fianza, 0, SpringLayout.WEST, fFin);
        layout.putConstraint(SpringLayout.NORTH, fianza, 5, SpringLayout.SOUTH, fFin);
        layout.putConstraint(SpringLayout.WEST, casilla, 0, SpringLayout.WEST, fianza);
        layout.putConstraint(SpringLayout.NORTH, casilla, 8, SpringLayout.SOUTH, etiqueta4);
        layout.putConstraint(SpringLayout.NORTH, select, 10, SpringLayout.SOUTH, casilla);
        layout.putConstraint(SpringLayout.WEST, select, 0, SpringLayout.WEST, casilla);

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

        volver.addActionListener(this);
        crear.addActionListener(this);
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public void actionPerformed(ActionEvent e) {
        texto.setVisible(false);
        if (e.getSource() == volver) {
            gui.getControlador().volverVerInmuebles();
        } else if (e.getSource() == crear) {
            gui.getControlador().crearOferta(Double.parseDouble(precio.getText()), LocalDate.parse(fIni.getText())
                    , LocalDate.parse(fFin.getText()), casilla.isSelected(), Double.parseDouble(fianza.getText())
                    , inmueble);
        }
    }

    public void creadaOK(String texto) {
        this.texto2.setText(texto);
        this.texto2.setVisible(true);
        this.texto2.setForeground(java.awt.Color.red);
    }
}
