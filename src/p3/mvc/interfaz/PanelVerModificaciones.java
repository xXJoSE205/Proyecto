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
    private String[] titulos = {"Precio", "Fecha Inicio", "Fecha Fin", "Vacacional", "Fianza" };
    private Object filas [][] = {};
    private GuiInmobiliaria gui;
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

    public PanelVerModificaciones(GuiInmobiliaria gui){
        this.gui = gui;

        this.lista = gui.getControlador().getOfertasRechazadas();
        final JTextArea modificaciones = new JTextArea();
        String cadena = ((Ofertante)gui.getControlador().getCliente()).getModificaciones();
        if(cadena!=null) {
            modificaciones.setText(((Ofertante) gui.getControlador().getCliente()).getModificaciones());
        }else{
            modificaciones.setText("No tienes propuestas");
        }
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        JTable tabla = new JTable(modeloDatos);
        JScrollPane scrollPane = new JScrollPane(tabla);
        for(Oferta o:lista){
            Object[] nuevaFila = {o.getPrecio(), o.getFechaInicio(), o.getFechaFin(), o.isVacacional(), o.getFianza()};
            modeloDatos.addRow(nuevaFila);
        }
        tabla.setPreferredScrollableViewportSize(new Dimension(600, 300));

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(volver);
        grupo.add(modificar);
        JPanel select = new JPanel(new GridLayout(1, 2));
        select.add(volver);
        select.add(modificar);
        select.setVisible(true);
        texto.setVisible(false);

        JLabel etiqueta1 = new JLabel("Modificaciones propuestas");
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, etiqueta1, 0, SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollPane, 0, SpringLayout.HORIZONTAL_CENTER, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.NORTH, etiqueta1);
        JScrollPane scrollBar = new JScrollPane(modificaciones);
        layout.putConstraint(SpringLayout.NORTH, scrollBar, 5, SpringLayout.SOUTH, scrollPane);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollBar, 0, SpringLayout.HORIZONTAL_CENTER, scrollPane);
        layout.putConstraint(SpringLayout.NORTH, texto, 5, SpringLayout.SOUTH, scrollBar);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, texto, 0, SpringLayout.HORIZONTAL_CENTER, scrollBar);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, select, 0, SpringLayout.HORIZONTAL_CENTER, texto);
        layout.putConstraint(SpringLayout.NORTH, select, 5, SpringLayout.SOUTH, texto);

        this.add(etiqueta1);
        this.add(select);
        this.add(texto);
        this.add(scrollBar);
        this.add(scrollPane);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 600));

        volver.addActionListener(this);
        modificar.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==volver){
            texto.setVisible(false);
            gui.getControlador().volverOfertante();
        }else if(e.getSource()==modificar){
            texto.setVisible(false);
            try {
                gui.getControlador().goModificarOferta(lista.get(tabla.getSelectedRow()));
            }catch (Exception exception){
                texto.setText("Selecciona oferta para modificarla");
                texto.setVisible(true);
                texto.setForeground(Color.red);
            }
        }
    }
}
