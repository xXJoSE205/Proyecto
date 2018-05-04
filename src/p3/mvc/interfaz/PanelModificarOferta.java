package p3.mvc.interfaz;

import p3.mvc.modelo.Oferta;
import p3.mvc.modelo.Ofertante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class PanelModificarOferta extends JPanel implements ActionListener {
    private final JTextField precio;
    private final JTextField fIni;
    private final JTextField fFin;
    private final JTextField fianza;
    private JCheckBox casilla = new JCheckBox("Vacacional");
    private JButton modificar = new JButton("Modificar oferta");
    private JButton volver = new JButton("Cancelar");
    private JLabel texto = new JLabel("");
    private Oferta oferta;
    private GuiInmobiliaria gui;

    PanelModificarOferta(GuiInmobiliaria gui) {
        this.gui = gui;

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        JTextArea modificaciones = new JTextArea();
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
        layout.putConstraint(SpringLayout.NORTH, texto, 5, SpringLayout.SOUTH, scrollBar);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, scrollBar);
        layout.putConstraint(SpringLayout.NORTH, select, 10, SpringLayout.SOUTH, scrollBar);
        layout.putConstraint(SpringLayout.WEST, select, 0, SpringLayout.WEST, scrollBar);

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

        volver.addActionListener(this);
        modificar.addActionListener(this);
    }

    public void setOferta(Oferta oferta){
        this.oferta = oferta;
    }

    public void actionPerformed(ActionEvent e){
        texto.setVisible(false);
        if(e.getSource()==volver){
            gui.getControlador().volverOfertante();
        }else if(e.getSource()==modificar){
            gui.getControlador().modificarOferta(Double.parseDouble(precio.getText()), LocalDate.parse(fIni.getText())
                    , LocalDate.parse(fFin.getText()), casilla.isSelected(), Double.parseDouble(fianza.getText())
                    , oferta);
        }
    }

    public void setError(String texto){
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}
