package p3.mvc.interfaz;

import p3.mvc.modelo.Inmueble;
import p3.mvc.modelo.Ofertante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelVerInmuebles extends JPanel implements ActionListener{
    private JLabel etiqueta1 = new JLabel("Resultados de la busqueda");
    private String[] titulos = {"Habitaciones", "Banos", "Dimensiones", "Ascensor", "Planta" };
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
    private JButton crear = new JButton("Crear Oferta");
    private List<Inmueble> lista;
    private JTable tabla = new JTable(modeloDatos);
    private JLabel texto = new JLabel("");

    public PanelVerInmuebles(GuiInmobiliaria gui){
        this.gui = gui;
        this.lista = ((Ofertante)gui.getControlador().getCliente()).getInmuebles();
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        JTable tabla = new JTable(modeloDatos);
        JScrollPane scrollPane = new JScrollPane(tabla);
        for(Inmueble i:lista){
            Object[] nuevaFila = {i.getnHabitaciones(), i.getnBanos(), i.getDimensiones(), i.getAscensor(), i.getPlanta()};
            modeloDatos.addRow(nuevaFila);
        }

        grupo.add(volver);
        select.add(volver);
        select.setVisible(true);

        layout.putConstraint(SpringLayout.WEST, etiqueta1, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, etiqueta1, 5, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.WEST, etiqueta1);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.NORTH, etiqueta1);
        layout.putConstraint(SpringLayout.WEST, select, 0, SpringLayout.WEST, scrollPane);
        layout.putConstraint(SpringLayout.NORTH, select, 425, SpringLayout.NORTH, scrollPane);

        this.add(etiqueta1);
        this.add(select);
        this.add(tabla);
        this.add(scrollPane);
        this.setVisible(true);

        volver.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==volver){
            gui.getControlador().volverOfertante();
        }else if(e.getSource()==crear){
            gui.getControlador().goCrearOferta(lista.get(tabla.getSelectedRow()));
        }
    }

    public void creadaOK(String texto) {
        this.texto.setText(texto);
        this.texto.setVisible(true);
        this.texto.setForeground(java.awt.Color.red);
    }
}