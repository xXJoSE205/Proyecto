package p3.mvc.interfaz;

import p3.mvc.modelo.Oferta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelComprobarOfertas extends JPanel implements ActionListener {
    private JLabel etiqueta1 = new JLabel("Tus ofertas");
    private String[] titulos = {"Precio", "Fecha Inicio", "Fecha Fin", "Vacacional", "Fianza"};
    private Object filas [][] = {};
    private GuiInmobiliaria gui;
    private JPanel select = new JPanel(new GridLayout(1, 3));
    private ButtonGroup grupo = new ButtonGroup();
    private DefaultTableModel modeloDatos = new DefaultTableModel(filas, titulos){
        public boolean isCellEditable(int row, int colum){
            return false;
        }
    };
    private JButton volver = new JButton("Volver");
    private JButton aceptar = new JButton("Aceptar");
    private JButton rechazar = new JButton("Rechazar");
    private JTextArea modificaciones = new JTextArea("Modificaciones propuestas");
    private JLabel texto = new JLabel("");
    private List<Oferta> ofertas;
    private JTable tabla = new JTable(modeloDatos);

    public PanelComprobarOfertas(GuiInmobiliaria gui){
        this.gui = gui;

        this.ofertas = gui.getControlador().getOfertasPendientes();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        JTable tabla = new JTable(modeloDatos);
        JScrollPane scrollPane = new JScrollPane(tabla);
        for(Oferta o:ofertas){
            Object[] nuevaFila = {o.getPrecio(), o.getFechaInicio(), o.getFechaFin(), o.isVacacional(), o.getFianza()};
            modeloDatos.addRow(nuevaFila);
        }

        grupo.add(volver);
        grupo.add(aceptar);
        grupo.add(rechazar);
        select.add(volver);
        select.add(aceptar);
        select.add(rechazar);
        select.setVisible(true);
        texto.setVisible(false);

        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.WEST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, modificaciones, 5, SpringLayout.SOUTH, scrollPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, modificaciones, 0, SpringLayout.HORIZONTAL_CENTER, scrollPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, scrollPane);
        layout.putConstraint(SpringLayout.NORTH, select, 8, SpringLayout.SOUTH, scrollPane);

        this.add(etiqueta1);
        this.add(select);
        this.add(scrollPane);
        this.setVisible(true);
        volver.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==volver){
            gui.getControlador().volverGerente();
        }else if(e.getSource()==aceptar){
            gui.getControlador().aceptarOferta(ofertas.get(tabla.getSelectedRow()));
        }else if(e.getSource()==rechazar){
            gui.getControlador().rechazarOferta(ofertas.get(tabla.getSelectedRow()), modificaciones.getText());
        }
    }
}
