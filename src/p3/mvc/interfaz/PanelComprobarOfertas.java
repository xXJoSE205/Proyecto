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
        JScrollPane scrollPane = new JScrollPane(tabla);
        for(Oferta o:ofertas){
            Object[] nuevaFila = {o.getPrecio(), o.getFechaInicio(), o.getFechaFin(), o.isVacacional(), o.getFianza()};
            modeloDatos.addRow(nuevaFila);
        }
        tabla.setPreferredScrollableViewportSize(new Dimension(600, 350));

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(aceptar);
        grupo.add(rechazar);
        JPanel select = new JPanel(new GridLayout(1, 3));
        select.add(volver);
        select.add(aceptar);
        select.add(rechazar);
        select.setVisible(true);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("Ofertas pendientes");
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollPane, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, modificaciones, 50, SpringLayout.SOUTH, scrollPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, modificaciones, 0, SpringLayout.HORIZONTAL_CENTER, scrollPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, scrollPane);
        layout.putConstraint(SpringLayout.NORTH, select, 8, SpringLayout.SOUTH, scrollPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, select);
        layout.putConstraint(SpringLayout.NORTH, texto, 20, SpringLayout.NORTH, select);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setColumnSelectionAllowed(false);
        tabla.getSelectionModel().getLeadSelectionIndex();
        tabla.setRowSelectionAllowed(true);

        this.add(etiqueta1);
        this.add(select);
        this.add(modificaciones);
        this.add(scrollPane);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
        aceptar.addActionListener(this);
        rechazar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==volver){
            texto.setVisible(false);
            gui.getControlador().volverGerente();
        }else if(e.getSource()==aceptar){
            texto.setVisible(false);
            try {
                gui.getControlador().aceptarOferta(ofertas.get(tabla.getSelectedRow()));
                modeloDatos.removeRow(tabla.getSelectedRow());
                texto.setText("Oferta aceptada");
            }catch (Exception e1){
                texto.setText("Selecciona oferta para aceptarla");
                texto.setVisible(true);
                texto.setForeground(Color.red);
            }
        }else if(e.getSource()==rechazar){
            texto.setVisible(false);
            try {
                int x=tabla.getSelectedRow();
                gui.getControlador().rechazarOferta(ofertas.get(tabla.getSelectedRow()), modificaciones.getText());
                modeloDatos.removeRow(tabla.getSelectedRow());
                texto.setText("Oferta rechazada");
            }catch (Exception e2){
                texto.setText("Selecciona oferta para rechazarla");
                texto.setVisible(true);
                texto.setForeground(Color.red);
            }
        }
    }

    public void setError(String texto){
        this.texto.setVisible(true);
        this.texto.setText(texto);
        this.texto.setForeground(java.awt.Color.red);
    }
}
