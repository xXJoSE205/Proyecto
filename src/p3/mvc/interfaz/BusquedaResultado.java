package p3.mvc.interfaz;

import p3.mvc.modelo.Inmueble;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class BusquedaResultado extends JPanel implements ActionListener {
    private final GuiInmobiliaria gui;
    private final JButton volver = new JButton("Volver");
    private final JLabel texto = new JLabel("");

    BusquedaResultado(GuiInmobiliaria gui){
        this.gui = gui;

        List<Inmueble> lista = gui.getControlador().getBusqueda();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        Object[][] filas = {};
        String[] titulos = {"Habitaciones", "Banos", "Dimensiones", "Ascensor", "Planta", "Direccion"};
        DefaultTableModel modeloDatos = new DefaultTableModel(filas, titulos) {
            public boolean isCellEditable(int row, int colum) {
                return false;
            }
        };
        JTable tabla = new JTable(modeloDatos);
        JScrollPane scrollPane = new JScrollPane(tabla);

        texto.setVisible(false);
        if(modeloDatos.getRowCount()>0){
            for(int k=0; k<modeloDatos.getRowCount();k++){
                modeloDatos.removeRow(k);
            }
        }
        if(lista.isEmpty()){
            texto.setVisible(true);
            this.texto.setForeground(java.awt.Color.red);
            texto.setText("No hay resultados");
        }else {
            for (Inmueble i : lista) {
                Object[] nuevaFila = {i.getnHabitaciones(), i.getnBanos(), i.getDimensiones(), i.getAscensor(), i.getPlanta(),i.getDireccion()};
                modeloDatos.addRow(nuevaFila);
            }
        }

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        JPanel select = new JPanel(new GridLayout(1, 2));
        select.add(volver);
        select.setVisible(true);

        JLabel etiqueta1 = new JLabel("Resultados de la busqueda");
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollPane, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.NORTH, texto);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, scrollPane);
        layout.putConstraint(SpringLayout.NORTH, select, 425, SpringLayout.NORTH, scrollPane);
        layout.putConstraint(SpringLayout.NORTH,texto,10,SpringLayout.NORTH,etiqueta1);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta1);

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setColumnSelectionAllowed(false);
        tabla.getSelectionModel().getLeadSelectionIndex();
        tabla.setRowSelectionAllowed(true);

        this.add(etiqueta1);
        this.add(texto);
        this.add(select);
        this.add(scrollPane);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));
        volver.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evento) {
        if(evento.getSource()==volver){
            gui.getControlador().volverBusqueda();
        }
    }

    public void creadaOK(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }

}
