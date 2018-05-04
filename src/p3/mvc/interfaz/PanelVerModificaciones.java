package p3.mvc.interfaz;

import p3.mvc.modelo.Oferta;
import p3.mvc.modelo.Ofertante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelVerModificaciones extends JPanel implements ActionListener {
    private JLabel etiqueta1 = new JLabel("Modificaciones propuestas");
    private String[] titulos = {"Precio", "Fecha Inicio", "Fecha Fin", "Vacacional", "Fianza" };
    private Object filas [][] = {};
    private GuiInmobiliaria gui;
    private JPanel select = new JPanel(new GridLayout(1, 2));
    private ButtonGroup grupo = new ButtonGroup();
    private DefaultTableModel modeloDatos = new DefaultTableModel(filas, titulos){
        public boolean isCellEditable(int row, int colum){
            return false;
        }
    };
    private JButton volver = new JButton("Volver");
    private JButton modificar = new JButton("Modificar");
    private List<Oferta> lista;
    private JTable tabla = new JTable(modeloDatos);
    private JLabel texto = new JLabel("");
    private JTextArea modificaciones = new JTextArea();

    public PanelVerModificaciones(GuiInmobiliaria gui){
        this.gui = gui;

        this.lista = gui.getControlador().getOfertasRechazadas();
        JTextArea modificaciones = new JTextArea();
        modificaciones.setText(((Ofertante)gui.getControlador().getCliente()).getModificaciones());
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        JTable tabla = new JTable(modeloDatos);
        JScrollPane scrollPane = new JScrollPane(tabla);
        for(Oferta o:lista){
            Object[] nuevaFila = {o.getPrecio(), o.getFechaInicio(), o.getFechaFin(), o.isVacacional(), o.getFianza()};
            modeloDatos.addRow(nuevaFila);
        }

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(modificar);
        JPanel select = new JPanel(new GridLayout(1, 2));
        select.add(volver);
        select.add(modificar);
        select.setVisible(true);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("Tus Inmuebles");
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollPane, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, modificaciones, 5, SpringLayout.SOUTH, scrollPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, modificaciones, 0, SpringLayout.HORIZONTAL_CENTER, scrollPane);
        layout.putConstraint(SpringLayout.NORTH, texto, 5, SpringLayout.SOUTH, modificaciones);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, modificaciones);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, texto);
        layout.putConstraint(SpringLayout.NORTH, select, 5, SpringLayout.SOUTH, texto);

        this.add(etiqueta1);
        this.add(select);
        this.add(tabla);
        this.add(texto);
        this.add(modificaciones);
        this.add(scrollPane);
        this.setVisible(true);

        volver.addActionListener(this);
        modificar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        texto.setVisible(false);
        if(e.getSource()==volver){
            gui.getControlador().volverOfertante();
        }else if(e.getSource()==modificar){
            gui.getControlador().goModificarOferta(lista.get(tabla.getSelectedRow()));
        }
    }
}
